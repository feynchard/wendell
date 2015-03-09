package wendell.user;

import com.googlecode.objectify.Key;

public interface UserService {

	public User createUser(String name, String email, String password) throws UserException;

	public User queryUserByEmail(String email);

	public User queryByKey(Key<User> userKey);
	
}
