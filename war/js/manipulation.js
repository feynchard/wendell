var editor, noteEditor;

$(function() {
	$("#sortable").sortable();
	$("#sortable").disableSelection();

	$('#calendar').fullCalendar({
		events : function(start, end, timezone, callback) {
			$.ajax({
				url : 'Service.do',
				type : 'post',
				data : {
					action : 'queryTodoEvent',
					startDate : start.unix(),
					endDate : end.unix()
				},
				success : function(todoes) {
					var events = [];
					$(todoes).each(function() {
						events.push({
							title : $(this).attr('title'),
							start : moment($(this).attr('startDate')),
							end : moment($(this).attr('endDate'))
						});

					});
					callback(events);
				}
			});

		},
		color : 'yellow',
		textColor : 'black',
		editable : false
	});
	$('#calendar').addClass('mfp-hide');

	$('#openCalendarBtn').magnificPopup({
		items : {
			type : 'inline',
			src : '#calendar'
		},

		callbacks : {
			open : function() {
				$('#calendar').fullCalendar('prev');
				$('#calendar').fullCalendar('next');
			}
		}
	});
	$('#createDirBtn').magnificPopup({
		items : {
			type : 'inline',
			src : '#createDirForm'
		}
	});
	$('#createProjectBtn').magnificPopup({
		items : {
			type : 'inline',
			src : '#createProjectForm'
		}
	});
});

var editorShown = false;
function toggleEditor() {
	if (editorShown) {
		$('#todoes').switchClass("col-md-4", "col-md-10", 1000);
		$('#sidebar').switchClass("col-md-8", "col-md-2");
	} else {
		$('#todoes').switchClass("col-md-10", "col-md-4");
		$('#sidebar').switchClass("col-md-2", "col-md-8");
	}
	editorShown = !editorShown;
}

function createEditor(editor, id, html) {
	if (editor)
		return;

	var config = {
		allowedContent : true
	};
	editor = CKEDITOR.appendTo(id, config, html);
}

function removeEditor(editor) {
	if (!editor)
		return;

	// Destroy the editor.
	editor.destroy();
	editor = null;
}

function findByName(array, name) {
	for (var i = 0; i < array.length; i++) {
		if (array[i].name === name) {
			return array[i];
		}
	}
}

// function findIndexByName(array, name) {
// for (var i = 0; i < array.length; i++) {
// if (array[i].name === name) {
// return i;
// }
// }
// }
