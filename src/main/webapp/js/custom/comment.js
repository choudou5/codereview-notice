var __praisePrefix = "thumbsUp-";//点赞前缀

$(function(){
	//清理过期点赞数据
	clearTimeOutCache();
	
});

//清理过期点赞数据
function clearTimeOutCache(){
	setTimeout(function() {
	  var nowTime = new Date().getTime();
	  store.forEach(function(key, value){
		 if(key.indexOf(__praisePrefix) != -1){
			if((nowTime - parseInt(value)) > 25200000){//超过7天则清理
				store.remove(key);
			}
		 } 
	  });
	}, 1500);
}

/**
 * 评论推送
 * @param noticeId
 * @param content
 * @returns id
 */
function commentPush(noticeId, content) {
	if(!noticeId)
		return null;
	var id = null;
    $.ajax({
        type: "post",
        cache: false,
        async: false,
        dataType: "json",
        url: "?module=comment&action=push",
        data: {noticeId: noticeId, content: content},
        success: function(data, textStatus){
        	if(data.status == 200){
        		noticeSuccess(data.content);
        		id = data.extParam.id;
        		$("#contentNone").hide();
        	}else{
        		noticeError(data.content);
        	}
        },
        complete: function(XMLHttpRequest, textStatus){
        	console.log("comment push complete");
        }
    });
    return id;
}

/**
 * 评论列表
 * @param pageNo
 * @returns {Boolean}
 */
function commentList() {
	var noticeId = $("#noticeId").val();
	var pageNo = $("#noticeId").attr("pageNo");
	if(!noticeId || !pageNo){
		$("#section-detail").css("background-color", "#EB613D");
		$("#detailBody").css("text-align", "center").html('<span class="noPageTitle">404</span><span class="noPageDesc">您访问的页面找不到了！</span><a href="?view=index">返回首页</a>');
		return;
	}
    $.ajax({
        type: "post",
        dataType: "json",
        url: "?module=comment&action=list",
        data: {noticeId: noticeId, pageNo: pageNo},
        success: function(data, textStatus){
        	if(data.status == 200){
        		var pageBean = data.content;
        		setPaging(pageBean);
        		if(pageBean.totalCount == 0)
        			$("#contentNone").show();
        		var datas = pageBean.result;
        		if(datas){
        			var ul = $("#msgBox .list ul");
        			for(var i=0; i< datas.length; i++){
        				var comment = datas[i];
        				var canPraise = canThumbsUp(comment.id, true);
        				//插入元素
        				ul.append("<li id='"+comment.id+"'>"+MsgBoxUtil.genCommentHtml(comment.id, "匿名", comment.content, comment.createTime, comment.thumbsUpCount, canPraise)+"</li>");
        			}
        		}
        	}else{
        		LogUtil.error(data.content);
        	}
        },
        complete: function(XMLHttpRequest, textStatus){
        	console.log("comment list complete");
        }
    });
}

/**
 * 设置分页
 * @param hasNext
 * @param nextPage
 */
function setPaging(pageBean){
	if(pageBean.hasNext){
		$("#noticeId").attr("pageNo", pageBean.nextPage);
		$(".pagings a").show();
	}else{
		$("#noticeId").attr("pageNo", '');
		$(".pagings a").hide();
		if(pageBean.totalPages > 1)
			$(".pagings .nomore").show();
	}
}

/**
 * 点赞
 * @param commentId
 */
function commentThumbsUp(commentId) {
	if(!commentId)
		return;
	var flag = canThumbsUp(commentId);
	if(flag){
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "?module=comment&action=thumbsUp",
	        data: {commentId: commentId},
	        success: function(data, textStatus){
	        	if(data.status == 200){
	        		var commentLi = $("#msgBox .list ul li#"+commentId);
	        		commentLi.find(".userName a.like").attr("href", "javascript:void(0);");
	        		var oldLikeCount = parseInt(commentLi.find(".userName span.likeCount").text());
	        		commentLi.find(".userName span.likeCount").text(oldLikeCount+1);
	        		commentLi.find(".userName a.like i").addClass("yet");
	        		LogUtil.info(data.content);
	        	}else{
	        		noticeError(data.content);
	        	}
	        },
	        complete: function(XMLHttpRequest, textStatus){
	        	console.log("comment thumbsUp complete");
	        }
	    });
	}
}

/**
 * 是否能点赞
 * @param commentId
 * @param isCheck
 * @returns {Boolean}
 */
function canThumbsUp(commentId, isCheck){
	var exists = store.get(__praisePrefix+commentId);
	if(exists == null){
		if(!isCheck)
			store.set(__praisePrefix+commentId, new Date().getTime());
		return true;
	}else{
		LogUtil.info(commentId+"：已点赞！");
		return false;
	}
}