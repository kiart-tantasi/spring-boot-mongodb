package com.example.springmongo.models;

import org.bson.Document;

public interface DocumentInterface<T> {
    public Document toDocument();
}
