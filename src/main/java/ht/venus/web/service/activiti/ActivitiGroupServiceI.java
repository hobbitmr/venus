package ht.venus.web.service.activiti;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import ht.venus.web.entity.activiti.ActivitiGroupEntity;
import ht.venus.web.entity.activiti.ActivitiGroupUserEntity;

public interface ActivitiGroupServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ActivitiGroupEntity activitiGroup,
	        List<ActivitiGroupUserEntity> activitiGroupUserList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ActivitiGroupEntity activitiGroup,
	        List<ActivitiGroupUserEntity> activitiGroupUserList);
	public void delMain (ActivitiGroupEntity activitiGroup);
}
