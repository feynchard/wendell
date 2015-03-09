package wendell.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import wendell.Project;
import wendell.user.User;
import wendell.util.DAOUtils;

import com.googlecode.objectify.Key;

public class ProjectDAO {
	public Project save(Project project) {
		ofy().save().entity(project).now();
		return project;
	}

	public Project findByName(User user, String name) {
		return (Project) DAOUtils.uniqueResult(ofy().load().type(Project.class)
				.ancestor(user).filter("name", name).list());
	}

	public Boolean isRepeatProject(User user, String name) {

		Project reloadedProject = findByName(user, name);

		return reloadedProject != null;
	}

	public List<Project> findAllByUser(User user) {
		return ofy().load().type(Project.class).ancestor(user).list();
	}

	public Project findByUserAndProjectKey(User user, Key<Project> projectKey) {
		return (Project) DAOUtils.uniqueResult(ofy()
				.load()
				.ancestor(user)
				.filterKey(
						"=",
						Key.create(Key.create(user), Project.class,
								projectKey.getId())).list());
	}
}
