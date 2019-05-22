package com.julianrendon.testclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.julianrendon.connection.ConnectionFactory;

public class MainArrayTest {

	public static void main(String[] args) throws SQLException {

		// Change USER to "hr" in the connection factory
		
		Connection connection = ConnectionFactory.getConnection();

		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		preparedStatement = connection.prepareStatement("SELECT * FROM employees");
		// preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();

		ArrayList<String> columnNames = new ArrayList<String>();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		System.out.println("There is " + columnCount + " columns in this table");

		for (int i = 1; i <= columnCount; i++) {
			columnNames.add(metaData.getColumnName(i));
		}

		System.out.println("Columns names: " + columnNames);

	}

}
