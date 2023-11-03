package com.example.springmongo;

import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@SpringBootApplication
public class SpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return (args) -> {
			final var uri = "mongodb://root:password@localhost:27017";
			try (MongoClient mongoClient = MongoClients.create(uri)) {
				// insert
				final var newDb = mongoClient.getDatabase("new");
				final var newCollation = newDb.getCollection("newCollection");
				newCollation.insertOne(new Document().append("fieldOne", "value"));
				// find
				final var found = newCollation.find(new Document().append("fieldOne", "value"));
				for (final var element : found) {
					System.out.println("element: " + element);
				}
			}

			// TODO: create MongoService
			// TODO: find a way to map java object to document
		};
	}
}

record Person(String fullname) {
};
