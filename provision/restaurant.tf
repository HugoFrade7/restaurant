variable "aws_region" {
  default = "eu-central-1"
  type    = string
}

variable "db_name" {
  default = "restaurant"
  type    = string
}

variable "db_username" {
  default = "restaurant"
  type    = string
}

variable "db_password" {
  default = "restaurant1234"
  type    = string
}

variable "backend_image_tag" {
  default = "latest"
  type    = string
}

variable "allow_cidrs" {
  type = list(string)
}

terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.0"
    }
  }
}

locals {
  # Common tags to be assigned to all resources
  common_tags = {
    Service     = "restaurant"
    Owner       = "hugo"
    Environment = "demo"
  }
}

provider "aws" {
  region = var.aws_region
}
