package com.app.addressbook;

import java.util.Scanner;

public class AddressBookMain {

	public static void main(String[] args) {

		// UC1: Welcome Message
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);

		// UC6: System managing multiple address books
		AddressBookSystem system = new AddressBookSystem();

		while (true) {

			System.out.println("\n===== Address Book System =====");
			System.out.println("1. Create Address Book");
			System.out.println("2. Open Address Book");
			System.out.println("3. Search Person by City (UC8)");
			System.out.println("4. Search Person by State (UC8)");
			System.out.println("5. View Persons by City (UC9)");
			System.out.println("6. View Persons by State (UC9)");
			System.out.println("7. Count Persons by City");
			System.out.println("8. Count Persons by State");
			System.out.println("9. Exit");

			System.out.print("Enter Choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1:
				System.out.print("Enter Address Book Name: ");
				String bookName = scanner.nextLine();
				system.createAddressBook(bookName);
				break;

			case 2:

				System.out.print("Enter Address Book Name: ");
				String name = scanner.nextLine();

				AddressBook book = system.getAddressBook(name);

				if (book == null) {
					System.out.println("Address Book not found!");
					break;
				}

				manageContacts(book, scanner, system);
				break;

			case 3:

				System.out.print("Enter City: ");
				String city = scanner.nextLine();

				system.viewPersonsByCity(city);
				break;

			case 4:

				System.out.print("Enter State: ");
				String state = scanner.nextLine();

				system.viewPersonsByState(state);
				break;

			case 5:

				System.out.print("Enter City: ");
				String cityView = scanner.nextLine();

				system.viewPersonsByCity(cityView);
				break;

			case 6:

				System.out.print("Enter State: ");
				String stateView = scanner.nextLine();

				system.viewPersonsByState(stateView);
				break;

			case 7:

				system.countPersonsByCity();
				break;

			case 8:

				system.countPersonsByState();
				break;

			case 9:

				System.out.println("Exiting program...");
				scanner.close();
				return;

			default:

				System.out.println("Invalid choice!");
			}
		}
	}

	/*
	 * Manage contacts inside selected AddressBook UC2 → Add Contact UC3 → Edit
	 * Contact UC4 → Delete Contact UC5 → Multiple Contacts
	 */
	private static void manageContacts(AddressBook book, Scanner scanner, AddressBookSystem system) {

		while (true) {

			System.out.println("\n---- Manage Contacts ----");
			System.out.println("1. Add Contact");
			System.out.println("2. Edit Contact");
			System.out.println("3. Delete Contact");
			System.out.println("4. Display Contacts");
			System.out.println("5. Back");

			System.out.print("Enter Choice: ");
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

				// UC7: Duplicate check happens inside addContact
				book.addContact(person);

				// UC9: Add person to city/state dictionary
				system.addPersonToCityState(person);

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

				System.out.println("Invalid choice!");
			}
		}
	}
}