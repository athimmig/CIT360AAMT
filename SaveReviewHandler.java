
/*
 * This handler saves a new review into the database
 */
public class SaveReviewHandler implements Handler {
	@Override
	public void handleIt(String[] params) {
		
		//TO DO
		//validate parameters
		
		SearchModel searchModel = new SearchModel();
		ReviewBean review = new ReviewBean();
		
		//get all of the data and store it into the bean
		review.setResort(searchModel.getResort(Integer.parseInt(params[0])));
		review.setWeather(Integer.parseInt(params[1]));
		review.setConditions(Integer.parseInt(params[2]));
		review.setCrowd(Integer.parseInt(params[3]));
		
		//save the bean
		ReviewModel model = new ReviewModel();
		model.addNewReview(review);		
	}
}
