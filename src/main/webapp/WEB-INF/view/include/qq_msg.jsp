<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <div id="qq">
	<div class="message" contentEditable='true'></div>
	<div class="But">
		<span class='submit'>发表</span>
	</div>
</div>
<!--qq end-->
<div id="time1"></div>
<!--msgCon begin-->
<div class="msgCon"></div>

<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
	//点击发表按扭，发表内容
	$("span.submit").click(function(){
		var txt=$(".message").html();
		if(txt==""){
			$('.message').focus();//自动获取焦点
			return;
		}

		$(".msgCon").prepend("<div class='msgBox'><dl><dt><img src='http://www.17sucai.com/preview/537801/2016-05-16/5-101/images/tx.jpg' width='50' height='50'/></dt><dd>神马都是浮云</dd></dl><div class='msgTxt'>"+txt+"</div></div>");
	});

</script>