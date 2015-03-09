package wendell.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wendell.user.User;
import wendell.web.service.WebServiceHandler;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		RequestDispatcher view;
		if (session.getAttribute("loginUser") != null) {
			view = req.getRequestDispatcher("/jsp/LifeNote.jsp");
			view.forward(req, resp);
			return;
		}

		view = req.getRequestDispatcher("/jsp/LoginPage.jsp");
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String password = req.getParameter("password");

		RequestDispatcher view;

		User user = WebServiceHandler.getUserService().queryUserByEmail(
				req.getParameter("email"));
		if (user == null || !password.equals(user.getPassword())) {
			req.setAttribute("loginWarning", "email或password有誤，請重新填寫。");
			view = req.getRequestDispatcher("/jsp/LoginPage.jsp");
			view.forward(req, resp);
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", user);
			view = req.getRequestDispatcher("/jsp/LifeNote.jsp");
			view.forward(req, resp);
		}

	}
}
