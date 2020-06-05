var medical = {
	deleteMedical : function(data, sfn, efn) {
		$.ajax({
			type : "post",
			url : app_url + "/medical/delete",
			data : data,
			success : sfn,
			error : efn,
			dataType : 'json'
		});
	},
	updateMedical : function(params, sfn, efn) {
		$.ajax({
		   type:"post",
           url: app_url+"/medical/update",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	},
	copyMedical : function(params, sfn, efn) {
		$.ajax({
		   type:"post",
           url: app_url+"/medical/copy",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	}
};