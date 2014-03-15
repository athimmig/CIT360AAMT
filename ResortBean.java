import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "skiResorts")
public class ResortBean implements Serializable {
	
public ResortBean() {
        
    }
    
    public ResortBean(HashMap hash) {
        id = Integer.parseInt(String.valueOf(hash.get("id")));
        name = (String)hash.get("name");
        latitude = Float.parseFloat(String.valueOf(hash.get("latitude")));
        longitude = Float.parseFloat(String.valueOf(hash.get("longitude")));
    }
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "resort_name")
	private String name;
	private float latitude;
	@Column(name = "longitude")
	private float longitude;

	 @OneToMany(mappedBy="resort", fetch=FetchType.EAGER)
	 @Column(name="resort_id")
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
