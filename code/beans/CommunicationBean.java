package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CommunicationBean implements Serializable{
	
	private String command;
	private ArrayList data;
	
	public CommunicationBean() {
		
	}
	
	public CommunicationBean(HashMap map) {
		command = (String)map.get("command");
		data = (ArrayList)map.get("data");
	}
	
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
