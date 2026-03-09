package com.app.addressbook;

import java.util.Scanner;

public class AddressBookMain {

	public static void main(String[] args) {

		// UC1: Display welcome message when program starts
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);

		// Creating AddressBook object (OOP relationship)
		AddressBook addressBook = new AddressBook();

		/*
		 * UC2: Ability to add a new Contact to Address Book - Use console to add person
		 * details - Create ContactPerson object - Pass object to AddressBook class
		 */

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

		// Creating ContactPerson object
		ContactPerson person = new ContactPerson(firstName, lastName, address, city, state, zip, phoneNumber, email);

		// Adding contact to AddressBook
		addressBook.addContact(person);

		System.out.println("\nCurrent Contact List:");
		addressBook.displayContacts();

		/*
		 * UC3: Ability to edit existing contact person using their name - Search
		 * contact using first name - Update details using console input
		 */

		System.out.print("\nEnter First Name of Contact to Edit: ");
		String nameToEdit = scanner.nextLine();

		addressBook.editContact(nameToEdit, scanner);

		System.out.println("\nUpdated Contact List:");
		addressBook.displayContacts();

		scanner.close();
	}
}