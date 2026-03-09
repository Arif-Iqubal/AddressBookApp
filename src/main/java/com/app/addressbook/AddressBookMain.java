package com.app.addressbook;

import java.util.Scanner;

public class AddressBookMain {

	public static void main(String[] args) {

		// UC1: Display welcome message
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);
		AddressBook addressBook = new AddressBook();

		/*
		 * UC5: Ability to add multiple persons - Use console input - Add contacts one
		 * by one
		 */

		while (true) {

			System.out.println("\nEnter Contact Details");

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

			System.out.print("Phone Number: ");
			String phoneNumber = scanner.nextLine();

			System.out.print("Email: ");
			String email = scanner.nextLine();

			ContactPerson person = new ContactPerson(firstName, lastName, address, city, state, zip, phoneNumber,
					email);

			// UC2: Add contact to AddressBook
			addressBook.addContact(person);

			System.out.print("\nDo you want to add another contact? (yes/no): ");
			String choice = scanner.nextLine();

			if (!choice.equalsIgnoreCase("yes")) {
				break;
			}
		}

		System.out.println("\nAll Contacts:");
		addressBook.displayContacts();

		/*
		 * UC3: Edit contact using name
		 */

		System.out.print("\nEnter First Name to Edit Contact: ");
		String editName = scanner.nextLine();

		addressBook.editContact(editName, scanner);

		/*
		 * UC4: Delete contact using name
		 */

		System.out.print("\nEnter First Name to Delete Contact: ");
		String deleteName = scanner.nextLine();

		addressBook.deleteContact(deleteName);

		System.out.println("\nFinal Contact List:");
		addressBook.displayContacts();

		scanner.close();
	}
}