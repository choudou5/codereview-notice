<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="msgBox">
    <form>
        <div>
            <input type="hidden" id="noticeId" value=${notice.id } pageNo="1" />
            <input type="hidden" id="userName" value="匿名" />
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
            <li id="contentNone" style="display: none;"><div class="content contentNone">暂无评论，快抢沙发</div></li>
        </ul>
        <div class="pagings">
        	<a href="javascript:commentList();" title="下一页">︾</a>
        	<span class="nomore">到底啦！</span>
        </div>
    </div>	
</div>

<script type="text/javascript" src="/js/jquery.js"></script>
<script async="async" type="text/javascript" src="/js/jBox-0.4.7/jBox.js"></script>
<script async="async" type="text/javascript" src="/js/jBox-0.4.7/jBox.Notice.min.js"></script>
<script type="text/javascript" src="/js/custom/msgBox.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/custom/comment.js"></script>


<script type="text/javascript">
    //初始化评论
    commentList();
 
</script>