package ht.venus.web.service.impl.activiti;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import ht.venus.web.service.activiti.ActivitiGroupServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import ht.venus.web.entity.activiti.ActivitiGroupEntity;
import ht.venus.web.entity.activiti.ActivitiGroupUserEntity;
@Service("activitiGroupService")
@Transactional
public class ActivitiGroupServiceImpl extends CommonServiceImpl implements ActivitiGroupServiceI {

	
	public void addMain(ActivitiGroupEntity activitiGroup,
	        List<ActivitiGroupUserEntity> activitiGroupUserList){
			//保存主信息
			this.save(activitiGroup);
		
			/**保存-工作流程组和人员关系对应model*/
			for(ActivitiGroupUserEntity activitiGroupUser:activitiGroupUserList){
				//外键设置
				activitiGroupUser.setGroupId(activitiGroup.getId());
				this.save(activitiGroupUser);
			}
	}

	
	public void updateMain(ActivitiGroupEntity activitiGroup,
	        List<ActivitiGroupUserEntity> activitiGroupUserList) {
		//保存订单主信息
		this.saveOrUpdate(activitiGroup);
		
		
		//===================================================================================
		//获取参数
		Object id0 = activitiGroup.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-工作流程组和人员关系对应model
	    String hql0 = "from ActivitiGroupUserEntity where 1 = 1 AND groupId = ? ";
	    List<ActivitiGroupUserEntity> activitiGroupUserOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-工作流程组和人员关系对应model
		for(ActivitiGroupUserEntity oldE:activitiGroupUserOldList){
			boolean isUpdate = false;
				for(ActivitiGroupUserEntity sendE:activitiGroupUserList){
					//需要更新的明细数据-工作流程组和人员关系对应model
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-工作流程组和人员关系对应model
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-工作流程组和人员关系对应model
		for(ActivitiGroupUserEntity activitiGroupUser:activitiGroupUserList){
			if(activitiGroupUser.getId()==null){
				//外键设置
				activitiGroupUser.setGroupId(activitiGroup.getId());
				this.save(activitiGroupUser);
			}
		}
		
	}

	
	public void delMain(ActivitiGroupEntity activitiGroup) {
		//删除主表信息
		this.delete(activitiGroup);
		
		//===================================================================================
		//获取参数
		Object id0 = activitiGroup.getId();
		//===================================================================================
		//删除-工作流程组和人员关系对应model
	    String hql0 = "from ActivitiGroupUserEntity where 1 = 1 AND groupId = ? ";
	    List<ActivitiGroupUserEntity> activitiGroupUserOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(activitiGroupUserOldList);
	}
	
}