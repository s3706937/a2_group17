package application;

import java.io.*; 
import java.lang.*; 
import java.util.*; 


/* Ticketing system class */
public class application {

	// Create array to hold all user information, max 50 users
	private static String [][] userDatabase = new String [50][5];
	// Set number of users to 0
	private static int num_users = 0;

	// Create a link between where data is in the user database array and its id
	private static final int USERNAME_INDEX = 0;
	private static final int NAME_INDEX = 1;
	private static final int EMAIL_INDEX = 2;
	private static final int PASSWD_INDEX = 3;
	private static final int ROLE_INDEX = 4;

	// Create array to hold all ticket information, max 500 tickets
	private static final int NUM_TICKET_FIELDS = 10;
	private static final int MAX_NUM_TICKETS = 500;
	private static String ticketDatabase[][] = new String [MAX_NUM_TICKETS][NUM_TICKET_FIELDS];
	private static int num_tickets = 0;

	// Create a link between where data is in the ticket database array and its id
	private static final int TICKET_FNAME = 0;
	private static final int TICKET_LNAME = 1;
	private static final int TICKET_STAFFID = 2;
	private static final int TICKET_EMAIL = 3;
	private static final int TICKET_CONTACT = 4;
	private static final int TICKET_DESCRIPTION = 5;
	private static final int TICKET_SEVERITY = 6;
	private static final int TICKET_STATUS = 7;
	private static final int TICKET_ID = 8;
	private static final int TICKET_SERVICEDESK = 9;

	// shared Scanner which can be used by all helper methods below
	private static Scanner SC = new Scanner(System.in);

	// Main class
	public static void main(String[] args) {
		// Insert all technicians and a demo user
		insertDefaultUsers();

		// Menu system for the program
		String selection;

		// Check user input until the exit function is met, i.e. X or x
		do {
			// Display Menu Options
			String WELCOME_BANNER = "Welcome to the IT ticketing system";
			banner(WELCOME_BANNER);
			System.out.println("A. Log in (requires an existing account)");
			System.out.println("B. Create an account");
			System.out.println("C. Reset password");
			System.out.println("X. Exit ticketing system");
			System.out.println();

			// Prompt user to enter selection
			System.out.print("Enter selection: ");
			selection = SC.nextLine();
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


	// Create banner title for all menu options
	private static void banner(String bannerName) {
		// Set number of symbols to length of string
		for (int i = 0; i < bannerName.length(); ++i) {
			System.out.print("=");
		}
		System.out.println();

		// Print banner name
		System.out.println(bannerName);

		for (int i = 0; i < bannerName.length(); ++i) {
			System.out.print("=");
		}
		System.out.println();
	}

	// Validate user input to ensure it is semi valid
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
		// Return whether it is valid or not
		return input;
	}

	// Checks whether a user exists in the system
	private static boolean check_login(final String[][] userDatabase, final String USERNAME, final String PASSWORD) {
		boolean valid = false;

		try {
			for(int i = 0; i < userDatabase.length; ++i){

				// Username and password must match
				if(USERNAME.contentEquals(userDatabase[i][USERNAME_INDEX])) {
					if(PASSWORD.contentEquals(userDatabase[i][PASSWD_INDEX])) {
						// If user exists, set to true
						valid = true;
						break;
					}
				}
			}
		} catch (NullPointerException npe) {
			System.out.println("\nError! User does not exist\n");
			attemptLogin();
		}
		// If user does not exist, return false
		return valid;
	}

	// Main login
	private static void attemptLogin() {
		boolean valid = false;
		// Login banner
		String ATTEMPTLOGIN_BANNER = "Login to Ticketing System";
		banner(ATTEMPTLOGIN_BANNER);

		// Request login credentials
		System.out.println("\nPlease enter login credentials\n");

		System.out.print("Please enter your username: ");
		String username = get_user_input();
		System.out.print("Please enter your password: ");
		String passwd = get_user_input();

		// Left debugging code in below for checking characters are typed correctly
		//System.out.println("\nYour username is: " + username);
		//System.out.println("Your password is: " + passwd + '\n');

		//Check login details
		//		do {
		//			valid = check_login(userDatabase, username, passwd);
		//		} while(!valid);

		// If valid login exists, then start next menu system
		if (check_login(userDatabase, username, passwd))
		{
			// Logged in menu system for the program
			String selection;

			// Check user input until the exit function is met, i.e. X or x
			do {
				// Display Menu Options
				final String MENU_BANNER = "IT ticketing system - Main Menu";
				banner(MENU_BANNER);

				System.out.println("\nA. Create an new ticket");
				System.out.println("B. View all open tickets");
				System.out.println("C. Change ticket priority (Technicians only)");
				System.out.println("D. Close ticket (Technicians only)");
				System.out.println("X. Logout\n");

				// Prompt user to enter selection
				System.out.print("Enter selection: ");
				selection = SC.nextLine();

				// Validate selection input length, ensure it is only 1 character in length
				if (selection.length() != 1) {
					System.out.println("Error - invalid selection!");
				}
				// Otherwise, take input and go to appropriate method
				else {

					// process user's selection
					switch (selection.toUpperCase()) {
					case "A":
						createTicket(username);
						break;

					case "B":
						viewTickets();
						break;

					case "C":
						// check for being an Technician TBD
						changeTicketPriority();
						break;

					case "D":
						// check for being an Technician TBD
						closeTicket();
						break;

					case "X":
						System.out.println("Logging out...");
						break;

					default:
						System.out.println("Error - invalid selection!");
					}
				}
				System.out.println();

			} while (!selection.equalsIgnoreCase("X"));
		} else {
			// Error if login is incorrect
			System.out.println("Incorrect Login");
		}
		System.out.println();
	}

	// Create an account for the a new user in the system
	private static void createAccount() {
		// Create account banner
		String CREATEACC_BANNER = "Create account";
		banner(CREATEACC_BANNER);
		System.out.println("Prompts to create an account");

		// Set function variables
		String username = "";
		String name = "";
		String email = "";
		String passwd = "";

		// Request username & validate
		System.out.print("Please enter a username: ");
		username = get_user_input();

		// Check for uniqueness - Later Sprint

		// Request name & validate
		System.out.print("Please enter your name : ");
		name = get_user_input();

		// Request password & validate
		System.out.print("Please enter your email address: ");
		email = get_user_input();

		// check validity - Later Sprint

		// check for uniqueness  - Later Sprint

		System.out.print("Please enter your password: ");
		passwd = get_user_input();

		// Debug for checking number of users
		//System.out.print("Total number of users: " + num_users);

		// All created users are staff not technicians 
		String defaultRole = "0";

		// Add user data to userDatabase array, and increase number of users value
		userDatabase[num_users][USERNAME_INDEX] = username;
		userDatabase[num_users][NAME_INDEX] = name;
		userDatabase[num_users][EMAIL_INDEX] = email;
		userDatabase[num_users][PASSWD_INDEX] = passwd;
		userDatabase[num_users][ROLE_INDEX] = defaultRole;
		++num_users;

		// Inform the user the accounts was created successfully
		System.out.println("Account " + username + " created");

		// Debug for checking number of users
		//System.out.print("Total number of users: " + num_users);
		System.out.println();

		// Debug for showing all users created after account created
		/*for (int i = 0; i < num_users; i++) {
			System.out.print(userDatabase[i][0] + ": ");
			for (int j = 1; j < userDatabase[i].length; j++) {
				System.out.print(userDatabase[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		 */
	}

	// Function to reset password - later sprint
	private static void resetPassword() {
		String RESETPWD_BANNER = "Reset Password";
		banner(RESETPWD_BANNER);
		System.out.println("Prompts to reset password");
		System.out.println("!!!FUNCTIONALITY NOT YET COMPLETED!!!");

		// Add code here to reset password

		System.out.println();
	}

	// Function to check severity of ticket is valid
	private static boolean check_severity(final String severity) {
		boolean valid = false;
		if(severity.contentEquals("1") || severity.contentEquals("2") || severity.contentEquals("3")) {
			valid = true;
		} else {
			System.out.println("Error! Please enter correct severity level (1 = Low | 2 = Medium | 3 = High): ");
		}
		return valid;
	}

	private static String[] set_ticket_severity(final String severity) {

		String[] severity_rating = new String[2];
		final int SERVICE_DESK = 1;
		final int RATING = 0;
		switch(severity) {
		case "1" :
			severity_rating[RATING] = "Low";
			severity_rating[SERVICE_DESK] = "1";
			break;
		case "2" :
			severity_rating[RATING] = "Medium";
			severity_rating[SERVICE_DESK] ="1";
			break;
		case "3" :
			severity_rating[RATING] = "High";
			severity_rating[SERVICE_DESK] = "2";
			break;
		}
		return severity_rating;
	}

	// Function to create ticket in the system
	private static void createTicket(final String USERNAME)
	{
		String[] full_name;
		String username = "";
		String[] severity_rating;
		String severity = "";
		String service_desk = "";
		String fname = "";
		String lname = "";
		String email = "";
		String ticket_id = "";
		final int FNAME_INDEX = 0;
		final int LNAME_INDEX = 1;
		final String CREATETICKET_BANNER = "Create a ticket";
		banner(CREATETICKET_BANNER);
		System.out.println("\nEnter details for ticket below");

		//Search for user to pre enter user's details for ticket
		for(int i = 0; i < userDatabase.length; ++i){

			if(USERNAME.contentEquals(userDatabase[i][USERNAME_INDEX])) {
				username = userDatabase[i][USERNAME_INDEX];
				full_name = userDatabase[i][NAME_INDEX].split(" ");
				fname = full_name[FNAME_INDEX];
				lname = full_name[LNAME_INDEX];
				email = userDatabase[i][EMAIL_INDEX];
				ticket_id = String.valueOf(num_tickets);;
				break;
			}
		}
		//Get remaining data from user
		System.out.print("\nPlease enter your staffID: ");
		String staffID = get_user_input();
		System.out.print("\nPlease enter your contact number: ");
		String contact_number = get_user_input();
		System.out.print("\nPlease enter a brief description of the problem: ");
		String description = get_user_input();
		System.out.print("\nPlease set the severity (1 = Low | 2 = Medium | 3 = High): ");
		do {
			severity = get_user_input();
		} while(!check_severity(severity));

		severity_rating = set_ticket_severity(severity);

		//Display final ticket to user to check
		System.out.println("Check the final ticket below");
		System.out.println("\nTicket ID: " + ticket_id + "\nFirst Name: " + fname + "\nLast Name: " + lname + "\nStaffID: " + staffID + "\nEmail: "
				+ email + "\nContact Number: " + contact_number + "\nDescription: " + description + "\nSeverity: " + severity_rating[0]);
		System.out.print("\nIf the above details are correct type 'Y' else 'N' to restart: ");
		String confirmation = get_user_input();
		if(!confirmation.toUpperCase().contentEquals("Y")) {
			createTicket(username);
		}
		//Create ticket
		ticketDatabase[num_tickets][TICKET_FNAME] = fname;
		ticketDatabase[num_tickets][TICKET_LNAME] = lname;
		ticketDatabase[num_tickets][TICKET_STAFFID] = staffID;
		ticketDatabase[num_tickets][TICKET_EMAIL] = email;
		ticketDatabase[num_tickets][TICKET_CONTACT] = contact_number;
		ticketDatabase[num_tickets][TICKET_DESCRIPTION] = description;
		ticketDatabase[num_tickets][TICKET_SEVERITY] = severity;
		ticketDatabase[num_tickets][TICKET_STATUS] = "Open";
		ticketDatabase[num_tickets][TICKET_ID] = ticket_id;
		ticketDatabase[num_tickets][TICKET_SERVICEDESK] = service_desk; 
		++num_tickets;

		System.out.println("\nTicket successfully created!");
	}

	// Function to view all tickets assigned to a person
	private static void viewTickets()
	{
		String VIEWTICKET_BANNER = "View your tickets";
		banner(VIEWTICKET_BANNER);
		System.out.println("Show me all your tickets ");
		System.out.println("!!!FUNCTIONALITY NOT YET COMPLETED!!!");
		// show tickets
		// if tech show closed ones too
	}

	//Function to display a single entry from any database
	private static boolean display_entry(final int DATABASE_INDEX, final String[][] DATABASE_TYPE, final String MSG, final boolean MSG_REQUIRED){

		if(MSG_REQUIRED) {
			System.out.println("\n" + MSG);
		}
		System.out.println("\nTicket ID: " + DATABASE_TYPE[DATABASE_INDEX][TICKET_ID] + "\nFirst Name: " + 
		DATABASE_TYPE[DATABASE_INDEX][TICKET_FNAME] + "\nLast Name: " + DATABASE_TYPE[DATABASE_INDEX][TICKET_LNAME] + "\nStaffID: " 
				+ DATABASE_TYPE[DATABASE_INDEX][TICKET_STAFFID] + "\nEmail: " + DATABASE_TYPE[DATABASE_INDEX][TICKET_EMAIL] 
						+ "\nContact Number: " + DATABASE_TYPE[DATABASE_INDEX][TICKET_CONTACT] + "\nDescription: " + 
				DATABASE_TYPE[DATABASE_INDEX][TICKET_DESCRIPTION] + "\nSeverity: " + DATABASE_TYPE[DATABASE_INDEX][TICKET_SEVERITY]);

		return true;


	}

	//Function to search either the ticket or user database and return desired search value
	private static int database_search(final String[][] DATABASE_TYPE, final int NUM_ENTRIES, final String SEARCH_KEY, final int DATABASE_FIELDS){
		int index = 0;
		boolean found = false;

		for(int i = 0; i < NUM_ENTRIES; ++i) {
			for(int j = 0; j < DATABASE_FIELDS; ++j) {
				//Search through the database fields to find a match with the key
				if(SEARCH_KEY.contentEquals(DATABASE_TYPE[i][j])) {
					index = i;
					found = true;
					break;
				}
			}
		}

		if(found) {
			return index;
		} else {
			return -1;
		}
	}

	// Function to change ticket priority
	private static void changeTicketPriority()
	{
		int index = 0;
		String CHGTICKETPR_BANNER = "Change ticket priority";
		banner(CHGTICKETPR_BANNER);
		String confirmation = "";
		String new_severity = "";
		String service_desk[];
		final int NEW_SERVICE_DESK = 1;
		//Retrieve ticket from user
		System.out.println("Enter the ticket ID you would like to change priority for: ");
		String ticket_id = get_user_input();
		//Search for ticket and return
		index = database_search(ticketDatabase, num_tickets, ticket_id, NUM_TICKET_FIELDS);
		if(index == -1) {
			System.out.println("Sorry, ticket not found!");
		}
		display_entry(index, ticketDatabase, "Is the below ticket the correct ticket? (Y/N)", true);
		confirmation = get_user_input();
		if(!confirmation.toUpperCase().contentEquals("Y")) {
			changeTicketPriority();
		} else {
			System.out.print("\nPlease set the new severity status (1 = Low | 2 = Medium | 3 = High): ");
			do {
				new_severity = get_user_input();
			} while (!check_severity(new_severity)); 
			service_desk = set_ticket_severity(new_severity);
			ticketDatabase[index][TICKET_SEVERITY] = service_desk[NEW_SERVICE_DESK];
			System.out.println("\nTicket severity updated successfully!");
		}
	}


	// Function to close ticket
	private static void closeTicket()
	{
		String CLOSETICKET_BANNER = "Ticket closure";
		banner(CLOSETICKET_BANNER);
		System.out.println("Which ticket would you like to close: ");
		System.out.println("!!!FUNCTIONALITY NOT YET COMPLETED!!!");
		// code to close ticket
	}

	// Function to add users that are not ones manually created by each user
	private static void insertDefaultUsers()
	{
		userDatabase[num_users][USERNAME_INDEX] = "hstyles";
		userDatabase[num_users][NAME_INDEX] = "Harry Styles";
		userDatabase[num_users][EMAIL_INDEX] = "hstyles@cinco.com.au";
		userDatabase[num_users][PASSWD_INDEX] = "12345";
		userDatabase[num_users][ROLE_INDEX] = "1";
		++num_users;

		userDatabase[num_users][USERNAME_INDEX] = "nhoran";
		userDatabase[num_users][NAME_INDEX] = "Niall Horan";
		userDatabase[num_users][EMAIL_INDEX] = "nhoran@cinco.com.au";
		userDatabase[num_users][PASSWD_INDEX] = "12345";
		userDatabase[num_users][ROLE_INDEX] = "1";
		++num_users;

		userDatabase[num_users][USERNAME_INDEX] = "ltomlinson";
		userDatabase[num_users][NAME_INDEX] = "Louis Tomlinson";
		userDatabase[num_users][EMAIL_INDEX] = "ltomlinson@cinco.com.au";
		userDatabase[num_users][PASSWD_INDEX] = "12345";
		userDatabase[num_users][ROLE_INDEX] = "2";
		++num_users;

		userDatabase[num_users][USERNAME_INDEX] = "zmalik";
		userDatabase[num_users][NAME_INDEX] = "Zayn Malik";
		userDatabase[num_users][EMAIL_INDEX] = "zmalik@cinco.com.au";
		userDatabase[num_users][PASSWD_INDEX] = "12345";
		userDatabase[num_users][ROLE_INDEX] = "2";
		++num_users;

		userDatabase[num_users][USERNAME_INDEX] = "demouser";
		userDatabase[num_users][NAME_INDEX] = "Demo User";
		userDatabase[num_users][EMAIL_INDEX] = "demouser@cinco.com.au";
		userDatabase[num_users][PASSWD_INDEX] = "12345";
		userDatabase[num_users][ROLE_INDEX] = "0";
		++num_users;
	}

}