import java.util.HashMap;

/*
 * This class's purpose is to map commands to handlers
 * It receives a command and forwards the parameters
 * to the correct handler
 */
public class ApplicationController {

	//contains the mapping between commands and handlers
	HashMap<String, Handler> commands = new HashMap<String, Handler>();
	
	//instantiates class with command/handler mappings
	public ApplicationController() {
		this.commands.put("search", new SearchHandler());
		this.commands.put("retrieve_reviews", new GetReviewsHandler());
		this.commands.put("average_reviews", new GetAverageReviewsHandler());
		this.commands.put("save_review", new SaveReviewHandler());
	}
	
	//takes a command and returns the handler
	private Handler get(String command) {
		return this.commands.get(command);
	}
	
	//get the handler and run the handleIt method off of it
	public void handleRequest(String command, String[] parameters) {
		this.get(command).handleIt(parameters);
	}
	
}
