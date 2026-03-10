package com.app.addressbook;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookSystem {

	// UC6: Dictionary storing multiple AddressBooks
	private Map<String, AddressBook> addressBookMap = new HashMap<>();

	public void createAddressBook(String name) {

		if (addressBookMap.containsKey(name)) {
			System.out.println("AddressBook already exists.");
			return;
		}

		addressBookMap.put(name, new AddressBook());
		System.out.println("AddressBook '" + name + "' created successfully.");
	}

	public AddressBook getAddressBook(String name) {
		return addressBookMap.get(name);
	}

	/*
	 * UC8: Search person by City across multiple AddressBooks using Streams
	 */
	public void searchPersonByCity(String city) {

		addressBookMap.values().stream().flatMap(book -> book.getContacts().stream())
				.filter(person -> person.getCity().equalsIgnoreCase(city)).forEach(ContactPerson::displayContact);
	}

	/*
	 * UC8: Search person by State across multiple AddressBooks using Streams
	 */
	public void searchPersonByState(String state) {

		addressBookMap.values().stream().flatMap(book -> book.getContacts().stream())
				.filter(person -> person.getState().equalsIgnoreCase(state)).forEach(ContactPerson::displayContact);
	}
}