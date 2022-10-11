package lemonadestand.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lemonadestand.entity.Lemonade;
import lemonadestand.entity.Order;

public class LemonadeDAO implements BaseDAO<Lemonade> {

	public LemonadeDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to find PostgreSQL Driver. Please ensure it is imported with Maven.");
		}
	}

	public Lemonade createLemonade(Lemonade lemonade) {
		try (PreparedStatement createLemonadeStatement = getDBConnection().prepareStatement(
				"INSERT INTO lemonade (lemon_juice, water, sugar, ice_cubes, price, order_id) VALUES (?, ?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {
			createLemonadeStatement.setDouble(1, lemonade.getLemonJuice());
			createLemonadeStatement.setDouble(2, lemonade.getWater());
			createLemonadeStatement.setDouble(3, lemonade.getSugar());
			createLemonadeStatement.setInt(4, lemonade.getIceCubes());
			createLemonadeStatement.setDouble(5, lemonade.getPrice());
			createLemonadeStatement.setInt(6, lemonade.getOrder().getId());
			int created = createLemonadeStatement.executeUpdate();
			if (created == 0) {
				throw new SQLException();
			}
			ResultSet resultSet = createLemonadeStatement.getGeneratedKeys();
			if (resultSet.next()) {
				return new Lemonade(resultSet.getInt(1), lemonade.getLemonJuice(), lemonade.getWater(),
						lemonade.getSugar(), lemonade.getIceCubes(), lemonade.getPrice(), lemonade.getOrder());
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to create lemonade");
		}
		return null;
	}

	@Override
	public PreparedStatement prepareCreateStatement(Lemonade record) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement prepareReadStatement(int id) throws SQLException {
		PreparedStatement getLemonadeById = getDBConnection().prepareStatement(
				"SELECT l.id AS \"lemonade_id\", l.lemon_juice, l.water, l.sugar, l.price, l.ice_cubes, OT.id AS \"order_id\", OT.total FROM lemonade AS l JOIN order_table AS OT ON l.order_id = OT.id WHERE id=?");
		getLemonadeById.setInt(1, id);
		return getLemonadeById;
	}

	@Override
	public Lemonade constructObject(Integer id, Lemonade lemonade) {
		return new Lemonade(id, lemonade.getLemonJuice(), lemonade.getWater(), lemonade.getSugar(),
				lemonade.getIceCubes(), lemonade.getPrice(), lemonade.getOrder());
	}

	@Override
	public Lemonade constructObject(ResultSet resultSet) throws SQLException {
		return new Lemonade(resultSet.getInt("lemonade_id"), resultSet.getDouble("lemonJuice"), resultSet.getDouble("water"),
				resultSet.getDouble("sugar"), resultSet.getInt("IceCubes"), resultSet.getDouble("price"), new Order(resultSet.getInt("order_id"), resultSet.getDouble("total")));
	}
}
