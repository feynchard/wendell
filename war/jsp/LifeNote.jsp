<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" ng-app="lifeNoteApp">
<head>
<meta charset="utf8">
<title>Life Note</title>
<script type="text/javascript" src="js/angularJS/angular.js"></script>
<script type="text/javascript" src="js/jquery/jquery-2.1.3.js"></script>
<link rel="stylesheet" href="stylesheet/jquery-ui/jquery-ui.theme.css">
<script src="js/jquery-ui/jquery-ui.js"></script>
<script src="js/magnific-popup/jquery.magnific-popup.js"></script>
<link rel="stylesheet"
	href="stylesheet/magnific-popup/magnific-popup.css">
<script src="js/moment/moment.min.js"></script>
<script src="js/fullcalendar/fullcalendar.js"></script>
<link rel="stylesheet" href="stylesheet/fullcalendar/fullcalendar.css">
<link rel="stylesheet" href="stylesheet/bootstrap/bootstrap.css">
<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
<script src="js/bootstrap/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet"
	href="stylesheet/bootstrap/bootstrap-datetimepicker.css">
<script src="js/ckeditor/ckeditor.js"></script>
<script src="js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript" src="js/manipulation.js"></script>
<script type="text/javascript" src="js/controllers.js"></script>
<link rel="stylesheet" type="text/css" href="stylesheet/main.css">
<link rel="stylesheet" type="text/css" href="stylesheet/popup.css">
</head>
<body ng-controller="LifeNoteCtrl" class="container"
	ng-init="initMain()">
	<%@include file="/jsp/Popup.jsp"%>
	<div class="row">
		<div id="navBar" class="col-md-3">
			<%@include file="/jsp/FunctionSection.jsp"%>
			<%@include file="/jsp/DirectoryPanel.jsp"%>
		</div>
		<div id="content" class="col-md-9">
			<div class="row">
				<div class="col-md-10" id="todoes">
					<%@include file="/jsp/Entry.jsp"%>
					<%@include file="/jsp/TodoPanel.jsp"%>
				</div>
				<div id="sidebar" class="col-md-2">
					<%@include file="/jsp/Calendar.jsp"%>
					<%@include file="/jsp/Editor.jsp"%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>