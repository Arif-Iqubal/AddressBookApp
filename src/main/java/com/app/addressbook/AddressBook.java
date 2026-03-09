package com.app.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

	// UC2: Use Collection class to store multiple contacts
	private List<ContactPerson> contactList = new ArrayList<>();

	// UC2: Method to add new contact
	public void addContact(ContactPerson person) {
		contactList.add(person);
		System.out.println("Contact added successfully.");
	}

	// Method to display all contacts
	public void displayContacts() {

		if (contactList.isEmpty()) {
			System.out.println("No contacts available.");
			return;
		}

		for (ContactPerson person : contactList) {
			person.displayContact();
		}
	}

	// UC3: Edit contact using first name
	public void editContact(String firstName, Scanner scanner) {

		for (ContactPerson person : contactList) {

			if (person.getFirstName().equalsIgnoreCase(firstName)) {

				System.out.println("Editing contact for: " + firstName);

				System.out.print("Enter New Address: ");
				person.setAddress(scanner.nextLine());

				System.out.print("Enter New City: ");
				person.setCity(scanner.nextLine());

				System.out.print("Enter New State: ");
				person.setState(scanner.nextLine());

				System.out.print("Enter New Zip: ");
				person.setZip(scanner.nextLine());

				System.out.print("Enter New Phone Number: ");
				person.setPhoneNumber(scanner.nextLine());

				System.out.print("Enter New Email: ");
				person.setEmail(scanner.nextLine());

				System.out.println("Contact updated successfully.");
				return;
			}
		}

		System.out.println("Contact not found.");
	}
}