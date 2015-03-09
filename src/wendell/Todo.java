package wendell;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Date;
import java.util.LinkedHashMap;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import wendell.user.User;
import wendell.util.JsonUtils;
import wendell.util.SimpleDate;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Todo implements Serializable, JSONStreamAware {

	private static final long serialVersionUID = 1L;

	@Id
	@Index
	private Long id;

	@Parent
	@Index
	private Key<User> user;

	@Index
	private String title;

	@Index
	private String note;

	@Index
	private Date startDate;

	@Index
	private Date endDate;

	@Index
	private Key<Directory> directory;

	@Index
	private Key<Project> project;

	@Index
	private Boolean todo;

	@Index
	private Boolean complete;

	public Todo() {
	}

	public Todo(Key<User> user, String title, String note, Date startDate,
			Date endDate, Key<Directory> directory, Boolean todo,
			Boolean complete) {
		this.user = user;
		this.title = title;
		this.note = note;
		this.startDate = startDate;
		this.endDate = endDate;
		this.directory = directory;
		this.todo = todo;
		this.complete = complete;
	}

	public Todo(Key<User> userKey, String title, String note, Date startDate,
			Date endDate, Boolean isTodo) {
		this(userKey, title, note, startDate, endDate, null, isTodo, false);
	}

	public Todo(Key<User> userKey, String title, String note, Boolean isTodo) {
		this(userKey, title, note, null, null, isTodo);
	}

	public Todo(Key<User> userKey, String title, String note) {
		this(userKey, title, note, true);
	}

	public String getTitle() {
		return title;
	}

	void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	void setNote(String note) {
		this.note = note;
	}

	public Date getStartDate() {
		return startDate;
	}

	void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getTodo() {
		return todo;
	}

	void setTodo(Boolean todo) {
		this.todo = todo;
	}

	public Boolean isTodo() {
		return todo;
	}

	public Key<Directory> getDirectory() {
		return directory;
	}

	void setDirectory(Key<Directory> directory) {
		this.directory = directory;
	}

	public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	public Key<User> getUser() {
		return user;
	}

	void setUser(Key<User> user) {
		this.user = user;
	}

	public Key<Project> getProject() {
		return project;
	}

	void setProject(Key<Project> project) {
		this.project = project;
	}

	public Boolean getComplete() {
		return complete;
	}

	void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public void complete() {
		this.complete = true;
	}

	public Boolean isComplete() {
		return this.complete;
	}

	@Override
	public void writeJSONString(Writer writer) throws IOException {
		LinkedHashMap<String, Object> object = new LinkedHashMap<String, Object>();
		object.put("id", id);
		object.put("title", title);
		object.put("note", note);
		object.put("startDate", SimpleDate.toDateString(startDate));
		object.put("endDate", SimpleDate.toDateString(endDate));
		object.put("directory",
				JsonUtils.getDirectoryJsonObject(user, directory));
		object.put(
				"project",
				project == null ? "" : JsonUtils.getProjectJsonObject(user,
						project));
		object.put("todo", todo.toString());
		object.put("complete", complete.toString());

		JSONValue.writeJSONString(object, writer);
	}

	public void modify(String title, String note, Date startDate, Date endDate,
			Directory directory, Project project) {
		this.title = title;
		this.note = note;
		this.startDate = startDate;
		this.endDate = endDate;
		if (directory != null) {
			this.directory = Key.create(Directory.class, directory.getId());
		}
		if (project != null) {
			this.project = Key.create(Project.class, project.getId());
		}
	}

	public void recover() {
		this.complete = false;
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
		Todo other = (Todo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void assignProject(Key<Project> project) {
		this.project = project;
	}

}
