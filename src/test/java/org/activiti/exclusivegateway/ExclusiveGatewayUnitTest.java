package org.activiti.exclusivegateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExclusiveGatewayUnitTest {

	Logger logger = Logger.getLogger(ExclusiveGatewayUnitTest.class);

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	@Deployment(resources = {"org/activiti/test/exclusivegateway/ExclusiveGateway.bpmn"})
	public void test() throws InterruptedException {
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		IdentityService identityService = activitiRule.getIdentityService();
		TaskService taskService = activitiRule.getTaskService();
		
		logger.info("------------------------- START -------------------------");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("totalParticipants", 1);
		variables.put("input", 15);
		variables.put("total", 0);
		ProcessInstance processInstance2 = runtimeService.startProcessInstanceByKey("exclusiveGatewayProcess", variables);
		
		// Setup groups for unit testing
		// Group 1
		logger.info("Setting up groups...");
		Group group1 = identityService.newGroup("group1");
		group1.setName("Group 1");
		identityService.saveGroup(group1);
		Group group2 = identityService.newGroup("group2");
		group2.setName("Group 2");
		identityService.saveGroup(group2);
		for (int i = 0; i < 15; i++) {
			User user = identityService.newUser("user" + i);
			user.setEmail("employee@gmail.com");
			user.setFirstName("employee");
			user.setLastName("User LastName " + i);
			user.setPassword("123");
			identityService.saveUser(user);
			if (i < 10) {
				if (identityService.createGroupQuery().groupId(group1.getId()).list().size() > 0) {
					logger.info("Insert user " + user.getId() + " into group " + identityService.createGroupQuery().groupId(group1.getId()).list().get(0).getId());
					identityService.createMembership(user.getId(), group1.getId());		
				}
			} else {	
				if (identityService.createGroupQuery().groupId(group2.getId()).list().size() > 0) {
					logger.info("Insert user " + user.getId() + " into group " + identityService.createGroupQuery().groupId(group2.getId()).list().get(0).getId());
					identityService.createMembership(user.getId(), group2.getId());		
				}
			}				
		}	
		logger.info("Setting up groups - DONE");
		logger.info("Number of groups: " + identityService.createGroupQuery().list().size());
		logger.info("Group 1 size: " + identityService.createUserQuery().memberOfGroup(group1.getId()).list().size());
		logger.info("Group 2 size: " + identityService.createUserQuery().memberOfGroup(group2.getId()).list().size());
		int totalParticipants = identityService.createUserQuery().memberOfGroup(group1.getId()).list().size() 
				+ identityService.createUserQuery().memberOfGroup(group2.getId()).list().size();
		
		logger.info("Set total particiapnts from both user tasks to variable 'totalParticipants'");
		runtimeService.setVariable(processInstance2.getId(), "totalParticipants", totalParticipants);
		logger.info("Process var 'totalParticipants' now has value: " + runtimeService.getVariable(processInstance2.getId(), "totalParticipants"));
		logger.info("Process var 'input' now has value: " + runtimeService.getVariable(processInstance2.getId(), "input"));
		logger.info("Process var 'total' now has value: " + runtimeService.getVariable(processInstance2.getId(), "total"));
		
		
		logger.info("Open tasks: " + taskService.createTaskQuery().list().size());
        logger.info("Task: " + taskService.createTaskQuery().list().get(0).getId() + " - " + taskService.createTaskQuery().list().get(0).getName());
		logger.info("Instance completed? " + processInstance2.isEnded());
        
	}
}
