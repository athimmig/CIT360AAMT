import java.util.ArrayList;

public class HibernateMain {

	public static void main(String[] args) {

		System.out.println("Start Test");
		RhymeModel RhymeModel = new RhymeModel();
		
		ArrayList<RhymeBank> words = RhymeModel.getAllRhymingWords("Air");
		
		int numberWords = words.size();
		for (int i = 0; i < numberWords; i++) {
			System.out.println(words.get(i).toString());
		}
	}

}
