package com.tkc.interstellar_route_planner.handler;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.tkc.interstellar_route_planner.InterstellarRoutePlannerApplication;

import java.time.Instant;

@SuppressWarnings({"unchecked", "rawtypes", "deprecation"})
public class StreamLambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {

        try {
            handler = (SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse>)
                    ((SpringBootProxyHandlerBuilder) new SpringBootProxyHandlerBuilder<>()
                            .defaultProxy()
                            .asyncInit(Instant.now().toEpochMilli()))
                            .springBootApplication(InterstellarRoutePlannerApplication.class)
                            .asyncInit()
                            .buildAndInitialize();
//            handler = SpringBootLambdaContainerHandler
//                    .getAwsProxyHandler(InterstellarRoutePlannerApplication.class);
        } catch (ContainerInitializationException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the container....");

        }

    }


    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        return handler.proxy(awsProxyRequest, context);
    }
}
