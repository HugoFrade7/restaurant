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
