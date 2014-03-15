import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SkiShareServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {			
			ApplicationController app = new ApplicationController();

			String[] zipParams = {"83440", "100"};
			String[] nameParams = {"Kelly Canyon", "100"};
			String[] cityStateParams = {"Rexburg, Idaho", "100"};
			String[] reviewNameParams = {"Kelly Canyon"};
			String[] reviewIdParams = {"31"};
			String[] newParams = {"31", "0", "2", "1"};

			System.out.println("Zip Code");
			app.handleRequest("search", zipParams);

			System.out.println();
			System.out.println("Name");
			app.handleRequest("search", nameParams);

			System.out.println();
			System.out.println("City State");
			app.handleRequest("search", cityStateParams);
			
			System.out.println();
			System.out.println("Reviews Name");
			app.handleRequest("retrieve_reviews", reviewNameParams);
			
			System.out.println();
			System.out.println("Add Review");
			app.handleRequest("save_review", newParams);
			
			System.out.println();
			System.out.println("Reviews Id");
			app.handleRequest("retrieve_reviews", reviewIdParams);
			
			System.out.println();
			System.out.println("Average");
			app.handleRequest("average_reviews", reviewIdParams);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
