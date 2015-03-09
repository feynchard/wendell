package wendell.util;

import org.json.simple.JSONObject;

import wendell.Directory;
import wendell.Project;
import wendell.user.User;
import wendell.web.service.WebServiceHandler;

import com.googlecode.objectify.Key;

public class JsonUtils {

	@SuppressWarnings("unchecked")
	public static JSONObject getDirectoryJsonObject(Key<User> userKey,
			Key<Directory> directoryKey) {
		User user = WebServiceHandler.getUserService().queryByKey(userKey);
		Directory directory = WebServiceHandler.getTodoService()
				.queryDirectoryByUserAndDirectoryKey(user, directoryKey);
		JSONObject object = new JSONObject();
		object.put("id", directory.getId());
		object.put("name", directory.getName());
		object.put("order", directory.getOrder());

		return object;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject getProjectJsonObject(Key<User> userKey,
			Key<Project> projectKey) {
		User user = WebServiceHandler.getUserService().queryByKey(userKey);
		Project project = WebServiceHandler.getTodoService()
				.queryProjectByUserAndProjectKey(user, projectKey);
		JSONObject object = new JSONObject();
		object.put("id", project.getId());
		object.put("name", project.getName());

		return object;
	}
}
