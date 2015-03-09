package wendell;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.googlecode.objectify.Key;

import wendell.user.User;
import wendell.web.service.RequestParameterEditing;

public interface TodoService {

	public List<Todo> queryTodoByUserAndDirectoryAndProjectAndComplete(
			User user, Directory directory, Project project, Boolean complete);

	public List<Todo> queryNoteByUserAndDirectory(User user,
			Directory directory, Project project);

	public void initUserProfile(User user);

	public List<Directory> queryAllDirectory(User user);

	public List<Directory> querySpecialDirectoryByUser(User user);

	public List<Directory> queryNormalDirectoryByUser(User user);

	public Directory queryDirectoryByUserAndName(User user, String name);

	public Todo createTodo(Todo todo);

	public Project queryProjectByUserAndName(User user, String projectString);

	public Todo queryTodoById(User user, Long id);

	public void saveTodo(Todo todo);

	public Directory queryDirectoryByUserAndDirectoryKey(User user,
			Key<Directory> directoryKey);

	public void deleteTodo(Todo todo);

	public int getLastNormalDirectoryOrder(User loginUser);

	public Directory createDirectory(User user, Directory directory)
			throws TodoException;

	public Set<Todo> queryTodoEventByUserAndStartDateAndEndDate(User loginUser,
			Date startDate, Date endDate);

	public List<Project> queryProjectByUser(User loginUser);

	public Project createNewProjejt(User loginUser, Project project)
			throws TodoException;

	public void modifyTodo(RequestParameterEditing editing, Todo todo)
			throws TodoException;

	public Project queryProjectByUserAndProjectKey(User user,
			Key<Project> projectKey);

	public void modifyDirectory(User user, Directory directory, String name)
			throws TodoException;

	public void modifyProject(User user, Project project, String name)
			throws TodoException;

}
