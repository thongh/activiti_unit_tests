package org.activiti.signal;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.pvm.delegate.ExecutionListenerExecution;
import org.apache.log4j.Logger;

public class MyExecutionListener implements ExecutionListener {

	Logger  logger = Logger.getLogger(MyExecutionListener.class);
	
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Execution, task assignee: " + 
		execution.getEngineServices()
			.getTaskService().createTaskQuery()
			.processInstanceId(execution.getProcessInstanceId()).singleResult().getAssignee());
	}

}
