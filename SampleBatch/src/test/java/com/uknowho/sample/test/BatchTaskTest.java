package com.uknowho.sample.test;

import org.junit.Ignore;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This Test class provides test cases for Batch test. 
 * 
 * Created date <19-Nov-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

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
