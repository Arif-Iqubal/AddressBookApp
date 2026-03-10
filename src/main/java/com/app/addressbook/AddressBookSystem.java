package com.app.addressbook;

import java.util.*;

public class AddressBookSystem {

	// UC6: Store multiple AddressBooks
	private Map<String, AddressBook> addressBookMap = new HashMap<>();

	/*
	 * UC9: Dictionary for City → Persons
	 */
	private Map<String, List<ContactPerson>> cityPersonMap = new HashMap<>();

	/*
	 * UC9: Dictionary for State → Persons
	 */
	private Map<String, List<ContactPerson>> statePersonMap = new HashMap<>();

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
	 * UC9: Add person into city and state dictionary
	 */
	public void addPersonToCityState(ContactPerson person) {

		cityPersonMap.computeIfAbsent(person.getCity(), k -> new ArrayList<>()).add(person);

		statePersonMap.computeIfAbsent(person.getState(), k -> new ArrayList<>()).add(person);
	}

	/*
	 * UC9: View persons by City
	 */
	public void viewPersonsByCity(String city) {

		List<ContactPerson> persons = cityPersonMap.get(city);

		if (persons == null || persons.isEmpty()) {
			System.out.println("No persons found in this city.");
			return;
		}

		persons.forEach(ContactPerson::displayContact);
	}

	/*
	 * UC9: View persons by State
	 */
	public void viewPersonsByState(String state) {

		List<ContactPerson> persons = statePersonMap.get(state);

		if (persons == null || persons.isEmpty()) {
			System.out.println("No persons found in this state.");
			return;
		}

		persons.forEach(ContactPerson::displayContact);
	}
}