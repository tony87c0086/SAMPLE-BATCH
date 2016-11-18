package com.uknowho.sample.batch.dao;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.uknowho.sample.batch.entity.NotificationEmail;
import com.uknowho.sample.batch.xmlmodel.PaginationModel;
import com.uknowho.sample.batch.xmlmodel.SortModel;

/**
 * This interface to define NotificationEmailDao specific Data NotificationEmail operation methods.
 * 
 * Created date <19-Nov-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 * @param <NotificationEmail> a NotificationEmail Entity object 
 * @param <Integer> the primary key of the NotificationEmail
 *
 */

public interface NotificationEmailDao extends GenericDao<NotificationEmail, Integer> {

	/**
     * List NotificationEmail record by script type
     * @param templateID
     * @param active
     * @return NotificationEmail object list
     */
	List<NotificationEmail> list(Integer templateID, Boolean active) throws ServiceException;
	
	/**
     * List NotificationEmail record by script type
     * @param templateID
     * @param active
     * @param sortList
     * @param pagination
     * @return NotificationEmail object list
     */
	List<NotificationEmail> list(
			Integer templateID,
			Boolean active, 
			List<SortModel> sortList, 
			PaginationModel pagination) throws ServiceException;
}
