
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SearchModel {

	public ResortBean getResort(Integer id) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query resortQuery = session.createQuery("select u from ResortBean as u where u.id='" + id.toString() + "'");

		ResortBean resort = (ResortBean)resortQuery.uniqueResult();
		
		transaction.commit();

		return resort;
	}

	public ResortBean getResort(String name) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query resortQuery = session.createQuery("select u from ResortBean as u where u.name='" + name + "'");

		ResortBean resort = (ResortBean)resortQuery.uniqueResult();
		
		transaction.commit();

		return resort;
	}

	public ArrayList<ResortBean> getAllResorts() {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query allResortsQuery = session.createQuery("select u from ResortBean as u order by u.id");

		ArrayList<ResortBean> resorts = new ArrayList<ResortBean>(allResortsQuery.list());

		transaction.commit();

		return resorts;
	}

	//	public ArrayList<ResortBean> getResortsFromLatLong(float latitude, float longitude, Integer radius) {
	//
	//	}
	//
	//	public ArrayList<ResortBean> getResortsFromName(String name, Integer radius) {
	//
	//	}
	//
	//	public ArrayList<ResortBean> getResortsFromCityState(String city, String state, Integer radius) {
	//
	//	}
	//
	private float[] getLatLongFromCityState(String city, String state) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query latLongQuery = session.createQuery("select u from ZipCodeBean as u where u.city='" + city + " && u.state='" + state + "'");

		//create a ZipCodeBean and set the id to zero for testing later
		ZipCodeBean zipCode = new ZipCodeBean();
		zipCode.setId(0);

		List<ZipCodeBean> zipCodesList = (List<ZipCodeBean>)latLongQuery.list();
		if (zipCodesList.size() > 0) {
			zipCode = zipCodesList.get(0);
		}

		float[] latLong = new float[2];
		//get the latitude and longitude
		if (zipCode.getId() != 0) {
			latLong[0] = zipCode.getLatitude();
			latLong[1] = zipCode.getLongitude();
		}
		
		transaction.commit();

		return latLong;
	}

	private float[] getLatLongFromZipCode(String zip_code) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query latLongQuery = session.createQuery("select u from ZipCodeBean as u where u.zip_code='" + zip_code + "'");

		//create a ZipCodeBean and set the id to zero for testing later
		ZipCodeBean zipCode = new ZipCodeBean();
		zipCode.setId(0);

		List<ZipCodeBean> zipCodesList = (List<ZipCodeBean>)latLongQuery.list();
		if (zipCodesList.size() > 0) {
			zipCode = zipCodesList.get(0);
		}

		float[] latLong = new float[2];
		//get the latitude and longitude
		if (zipCode.getId() != 0) {
			latLong[0] = zipCode.getLatitude();
			latLong[1] = zipCode.getLongitude();
		}
		
		transaction.commit();

		return latLong;
	}

	//	private float[] getLatLongRanges(float latitude, float longitude, Integer radius) {
	//		
	//	}

}