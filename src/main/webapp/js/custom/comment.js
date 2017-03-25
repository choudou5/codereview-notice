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
        url: "/codereviewnotice?module=comment&action=push",
        data: {noticeId: noticeId, content: content},
        success: function(data, textStatus){
        	if(data.status == 200){
        		noticeSuccess(data.content);
        		id = data.extParam.id;
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
	if(!noticeId || !pageNo)
		return;
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/codereviewnotice?module=comment&action=list",
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
        				//插入元素
        				ul.append("<li id='"+comment.id+"'>"+MsgBoxUtil.genCommentHtml(comment.id, "匿名", comment.content, comment.createTime)+"</li>");
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
	}else{
		$("#noticeId").attr("pageNo", '');
		$(".pagings a").hide();
		$(".pagings .nomore").show();
	}
}

/**
 * 点赞
 * @param commentId
 */
function commentThumbsUp(commentId) {
	var noticeId = $("#noticeId").val();
	if(!noticeId || !commentId)
		return;
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/codereviewnotice?module=comment&action=thumbsUp",
        data: {noticeId: noticeId, commentId: commentId},
        success: function(data, textStatus){
        	if(data.status == 200){
        		var commentLi = $("#msgBox .list ul li#"+commentId);
        		commentLi.find(".content a.like").attr("href", "javascript:void(0);");
        		var oldLikeCount = parseInt(commentLi.find(".content span.likeCount").text());
        		commentLi.find(".content span.likeCount").text(oldLikeCount+1);
        		commentLi.find(".content a.like i").addClass("yet");
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