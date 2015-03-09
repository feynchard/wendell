<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="dirs">
		<ul class="list-group">
			<li ng-repeat="dir in sdirs" ng-click="dirOnClick(dir)"
				ng-class="{active:selectedDir == dir}" class="list-group-item">{{dir.name}}</li>
		</ul>
		<ul id="sortable" class="list-group">
			<li ng-repeat="dir in ndirs" ng-click="dirOnClick(dir)"
				ng-class="{active:selectedDir == dir}" class="list-group-item">
				<span id="modifyDirBtn" ng-click="popupModifyDirPanel(dir)"
				class="badge"><span class="glyphicon glyphicon-pencil"></span></span>
				{{dir.name}}
			</li>
		</ul>
		<div>
			<button id="createDirBtn" class="btn btn-default">
				<span class="glyphicon glyphicon-plus"></span>
			</button>
		</div>
	</div>
</body>
</html>