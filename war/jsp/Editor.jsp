<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="editor" ng-show="editMode">
		<form ng-submit="modifyTodo()">
			<div class="form-group">
				<select id="todoDir" name="dir" class="form-control"
					ng-options="dir as dir.name for dir in ndirs" ng-model="todoDir">
					<option value="">Choose Directory</option>
				</select>
			</div>
			<div class="form-group">
				Title: <input type="text" ng-model="title" class="form-control">
			</div>
			<div ng-show="inTodoMode()">
				<div class="form-group">
					Start:
					<div class='input-group date' id='datetimepickerstart'>
						<input type='text' class="form-control" ps-datetime-picker
							ng-model="startDate" /> <span class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span> </span>
					</div>
					End:
					<div class='input-group date' id='datetimepickerend'>
						<input type='text' class="form-control" ps-datetime-picker
							ng-model="endDate" /> <span class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span> </span>
					</div>
				</div>
			</div>
			<div class="form-group">
				Project: <select id="project" name="project" class="form-control"
					ng-options="project as project.name for project in projects"
					ng-model="todoProject">
					<option value="">choose project</option>
				</select>
			</div>
			<div class="form-group">
				Content:
				<textarea id="ckeditor" rows="10" cols="15" ng-model="note"></textarea>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-default">Save</button>
			</div>
			<!-- 			<button type="button" ng-show="inTodoMode()" class="btn btn-default" -->
			<!-- 				hidden>轉成待辦事項</button> -->
			<!-- 			<button type="button" ng-hide="inTodoMode()" class="btn btn-default" -->
			<!-- 				hidden>轉成筆記</button> -->
			<!-- 			<button type="reset" class="btn btn-default">Cancel</button> -->
		</form>
	</div>
</body>
</html>