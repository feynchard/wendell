package wendell.web.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import wendell.Project;
import wendell.TodoException;
import wendell.user.User;

import com.googlecode.objectify.Key;

public class CreateProjectService implements WebService {

	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {
		try {
			WebServiceHandler.getTodoService().createNewProjejt(
					editing.getLoginUser(),
					new Project(Key.create(User.class, editing.getLoginUser()
							.getId()), editing.getTemp()));
		} catch (TodoException e) {
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println(e.getMessage());
		}

	}
}
