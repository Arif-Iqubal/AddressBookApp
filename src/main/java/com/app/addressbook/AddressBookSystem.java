package com.app.addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookSystem {

	/*
	 * UC6: Dictionary storing multiple AddressBooks Key = AddressBook Name Value =
	 * AddressBook Object
	 */
	private Map<String, AddressBook> addressBookMap = new HashMap<>();

	/*
	 * UC9: Dictionary for City → Persons
	 */
	private Map<String, List<ContactPerson>> cityPersonMap = new HashMap<>();

	/*
	 * UC9: Dictionary for State → Persons
	 */
	private Map<String, List<ContactPerson>> statePersonMap = new HashMap<>();

	/*
	 * UC6: Create new AddressBook
	 */
	public void createAddressBook(String name) {

		if (addressBookMap.containsKey(name)) {
			System.out.println("AddressBook already exists.");
			return;
		}

		addressBookMap.put(name, new AddressBook());

		System.out.println("AddressBook '" + name + "' created successfully.");
	}

	/*
	 * UC6: Get AddressBook by name
	 */
	public AddressBook getAddressBook(String name) {
		return addressBookMap.get(name);
	}

	/*
	 * UC9: Add person to city and state dictionary
	 */
	public void addPersonToCityState(ContactPerson person) {

		cityPersonMap.computeIfAbsent(person.getCity(), k -> new ArrayList<>()).add(person);

		statePersonMap.computeIfAbsent(person.getState(), k -> new ArrayList<>()).add(person);
	}

	/*
	 * UC8: Search person by City across multiple AddressBooks Uses Java Streams
	 */
	public void searchPersonByCity(String city) {

		addressBookMap.values().stream().flatMap(book -> book.getContacts().stream())
				.filter(person -> person.getCity().equalsIgnoreCase(city)).forEach(System.out::println);
	}

	/*
	 * UC8: Search person by State across multiple AddressBooks Uses Java Streams
	 */
	public void searchPersonByState(String state) {

		addressBookMap.values().stream().flatMap(book -> book.getContacts().stream())
				.filter(person -> person.getState().equalsIgnoreCase(state)).forEach(System.out::println);
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

		persons.forEach(System.out::println);
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

		persons.forEach(System.out::println);
	}

	/*
	 * UC10: Count persons by City using Streams
	 */
	public void countPersonsByCity() {

		addressBookMap.values().stream().flatMap(book -> book.getContacts().stream())
				.collect(Collectors.groupingBy(ContactPerson::getCity, Collectors.counting()))
				.forEach((city, count) -> System.out.println(city + " : " + count));
	}

	/*
	 * UC10: Count persons by State using Streams
	 */
	public void countPersonsByState() {

		addressBookMap.values().stream().flatMap(book -> book.getContacts().stream())
				.collect(Collectors.groupingBy(ContactPerson::getState, Collectors.counting()))
				.forEach((state, count) -> System.out.println(state + " : " + count));
	}
}