package com.dreamerpartner.codereview.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class BaseLog {

	protected static Log logger = LogFactory.getLog("BaseLog");
	
	/*
	 %m：输出代码中指定的消息。
	 %p：输出优先级。
	 %r：输入自应用启动到输出该log信息耗费的毫秒数。
	 %c：输出所属的类目，通常就是所在类的全名。
	 %t：输出产生该日志线程的线程名。
	 %n：输出一个回车换行符。Windows平台为“\r\n”，UNIX为“\n”。
	 %d：输出日志时间点的日期或时间，默认格式为ISO8601，推荐使用“%d{ABSOLUTE}”，这个输出格式形如：“2007-05-07 18:23:23,500”，符合中国人习惯。
	 %l：输出日志事件发生的位置，包括类名、线程名，以及所在代码的行数。
	 */
	
}
