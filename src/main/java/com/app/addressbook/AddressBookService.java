package com.app.addressbook;

import java.util.List;

public class AddressBookService {

	private AddressBookDAO dao = new AddressBookDAO();

	/*
	 * UC16: Service method to fetch contacts
	 */
	public List<ContactPerson> getAllContacts() {
		return dao.getAllContacts();
	}
}