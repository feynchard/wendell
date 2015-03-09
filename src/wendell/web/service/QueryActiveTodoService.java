package wendell.web.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import wendell.Todo;

public class QueryActiveTodoService implements WebService {

	@SuppressWarnings("unchecked")
	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json; charset=utf8");
		JSONArray objects = new JSONArray();

		List<Todo> todos = WebServiceHandler.getTodoService()
				.queryTodoByUserAndDirectoryAndProjectAndComplete(
						editing.getLoginUser(), editing.getDirectory(),
						editing.getProject(), false);
		for (Todo todo : todos) {
			objects.add(todo);
		}

		PrintWriter out = resp.getWriter();
		objects.writeJSONString(out);

	}

}
