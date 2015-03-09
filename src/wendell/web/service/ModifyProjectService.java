package wendell.web.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import wendell.TodoException;
import wendell.TodoService;

public class ModifyProjectService implements WebService {

	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {
		try {
			TodoService todoService = WebServiceHandler.getTodoService();
			todoService.modifyProject(editing.getLoginUser(),
					editing.getProject(), editing.getTemp());
		} catch (TodoException e) {
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println(e.getMessage());
		}

	}

}
