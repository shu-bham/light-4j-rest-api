package com.example.users.service;

import com.example.users.db.MongoStartupHookProvider;
import com.example.users.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.networknt.http.*;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersUserIdDeleteService implements HttpService<Void, String> {
    private static final Logger logger = LoggerFactory.getLogger(UsersUserIdDeleteService.class);

    @Override
    public ResponseEntity invoke(RequestEntity<Void> requestEntity) {
        HeaderMap responseHeaders = new HeaderMap();
        responseHeaders.add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        int userId = Integer.parseInt(requestEntity.getPathParameters().get("userId").getFirst());
        try {
            MongoCollection<User> collection = MongoStartupHookProvider.db.getCollection("Users", User.class);
            logger.info("userId:{}", userId);
            User user = collection.findOneAndDelete(Filters.eq("studentId", userId));
            if (user != null) {
                logger.info("User deleted userId:{}", userId);
                return new ResponseEntity<>("User with userId:" + userId + " deleted successfully!", responseHeaders, HttpStatus.OK);
            } else
                return new ResponseEntity<>("No User found with userId:" + userId, responseHeaders, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Server Error", e);
            return new ResponseEntity<>("Error Deleting User with userId:" + userId, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
