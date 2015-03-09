package wendell.web.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import wendell.Project;

public class QueryProjectService implements WebService {

	@SuppressWarnings("unchecked")
	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json; charset=utf8");
		JSONArray objects = new JSONArray();

		List<Project> projects = WebServiceHandler.getTodoService()
				.queryProjectByUser(editing.getLoginUser());
		for (Project project : projects) {
			objects.add(project);
		}
		PrintWriter out = resp.getWriter();
		objects.writeJSONString(out);

	}

}
