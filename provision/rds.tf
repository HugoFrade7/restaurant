resource "aws_security_group" "db_sg" {
  name   = "db-sg"
  vpc_id = module.vpc.vpc_id

  ingress {
    from_port   = 5432
    protocol    = "tcp"
    to_port     = 5432
    cidr_blocks = ["0.0.0.0/0"]
    description = "allow anyone to connect"
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = local.common_tags
}

module "rds" {
  source  = "terraform-aws-modules/rds/aws"
  version = "~> 3.0"

  identifier = "restaurant-db"

  publicly_accessible    = false
  subnet_ids             = tolist(module.vpc.private_subnets)
  vpc_security_group_ids = [aws_security_group.db_sg.id]

  instance_class    = "db.t3.medium"
  allocated_storage = "5"

  engine         = "postgres"
  engine_version = "13.2"
  port           = "5432"

  # credentials
  username = "restaurant"
  password = "1234-abcd"
  name     = "restaurant"

  create_db_option_group    = false
  create_db_parameter_group = false
  create_monitoring_role    = false

  tags = local.common_tags
}