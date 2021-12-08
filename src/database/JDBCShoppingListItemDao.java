package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ShoppingListItem;

public class JDBCShoppingListItemDao implements ShoppingListItemDao {

	@Override
	public List<ShoppingListItem> getAllItems() {
		List<ShoppingListItem> items = new ArrayList<ShoppingListItem>();
		Database database = new Database();
		Connection connection = database.connect();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM ShoppingListItem");
			ResultSet results = statement.executeQuery();

			while (results.next()) {
				ShoppingListItem item = new ShoppingListItem();
				item.setId(results.getInt("id"));
				item.setOstos(results.getString("title"));
				items.add(item);
			}
			results.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public ShoppingListItem getItem(long id) {
		System.out.println("LONGID: " + id);
		ShoppingListItem item = new ShoppingListItem();
		Database database = new Database();
		Connection connection = database.connect();
		int intId = Integer.parseInt((id + ""));
		System.out.println("JUKKA " + intId);
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM ShoppingListItem");
			ResultSet results = statement.executeQuery();

			while (results.next()) {
				System.out.println("JUKKA " + results.getInt("id"));
				if (intId == results.getInt("id")) {
					System.out.println("LÖYTYI");
					item.setId(results.getInt("id"));
					item.setOstos(results.getString("title"));
				}
			}
			results.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean addItem(ShoppingListItem newItem) {
		Database database = new Database();
		Connection connection = database.connect();
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO ShoppingListItem(title) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, newItem.getOstos());
			statement.executeUpdate();

			ResultSet keys = statement.getGeneratedKeys();
			if (keys.next()) {
				long id = keys.getLong(1);

			}
			keys.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean removeItem(ShoppingListItem item) {
		Database database = new Database();
		Connection connection = database.connect();
		int deletedId = 0;
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM ShoppingListItem WHERE title LIKE(?)");
			statement.setString(1, item.getOstos());
			ResultSet results = statement.executeQuery();

			deletedId = results.getInt("id");

			results.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		database = new Database();
		connection = database.connect();
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM ShoppingListItem WHERE id = ?");
			statement.setInt(1, deletedId);
			statement.executeUpdate();

			statement.close();
			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}