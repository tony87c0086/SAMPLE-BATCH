package com.uknowho.sample.batch.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uknowho.sample.batch.abstracts.ManagerAbstract;
import com.uknowho.sample.batch.constant.ErrorCodeConstant;
import com.uknowho.sample.batch.constant.ErrorMessageConstant;
import com.uknowho.sample.batch.dao.TemplateDao;
import com.uknowho.sample.batch.entity.Template;
import com.uknowho.sample.batch.exception.ServiceException;
import com.uknowho.sample.batch.utility.DataFormat;
import com.uknowho.sample.batch.xmlmodel.PaginationModel;
import com.uknowho.sample.batch.xmlmodel.SortModel;
import com.uknowho.sample.batch.xmlmodel.TemplateModel;

/**
 * This TemplateManager class is Defines for template related methods.
 * 
 * 
 * Created date <19-Nov-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Service("templateManager")
public class TemplateManager extends ManagerAbstract {
	
	private static final Logger logger = LoggerFactory.getLogger(TemplateManager.class);
	
	@Autowired
	private TemplateDao templateDao;
	
	public TemplateManager() {
		logger.info("TemplateManager default constrction method load.");
	}
	
	// Fetch exist template 
	public TemplateModel fetchTemplateModel(final Integer templateID)
			throws ServiceException {
		TemplateModel target = null;
		try {
			
			Template entity = templateDao.get(templateID);
			
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.TEMPLATE_NOT_FOUND, 
						ErrorMessageConstant.TEMPLATE_NOT_FOUND);
			} 
			
			target = popularTemplateToModel(entity);

		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return target;
	}
	
	// List all template template model
	public List<TemplateModel> listTemplateModel() throws ServiceException {
		return listTemplateModel(null, null, null);
	}
	
	/**
	 * @param active
	 * @return List<TemplateModel>
	 * @throws ServiceException
	 */
	public List<TemplateModel> listTemplateModel(
			final Boolean active) throws ServiceException {
		return listTemplateModel(active, null, null);
	}
		
	/**
	 * @param active
	 * @param sortList
	 * @param pagination
	 * @return List<TemplateModel>
	 * @throws ServiceException
	 */
	public List<TemplateModel> listTemplateModel(
			final Boolean active,
			final List<SortModel> sortList,
			final PaginationModel pagination) throws ServiceException {
		List<TemplateModel> targetList = null;
		try {
			
			targetList = new ArrayList<TemplateModel>();
			
			List<Template> templateList = null;
			if ((DataFormat.isListValid(sortList)) || (pagination != null)) {
				templateList = templateDao.list(
						active, 
						sortList,
						pagination);
			} else if (active != null) {
				templateList = templateDao.list(active);
			} else {
				templateList = templateDao.list();
			}
			
			if (DataFormat.isListValid(templateList)) {
				for (Template templateEntity : templateList) {
					if (templateEntity != null) {
						targetList.add(popularTemplateToModel(templateEntity));
					}
				}
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			if (targetList == null) {
				targetList = new ArrayList<TemplateModel>();
			}
		}
		return targetList;
	}

	
	public List<Integer> updateTemplateModelList(
			final List<TemplateModel> modelList, 
			final Boolean master) throws ServiceException {
		List<Integer> templateIDList = null;
		if (DataFormat.isListValid(modelList)) {
			templateIDList = new ArrayList<Integer>();
			Integer templateID = null;
			for (TemplateModel model : modelList) {
				if (model != null) {
					templateID = updateTemplateModel(model, master);
					templateIDList.add(templateID);
				}
			}
		}
		return templateIDList;
	}
	
	// Update existing template in DB
	public Integer updateTemplateModel(final TemplateModel model, final Boolean master)
			throws ServiceException {
		Integer templateID = null;
		try {
			// Data integrity Check 
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.TEMPLATE_MODEL_NOT_FOUND, 
						ErrorMessageConstant.TEMPLATE_MODEL_NOT_FOUND);
			}
			
			templateID = model.getTemplateID();
			Template entity = templateDao.get(templateID);
			
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.TEMPLATE_NOT_FOUND, 
						ErrorMessageConstant.TEMPLATE_NOT_FOUND);
			}
			
			entity = loadTemplateFromModel(entity, model);
			entity = templateDao.update(entity);

			if (entity != null) {
				templateID = entity.getTemplateID();
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return templateID;
	}
	
	public boolean deleteTemplateModelList(final List<Integer> templateIDList)
			throws ServiceException {
		boolean isSuccess = false;
		if (DataFormat.isListValid(templateIDList)) {
			for (Integer templateID : templateIDList) {
				if (templateID != null) {
					deleteTemplateModel(templateID);
				}
			}
		}
		isSuccess = true;
		return isSuccess;
	}
	
	// Delete existing template in DB
	public boolean deleteTemplateModel(final Integer templateID)
			throws ServiceException {
		boolean isSuccess = false;
		try {
			// Data integrity Check 
			if (templateID == null) {
				throw new ServiceException(ErrorCodeConstant.TEMPLATE_NOT_FOUND, 
						ErrorMessageConstant.TEMPLATE_NOT_FOUND);
			}
			
			isSuccess = templateDao.delete(templateID);

		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return isSuccess;
	}
	
}
