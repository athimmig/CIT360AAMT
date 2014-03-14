import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.*;

@Entity
@Table(name = "zip_codes")
public class ZipCodeBean implements Serializable  {
	
	
public ZipCodeBean() {
        
    }
    
    public ZipCodeBean(HashMap hash) {
        id = Integer.parseInt(String.valueOf(hash.get("id")));
        zip_code = Integer.parseInt(String.valueOf(hash.get("zip_code")));
        latitude = Float.parseFloat(String.valueOf(hash.get("latitude")));
        longitude = Float.parseFloat(String.valueOf(hash.get("longitude")));
        state = (String)hash.get("state");
        city = (String)hash.get("city");
        county = (String)hash.get(county);
    }
	

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "zip_code")
    private Integer zip_code;
    @Column(name = "latitude")
    private Float latitude;
    @Column(name = "longitude")
    private Float longitude;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "county")
    private String county;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Integer getZipCode() {
        return zip_code;
    }
    public void setZipCode(int zip_code) {
        this.zip_code = zip_code;
    }

    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
    
    public float getLongitude() {
        return longitude;
    }
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }

    public String toString() {
        return "Location: [Id: " + id + ", Zip Code: " + zip_code + ", Latitude: " + latitude + ", Longitude: " + longitude + ", State: " + state
        		+ ", City: " + city + ", County: " + county + "]";
    }

}

