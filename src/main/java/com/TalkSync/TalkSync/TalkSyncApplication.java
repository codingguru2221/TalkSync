package com.TalkSync.TalkSync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TalkSyncApplication {

	public static void main(String[] args) {
		try {
			ApplicationContext context = SpringApplication.run(TalkSyncApplication.class, args);
			displayStartupInfo();
		} catch (Exception e) {
			System.err.println("========================================");
			System.err.println(" ERROR STARTING APPLICATION ");
			System.err.println("========================================");
			System.err.println("The application failed to start due to a database connection issue.");
			System.err.println("");
			System.err.println("Common solutions:");
			System.err.println("1. Make sure MySQL is running");
			System.err.println("2. Verify the database 'talksync' exists");
			System.err.println("3. Check your credentials in application.properties");
			System.err.println("4. Run update-db-credentials.bat to update your credentials");
			System.err.println("");
			System.err.println("Error details:");
			e.printStackTrace();
			System.err.println("========================================");
		}
	}
	
	private static void displayStartupInfo() {
		System.out.println("========================================");
		System.out.println(" TalkSync Application Started ");
		System.out.println("========================================");
		System.out.println("Make sure MySQL is running with the following configuration:");
		System.out.println("  Database: talksync");
		System.out.println("  Username: Check application.properties");
		System.out.println("  Password: Check application.properties");
		System.out.println("");
		System.out.println("If you're having connection issues:");
		System.out.println("  1. Run test-db-connection.bat to test your credentials");
		System.out.println("  2. Run update-db-credentials.bat to update your credentials");
		System.out.println("  3. Make sure the 'talksync' database exists");
		System.out.println("");
		System.out.println("API Endpoints:");
		System.out.println("  Authentication:");
		System.out.println("    POST /api/auth/register - Register a new user");
		System.out.println("    POST /api/auth/login - Login with existing credentials");
		System.out.println("");
		System.out.println("  Contacts:");
		System.out.println("    POST /api/contacts/add - Add a contact");
		System.out.println("    GET /api/contacts/list - Get user's contacts");
		System.out.println("    GET /api/contacts/pending - Get pending contact requests");
		System.out.println("    POST /api/contacts/accept - Accept a contact request");
		System.out.println("");
		System.out.println("Application is running on: http://localhost:8080");
		System.out.println("========================================");
	}

}