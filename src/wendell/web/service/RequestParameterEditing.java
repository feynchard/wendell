package wendell.web.service;

import java.io.Serializable;
import java.util.Date;

import wendell.Directory;
import wendell.Project;
import wendell.user.User;

public class RequestParameterEditing implements Serializable {

	private User loginUser;

	private String action;

	private Long id;

	private String title;

	private String note;

	private Directory directory;

	private Project project;

	private Date startDate;

	private Date endDate;

	private Boolean todo;

	private Boolean complete;

	private String temp;

	public RequestParameterEditing(User loginUser, String action, Long id,
			String title, String note, Directory directory, Project project,
			Date startDate, Date endDate, Boolean todo, Boolean complete,
			String temp) {
		this.loginUser = loginUser;
		this.action = action;
		this.id = id;
		this.title = title;
		this.note = note;
		this.directory = directory;
		this.project = project;
		this.startDate = startDate;
		this.endDate = endDate;
		this.todo = todo;
		this.complete = complete;
		this.temp = temp;
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getTodo() {
		return todo;
	}

	public void setTodo(Boolean todo) {
		this.todo = todo;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

}
