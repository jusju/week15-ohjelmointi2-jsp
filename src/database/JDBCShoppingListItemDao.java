package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ShoppingListItem;

public class JDBCShoppingListItemDao implements ShoppingListItemDao {

	@Override
	public List<ShoppingListItem> getAllItems() {
		String URL = "jdbc:sqlite:/tmp/shoppinglist.sqlite";
		ResultSet results = null;
		PreparedStatement statement = null;

		List<ShoppingListItem> items = new ArrayList<ShoppingListItem>();

		Connection connection = null;

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(URL);
			statement = connection.prepareStatement("SELECT * FROM ShoppingListItem");
			results = statement.executeQuery();

			while (results.next()) {
				ShoppingListItem item = new ShoppingListItem();
				item.setId(results.getInt("id"));
				item.setOstos(results.getString("title"));
				items.add(item);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return items;
	}

	@Override
	public ShoppingListItem getItem(long id) {
		String URL = "jdbc:sqlite:/tmp/shoppinglist.sqlite";
		System.out.println("LONGID: " + id);
		ShoppingListItem item = new ShoppingListItem();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		int intId = Integer.parseInt((id + ""));
		System.out.println("JUKKA " + intId);
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(URL);
			statement = connection.prepareStatement("SELECT * FROM ShoppingListItem");
			results = statement.executeQuery();

			while (results.next()) {
				System.out.println("JUKKA " + results.getInt("id"));
				if (intId == results.getInt("id")) {
					System.out.println("LÖYTYI");
					item.setId(results.getInt("id"));
					item.setOstos(results.getString("title"));
				}
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}

	@Override
	public boolean addItem(ShoppingListItem newItem) {
		String URL = "jdbc:sqlite:/tmp/shoppinglist.sqlite";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(URL);
			statement = connection.prepareStatement("INSERT INTO ShoppingListItem(title) VALUES (?)",
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
		String URL = "jdbc:sqlite:/tmp/shoppinglist.sqlite";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		int deletedId = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(URL);
			statement = connection
					.prepareStatement("SELECT * FROM ShoppingListItem WHERE title LIKE(?)");
			statement.setString(1, item.getOstos());
			results = statement.executeQuery();

			deletedId = results.getInt("id");



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 



		try {
			statement = connection.prepareStatement("DELETE FROM ShoppingListItem WHERE id = ?");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			statement.setInt(1, deletedId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("KIRJOTELLAAN!!!");
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		return true;
	}
}