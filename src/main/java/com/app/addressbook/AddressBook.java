package com.app.addressbook;

import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class AddressBook {

	private AddressBookDAO dao = new AddressBookDAO();
	// UC5: Collection to store multiple contacts
	private List<ContactPerson> contactList = new ArrayList<>();

	/*
	 * UC7: Add contact with duplicate check
	 */
	public void addContact(ContactPerson person) {

		// UC7: Duplicate check in memory
		if (contactList.contains(person)) {
			System.out.println("Duplicate entry! Person already exists.");
			return;
		}

		// Add to DB first (UC16)
		boolean isAddedToDB = dao.insertContact(person);

		if (!isAddedToDB) {
			System.out.println("Failed to add contact to database.");
			return;
		}

		// Add to memory only if DB success (UC17 Sync)
		contactList.add(person);

		System.out.println("Contact added successfully (DB + Memory).");
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
	public boolean editContact(String firstName, ContactPerson updatedPerson) {

		for (ContactPerson person : contactList) {

			if (person.getFirstName().equalsIgnoreCase(firstName)) {

				person.setAddress(updatedPerson.getAddress());
				person.setCity(updatedPerson.getCity());
				person.setState(updatedPerson.getState());
				person.setZip(updatedPerson.getZip());
				person.setPhoneNumber(updatedPerson.getPhoneNumber());
				person.setEmail(updatedPerson.getEmail());

				return true;
			}
		}

		return false;
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

	// UC13: Read Write from file

	public void writeToFile(String fileName) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

			for (ContactPerson person : contactList) {

				writer.write(person.getFirstName() + "," + person.getLastName() + "," + person.getAddress() + ","
						+ person.getCity() + "," + person.getState() + "," + person.getZip() + ","
						+ person.getPhoneNumber() + "," + person.getEmail());

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

				ContactPerson person = new ContactPerson(data[0], data[1], data[2], data[3], data[4], data[5], data[6],
						data[7]);

				contactList.add(person);
			}

			System.out.println("Contacts loaded from file successfully.");

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}

	// UC14: Read Write from csv

	public void writeToCSV(String fileName) {

		try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {

			for (ContactPerson person : contactList) {

				String[] data = { person.getFirstName(), person.getLastName(), person.getAddress(), person.getCity(),
						person.getState(), person.getZip(), person.getPhoneNumber(), person.getEmail() };

				writer.writeNext(data);
			}

			System.out.println("Contacts written to CSV successfully.");

		} catch (IOException e) {
			System.out.println("Error writing CSV: " + e.getMessage());
		}
	}

	public void readFromCSV(String fileName) {

		try (CSVReader reader = new CSVReader(new FileReader(fileName))) {

			String[] data;

			while ((data = reader.readNext()) != null) {

				ContactPerson person = new ContactPerson(data[0], data[1], data[2], data[3], data[4], data[5], data[6],
						data[7]);

				contactList.add(person);
			}

			System.out.println("Contacts loaded from CSV successfully.");

		} catch (Exception e) {
			System.out.println("Error reading CSV: " + e.getMessage());
		}
	}

	// UC15: Read Write from json

	public void writeToJSON(String fileName) {

		Gson gson = new Gson();

		try (FileWriter writer = new FileWriter(fileName)) {

			gson.toJson(contactList, writer);

			System.out.println("Contacts written to JSON successfully.");

		} catch (IOException e) {
			System.out.println("Error writing JSON: " + e.getMessage());
		}
	}

	public void readFromJSON(String fileName) {

		Gson gson = new Gson();

		try (FileReader reader = new FileReader(fileName)) {

			Type listType = new TypeToken<List<ContactPerson>>() {
			}.getType();

			List<ContactPerson> persons = gson.fromJson(reader, listType);

			if (persons != null) {
				contactList.addAll(persons);
			}

			System.out.println("Contacts loaded from JSON successfully.");

		} catch (Exception e) {
			System.out.println("Error reading JSON: " + e.getMessage());
		}
	}

	// UC:17 Update contact in jdbc

	public boolean updateContactInMemory(String firstName, ContactPerson updatedPerson) {

		for (ContactPerson person : contactList) {

			if (person.getFirstName().equalsIgnoreCase(firstName)) {

				person.setAddress(updatedPerson.getAddress());
				person.setCity(updatedPerson.getCity());
				person.setState(updatedPerson.getState());
				person.setZip(updatedPerson.getZip());
				person.setPhoneNumber(updatedPerson.getPhoneNumber());
				person.setEmail(updatedPerson.getEmail());

				return true;
			}
		}

		return false;
	}
}