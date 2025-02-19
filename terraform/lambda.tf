# Configure the AWS Provider
provider "aws" {
  region = "eu-north-1"
  # add your keys here
  access_key = "xyz"
  secret_key = "xyz"
}

resource "aws_iam_role" "lambda_role" {
  name = "lambda_role"

  # Terraform's "jsonencode" function converts a
  # Terraform expression result to valid JSON syntax.
  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Sid    = ""
        Principal = {
          Service = "lambda.amazonaws.com"
        }
      },
    ]
  })

  tags = {
    tag-key = "springboot-lambda"
  }
}

resource "aws_iam_role_policy_attachment" "basic_permission" {
  role       = aws_iam_role.lambda_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

resource "aws_lambda_function" "frances-lambda2" {
  # If the file is not in the current working directory you will need to include a
  # path.module in the filename.
  filename      = "${path.module}/../target/interstellar.jar"
  source_code_hash  = "${path.module}/../target/interstellar.jar"
  function_name = "frances-lambda2"
  role          = aws_iam_role.lambda_role.arn
  handler       = "com.tkc.interstellar_route_planner.handler.StreamLambdaHandler::handleRequest"

  # source_code_hash = data.archive_file.lambda.output_base64sha256

  runtime = "java17"
  timeout = 900
  memory_size = 1024

  environment {
    variables = {
      application_env = "dev"
    }
  }
}