package servlet;

import java.util.List;
import java.util.Scanner;

import database.JDBCShoppingListItemDao;
import model.ShoppingListItem;

public class ShoppingListProgram {


	public void listaaTuotteet() {
		JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao();
		System.out.println("Shopping list contents:");
		List<ShoppingListItem> items = dao.getAllItems();
		for (int i = 0; i < items.size(); i++) {
			ShoppingListItem item = items.get(i);
			System.out.println("(" + item.getId() + ") " + item.getOstos());
		}
		System.out.println();
		
	}

	public void lisaaTuote(String tuote) {
		ShoppingListItem newItem = new ShoppingListItem();
		newItem.setOstos(tuote);
		JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao();
		dao.addItem(newItem);
		System.out.println("Successfully added " + tuote);
		System.out.println();
	}

	public void poistaTuote(String tuote) {
		ShoppingListItem item = new ShoppingListItem();
		item.setOstos(tuote);
		JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao();
		dao.removeItem(item);
		System.out.println("Successfully deleted " + tuote);
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ShoppingListProgram program = new ShoppingListProgram();
		while (true) {
			String komento = "";
			System.out.println("Welcome to the shopping list app!");
			System.out.println("Available commands:");
			System.out.println("list");
			System.out.println("add [product name]");
			System.out.println("remove [product name]");
			System.out.println("quit");
			System.out.println("");
			System.out.print("> ");
			komento = input.nextLine();
			String[] solut = komento.split(" ");
			if (solut[0].equals("list")) {
				program.listaaTuotteet();
			} else if (solut[0].equals("add")) {
				program.lisaaTuote(solut[1]);
			} else if (solut[0].equals("remove")) {
				program.poistaTuote(solut[1]);
			} else if (solut[0].equals("quit")) {
				break;
			} else {
				System.out.println("Ohjelma on lopetettu, koska annoit " + "komennon quit tai virheellisen komennon.");
				System.exit(0);
			}
		}

	}

}
