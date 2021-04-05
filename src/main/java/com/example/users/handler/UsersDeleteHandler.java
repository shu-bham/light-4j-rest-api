package com.example.users.handler;

import com.example.users.service.UsersDeleteService;
import com.networknt.handler.LightHttpHandler;
import com.networknt.http.HttpMethod;
import com.networknt.http.RequestEntity;
import com.networknt.http.ResponseEntity;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;

import java.util.Deque;
import java.util.Map;

/**
 * For more information on how to write business handlers, please check the link below.
 * https://doc.networknt.com/development/business-handler/rest/
 */
public class UsersDeleteHandler implements LightHttpHandler {
    UsersDeleteService service;

    public UsersDeleteHandler() {
        this.service = new UsersDeleteService();
    }


    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        HeaderMap requestHeaders = exchange.getRequestHeaders();
        Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();
        Map<String, Deque<String>> pathParameters = exchange.getPathParameters();
        HttpMethod httpMethod = HttpMethod.resolve(exchange.getRequestMethod().toString());
        RequestEntity requestEntity = new RequestEntity<>(null, requestHeaders, httpMethod, null, null, queryParameters, pathParameters);
        ResponseEntity<String> responseEntity = service.invoke(requestEntity);
        responseEntity.getHeaders().forEach(values -> {
            exchange.getResponseHeaders().add(values.getHeaderName(), values.getFirst());
        });
        exchange.setStatusCode(responseEntity.getStatusCodeValue());
        exchange.getResponseSender().send(responseEntity.getBody());
    }
}
