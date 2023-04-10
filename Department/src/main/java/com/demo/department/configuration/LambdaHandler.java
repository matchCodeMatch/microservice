package com.demo.department.configuration;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.demo.department.model.Department;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, Object> {



    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        Department department1 = new Department("1","IT");
        Department department2 = new Department("2","Computer Science");
        String requestMethod = input.getHttpMethod();
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent =
                new APIGatewayProxyResponseEvent();
        apiGatewayProxyResponseEvent.setStatusCode(200);
        switch (requestMethod){
            case "GET":{
                apiGatewayProxyResponseEvent.setBody(department2.toString());
                break;
            }
            case "POST":
            case "PUT":{
                apiGatewayProxyResponseEvent.setBody(department1.toString());
                break;
            }
            case "DELETE":{
                apiGatewayProxyResponseEvent.setStatusCode(204);
                apiGatewayProxyResponseEvent.setBody(department1.toString());
            }
        }
        return apiGatewayProxyResponseEvent;
    }
}