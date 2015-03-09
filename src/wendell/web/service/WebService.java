package wendell.web.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface WebService {

	public void action(RequestParameterEditing editing, HttpServletResponse resp) throws IOException;
	

}
