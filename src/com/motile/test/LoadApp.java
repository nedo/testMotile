package com.motile.test;

import java.util.ArrayList;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.jayway.android.robotium.solo.By;
import com.jayway.android.robotium.solo.Condition;
import com.jayway.android.robotium.solo.Solo;

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

	public void testWebView() throws ClassNotFoundException
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Webview");
		solo.assertCurrentActivity("Screen not loaded.", Constants.WEB_VIEW_ACTIVITY);
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.takeScreenshot();		
		if(solo.searchButton("Webview", true))
			solo.clickOnButton("Webview");
		//solo.sleep(3000);
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		
		//Use of - waitForWebElement(By by)
		solo.waitForWebElement(By.id("Email"));
		if(solo.searchText("Username", true))
		{
			Global.getAllWebElements();
			//Use of - enterTextInWebElement(By by, String text)
			solo.enterTextInWebElement(By.name("Email"), "qaperson49@hotmail");
			//Use of - clearTextInWebElement(By by)
			solo.clearTextInWebElement(By.name("Email"));
			solo.enterTextInWebElement(By.name("Email"), "qaperson49@gmail.com");
			//Use of - typeTextInWebElement(By by, String text)
			solo.typeTextInWebElement(By.name("Passwd"), "Qaperson123");	
			solo.clickOnWebElement(By.id("PersistentCookie"));
			// Use of - getWebElement(By by, int index)
			//WebElement vCheckbox = solo.getWebElement(By.id("PersistentCookie"), 0);
			//Use of -clickOnWebElement(WebElement webElement)
			//solo.clickOnWebElement(vCheckbox);
			// Use of -clickOnWebElement(By by) 
			solo.clickOnWebElement(By.id("signIn"), 1, true);
			solo.sleep(20000);
		}
		else Log.i(Constants.vTag, "Sing in screen not loaded");

		if(solo.searchText("qaperson49@gmail.com", 0, true, true))
		{
			Log.i(Constants.vTag, "Successful logged in.");
			Global.getAllWebElements();
			solo.clickOnText("Gmail Team");
			solo.sleep(10000);
			Global.getAllWebElements();
			solo.takeScreenshot("LoggedIn", 50);
		}
		else Log.i(Constants.vTag, "Not able to Log in.");
		solo.sleep(20000);
		
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());	
	}
	
	public void testCustomHTMLWebView() throws ClassNotFoundException
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Custom HTML");
		solo.assertCurrentActivity("Screen not loaded.", Constants.CUSTOM_HTML_WEB_VIEW);
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		//Use of waitForCondition(Condition condition, final int timeout) 		
		solo.waitForCondition(this, Constants.vMinTimeout);
		solo.clickOnButton("Custom HTML");
		
		solo.enterTextInWebElement(By.id("UserID"), "nedo");
		solo.enterTextInWebElement(By.id("Password"), "123456");
		solo.enterTextInWebElement(By.id("Password2"), "123456");
		solo.enterTextInWebElement(By.id("Name"), "naveed");
		solo.enterTextInWebElement(By.id("Email"), "a@a.com");
		solo.clickOnWebElement(By.id("Agreement"));
		Log.i(Constants.vTag, "Filled form, going to click on Register");
		
		solo.clickOnWebElement(By.name("Register"));
		Global.getAllWebElements();
		
		assertTrue("testLoadCustomHTMLWebView Passed", solo.searchText("naveed"));
	}
	
	public void testImageView()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Image View");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.", Constants.IMAGE_VIEW_ACTIVITY_NAME);			
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		Log.i(Constants.vTag, ": Total Images: "+Global.getNoOfImageViews()+" Total Image Buttons: "+Global.getNoOfImageButtons());		
		
		for(int i=1; i<Global.getNoOfImageButtons(); i++)
		{
			Log.i(Constants.vTag, "Going to click on "+i+" button");
			solo.clickOnImageButton(i);
		}
	}
	
	public void testRadioButtons()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Radio Button");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.",Constants.RADIO_BUTTON_ACTIVITY_NAME);			
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		Log.i(Constants.vTag, ": Total Radio Buttons: "+Global.getNoOfRadioButtons()+" Total Radio Groups: "+Global.getNoOfRadioGroup());
		for(int i=0; i<Global.getNoOfRadioButtons(); i++)
		{
			Log.i(Constants.vTag, "Going to click on "+i+" radio button");
			if(solo.isRadioButtonChecked(i))
				Log.i(Constants.vTag, i+" radio button is already checked, no need to chech it");
			else solo.clickOnRadioButton(i);
		}
	}

	public void testCheckBoxes()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Check Box");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.",Constants.CHECK_BOX_ACTIVITY_NAME);
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());		
		Log.i(Constants.vTag, "Total check Box: "+Global.getNoOfCheckbox());
		
		for(int i=0; i<Global.getNoOfCheckbox(); i++)
		{
			Log.i(Constants.vTag, "Going to click on "+i+" check box");
			if(solo.isCheckBoxChecked(i))
				Log.i(Constants.vTag, i+" checkbox is already checked, no need to chech it");
			else solo.clickOnCheckBox(i);
		}
	}

	public void testListView()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("ListView");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.",Constants.LIST_VIEW_ACTIVITY_NAME);			
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());		
		Log.i(Constants.vTag, "Total Lists: "+Global.getNoOfListViews()+"");
		Log.i(Constants.vTag, "Total rows in List: "+solo.getCurrentViews(ListView.class).get(0).getCount());
		Log.i(Constants.vTag, "Total Visible TextViews in List: "+Global.getNoOfTextViewsInListView());		
		assertTrue("Problem selecting value from ListView", selectValueFromList("November", solo.getCurrentViews(ListView.class).get(0)));
		
		}

	public void testGridView()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("GridView");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.",Constants.GRID_VIEW_ACTIVITY_NAME);
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		
		Log.i(Constants.vTag, "Total Grid views: "+Global.getNoOfGridViews());
		Log.i(Constants.vTag, "Total Items in Grid: "+solo.getCurrentViews(GridView.class).get(0).getCount());
		Log.i(Constants.vTag, "Total visible ImageView in Grid: "+Global.getNoOfImageViewsInGridView());		
		
		//Will click on every even no image
		for(int i=1; i<Global.getNoOfImageViewsInGridView();i++)
		{
			if(i%2==0)
			{
				Log.i(Constants.vTag, "Clicking on ImageId"+LoadApp.solo.getCurrentViews(GridView.class, LoadApp.solo.getCurrentViews(GridView.class).get(0)).get(i).getId());
				solo.clickOnImage(i);
			}
		}
	}
	
	public boolean selectValueFromList(String vSearchMonth, ListView vTargetList)
	{
		//will search and select month from list
		Global.vFlag = false;
		if(solo.searchText(vSearchMonth, 1, true, true))
		{
			Log.i(Constants.vTag, ": Found "+vSearchMonth+" in list, going to click on it.");
			solo.clickOnText(vSearchMonth);
			solo.clickOnButton("Select Contents");
			Global.vFlag = true;
		}
		else { Log.i(Constants.vTag, vSearchMonth+" Not found"); Global.vFlag = false;}
		
		return Global.vFlag;
	}

	public void testCalenderView()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("CalenderView");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		//solo.assertCurrentActivity("Screen not loaded.",Constants.CALENDER_VIEW_ACTIVITY_NAME);
	}
	
	public void testDateTimeView()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("DateTimeView");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		//solo.assertCurrentActivity("Screen not loaded.",Constants.DATE_TIME_VIEW_ACTIVITY_NAME);
	}
	
	public void testTogleButton()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Toggle Button");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.",Constants.TOGGLE_BUTTON_ACTIVITY_NAME);	
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		
		ArrayList<ToggleButton> vToggleButton = solo.getCurrentViews(ToggleButton.class);		
		Log.i(Constants.vTag, "Total Toogle Buttons: "+Global.getNoOfToggleButtons());
		for(int i=0; i<vToggleButton.size(); i++)
		{
			Log.i(Constants.vTag, "Button Name: "+vToggleButton.get(i).getText()); 
			if(!solo.isToggleButtonChecked(i))
				{Log.i(Constants.vTag, "Button is Switch Off, going to Switch On"); solo.clickOnToggleButton(vToggleButton.get(i).getText().toString());}
			else Log.i(Constants.vTag, "Button is aleready Switched On.");
		}
	}

	public void testButton()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Small Button");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.",Constants.SMALL_BUTTON_ACTIVITY);
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		
		ArrayList<Button> vSmallButton = solo.getCurrentViews(Button.class);
		Log.i(Constants.vTag, "Total Buttons"+vSmallButton.size());
		for(int i=0; i<vSmallButton.size(); i++)
		{
			Log.i(Constants.vTag, "Clicking on: "+vSmallButton.get(i).getText()); 
			solo.clickOnButton(vSmallButton.get(i).getText().toString());		
		}
	}

	public void testSpinner()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Spinner");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.",Constants.SPINNER_ACTIVITY_NAME);
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		
		ArrayList<Spinner> vSpinner = solo.getCurrentViews(Spinner.class);
		Log.i(Constants.vTag, "Total Spinners: "+vSpinner.size());
		Log.i(Constants.vTag, "Selected Item Is: "+vSpinner.get(0).getSelectedItem().toString()+" at Index: "+vSpinner.get(0).getSelectedItemPosition()); 
		Log.i(Constants.vTag, "Going to select Earth.");
		solo.pressSpinnerItem(0, 2);
		assertTrue("Spinner Value not selected successfully", solo.isSpinnerTextSelected(0, "Earth"));
	}	

	public void testSwitchView()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Switch");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		//solo.assertCurrentActivity("Screen not loaded.",Constants.SWITCH_ACTIVITY_NAME);
	}
	
	public void testRatingBar()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Rating Bar");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.",Constants.RATING_BAR_ACTIVITY);
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		
		ArrayList<RatingBar> vRatingBar = solo.getCurrentViews(RatingBar.class);
		Log.i(Constants.vTag, "Total Rating Bars "+vRatingBar.size());
		Log.i(Constants.vTag, "Current Rating is "+vRatingBar.get(0).getRating());
		vRatingBar.get(0).setRating(4);
		assertTrue("Rating not successfull", (vRatingBar.get(0).getRating() == 4));
	}
	
	public void testTabs()
	{
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.clickOnButton("Tabs");
		Log.i(Constants.vTag, solo.getCurrentActivity().getLocalClassName().toString());
		solo.assertCurrentActivity("Screen not loaded.",Constants.TABS_ACTIVITY_NAME);
		Log.i(Constants.vTag, "Screen Title: "+solo.getText(0).getText().toString());
		solo.clickOnText("Artists");
		assertTrue("Artists tab not loaded.", solo.searchText("Hi TabOneContentsActivity", true));
			
		solo.clickOnText("Albums");
		assertTrue("Albums tab not loaded.", solo.searchText("Hi TabTwoContentsActivity", true));
		
		solo.clickOnText("Songs");
		assertTrue("Songs tab not loaded.", solo.searchText("Hi TabThreeContentsActivity", true));
	}
	
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