import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReviewModel {
	
	//stores a new review in the database
	public void addNewReview(ReviewBean review) {
		
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(review);
		
		transaction.commit();
	}
}
