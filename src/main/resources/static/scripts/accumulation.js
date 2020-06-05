var accumulation = {
	deleteAccumulation : function(data, sfn, efn) {
		$.ajax({
			type : "post",
			url : app_url + "/accumulation/delete",
			data : data,
			success : sfn,
			error : efn,
			dataType : 'json'
		});
	},
	updateAccumulation : function(params, sfn, efn) {
		$.ajax({
		   type:"post",
           url: app_url+"/accumulation/update",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	},
	copyAccumulation : function(params, sfn, efn) {
		$.ajax({
		   type:"post",
           url: app_url+"/accumulation/copy",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	}
};