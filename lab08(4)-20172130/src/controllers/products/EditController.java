package controllers.products;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import controller.PMF;
import controller.users.AccessControllerU;
import model.entity.*;

public class EditController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (AccessControllerU.isPermited(req.getServletPath(), req, resp, this)){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Product user = pm.getObjectById(Product.class, Long.parseLong(req.getParameter("id")));
		pm.close();
		req.setAttribute("invoice", user);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/edit.jsp");
		rd.forward(req, resp);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Product user = pm.getObjectById(Product.class, Long.parseLong(req.getParameter("id")));
		user.setCost(Double.parseDouble(req.getParameter("cost")));
		user.setQuant(Integer.parseInt(req.getParameter("quant")));
		user.setCode(req.getParameter("code"));
		user.setNombre(req.getParameter("nombre"));
		pm.close();
 		req.setAttribute("invoice", user);
		resp.sendRedirect("/products/view?id="+req.getParameter("id"));
	}
}
