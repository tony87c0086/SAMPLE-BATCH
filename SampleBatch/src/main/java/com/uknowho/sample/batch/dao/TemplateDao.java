package com.uknowho.sample.batch.dao;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.uknowho.sample.batch.entity.Template;
import com.uknowho.sample.batch.xmlmodel.PaginationModel;
import com.uknowho.sample.batch.xmlmodel.SortModel;

/**
 * This interface to define TemplateDao specific Data Template operation methods.
 * 
 * Created date <19-Nov-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 * @param <Template> a Template Entity object 
 * @param <Integer> the primary key of the Template
 *
 */

public interface TemplateDao extends GenericDao<Template, Integer> {

	/**
     * List Template record by script type
     * @param active
     * @return Template object list
     */
	List<Template> list(Boolean active) throws ServiceException;
	
	/**
     * List Template record by script type
     * @param active
     * @param sortList
     * @param pagination
     * @return Template object list
     */
	List<Template> list(
			Boolean active, 
			List<SortModel> sortList, 
			PaginationModel pagination) throws ServiceException;
}
