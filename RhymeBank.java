import javax.persistence.*;


@Entity
@Table(name = "word_bank")
public class RhymeBank {

	@Id
	@GeneratedValue
	@Column(name = "word_id")
	private Integer id;
	@Column(name = "word")
	private String word;
	@Column(name = "rhyme_type")
	private String rhymeType;
	
	public String toString() {
		return "Id: " + id + " Word: " + word + " Rhyme Type: " + rhymeType;
	}
	
}
