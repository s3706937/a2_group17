package application;

import java.util.Scanner;
import java.util.StringTokenizer;

/* Appointment Management System class */
public class application {

	private final static String [][] userDatabase = 
		{
			{"hstyles","Harry Styles","hstyles@cinco.com.au","12345","1"},
			{"nhoran","Niall Horan","nhoran@cinco.com.au","12345","1"},
			{"ltomlinson","Louis Tomlinson","ltomlinson@cinco.com.au","12345","2"},
			{"zmalik","Zayn Malik","zmalik@cinco.com.au","12345","2"},
			{"demouser","Demo User","demouser@cinco.com.au","12345","0"}
		};	
	
	public String ticketDatabase[][];
	
	
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
			banner(WELCOME_BANNER);
			
			
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


	private static void banner(String bannerName) {
		for (int i = 0; i < bannerName.length(); ++i) {
			System.out.print("=");
		}
		System.out.println();
		System.out.println(bannerName);
		for (int i = 0; i < bannerName.length(); ++i) {
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
	
    private static boolean check_login(final String[][] userDatabase, final String USERNAME, final String PASSWORD) {
        
        final int username = 0;
        final int passwd = 3;
        boolean valid = false;
       
        for(int i = 0; i < userDatabase.length; ++i){
           
                if(USERNAME.contentEquals(userDatabase[i][username])) {
                    if(PASSWORD.contentEquals(userDatabase[i][passwd])) {
                        valid = true;
                        break;
                    }
                }
        }           
        return valid;
    }
	
	private static void attemptLogin() {
		String ATTEMPTLOGIN_BANNER = "Login to Ticketing System";
		banner(ATTEMPTLOGIN_BANNER);
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
		
		if (check_login(userDatabase, username, passwd))
		{
			System.out.println("Winner winner chicken dinner");
			
			// Menu system for the program
			String selection;
			// Check user input until the exit function is met, i.e. X or x
			
			do {
				// Display Menu Options
				
				String MENU_BANNER = "IT ticketing system - Main Menu";
				banner(MENU_BANNER);
								
				System.out.println("A. Create an new ticket");
				System.out.println("B. View all open tickets");
				System.out.println("C. Change ticket priority (Technicians only)");
				System.out.println("D. Close ticket (Technicians only)");
				System.out.println("X. Logout");
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
						createTicket();
						break;

					case "B":
						viewTickets();
						break;

					case "C":
						// check for being an Technician
						changeTicketPriority();
						break;
						
					case "D":
						// check for being an Technician
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
			
		}
		else
		{
			System.out.println("Incorrect Login");
		}
		
	
		
		System.out.println();
	}

	private static void createAccount() {
		String CREATEACC_BANNER = "Create account";
		banner(CREATEACC_BANNER);
		System.out.println("Prompts to create an account");
		
		// Add more code here
		System.out.print("Please enter a username: ");
		String username = get_user_input();
		
		// Check for uniqueness
		
		System.out.print("Please enter your name : ");
		String name = get_user_input();
		
		System.out.print("Please enter your email address: ");
		String email = get_user_input();
		
		// check validity
		
		// check for uniqueness
		
		System.out.print("Please enter your password: ");
		String passwd = get_user_input();
		
		// are you staff or technician
		
		// create account in array with username, email, password and privilege set to 0 as Staff and 1 as Technician 

		
		System.out.println();
	}

	private static void resetPassword() {
		String RESETPWD_BANNER = "Reset Password";
		banner(RESETPWD_BANNER);
		System.out.println("Prompts to reset password");
		
		// Testing printing out all users
		
	    for (int i = 0; i < userDatabase.length; i++) {
	        System.out.print(userDatabase[i][0] + ": ");
	        for (int j = 1; j < userDatabase[i].length; j++) {
	          System.out.print(userDatabase[i][j] + " ");
	        }
	        System.out.println();
	      }
		
		// Add more code here
			
		System.out.println();
	}
	
	private static void createTicket()
	{
		String CREATETICKET_BANNER = "Create a ticket";
		banner(CREATETICKET_BANNER);
		System.out.println("Enter details for ticket below");
		// code to create ticket
	}

	private static void viewTickets()
	{
		String VIEWTICKET_BANNER = "View your tickets";
		banner(VIEWTICKET_BANNER);
		System.out.println("Show me all your tickets");
		// show tickets
		// if tech show closed ones too
	}
	
	private static void changeTicketPriority()
	{
		String CHGTICKETPR_BANNER = "Change ticket priority";
		banner(CHGTICKETPR_BANNER);
		System.out.println("Which ticket would you like to change priority for: ");
		// code to change ticket priority
	}
	
	private static void closeTicket()
	{
		String CLOSETICKET_BANNER = "Ticket closure";
		banner(CLOSETICKET_BANNER);
		System.out.println("Which ticket would you like to close: ");
		// code to close ticket
	}
		
}
