package handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.quickconnectfamily.json.JSONOutputStream;

import models.SearchModel;
import models.ReviewModel;

import beans.ResortBean;
import beans.ReviewBean;

/*
 * This handler gets all of the reviews for a resort
 */
public class GetReviewsHandler implements Runnable {

	ArrayList parameters;
	private JSONOutputStream out;
	
	public GetReviewsHandler(ArrayList params, JSONOutputStream outToClient) {
		parameters = params;
		out = outToClient;
	}

	@Override
	public void run() {
		
		//TO DO
		//validate the parameters
		
		SearchModel model = new SearchModel();
		ReviewModel reviewModel = new ReviewModel();
		ResortBean resort;
		
		//try to get the resort with the id expecting the id
		//of the resort first
		//if that fails then get the resort with the name
		try {
			Integer id = Integer.parseInt((String)parameters.get(0));
			resort = model.getResort(id);
		} catch (Exception e) {
			resort = model.getResort((String)parameters.get(0));
		}
		
		//get the reviews and store them into an ArrayList to be
		//sent back to the client
		Set<ReviewBean> reviews = (Set<ReviewBean>) reviewModel.getReviews(resort.getId());
		ArrayList<ReviewBean> reviewsList = new ArrayList<ReviewBean>();
		reviewsList.addAll(reviews);
		
		
		//TO DO
		//send the Communication bean back to the client
	}
}
