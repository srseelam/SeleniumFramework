package testCases;

import static framework.BDDActions.and;
import static framework.BDDActions.given;
import static framework.BDDActions.then;
import static framework.BDDActions.when;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testData.TestDataReader;
import framework.TestBase;

public class TestCaseForHomePage extends TestBase {

	@Override
	protected void goToUrl() {
	}

	@Test(priority = 1)
	public void verifyHomePage() {
		String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
		TestDataReader getTestData = new TestDataReader(methodName);
		System.out.println("Partnumber= " + getTestData.getPartNumner1());
		given(user).opensHomePage();
		given(user).searchForPartNumner(getTestData.getPartNumner1());
		and(user).clickOnFirstItemInSLP();
		and(user).clickOnAddToCartButton();
		and(user).clickOnViewCartOnOverlay();
	}
}