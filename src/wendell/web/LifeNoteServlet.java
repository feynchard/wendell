package wendell.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LifeNoteServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher view;
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("loginUser") == null) {
			view = req.getRequestDispatcher("/jsp/LoginPage.jsp");
			view.forward(req, resp);
			return;
		}

		view = req.getRequestDispatcher("/jsp/LifeNote.jsp");
		view.forward(req, resp);
	}
}
