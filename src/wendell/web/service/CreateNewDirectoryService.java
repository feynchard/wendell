package wendell.web.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import wendell.Directory;
import wendell.TodoException;
import wendell.TodoService;
import wendell.user.User;

import com.googlecode.objectify.Key;

public class CreateNewDirectoryService implements WebService {

	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {
		try {
			TodoService todoService = WebServiceHandler.getTodoService();
			Integer order = todoService.getLastNormalDirectoryOrder(editing
					.getLoginUser());
			todoService.createDirectory(editing.getLoginUser(), new Directory(
					Key.create(User.class, editing.getLoginUser().getId()),
					editing.getTemp(), order + 1));
		} catch (TodoException e) {
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println(e.getMessage());
		}

	}

}
