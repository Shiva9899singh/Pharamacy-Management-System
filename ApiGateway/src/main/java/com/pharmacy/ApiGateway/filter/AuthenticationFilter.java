package com.pharmacy.ApiGateway.filter;

import com.pharmacy.ApiGateway.exception.InvalidCredentialsException;
import com.pharmacy.ApiGateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RouteValidator routeValidator;

    // Constructor to initialize the filter with its configuration class
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            // Check if the request is for a secured route
            if (routeValidator.isSecured.test(exchange.getRequest())) {

                // Check if header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new InvalidCredentialsException("Header Authorization is Missing");
                }

                // Extracting the Authorization header
                String authHeaders = Objects.requireNonNull(exchange.getRequest().getHeaders()
                        .get(HttpHeaders.AUTHORIZATION)).get(0);

                if (authHeaders != null && authHeaders.startsWith("Bearer ")) {
                    authHeaders = authHeaders.substring(7);
                }
                try {
                    ServerHttpRequest request = exchange.getRequest();
                    jwtUtil.validateToken(authHeaders);

//                    String role = jwtUtil.extractRole(authHeaders);

//                    if (request.getURI().getPath().startsWith("/drugs") && !"ADMIN".equals(role)) {
//                        throw new InvalidCredentialsException("Forbidden: ADMIN role required", HttpStatus.FORBIDDEN);
//                    }

                } catch (Exception e) {
                    throw new InvalidCredentialsException("Invalid Credentials!!");
                }
            }

            return chain.filter(exchange);
        });

    }

    // Configuration class for the filter
    public static class Config {

    }
}
