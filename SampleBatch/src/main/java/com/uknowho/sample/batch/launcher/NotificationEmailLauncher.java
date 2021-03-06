package com.uknowho.sample.batch.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This NotificationEmailLauncher class implements Batch Launcher for sending email.
 * 
 * Created date <19-Nov-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */


@Component
public class NotificationEmailLauncher {

	private static final Logger logger = LoggerFactory.getLogger(NotificationEmailLauncher.class);
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job notificationEmail;
	
	public void run() {
		
		try {
			
			logger.debug("NotificationEmailLauncher called.");
			
			JobParameters jobParameter = new JobParametersBuilder().addLong(
					"time",
					System.currentTimeMillis()).toJobParameters();
			
			JobExecution jobExecution = jobLauncher.run(notificationEmail, jobParameter);
			
			logger.debug("NotificationEmailLauncher end. Status : " + jobExecution.getStatus());
			
		} catch (Exception e) {
			logger.error("NotificationEmailLauncher run error: ",  e);
		}
		
	}
}
