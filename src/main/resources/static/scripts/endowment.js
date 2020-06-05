var endowment = {
	deleteEndowment : function(data, sfn, efn) {
		$.ajax({
			type : "post",
			url : app_url + "/endowment/delete",
			data : data,
			success : sfn,
			error : efn,
			dataType : 'json'
		});
	},
	updateEndowment : function(params, sfn, efn) {
		$.ajax({
		   type:"post",
           url: app_url+"/endowment/update",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	},
	copyEndowment : function(params, sfn, efn) {
		$.ajax({
		   type:"post",
           url: app_url+"/endowment/copy",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	}
};