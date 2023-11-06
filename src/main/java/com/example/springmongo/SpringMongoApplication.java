package com.example.springmongo;

import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.springmongo.models.User;
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
				final var newUser = new User("John", "Doe", 22);
				final var collection = mongoClient.getDatabase("test").getCollection("users");
				collection.insertOne(newUser.toDocument());

				// find and clean up
				final var documentA = collection
						.findOneAndDelete(new Document().append("firstName", newUser.getFirstName()));
				if (documentA == null) {
					System.out.println("document A is not found in DB");
				} else {
					System.out.println("document A is found in DB");
					// convert from document to user object
					final var john = new User(documentA);
					System.out.println(
							String.format("found user with first name %s, last name %s and age %d",
									john.getFirstName(), john.getLastName(), john.getAge()));
				}

				// not found case
				final var documentB = collection.findOneAndDelete(new Document().append("firstName", "not exist"));
				if (documentB == null) {
					System.out.println("document B is not found in DB");
				} else {
					System.out.println("document B is found in DB");

				}
			}
		};
	}

	// TODO: create config for MongoClient

	// TODO: create MongoService

	// TODO: try new sync java driver
}
