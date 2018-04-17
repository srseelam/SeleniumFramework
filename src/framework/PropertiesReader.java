package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	private Properties prop = new Properties();

	public PropertiesReader() {
		try {
			final String externallySuppliedPropertiesFile = System
					.getProperty("properties");
			String defaultPropertiesFile = "";
			if (ValidateOS.isWindows()) {
				defaultPropertiesFile = "properties\\local.properties";
			} else if (ValidateOS.isMac()) {
				defaultPropertiesFile = "properties/local.properties";
			}

			String propertiesFilePath = defaultPropertiesFile; // default path
			if (exists(externallySuppliedPropertiesFile)) {
				propertiesFilePath = "properties\\"
						+ externallySuppliedPropertiesFile;
			}
			prop.load(new FileInputStream(propertiesFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean exists(final String externallySuppliedPropertiesFile) {
		return externallySuppliedPropertiesFile != null
				&& new File("properties\\" + externallySuppliedPropertiesFile)
						.exists();
	}

	public String getDriverName() {
		return prop.getProperty("driver");
	}

	public String getRemoteGirdURL() {
		if (prop.containsKey("remoteGridURL"))
			return prop.getProperty("remoteGridURL");
		else
			return "";
	}

	public String getUrl() {
		String url = prop.getProperty("url");
		return url;
	}

	public String getSecureUrl() {
		return prop.getProperty("secureUrl");
	}

	public String getWorkStream() {
		String workStream = prop.getProperty("workStream");
		return workStream;

	}

	public String getTestDataLocation() {
		String testDataFileName = prop.getProperty("testData");
		return testDataFileName;

	}

	public String getEnvironment() {
		String testEnv = prop.getProperty("env");
		return testEnv;
	}

	public String getTeam() {
		String testEnv = prop.getProperty("team");
		return testEnv;
	}

	public String getFFPrgFlag() {
		String testFFPrgFlag = prop.getProperty("FFPrgFlag");
		return testFFPrgFlag;
	}

	public String getScene7Flag() {
		String testScene7Flag = prop.getProperty("Scene7Flag");
		return testScene7Flag;
	}

	public String getDBUrl() {
		return prop.getProperty("dbConnectionUrl");
	}

	public boolean shouldCloseBrowser() {
		String closeBrowser = prop.getProperty("closeBrowser");
		// Default to closing the browser even when the property is not defined.
		// If defined, it should match 'yes' or 'true'
		return null == closeBrowser || Boolean.parseBoolean(closeBrowser);
	}

	public String getITADownUrl() {
		return prop.getProperty("itaDownUrl");
	}

	public String getITAInduceQuantityUrl() {
		return prop.getProperty("itaInduceQuantityUrl");
	}

	public String getSchemaName() {
		String schemaName = prop.getProperty("schemaName");
		return schemaName;
	}

	public String getBloomReachUrl() {
		return prop.getProperty("bloomReachUrl");

	}

	public String getInventoryCluster() {
		return prop.getProperty("inventory_util");
	}
}
