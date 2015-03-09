package wendell.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

import wendell.Directory;
import wendell.Project;
import wendell.Todo;
import wendell.user.User;
import wendell.user.dao.UserDAO;
import wendell.util.DAOUtils;

import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

public class TodoDAO {

	private static UserDAO userDAO;

	public TodoDAO() {
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
	}

	public Todo save(Todo todo) {
		ofy().save().entity(todo).now();
		return todo;
	}

	public List<Todo> findTodoByUserAndDirectoryAndProjectAndCompleteAndTodo(
			User user, Directory directory, Project project, Boolean complete,
			Boolean todo) {
		Query<Todo> q = ofy().load().type(Todo.class).ancestor(user);
		if (project == null) {
			q = q.filter("directory",
					Key.create(Directory.class, directory.getId()));

		} else {
			Filter directoryFilter = new FilterPredicate("directory",
					FilterOperator.EQUAL, Key.create(Directory.class,
							directory.getId()).getRaw());

			Filter projectFilter = new FilterPredicate("project",
					FilterOperator.EQUAL, Key.create(Project.class,
							project.getId()).getRaw());

			Filter equalFilter = CompositeFilterOperator.and(directoryFilter,
					projectFilter);
			q = q.filter(equalFilter);
		}

		if (complete != null) {
			Filter todoFilter = new FilterPredicate("todo",
					FilterOperator.EQUAL, todo);

			Filter completeFilter = new FilterPredicate("complete",
					FilterOperator.EQUAL, complete);

			Filter equalFilter = CompositeFilterOperator.and(todoFilter,
					completeFilter);
			q = q.filter(equalFilter);
			q = q.filter("complete", complete);
		} else {
			q = q.filter("todo", todo);
		}

		return q.orderKey(true).list();
	}

	public List<Todo> findByUserAndStartDate(User user, Date startDateFrom,
			Date startDateTo) {

		Filter startDateMinFilter = new FilterPredicate("startDate",
				FilterOperator.GREATER_THAN_OR_EQUAL, startDateFrom);

		Filter startDateMaxFilter = new FilterPredicate("startDate",
				FilterOperator.LESS_THAN_OR_EQUAL, startDateTo);

		Filter dateRangeFilter = CompositeFilterOperator.and(
				startDateMinFilter, startDateMaxFilter);

		return ofy().load().type(Todo.class).ancestor(user)
				.filter(dateRangeFilter).list();
	}

	public List<Todo> findByUserAndEndDate(User user, Date endDateFrom,
			Date endDateTo) {

		Filter endDateMinFilter = new FilterPredicate("endDate",
				FilterOperator.GREATER_THAN_OR_EQUAL, endDateFrom);

		Filter endDateMaxFilter = new FilterPredicate("endDate",
				FilterOperator.LESS_THAN_OR_EQUAL, endDateTo);

		Filter dateRangeFilter = CompositeFilterOperator.and(endDateMinFilter,
				endDateMaxFilter);

		return ofy().load().type(Todo.class).ancestor(user)
				.filter(dateRangeFilter).list();
	}

	public Todo findById(User user, Long id) {
		return (Todo) DAOUtils.uniqueResult(ofy().load().ancestor(user)
				.filterKey("=", Key.create(Key.create(user), Todo.class, id))
				.list());
	}

	public void delete(Todo todo) {
		ofy().delete().entity(todo).now();
	}
}
