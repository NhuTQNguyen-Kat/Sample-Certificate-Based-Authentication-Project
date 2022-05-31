import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


WebUI.switchToWindowTitle('badssl.com')

WebUI.click(findTestObject('Object Repository/Page_badssl.com/a_Certificate Downloads'))

//WebUI.click(findTestObject('Object Repository/Page_Certificate Downloads  badssl.com/a_badssl.com-client.p12'))

String downloadLocation = "${System.getProperty("user.home")}/Downloads/"
String home = "${System.getProperty("user.home")}"
 
def cmd_Install_MacOS = "security import " + downloadLocation + "badssl.com-client.p12 -P badssl.com -A"
def cmd_Install_Linux = "pk12util -d sql:" + home + "/.pki/nssdb -i " + downloadLocation + "badssl.com-client.p12 -W badssl.com"

CustomKeywords.'runCmd.cmd.execute'(cmd_Install_Linux)

//def cmd_Select = "defaults write com.google.Chrome AutoSelectCertificateForUrls -array-add -string '{'pattern':'https://[*.]https://client.badssl.com/','filter':{'ISSUER':{'CN':'BadSSL Client Root Certificate Authority'}}}'"
//CustomKeywords.'runCmd.cmd.execute'(cmd_Select)


