package org.activiti.exclusivegateway;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.log4j.Logger;

public class InitServiceTask implements JavaDelegate {

	Logger logger = Logger.getLogger(InitServiceTask.class);

	public void execute(DelegateExecution execution) throws Exception {

		logger.info("Init service task execution : " + execution.getId());

		// Setup groups for unit testing
		// Group 1
//		
//		IdentityService identityService = execution.getEngineServices().getIdentityService();
//		RuntimeService runtimeService = execution.getEngineServices().getRuntimeService();
//		
//		logger.info("Setting up groups...");
//		Group group1 = identityService.newGroup("group1");
//		group1.setName("Group 1");
//		identityService.saveGroup(group1);
//		Group group2 = identityService.newGroup("group2");
//		group2.setName("Group 2");
//		identityService.saveGroup(group2);
//		logger.info(identityService.createGroupQuery().groupId(group1.getId()).list().size());
//		for (int j = 0; j < 15; j++) {
//			logger.info("Creating user " + j);
//			User user = identityService.newUser("user" + j);
//			user.setEmail("employee@gmail.com");
//			user.setFirstName("employee");
//			user.setLastName("User LastName " + j);
//			user.setPassword("123");
//			identityService.saveUser(user);
//			if (j < 10) {
//				if (identityService.createGroupQuery().groupId(group1.getId())
//						.list().size() > 0) {
//					logger.info("Insert user "
//							+ user.getId()
//							+ " into group "
//							+ identityService.createGroupQuery()
//									.groupId(group1.getId()).list().get(0)
//									.getId());
//					identityService.createMembership(user.getId(),
//							group1.getId());
//				}
//			} else {
//				if (identityService.createGroupQuery().groupId(group2.getId())
//						.list().size() > 0) {
//					logger.info("Insert user "
//							+ user.getId()
//							+ " into group "
//							+ identityService.createGroupQuery()
//									.groupId(group2.getId()).list().get(0)
//									.getId());
//					identityService.createMembership(user.getId(),
//							group2.getId());
//				}
//			}
//		}
//		logger.info("Setting up groups - DONE");
//		logger.info("Number of groups: "
//				+ identityService.createGroupQuery().list().size());
//		logger.info("Group 1 size: "
//				+ identityService.createUserQuery()
//						.memberOfGroup(group1.getId()).list().size());
//		logger.info("Group 2 size: "
//				+ identityService.createUserQuery()
//						.memberOfGroup(group2.getId()).list().size());
//		int totalParticipants = identityService.createUserQuery()
//				.memberOfGroup(group1.getId()).list().size()
//				+ identityService.createUserQuery()
//						.memberOfGroup(group2.getId()).list().size();
//
//		logger.info("Set total particiapnts from both user tasks to variable 'totalParticipants'");
//		runtimeService.setVariable(execution.getId(),
//				"totalParticipants", totalParticipants);
//		
//		logger.info("Process var 'totalParticipants' now has value: " + runtimeService.getVariable(execution.getId(), "totalParticipants"));
//		logger.info("Process var 'input' now has value: " + runtimeService.getVariable(execution.getId(), "input"));
//		logger.info("Process var 'total' now has value: " + runtimeService.getVariable(execution.getId(), "total"));

	}

}
