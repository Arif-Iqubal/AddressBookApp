package com.app.addressbook;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookSystem {

	/*
	 * UC6: Dictionary to maintain multiple AddressBooks Key = AddressBook Name
	 * Value = AddressBook Object
	 */
	private Map<String, AddressBook> addressBookMap = new HashMap<>();

	// Create new AddressBook
	public void createAddressBook(String name) {

		if (addressBookMap.containsKey(name)) {
			System.out.println("AddressBook already exists.");
			return;
		}

		addressBookMap.put(name, new AddressBook());
		System.out.println("AddressBook '" + name + "' created successfully.");
	}

	// Get AddressBook by name
	public AddressBook getAddressBook(String name) {
		return addressBookMap.get(name);
	}

	// Manage contacts inside selected AddressBook
	public void manageAddressBook(AddressBook addressBook, Scanner scanner) {

		while (true) {

			System.out.println("\n1. Add Contact");
			System.out.println("2. Edit Contact");
			System.out.println("3. Delete Contact");
			System.out.println("4. Display Contacts");
			System.out.println("5. Back");

			System.out.print("Enter Choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

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

				addressBook.addContact(person);
				break;

			case 2:

				System.out.print("Enter First Name to Edit: ");
				String editName = scanner.nextLine();

				addressBook.editContact(editName, scanner);
				break;

			case 3:

				System.out.print("Enter First Name to Delete: ");
				String deleteName = scanner.nextLine();

				addressBook.deleteContact(deleteName);
				break;

			case 4:
				addressBook.displayContacts();
				break;

			case 5:
				return;

			default:
				System.out.println("Invalid Choice");
			}
		}
	}
}