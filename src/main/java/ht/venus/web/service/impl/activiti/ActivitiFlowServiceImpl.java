package ht.venus.web.service.impl.activiti;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ht.venus.web.service.activiti.ActivitiFlowServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("activitiFlowService")
@Transactional
public class ActivitiFlowServiceImpl extends CommonServiceImpl implements ActivitiFlowServiceI {
	
}