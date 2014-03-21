package com.example.skishare.beans;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ResortBean {

	public ResortBean() {
		
	}
	
	public ResortBean(HashMap map) {
		id = Integer.parseInt((String)map.get("id"));
		name = map.get("name").toString();
		latitude = Float.parseFloat((String)map.get("latitude"));
		longitude = Float.parseFloat((String)map.get("longitude"));
	}
	
	private Integer id;
	private String name;
	private float latitude;
	private float longitude;

	private Set<ReviewBean> reviews = new HashSet<ReviewBean>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Integer getId() {
		return id;
	}

	public Set<ReviewBean> getReviews() {
		return reviews;
	}

	public void newOperation() {
	}

	@Override
	public String toString() {
		return "ResortBean [id=" + id + ", name=" + name + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}

}
