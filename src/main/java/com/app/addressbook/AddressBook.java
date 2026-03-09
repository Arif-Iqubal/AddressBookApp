package com.app.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

	private List<ContactPerson> contactList = new ArrayList<>();

	public void addContact(ContactPerson person) {
		contactList.add(person);
		System.out.println("Contact added successfully.");
	}

	public void displayContacts() {
		for (ContactPerson person : contactList) {
			person.displayContact();
		}
	}
}