package wendell.web.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import wendell.Directory;
import wendell.Project;
import wendell.TodoService;
import wendell.impl.TodoServiceImpl;
import wendell.user.User;
import wendell.user.UserService;
import wendell.user.impl.UserServiceImpl;
import wendell.util.SimpleDate;

public class WebServiceHandler {

	private static TodoService todoService;

	private static UserService userService;

	static {
		todoService = getTodoService();
		userService = getUserService();
	}

	public static RequestParameterEditing getRequestParameterEditing(
			HttpServletRequest req) {
		User loginUser = (User) req.getSession().getAttribute("loginUser");
		String action = req.getParameter("action");
		String idString = req.getParameter("id");
		String title = req.getParameter("title");
		String note = req.getParameter("note");
		String dirString = req.getParameter("dir");
		String projectString = req.getParameter("project");
		String startDateString = req.getParameter("startDate");
		String endDateString = req.getParameter("endDate");
		String todoString = req.getParameter("todo");
		String completeString = req.getParameter("complete");
		String temp = req.getParameter("temp");

		Long id = StringUtils.trimToNull(idString) == null ? null : Long
				.valueOf(idString);
		Directory directory = StringUtils.trimToNull(dirString) == null ? null
				: todoService.queryDirectoryByUserAndName(loginUser, dirString);
		Project project = StringUtils.trimToNull(projectString) == null ? null
				: todoService.queryProjectByUserAndName(loginUser,
						projectString);
		Date startDate = SimpleDate.createDate(startDateString);
		Date endDate = SimpleDate.createDate(endDateString);
		Boolean todo = todoString == null ? null : Boolean.valueOf(todoString);
		Boolean complete = completeString == null ? null : Boolean
				.valueOf(completeString);

		return new RequestParameterEditing(loginUser, action, id, title, note,
				directory, project, startDate, endDate, todo, complete, temp);
	}

	public static WebService createWebService(ActionCommand actionCommand) {
		WebService webService = null;

		switch (actionCommand) {
		case QUERY_USER_NAME:
			webService = new QueryUserNameService();
			break;

		case QUERY_SPECIAL_DIRECTORY:
			webService = new QuerySpecialDirectoryService();
			break;

		case QUERY_NORMAL_DIRECTORY:
			webService = new QueryNormalDirectoryService();
			break;

		case QUERY_ACTIVE_TODO:
			webService = new QueryActiveTodoService();
			break;

		case QUERY_COMPLETE_TODO:
			webService = new QueryCompleteTodoService();
			break;

		case QUERY_NOTE:
			webService = new QueryNoteService();
			break;

		case CREATE_NEW_TODO:
			webService = new CreateTodoService();
			break;

		case MODIFY_TODO:
			webService = new ModifyTodoService();
			break;

		case REMOVE_TODO:
			webService = new RemoveTodoService();
			break;

		case RECOVER_TODO:
			webService = new RecoverTodoService();
			break;

		case DELETE_TODO:
			webService = new DeleteTodoService();
			break;

		case CREATE_NEW_DIRECTORY:
			webService = new CreateNewDirectoryService();
			break;

		case QUERY_TODO_EVENT:
			webService = new QueryTodoEventService();
			break;

		case QUREY_PROJECT:
			webService = new QueryProjectService();
			break;

		case CREATE_NEW_PROJECT:
			webService = new CreateProjectService();
			break;

		case CREATE_NEW_NOTE:
			webService = new CreateNewNoteService();
			break;

		case MODIFY_DIRECTORY:
			webService = new ModifyDirectoryService();
			break;

		case MODIFY_PROJECT:
			webService = new ModifyProjectService();
			break;

		default:
			webService = null;
			break;
		}

		return webService;
	}

	public static TodoService getTodoService() {
		if (todoService == null) {
			todoService = new TodoServiceImpl();
		}
		return todoService;
	}

	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}
}
