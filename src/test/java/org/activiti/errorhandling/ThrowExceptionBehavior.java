package org.activiti.errorhandling;

import java.util.logging.LogManager;

import java.util.logging.Logger;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;


public class ThrowExceptionBehavior implements ActivityBehavior {
	Logger rootLogger = LogManager.getLogManager().getLogger("");

	public void execute(ActivityExecution execution) throws Exception {
//		String var = (String) execution.getVariable("var");

		PvmTransition transition = null;
		try {
			executeBusinessLogic(execution);
			transition = execution.getActivity().findOutgoingTransition(
					"no-exception");
		} catch (BpmnError e) {
			transition = execution.getActivity().findOutgoingTransition(
					"exception");
		}
		execution.take(transition);
	}

	private void executeBusinessLogic(DelegateExecution execution) {
		rootLogger.info("Execute validate service task for execution id "
				+ execution.getId());
		String isbn = (String) execution.getVariable("isbn");
		rootLogger.info("received isbn " + isbn);
		if (isbn.length() < 1) {
			throw new BpmnError("isbn cannot be blank. We have a BPMN error");
		}
	}
}
