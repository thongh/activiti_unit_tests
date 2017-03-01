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

public class CallActivityExecutionListener implements ExecutionListener {
	Logger logger = Logger.getLogger(CallActivityExecutionListener.class);

	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Call activity execution listener triggered for execution id: "
				+ execution.getId());
		String xml = getProcessDefXml(execution);
		logger.info(xml);
	}
	
	private String getProcessDefXml(DelegateExecution execution) {
		RepositoryService repositoryService = execution.getEngineServices().getRepositoryService();
		String deploymentId = repositoryService.createDeploymentQuery().list().get(0).getId();
		List<String> deploymentResources = repositoryService.getDeploymentResourceNames(deploymentId);
		String bpmnResourceName = "org/activiti/test/errorhandling/Process1.bpmn";
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().list().get(0);
		ReadOnlyProcessDefinition readOnlyProcessDefinition = ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinition.getId());
		InputStream deploymentInputStream = repositoryService.getResourceAsStream(deploymentId, bpmnResourceName);
		byte[] bytes = IoUtil.readInputStream(deploymentInputStream, "input stream");
		String contentFromFile = new String(bytes);
		return contentFromFile;
	}
}
