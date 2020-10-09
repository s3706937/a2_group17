package application;

import java.util.Scanner;
import java.util.StringTokenizer;

/* Appointment Management System class */
public class application {

	// shared Scanner which can be used by all helper methods below
	private static Scanner SC = new Scanner(System.in);


	// Main class
	public static void main(String[] args) {
		// Menu system for the program
		String selection;
		// Check user input until the exit function is met, i.e. X or x
		do {
			// Display Menu Options
			
			String WELCOME_BANNER = "Welcome to the IT ticketing system";
			banner(WELCOME_BANNER.length());
			System.out.println(WELCOME_BANNER);
			banner(WELCOME_BANNER.length());
			System.out.println();
			
			System.out.println("A. Log in (requires an existing account)");
			System.out.println("B. Create an account");
			System.out.println("C. Reset password");
			System.out.println("X. Exit ticketing system");
			System.out.println();

			// Prompt user to enter selection
			System.out.print("Enter selection: ");
			selection = SC.nextLine();

			// Blank line for formatting
			System.out.println();

			// Validate selection input length, ensure it is only 1 character in length
			if (selection.length() != 1) {
				System.out.println("Error - invalid selection!");
			}
			// Otherwise, take input and go to appropriate method
			else {

				// process user's selection
				switch (selection.toUpperCase()) {
				case "A":
					attemptLogin();
					break;

				case "B":
					createAccount();
					break;

				case "C":
					resetPassword();
					break;

				case "X":
					System.out.println("Exiting the program...");
					break;

				default:
					System.out.println("Error - invalid selection!");
				}
			}
			System.out.println();

		} while (!selection.equalsIgnoreCase("X"));
	}


	private static void banner(int bannerLength) {
		for (int i = 0; i < bannerLength; ++i) {
			System.out.print("=");
		}
		System.out.println();
	}
	
	public static String get_user_input() {
		String input = "";
		boolean valid = false;
		do {
			input = SC.nextLine();
			if (input.length() < 1) {
				System.out.print("Error, please enter valid input: ");
				valid = false;
			} else {
				valid = true;
			}
		} while (valid == false);

		return input;
	}
	
	private static void attemptLogin() {
		String ATTEMPTLOGIN_BANNER = "Login to Ticketing System";
		banner(ATTEMPTLOGIN_BANNER.length());
		System.out.println(ATTEMPTLOGIN_BANNER);
		banner(ATTEMPTLOGIN_BANNER.length());
		System.out.println();

		System.out.println("Please enter login credentials");
		System.out.println();
		
		System.out.print("Please enter your username: ");
		String username = get_user_input();
		System.out.print("Please enter your password: ");
		String passwd = get_user_input();
		
		System.out.print("Your username is: ");
		System.out.println(username);
		System.out.print("Your username is: ");
		System.out.println(passwd);
		
		// Add more code here
	}

	private static void createAccount() {
		String CREATEACC_BANNER = "Create account";
		banner(CREATEACC_BANNER.length());
		System.out.println(CREATEACC_BANNER);
		banner(CREATEACC_BANNER.length());
		System.out.println();

		// Add more code here
		System.out.println("Prompts to create an account");
		System.out.println();
	}

	private static void resetPassword() {
		String RESETPWD_BANNER = "Reset Password";
		banner(RESETPWD_BANNER.length());
		System.out.println(RESETPWD_BANNER);
		banner(RESETPWD_BANNER.length());
		System.out.println();
		
		// Add more code here
		System.out.println("Prompts tp reset password");
		System.out.println();
	}

}
