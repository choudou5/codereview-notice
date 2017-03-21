<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="msgBox">
    <form>
        <div>
            <input type="hidden" id="userName" class="f-text" value="匿名" />
        </div>
        <div><textarea id="conBox" class="f-text"></textarea></div>
        <div class="tr">
            <p>
                <span class="countTxt">还能输入</span><strong class="maxNum">140</strong><span>个字</span>

                <input id="sendBtn" type="button" value=" 发表 " title="快捷键 Ctrl+Enter" />
            </p>
        </div>
    </form>
    <div class="list">
        <h3><span>精彩评论</span></h3>
        <ul>
            <li>
                <div class="userPic"><img src="http://tva2.sinaimg.cn/crop.0.0.1242.1242.50/ad17cc71jw8ezd2aah1rcj20yi0yiwj4.jpg" /></div>
                <div class="content">
                    <div class="userName">
                    	<a href="javascript:;">永不上线</a>
                    	<a class="like" href="javascript:;"><i class="fa fa-thumbs-o-up"></i>&nbsp;<span>0</span></a>
                    </div>
                    <div class="times"><span>07月05日 15:14</span><a class="del" href="javascript:;">删除</a></div>
                    <div class="msgInfo">新增删除广播功能。</div>
                </div>
            </li>
        </ul>
    </div>	
</div>

<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/msgBox.js"></script>
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