package wendell.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wendell.user.User;
import wendell.user.UserException;
import wendell.user.UserService;
import wendell.web.service.WebServiceHandler;

public class CreateUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher view = req
				.getRequestDispatcher("/jsp/CreateUserPage.jsp");
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = WebServiceHandler.getUserService();
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String re_password = req.getParameter("re-password");

		User oldUser = userService.queryUserByEmail(email);

		RequestDispatcher view;

		Boolean createUserFail = false;
		if (email == "" || re_password == "" || password == "" || name == "") {
			req.setAttribute("createUserWarning", "資料不齊全，請重新填寫。");
			createUserFail = true;
		} else if (oldUser != null) {
			req.setAttribute("createUserWarning", "此email已經有人使用，請重新填寫。");
			createUserFail = true;
		} else if (!password.equals(re_password)) {
			req.setAttribute("createUserWarning", "確認密碼失敗，請重新填寫。");
			createUserFail = true;
		}

		if (createUserFail) {
			view = req.getRequestDispatcher("/jsp/CreateUserPage.jsp");
			view.forward(req, resp);
		} else {
			try {
				User user = userService.createUser(name, email, password);
				WebServiceHandler.getTodoService().initUserProfile(user);

				HttpSession session = req.getSession();
				session.setAttribute("loginUser", user);
				resp.sendRedirect("/wendell");
			} catch (UserException e) {
				req.setAttribute("createUserWarning", e.getMessage());
				view = req.getRequestDispatcher("/jsp/CreateUserPage.jsp");
				view.forward(req, resp);
			}
		}
	}
}
