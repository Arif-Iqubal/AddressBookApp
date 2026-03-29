package com.app.addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public boolean insertContact(ContactPerson person) {

		String query = "INSERT INTO contacts (first_name, last_name, address, city, state, zip, phone, email, date_added) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setString(3, person.getAddress());
			ps.setString(4, person.getCity());
			ps.setString(5, person.getState());
			ps.setString(6, person.getZip());
			ps.setString(7, person.getPhoneNumber());
			ps.setString(8, person.getEmail());

			// UC18: Add current date
			ps.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("DB Insert Error: " + e.getMessage());
		}

		return false;
	}
	// UC17: Update contact using jdbc

	public boolean updateContact(String firstName, ContactPerson updatedPerson) {

		String query = "UPDATE contacts SET address=?, city=?, state=?, zip=?, phone=?, email=? WHERE first_name=?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, updatedPerson.getAddress());
			ps.setString(2, updatedPerson.getCity());
			ps.setString(3, updatedPerson.getState());
			ps.setString(4, updatedPerson.getZip());
			ps.setString(5, updatedPerson.getPhoneNumber());
			ps.setString(6, updatedPerson.getEmail());
			ps.setString(7, firstName);

			int rowsUpdated = ps.executeUpdate();

			return rowsUpdated > 0;

		} catch (SQLException e) {
			System.out.println("DB Update Error: " + e.getMessage());
		}

		return false;
	}

	// UC18 :
	public List<ContactPerson> getContactsByDateRange(String startDate, String endDate) {

		List<ContactPerson> list = new ArrayList<>();

		String query = "SELECT * FROM contacts WHERE date_added BETWEEN ? AND ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setDate(1, java.sql.Date.valueOf(startDate));
			ps.setDate(2, java.sql.Date.valueOf(endDate));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ContactPerson person = new ContactPerson(rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"),
						rs.getString("phone"), rs.getString("email"));

				list.add(person);
			}

		} catch (SQLException e) {
			System.out.println("DB Fetch Error: " + e.getMessage());
		}

		return list;
	}

	// UC19: Count by state
	public Map<String, Integer> getContactCountByCity() {

		Map<String, Integer> result = new HashMap<>();

		String query = "SELECT city, COUNT(*) as count FROM contacts GROUP BY city";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				result.put(rs.getString("city"), rs.getInt("count"));
			}

		} catch (SQLException e) {
			System.out.println("DB Error: " + e.getMessage());
		}

		return result;
	}

	public Map<String, Integer> getContactCountByState() {

		Map<String, Integer> stateCountMap = new HashMap<>();

		String query = "SELECT state, COUNT(*) AS count FROM contacts GROUP BY state";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				String state = rs.getString("state");
				int count = rs.getInt("count");

				stateCountMap.put(state, count);
			}

		} catch (SQLException e) {
			System.out.println("DB Error: " + e.getMessage());
		}

		return stateCountMap;
	}

	public boolean addContactWithTransaction(ContactPerson person) {

		Connection conn = null;

		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);

			String query = "INSERT INTO contacts (first_name, last_name, address, city, state, zip, phone, email, date_added) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setString(3, person.getAddress());
			ps.setString(4, person.getCity());
			ps.setString(5, person.getState());
			ps.setString(6, person.getZip());
			ps.setString(7, person.getPhoneNumber());
			ps.setString(8, person.getEmail());
			ps.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));

			ps.executeUpdate();

			conn.commit();
			return true;

		} catch (Exception e) {

			try {
				if (conn != null)
					conn.rollback();
			} catch (Exception ex) {
				System.out.println("Rollback failed");
			}

		} finally {

			try {
				if (conn != null)
					conn.setAutoCommit(true);
			} catch (Exception e) {
			}
		}

		return false;
	}
}