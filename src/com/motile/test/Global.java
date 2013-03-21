package com.motile.test;
	    
import java.util.ArrayList;
import com.jayway.android.robotium.solo.WebElement;
import junit.framework.Assert;
import android.app.ActivityManager;
import android.os.Debug.MemoryInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

class Global {;
	static boolean vFlag;
	static int vMinSleepTime = 2000;
	
	static int getNoOfAllViews()
	{
		return MotileTest.solo.getCurrentViews().size();		
	}

	static int getNoOfImageViews()
	{		 
		return MotileTest.solo.getCurrentViews(ImageView.class).size();
	}
	
	static int getNoOfImageButtons()
	{
		return MotileTest.solo.getCurrentViews(ImageButton.class).size();		
	}
	
	static int getNoOfRadioButtons()
	{
		return MotileTest.solo.getCurrentViews(RadioButton.class).size();
	}
	
	static int getNoOfRadioGroup()
	{
		return MotileTest.solo.getCurrentViews(RadioGroup.class).size();
	}
	
	static int getNoOfCheckbox()
	{
		return MotileTest.solo.getCurrentViews(CheckBox.class).size();
	}	

	static int getNoOfListViews()
	{
		return MotileTest.solo.getCurrentViews(ListView.class).size();
	}
	
	static int getNoOfTextViewsInListView()
	{
		return MotileTest.solo.getCurrentViews(TextView.class, MotileTest.solo.getCurrentViews(ListView.class).get(0)).size(); 	
	}
	
	static int getNoOfGridViews()
	{
		return MotileTest.solo.getCurrentViews(GridView.class).size();
	}
	
	static int getNoOfImageViewsInGridView()
	{
		return MotileTest.solo.getCurrentViews(GridView.class, MotileTest.solo.getCurrentViews(GridView.class).get(0)).size(); 	
	}
	
	static int getNoOfToggleButtons()
	{
		return MotileTest.solo.getCurrentViews(ToggleButton.class).size();
	}
	
	static int getNoOfButtons()
	{
		return MotileTest.solo.getCurrentViews(Button.class).size();
	}
	
	static int getNoOfTextViews()
	{
		return MotileTest.solo.getCurrentViews(TextView.class).size();
	}
	
	static void getAllWebElements()
	{
		ArrayList<WebElement> vWebView =  MotileTest.solo.getCurrentWebElements();
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

}