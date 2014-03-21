package com.example.skishare;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.example.skishare.handlers.SearchHandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SkiShareActivity extends Activity {

	private EditText searchInput;
	private SeekBar rangeInput;
	private Button searchButton;
	private LinearLayout resultsWrapper;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ski_share);
				
		//get all of the objects on the screen
		getAllUIElements();
		
		//initialize handler
		initializeAndroidHandler();
		
		//set up on click listener
		searchButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String searchText = searchInput.getText().toString();
				String range = ((Integer)rangeInput.getProgress()).toString();
				ArrayList parameters = new ArrayList();
				parameters.add(searchText);
				parameters.add(range);
				
				ApplicationController.getApplicationController().handleRequest("search", parameters);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ski_share, menu);
		return true;
	}
	
	public void initializeAndroidHandler() {
		Handler uiHandler = new Handler();
		AndroidUIHandler.setHandler(uiHandler);
		AndroidUIHandler.addWeakReference("search_input", new WeakReference(searchInput));
		AndroidUIHandler.addWeakReference("range_input", new WeakReference(rangeInput));
		AndroidUIHandler.addWeakReference("search_button", new WeakReference(searchButton));
		AndroidUIHandler.addWeakReference("results_wrapper", new WeakReference(resultsWrapper));
		AndroidUIHandler.addWeakReference("context", new WeakReference(context));
	}
	
	public void getAllUIElements() {
		searchInput = (EditText)this.findViewById(R.id.searchInput);
		rangeInput = (SeekBar)this.findViewById(R.id.rangeBar);
		searchButton = (Button)this.findViewById(R.id.searchButton);
		resultsWrapper = (LinearLayout)this.findViewById(R.id.resultsLayout);
		context = resultsWrapper.getContext();
	}
}
