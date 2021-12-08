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

@WebServlet("/showaddingform")
public class ShowAddingForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	
        // lähetä request edelleen index.jsp sivulle
        req.getRequestDispatcher("/WEB-INF/addshopping.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String title = req.getParameter("title");
    	
		ShoppingListItem newItem = new ShoppingListItem();
		newItem.setOstos(title);
		JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao();
		dao.addItem(newItem);
    	
        resp.sendRedirect("/shoppinglist");
    }
    
}
