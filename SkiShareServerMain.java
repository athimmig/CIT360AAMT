import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SkiShareServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SearchModel model = new SearchModel();
//		ArrayList<ResortBean> resorts = model.getAllResorts();
//		printResorts(resorts);
		
		ResortBean resort = model.getResort("Kelly Canyon");
		System.out.println(resort.toString());
	}
	
	public static void printResorts(ArrayList<ResortBean> resorts) {
		try {
			int numberResorts = resorts.size();
			for (int i = 0; i < numberResorts; i++) {
				System.out.println(resorts.get(i).toString() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
