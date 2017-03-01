package org.activiti.migration;

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

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cmd.SetProcessDefinitionVersionCmd;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class InstancesMigrationUnitTest {

	Logger logger = Logger.getLogger(InstancesMigrationUnitTest.class);

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

//	@Test
	@Deployment(resources = { "org/activiti/test/migration/MigrationTestProcess.bpmn" })
	public void test() throws InterruptedException {

		RuntimeService runtimeService = activitiRule.getRuntimeService();
		TaskService taskService = activitiRule.getTaskService();
		RepositoryService repositoryService = activitiRule
				.getRepositoryService();

		// Start an instance with current process def
		logger.info("------------------------- START PROCESS -------------------------");
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("migrationTestProcess");

		// check that receive task has been reached
		logger.info("------------------------- CHECK CURRENT WAIT STATE -------------------------");
		List<Execution> executions = runtimeService.createExecutionQuery()
				.processInstanceId(processInstance.getId()).list();
		for (int i = 0; i < executions.size(); i++) {
			logger.info("Execution: " + executions.get(i).getName() + " - "
					+ executions.get(i).getId());
		}
		Execution execution = runtimeService.createExecutionQuery()
				.processInstanceId(processInstance.getId())
				.activityId("receivetask1").singleResult();
		logger.info("Received task: " + execution.getActivityId());
		logger.info("Open tasks: " + taskService.createTaskQuery().count());

		// deploy new version of the process definition
		org.activiti.engine.repository.Deployment deployment = repositoryService
				.createDeployment()
				.addClasspathResource(
						"org/activiti/test/migration/MigrationTestProcessv2.bpmn")
				.deploy();
		logger.info("Process def deployed: "
				+ repositoryService.createProcessDefinitionQuery().count());
		assertEquals(2, repositoryService.createProcessDefinitionQuery()
				.count());

		// migrate process instance to new process definition version
		CommandExecutor commandExecutor = ((ProcessEngineImpl) activitiRule
				.getProcessEngine()).getProcessEngineConfiguration()
				.getCommandExecutor();
		commandExecutor.execute(new SetProcessDefinitionVersionCmd(
				processInstance.getId(), 2));

		// signal process instance
		runtimeService.signal(execution.getId());

		// check that the instance now uses the new process definition version
		ProcessDefinition newProcessDefinition = repositoryService
				.createProcessDefinitionQuery().processDefinitionVersion(2)
				.singleResult();
		logger.info("New process def: " + newProcessDefinition.getName()
				+ " version: " + newProcessDefinition.getVersion());
		processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstance.getId()).singleResult();
		assertEquals(newProcessDefinition.getId(),
				processInstance.getProcessDefinitionId());
		logger.info("New process def id: " + newProcessDefinition.getId());
		logger.info("Process instance id and its def id : "
				+ processInstance.getId() + " - "
				+ processInstance.getProcessDefinitionId());

		// check history
		if (((ProcessEngineImpl) activitiRule
				.getProcessEngine()).getProcessEngineConfiguration()
				.getHistoryLevel().isAtLeast(
				HistoryLevel.ACTIVITY)) {
			HistoricProcessInstance historicPI = activitiRule.getHistoryService()
					.createHistoricProcessInstanceQuery()
					.processInstanceId(processInstance.getId()).singleResult();
			assertEquals(newProcessDefinition.getId(),
					historicPI.getProcessDefinitionId());
		}

		// undeploy "manually" deployed process definition
		repositoryService.deleteDeployment(deployment.getId(), true);
	}
}
