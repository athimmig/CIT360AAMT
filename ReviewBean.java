import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "resort_reviews")


public class ReviewBean {

	  @Id 
	  @GeneratedValue
	  private Integer reviewid;
	  
	  @Column(name = "resort_id")
	  private Integer resortid;
	 
	  @Column(name = "review_date")
	  private String reviewdate;
	  
	  @Column(name = "weather")
	  private Integer weather;
	  
	  @Column(name = "conditions")
	  private Integer conditions;
	  
	  @Column(name = "crowd")
	  private Integer crowd;
	  
	  
	public Integer getReviewid() {
		return reviewid;
	}
	public void setReviewid(Integer reviewid) {
		this.reviewid = reviewid;		
	}
	public Integer getResortid() {
		return resortid;
	}
	public void setResortid(Integer resortid) {
		this.resortid = resortid;		
	}
	public Date getReviewdate() {
		return new Date(Long.parseLong(reviewdate));
	}
	public void setReviewdate(String reviewdate) {
		this.reviewdate = reviewdate;		
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
	
}
