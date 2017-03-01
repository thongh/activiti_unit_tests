package org.activiti.email;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class CheckEmailErrorServiceTask implements JavaDelegate {

	Logger logger = Logger.getLogger(CheckEmailErrorServiceTask.class);
	
	public void execute(DelegateExecution execution) throws Exception {
//		String var = (String) execution.getVariable("input");
//		var = var.toUpperCase();
//		execution.setVariable("input", var);
		
		RuntimeService runtimeService = execution.getEngineServices().getRuntimeService();
		
		logger.info("Check email service executed. Process: " + execution.getProcessDefinitionId());
	}
}
