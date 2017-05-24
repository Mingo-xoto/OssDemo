/**
 * 
 */
package com.yhq.log4j;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * 自定义日志输出级别：只输出相等级别到同一个文件，不输出到不同文件
 * 
 * @author YHQ
 * @ClassName LogAppender
 * @Version
 * @date 2016-9-10 下午5:20:46
 */
public class LogAppender extends DailyRollingFileAppender {
	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		// 只判断是否相等，而不判断优先级
		return this.getThreshold().equals(priority);
	}
}
