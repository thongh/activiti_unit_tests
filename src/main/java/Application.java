import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

public class Application {

	public static void main(String[] args) {
//		ProcessEngineConfiguration cfg = (StandaloneProcessEngineConfiguration) ProcessEngineConfiguration
//				.createStandaloneProcessEngineConfiguration();
		// ProcessEngineConfiguration cfg = new
		// StandaloneProcessEngineConfiguration()
//		cfg.setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000");
//		cfg.setJdbcUsername("sa");
//		cfg.setJdbcPassword("");
//		cfg.setJdbcDriver("org.h2.Driver");
//		cfg.setDatabaseSchemaUpdate("true");
		System.out.println("Starting");
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
				.setJdbcUsername("sa")
				.setJdbcPassword("")
				.setJdbcDriver("org.h2.Driver")
				.setDatabaseSchemaUpdate(
						ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		ProcessEngine processEngine = cfg.buildProcessEngine();
		String pName = processEngine.getName();
		String ver = processEngine.VERSION;
		System.out.println("Completing");
		System.out.println("ProcessEngine [" + pName + "] Version: [" + ver
				+ "]");

	}

}
