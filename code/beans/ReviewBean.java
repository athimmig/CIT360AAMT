package beans;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.*;

@Entity
@Table(name = "resort_reviews")
public class ReviewBean {

	public ReviewBean() {

	}

	public ReviewBean(HashMap map) {
		id = Integer.parseInt((String)map.get("id"));
		reviewDate = (String)map.get("reviewDate");
		weather = Integer.parseInt((String)map.get("weather"));
		conditions = Integer.parseInt((String)map.get("conditions"));
		crowd = Integer.parseInt((String)map.get("crowd"));
	}

	@Id 
	@GeneratedValue
	private Integer id;

	@Column(name = "resort_id")
	private Integer resortId;

	@Column(name = "review_date")
	private String reviewDate;

	private Integer weather;

	private Integer conditions;

	private Integer crowd;



	public Integer getResortId() {
		return resortId;
	}
	public void setResortId(Integer resortId) {
		this.resortId = resortId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer reviewid) {
		this.id = reviewid;		
	}
	public Date getReviewdate() {
		return new Date(Long.parseLong(reviewDate));
	}
	public void setReviewdate(String reviewdate) {
		this.reviewDate = reviewdate;		
	}	
	public Integer getWeather() {
		return weather;
	}
	public void setWeather(Integer weather) {
		this.weather = weather;
	}
	public Integer getConditions() {
		return conditions;
	}
	public void setConditions(Integer conditions) {
		this.conditions = conditions;
	}
	public Integer getCrowd() {
		return crowd;
	}
	public void setCrowd(Integer crowd) {
		this.crowd = crowd;
	}
	@Override
	public String toString() {
		return "ReviewBean [id=" + id + ", resortId=" + resortId
				+ ", reviewdate=" + reviewDate + ", weather=" + weather
				+ ", conditions=" + conditions + ", crowd=" + crowd + "]";
	}

}
