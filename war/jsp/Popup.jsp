<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div hidden>
		<div id="createDirForm" class="popup">
			<form ng-submit="createNewDir()">
				<div class="form-group">
					Input Directory Name: <input type="text" class="form-control"
						ng-model="tempName" required>
				</div>
				<div>
					<button class="btn btn-default" type="submit">Create</button>
					<button ng-click="closePopup()" class="btn btn-default"
						type="reset">Cancel</button>
				</div>
			</form>
		</div>

		<div id="modifyDirForm" class="popup">
			<form ng-submit="modifyDir()">
				<div class="form-group">
					Input Directory Name: <input type="text" class="form-control"
						ng-model="tempName" required>
				</div>
				<div>
					<button class="btn btn-default" type="submit">Modify</button>
					<button ng-click="closePopup()" class="btn btn-default"
						type="reset">Cancel</button>
				</div>
			</form>
		</div>

		<div id="createProjectForm" class="popup">
			<form ng-submit="createNewProject()">
				<div class="form-group">
					Input Project Name: <input type="text" class="form-control"
						ng-model="tempName" required>
				</div>
				<div>
					<button class="btn btn-default" type="submit">Create</button>
					<button ng-click="closePopup()" class="btn btn-default"
						type="reset">Cancel</button>
				</div>
			</form>
		</div>

		<div id="modifyProjectForm" class="popup">
			<form ng-submit="modifyProject()">
				<div class="form-group">
					Input Project Name: <input type="text" class="form-control"
						ng-model="tempName" required>
				</div>
				<div>
					<button class="btn btn-default" type="submit">Modify</button>
					<button ng-click="closePopup()" class="btn btn-default"
						type="reset">Cancel</button>
				</div>
			</form>
		</div>

		<div id="confirmPanel" class="popup">
			<div id="confirmMassage" class="alert alert-warning">你確定嗎？</div>
			<div id="confirmBtnDiv">
				<button ng-click="sayYes()" class="btn btn-default" type="button">確定</button>
				<button ng-click="sayNo()" class="btn btn-default" type="button">取消</button>
			</div>
		</div>

		<div id="warningMessageDiv"
			class="alert alert-warning alert-dismissible popup" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close" onclick="$.magnificPopup.close();">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Warning!</strong>
			<p id="warningMessage">Better check yourself, you're not looking
				too good.</p>
		</div>

	</div>
</body>
</html>