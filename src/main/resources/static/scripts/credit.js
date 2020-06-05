var credit = {
	deleteCredit : function(data, sfn, efn) {
		$.ajax({
			type : "post",
			url : "/credit/delete",
			data : data,
			success : sfn,
			error : efn,
			dataType : 'json'
		});
	},
	updateCredit : function(params, sfn, efn) {
		$.ajax({
		   type:"post",
           url: "/credit/update",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	},
	consumeReport : function(params, sfn, efn){
		$.ajax({
		   type:"post",
           url: "/credit-report/consume",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	},
	weekConsumeReport : function(params, sfn, efn){
		$.ajax({
		   type:"post",
           url: "/credit-report/week-consume",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	},
	monthConsumeReport : function(params, sfn, efn){
		$.ajax({
		   type:"post",
           url: "/credit-report/month-consume",
           data:params,
           dataType:'json',
           success: sfn,
           error: efn
       });
	}
};