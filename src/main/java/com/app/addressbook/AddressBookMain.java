package com.app.addressbook;

import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {

        // UC1: Display welcome message
        System.out.println("Welcome to Address Book Program");

        Scanner scanner = new Scanner(System.in);

        AddressBook addressBook = new AddressBook();

        /*
        UC2: Add a new contact using console input
        */

        System.out.println("\nEnter Contact Details");

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

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        ContactPerson person = new ContactPerson(firstName, lastName, address,
                city, state, zip, phoneNumber, email);

        addressBook.addContact(person);

        System.out.println("\nContact List:");
        addressBook.displayContacts();


        /*
        UC3: Edit existing contact using name
        */

        System.out.print("\nEnter First Name to Edit Contact: ");
        String editName = scanner.nextLine();

        addressBook.editContact(editName, scanner);

        System.out.println("\nUpdated Contact List:");
        addressBook.displayContacts();


        /*
        UC4: Delete contact using person's name
        */

        System.out.print("\nEnter First Name to Delete Contact: ");
        String deleteName = scanner.nextLine();

        addressBook.deleteContact(deleteName);

        System.out.println("\nContact List After Deletion:");
        addressBook.displayContacts();

        scanner.close();
    }
}