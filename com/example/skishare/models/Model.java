package com.example.skishare.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.skishare.ApplicationController;
import com.example.skishare.beans.CommunicationBean;

public class Model {
	public HashMap sendRequest(String command, ArrayList parameters) {
		HashMap resultSet = new HashMap();

		//create a communication bean to be sent
		CommunicationBean data = new CommunicationBean();
		data.setCommand(command);
		data.setData(parameters);

		//send the data to the server and then listen for more information
		try {
			SkiSocketSingleton.getSkiSocket().getOutToServer().writeObject(data);
			resultSet = (HashMap)SkiSocketSingleton.getSkiSocket().getInFromServer().readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
}
