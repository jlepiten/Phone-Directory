/*
 * PURPOSE: 
 * 			A user interactive program that maintains a list of records containing names and phone numbers. 
 * 			The user can enter commands that will be executed, e.g. entering the character 'a' to show all
 * 			records. 
 * SOLUTION:
 * 			Write methods for each user command. Use Scanner to take user input and call the corresponding 
 * 			method. The user will be continuously prompted for a command until they enter the 'q' command
 * 			to quit. A separate Customer class is defined to create Customer objects that contain 
 * 			data such as name and phone number. 
 * DATA STRUCTURES:
 * 			LinkedList
 * HOW TO USE:
 * 			Enter the desired letter command (lower-case) and press the enter key to execute.			
 */

package phoneDirectory;

import java.util.*; 

public class phonedir { 
	
	static LinkedList<Customer> Directory = new LinkedList<Customer>(); // phone directory
	static Customer curr; // current record
	static Scanner user = new Scanner(System.in); // Scanner for user input
	static boolean loop = true; 
	
	public static void main(String[] args) {
		
		System.out.println("A program to keep a phone directory:\n");
		
		/*
		 * Infinite while loop until user enters q command to quit.
		 * Continuously displays menu after each user command.
		 */
		while (loop) {
			menu();
			System.out.println("Enter a command from the list above (q to quit):  ");
			String command = user.next();
			
			switch (command) {
			case "a": case "A":
				showAll(); break;
			case "d": case "D":
				delete(); break;
			case "f": case "F":
				first(); break;
			case "l": case "L":
				last(); break;
			case "n": case "N":
				add(); break;
			case "p": case "P":
				phone(); break;
			case "q": case "Q":
				quit(); break;
			case "s": case "S":
				selectCurrent(); break;
			default:
				System.out.println("Illegal command.\n");
			}
			
		}
	}
	
	/*
	 * Shows all records in the given phone directory.
	 * (Prints all elements in the specified LinkedList.)
	 * ==========
	 * User command a.
	 */
	public static void showAll() {
		System.out.printf("%-20s %-20s %-20s\n", "FIRST NAME", " LAST NAME", "PHONE NUMBER");
		System.out.printf("%-20s %-20s %-20s\n", "==========", "===========", "============");
		for (Customer cust : Directory)
			System.out.printf("%-20s %-20s %-20s\n", cust.getFirst(), cust.getLast(), cust.getPhone());
		System.out.println("\n\n\n");
	}
	
	/*
	 * Adds a new record. 
	 * Prompts user for the first name, last name, and phone number of the new record.
	 * ==========
	 * User command n.
	 */
	public static void add() {
		System.out.println("Enter first name: ");
		String firstName = user.next();
		System.out.println("Enter last name: ");
		String lastName = user.next();
		System.out.println("Enter phone number (###-###-####): ");
		String phoneNum = user.next();
		
		// User must type phone number in correct format.
		while (!(phoneNum.matches("\\d{3}-\\d{3}-\\d{4}"))) {
			System.out.println("Please enter phone number in the correct format (###-###-####): ");
			phoneNum = user.next();
		}
		
		Customer cust = new Customer(firstName, lastName, phoneNum); // Create new Customer.
		
		// Insert record into the directory such that the list is sorted according to last name.
		if (Directory.isEmpty()) {
			Directory.add(cust);
		} else {
			// Insert record in correct alphabetical position by last name.
			boolean added = false;
			int size = Directory.size();
			for (int i = 0; i < size; i++) {
				if (lastName.compareToIgnoreCase(Directory.get(i).getLast()) < 0) {
					Directory.add(i, cust);
					added = true;
					i++;
				}
			}
			if (!added) {
				Directory.add(cust);
			}
		}
		
		curr = cust; // Set new record as the current record.
		showCurrent();
	}
	
	/*
	 * Delete the current record.
	 * ==========
	 * User command d.
	 */
	public static void delete() {
		// Checks first if directory is empty.
		if (Directory.isEmpty()) {
			System.out.println("The directory is empty.");
		} else {
			Directory.remove(curr);
			if (curr == null) {
				System.out.println("No current record selected.");
			} else {
				System.out.println("Deleted: " + curr);
			}
			
			// After deletion, there is no record currently selected.
			curr = null;
		}
		System.out.println();
	}
	
	/*
	 * Select a record from the list to become the current record.
	 * Prompts user for the name.
	 * ==========
	 * User command s.
	 */
	public static void selectCurrent() {
		System.out.println("Enter first name: ");
		String firstName = user.next();
		System.out.println("Enter last name: ");
		String lastName = user.next();
		
		// Iterates through the directory to see if any names match.
		boolean match = false;
		compare:
		for (Customer cust : Directory) {
			if (cust.getFirst().equalsIgnoreCase(firstName) && cust.getLast().equalsIgnoreCase(lastName)) {
				match = true;
				curr = cust;
				showCurrent();
				break compare;
			}
		}
		
		if (!match)
			System.out.println("No matching record found.\n");
	}
	
	/*
	 * Change the first name in the current record.
	 * Prompts user for first name.
	 * ==========
	 * User command f.
	 */
	public static void first() {
		// First check if there is a current record.
		if (curr == null) {
			System.out.println("No current record.\n");
		} else {
			System.out.println("Enter new first name: ");
			String firstName = user.next();
			curr.setFirst(firstName);
			showCurrent();
		}
	}
	
	/*
	 * Change the last name in the current record.
	 * Prompts user for last name.
	 * ==========
	 * User command l.
	 */
	public static void last() {
		// First check if there is a current record.
		if (curr == null) {
			System.out.println("No current record.\n");
		} else {
			System.out.println("Enter new last name: ");
			String lastName = user.next();
			curr.setLast(lastName);
			showCurrent();
		}
	}
	
	/*
	 * Change the phone number in the current record.
	 * Prompts user for phone number.
	 * ==========
	 * User command p.
	 */
	public static void phone() {
		// First check if there is a current record.
		if (curr == null) {
			System.out.println("No current record.\n");
		} else {
			System.out.println("Enter new phone number: ");
			String phoneNum = user.next();
			curr.setPhone(phoneNum);
			showCurrent();
		}
	}
	
	/*
	 * Quits the program by exiting out of the while loop in main method.
	 * ==========
	 * User command q.
	 */
	public static void quit() {
		loop = false;
	}
	
	// Displays current Customer.
	private static void showCurrent() {
		System.out.println(">>> Current record is: " + curr);
		System.out.println();
	}
	
	// Displays menu.
	private static void menu() {
		System.out.println("	a	Show all records.");
		System.out.println("	d	Delete the current record.");
		System.out.println("	f	Change the first name in the current record.");
		System.out.println("	l	Change the last name in the current record.");
		System.out.println("	n	Add a new record.");
		System.out.println("	p	Change the phone number in the current record.");
		System.out.println("	q	Quit.");
		System.out.println("	s	Select a record from the record list to become the current record.\n");
	}
	
}
