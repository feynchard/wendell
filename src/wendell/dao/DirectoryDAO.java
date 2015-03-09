package wendell.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import wendell.Directory;
import wendell.user.User;
import wendell.util.DAOUtils;

public class DirectoryDAO {

	public Directory save(Directory directory) {
		ofy().save().entity(directory).now();
		return directory;
	}

	public Directory findByUserAndName(User user, String name) {
		return (Directory) DAOUtils.uniqueResult(ofy().load()
				.type(Directory.class).ancestor(user).filter("name", name)
				.list());
	}

	public Boolean isRepeatDirectory(User user, String temp) {

		Directory reloadedDirectory = findByUserAndName(user, temp);

		return reloadedDirectory != null;
	}

	public List<Directory> findAllByUser(User user) {
		return ofy().load().type(Directory.class).ancestor(user).list();
	}

	public List<Directory> findByUserAndSpecial(User user, Boolean special) {
		return ofy().load().type(Directory.class).ancestor(user)
				.filter("special", special).list();
	}

	public Directory findByUserAndKey(User user, Key<Directory> directoryKey) {
		return (Directory) DAOUtils.uniqueResult(ofy()
				.load()
				.ancestor(user)
				.filterKey(
						"=",
						Key.create(Key.create(user), Directory.class,
								directoryKey.getId())).list());
	}
}
