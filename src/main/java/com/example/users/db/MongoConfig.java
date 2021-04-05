package com.example.users.db;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MongoConfig {
    @JsonIgnore
    String description;
    String connectionString;
    String name;

    public MongoConfig() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}