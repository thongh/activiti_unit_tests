package org.activiti.spring;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:Beans.xml")
public class ActivitiSpringUnitTest {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private RepositoryService repositoryService;

	Logger rootLogger = LogManager.getLogManager().getLogger("");

//	@Test
	public void startSimpleProcess() throws FileNotFoundException {

		// Set process variables
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("caseId", "");
		variableMap.put("caseName", "");
		variableMap.put("isbn", 0);
		variableMap.put("validationResult", false);
		variableMap.put("bookOrder", new BookOrder());

		// Initiate process with initial process variables
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("helloActivitiSpringProcess", variableMap);
		
		ProcessInstance processInstance2 = runtimeService
				.startProcessInstanceByKey("helloActivitiSpringProcess", variableMap);
		// runtimeService.setVariable(processInstance.getId(), "caseId",
		// "123456");
		// runtimeService.setVariable(processInstance.getId(), "caseName",
		// "Case 123456");
		// runtimeService.setVariable(processInstance.getId(), "isbn", 123);

		// When process is started, it will firstly get to node1, the init
		// service task

		// Check instance variables
		// Process execution
//		List<Execution> executions = runtimeService.createExecutionQuery()
//				.processInstanceId(processInstance.getId()).list();
//		for (Execution execution : executions) {
//			rootLogger.info("Process execution: " + " id: " + execution.getId()
//					+ " and current activity id= " + execution.getActivityId());
//			rootLogger.info("Execution variables: "
//					+ runtimeService.getVariables(execution.getId()));
//		}
		
		rootLogger.info("Variable isbn = " + runtimeService.getVariable(processInstance.getId(), "isbn"));
		rootLogger.info("Variable temp = " + runtimeService.getVariable(processInstance.getId(), "temp"));
		
		// Task
//		TaskQuery taskQuery = taskService.createTaskQuery()
//				.taskCategory("review")
//				.active()
//				.orderByTaskCreateTime()
//				.desc()
//				.includeProcessVariables();
//		rootLogger.info("Task query count: " + taskQuery.count());
//	    rootLogger.info("Task query list size: " + taskQuery.list().size());
////		Task task = taskQuery.singleResult();
//	    Task task = taskQuery.list().get(0);
//	    TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//	    rootLogger.info("Task form: " + taskFormData.getFormKey());
//	    
//	    List<FormProperty> formProperties = taskFormData.getFormProperties();
//	    for (int i = 0; i < formProperties.size(); i++) {
//	    	FormProperty formProperty = formProperties.get(i);
//	    	rootLogger.info("REVIEW FORM -- property id = " + formProperty.getId() + " value = " + formProperty.getValue());
//	    }
//	    
//	    // Completing the task continues the process which leads to calling the next user task
//	    // Submit the form and complete user task 1
//	    rootLogger.info("Submit Review");
//	    taskService.complete(task.getId());
	    
	    
	    
//	    executions = runtimeService.createExecutionQuery()
//				.processInstanceId(processInstance.getId()).list();
//		for (Execution execution : executions) {
//			rootLogger.info("Process execution: " + " id: " + execution.getId()
//					+ " and current activity id= " + execution.getActivityId());
//			rootLogger.info("Execution variables: "
//					+ runtimeService.getVariables(execution.getId()));
//			if (execution.getActivityId().equals("usertask3")) {
////				rootLogger.info("Set validation result to true");
////				runtimeService.setVariable(execution.getId(), "validationResult", true);
//			}
//		}

		
//		executions = runtimeService.createExecutionQuery()
//				.processInstanceId(processInstance.getId()).list();
//		for (Execution execution : executions) {
//			rootLogger.info("Process execution: " + " id: " + execution.getId()
//					+ " and current activity id= " + execution.getActivityId());
//			rootLogger.info("Execution variables: "
//					+ runtimeService.getVariables(execution.getId()));
//			if (execution.getActivityId().equals("usertask3")) {
//				runtimeService.setVariable(execution.getId(), "validationResult", true);
//			}
//		}
		
//		TaskQuery taskQuery2 = taskService.createTaskQuery();
////		Task task2 = taskQuery2.singleResult();
//		Task task2 = taskQuery2.list().get(0);
//	    TaskFormData taskFormData2 = formService.getTaskFormData(task2.getId());
//	    rootLogger.info("Task 2 form: " + taskFormData2.getFormKey());
//	    List<FormProperty> formProperties2 = taskFormData2.getFormProperties();
//	    for (int i = 0; i < formProperties2.size(); i++) {
//	    	FormProperty formProperty = formProperties2.get(i);
//	    	rootLogger.info("APPROVAL FORM -- property id = " + formProperty.getId() + " value = " + formProperty.getValue());
//	    }
//	    
//	    // Submit the form and complete user task 1
//	    rootLogger.info("Submit Final Approval");
//	    taskService.complete(task2.getId());
//	    
//
//		// Checking the other running process instances
//		List<ProcessInstance> instanceList = runtimeService
//				.createProcessInstanceQuery()
//				.processDefinitionKey("myProcess").list();
//		rootLogger.info("Running processes: " + instanceList.size());
//		for (ProcessInstance queryProcessInstance : instanceList) {
//			assertEquals(false, queryProcessInstance.isEnded());
//			System.out.println("id " + queryProcessInstance.getId()
//					+ ", ended=" + queryProcessInstance.isEnded());
//		}
//		
//		String barFileName = "process-one.bar";
//		ZipInputStream inputStream = new ZipInputStream(new FileInputStream(barFileName));
//
//		repositoryService.createDeployment()
//		    .name("process-one.bar")
//		    .addZipInputStream(inputStream)
//		    .deploy();
	    
	}
}
