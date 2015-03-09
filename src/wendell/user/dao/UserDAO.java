package wendell.user.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;

import wendell.user.User;
import wendell.util.DAOUtils;

public class UserDAO {

	public User save(User user) {
		ofy().save().entity(user).now();
		return user;
	}

	public Boolean isRepeatUser(User user) {

		User reloadedUser = findByEmail(user.getEmail());

		return reloadedUser != null;
	}

	public User findByEmail(String email) {

		return (User) DAOUtils.uniqueResult(ofy().load().type(User.class)
				.filter("email", email).list());
	}

	public User findByKey(Key<User> userKey) {
		return (User) DAOUtils.uniqueResult(ofy().load()
				.filterKey("=", userKey).list());
	}

}
