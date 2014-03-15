import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "resort_reviews")
public class ReviewBean {

	  @Id 
	  @GeneratedValue
	  private Integer id;
	 
	  @Column(name = "review_date")
	  private String reviewdate;
	  
	  private Integer weather;
	  
	  private Integer conditions;
	  
	  private Integer crowd;
	  
	  @ManyToOne
	  @JoinColumn(name="resort_id", referencedColumnName="id")
	  private ResortBean resort;
	  
	  
	public ResortBean getResort() {
		return resort;
	}
	public void setResort(ResortBean resort) {
		this.resort = resort;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer reviewid) {
		this.id = reviewid;		
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
	@Override
	public String toString() {
		return "ReviewBean [id=" + id + ", reviewdate=" + reviewdate
				+ ", weather=" + weather + ", conditions=" + conditions
				+ ", crowd=" + crowd + ", resort=" + resort + "]";
	}
	
}
