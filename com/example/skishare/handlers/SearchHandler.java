package com.example.skishare.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import com.example.skishare.ApplicationController;
import com.example.skishare.R;
import com.example.skishare.beans.CommunicationBean;
import com.example.skishare.beans.ResortBean;
import com.example.skishare.models.Model;

import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchHandler implements Runnable {
	
	//parameters for the search
	ArrayList parameters;
	
	public SearchHandler(ArrayList params) {
		parameters = params;
	}
	
	@Override
	public void run() {
		//get each parameters
		String searchText = parameters.get(0).toString();
		String range = parameters.get(1).toString();
		
		ArrayList<String> data = new ArrayList<String>();
		data.add(searchText);
		data.add(range);
		
		//send a request for the the search results
		Model model = new Model();
		HashMap results = model.sendRequest("search", data);
		
		//send the results off to the ApplicationController to be handled
		ApplicationController.getApplicationController().handleRequest((String)results.get("command"), (ArrayList)results.get("data"));
	}
}