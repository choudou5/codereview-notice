<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="/js/umeditor1.2.3/themes/default/_css/umeditor.css">
    <link rel='stylesheet' type='text/css' href='/css/index.css'>
    <link type="text/css" rel="stylesheet" href="/css/notice-form.css">
    <title>Add Notice</title>
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
      <form action="/codereviewnotice?view=saveForm" method="post">
		   <div class="item kKOtfr">
		      	<div class="input-group">
		      		<span>分组:</span>
		      		<label>
		      			<select name="groupKey">
		     			  <c:forEach var="group" items="${groupList }">
		            		<option value="${group }">${group }</option>
		            	  </c:forEach>
		      			</select>
		      		</label>
		      	</div>
		      	<div class="input-group">
		      		<span>标题:</span>
		      		<label>
		      			<input name="title" class="input-large" placeholder="请输入公告标题" maxlength="25" />
		      		</label>
		      	</div>
		      	
		      	<div class="input-group">
		      		<!--style给定宽度可以影响编辑器的最终宽度 content -->
					<script type="text/plain" id="myEditor" style="width:1000px;height:240px;"><p>把您的代码片段截图 粘贴上了吧</p></script>
		      	</div>
		       
				<div class="clear"></div>
				
				<div class="input-group">
		      		<label>
		      			<input name="adminAccount" placeholder="请输入管理员账号" maxlength="30" />
		      		</label>
		      	</div>
		      	
				<div class="input-group">
		      		<input name="type" type="hidden" value="${type }" />
					<input type="submit" value="提交" />
		      	</div>
		      	
				<div id="btns">
					
				    <table>
				        <tr>
				            <td>
				                <button class="btn" unselected="on" onclick="getAllHtml()">获得整个html的内容</button>&nbsp;
				                <button class="btn" onclick="getContent()">获得内容</button>&nbsp;
				                <button class="btn" onclick="setContent()">写入内容</button>&nbsp;
				                <button class="btn" onclick="setContent(true)">追加内容</button>&nbsp;
				                <button class="btn" onclick="getContentTxt()">获得纯文本</button>&nbsp;
				                <button class="btn" onclick="getPlainTxt()">获得带格式的纯文本</button>&nbsp;
				                <button class="btn" onclick="hasContent()">判断是否有内容</button>
				            </td>
				        </tr>
				        <tr>
				            <td>
				                <button class="btn" onclick="setFocus()">编辑器获得焦点</button>&nbsp;
				                <button class="btn" onmousedown="isFocus();return false;">编辑器是否获得焦点</button>&nbsp;
				                <button class="btn" onclick="doBlur()">编辑器取消焦点</button>&nbsp;
				                <button class="btn" onclick="insertHtml()">插入给定的内容</button>&nbsp;
				                <button class="btn" onclick="getContentTxt()">获得纯文本</button>&nbsp;
				                <button class="btn" id="enable" onclick="setEnabled()">可以编辑</button>&nbsp;
				                <button class="btn" onclick="setDisabled()">不可编辑</button>
				            </td>
				        </tr>
				        <tr>
				            <td>
				                <button class="btn" onclick="UM.getEditor('myEditor').setHide()">隐藏编辑器</button>&nbsp;
				                <button class="btn" onclick="UM.getEditor('myEditor').setShow()">显示编辑器</button>&nbsp;
				                <button class="btn" onclick="UM.getEditor('myEditor').setHeight(300)">设置编辑器的高度为300</button>&nbsp;
				                <button class="btn" onclick="UM.getEditor('myEditor').setWidth(1200)">设置编辑器的宽度为1200</button>
				            </td>
				        </tr>
						<tr>
				            <td>
				                <button class="btn" onclick="createEditor()"/>创建编辑器</button>
		            			<button class="btn" onclick="deleteEditor()"/>删除编辑器</button>
				            </td>
				        </tr>
				    </table>
				    <div><h3 id="focush2"></h3></div>
				</div>
		
		      </div>
      </form>
    </div>
  </div>
</section>

<script type="text/javascript" src="/js/umeditor1.2.3/third-party/jquery.min.js"></script>
<script type="text/javascript" src="/js/umeditor1.2.3/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/umeditor1.2.3/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/umeditor1.2.3/editor_api.js"></script>
<script type="text/javascript" src="/js/umeditor1.2.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    //实例化编辑器
    var um = UM.getEditor('myEditor');
    um.addListener('blur',function(){
        $('#focush2').html('编辑器失去焦点了')
    });
    um.addListener('focus',function(){
        $('#focush2').html('')
    });
    //按钮的操作
    function insertHtml() {
        var value = prompt('插入html代码', '');
        um.execCommand('insertHtml', value)
    }
    function isFocus(){
        alert(um.isFocus())
    }
    function doBlur(){
        um.blur()
    }
    function createEditor() {
        enableBtn();
        um = UM.getEditor('myEditor');
    }
    function getAllHtml() {
        alert(UM.getEditor('myEditor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用umeditor')方法可以设置编辑器的内容");
        UM.getEditor('myEditor').setContent('欢迎使用umeditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UM.getEditor('myEditor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UM.getEditor('myEditor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UM.getEditor('myEditor').selection.getRange();
        range.select();
        var txt = UM.getEditor('myEditor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UM.getEditor('myEditor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UM.getEditor('myEditor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UM.getEditor('myEditor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UM.getEditor('myEditor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            domUtils.removeAttributes(btn, ["disabled"]);
        }
    }
</script>

</html>