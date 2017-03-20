<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--baidu share star-->
<div class="bdshare" style="overflow:hidden;border:1px solid #e8e8e8;padding:1px;position:relative;height:48px;">
     <div class="bdsharebuttonbox" style="float:left;margin-top:5px;margin-left:11px;">
       <a href="#" class="bds_sqq" title="分享到QQ好友" data-cmd="sqq"></a>
       <a href="#" class="bds_weixin" title="分享到微信" data-cmd="weixin"></a>
       <a href="#" class="bds_qzone" title="分享到QQ空间" data-cmd="qzone"></a>
       <a href="#" class="bds_tsina" title="分享到新浪微博" data-cmd="tsina"></a>
       <a href="#" class="bds_copy" data-cmd="copy"></a>
       <a href="#" class="bds_more" data-cmd="more"></a>
     </div>
</div>
<!--baidu share end-->
<script type="text/javascript">
	window.onload = function() {
	    (function() {
	    	//百度分享
	        window._bd_share_config = {
	              "common": {
	                "bdSnsKey": {},
	                "bdText": "这个应用不错，分享Git地址给大家",
	                "bdMini": "2",
	                "bdPic": "",
	                "bdStyle": "1",
	                "bdSize": "32",
	                "bdUrl": "https://github.com/DreamerPartner/codereview-notice"
	              },
	              "share": {
	                "bdSize": "24"
	              }
	            };
	        with(document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~ ( - new Date() / 36e5)];
	    })();
	};
</script>