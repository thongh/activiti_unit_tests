package org.activiti.spring;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.el.Expression;

public class CreateService implements JavaDelegate {

	Logger rootLogger = LogManager.getLogManager().getLogger("");
	
	private Expression caseId;
	private Expression caseName;
	private Expression isbn;
	
	public void execute(DelegateExecution execution) throws Exception {
		try {
			
			// Checking input mappings via Act Expression
//			rootLogger.info("Initializing process instance: " + execution.getProcessInstanceId());
//			rootLogger.info("received isbn " + isbn.getValue(execution));
//			rootLogger.info("received isbn " + execution.getVariable("isbn"));			
		    executeBusinessLogic(execution);
		    
		} catch (BpmnError e) {
		    rootLogger.log(Level.SEVERE, "There is BPMN exception here. Error is: " + e);
		    // Have to throw the BPMNError here for error intermediate event to catch
		    throw new BpmnError("isbnisblank");
		}
	}
	
	private void executeBusinessLogic(DelegateExecution execution) {
		String caseId = execution.getProcessInstanceId();
		String caseName = "Book order case " + caseId;
		int isbn = (int)(Math.random() * (1000 - 1) + 1) + 1;
		boolean validationResult = false;
		BookOrder bookOrder = new BookOrder();
		rootLogger.info("Initiate service id " + execution.getId());
		execution.setVariable("caseId", caseId);
//		rootLogger.info("Set case name to: " + caseName);
//		execution.setVariable("caseName", caseName);
//		rootLogger.info("Set isbn to: " + isbn);
//		execution.setVariable("isbn", isbn);
//		rootLogger.info("Set validation result to: " + validationResult);
//		execution.setVariable("validationResult", validationResult);
//		rootLogger.info("Set bookOrder to: " + bookOrder);		
//		execution.setVariable("bookOrder", bookOrder);
	}
	
	
}
