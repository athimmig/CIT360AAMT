import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/*
 * This handler gets all of the reviews for a resort
 */
public class GetReviewsHandler implements Handler {

	@Override
	public void handleIt(String[] parameters) {
		
		//TO DO
		//validate the parameters
		
		SearchModel model = new SearchModel();
		ResortBean resort;
		
		//try to get the resort with the id expecting the id
		//of the resort first
		//if that fails then get the resort with the name
		try {
			Integer id = Integer.parseInt(parameters[0]);
			resort = model.getResort(id);
		} catch (Exception e) {
			resort = model.getResort(parameters[0]);
		}
		
		//get the reviews and store them into an ArrayList to be
		//sent back to the client
		Set<ReviewBean> reviews = resort.getReviews();
		ArrayList<ReviewBean> reviewsList = new ArrayList<ReviewBean>();
		reviewsList.addAll(reviews);
		
		//TO DO
		//send the ArrayList back to the client
	}
}
