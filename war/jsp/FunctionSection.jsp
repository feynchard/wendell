<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="functionSection">
		<div class="form-group">
			<strong class="label label-primary" id="userName"> Hi,
				{{userName}} </strong> <a id="logout" href="/Logout.do"
				class="btn btn-default">Logout</a> <br>
		</div>
		<div class="form-group">
			<label for="todoMode" class="radio-inline"><input
				type="radio" id="todoMode" name="mode" class="checkBox"
				value="todoMode" ng-model="todoMode" ng-change="changeTodoMode()">Todo
				Mode</label><label class="radio-inline" for="noteMode"><input
				type="radio" id="noteMode" class="checkBox" name="mode"
				value="noteMode" ng-model="todoMode" ng-change="changeTodoMode()">Note
				Mode</label>
		</div>
		<div>
			<button id="createProjectBtn" type="button" class="btn btn-default">Create
				Project</button>
		</div>
		<div class="form-group input-group">
			<select id="project" name="project"
				ng-options="project as project.name for project in projects"
				ng-model="project" ng-change="changeProject(project)"
				class="form-control">
				<option value="">choose project</option>
			</select><span id="modifyProjectBtn" class="input-group-addon"
				ng-click="popupModifyProjectPanel()"><span
				class="glyphicon glyphicon-pencil"></span> </span>
		</div>
	</div>
</body>
</html>