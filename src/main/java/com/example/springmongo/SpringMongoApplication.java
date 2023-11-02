package com.example.springmongo;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Collation;

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
				// newCollation.insertOne(new Document().append("fieldOne", "value"));
				// newCollation.insertOne(new Document().append("fieldOne", "value"));
				// newCollation.insertOne(new Document().append("fieldOne", "value"));

				// find
				final var found = newCollation.find(new Document().append("fieldOne", "value"));
				for (final var element : found) {
					System.out.println("element: " + element);
				}
				// MongoDatabase database = mongoClient.getDatabase("sample_mflix");
				// MongoCollection<Document> collection = database.getCollection("movies");
				// Document doc = collection.find(eq("title", "Back to the Future")).first();
				// if (doc != null) {
				// System.out.println(doc.toJson());
				// } else {
				// System.out.println("No matching documents found.");
				// }
			}
		};
	}
}

record Person(String fullname) {
};
