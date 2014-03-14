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
	@Column(name = "resort_id")
	  private Integer id;
	@Column(name = "resort_name")
	  private String name;
	@Column(name = "lattitude")
	  private float latitude;
	@Column(name = "longitude")
	  private float longitude;
//	@Column(name = "longitude")
//	  private Set<ReviewBean> reviews;

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

//	public Set<ReviewBean> getReviews() {
//		return reviews;
//	}

	public void newOperation() {
	  }

	@Override
	public String toString() {
		return "ResortBean [id=" + id + ", name=" + name + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}

	}
