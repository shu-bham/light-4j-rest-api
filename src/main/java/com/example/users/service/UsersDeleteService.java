package com.example.users.service;

import com.example.users.db.MongoStartupHookProvider;
import com.example.users.model.User;
import com.mongodb.client.MongoCollection;
import com.networknt.http.*;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersDeleteService implements HttpService<Void, String> {
    private static final Logger logger = LoggerFactory.getLogger(UsersDeleteService.class);

    @Override
    public ResponseEntity invoke(RequestEntity<Void> requestEntity) {
        HeaderMap responseHeaders = new HeaderMap();
        responseHeaders.add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        try {
            MongoCollection<User> userCollection = MongoStartupHookProvider.db.getCollection("Users", User.class);
            userCollection.drop();
            return new ResponseEntity<>("Deleted All Users!", responseHeaders, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Server Error", e);
            return new ResponseEntity<>("Error deleting records", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
