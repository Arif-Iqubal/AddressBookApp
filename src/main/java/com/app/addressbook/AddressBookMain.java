package com.app.addressbook;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {

	public static void main(String[] args) {

		// UC1: Welcome message
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);

		// UC6: System managing multiple address books
		AddressBookSystem system = new AddressBookSystem();

		while (true) {

			System.out.println("\n===== Address Book System =====");
			System.out.println("1. Create Address Book");
			System.out.println("2. Open Address Book");
			System.out.println("3. Search Person by City (UC8)");
			System.out.println("4. Search Person by State (UC8)");
			System.out.println("5. View Persons by City (UC9)");
			System.out.println("6. View Persons by State (UC9)");
			System.out.println("7. Count Persons by City (UC10)");
			System.out.println("8. Count Persons by State (UC10)");
			System.out.println("9. Sort contacts by Name (UC11)");
			System.out.println("10. Exit");

			System.out.print("Enter Choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1:

				System.out.print("Enter Address Book Name: ");
				String bookName = scanner.nextLine();

				system.createAddressBook(bookName);
				break;

			case 2:

				System.out.print("Enter Address Book Name: ");
				String name = scanner.nextLine();

				AddressBook book = system.getAddressBook(name);

				if (book == null) {
					System.out.println("Address Book not found!");
					break;
				}

				manageContacts(book, scanner, system);
				break;

			case 3:

				System.out.print("Enter City: ");
				String city = scanner.nextLine();

				system.viewPersonsByCity(city);
				break;

			case 4:

				System.out.print("Enter State: ");
				String state = scanner.nextLine();

				system.viewPersonsByState(state);
				break;

			case 5:

				System.out.print("Enter City: ");
				String cityView = scanner.nextLine();

				system.viewPersonsByCity(cityView);
				break;

			case 6:

				System.out.print("Enter State: ");
				String stateView = scanner.nextLine();

				system.viewPersonsByState(stateView);
				break;

			case 7:

				system.countPersonsByCity();
				break;

			case 8:

				system.countPersonsByState();
				break;
			case 9:
				AddressBook book1 = new AddressBook();
				book1.sortContactsByName();
				break;

			case 10:

				System.out.println("Exiting program...");
				scanner.close();
				return;

			default:

				System.out.println("Invalid choice!");
			}
		}
	}

	/*
	 * Manage contacts inside AddressBook UC2 → Add Contact UC3 → Edit Contact UC4 →
	 * Delete Contact UC5 → Multiple Contacts UC11 → Sort Contacts
	 */
	private static void manageContacts(AddressBook book, Scanner scanner, AddressBookSystem system) {

		while (true) {

			System.out.println("\n---- Manage Contacts ----");
			System.out.println("1. Add Contact");
			System.out.println("2. Edit Contact");
			System.out.println("3. Delete Contact");
			System.out.println("4. Display Contacts");
			System.out.println("5. Sort Contacts by Name (UC11)");
			System.out.println("5. Sort Contacts by Name (UC11)");
			System.out.println("6. Sort Contacts by City (UC12)");
			System.out.println("7. Sort Contacts by State (UC12)");
			System.out.println("8. Sort Contacts by Zip (UC12)");
			System.out.println("9. Save Contacts to File (UC13)");
			System.out.println("10. Load Contacts from File (UC13)");
			System.out.println("11. Save Contacts to CSV (UC14)");
			System.out.println("12. Load Contacts from CSV (UC14)");
			System.out.println("13. Save Contacts to JSON (UC15)");
			System.out.println("14. Load Contacts from JSON (UC15)");
			System.out.println("15. Retrieve Contacts from Database (UC16)");
			System.out.println("16. Retrieve Contacts by Date Range (UC18)");
			System.out.println("17. Count Contacts by City (DB)");
			System.out.println("18. Count Contacts by State (DB)");
			System.out.println("19. Back");

			System.out.print("Enter Choice: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {

			case 1:

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

				System.out.print("Phone: ");
				String phone = scanner.nextLine();

				System.out.print("Email: ");
				String email = scanner.nextLine();

				ContactPerson person = new ContactPerson(firstName, lastName, address, city, state, zip, phone, email);

				book.addContact(person);

				// UC9: store person in city/state dictionary
				system.addPersonToCityState(person);

				break;

			case 2:

				System.out.print("Enter First Name to Edit: ");
				String editName = "";
				String firstName1 = "";
				String lastName1 = "";
				String address1 = "";
				String city1 = "";
				String state1 = "";
				String zip1 = "";
				String phone1 = "";
				String email1 = "";
				ContactPerson person1 = new ContactPerson(firstName1, lastName1, address1, city1, state1, zip1, phone1,
						email1);
				book.editContact(editName, person1);
				break;

			case 3:

				System.out.print("Enter First Name to Delete: ");
				String deleteName = scanner.nextLine();

				book.deleteContact(deleteName);
				break;

			case 4:

				book.displayContacts();
				break;

			case 5:
				book.sortContactsByName();
				break;

			case 6:
				book.sortContactsByCity();
				break;

			case 7:
				book.sortContactsByState();
				break;

			case 8:
				book.sortContactsByZip();
				break;

			case 9:

				System.out.print("Enter file name: ");
				String writeFile = scanner.nextLine();

				book.writeToFile(writeFile);
				break;

			case 10:

				System.out.print("Enter file name: ");
				String readFile = scanner.nextLine();

				book.readFromFile(readFile);
				break;

			case 11:

				System.out.print("Enter CSV file name: ");
				String writeCSV = scanner.nextLine();

				book.writeToCSV(writeCSV);
				break;

			case 12:

				System.out.print("Enter CSV file name: ");
				String readCSV = scanner.nextLine();

				book.readFromCSV(readCSV);
				break;

			case 13:

				System.out.print("Enter JSON file name: ");
				String writeJSON = scanner.nextLine();

				book.writeToJSON(writeJSON);
				break;

			case 14:

				System.out.print("Enter JSON file name: ");
				String readJSON = scanner.nextLine();

				book.readFromJSON(readJSON);
				break;

			case 15:

				AddressBookService service = new AddressBookService();

				List<ContactPerson> contacts = service.getAllContacts();

				if (contacts.isEmpty()) {
					System.out.println("No records found in DB.");
				} else {
					contacts.forEach(System.out::println);
				}

				break;

			case 16:

				System.out.print("Enter Start Date (YYYY-MM-DD): ");
				String start = scanner.nextLine();

				System.out.print("Enter End Date (YYYY-MM-DD): ");
				String end = scanner.nextLine();

				AddressBookService service1 = new AddressBookService();

				List<ContactPerson> contacts1 = service1.getContactsByDateRange(start, end);

				if (contacts1.isEmpty()) {
					System.out.println("No contacts found in given date range.");
				} else {
					contacts1.forEach(System.out::println);
				}

				break;

			case 17:

				AddressBookService service11 = new AddressBookService();

				Map<String, Integer> cityCount = service11.getContactCountByCity();

				cityCount.forEach((x, count) -> System.out.println(x + " : " + count));

				break;

			case 18:

				AddressBookService service2 = new AddressBookService();

				Map<String, Integer> stateCount = service2.getContactCountByState();

				stateCount.forEach((x, count) -> System.out.println(x + " : " + count));

				break;

			case 19:
				return;

			default:

				System.out.println("Invalid choice!");
			}
		}
	}
}