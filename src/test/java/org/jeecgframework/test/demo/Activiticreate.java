package org.jeecgframework.test.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.ExtensionAttribute;
import org.activiti.bpmn.model.ExtensionElement;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.jeecgframework.core.junit.AbstractUnitTest;
import org.jeecgframework.core.util.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class Activiticreate extends AbstractUnitTest {
	private static final Logger logger = Logger.getLogger(Activiticreate.class);
	@Autowired
	ProcessEngine processEngine;
	@Autowired
	RepositoryService repositoryService;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Test
	public void test01() throws IOException {
		System.out.println(".........start...");

		// 1. Build up the model from scratch
		BpmnModel model = new BpmnModel();
		org.activiti.bpmn.model.Process process = new org.activiti.bpmn.model.Process();
		model.addProcess(process);
		final String PROCESSID = "process01";
		final String PROCESSNAME = "测试01";
		process.setId(PROCESSID);
		process.setName(PROCESSNAME);

		process.addFlowElement(createStartEvent());
		process.addFlowElement(createSequenceFlow("startEvent","task1", "不", ""));
		process.addFlowElement(createUserTask("task1", "节点01",
				"candidateGroup1"));
		process.addFlowElement(createSequenceFlow("task1","createExclusiveGateway1", "", ""));
		process.addFlowElement(createExclusiveGateway("createExclusiveGateway1"));
		
		process.addFlowElement(createSequenceFlow("createExclusiveGateway1", "task2", "通过", "${pass=='1'}"));
		process.addFlowElement(createSequenceFlow("createExclusiveGateway1","task3", "不通过", "${pass=='2'}"));
		
		process.addFlowElement(createUserTask("task2", "节点02",
				"candidateGroup2"));
		
		process.addFlowElement(createUserTask("task3", "节点03",
				"candidateGroup3"));
		
		process.addFlowElement(createSequenceFlow("task2", "endEvent", "通", ""));
		process.addFlowElement(createSequenceFlow("task3","endEvent", "", ""));
		
		process.addFlowElement(createEndEvent());

		// 2. Generate graphical information
		new BpmnAutoLayout(model).execute();

		// 3. Deploy the process to the engine
		Deployment deployment = repositoryService
				.createDeployment().disableSchemaValidation().addBpmnModel(PROCESSID + ".bpmn", model)
				.name(PROCESSID + "_deployment").deploy();
		System.out.println(".........deploy...........");
		// 4. Start a process instance
	   HashMap<String, Object> param=new HashMap<String, Object>();
	   param.put("tablebame", "用户表");
	   param.put("param", "userid");
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(PROCESSID,param);
		System.out.println("=====Task candidateGroup1 ====: " + processEngine.getTaskService().createTaskQuery()
				.taskCandidateGroup("candidateGroup1").count() );
		List<Task> tasks = processEngine.getTaskService().createTaskQuery()
				.taskCandidateGroup("candidateGroup1").list();
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("pass", "2");
		System.out.println("=====Task candidateGroup2 ====: " + processEngine.getTaskService().createTaskQuery()
				.taskCandidateGroup("candidateGroup2").count() );
		
		taskService.complete(tasks.get(0).getId(), data);
	

		
		
		System.out.println("=====Task candidateGroup3 ====: " + processEngine.getTaskService().createTaskQuery()
				.taskCandidateGroup("candidateGroup3").count() );
		// 6. Save process diagram to a file
		InputStream processDiagram = repositoryService
				.getProcessDiagram(processInstance.getProcessDefinitionId());
		FileUtils.copyInputStreamToFile(processDiagram, new File(
				"D:/创业信息/建材管理系统/源码/deployments/" + PROCESSID + ".png"));

		// 7. Save resulting BPMN xml to a file
		InputStream processBpmn = processEngine.getRepositoryService()
				.getResourceAsStream(deployment.getId(), PROCESSID + ".bpmn");
		FileUtils
				.copyInputStreamToFile(processBpmn, new File(
						"D:/创业信息/建材管理系统/源码/deployments/" + PROCESSID
								+ ".bpmn"));

		System.out.println(".........end...........");
	}

	/* 任务节点 */
	protected static UserTask createUserTask(String id, String name,
			String candidateGroup) {
		List<String> candidateGroups = new ArrayList<String>();
		candidateGroups.add(candidateGroup);
		UserTask userTask = new UserTask();
		userTask.setName(name);
		userTask.setId(id);
		userTask.setCandidateGroups(candidateGroups);
		List<String> candidateUsers = new ArrayList<String>();
		candidateUsers.add("小欧");
		candidateUsers.add("小明");
		userTask.setCandidateUsers(candidateUsers);
		ExtensionElement childElement=new ExtensionElement();
		childElement.setName("activiti:taskListener");
		ExtensionAttribute eventAttribute=new ExtensionAttribute();
		eventAttribute.setName("event");
		eventAttribute.setValue("create");
		childElement.addAttribute(eventAttribute);
		
		ExtensionAttribute classAttribute=new ExtensionAttribute();
		classAttribute.setName("class");
		classAttribute.setValue("ht.divineland.core.activiti.MyTaskListener");
		childElement.addAttribute(classAttribute);
		//childElement.setElementText("<activiti:taskListener event=\"create\" class=\"ht.divineland.core.activiti.MyTaskListener\" />");
		userTask.addExtensionElement(childElement);
		System.out.println("======22===");
		return userTask;
	}

	/* 连线 */
	protected static SequenceFlow createSequenceFlow(String from, String to,
			String name, String conditionExpression) {
		SequenceFlow flow = new SequenceFlow();
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		flow.setName(name);
		if (StringUtils.hasText(conditionExpression)) {
			flow.setConditionExpression(conditionExpression);
		}
		return flow;
	}

	/* 排他网关 */
	protected static ExclusiveGateway createExclusiveGateway(String id) {
		ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
		exclusiveGateway.setId(id);
		return exclusiveGateway;
	}

	/* 开始节点 */
	protected static StartEvent createStartEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEvent");
		return startEvent;
	}

	/* 结束节点 */
	protected static EndEvent createEndEvent() {
		EndEvent endEvent = new EndEvent();
		endEvent.setId("endEvent");
		return endEvent;
	}
}
