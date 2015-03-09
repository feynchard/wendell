<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="form-group">
		<div ng-show="inTodoMode()">
			<input id="entry" ng-model="entry" ng-enter="entryOnEnter()"
				class="form-control">
		</div>
		<div ng-hide="inTodoMode()">
			<div class="form-group">
				<button type="button" class="btn btn-success"
					ng-click="showNoteEditor()">Create Note</button>
			</div>
			<form ng-show="noteEditorShown" ng-submit="createNewNote()">
				<div class="form-group">
					<label for="noteTile">Title:</label> <input type="text"
						class="form-control" ng-model="title" required>
				</div>
				<div class="form-group">
					<label>Content:</label>
					<textarea id="noteContentEditor" ng-model="noteContent" rows="10"
						cols="15"></textarea>
				</div>
				<button type="submit" class="btn btn-default">Create</button>
				<button type="reset" class="btn btn-default"
					ng-click="hideNoteEditor()">Cancel</button>
			</form>
			<hr>
		</div>
	</div>
</body>
</html>