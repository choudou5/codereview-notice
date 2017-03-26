<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="msgBox">
    <form>
        <div>
            <input type="hidden" id="noticeId" value="${notice.id }" pageNo="1" />
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
        	<a href="javascript:commentList();" title="下一页" style="display: none;">︾</a>
        	<span class="nomore">到底啦！</span>
        </div>
    </div>	
</div>