package org.activiti.email;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;

public class PreEmailListener implements ActivityBehavior {

	Logger logger = Logger.getLogger(PreEmailListener.class);
	
	public void execute(ActivityExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String var = (String) execution.getVariable("var");

		PvmTransition transition = null;
		try {
			logger.info("Pre email listener executed.");
			transition = execution.getActivity().findOutgoingTransition(
					"no-exception");
		} catch (Exception e) {
			transition = execution.getActivity().findOutgoingTransition(
					"exception");
		}
		execution.take(transition);
	}

}
