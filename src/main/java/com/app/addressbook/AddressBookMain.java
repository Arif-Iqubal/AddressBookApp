package com.app.addressbook;

import java.util.Scanner;

public class AddressBookMain {

	public static void main(String[] args) {

		// UC1: Welcome message
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);

		// UC6: System managing multiple AddressBooks
		AddressBookSystem system = new AddressBookSystem();

		while (true) {

			System.out.println("\n----- Address Book System -----");
			System.out.println("1. Create New Address Book");
			System.out.println("2. Open Address Book");
			System.out.println("3. Search Person by City");
			System.out.println("4. Search Person by State");
			System.out.println("5. Exit");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1:
				// UC6: Create AddressBook
				System.out.print("Enter Address Book Name: ");
				String bookName = scanner.nextLine();

				system.createAddressBook(bookName);
				break;

			case 2:
				// Select AddressBook
				System.out.print("Enter Address Book Name: ");
				String name = scanner.nextLine();

				AddressBook book = system.getAddressBook(name);

				if (book == null) {
					System.out.println("Address Book not found!");
					break;
				}

				manageContacts(book, scanner);
				break;

			case 3:
				// UC8: Search by City
				System.out.print("Enter City Name: ");
				String city = scanner.nextLine();

				system.searchPersonByCity(city);
				break;

			case 4:
				// UC8: Search by State
				System.out.print("Enter State Name: ");
				String state = scanner.nextLine();

				system.searchPersonByState(state);
				break;

			case 5:
				System.out.println("Exiting program...");
				scanner.close();
				return;

			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	/*
	 * Manage contacts inside selected AddressBook (UC2, UC3, UC4, UC5)
	 */
	private static void manageContacts(AddressBook book, Scanner scanner) {

		while (true) {

			System.out.println("\n---- Manage Contacts ----");
			System.out.println("1. Add Contact");
			System.out.println("2. Edit Contact");
			System.out.println("3. Delete Contact");
			System.out.println("4. Display Contacts");
			System.out.println("5. Back");

			System.out.print("Enter choice: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {

			case 1:

				System.out.print("First Name: ");
				String firstName = scanner.nextLine();

				System.out.print("Last Name: ");
				String lastName = scanner.nextLine();

				System.out.print("Address: ");
				String address = scanner.nextLine();

				System.out.print("City: ");
				String city = scanner.nextLine();

				System.out.print("State: ");
				String state = scanner.nextLine();

				System.out.print("Zip: ");
				String zip = scanner.nextLine();

				System.out.print("Phone: ");
				String phone = scanner.nextLine();

				System.out.print("Email: ");
				String email = scanner.nextLine();

				ContactPerson person = new ContactPerson(firstName, lastName, address, city, state, zip, phone, email);

				book.addContact(person);
				break;

			case 2:

				System.out.print("Enter First Name to Edit: ");
				String editName = scanner.nextLine();

				book.editContact(editName, scanner);
				break;

			case 3:

				System.out.print("Enter First Name to Delete: ");
				String deleteName = scanner.nextLine();

				book.deleteContact(deleteName);
				break;

			case 4:

				book.displayContacts();
				break;

			case 5:

				return;

			default:
				System.out.println("Invalid choice.");
			}
		}
	}
}