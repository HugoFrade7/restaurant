aws_region = "eu-central-1"

# Database
db_name     = "restaurant"
db_username = "restaurant"
db_password = "1234-abcd"

# Backend
# TODO replace aws_account_id placeholder
backend_image = "<aws_account_id>.dkr.ecr.eu-central-1.amazonaws.com/restaurant-backend:latest"
allow_cidrs = ["0.0.0.0/0"]

# Bastion
bastion_key_pair_name = "eu-central-1-restaurant"