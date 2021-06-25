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

variable "backend_image" {
  type    = string
}

variable "allow_cidrs" {
  type = list(string)
}

variable "bastion_key_pair_name" {
  type = string
}
