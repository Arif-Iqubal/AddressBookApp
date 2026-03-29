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

	public boolean updateContact(String firstName, ContactPerson updatedPerson) {
		return dao.updateContact(firstName, updatedPerson);
	}

	public boolean isSyncedWithDB(String firstName, ContactPerson localPerson) {

		List<ContactPerson> dbContacts = dao.getAllContacts();

		for (ContactPerson dbPerson : dbContacts) {

			if (dbPerson.getFirstName().equalsIgnoreCase(firstName)) {

				return dbPerson.equals(localPerson);
			}
		}

		return false;
	}

	//UC18: 
	public List<ContactPerson> getContactsByDateRange(String startDate, String endDate) {
		return dao.getContactsByDateRange(startDate, endDate);
	}
}