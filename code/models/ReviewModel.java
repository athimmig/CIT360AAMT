package models;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.ReviewBean;

public class ReviewModel {
	
	//stores a new review in the database
	public void addNewReview(ReviewBean review) {
		
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(review);
		
		transaction.commit();
	}
	
	public ArrayList<ReviewBean> getReviews(Integer resortId) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		Query reviewsQuery = session.createQuery("Select u from resort_reviews where u.resortId='" + resortId + "' order by u.reviewDate");
		
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>(reviewsQuery.list());
		
		transaction.commit();
		
		return reviews;
	}
}
