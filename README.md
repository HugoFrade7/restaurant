# Restaurant App

This project exposes a graphql api that allows users to execute basic CRUD operations on `Restaurants` and `Dishes` entities.
A Dish is scoped by a Restaurant.

## Getting Started

It is possible to spin up the project both locally and remotely deploy it to AWS with terraform.

#### Prerequisites

- Terraform
- AWS Account
- Docker
- Docker-compose

### Local Deployment 

- `docker-compose up`

### Remote Deployment

Steps described below assume deployment on AWS eu-central-1 region.

1. Build EC2 KeyPair to allow ssh


2. Create an ECR repository: restaurant-backend


3. Login into AWS ECR
   
`aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin <aws_account_id>.dkr.ecr.eu-central-1.amazonaws.com`

4. Build docker image locally

`docker build -t <aws_account_id>.dkr.ecr.eu-central-1.amazonaws.com/restaurant-backend:latest .`
    
5. Push docker image to remote repository

`docker push <aws_account_id>.dkr.ecr.eu-central-1.amazonaws.com/restaurant-backend:latest`
    
6. Change variable values on `restaurant.auto.tfvars` accordingly


7. Deploy

```
cd provision
terraform get
terraform init
terraform apply --auto-approve
```