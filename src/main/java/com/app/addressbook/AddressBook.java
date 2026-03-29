package com.app.addressbook;

import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class AddressBook {

	// UC5: Collection to store multiple contacts
	private List<ContactPerson> contactList = new ArrayList<>();

	/*
	 * UC7: Add contact with duplicate check
	 */
	public void addContact(ContactPerson person) {

		if (contactList.contains(person)) {
			System.out.println("Duplicate entry! Person already exists.");
			return;
		}

		contactList.add(person);
		System.out.println("Contact added successfully.");
	}

	/*
	 * UC5: Display all contacts
	 */
	public void displayContacts() {

		if (contactList.isEmpty()) {
			System.out.println("No contacts found.");
			return;
		}

		contactList.forEach(System.out::println);
	}

	/*
	 * UC3: Edit contact using person's first name
	 */
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

	/*
	 * UC4: Delete contact using person's name
	 */
	public void deleteContact(String firstName) {

		boolean removed = contactList.removeIf(person -> person.getFirstName().equalsIgnoreCase(firstName));

		if (removed)
			System.out.println("Contact deleted successfully.");
		else
			System.out.println("Contact not found.");
	}

	/*
	 * Used in Streams operations (UC8 & UC10)
	 */
	public List<ContactPerson> getContacts() {
		return contactList;
	}

	/*
	 * UC11: Sort contacts alphabetically by person's name Uses Java Streams
	 */
	public void sortContactsByName() {

		List<ContactPerson> sortedList = contactList.stream()
				.sorted(Comparator.comparing(ContactPerson::getFirstName).thenComparing(ContactPerson::getLastName))
				.collect(Collectors.toList());

		sortedList.forEach(System.out::println);
	}

	// UC12: Sort contacts by City
	public void sortContactsByCity() {

		if (contactList.isEmpty()) {
			System.out.println("No contacts available.");
			return;
		}

		contactList.stream().sorted(Comparator.comparing(ContactPerson::getCity)).forEach(System.out::println);
	}

	// UC12: Sort contacts by State
	public void sortContactsByState() {

		if (contactList.isEmpty()) {
			System.out.println("No contacts available.");
			return;
		}

		contactList.stream().sorted(Comparator.comparing(ContactPerson::getState)).forEach(System.out::println);
	}

	// UC12: Sort contacts by Zip
	public void sortContactsByZip() {

		if (contactList.isEmpty()) {
			System.out.println("No contacts available.");
			return;
		}

		contactList.stream().sorted(Comparator.comparing(ContactPerson::getZip)).forEach(System.out::println);
	}
	
	//UC13: Read Write from file
	
	

	public void writeToFile(String fileName) {

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

	        for (ContactPerson person : contactList) {

	            writer.write(
	                    person.getFirstName() + "," +
	                    person.getLastName() + "," +
	                    person.getAddress() + "," +
	                    person.getCity() + "," +
	                    person.getState() + "," +
	                    person.getZip() + "," +
	                    person.getPhoneNumber() + "," +
	                    person.getEmail()
	            );

	            writer.newLine();
	        }

	        System.out.println("Contacts saved to file successfully.");

	    } catch (IOException e) {
	        System.out.println("Error writing to file: " + e.getMessage());
	    }
	}
	
	


	public void readFromFile(String fileName) {

	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

	        String line;

	        while ((line = reader.readLine()) != null) {

	            String[] data = line.split(",");

	            ContactPerson person = new ContactPerson(
	                    data[0], data[1], data[2],
	                    data[3], data[4], data[5],
	                    data[6], data[7]
	            );

	            contactList.add(person);
	        }

	        System.out.println("Contacts loaded from file successfully.");

	    } catch (IOException e) {
	        System.out.println("Error reading file: " + e.getMessage());
	    }
	}
}