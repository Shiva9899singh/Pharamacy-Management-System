package com.pharmacy.ApiGateway.filter;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/signup",
            "/auth/login",
            "/auth/validate",
            "/swagger-ui/index.html",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));


}
