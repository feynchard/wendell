<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div>
		<ul class="list-group" id="draggable">
			<li ng-repeat="todo in activeTodoes | filter:dir"
				ng-click="selectTodo(todo)"
				ng-class="{active:selectedTodo === todo}"
				ng-dblclick="editTodo(todo)" class="list-group-item"><span
				ng-click="deleteTodo(todo)" class="badge"><span
					class="glyphicon glyphicon-trash"></span></span> <input type="checkbox"
				class="checkBox" ng-show="inTodoMode()" ng-click="removeTodo(todo)"><span
				class="todo">{{todo.title}}</span></li>
		</ul>
		<hr ng-show="inTodoMode()">
		<ul class="list-group">
			<li ng-repeat="todo in completeTodoes | filter:dir"
				ng-dblclick="editTodo(todo)" class="list-group-item disabled">
				<div>
					<input type="checkbox" checked ng-show="inTodoMode()"
						ng-click="recoverTodo(todo)">
					<del>{{todo.title}}</del>
				</div>
			</li>
		</ul>
	</div>
</body>
</html>