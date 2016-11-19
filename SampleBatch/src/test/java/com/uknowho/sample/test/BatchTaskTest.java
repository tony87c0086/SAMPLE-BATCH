package com.uknowho.sample.test;

import org.junit.Ignore;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Ignore
public class BatchTaskTest {

	@SuppressWarnings({ "unused", "resource" } )
	public static void main(String[] args) {
		
		try {
			
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] {
							"classpath:/config/applicationDataSource.xml",
							"classpath:/config/applicationHibernateContext.xml",
							"classpath:/config/applicationContextServices.xml",
							"classpath:/config/applicationContext.xml"
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
