package certFunc
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.configuration.RunConfiguration


class cert {

	/**
	 * Install Certification
	 */
	@Keyword
	def installCert(String password) {
		
		String projectLocation = RunConfiguration.getProjectDir()
		String certFileLocation = projectLocation + "/CertFile/"
		String home = "${System.getProperty("user.home")}"

		def cmd_Install_MacOS = "security import " + certFileLocation + "badssl.com-client.p12 -P " + password + " -A"
		def cmd_Install_Linux = "pk12util -d sql:" + home + "/.pki/nssdb -i " + certFileLocation + "badssl.com-client.p12 -W " + password

		KeywordUtil.logInfo("Log for cmd Install Linux: "+ cmd_Install_Linux)
		KeywordUtil.logInfo("Install certificate")

		try {
			String out = executeCommand(cmd_Install_Linux);
			KeywordUtil.logInfo("Output: " + out)
			KeywordUtil.markPassed("Install certificate successfully")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to install certificate")
		}
	}

	/**
	 * Delete Certification
	 */
	@Keyword
	def deleteCert(String commonName, String password) {
		
		String home = "${System.getProperty("user.home")}"

		def cmd_Remove_MacOS = "security delete-certificate -c 'BadSSL Client Certificate'"
		def cmd_Remove_Linux = "certutil -d sql:" + home + "/.pki/nssdb -D -n '" + commonName +"' -w " + password

		KeywordUtil.logInfo("Log for cmd Remove Linux: "+ cmd_Remove_Linux)
		KeywordUtil.logInfo("Delete certificate")

		try {
			String out = executeCommand(cmd_Remove_Linux);
			KeywordUtil.logInfo("Output: " + out)
			KeywordUtil.markPassed("Delete certificate successfully")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to delete certificate")
		}
	}


	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			String[] cmd = ["bash", "-c", command]
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
			//KeywordUtil.logInfo("log: "+ p.getErr().text)
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();
	}
}
