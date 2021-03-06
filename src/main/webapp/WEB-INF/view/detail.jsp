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
    <link type="text/css" rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel='stylesheet' type='text/css' href='/js/jBox-0.4.7/jBox.Notice.css'>
    <link rel='stylesheet' type='text/css' href='/css/index.css'>
    <link type="text/css" rel="stylesheet" href="/css/notice-form.css">
    <link type="text/css" rel="stylesheet" href="/css/msgBox.css">
    <title>公告详情</title>
</head>
<body>
<section id="section-detail" class="section gMyTHp" style="background-color:#EEEEEE" data-reactid="172">
  <div class="fUkIQu" data-reactid="173">
    <svg data-reactid="174">
      <defs data-reactid="175">
        <pattern id="pattern-1" patternUnits="userSpaceOnUse" width="20" height="20" data-reactid="176">
          <path d="M 2.5,2.5l5,5M2.5,7.5l5,-5" fill="transparent" stroke-width="1" shape-rendering="auto" stroke="#CCCCCC" stroke-linecap="square" data-reactid="177"></path>
        </pattern>
      </defs>
      <rect style="fill:url(#pattern-1);" x="0" y="0" height="3000" width="3000" data-reactid="178"></rect>
    </svg>
  </div>
  <div id="detailBody" class="jcZDsC" style="border-left: none;">
  	<a href="?view=index">返回首页</a>
  	<div class="header-center">
    	<h2>${notice.title }</h2>
    	<span class="explain">分组：${notice.groupKey} ，
    	类型：
    	<font color="#66CCCC">
    	<c:if test="${notice.type eq 'good'}">优秀Code</c:if>
    	<c:if test="${notice.type eq 'bad'}">待改善Code</c:if>
    	</font>
    	，发布人： ${notice.createBy }
    	，时间： ${notice.createTime }</span>
    </div>
    <br>
    <div style="border: 1px solid #CCCCCC;">${notice.content }</div>
    <br><br>
 	<%@include file="/WEB-INF/view/include/msg_box.jsp" %>
  </div>
</section>

<script type="text/javascript" src="/js/jquery.js"></script>
<script async="async" type="text/javascript" src="/js/jBox-0.4.7/jBox.js"></script>
<script async="async" type="text/javascript" src="/js/jBox-0.4.7/jBox.Notice.min.js"></script>
<script type="text/javascript" src="/js/store1.3.20/store.min.js"></script>
<script type="text/javascript" src="/js/custom/msgBox.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/custom/comment.js"></script>

<script type="text/javascript">
    //初始化评论
    commentList();
 
</script>

</html>