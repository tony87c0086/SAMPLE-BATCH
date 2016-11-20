package com.uknowho.sample.batch.publisher;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uknowho.sample.batch.manager.NotificationEmailManager;
import com.uknowho.sample.batch.service.SMTPMailerService;
import com.uknowho.sample.batch.utility.DataFormat;
import com.uknowho.sample.batch.xmlmodel.NotificationEmailModel;

/**
 * This NotificationEmailSender class implements Batch for send email.
 * 
 * Created date <19-Nov-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Service
public class NotificationEmailSender implements Tasklet {

	private static final Logger logger = LoggerFactory.getLogger(NotificationEmailSender.class);
	
//	@Autowired
//	private TemplateManager templateManager;
	
	@Autowired
	private NotificationEmailManager notificationEmailManager;
	
	@Autowired
	private SMTPMailerService SMTPMailerService;
	
	public NotificationEmailSender() {
		logger.info("NotificationEmailSender default construction method load.");
	}
	
	public RepeatStatus execute(
			final StepContribution stepContribution,
			final ChunkContext chunkContext) throws Exception {
		List<NotificationEmailModel> emailList = null;
		try {
			
			logger.debug("NotificationEmailSender execute called.");
			
			boolean active = true; // Only active record
			Integer templetaID =  null; // Filter by velocity template 
			emailList = notificationEmailManager.
					listNotificationEmailModel(templetaID, active);
			
			if (DataFormat.isListValid(emailList)) {
				for (NotificationEmailModel email : emailList) {
					if (email != null) {
						try {
							// TODO Send rich HTML velocity email if needed
							boolean isSent = SMTPMailerService.sendSimpleMail(email);
							email.setActive(!isSent);
						} catch (Exception e) {
							logger.error("SMTPMailerService error: ",  e);
						}
					}
				}
			}
			
			logger.debug("NotificationEmailSender execute end.");
			
		} catch (Exception e) {
			logger.error("NotificationEmailSender execute error: ",  e);
		} finally {
			// Update notification record active to false if email sending successfully.
			if (DataFormat.isListValid(emailList)) {
				try {
					notificationEmailManager.updateNotificationEmailModelList(emailList, true);
				} catch (Exception e) {
					logger.error("Update NotificationEmail error: ",  e);
				}
			}
		}
		
		return RepeatStatus.FINISHED;
	}
}
