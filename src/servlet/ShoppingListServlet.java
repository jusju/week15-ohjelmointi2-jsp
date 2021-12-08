package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.ShoppingListItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCShoppingListItemDao;

@WebServlet("/shoppinglist")
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    

		JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao();
		System.out.println("Shopping list contents:");
		List<ShoppingListItem> items = dao.getAllItems();
        
        
        
        // lähetetään aika merkkijono JSP-sivulle attribuuttina
        req.setAttribute("shoppingitems", items);

        // lähetä request edelleen index.jsp sivulle
        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }
}
