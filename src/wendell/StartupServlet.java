package wendell;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import wendell.user.User;

import com.googlecode.objectify.ObjectifyService;

public class StartupServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		ObjectifyService.register(User.class);
		ObjectifyService.register(Todo.class);
		ObjectifyService.register(Directory.class);
		ObjectifyService.register(Project.class);

	}
}
