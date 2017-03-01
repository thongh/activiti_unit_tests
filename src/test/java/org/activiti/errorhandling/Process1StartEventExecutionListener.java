package org.activiti.errorhandling;

import java.io.InputStream;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.signal.MyExecutionListener;
import org.apache.log4j.Logger;

public class Process1StartEventExecutionListener implements ExecutionListener {
	Logger logger = Logger.getLogger(Process1StartEventExecutionListener.class);

	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Process 1 started. This is a start listener being triggered: "
				+ execution.getId());
		logger.info("Setting variable 'isbn' to 'abc' to delibarately cause exception");
		execution.getEngineServices().getRuntimeService().setVariable(execution.getId(), "isbn", "abc");
	}
	
}
