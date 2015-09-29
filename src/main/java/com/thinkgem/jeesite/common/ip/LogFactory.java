package com.thinkgem.jeesite.common.ip;



import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 * 
 * 功能描述：日志工厂
 *
 * @author  Administrator
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class LogFactory {
	
	private static final Logger logger;
	
	static {
		logger = Logger.getLogger("stdout");
		logger.setLevel(Level.DEBUG);
	}

	public static void log(String info, Level level, Throwable ex) {
		logger.log(level, info, ex);
	}
	
	public static Level  getLogLevel(){
		return logger.getLevel();
	}

}