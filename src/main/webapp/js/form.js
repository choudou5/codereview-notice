$("#noticeForm").validate({
	rules:{
		groupKey:{
			required:true
		},
		title:{
			required: true,
			minlength:6,
			maxlength:25
		},
		adminAccount: {required: true, remote: "/codereviewnotice?view=ajaxValidAccount"}
	},
	messages: {
		adminAccount: {remote: "账号不正确，请联系管理员", required: "请输入管理员账号"}
	},
	errorClass: "help-inline",
	errorElement: "span",
	highlight:function(element, errorClass, validClass) {
		$(element).parents('.control-group').addClass('error');
	},
	unhighlight: function(element, errorClass, validClass) {
		$(element).parents('.control-group').removeClass('error');
		$(element).parents('.control-group').addClass('success');
	},
	submitHandler:function() {
        ajaxSubmitForm();
   },
});

function submitForm(){
	$("#noticeForm").valid();
}

function ajaxSubmitForm() {
	var param = $("#noticeForm").serialize();
    var url = "/codereviewnotice?view=formSave";
    $.ajax({
        type: "post",
        cache: false,
        dataType: "json",
        url: url,
        data: param,
        beforeSend: function(XMLHttpRequest){
            console.log("ajaxSubmitForm beforeSend");
        },
        success: function(data, textStatus){
        	console.log("ajaxSubmitForm success:"+data);
        	if(data.status == 200){
        		alert(data.content);
        		window.location.reload();
        	}else{
        		alert(data.content);
        	}
        },
        complete: function(XMLHttpRequest, textStatus){
        	console.log("ajaxSubmitForm complete");
        }
    });
}