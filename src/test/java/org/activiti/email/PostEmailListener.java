package org.activiti.email;

import org.activiti.engine.RuntimeService;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;

public class PostEmailListener implements ActivityBehavior {

	Logger logger = Logger.getLogger(PostEmailListener.class);

	public void execute(ActivityExecution execution) throws Exception {
//		String var = (String) execution.getVariable("var");

		PvmTransition transition = null;
		try {
			executeBusinessLogic(execution);
			transition = execution.getActivity().findOutgoingTransition(
					"no-exception");
		} catch (Exception e) {
			logger.info("Post mail listener exception: " + e.getMessage());
			transition = execution.getActivity().findOutgoingTransition(
					"exception");
		}
		execution.take(transition);
	}

	private void executeBusinessLogic(ActivityExecution execution) {
		RuntimeService runtimeService = execution.getEngineServices()
				.getRuntimeService();

		logger.info("Post email listener executed.");
	}
}
