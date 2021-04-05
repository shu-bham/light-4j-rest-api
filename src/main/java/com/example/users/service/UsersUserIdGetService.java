package com.example.users.service;

import com.example.users.db.MongoStartupHookProvider;
import com.example.users.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.networknt.config.Config;
import com.networknt.http.*;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersUserIdGetService implements HttpService<Void, String> {
    private static final Logger logger = LoggerFactory.getLogger(UsersUserIdGetService.class);

    @Override
    public ResponseEntity invoke(RequestEntity<Void> requestEntity) {
        HeaderMap responseHeaders = new HeaderMap();
        responseHeaders.add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        MongoCollection<User> collection = MongoStartupHookProvider.db.getCollection("Users", User.class);
        int userId = Integer.parseInt(requestEntity.getPathParameters().get("userId").getFirst());
        logger.info("userId:{}", userId);
        User user = collection.find(Filters.eq("studentId", userId)).first();
        if (user == null) {
            return new ResponseEntity<>("User Doesn't Exist", responseHeaders, HttpStatus.OK);
        } else {
            try {
                return new ResponseEntity<>(Config.getInstance().getMapper().writeValueAsString(user), responseHeaders, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                logger.error("JSON exception", e);
                return new ResponseEntity<>("Server Error", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
