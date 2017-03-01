package org.activiti.errorhandling;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class CatchErrorServiceTask implements JavaDelegate {

	Logger logger = Logger.getLogger(CatchErrorServiceTask.class);
	
	public void execute(DelegateExecution execution) throws Exception {
//		String var = (String) execution.getVariable("input");
//		var = var.toUpperCase();
//		execution.setVariable("input", var);
		logger.info("Catch Error Service Task started for execution id : " + execution.getId());
		
//		RuntimeService runtimeService = execution.getEngineServices().getRuntimeService();
//		runtimeService.signalEventReceived("Email Signal");
	}

}
