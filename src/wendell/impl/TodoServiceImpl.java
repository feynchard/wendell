package wendell.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wendell.Directory;
import wendell.Project;
import wendell.Todo;
import wendell.TodoException;
import wendell.TodoService;
import wendell.dao.DirectoryDAO;
import wendell.dao.ProjectDAO;
import wendell.dao.TodoDAO;
import wendell.user.User;
import wendell.user.dao.UserDAO;
import wendell.web.service.RequestParameterEditing;

import com.googlecode.objectify.Key;

public class TodoServiceImpl implements TodoService {

	private static String RENAME_MESSAGE = "重複命名，請重新填寫。";

	private static TodoDAO todoDAO;

	private static DirectoryDAO directoryDAO;

	private static ProjectDAO projectDAO;

	private static UserDAO userDAO;

	public TodoServiceImpl() {
		if (todoDAO == null) {
			todoDAO = new TodoDAO();
		}
		if (directoryDAO == null) {
			directoryDAO = new DirectoryDAO();
		}
		if (projectDAO == null) {
			projectDAO = new ProjectDAO();
		}
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
	}

	@Override
	public List<Todo> queryTodoByUserAndDirectoryAndProjectAndComplete(
			User user, Directory directory, Project project, Boolean complete) {
		return todoDAO.findTodoByUserAndDirectoryAndProjectAndCompleteAndTodo(
				user, directory, project, complete, Boolean.TRUE);
	}

	@Override
	public void initUserProfile(final User user) {
		String directoryName = "Input Box";
		Directory directory = directoryDAO.findByUserAndName(user,
				directoryName);
		String projectName = "Next Action";
		Project project = projectDAO.findByName(user, projectName);
		if (directory == null) {
			directory = directoryDAO.save(new Directory(Key.create(User.class,
					user.getId()), directoryName, 0, Boolean.TRUE));
		}
		if (project == null) {
			projectDAO.save(new Project(Key.create(User.class, user.getId()),
					projectName));
		}
	}

	@Override
	public List<Directory> queryAllDirectory(User user) {
		return directoryDAO.findAllByUser(user);
	}

	@Override
	public List<Directory> querySpecialDirectoryByUser(User user) {
		return directoryDAO.findByUserAndSpecial(user, true);
	}

	@Override
	public List<Directory> queryNormalDirectoryByUser(User user) {
		return directoryDAO.findByUserAndSpecial(user, false);
	}

	@Override
	public Directory queryDirectoryByUserAndName(User user, String name) {
		return directoryDAO.findByUserAndName(user, name);
	}

	@Override
	public List<Todo> queryNoteByUserAndDirectory(User user,
			Directory directory, Project project) {
		return todoDAO.findTodoByUserAndDirectoryAndProjectAndCompleteAndTodo(
				user, directory, project, null, Boolean.FALSE);
	}

	@Override
	public Todo createTodo(Todo todo) {
		return todoDAO.save(todo);

	}

	@Override
	public Project queryProjectByUserAndName(User user, String projectString) {
		return projectDAO.findByName(user, projectString);
	}

	@Override
	public Todo queryTodoById(User user, Long id) {
		return todoDAO.findById(user, id);
	}

	@Override
	public void saveTodo(Todo todo) {
		todoDAO.save(todo);
	}

	@Override
	public Directory queryDirectoryByUserAndDirectoryKey(User user,
			Key<Directory> directoryKey) {
		return directoryDAO.findByUserAndKey(user, directoryKey);
	}

	@Override
	public void deleteTodo(Todo todo) {
		todoDAO.delete(todo);
	}

	@Override
	public int getLastNormalDirectoryOrder(User loginUser) {
		List<Directory> normalDirectories = directoryDAO.findByUserAndSpecial(
				loginUser, false);
		return normalDirectories.size();
	}

	@Override
	public Directory createDirectory(User user, Directory directory)
			throws TodoException {
		if (directoryDAO.isRepeatDirectory(user, directory.getName())) {
			throw new TodoException(RENAME_MESSAGE);
		}
		Directory ret = directoryDAO.save(directory);
		return ret;
	}

	@Override
	public Set<Todo> queryTodoEventByUserAndStartDateAndEndDate(User loginUser,
			Date startDate, Date endDate) {
		Set<Todo> rets = new HashSet<Todo>();
		rets.addAll(todoDAO.findByUserAndStartDate(loginUser, startDate,
				endDate));
		rets.addAll(todoDAO.findByUserAndEndDate(loginUser, startDate, endDate));
		return rets;
	}

	@Override
	public List<Project> queryProjectByUser(User loginUser) {
		List<Project> projects = projectDAO.findAllByUser(loginUser);
		return projects;
	}

	@Override
	public Project createNewProjejt(User loginUser, Project project)
			throws TodoException {
		if (projectDAO.isRepeatProject(loginUser, project.getName())) {
			throw new TodoException(RENAME_MESSAGE);
		}

		projectDAO.save(project);
		return project;
	}

	@Override
	public void modifyTodo(RequestParameterEditing editing, Todo todo)
			throws TodoException {
		Date startDate = editing.getStartDate();
		Date endDate = editing.getEndDate();
		if ((startDate == null ^ endDate == null)) {
			if (startDate.after(endDate)) {
				throw new TodoException("日期設定有誤，請重新填寫。");
			}
		}

		todo.modify(editing.getTitle(), editing.getNote(), startDate, endDate,
				editing.getDirectory(), editing.getProject());
		todoDAO.save(todo);

	}

	@Override
	public Project queryProjectByUserAndProjectKey(User user,
			Key<Project> projectKey) {
		return projectDAO.findByUserAndProjectKey(user, projectKey);
	}

	@Override
	public void modifyDirectory(User user, Directory directory, String temp)
			throws TodoException {
		if (directoryDAO.isRepeatDirectory(user, temp)) {
			throw new TodoException("已有相同名稱目錄。");
		}
		directory.assignName(temp);
		directoryDAO.save(directory);
	}

	@Override
	public void modifyProject(User user, Project project, String name)
			throws TodoException {
		if (projectDAO.isRepeatProject(user, name)) {
			throw new TodoException("已有相同名稱之計劃。");
		}
		project.assignName(name);
		projectDAO.save(project);
	}
}
