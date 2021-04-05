package com.example.users.db;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.networknt.config.Config;
import com.networknt.server.StartupHookProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class MongoStartupHookProvider implements StartupHookProvider {

    private static final Logger logger = LoggerFactory.getLogger(MongoStartupHookProvider.class);

    static String CONFIG_NAME = "mongo";
    public static MongoDatabase db;

    public void onStartup() {
        logger.info("MongoStartupHookProvider is called");
        initDataSource();
        logger.info("MongoStartupHookProvider db = {} ", db);
    }

    static void initDataSource() {
        MongoConfig config = (MongoConfig) Config.getInstance().getJsonObjectConfig(CONFIG_NAME, MongoConfig.class);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(config.getConnectionString()))
                .codecRegistry(codecRegistry)
                .build();
        MongoClient mongoClient = MongoClients.create(clientSettings);
        db = mongoClient.getDatabase(config.getName());
    }
}