package org.activiti.signal;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.failedJobRetry.FailedJobRetryUnitTest;
import org.apache.log4j.Logger;

public class MyTaskCreateListener implements TaskListener {

	Logger  logger = Logger.getLogger(MyTaskCreateListener.class);
	
	public void notify(DelegateTask delegateTask) {
		// Custom logic goes here
		logger.info("User task is created.");
		logger.info("Listener executed.");	
		logger.info("Delegate Task assignee: " + delegateTask.getAssignee());		
		DelegateExecution execution = delegateTask.getExecution();
		RuntimeService runtimeService = execution.getEngineServices().getRuntimeService();	
		runtimeService.signalEventReceived("EMAIL_SUPPORT_SIGNAL");
	}
}
