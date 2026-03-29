package com.app.addressbook;

import java.util.Objects;

public class ContactPerson {

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

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}
	

	public String getAddress() {
		return address;
	}

	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
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
	
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!(obj instanceof ContactPerson))
			return false;

		ContactPerson person = (ContactPerson) obj;

		return firstName.equalsIgnoreCase(person.firstName) && lastName.equalsIgnoreCase(person.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName.toLowerCase(), lastName.toLowerCase());
	}

	/*
	 * UC11: toString() used for printing sorted entries
	 */
	@Override
	public String toString() {
		return "Name: " + firstName + " " + lastName + ", City: " + city + ", State: " + state + ", Phone: "
				+ phoneNumber + ", Email: " + email;
	}
}