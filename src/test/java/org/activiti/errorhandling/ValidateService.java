package org.activiti.errorhandling;

import org.apache.log4j.Logger;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ValidateService implements JavaDelegate {

	Logger logger = Logger.getLogger(ValidateService.class);

	public void execute(DelegateExecution execution) throws Exception {
		try {
			executeBusinessLogic(execution);
		} catch (BpmnError e) {
			logger.error("There is BPMN exception here. Error is: " + e);
			// Have to throw the BPMNError here for error intermediate event to
			// catch
			throw new BpmnError("isbnerror");
		}
	}

	private void executeBusinessLogic(DelegateExecution execution) {
		logger.info("Execute validate service task for execution id "
				+ execution.getId());
		String isbn = (String) execution.getVariable("isbn");
		logger.info("received isbn " + isbn);
		if (isbn.equals("abc")) {
			throw new BpmnError("isbnerror");
		}
	}

}
