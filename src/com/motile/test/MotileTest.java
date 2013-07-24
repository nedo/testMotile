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

public class MotileTest extends ActivityInstrumentationTestCase2 implements Condition{
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
	public MotileTest() throws ClassNotFoundException
		{	
		super(launcherActivityClass);
		}

	public static Solo solo;
	
	protected void setUp() throws Exception	{	
	  solo = new Solo(getInstrumentation(), getActivity());		  
	}	
/*
 * This test case will load WebView and 
 * try to login with google test account
 * load an email details
 * */
	
	public void testActivityHavingWebView() throws ClassNotFoundException
	{
		solo.clickOnButton("Webview");
		solo.assertCurrentActivity("Screen not loaded.", Constants.WEB_VIEW_ACTIVITY);
		solo.takeScreenshot();		
		assertTrue("Webview button available.", solo.searchButton("Webview", true));
		solo.clickOnButton("Webview");
		solo.sleep(Global.vMinSleepTime);	
	}
		
	/*
	 * This test case will load custom HTML WebView and 
	 * try to fill a registration form
	 * */
	
	public void testActivityHavingCustomHTMLWebView() throws ClassNotFoundException
	{
		solo.clickOnButton("Custom HTML");
		solo.assertCurrentActivity("Screen not loaded.", Constants.CUSTOM_HTML_WEB_VIEW);

		//Use of waitForCondition(Condition condition, final int timeout) 		
		solo.waitForCondition(this, Constants.vMinTimeout);
		solo.clickOnButton("Custom HTML");
		
		solo.enterTextInWebElement(By.id("UserID"), "nedo");
		solo.enterTextInWebElement(By.id("Password"), "123456");
		solo.enterTextInWebElement(By.id("Password2"), "123456");
		solo.enterTextInWebElement(By.id("Name"), "naveed");
		solo.enterTextInWebElement(By.id("Email"), "a@a.com");
		solo.clickOnWebElement(By.id("Agreement"));		
		solo.clickOnWebElement(By.name("Register"));
		Global.getAllWebElements();
		
		assertTrue("testLoadCustomHTMLWebView Passed", solo.searchText("naveed"));
	}
	
	
	/*
	 * This test case will load screen containing Images & ImageButtons 
	 * count number of Images & ImageButtons
	 * click on each ImageButton
	 * */	
	public void testActivityHavingImageView()
	{
		solo.clickOnButton("Image View");
		solo.assertCurrentActivity("Screen not loaded.", Constants.IMAGE_VIEW_ACTIVITY_NAME);			
		Log.i(Constants.vTag, ": Total Images: "+Global.getNoOfImageViews()+" Total Image Buttons: "+Global.getNoOfImageButtons());		
		
		for(int i=1; i<Global.getNoOfImageButtons(); i++)
		{
			Log.i(Constants.vTag, "Going to click on "+i+" button");
			solo.clickOnImageButton(i);
		}
	}
	
	/*
	 * This test case will load screen containing RadioButtons 
	 * count number of RadioButtons
	 * select only un-selected RadioButtons
	 * */		
	public void testActivityHavingRadioButtons()
	{
		solo.clickOnButton("Radio Button");
		solo.assertCurrentActivity("Screen not loaded.",Constants.RADIO_BUTTON_ACTIVITY_NAME);			
		Log.i(Constants.vTag, ": Total Radio Buttons: "+Global.getNoOfRadioButtons()+" Total Radio Groups: "+Global.getNoOfRadioGroup());
		for(int i=0; i<Global.getNoOfRadioButtons(); i++)
		{
			Log.i(Constants.vTag, "Going to click on "+i+" radio button");
			if(solo.isRadioButtonChecked(i))
				Log.i(Constants.vTag, i+" radio button is already checked, no need to chech it");
			else solo.clickOnRadioButton(i);
		}
	}
	
	/*
	 * This test case will load screen containing CheckBoxes 
	 * count number of CheckBoxes
	 * check only un-checked CheckBoxes
	 * */
	public void testActivityHavingCheckBoxes()
	{
		solo.clickOnButton("Check Box");
		solo.assertCurrentActivity("Screen not loaded.",Constants.CHECK_BOX_ACTIVITY_NAME);
		Log.i(Constants.vTag, "Total check Box: "+Global.getNoOfCheckbox());
		
		for(int i=0; i<Global.getNoOfCheckbox(); i++)
		{
			Log.i(Constants.vTag, "Going to click on "+i+" check box");
			if(solo.isCheckBoxChecked(i))
				Log.i(Constants.vTag, i+" checkbox is already checked, no need to chech it");
			else solo.clickOnCheckBox(i);
		}
	}
	
	/*
	 * This test case will load screen with ListView containing TextViews
	 * count number of ListViews, visible rows in List, TextViews in List
	 * select some value from ListView and verify selection
	 * */
	public void testActivityHavingListView()
	{		
		solo.clickOnButton("ListView");	
		solo.assertCurrentActivity("Screen not loaded.",Constants.LIST_VIEW_ACTIVITY_NAME);			
		Log.i(Constants.vTag, "Total Lists: "+Global.getNoOfListViews()+"");
		Log.i(Constants.vTag, "Total rows in List: "+solo.getCurrentViews(ListView.class).get(0).getCount());
		Log.i(Constants.vTag, "Total Visible TextViews in List: "+Global.getNoOfTextViewsInListView());		
		assertTrue("Problem selecting value from ListView", selectValueFromList("November", solo.getCurrentViews(ListView.class).get(0)));		
		}
	
	/*
	 * This test case will load screen with GridView containing ImageViews
	 * count total number of GridViews, ImageView & visible ImageVies 
	 * click on every second ImageView 
	 * */
	public void testActivityHavingGridView()
	{
		solo.clickOnButton("GridView");
		solo.assertCurrentActivity("Screen not loaded.",Constants.GRID_VIEW_ACTIVITY_NAME);
		
		Log.i(Constants.vTag, "Total Grid views: "+Global.getNoOfGridViews());
		Log.i(Constants.vTag, "Total Items in Grid: "+solo.getCurrentViews(GridView.class).get(0).getCount());
		Log.i(Constants.vTag, "Total visible ImageView in Grid: "+Global.getNoOfImageViewsInGridView());		
		
		//Will click on every even no image
		for(int i=1; i<Global.getNoOfImageViewsInGridView();i++)
		{
			if(i%2==0)
			{
				Log.i(Constants.vTag, "Clicking on ImageId"+MotileTest.solo.getCurrentViews(GridView.class, MotileTest.solo.getCurrentViews(GridView.class).get(0)).get(i).getId());
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
	
	/*
	 * This test case will load screen with CalenderView
	 * it will be implemented soon   
	 * */
	public void testActivityHavingCalenderView()
	{
		solo.clickOnButton("CalenderView");	
		//solo.assertCurrentActivity("Screen not loaded.",Constants.CALENDER_VIEW_ACTIVITY_NAME);
	}

	/*
	 * This test case will load screen with DateTimeView
	 * it will be implemented soon   
	 * */	
	public void testActivityHavingDateTimeView()
	{
		solo.clickOnButton("DateTimeView");
		//solo.assertCurrentActivity("Screen not loaded.",Constants.DATE_TIME_VIEW_ACTIVITY_NAME);
	}
	
	/*
	 * This test case will load screen containing ToggleButtons 
	 * count number of ToggleButtons
	 * switch-on ToggleButtons
	 * */	
	public void testActivityHavingTogleButton()
	{
		solo.clickOnButton("Toggle Button");
		solo.assertCurrentActivity("Screen not loaded.",Constants.TOGGLE_BUTTON_ACTIVITY_NAME);	
		
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
	
	/*
	 * This test case will load screen containing Buttons 
	 * count number of Buttons
	 * click on Button
	 * */	
	public void testActivityHavingButton()
	{
		solo.clickOnButton("Small Button");
		solo.assertCurrentActivity("Screen not loaded.",Constants.SMALL_BUTTON_ACTIVITY);
		
		ArrayList<Button> vSmallButton = solo.getCurrentViews(Button.class);
		Log.i(Constants.vTag, "Total Buttons"+vSmallButton.size());
		for(int i=0; i<vSmallButton.size(); i++)
		{
			Log.i(Constants.vTag, "Clicking on: "+vSmallButton.get(i).getText()); 
			solo.clickOnButton(vSmallButton.get(i).getText().toString());		
		}
	}

	/*
	 * This test case will load screen containing Spinner 
	 * count number of Spinners
	 * select a value and verify selection
	 * */	
	public void testActivityHavingSpinner()
	{
		solo.clickOnButton("Spinner");
		solo.assertCurrentActivity("Screen not loaded.",Constants.SPINNER_ACTIVITY_NAME);
		
		ArrayList<Spinner> vSpinner = solo.getCurrentViews(Spinner.class);
		Log.i(Constants.vTag, "Total Spinners: "+vSpinner.size());
		solo.pressSpinnerItem(0, 2);
		assertTrue("Spinner Value not selected successfully", solo.isSpinnerTextSelected(0, "Earth"));
	}	
	
	/*
	 * This test case will load screen with SwitchView
	 * it will be implemented soon   
	 * */
	public void testActivityHavingSwitchView()
	{
		solo.clickOnButton("Switch");
		//solo.assertCurrentActivity("Screen not loaded.",Constants.SWITCH_ACTIVITY_NAME);
	}

	/*
	 * This test case will load screen containing RatingBar 
	 * count number of RatingBars
	 * select rating and verify selection
	 * */	
	public void testActivityHavingRatingBar()
	{
		solo.clickOnButton("Rating Bar");
		solo.assertCurrentActivity("Screen not loaded.",Constants.RATING_BAR_ACTIVITY);
		
		ArrayList<RatingBar> vRatingBar = solo.getCurrentViews(RatingBar.class);
		Log.i(Constants.vTag, "Total Rating Bars "+vRatingBar.size());
		Log.i(Constants.vTag, "Current Rating is "+vRatingBar.get(0).getRating());
		vRatingBar.get(0).setRating(4);
		assertTrue("Rating not successfull", (vRatingBar.get(0).getRating() == 4));
	}
	
	/*
	 * This test case will load screen containing Tabs 
	 * Load each tab and verify its loaded
	 * */	
	public void testActivityHavingTabs()
	{
		solo.clickOnButton("Tabs");
		solo.assertCurrentActivity("Screen not loaded.",Constants.TABS_ACTIVITY_NAME);
		solo.clickOnText("Artists");
		assertTrue("Artists tab not loaded.", solo.searchText("Hi TabOneContentsActivity", true));
			
		solo.clickOnText("Albums");
		assertTrue("Albums tab not loaded.", solo.searchText("Hi TabTwoContentsActivity", true));
		
		solo.clickOnText("Songs");
		assertTrue("Songs tab not loaded.", solo.searchText("Hi TabThreeContentsActivity", true));
	}
	
	public void tearDown() throws Exception	
	{	
		solo.finishOpenedActivities();
	}

	public boolean isSatisfied() {
		if(solo.searchButton("Custom HTML", true)) {Log.i(Constants.vTag, "Condition Passed"); return true;}
		else {Log.i(Constants.vTag, "Condition Filed."); return false;}
	}
}