package org.activiti.parallelgateway;

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

public class ParallelGatewayUnitTest {
	
	Logger  logger = Logger.getLogger(ParallelGatewayUnitTest.class);
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
//	@Test
	@Deployment(resources = {"org/activiti/test/parallelgateway/ParallelProcess.bpmn"})
	public void test() throws InterruptedException {
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		
		logger.info("------------------------- START -------------------------");
		ProcessInstance processInstance2 = activitiRule.getRuntimeService().startProcessInstanceByKey("parallelProcess");
		
		Thread.sleep(5000);
		
		logger.info("Instance completed? " + processInstance2.isEnded());
        
	}

}
