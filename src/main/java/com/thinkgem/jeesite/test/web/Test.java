package com.thinkgem.jeesite.test.web;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thinkgem.jeesite.common.db.MultipleDataSource;
import com.thinkgem.jeesite.common.db.MySqlMapper;
import com.thinkgem.jeesite.common.db.SqlServerMapper;
public class Test {
	
	 public static void main(String[] args) {
	        //初始化ApplicationContext
	        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mvc.xml");

	        MySqlMapper mySqlMapper = applicationContext.getBean(MySqlMapper.class);

	        SqlServerMapper sqlServerMapper = applicationContext.getBean(SqlServerMapper.class);
	        
	        //设置数据源为MySql,使用了AOP测试时请将下面这行注释
	        MultipleDataSource.setDataSourceKey("dataSource");
	        mySqlMapper.getList();
	        //设置数据源为SqlServer,使用AOP测试时请将下面这行注释
	        MultipleDataSource.setDataSourceKey("dataSource2");
	        sqlServerMapper.getList();
	    }
}
