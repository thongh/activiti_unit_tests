package org.activiti.rest;

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

public class RestAPIUnitTest {

	Logger logger = Logger.getLogger(RestAPIUnitTest.class);

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

//	@Test
	@Deployment(resources = { "org/activiti/test/REST/CompleteTask.bpmn" })
	public void test() throws InterruptedException {

		RuntimeService runtimeService = activitiRule.getRuntimeService();
		TaskService taskService = activitiRule.getTaskService();

		logger.info("------------------------- START -------------------------");
		ProcessInstance processInstance2 = runtimeService
				.startProcessInstanceByKey("completeTaskProcess");

		// Start calling the REST API

		// Get deployments
		String getDeploymentsREST = "http://localhost:8080/activiti-rest/service/repository/deployments";
		String completeTaskREST = "";
		try {
			URL url = new URL(getDeploymentsREST);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("kermit", "kermit"
							.toCharArray());
				}
			});

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			logger.info("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				logger.info(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

		// Complete user task 1
		Task task = taskService.createTaskQuery().singleResult();
		logger.info("Complete user task id " + task.getId() + " - "
				+ task.getName());
		taskService.claim(task.getId(), "kermit");
		taskService.complete(task.getId());
		Thread.sleep(5000);
		logger.info("Instance completed? " + processInstance2.isEnded());

	}
}
