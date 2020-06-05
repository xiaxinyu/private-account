var app = {
	messager : {
		show : function(obj) {
			var initParams = {
				timeout : 2000,
				showType : 'fade',
				style : {
					right : '',
					bottom : ''
				}
			};
			var finalParams = $.extend(initParams, obj);
			$.messager.show(finalParams);
		}
	},

	date : {
		format : function(value, row, index) {
			var date = new Date(value);
			return date.format('yyyy-MM-dd');
		}
	},

	money : {
		rmb : function(value, row, index) {
			return '<span style="font-size:12px;font-family:\'Times New Roman\';margin-right:2px;">¥</span><span>' + value+'</span>';
		}
	},
	
	login : function(params,sfn,efn){
		$.ajax({
			   type:"post",
	           url: app_url+"/application/login",
	           data:JSON.stringify(params),
	           contentType:'application/json',
	           dataType:'json',
	           success: sfn,
	           error: efn
	       });
		}
}