import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import beans.CommunicationBean;


public class SkiShareServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//create listener socket
			ServerSocket listenerSocket = new ServerSocket(9292);
			while (true) {
				System.out.println("Waiting for a client connection request.");
				//wait for a connection
				Socket toClient = listenerSocket.accept();
				
				//once the connection is established create new input output streams for it
				JSONInputStream inFromClient = new JSONInputStream(toClient.getInputStream());
				JSONOutputStream outToClient = new JSONOutputStream(toClient.getOutputStream());
				
				//while the client is still connected
				while(toClient.isConnected()) {

					//wait for a request
					HashMap communication = (HashMap)inFromClient.readObject();
					System.out.println("Got request");
					
					CommunicationBean data = new CommunicationBean(communication);
					//dispatch the request
					ApplicationController.getApplicationController().handleRequest(data.getCommand(), data.getData(), outToClient);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
