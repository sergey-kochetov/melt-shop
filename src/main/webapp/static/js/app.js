var resume = {

	alert : function(message){
		alert(message);
	},

	moreProfiles : function() {
		var page = parseInt($('#profileContainer').attr('data-profile-number')) + 1;
		var total= parseInt($('#profileContainer').attr('data-profile-total'));
		if (page >= total) {
			$('#loadMoreIndicator').remove();
			$('#loadMoreContainer').remove();
			return;
		}
		var url = '/fragment/more?page=' + page;

		$('#loadMoreContainer').css('display', 'none');
		$('#loadMoreIndicator').css('display', 'block');
		$.ajax({
			url : url,
			success : function(data) {
				$('#loadMoreIndicator').css('display', 'none');
				$('#profileContainer').append(data);
				$('#profileContainer').attr('data-profile-number', page);
				if (page >= total-1) {
					$('#loadMoreIndicator').remove();
					$('#loadMoreContainer').remove();
				} else {
					$('#loadMoreContainer').css('display', 'block');
				}
			},
			error : function(data) {
				$('#loadMoreIndicator').css('display', 'none');
				resume.alert('Error! Try again later...');
			}
		});
	},

	ui : {
    		// http://handlebarsjs.com/
    		template : null,

    		getTemplate : function() {
    			if (resume.ui.template == null) {
    				var source = $("#ui-block-template").html();
    				resume.ui.template = Handlebars.compile(source);
    			}
    			return resume.ui.template;
    		},

    		addBlock : function() {
    			var template = resume.ui.getTemplate();
    			var container = $('#ui-block-container');
    			var blockIndex = container.find('.ui-item').length;
    			var context = {
    				blockIndex : blockIndex
    			};
    			container.append(template(context));

    			resume.createDatePicker();
    			container.find('input.level-slider').slider();
    		},

    		updateSelect : function(thisObj) {
    			if(thisObj.val() == '') {
    				var idSelectRef = thisObj.attr('data-ref-select');
    				$('#'+idSelectRef).val('');
    			}
    		}
    	}
};