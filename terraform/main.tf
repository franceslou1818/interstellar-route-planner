import {
  to = aws_lambda_function.frances-lambda2
  id = "frances-lambda2"
}

import {
  to = aws_iam_role.lambda_role2
  id = "lambda_role2"
}

import {
  to = aws_api_gateway_rest_api.frances-api-gateway2
  id = "08wwn7u3s0"
}

import {
  to = aws_lambda_permission.api_gateway_lambda_permission
  id = "frances-lambda2/AllowExecutionFromAPIGateway"
}

import {
  to = aws_api_gateway_resource.lambda_resource
  id = "08wwn7u3s0/xa97nwrfsc"
}

