<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=8">
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-control" content="no-cache">
	<meta http-equiv="Cache" content="no-cache">
    <link type="text/css" rel="stylesheet" href="/js/umeditor1.2.3/themes/default/_css/umeditor.css">
    <link rel='stylesheet' type='text/css' href='/css/index.css'>
    <link rel='stylesheet' type='text/css' href='/js/jBox-0.4.7/jBox.Notice.css'>
    <link type="text/css" rel="stylesheet" href="/css/notice-form.css">
    <title>添加公告</title>
</head>
<body>

<section id="section-0" class="section gMyTHp" style="background-color:#C6F0D5" data-reactid="172">
  <div class="fUkIQu" data-reactid="173">
    <svg data-reactid="174">
      <defs data-reactid="175">
        <pattern id="pattern-1" patternUnits="userSpaceOnUse" width="20" height="20" data-reactid="176">
          <path d="M 5,5l10,10M15,5l10,-10 M5,15l-10,10M15,25l10,-10 M-5,5l10,-10" fill="transparent" stroke-width="1" shape-rendering="auto" stroke="#CCCCCC" stroke-linecap="square" data-reactid="177"></path>
        </pattern>
      </defs>
      <rect style="fill:url(#pattern-1);" x="0" y="0" height="3000" width="3000" data-reactid="178"></rect>
    </svg>
  </div>
  <div class="jcZDsC" style="border-left: none;">
    <div class="header-center">
    	<h2>新增公告</h2>
    	<span class="explain">把您的代码片段截图 粘贴上来吧！</span>
    </div>
    <div class="euuwmB">
      <form id="noticeForm" action="#" method="post">
		   <div class="item kKOtfr">
		      	<div class="input-group">
		      		<span>分组:<font color="red">*</font></span>
		      		<label>
		      			<select name="groupKey">
		     			  <c:forEach var="group" items="${groupList }">
		            		<option value="${group }" <c:if test="${group eq groupKey}">selected="selected"</c:if>>${group }</option>
		            	  </c:forEach>
		      			</select>
		      		</label>
		      	</div>
		      	<div class="input-group">
		      		<span>类型:<font color="red">*</font></span>
		      		<label>
		      			<input name="type" type="radio" value="good" <c:if test="${type eq 'good'}">checked="checked"</c:if> />优秀Code &nbsp;&nbsp;
		      			<input name="type" type="radio" value="bad" <c:if test="${type eq 'bad'}">checked="checked"</c:if> />待改善Code
		      		</label>
		      	</div>
		      	<div class="input-group">
		      		<span>标题:<font color="red">*</font></span>
		      		<label>
		      			<input name="title" type="text" class="input-large" placeholder="请输入公告标题" maxlength="25" />
		      		</label>
		      	</div>
		      	
		      	<div class="input-group">
		      		<!--style给定宽度可以影响编辑器的最终宽度 content -->
					<script type="text/plain" id="contentEditor" name="content" style="width:80%;height:450px;"><p>把您的代码片段截图 粘贴上来吧</p></script>
		      	</div>
				<div class="clear"></div>
				<div class="input-group">
		      		<label>
		      			<input name="adminAccount" type="password" placeholder="请输入管理员账号" maxlength="30" /><font color="red">*</font>
		      		</label>
		      	</div>
				<div class="input-group">
					<input type="submit" value="提交" onclick="submitForm()"/>
		      	</div>
		      	
		      </div>
      </form>
    </div>
  </div>
</section>

<script type="text/javascript" src="/js/umeditor1.2.3/third-party/jquery.min.js"></script>
<script type="text/javascript" src="/js/jBox-0.4.7/jBox.js"></script>
<script type="text/javascript" src="/js/jBox-0.4.7/jBox.Notice.min.js"></script>
<script type="text/javascript" src="/js/umeditor1.2.3/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/umeditor1.2.3/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/umeditor1.2.3/editor_api.js"></script>
<script type="text/javascript" src="/js/umeditor1.2.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/jquery.validate.js"></script>
<script type="text/javascript" src="/js/form.js"></script>
<script type="text/javascript">
    //实例化编辑器
    var um = UM.getEditor('contentEditor');
 
    var noticeObj;
    function notice(msg){
    	noticeObj = new jBox('Notice', {
	    	id: "jBoxMsgNotice",
	        content: msg,
	        autoClose: 12000,
	        color: 'black',
	        attributes: {                // Note that attributes can only be 'left' or 'right' when using numbers for position, e.g. {x: 300, y: 20}
	            x: 'left',                 // Horizontal position, use 'left' or 'right'
	            y: 'bottom'                   // Vertical position, use 'top' or 'bottom'
	        },
        });
    }
    function noticeClose(){
    	if(noticeObj != null){
    		noticeObj.destroy();
    		noticeObj = null;
    	}
    }
</script>

</html>