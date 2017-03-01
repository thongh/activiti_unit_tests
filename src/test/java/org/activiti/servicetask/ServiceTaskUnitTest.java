package org.activiti.servicetask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class ServiceTaskUnitTest {
	
	Logger  logger = Logger.getLogger(ServiceTaskUnitTest.class);
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
//	@Test
	@Deployment(resources = {"org/activiti/test/servicetask/ServiceTaskProcess.bpmn"})
	public void test() throws InterruptedException {
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		TaskService taskService = activitiRule.getTaskService();
		
		logger.info("------------------------- START -------------------------");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("temp", "isbn_001");
		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("serviceTaskProcess", variables);
		
		// Complete user task 1
		Task task = taskService.createTaskQuery().singleResult();
		logger.info("Complete user task id " + task.getId() + " - " + task.getName());
		activitiRule.getTaskService().complete(task.getId());
		
		// Now the service task will be executed and will be running for 10 secs until complete
		// Check execution
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
        logger.info("Variable isbn = " + runtimeService.getVariable(processInstance.getId(), "isbn"));
        logger.info("Variable temp = " + runtimeService.getVariable(processInstance.getId(), "isbn"));	
		


		
		
        
	}

}
