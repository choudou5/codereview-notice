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
		createBy:{
			required:true
		},
		sysPwd: {required: true, remote: "?module=notice&action=ajaxValidPwd"}
	},
	messages: {
		sysPwd: {remote: "密码不正确，请联系管理员", required: "请输入系统密码"}
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
    var url = "?module=notice&action=formSave";
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