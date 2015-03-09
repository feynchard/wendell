package wendell.web.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import wendell.Todo;
import wendell.TodoService;

public class RecoverTodoService implements WebService {

	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {
		TodoService todoService = WebServiceHandler.getTodoService();
		Todo todo = todoService.queryTodoById(editing.getLoginUser(),
				editing.getId());
		todo.recover();
		todoService.saveTodo(todo);

	}

}
