package com.app.addressbook;

import java.util.Objects;

public class ContactPerson {

	// UC1: Contact fields
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String email;

	public ContactPerson(String firstName, String lastName, String address, String city, String state, String zip,
			String phoneNumber, String email) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * UC7: Override equals() method to check duplicate entry Duplicate defined by
	 * same firstName and lastName
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		ContactPerson person = (ContactPerson) obj;

		return firstName.equalsIgnoreCase(person.firstName) && lastName.equalsIgnoreCase(person.lastName);
	}

	/*
	 * Required when equals() is overridden
	 */
	@Override
	public int hashCode() {
		return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase());
	}

	public void displayContact() {

		System.out.println("---------------------------");
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("Address: " + address);
		System.out.println("City: " + city);
		System.out.println("State: " + state);
		System.out.println("Zip: " + zip);
		System.out.println("Phone: " + phoneNumber);
		System.out.println("Email: " + email);
	}
}