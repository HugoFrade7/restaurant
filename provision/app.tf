#######
# ALB #
#######
module "alb" {
  source  = "umotif-public/alb/aws"
  version = "~> 2.0"

  name_prefix        = "restaurant-alb"
  load_balancer_type = "application"
  internal           = false
  vpc_id             = module.vpc.vpc_id
  subnets            = module.vpc.public_subnets
}

resource "aws_lb_listener" "alb_80" {
  load_balancer_arn = module.alb.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = module.ecs-fargate.target_group_arn[0]
  }
}


module "security_group" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "~> 4.0"

  security_group_id = module.alb.security_group_id
  create_sg         = false

  ingress_cidr_blocks   = ["0.0.0.0/0"]
  ingress_rules = ["http-80-tcp", "all-icmp"]

}

#######
# ECS #
#######
resource "aws_ecs_cluster" "restaurant_ecs_cluster" {
  name = "restaurant-ecs-cluster"

  capacity_providers = ["FARGATE_SPOT", "FARGATE"]
  default_capacity_provider_strategy {
    capacity_provider = "FARGATE"
  }

  setting {
    name  = "containerInsights"
    value = "disabled"
  }

  tags = local.common_tags
}

module "ecs-fargate" {
  source  = "umotif-public/ecs-fargate/aws"
  version = "~> 6.1.0"

  name_prefix        = "restaurant-ecs-fargate"
  vpc_id             = module.vpc.vpc_id
  private_subnet_ids = module.vpc.private_subnets

  cluster_id = aws_ecs_cluster.restaurant_ecs_cluster.id

  task_container_image   = "942112512222.dkr.ecr.eu-central-1.amazonaws.com/restaurant-backend:latest"
  task_definition_cpu    = 256
  task_definition_memory = 1024

  task_container_port             = 9000
  task_container_assign_public_ip = false

  target_groups = [
    {
      target_group_name = "restaurant-tg"
      container_port    = 9000
    }
  ]

  health_check = {
    port = "traffic-port"
    path = "/actuator/health"
  }

  task_container_environment = {
    DATASOURCE_URL="jdbc:postgresql://restaurant-db.czithxfsjhgh.eu-central-1.rds.amazonaws.com:5432/restaurant"
    DATASOURCE_PASSWORD="1234-abcd"
  }
  depends_on = [module.alb, module.rds]

  tags = local.common_tags
}

resource "aws_security_group_rule" "task_ingress_9000" {
  security_group_id        = module.ecs-fargate.service_sg_id
  type                     = "ingress"
  protocol                 = "tcp"
  from_port                = 9000
  to_port                  = 9000
  source_security_group_id = module.alb.security_group_id
}
