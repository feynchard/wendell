package wendell.user.impl;

import com.googlecode.objectify.Key;

import wendell.user.User;
import wendell.user.UserException;
import wendell.user.UserService;
import wendell.user.dao.UserDAO;

public class UserServiceImpl implements UserService {

	private static UserDAO userDAO;

	public UserServiceImpl() {
		if (userDAO == null) {
			userDAO = new UserDAO();
		}

	}

	@Override
	public User createUser(String name, String email, String password)
			throws UserException {
		User user = new User(name, email, password);

		if (userDAO.isRepeatUser(user)) {
			throw new UserException("");
		}

		userDAO.save(user);

		return user;
	}

	@Override
	public User queryUserByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	public User queryByKey(Key<User> userKey) {
		return userDAO.findByKey(userKey);
	}

}
