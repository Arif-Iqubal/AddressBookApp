package com.app.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

	// UC5: Collection storing multiple contacts
	private List<ContactPerson> contactList = new ArrayList<>();

	/*
	 * UC7: Check duplicate before adding contact
	 */
	public void addContact(ContactPerson person) {

		if (contactList.contains(person)) {
			System.out.println("Duplicate entry! Person already exists.");
			return;
		}

		contactList.add(person);
		System.out.println("Contact added successfully.");
	}

	public void displayContacts() {

		if (contactList.isEmpty()) {
			System.out.println("No contacts available.");
			return;
		}

		for (ContactPerson person : contactList) {
			person.displayContact();
		}
	}

	// UC3: Edit contact
	public void editContact(String firstName, Scanner scanner) {

		for (ContactPerson person : contactList) {

			if (person.getFirstName().equalsIgnoreCase(firstName)) {

				System.out.print("Enter New Address: ");
				person.setAddress(scanner.nextLine());

				System.out.print("Enter New City: ");
				person.setCity(scanner.nextLine());

				System.out.print("Enter New State: ");
				person.setState(scanner.nextLine());

				System.out.print("Enter New Zip: ");
				person.setZip(scanner.nextLine());

				System.out.print("Enter New Phone: ");
				person.setPhoneNumber(scanner.nextLine());

				System.out.print("Enter New Email: ");
				person.setEmail(scanner.nextLine());

				System.out.println("Contact updated successfully.");
				return;
			}
		}

		System.out.println("Contact not found.");
	}

	// UC4: Delete contact
	public void deleteContact(String firstName) {

		boolean removed = contactList.removeIf(person -> person.getFirstName().equalsIgnoreCase(firstName));

		if (removed)
			System.out.println("Contact deleted successfully.");
		else
			System.out.println("Contact not found.");
	}
}