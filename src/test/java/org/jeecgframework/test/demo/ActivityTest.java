package org.jeecgframework.test.demo;


import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.jeecgframework.core.junit.AbstractUnitTest;
import org.jeecgframework.web.system.sms.controller.TSSmsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class ActivityTest  extends AbstractUnitTest{
	private static final Logger logger = Logger.getLogger(ActivityTest.class);
	@Autowired
	ProcessEngine processEngine;
	@Test
	public void testLogin() throws Exception {
		
		
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("deptLeader").list();
        System.out.println("======bigin===========");
        for (Task task : tasks) {
        	System.out.println("=====Task available====: " + task.getName());
        }
	}
}
