package com.example.users.service;

import com.example.users.db.MongoStartupHookProvider;
import com.example.users.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mongodb.client.MongoCollection;
import com.networknt.config.Config;
import com.networknt.http.*;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LoadGetService implements HttpService<Void, String> {
    private static final Logger logger = LoggerFactory.getLogger(LoadGetService.class);
    public static final String USERS_FILE_PATH = "users.json";

    @Override
    public ResponseEntity invoke(RequestEntity<Void> requestEntity) {
        try {
            InputStream inputStream = Config.getInstance().getInputStreamFromFile(USERS_FILE_PATH);
            List<User> users = Config.getInstance().getMapper().readValue(inputStream, new TypeReference<>() {
            });
            logger.info("no. of users to be loaded:{}", users.size());
            MongoCollection<User> collection = MongoStartupHookProvider.db.getCollection("Users", User.class);
            collection.insertMany(users);
            HeaderMap responseHeaders = new HeaderMap();
            responseHeaders.add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String body = "Inserted " + users.size() + " user records from " + USERS_FILE_PATH;
            logger.info(body);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(body, responseHeaders, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            logger.error("IO Exception", e);
            return new ResponseEntity<>("File Read Failed", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Exception", e);
            return new ResponseEntity("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
