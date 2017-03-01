package org.activiti.failedJobRetry;

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
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/org/activiti/test/failedJobRetry/Beans.xml")
public class FailedJobRetryUnitTest {
	
	Logger  logger = Logger.getLogger(FailedJobRetryUnitTest.class);
	
//	@Test
	public void test() throws InterruptedException {
		
	}

}
