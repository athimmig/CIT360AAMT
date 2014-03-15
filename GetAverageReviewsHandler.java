import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/*
 * This handler obtains the average review for a resort
 */
public class GetAverageReviewsHandler implements Handler {
	@Override
	public void handleIt(String[] parameters) {
		
		//TO DO
		//validate the parameters
		
		//create all necessary variables
		Integer averageCrowd = 0;
		Integer averageWeather = 0;
		Integer averageConditions = 0;
		SearchModel model = new SearchModel();
		ResortBean resort;
		ReviewBean tempReview;
		
		//try to parse the parameter as an integer expecting it to be the
		//id but if not then it will retreive the resort by the resort name
		try {
			Integer id = Integer.parseInt(parameters[0]);
			resort = model.getResort(id);
		} catch (Exception e) {
			resort = model.getResort(parameters[0]);
		}
		
		//get the reviews and iterate through them to find the averages
		Set<ReviewBean> reviews = resort.getReviews();
		Iterator<ReviewBean> i = reviews.iterator();
		Integer size = reviews.size();
		while (i.hasNext()) {
			tempReview = i.next();
			averageCrowd += tempReview.getCrowd();
			averageWeather += tempReview.getWeather();
			averageConditions += tempReview.getConditions();
		}
		
		//find the averages
		averageCrowd = (int)Math.floor((averageCrowd / (double)size));
		averageWeather = (int)Math.floor((averageWeather / (double)size));
		averageConditions = (int)Math.floor((averageConditions / (double)size));
		
		//create a bean that holds all the averages to be sent back to the client
		ReviewBean average = new ReviewBean();
		average.setCrowd(averageCrowd);
		average.setWeather(averageWeather);
		average.setConditions(averageConditions);
		
		//TO DO 
		//Send the bean back to the client
	}
}
