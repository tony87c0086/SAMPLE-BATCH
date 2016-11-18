package com.uknowho.sample.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.uknowho.sample.batch.dao.NotificationEmailDao;
import com.uknowho.sample.batch.dao.TemplateDao;
import com.uknowho.sample.batch.entity.NotificationEmail;
import com.uknowho.sample.batch.entity.Template;

/**
 * This Test class provides test cases for related DAO test. 
 * 
 * Created date <19-Nov-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class HibernateTest extends TestCasePreload {
	
	@Autowired
	private TemplateDao templateDao;
	
	@Autowired
	private NotificationEmailDao notificationEmailDao;
	
	@Test
	public void testTemplateDao() {
		Template template = null;
		Integer templateid = null;
		List<Template> templatelist = templateDao.list();
		
		if (templatelist != null && templatelist.size()>0) {
			templateid = templatelist.get(0).getTemplateID();
			template = templateDao.get(templateid);
		} else {
			template = new Template();
		}
		assertNotNull(template);
	}
	
	@Test
	public void testNotificationEmailDao() {
		NotificationEmail notificationEmail = null;
		Integer notificationEmailid = null;
		List<NotificationEmail> notificationEmaillist = notificationEmailDao.list();
		
		if (notificationEmaillist != null && notificationEmaillist.size()>0) {
			notificationEmailid = notificationEmaillist.get(0).getNotificationEmailID();
			notificationEmail = notificationEmailDao.get(notificationEmailid);
		} else {
			notificationEmail = new NotificationEmail();
		}
		assertNotNull(notificationEmail);
	}
	
}
