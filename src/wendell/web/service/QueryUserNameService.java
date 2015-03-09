package wendell.web.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class QueryUserNameService implements WebService {

	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain; charset=utf8");
		PrintWriter out = resp.getWriter();
		out.println(editing.getLoginUser().getName());

	}

}
