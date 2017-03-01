package org.activiti.errorhandling;

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

public class ErrorHandlingUnitTest {
	
	Logger  logger = Logger.getLogger(ErrorHandlingUnitTest.class);
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
//	@Test
	@Deployment(resources = {"org/activiti/test/errorhandling/ErrorHandlingProcess.bpmn",
							 "org/activiti/test/errorhandling/Process1.bpmn"})
	public void test() throws InterruptedException {
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		TaskService taskService = activitiRule.getTaskService();
		
		logger.info("------------------------- START -------------------------");
		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("errorHandlingProcess");
     
	}

}
