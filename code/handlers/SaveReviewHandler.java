package handlers;
import java.util.ArrayList;

import org.quickconnectfamily.json.JSONOutputStream;

import models.ReviewModel;
import models.SearchModel;
import beans.ReviewBean;


/*
 * This handler saves a new review into the database
 */
public class SaveReviewHandler implements Runnable {
	
	ArrayList parameters;
	private JSONOutputStream out;
	
	public SaveReviewHandler(ArrayList params, JSONOutputStream outToClient) {
		parameters = params;
		out = outToClient;
	}
	
	@Override
	public void run() {
		
		//TO DO
		//validate parameters
		
		SearchModel searchModel = new SearchModel();
		ReviewBean review = new ReviewBean();
		
		//get all of the data and store it into the bean
		review.setResortId(Integer.parseInt((String)parameters.get(0)));
		review.setWeather(Integer.parseInt((String)parameters.get(1)));
		review.setConditions(Integer.parseInt((String)parameters.get(2)));
		review.setCrowd(Integer.parseInt((String)parameters.get(3)));
		
		//save the bean
		ReviewModel model = new ReviewModel();
		model.addNewReview(review);		
	}
}
