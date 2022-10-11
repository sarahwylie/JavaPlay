package lemonadestand.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lemonadestand.entity.Entity;

public interface BaseDAO<T extends Entity> {

	PreparedStatement prepareCreateStatement(T record) throws SQLException;

	PreparedStatement prepareReadStatement(int id) throws SQLException;

	T constructObject(Integer id, T record);

	T constructObject(ResultSet resultSet) throws SQLException;

	default Connection getDBConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:3001/postgres", "postgres", "root");
	}

	default T create(T record) {
		try (PreparedStatement createStatement = prepareCreateStatement(record)) {
			int created = createStatement.executeUpdate();
			if (created == 0) {
				throw new SQLException();
			}
			ResultSet resultSet = createStatement.getGeneratedKeys();
			if (resultSet.next()) {
				return constructObject(resultSet.getInt(1), record);
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to create");
		}
		return null;
	}

	default T getById(int id) {
		T result = null;
		try (PreparedStatement readStatement = prepareReadStatement(id)) {
			ResultSet resultSet = readStatement.executeQuery();
			while (resultSet.next()) {
				return constructObject(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	default boolean update(T record) {
		return false;
	}
	
	default boolean delete (T record) {
		return false;
	}
}
