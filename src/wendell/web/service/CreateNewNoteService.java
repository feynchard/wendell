package wendell.web.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import wendell.Directory;
import wendell.Project;
import wendell.Todo;
import wendell.TodoService;
import wendell.user.User;

import com.googlecode.objectify.Key;

public class CreateNewNoteService implements WebService {

	@Override
	public void action(RequestParameterEditing editing, HttpServletResponse resp)
			throws IOException {
		TodoService todoService = WebServiceHandler.getTodoService();

		Key<Project> project = editing.getProject() == null ? null : Key
				.create(Project.class, editing.getProject().getId());

		Todo todo = new Todo(Key.create(User.class, editing.getLoginUser()
				.getId()), editing.getTitle(), editing.getNote(), null, null,
				Key.create(Directory.class, editing.getDirectory().getId()),
				false, false);
		todo.assignProject(project);

		todoService.createTodo(todo);
	}

}
