package wendell.web.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import wendell.Todo;
import wendell.TodoException;
import wendell.TodoService;

public class ModifyTodoService implements WebService {

	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {
		try {
			TodoService todoService = WebServiceHandler.getTodoService();
			Todo todo = todoService.queryTodoById(editing.getLoginUser(),
					editing.getId());
			todoService.modifyTodo(editing, todo);
		} catch (TodoException e) {
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println(e.getMessage());
		}
	}

}
