package com.example.springmongo.models;

import org.bson.Document;

public class User implements DocumentInterface<User> {
    private String firstName;
    private String lastName;
    private Integer age;

    public User(Document document) {
        this.firstName = document.getString("firstName");
        this.lastName = document.getString("lastName");
        this.age = document.getInteger("age");
    }

    @Override
    public Document toDocument() {
        return new Document().append("firstName", this.firstName).append("lastName", this.lastName)
                .append("age", this.age);
    }
}
