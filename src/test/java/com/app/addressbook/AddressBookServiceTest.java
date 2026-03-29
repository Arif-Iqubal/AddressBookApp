package com.app.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookServiceTest {

	private AddressBook addressBook;

	@BeforeEach
	void setUp() {
		addressBook = new AddressBook();
	}

	/*
	 * UC2: Add Contact Test
	 */
	@Test
	void givenNewContact_whenAdded_shouldReturnTrue() {

		ContactPerson person = new ContactPerson("Arif", "Iqbal", "Bhopal", "Bhopal", "MP", "462001", "9999999999",
				"arif@mail.com");

		addressBook.addContact(person);

		assertEquals(1, addressBook.getContacts().size());
	}

	/*
	 * UC7: Duplicate Contact Test
	 */
	@Test
	void givenDuplicateContact_whenAdded_shouldNotAdd() {

		ContactPerson person1 = new ContactPerson("Arif", "Iqbal", "Bhopal", "Bhopal", "MP", "462001", "999",
				"a@mail.com");
		ContactPerson person2 = new ContactPerson("Arif", "Iqbal", "Bhopal", "Bhopal", "MP", "462001", "999",
				"a@mail.com");

		addressBook.addContact(person1);
		addressBook.addContact(person2);

		assertEquals(1, addressBook.getContacts().size());
	}

	/*
	 * UC3: Edit Contact Test
	 */
	@Test
	void givenExistingContact_whenEdited_shouldUpdateDetails() {

		ContactPerson person = new ContactPerson("Arif", "Iqbal", "Bhopal", "Bhopal", "MP", "462001", "999",
				"a@mail.com");

		addressBook.addContact(person);

		ContactPerson updated = new ContactPerson("Arif", "Iqbal", "Delhi", "Delhi", "DL", "110001", "888",
				"new@mail.com");

		boolean result = addressBook.editContact("Arif", updated);

		assertTrue(result);
		assertEquals("Delhi", addressBook.getContacts().get(0).getCity());
	}

	/*
	 * UC4: Delete Contact Test
	 */
	@Test
	void givenContact_whenDeleted_shouldRemove() {

		ContactPerson person = new ContactPerson("Arif", "Iqbal", "Bhopal", "Bhopal", "MP", "462001", "999",
				"a@mail.com");

		addressBook.addContact(person);

		addressBook.deleteContact("Arif");

		assertEquals(0, addressBook.getContacts().size());
	}

	@Test
	public void givenDB_whenRetrieveContacts_shouldReturnList() {

		AddressBookService service = new AddressBookService();

		List<ContactPerson> contacts = service.getAllContacts();

		assertNotNull(contacts);
		assertTrue(contacts.size() >= 0);
	}

	@Test
	void givenDB_whenFetched_shouldReturnList() {

		AddressBookService service = new AddressBookService();

		assertNotNull(service.getAllContacts());
	}

}