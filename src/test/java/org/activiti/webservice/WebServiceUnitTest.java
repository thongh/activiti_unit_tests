package org.activiti.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
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

public class WebServiceUnitTest {

	Logger logger = Logger.getLogger(WebServiceUnitTest.class);

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	@Deployment(resources = { "org/activiti/test/webservice/WebServiceViaJavaProcess.bpmn" })
	public void test() throws InterruptedException {

		String processdefId = "webserviceJavaProcess";
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		TaskService taskService = activitiRule.getTaskService();

		logger.info("------------------------- START -------------------------");
		ProcessInstance processInstance2 = runtimeService
				.startProcessInstanceByKey(processdefId);

		logger.info("Open tasks: " + taskService.createTaskQuery().list().size());
        logger.info("Task: " + taskService.createTaskQuery().list().get(0).getId() + " - " + taskService.createTaskQuery().list().get(0).getName());
		logger.info("Instance completed? " + processInstance2.isEnded());

	}
	
	private void callWebService(String wsdlurl) {
		
		// Call webservice via third party library
		
	}
}
