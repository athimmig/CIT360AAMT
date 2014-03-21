import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import handlers.*;

import org.quickconnectfamily.json.JSONOutputStream;

/*
 * This class's purpose is to map commands to handlers
 * It receives a command and forwards the parameters
 * to the correct handler
 * 
 * This class is a singleton
 */
public class ApplicationController {

	private static ApplicationController app;
	//executor thread pool since there is potential for constant threads being created
	private static Executor threadPool;
	//contains the mapping between commands and handlers
	private HashMap<String, String> commands = new HashMap<String, String>();

	//instantiates class with command/handler mappings
	private ApplicationController() {
		this.commands.put("search", "handlers.SearchHandler");
		this.commands.put("retrieve_reviews", "handlers.GetReviewsHandler");
		this.commands.put("average_reviews", "handlers.GetAverageReviewsHandler");
		this.commands.put("save_review", "handlers.SaveReviewHandler");

		//create the thread pool
		threadPool = Executors.newCachedThreadPool();
	}

	public static ApplicationController getApplicationController() {
		if (app == null) {
			app = new ApplicationController();
		}
		return app;
	}

	//takes a command and returns the handler
	private String get(String command) {
		return this.commands.get(command);
	}

	//get the handler and run the handleIt method off of it
	public void handleRequest(String command, ArrayList parameters, JSONOutputStream outToClient) {
		try {
			//use reflection to get and instance of the runnable
			Class<?> handler = Class.forName(this.get(command));
			Constructor<?> constructor = handler.getConstructor(ArrayList.class, JSONOutputStream.class);
			Runnable instance = (Runnable)constructor.newInstance(parameters, outToClient);

			//give Runnable to thread pool to execute
			threadPool.execute(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
