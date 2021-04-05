package com.example.users.service;

import com.example.users.db.MongoStartupHookProvider;
import com.example.users.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import com.networknt.http.*;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class UsersUserIdPutService implements HttpService<User, String> {
    private static final Logger logger = LoggerFactory.getLogger(UsersUserIdPutService.class);

    @Override
    public ResponseEntity invoke(RequestEntity<User> requestEntity) {
        User user = requestEntity.getBody();
        logger.debug(user.toString());
        HeaderMap responseHeaders = new HeaderMap();
        responseHeaders.add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        MongoCollection<User> users = MongoStartupHookProvider.db.getCollection("Users", User.class);
        UpdateResult updateResult = users.replaceOne(Filters.eq("studentId", user.getStudentId()), user);
        String body = "Failed to update";
        if (updateResult.wasAcknowledged()) {
            body = "User updated successfully!";
//            long id = Objects.requireNonNull(updateResult.getUpsertedId()).asInt64().longValue();
            String recordUrl = "http://localhost:8080/v1/users/" + user.getStudentId();
            logger.info("{}, {}", body, recordUrl);
            responseHeaders.add(new HttpString("link"), recordUrl);
            return new ResponseEntity<>(body, responseHeaders, HttpStatus.OK);
        }

        logger.info(body);
        return new ResponseEntity<>(body, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
