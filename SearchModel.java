
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SearchModel {

	//get a resort from its id
	public ResortBean getResort(Integer id) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query resortQuery = session.createQuery("select u from ResortBean as u where u.id='" + id.toString() + "'");

		ResortBean resort = (ResortBean)resortQuery.uniqueResult();
		

		transaction.commit();

		return resort;
	}

	//get a resort from its name
	public ResortBean getResort(String name) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query resortQuery = session.createQuery("select u from ResortBean as u where u.name='" + name + "'");
				
		ResortBean resort = (ResortBean)resortQuery.uniqueResult();

		transaction.commit();

		return resort;

	}

	//get all resorts in the database
	public ArrayList<ResortBean> getAllResorts() {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query allResortsQuery = session.createQuery("select u from ResortBean as u order by u.id");

		ArrayList<ResortBean> resorts = new ArrayList<ResortBean>(allResortsQuery.list());

		transaction.commit();

		return resorts;
	}

	//get all resorts using a given latitude and longitude
	public ArrayList<ResortBean> getResortsFromLatLong(float latitude, float longitude, Integer radius) {
		float[][] ranges = this.getLatLongRanges(latitude, longitude, radius);

		return this.getResortsFromRange(ranges);
	}
	
	//get all resorts around resort from its name
	public ArrayList<ResortBean> getResortsFromName(String name, Integer radius) {
		ResortBean resort = this.getResort(name);

		if (resort != null) {
			float[][] ranges = this.getLatLongRanges(resort.getLatitude(), resort.getLongitude(), radius);

			return this.getResortsFromRange(ranges);
		} else {
			return null;
		}

	}

	//get all resorts around a city state
	public ArrayList<ResortBean> getResortsFromCityState(String city, String state, Integer radius) {
		float[] latLong = this.getLatLongFromCityState(city, state);

		float[][] ranges = this.getLatLongRanges(latLong[0], latLong[1], radius);

		return this.getResortsFromRange(ranges);
	}

	//get all resorts around a zip code
	public ArrayList<ResortBean> getResortsFromZipCode(String zipCode, Integer radius) {
		float[] latLong = this.getLatLongFromZipCode(zipCode);

		float[][] ranges = this.getLatLongRanges(latLong[0], latLong[1], radius);

		return this.getResortsFromRange(ranges);

	}

	//get the latitude and longitude for a city state
	private float[] getLatLongFromCityState(String city, String state) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query latLongQuery = session.createQuery("select u from ZipCodeBean as u where u.city='" + city + "' AND u.state='" + state + "'");

		//create a ZipCodeBean and set the id to zero for testing later
		ZipCodeBean zipCode = new ZipCodeBean();

		//get a list of all matches and just get the first one because some city states
		//have multiple zip codes because of their size
		//we don't want to mess with asking which one they want.  We will just
		//get the first one
		List<ZipCodeBean> zipCodesList = (List<ZipCodeBean>)latLongQuery.list();
		if (zipCodesList.size() > 0) {
			zipCode = zipCodesList.get(0);
		}
		
		float[] latLong = new float[2];
		//get the latitude and longitude
		if (zipCode != null) {
			latLong[0] = zipCode.getLatitude();
			latLong[1] = zipCode.getLongitude();
		}

		transaction.commit();

		return latLong;
	}

	//get latitude and longitude for a zip code
	private float[] getLatLongFromZipCode(String zip_code) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		Query latLongQuery = session.createQuery("select u from ZipCodeBean as u where u.zip_code='" + zip_code + "'");

		//create a ZipCodeBean and set the id to zero for testing later
		ZipCodeBean zipCode = new ZipCodeBean();
		zipCode.setId(0);
		
		//get a list of the found zip codes and just get the first one found
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

	//get min max range for latitude and longitude using radius
	private float[][] getLatLongRanges(float latitude, float longitude, Integer radius) {
		double meters = (double)radius * 1609.344;
		float degrees = (float)(meters / 111000.0);
		float[][] range = new float[2][2];

		range[0][0] = latitude - degrees;
		range[0][1] = latitude + degrees;
		range[1][0] = longitude - ((float)Math.cos((double)latitude) + degrees);
		range[1][1] = longitude + ((float)Math.cos((double)latitude) + degrees);

		return range;
	}

	//get all the resorts fitting within min max latitude and longitude range
	private ArrayList<ResortBean> getResortsFromRange(float[][] ranges) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();

		Transaction transaction = session.beginTransaction();
		String query = "select u from ResortBean as u where u.latitude>='" + ranges[0][0] + "' AND " + 
				"u.latitude<='" + ranges[0][1] + "' AND u.longitude>='" + ranges[1][0] +
				"' AND u.longitude<='" + ranges[1][1] + "'";
		Query resortsQuery = session.createQuery(query);

		ArrayList<ResortBean> resorts = new ArrayList<ResortBean>(resortsQuery.list());
		transaction.commit();

		return resorts;
	}

}