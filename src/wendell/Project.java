package wendell;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.LinkedHashMap;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import wendell.user.User;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Project implements Serializable, JSONStreamAware {

	private static final long serialVersionUID = 1L;

	@Id
	@Index
	private Long id;

	@Parent
	@Index
	private Key<User> user;

	@Index
	private String name;

	public Project() {
	}

	public Project(Key<User> user, String name) {
		this.user = user;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	@Override
	public void writeJSONString(Writer writer) throws IOException {
		LinkedHashMap<String, Object> object = new LinkedHashMap<String, Object>();
		object.put("id", id);
		object.put("name", name);

		JSONValue.writeJSONString(object, writer);
	}

	public void assignName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
