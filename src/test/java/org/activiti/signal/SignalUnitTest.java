package org.activiti.signal;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignalUnitTest {
	
	Logger  logger = Logger.getLogger(SignalUnitTest.class);
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
//	@Test
	@Deployment(resources = {"org/activiti/test/signal/signal-test-process.bpmn",
							 "org/activiti/test/signal/signal-catch-process.bpmn",
							 "org/activiti/test/signal/signal-start-process.bpmn",
							 "org/activiti/test/signal/signal-usertask-process.bpmn",
							 "org/activiti/test/signal/signal-task-listener-process.bpmn"})
	public void test() throws InterruptedException {
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		
		logger.info("------------------------- START SIGNAL CATCH PROCESS -------------------------");
		ProcessInstance processInstance2 = activitiRule.getRuntimeService().startProcessInstanceByKey("signalCatchProcess");
		logger.info("Execution counts: " + runtimeService.createExecutionQuery().count());
		List<Execution> executions = 
				runtimeService.createExecutionQuery().
				processInstanceId(processInstance2.getId()).list();
        for (int i = 0; i < executions.size(); i++) {
        	logger.info("Execution: " + executions.get(i).getName() 
        			+ " - " + executions.get(i).getId());
        }
		
        TimeUnit.SECONDS.sleep(5);
        
		logger.info("------------------------- START SIGNAL TEST PROCESS -------------------------");
		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("signalTaskListenerProcess");
		logger.info("Process instance id: " + processInstance.getId());
		Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
		
		logger.info("Open task : " + task.getId() + " - " + task.getName() + " / assigned to: " + task.getAssignee());
//		logger.info("Complete user task.");
		activitiRule.getTaskService().claim(task.getId(), "kermit");
//		task = activitiRule.getTaskService().createTaskQuery().singleResult();		
//		logger.info("Open task : " + task.getId() + " - " + task.getName() + " / assigned to: " + task.getAssignee());
		
		
		
		TimeUnit.SECONDS.sleep(5);
		logger.info("------------------------- CHECK SIGNAL CATCH PROCESS -------------------------");		
		List<Execution> signalSubscriptions = runtimeService.createExecutionQuery().processInstanceId(processInstance2.getId())
			      .signalEventSubscriptionName("EMAIL_SUPPORT_SIGNAL")
			      .list();
		for (int i = 0; i < signalSubscriptions.size(); i++) {
			logger.info("Signal subscription " + signalSubscriptions.get(i).getId() 
					+ " - " + signalSubscriptions.get(i).getName());
		}
		
		executions = 
				runtimeService.createExecutionQuery().
				processInstanceId(processInstance2.getId()).list();
        for (int i = 0; i < executions.size(); i++) {
        	logger.info("Execution: " + executions.get(i).getName() 
        			+ " - " + executions.get(i).getId());
        }
		
		TimeUnit.SECONDS.sleep(5);
		
        
	}

}
