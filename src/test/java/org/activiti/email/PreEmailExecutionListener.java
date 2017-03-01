package org.activiti.email;

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

public class PreEmailExecutionListener implements ExecutionListener {
	Logger logger = Logger.getLogger(PreEmailExecutionListener.class);

	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Pre email execution listener: "
				+ execution.getId());
		String xml = getProcessDefXml(execution);
		logger.info(xml);
	}
	
	private String getProcessDefXml(DelegateExecution execution) {
		RepositoryService repositoryService = execution.getEngineServices().getRepositoryService();
		String deploymentId = repositoryService.createDeploymentQuery().singleResult().getId();
		List<String> deploymentResources = repositoryService.getDeploymentResourceNames(deploymentId);
		String bpmnResourceName = "org/activiti/test/email/EmailProcess.bpmn";
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
		ReadOnlyProcessDefinition readOnlyProcessDefinition = ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinition.getId());
		InputStream deploymentInputStream = repositoryService.getResourceAsStream(deploymentId, bpmnResourceName);
		byte[] bytes = IoUtil.readInputStream(deploymentInputStream, "input stream");
		String contentFromFile = new String(bytes);
		return contentFromFile;
	}
}
