package beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "skiResorts")
public class ResortBean implements Serializable{

	public ResortBean() {

	}

	public ResortBean(HashMap map) {
		id = Integer.parseInt((String)map.get("id"));
		name = (String)map.get("name");
		latitude = Float.parseFloat((String)map.get("latitude"));
		longitude = Float.parseFloat((String)map.get("longitude"));
	}

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "resort_name")
	private String name;
	private float latitude;
	private float longitude;

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

	public void newOperation() {
	}

	@Override
	public String toString() {
		return "ResortBean [id=" + id + ", name=" + name + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}

}
