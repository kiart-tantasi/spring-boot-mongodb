package com.example.springmongo.models;

import org.bson.Document;

// NOTE: might use lombok for less code
public class User implements DocumentInterface<User> {
    private String firstName;
    private String lastName;
    private Integer age;

    public User(Document document) {
        this.firstName = document.getString("firstName");
        this.lastName = document.getString("lastName");
        this.age = document.getInteger("age");
    }

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    @Override
    public Document toDocument() {
        return new Document().append("firstName", this.firstName).append("lastName", this.lastName)
                .append("age", this.age);
    }
}
