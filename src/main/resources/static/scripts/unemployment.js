var unemployment = {
	deleteUnEmployment : function(data, sfn, efn) {
		$.ajax({
			type : "post",
			url : app_url + "/unemployment/delete",
			data : data,
			success : sfn,
			error : efn,
			dataType : 'json'
		});
	},
	updateUnEmployment : function(params, sfn, efn) {
		$.ajax({
		   type:"post",
           url: app_url+"/unemployment/update",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	},
	copyUnEmployment : function(params, sfn, efn) {
		$.ajax({
		   type:"post",
           url: app_url+"/unemployment/copy",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	}
};