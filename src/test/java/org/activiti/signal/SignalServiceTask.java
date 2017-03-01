package org.activiti.signal;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class SignalServiceTask implements JavaDelegate {

	Logger logger = Logger.getLogger(SignalServiceTask.class);
	
	public void execute(DelegateExecution execution) throws Exception {
//		String var = (String) execution.getVariable("input");
//		var = var.toUpperCase();
//		execution.setVariable("input", var);
		logger.info("SingalServiceTask execution : " + execution.getId());
		
//		RuntimeService runtimeService = execution.getEngineServices().getRuntimeService();
//		runtimeService.signalEventReceived("Email Signal");
		
		logger.info("Try to get all participants of the process instances");
	}

}
