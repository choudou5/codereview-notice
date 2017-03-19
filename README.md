# 项目：代码审核公告

## 项目背景
1. 公司程序猿的开发水平都参差不齐，习惯也不一样
2. 缺少规范文档，甚至就没有
3. 接手同事的代码，吐槽过对方代码无数次
4. 指正同事的代码规范，但效果不佳
5. 程序猿对代码的重视程度不够

---

## 技术框架
1. Servlet-2.5
2. Lucene-4.10.4（数据存储）
3. OSS（图片上传）
4. umeditor-1.2.3 (富文本编辑)

---

## 本地部署说明（JDK7+）
1. 导入工程到 Eclipse
2. 编辑 system.properties（由于图片直传到阿里云OSS上， 所以需要配置相关参数， “48TB” 一年才9块大洋，别跟我说没钱）
3. 配完 就可以去部署启动了。


## 下次更新清单
- 粘贴上传图片---增加响应提示
- 编辑，删除功能
- 评论功能

---
## 效果图
### 分组清单
![Alt text](http://choudoufu-hd2.oss-cn-shanghai.aliyuncs.com/codereview/20170320/72131489951313024.png "首页分组")

### 列表
![Alt text](http://choudoufu-hd2.oss-cn-shanghai.aliyuncs.com/codereview/20170320/57211489951570542.png "列表")

### 新增
![Alt text](http://choudoufu-hd2.oss-cn-shanghai.aliyuncs.com/codereview/20170320/94261489951816664.png "新增")

### 详情
![Alt text](http://choudoufu-hd2.oss-cn-shanghai.aliyuncs.com/codereview/20170320/87841489951979008.png "详情")
 
