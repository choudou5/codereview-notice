<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="/js/umeditor1.2.3/themes/default/_css/umeditor.css">
    <script type="text/javascript" src="/js/umeditor1.2.3/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="/js/umeditor1.2.3/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/umeditor1.2.3/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="editor_api.js"></script>
    <script type="text/javascript" src="/js/umeditor1.2.3/lang/zh-cn/zh-cn.js"></script>
    <title>ueditor demo</title>
</head>

<body>
    <!-- 加载编辑器的容器 -->
    <script id="editorContainer" name="content" type="text/plain">
        	这里写你的初始化内容
    </script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('editorContainer');
    </script>
</body>

</html>