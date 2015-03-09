package wendell.web.service;

public enum ActionCommand {

	// ---------------- user ----------------
	QUERY_USER_NAME("getUserName"),

	// --------------- directory ------------------------
	QUERY_SPECIAL_DIRECTORY("getSpecialDirectories"),

	QUERY_NORMAL_DIRECTORY("getNormalDirectories"),

	CREATE_NEW_DIRECTORY("createNewDir"),

	MODIFY_DIRECTORY("modifyDirectory"),

	// ------------- todo -----------------------------
	QUERY_ACTIVE_TODO("getActiveTodoes"),

	QUERY_COMPLETE_TODO("getCompleteTodoes"),

	CREATE_NEW_TODO("createNewTodo"),

	MODIFY_TODO("modifyTodo"),

	REMOVE_TODO("removeTodo"),

	RECOVER_TODO("recoverTodo"),

	DELETE_TODO("deleteTodo"),

	QUERY_TODO_EVENT("queryTodoEvent"),

	// ----------------- note -------------------
	QUERY_NOTE("getNotes"),

	CREATE_NEW_NOTE("createNewNote"),

	// --------------- project --------------------
	QUREY_PROJECT("getProjects"),

	CREATE_NEW_PROJECT("createNewProject"),

	MODIFY_PROJECT("modifyProject");

	private String action;

	private ActionCommand(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public static ActionCommand findActionCommand(String action) {
		for (ActionCommand actionCommand : ActionCommand.values()) {
			if (action.equals(actionCommand.getAction())) {
				return actionCommand;
			}
		}

		return null;
	}

}
