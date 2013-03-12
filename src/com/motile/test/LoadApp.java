package com.motile.test;

import java.util.ArrayList;

import junit.*;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.webkit.WebView;

import com.jayway.android.robotium.solo.By;
import com.jayway.android.robotium.solo.Condition;
import com.jayway.android.robotium.solo.Solo;
import com.jayway.android.robotium.solo.WebElement;

public class LoadApp extends ActivityInstrumentationTestCase2 implements Condition{
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME ="com.motile.MainActivity";  
	private static Class<?> launcherActivityClass;	
	
	static{	
		try	
		  {	
	  	   launcherActivityClass=Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);	
		  }catch(ClassNotFoundException	e){ 
		  throw	new RuntimeException(e);
			}	
	     }	

	@SuppressWarnings("unchecked")
	public LoadApp() throws ClassNotFoundException
		{	
		super(launcherActivityClass);
		}

	public static Solo solo;
	
	protected void setUp() throws Exception	{	
	  Log.i(Constants.vTag, "Starting test case");
	  solo = new Solo(getInstrumentation(), getActivity());		  
	}	

	public void test1LoadWebView() throws ClassNotFoundException
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.takeScreenshot();		
		if(solo.searchButton("Webview", true))
			solo.clickOnButton("Webview");
		solo.sleep(3000);
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
	
		solo.sleep(1000);		
		//Use of - waitForWebElement(By by)
		solo.waitForWebElement(By.id("Email"));
		if(solo.searchText("Username", true))
		{
			GetAllWebElements();
			//Use of - enterTextInWebElement(By by, String text)
			solo.enterTextInWebElement(By.name("Email"), "qaperson49@hotmail");
			solo.sleep(1000);	
			//Use of - clearTextInWebElement(By by)
			solo.clearTextInWebElement(By.name("Email"));
			solo.sleep(1000);
			solo.enterTextInWebElement(By.name("Email"), "qaperson49@gmail.com");
			solo.sleep(1000);
			//Use of - typeTextInWebElement(By by, String text)
			solo.typeTextInWebElement(By.name("Passwd"), "Qaperson123");	
			solo.sleep(1000);			
			solo.clickOnWebElement(By.id("PersistentCookie"));
			// Use of - getWebElement(By by, int index)
			//WebElement vCheckbox = solo.getWebElement(By.id("PersistentCookie"), 0);
			//Use of -clickOnWebElement(WebElement webElement)
			//solo.clickOnWebElement(vCheckbox);
			solo.sleep(1000);	
			// Use of -clickOnWebElement(By by) 
			solo.clickOnWebElement(By.id("signIn"), 1, true);
			solo.sleep(20000);
		}
		else Log.i(Constants.vTag, "Sing in screen not loaded");

		if(solo.searchText("qaperson49@gmail.com", 0, true, true))
		{
			Log.i(Constants.vTag, "Successful logged in.");
			GetAllWebElements();
			solo.clickOnText("Gmail Team");
			solo.sleep(10000);
			GetAllWebElements();
			solo.clickOnText("Show details");
			solo.takeScreenshot("LoggedIn", 50);
		}
		else Log.i(Constants.vTag, "Not logged in.");
		solo.sleep(20000);
		
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());	
	}
	
	public void test2LoadCustomHTMLWebView() throws ClassNotFoundException
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		//Use of waitForCondition(Condition condition, final int timeout) 
		
		solo.waitForCondition(this, Constants.vMinTimeout);
		
		
			solo.clickOnButton("Custom HTML");
		solo.sleep(3000);
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		
		if(!solo.searchText("Robotium", true))
			Log.i(Constants.vTag, "not available");
		else Log.i(Constants.vTag, "available");
		GetAllWebElements();
	}
	
	public void GetAllWebElements()
	{
		ArrayList<WebElement> vWebView =  solo.getCurrentWebElements();
			for(int i=0; i<vWebView.size(); i++)
			{
				Log.i(Constants.vTag, "==="+i+"===ID:"+vWebView.get(i).getId()+
							 " ClassName: "+vWebView.get(i).getClassName()+
							 " Xlocation: "+vWebView.get(i).getLocationX()+
							 " YLocation: "+vWebView.get(i).getLocationY()+
							 " Name: "+vWebView.get(i).getName()+
							 " TagName: "+vWebView.get(i).getTagName()+
							 " Text: "+vWebView.get(i).getText()+
							 " Class: "+vWebView.get(i).getClass());	
			}
	}
	
	/*---------------To verify---------------------------------------
	- waitForCondition(Condition condition, final int timeout) ========================================
	- waitForWebElement(By by) //Example: waitForWebElement(By.id("id")); =============================
	- waitForWebElement(By by, int timeout, boolean scroll) 
	- waitForWebElement(By by, int match, int timeout, boolean scroll) 
	- clickOnWebElement(WebElement webElement)
	- clickOnWebElement(By by) 	=======================================================================
	- clickOnWebElement(By by, int match) 	
	- clickOnWebElement(By by, int match, boolean scroll) 	
	- enterTextInWebElement(By by, String text)======================================================== 	
	- typeTextInWebElement(By by, String text)========================================================= 	
	- typeTextInWebElement(By by, String text, int match) 	
	- typeTextInWebElement(WebElement webElement, String text)=========================================
	- clearTextInWebElement(By by) ====================================================================	
	- getWebElement(By by, int index) 
	- getCurrentViews(Class<T> classToFilterBy) 	
	- getCurrentViews(Class<T> classToFilterBy, View parent) 	
	- takeScreenshot(String name, int quality)========================================================= 
New class RobotiumUtils: 
	- removeInvisibleViews(Iterable<T> viewList) 
	- filterViews(Class<T> classToFilterBy, Iterable<?> viewList) 
	- filterViewsToSet(Class<View> classSet[], Iterable<View> viewList) 
	- sortViewsByLocationOnScreen(List<? extends View> views) 
	- sortViewsByLocationOnScreen(List<? extends View> views, boolean 
	yAxisFirst) 
	- getNumberOfMatches(String regex, View view, Set<View> 
	uniqueTextViews) 
Methods removed: 
	- getAllOpenedActivities() 
	- finishInactiveActivities() 
	- getCurrentXViews //replaced by getCurrentViews(Class<T> 
	classToFilterBy). Example: getCurrentViews(ListView.class) 
	 *
	 */	
	public void tearDown() throws Exception	
	{	
		Log.i(Constants.vTag, "Closing test case");
		solo.finishOpenedActivities();
	}

	public boolean isSatisfied() {
		Log.i(Constants.vTag, "Verifying condition");
		if(solo.searchButton("Custom HTML", true)) {Log.i(Constants.vTag, "Condition Passed"); return true;}
		else {Log.i(Constants.vTag, "Condition Filed."); return false;}
	}
}