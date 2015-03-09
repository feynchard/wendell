package wendell.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebServiceServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestParameterEditing requestParameterEditing = WebServiceHandler
				.getRequestParameterEditing(req);

		WebService webService = WebServiceHandler
				.createWebService(ActionCommand
						.findActionCommand(requestParameterEditing.getAction()));

		webService.action(requestParameterEditing, resp);
	}
}
