package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.JDBCShoppingListItemDao;
import model.ShoppingListItem;

@WebServlet("/list")
public class DeleteShoppingListItem extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, 
    	IOException {
    	
    	String id = req.getParameter("id");
    	JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao();
    	System.out.println("JUKKA");
    	System.out.println(id);
    	ShoppingListItem item = dao.getItem(Long.parseLong(id));
    	dao.removeItem(item);

        String json = new Gson().toJson(item);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(json);
    	
    }
    

    
}