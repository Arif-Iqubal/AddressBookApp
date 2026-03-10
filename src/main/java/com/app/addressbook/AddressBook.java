package com.app.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

	private List<ContactPerson> contactList = new ArrayList<>();

	public void addContact(ContactPerson person) {

		if (contactList.contains(person)) {
			System.out.println("Duplicate entry! Person already exists.");
			return;
		}

		contactList.add(person);
		System.out.println("Contact added successfully.");
	}

	public List<ContactPerson> getContacts() {
		return contactList;
	}

	public void displayContacts() {

		if (contactList.isEmpty()) {
			System.out.println("No contacts available.");
			return;
		}

		contactList.forEach(ContactPerson::displayContact);
	}

	public void editContact(String firstName, Scanner scanner) {

		for (ContactPerson person : contactList) {

			if (person.getFirstName().equalsIgnoreCase(firstName)) {

				System.out.print("Enter New City: ");
				person.setCity(scanner.nextLine());

				System.out.print("Enter New State: ");
				person.setState(scanner.nextLine());

				System.out.println("Contact updated successfully.");
				return;
			}
		}

		System.out.println("Contact not found.");
	}

	public void deleteContact(String firstName) {

		boolean removed = contactList.removeIf(person -> person.getFirstName().equalsIgnoreCase(firstName));

		if (removed)
			System.out.println("Contact deleted successfully.");
		else
			System.out.println("Contact not found.");
	}
}