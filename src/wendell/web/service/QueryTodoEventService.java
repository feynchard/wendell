package wendell.web.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import wendell.Todo;

public class QueryTodoEventService implements WebService {

	@SuppressWarnings("unchecked")
	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json; charset=utf8");
		JSONArray objects = new JSONArray();

		Set<Todo> todoes = WebServiceHandler.getTodoService()
				.queryTodoEventByUserAndStartDateAndEndDate(
						editing.getLoginUser(), editing.getStartDate(),
						editing.getEndDate());

		for (Todo todo : todoes) {
			objects.add(todo);
		}

		PrintWriter out = resp.getWriter();
		objects.writeJSONString(out);
	}

}
