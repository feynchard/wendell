var lifeNoteApp = angular.module('lifeNoteApp', {});

lifeNoteApp.directive('ngEnter', function() {
	return function(scope, element, attrs) {
		element.bind("keydown keypress", function(event) {
			if (event.which === 13) {
				scope.$apply(function() {
					scope.$eval(attrs.ngEnter);
				});

				event.preventDefault();
			}
		});
	};
});

lifeNoteApp.factory('moment', function momentFactory() {
	return moment;
});

lifeNoteApp.directive('psDatetimePicker', function(moment) {
	return {
		restrict : 'A',
		require : 'ngModel',
		link : function(scope, element, attributes, ctrl) {
			var d = $(element).parent('.date')
			d.datetimepicker({
				format : 'YYYY-MM-DD HH:mm',
				sideBySide : true
			});
			var picker = d.data("DateTimePicker");

			element.on('blur', function(event) {
				scope.$apply(function() {
					var date = picker.date();
					ctrl.$setViewValue(date.format('YYYY-MM-DD HH:mm'));
				});
			});
		}
	};
});

lifeNoteApp.config(function($httpProvider) {
	$httpProvider.defaults.transformRequest = function(data) {
		if (data === undefined) {
			return data;
		}
		return $.param(data);
	}
});

lifeNoteApp.controller('LifeNoteCtrl', function($scope, $http) {
	// ----------------- watch area ----------------------
	$scope.$watch(function($scope) {
		return $scope.selectedDir;
	}, function(newValue, oldValue) {
		$scope.todoDir = newValue;
		$scope.refreshTodoesOrNotes();
	});

	$scope.$watch(function($scope) {
		return $scope.selectedProject;
	}, function(newValue, oldValue) {
		$scope.todoProject = newValue;
		$scope.refreshTodoesOrNotes();
	});

	// ---------------- function area ------------------
	$scope.servicePost = function($http, actionParam, dataParam,
			successCallback) {
		dataParam.action = actionParam;
		$http({
			method : 'post',
			url : 'Service.do',
			data : dataParam,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(successCallback);
	}

	$scope.changeTodoToAnotherDir = function(todoId, dirName) {
		$scope.servicePost($http, 'changeDirectory', {
			id : todoId,
			dir : dirName
		}, function(data) {
			$scope.refreshTodoesOrNotes();
		});
	}

	$scope.inTodoMode = function() {
		return $scope.todoMode === 'todoMode';
	};

	$scope.updateLocalTodoData = function(todo) {
		$scope.todoId = todo.id;
		$scope.title = todo.title;
		$scope.todoContent = todo.note;
		$scope.startDate = todo.startDate;
		$scope.endDate = todo.endDate;
		$scope.todoProject = todo.project;
		$scope.todoDir = todo.dir;
		$scope.todo = todo.todo;
		$scope.complete = todo.complete;
	}

	$scope.resetLocalTodoData = function() {
		$scope.todoId = "";
		$scope.title = "";
		$scope.todoContent = "";
		$scope.startDate = "";
		$scope.endDate = "";
		$scope.todoProject = "";
		$scope.todo = "";
		$scope.complete = "";
	}

	// query todoes
	$scope.getQueryTodoInfo = function() {
		var info = {
			dir : this.selectedDir == null ? 'Input Box'
					: (this.selectedDir.name || 'Input Box'),
			project : this.selectedProject == null ? ''
					: (this.selectedProject.name || '')
		};
		return info;
	}

	// create, modify todo | create, modify note
	$scope.getTodoData = function() {
		return {
			id : this.todoId,
			title : this.title,
			note : this.todoContent,
			dir : this.todoDir == null ? 'Input Box'
					: (this.todoDir.name || 'Input Box'),
			project : this.todoProject == null ? ''
					: (this.todoProject.name || ''),
			startDate : this.startDate,
			endDate : this.endDate,
			todo : this.inTodoMode(),
			complete : this.selectedTodo.complete
		};
	};

	$scope.refreshDirs = function() {
		$scope.servicePost($http, 'getSpecialDirectories', $scope
				.getQueryTodoInfo(), function(data) {
			$scope.sdirs = data.sort(function(a, b) {
				return a.order - b.order;
			});
		});

		$scope.servicePost($http, 'getNormalDirectories', $scope
				.getQueryTodoInfo(), function(data) {
			$scope.ndirs = data.sort(function(a, b) {
				return a.order - b.order;
			});
		});
	}

	$scope.refreshTodoes = function() {
		$scope.servicePost($http, 'getActiveTodoes', $scope.getQueryTodoInfo(),
				function(data) {
					$scope.activeTodoes = data.sort(function(a, b) {
						return b.id - a.id;
					});
				});
		$scope.servicePost($http, 'getCompleteTodoes', $scope
				.getQueryTodoInfo(), function(data) {
			$scope.completeTodoes = data.sort(function(a, b) {
				return b.id - a.id;
			});
		});
	}

	$scope.refreshNotes = function() {
		$scope.servicePost($http, 'getNotes', $scope.getQueryTodoInfo(),
				function(data) {
					$scope.activeTodoes = data.sort(function(a, b) {
						return b.id - a.id;
					});
					$scope.completeTodoes = null;
				});
	}

	$scope.refreshTodoesOrNotes = function() {
		if ($scope.inTodoMode()) {
			$scope.refreshTodoes();
		} else {
			$scope.refreshNotes();
		}
	}

	$scope.refreshProject = function() {
		$scope.servicePost($http, 'getProjects', $scope.getQueryTodoInfo(),
				function(data) {
					$scope.projects = data.sort(function(a, b) {
						return a.id - b.id;
					});
				});
	}

	$scope.closePopup = function() {
		$.magnificPopup.close();
		$scope.tempName = '';
	}

	$scope.alertError = function(data) {
		if ((typeof data) === 'string' && data !== '') {
			$('#warningMessage').replaceWith(data);
			var warningMessage = $("#warningMessageDiv");
			$.magnificPopup.open({
				items : {
					src : warningMessage,
					type : 'inline'
				}
			});
			return true;
		}

		return false;
	}

	$scope.sayYes = function() {
		$scope.closePopup();
		$scope.confirmCallback();
		$scope.confirmCallback = null;
	}

	$scope.sayNo = function() {
		$scope.closePopup();
		$scope.confirmCallback = null;
	}

	$scope.confirm = function(message, callback) {
		$('#confirmMassage').html(message);
		$.magnificPopup.open({
			items : {
				type : 'inline',
				src : '#confirmPanel'
			}
		});

		$scope.confirmCallback = callback;
	}

	// -------- data initial area -----------------------
	$scope.initMain = function() {
		$scope.selectedDir = {
			id : 0,
			name : 'Input Box',
			order : 0
		};
		$scope.todoDir = {
			id : 0,
			name : 'Input Box',
			order : 0
		};
		$scope.todoProject = {
			id : 0,
			name : ''
		}
		$scope.selectedProject = {
			id : 0,
			name : ''
		}
		$scope.selectedTodo = {
			id : 0,
			title : '',
			note : '',
			dir : '',
			project : '',
			startDate : '',
			endDate : '',
			todo : true,
			complete : false
		}
		$scope.editMode = false;
		$scope.todoMode = 'todoMode';
		$scope.noteEditorShown = false;
	}

	$scope.servicePost($http, 'getUserName', {}, function(data) {
		$scope.userName = data
	});

	$scope.refreshDirs();

	$scope.refreshTodoes();

	$scope.refreshProject();

	// ------------- event handle area -----------------

	// ---------------------- dir --------------------
	$scope.dirOnClick = function(dir) {
		$scope.selectedDir = dir;
	}

	$scope.createNewDir = function() {
		$scope.servicePost($http, 'createNewDir', {
			temp : $scope.tempName
		}, function(data) {
			$scope.tempName = '';

			if (!$scope.alertError(data)) {
				$scope.refreshDirs();
				$scope.closePopup();
			}

		});
	}

	$scope.popupModifyDirPanel = function(dir) {
		$scope.tempName = dir.name;
		$.magnificPopup.open({
			items : {
				type : 'inline',
				src : '#modifyDirForm'
			}
		});
	}

	$scope.modifyDir = function() {
		$scope.servicePost($http, 'modifyDirectory', {
			dir : $scope.selectedDir.name,
			temp : $scope.tempName
		}, function(data) {
			$scope.tempName = '';

			if (!$scope.alertError(data)) {
				$scope.refreshDirs();
				$scope.selectedDir = findByName($scope.ndirs, $scope.tempName);
				$scope.closePopup();
			}

		});
	}

	// ------------ todo -------------------------
	$scope.createNewTodo = function() {
		$scope.servicePost($http, 'createNewTodo', $scope.getTodoData(),
				function(data) {
					$scope.entry = '';
					$scope.refreshTodoes();
				});
	}

	$scope.entryOnEnter = function() {
		$scope.title = $scope.entry;
		$scope.createNewTodo();
	}

	$scope.modifyTodo = function() {
		$scope.todoContent = editor.getData();
		$scope.servicePost($http, 'modifyTodo', $scope.getTodoData(), function(
				data) {
			if (!$scope.alertError(data)) {
				$scope.refreshTodoesOrNotes();
				$scope.editMode = !$scope.editMode;
				toggleEditor();
			}
		});
	}

	$scope.removeTodo = function(todo) {
		$scope.updateLocalTodoData(todo);
		$scope.servicePost($http, 'removeTodo', $scope.getTodoData(), function(
				data) {
			$scope.refreshTodoes();
		});
	}

	$scope.recoverTodo = function(todo) {
		$scope.updateLocalTodoData(todo);
		$scope.servicePost($http, 'recoverTodo', $scope.getTodoData(),
				function(data) {
					$scope.refreshTodoes();
				});
	}

	$scope.deleteTodo = function(todo) {
		$scope.confirm("你確定要刪除此" + ($scope.inTodoMode() ? "待辦事項" : "筆記") + "？",
				function() {
					$scope.updateLocalTodoData(todo);
					$scope.servicePost($http, 'deleteTodo', $scope
							.getTodoData(), function(data) {
						$scope.refreshTodoes();
					});
				});
	}

	$scope.selectTodo = function(todo) {
		$scope.selectedTodo = todo;
		$scope.updateLocalTodoData(todo);
		if ($scope.editMode) {
			removeEditor(editor);
			editor = CKEDITOR.replace('ckeditor', {
				allowedContent : true
			});
			$scope.prepareDataForEdit(todo);
		}
	}

	$scope.editTodo = function(todo) {
		$scope.editMode = !$scope.editMode;
		if ($scope.editMode) {
			removeEditor(editor);
			editor = CKEDITOR.replace('ckeditor', {
				allowedContent : true
			});
			$scope.prepareDataForEdit(todo);
		}
		toggleEditor();
	}

	$scope.prepareDataForEdit = function(todo) {
		$scope.todoDir = findByName($scope.ndirs, todo.directory.name);
		$scope.todoProject = findByName($scope.projects, todo.project.name);
		editor.setData(todo.note);
	}

	// --------------------- note --------------------------
	$scope.createNewNote = function() {
		$scope.todoContent = noteEditor.getData();
		$scope.servicePost($http, 'createNewNote', $scope.getTodoData(),
				function(data) {
					$scope.refreshTodoesOrNotes();
					$scope.resetLocalTodoData();
					noteEditor.setData('');
					$scope.hideNoteEditor();
				});
	}

	$scope.showNoteEditor = function() {
		$scope.resetLocalTodoData();
		noteEditor = CKEDITOR.replace('noteContentEditor', {
			allowedContent : true
		});
		$scope.noteEditorShown = true;
	}

	$scope.hideNoteEditor = function() {
		$scope.noteEditorShown = false;
		removeEditor(noteEditor);
	}

	// ------------------ function -----------------------
	$scope.changeTodoMode = function() {
		if ($scope.inTodoMode()) {
			$scope.refreshTodoes();
		} else {
			$scope.refreshNotes();
		}
	}

	// ------------- project ----------------------
	$scope.changeProject = function(project) {
		$scope.selectedProject = project;
	}

	$scope.createNewProject = function() {
		$scope.servicePost($http, 'createNewProject', {
			temp : $scope.tempName
		}, function(data) {
			$scope.tempName = "";

			if (!$scope.alertError(data)) {
				$scope.refreshProject();
				$scope.closePopup();
			}

		});
	}

	$scope.popupModifyProjectPanel = function() {
		if ($scope.selectedProject.name == false) {
			$scope.alertError("無法修改此計劃。")
			return;
		}
		$scope.tempName = $scope.selectedProject.name;
		$.magnificPopup.open({
			items : {
				type : 'inline',
				src : '#modifyProjectForm'
			}
		});
	}

	$scope.modifyProject = function() {

		$scope.servicePost($http, 'modifyProject', {
			project : $scope.selectedProject.name,
			temp : $scope.tempName
		}, function(data) {
			$scope.tempName = "";

			if (!$scope.alertError(data)) {
				$scope.refreshProject();
				$scope.selectedProject = findByName($scope.projects,
						$scope.tempName);
				$scope.closePopup();
			}

		});
	}
});
