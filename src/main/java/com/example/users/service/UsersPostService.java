package com.example.users.service;

import com.example.users.db.MongoStartupHookProvider;
import com.example.users.model.User;
import com.mongodb.client.MongoCollection;
import com.networknt.http.*;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class UsersPostService implements HttpService<List<User>, String> {
    private static final Logger logger = LoggerFactory.getLogger(UsersPostService.class);
    private static final String GET_URI = "http://localhost:8080/v1/users/";

    @Override
    public ResponseEntity invoke(RequestEntity<List<User>> requestEntity) {
        List<User> users = requestEntity.getBody();
        HeaderMap responseHeaders = new HeaderMap();
        responseHeaders.add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        try {
            MongoCollection<User> collection = MongoStartupHookProvider.db.getCollection("Users", User.class);
            collection.insertMany(users);
            // todo: add to links header
//            List<String> links = users.stream().map(user -> GET_URI + user.getId()).collect(Collectors.toList());
            String body = "Inserted " + users.size() + " users into DB";
            logger.info(body);
            return new ResponseEntity<>(body, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Server Error", e);
            return new ResponseEntity<>("Error inserting records", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
