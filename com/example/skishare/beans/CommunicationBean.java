package com.example.skishare.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CommunicationBean implements Serializable{
	
	private String command;
	private ArrayList data;
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public ArrayList getData() {
		return data;
	}
	public void setData(ArrayList data) {
		this.data = data;
	}
	

	
}
