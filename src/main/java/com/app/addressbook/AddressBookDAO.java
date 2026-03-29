package com.app.addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDAO {

	/*
	 * UC16: Retrieve all contacts from DB
	 */
	public List<ContactPerson> getAllContacts() {

		List<ContactPerson> contactList = new ArrayList<>();

		String query = "SELECT * FROM contacts";

		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {

				ContactPerson person = new ContactPerson(rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"),
						rs.getString("phone"), rs.getString("email"));

				contactList.add(person);
			}

		} catch (SQLException e) {
			System.out.println("DB Error: " + e.getMessage());
		}

		return contactList;
	}
}