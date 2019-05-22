package com.julianrendon.interfaceDAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.julianrendon.connection.ConnectionFactory;
import com.julianrendon.interfaceDAO.PersonDAO;
import com.julianrendon.model.Person;

public class PersonDAOImplementation implements PersonDAO {

	Connection connection = ConnectionFactory.getConnection();

	// Constructor
	public PersonDAOImplementation() {

	}

	// Create a new Person
	public void create(Person person) {

		PreparedStatement preparedStatement;

		try {
			String createQuery = "INSERT INTO PERSONS(id, name) VALUES(?,?)";
			preparedStatement = connection.prepareStatement(createQuery);
			preparedStatement.setInt(1, person.getId());
			preparedStatement.setString(2, person.getName());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Find by id
	@Override
	public Person findById(int id) {

		Person person = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			String selectIdQuery = "SELECT * FROM persons where id = ?";
			preparedStatement = connection.prepareStatement(selectIdQuery);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		try {
			resultSet.next();
			person = new Person();
			person.setId(resultSet.getInt("id"));
			person.setName(resultSet.getString("name"));
			resultSet.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return person;
	}

	// find All people
	public List<Person> findAll() {

		List<Person> persons = new ArrayList<>();
		Person person = null;
		ResultSet resultSet;
		PreparedStatement preparedStatement;

		try {
			String selectAllQuery = "SELECT * FROM PERSONS ORDER BY ID";
			preparedStatement = connection.prepareStatement(selectAllQuery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getInt("id"));
				person.setName(resultSet.getString("name"));
				persons.add(person);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return persons;
	}

	// Update person's info
	public void update(Person person) {

		PreparedStatement preparedStatement;

		try {
			String updateQuery = "UPDATE PERSONS SET NAME = ? WHERE ID = ?";
			// System.out.println("Query = " + updateQuery);
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// Delete person
	public void delete(int id) {

		PreparedStatement preparedStatement;

		try {
			String deleteQuery = "DELETE FROM PERSONS WHERE ID =" + id;
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
