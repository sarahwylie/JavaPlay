package lemonadestand.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lemonadestand.entity.Customer;
import lemonadestand.entity.LemonadeStand;
import lemonadestand.entity.Order;

public class OrderDAO {

	public OrderDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to find PostgreSQL Driver. Please ensure it is imported with Maven.");
		}
	}

	private Connection getDBConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:3001/postgres", "postgres", "root");
	}

	public Order createOrder(Order order) {
		try (PreparedStatement createOrderStatement = getDBConnection().prepareStatement(
				"INSERT INTO order_table (total, customer_id, lemonade_stand_id) VALUES (?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);) {
			createOrderStatement.setDouble(1, order.getTotal());
			createOrderStatement.setInt(2, order.getCustomer().getId());
			createOrderStatement.setInt(3, order.getLemonadeStand().getId());
			int created = createOrderStatement.executeUpdate();
			if (created == 0) {
				throw new SQLException();
			}
			ResultSet resultSet = createOrderStatement.getGeneratedKeys();
			if (resultSet.next()) {
				return new Order(resultSet.getInt(1), order.getTotal(), order.getCustomer(), order.getLemonadeStand());
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("Unable to create your order!");
		}
		return null;
	}

	public Order getOrder(int id) {
		Order order = null;
		try (PreparedStatement preparedStatement = getDBConnection().prepareStatement(
				"SELECT OT.id AS \"order_id\", OT.total, C.id AS \"customer_id\", C.name AS \"customer_name\", C.phone_number, LS.id AS \"lemonade_stand_id\", LS.name as \"lemonade_stand_name\" FROM order_table AS OT JOIN customer as C ON OT.customer_id = C.id JOIN lemonade_stand AS LS on OT.lemonade_stand_id = LS.id WHERE id=?")) {
//			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM order WHERE id=?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				order = new Order(resultSet.getInt("order_id"), resultSet.getDouble("total"),
						new Customer(resultSet.getInt("customer_id"), resultSet.getString("customer_name"),
								resultSet.getString("phoneNumber")),
						new LemonadeStand(resultSet.getInt("lemonade_stand_id"),
								resultSet.getString("lemonade_stand_name")));
//				order = new Order(resultSet.getInt("id"), resultSet.getDouble("total"));
//				PreparedStatement customerStatement = connection.prepareStatement("SELECT * FROM customer WHERE id=?");
//				customerStatement.setInt(1,  resultSet.getInt("customer_id"));
//				PreparedStatement lemonadeStandStatement = connection.prepareStatement("SELECT * FROM lemonadeStand WHERE id=?");
//				lemonadeStandStatement.setInt(1,  resultSet.getInt("lemonade_stand_id"));
			}
		} catch (SQLException e) {
			System.out.println("Order with id " + id + "does not exist");
		}
		return order;
	}

	public boolean updateOrder(Order order) {
		try (PreparedStatement updateOrderStatement = getDBConnection()
				.prepareStatement("UPDATE order_table SET total=? WHERE id=?");) {
			updateOrderStatement.setDouble(1, order.getTotal());
			updateOrderStatement.setInt(2, order.getId());
			int updated = updateOrderStatement.executeUpdate();
			if (updated == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("Unable to update your order!");
			e.printStackTrace();
		}
		return true;
	}
}
