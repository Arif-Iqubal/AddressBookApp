package com.app.addressbook;

import java.util.Scanner;

public class AddressBookMain {

	public static void main(String[] args) {

		// UC1: Welcome message
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);

		// UC6: System that manages multiple address books
		AddressBookSystem system = new AddressBookSystem();

		while (true) {

			System.out.println("\n1. Create New Address Book");
			System.out.println("2. Select Address Book");
			System.out.println("3. Exit");

			System.out.print("Enter Choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1:

				// UC6: Create new AddressBook with unique name
				System.out.print("Enter Address Book Name: ");
				String bookName = scanner.nextLine();

				system.createAddressBook(bookName);
				break;

			case 2:

				System.out.print("Enter Address Book Name: ");
				String name = scanner.nextLine();

				AddressBook addressBook = system.getAddressBook(name);

				if (addressBook == null) {
					System.out.println("Address Book not found!");
					break;
				}

				system.manageAddressBook(addressBook, scanner);
				break;

			case 3:
				System.out.println("Exiting...");
				scanner.close();
				return;

			default:
				System.out.println("Invalid Choice");
			}
		}
	}
}