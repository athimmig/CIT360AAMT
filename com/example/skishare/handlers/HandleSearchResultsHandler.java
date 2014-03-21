package com.example.skishare.handlers;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.example.skishare.AndroidUIHandler;

import android.content.Context;
import android.widget.TextView;
import android.widget.LinearLayout;

public class HandleSearchResultsHandler implements Runnable {

	//contains the results of the search
	ArrayList results;

	//just sets the results so that the runnable has access to them
	//while in it's own thread
	public HandleSearchResultsHandler(ArrayList resultSet) {
		results = resultSet;
	}

	@Override
	public void run() {
		//post a new runnable to the UI thread to update the UI with the results
		AndroidUIHandler.getHandler().post(new Runnable() {
			@Override
			public void run() {
				try {
					HashMap result;
					TextView resortName;
					
					//remove all previous search results
					LinearLayout layout = (LinearLayout)AndroidUIHandler.getWeakReference("results_wrapper").get();
					layout.removeAllViews();
					
					//loop through the results
					Iterator<HashMap> i = results.iterator();
					while (i.hasNext()) {
						result = i.next();
						
						//create a new text view and set all of the information
						resortName = new TextView((Context)(AndroidUIHandler.getWeakReference("context").get()));
						resortName.setText(result.get("name").toString());
						resortName.setTextSize((float)40.0);
						
						//add textview to the view
						layout.addView(resortName);				
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
