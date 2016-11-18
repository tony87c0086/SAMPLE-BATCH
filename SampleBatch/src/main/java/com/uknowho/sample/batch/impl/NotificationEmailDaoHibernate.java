package com.uknowho.sample.batch.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.uknowho.sample.batch.config.Configuration;
import com.uknowho.sample.batch.constant.ErrorCodeConstant;
import com.uknowho.sample.batch.constant.ErrorMessageConstant;
import com.uknowho.sample.batch.dao.NotificationEmailDao;
import com.uknowho.sample.batch.entity.NotificationEmail;
import com.uknowho.sample.batch.xmlmodel.PaginationModel;
import com.uknowho.sample.batch.xmlmodel.SortModel;
import com.uknowho.sample.rest.exception.ServiceException;
import com.uknowho.sample.rest.utility.DataFormat;

/**
 * This NotificationEmailDaoHibernate class implements the NotificationEmailDao interface.
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

@Repository("notificationEmailDao")
public class NotificationEmailDaoHibernate 
	extends GenericDaoHibernate<NotificationEmail, Integer> implements NotificationEmailDao {

	private static final Logger logger = LoggerFactory.getLogger(NotificationEmail.class);
	
	public NotificationEmailDaoHibernate() {
		super(NotificationEmail.class);
	}	
	
	@Override
	public List<NotificationEmail> list() {
		return listWithCriteria(null, null, null);
	}

	@Override
	public NotificationEmail get(Integer ID) {
		NotificationEmail object = null;
		List<NotificationEmail> objectList = listWithCriteria(Restrictions.
				eq("notificationEmailID", ID), null, null);
		if ((objectList != null) && (objectList.size()>0)) {
			object = objectList.get(0);
		}
		return object;
	}
	
	@Override
	public NotificationEmail save(NotificationEmail object) throws ServiceException {
		try {
			
			getSession().saveOrUpdate(object);
			
		} catch (ConstraintViolationException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.CONSTRAINT_FAILURE, 
					ErrorMessageConstant.CONSTRAINT_FAILURE
							+ object.getNotificationEmailID());
		} catch (HibernateException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.RECORD_SAVE_FAILURE, 
					"Error while saving NotificationEmail details");
		} 
		return object;
	}
	
	@Override
	public NotificationEmail update(NotificationEmail object) throws ServiceException {
		try {
			
			getSession().update(object);
			
		} catch (ConstraintViolationException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.CONSTRAINT_FAILURE, 
					ErrorMessageConstant.CONSTRAINT_FAILURE
							+ object.getNotificationEmailID());
		} catch (HibernateException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.RECORD_UPDATE_FAILURE, 
					"Error while updating NotificationEmail details");
		} 
		return object;
	}

	@Override
	public NotificationEmail merge(NotificationEmail object) {
		try {
			
			getSession().merge(object);
			
		} catch (ConstraintViolationException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.CONSTRAINT_FAILURE, 
					ErrorMessageConstant.CONSTRAINT_FAILURE
							+ object.getNotificationEmailID());
		} catch (HibernateException ex) {
			logger.error(ex.getMessage());
			throw new ServiceException(ErrorCodeConstant.RECORD_MERGE_FAILURE, 
					"Error while merging NotificationEmail details");
		} 
		return object;
	}

	@SuppressWarnings("unchecked")
	private List<NotificationEmail> listWithCriteria(Criterion restrictions,
			List<SortModel> sortList, 
			PaginationModel pagination) {
		List<NotificationEmail> objectList = new ArrayList<NotificationEmail>();
		try {
			Criteria criteria =  getSession().createCriteria(NotificationEmail.class);
			
			// Sort order
			if (DataFormat.isListValid(sortList)) {
				for (SortModel sort : sortList) {
					if (sort != null) {
						if (sort.isDescend()) {
							criteria.addOrder(Order.desc(sort.getField()));
						} else {
							criteria.addOrder(Order.asc(sort.getField()));
						}
					}
				}
			} else {
				criteria.addOrder(Order.asc("templateID"));
			}
			
			// Limit result
			if (pagination != null) {
				criteria.setFirstResult(pagination.getFirstResult());
				criteria.setMaxResults(pagination.getMaxResult());
			} else {
				criteria.setFirstResult(Configuration.MIN_DEFAULT_RETURN_RECORD);
				criteria.setMaxResults(Configuration.MAX_DEFAULT_RETURN_RECORD);
			}
			
			if (restrictions != null) {
				criteria.add(restrictions);
			}
			objectList = criteria.list();
			
			if ((objectList == null) || (objectList.isEmpty())) {
				logger.warn(ErrorMessageConstant.RECORD_NOT_FOUND
							+ "NotificationEmail.");
				objectList = new ArrayList<NotificationEmail>();
			}
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new ServiceException(ErrorCodeConstant.HIBERNATE_EXCEPTION,
					"DB Error : " + e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION,
					"Unknown Error : " + e.getMessage());
		}
		return objectList;
	}
	
	@Override
	public List<NotificationEmail> list(
			Integer templateID, 
			Boolean active) throws ServiceException {
		return list(templateID, active, null, null);
	}

	@Override
	public List<NotificationEmail> list(
			Integer templateID,
			Boolean active, 
			List<SortModel> sortList, 
			PaginationModel pagination) throws ServiceException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (templateID != null) {
			resMap.put("templateID", templateID);
		}
		if (active != null) {
			resMap.put("active", active);
		}
		return listWithCriteria(Restrictions.allEq(resMap), sortList, pagination);
	}

}
