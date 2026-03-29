package com.app.addressbook;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookServiceTest {

	@Test
	public void givenDB_whenRetrieveContacts_shouldReturnList() {

		AddressBookService service = new AddressBookService();

		List<ContactPerson> contacts = service.getAllContacts();

		assertNotNull(contacts);
		assertTrue(contacts.size() >= 0);
	}
}