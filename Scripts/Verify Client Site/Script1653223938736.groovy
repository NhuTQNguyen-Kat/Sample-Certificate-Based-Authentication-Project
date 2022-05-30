import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling

'Set up'
@SetUp()
def setUp( ) {
	'Install cert'
	WebUI.callTestCase(findTestCase('Set up Authentication'), FailureHandling.STOP_ON_FAILURE)
}

WebUI.click(findTestObject('Object Repository/Page_badssl.com/a_client'))

'Tear down'
@TearDown()
def tearDown() {
	'Delete cert'
	WebUI.callTestCase(findTestCase('Delete Authentication'))
}

