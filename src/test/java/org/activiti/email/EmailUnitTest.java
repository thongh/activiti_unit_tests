package org.activiti.email;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailUnitTest {
	
	Logger  logger = Logger.getLogger(EmailUnitTest.class);
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
//	@Test
	@Deployment(resources = {"org/activiti/test/email/EmailProcess.bpmn"})
	public void test() throws InterruptedException {
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		TaskService taskService = activitiRule.getTaskService();
		
		logger.info("------------------------- START -------------------------");
		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("emailProcess");
		
		Thread.sleep(2000);
		logger.info("Execution counts: " + runtimeService.createExecutionQuery().count());
		List<Execution> executions = 
				runtimeService.createExecutionQuery().
				processInstanceId(processInstance.getId()).list();
        for (int i = 0; i < executions.size(); i++) {
        	logger.info("Execution: " + executions.get(i).getName() 
        			+ " - " + executions.get(i).getId());
        }
        
        logger.info("Open tasks");
        logger.info(taskService.createTaskQuery().count());
		


		
		
        
	}

}
