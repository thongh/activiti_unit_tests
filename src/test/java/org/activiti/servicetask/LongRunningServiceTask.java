package org.activiti.servicetask;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

public class LongRunningServiceTask implements JavaDelegate {

	Logger logger = Logger.getLogger(LongRunningServiceTask.class);
	
	private Expression temp;
	
	public void execute(DelegateExecution execution) throws Exception {

		//logger.info("Long running service task started, execution id : " + execution.getId());
		//Thread.sleep(10000);	// stop execution for 10 secs to example a long running task
		//logger.info("Long running task completed");
	
		// Test
		logger.info("ServiceTask test field injection");
		execution.setVariable("isbn", ((String)temp.getValue(execution)).toUpperCase());
	}

}
