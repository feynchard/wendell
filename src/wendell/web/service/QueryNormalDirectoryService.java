package wendell.web.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import wendell.Directory;

public class QueryNormalDirectoryService implements WebService {

	@SuppressWarnings("unchecked")
	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("application/json; charset=utf8");
		JSONArray objects = new JSONArray();

		List<Directory> directories = WebServiceHandler.getTodoService()
				.queryNormalDirectoryByUser(editing.getLoginUser());
		for (Directory directory : directories) {
			objects.add(directory);
		}

		PrintWriter out = resp.getWriter();
		objects.writeJSONString(out);
	}

}
