package handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import models.SearchModel;

import org.apache.commons.lang3.StringUtils;
import org.quickconnectfamily.json.JSONOutputStream;

import beans.CommunicationBean;
import beans.ResortBean;

/*
 * This handler performs a search using either a provided zip code,
 * resort name, or city state string
 */
public class SearchHandler implements Runnable {

	ArrayList parameters;
	private JSONOutputStream out;

	public SearchHandler(ArrayList params, JSONOutputStream outToClient) {
		parameters = params;
		out = outToClient;
	}

	@Override
	public void run() {

		//TO DO
		//validate parameters

		ArrayList<ResortBean> resorts;
		String[] cityState;

		//first try the zip code
		SearchModel model = new SearchModel();
		resorts = model.getResortsFromZipCode((String)parameters.get(0), Integer.parseInt((String)parameters.get(1)));

		//if there were no results try the resort name
		if (resorts.size() == 0) {
			resorts = model.getResortsFromName((String)parameters.get(0), Integer.parseInt((String)parameters.get(1)));
		}

		//if there were no results try the city state string
		if (resorts == null) {
			cityState = this.parseCityState((String)parameters.get(0));
			if (cityState != null) {
				resorts = model.getResortsFromCityState(cityState[0], cityState[1], Integer.parseInt((String)parameters.get(1)));
			}
		}

		//TO DO
		//add search from given latitude and longitude

		//Send the data back
		CommunicationBean data = new CommunicationBean();
		data.setCommand("handle_search_results");
		data.setData((ArrayList)resorts);

		try {
			out.writeObject(data);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String[] parseCityState(String cityState) {
		HashMap<String, String> stateList = new HashMap<String, String>(); 
		stateList.put("AL", "Alabama");
		stateList.put("AK", "Alaska");
		stateList.put("AZ", "Arizona");
		stateList.put("AR", "Arkansas");
		stateList.put("CA", "California");
		stateList.put("CO", "Colorado");
		stateList.put("CT", "Connecticut");
		stateList.put("DE", "Delaware");
		stateList.put("DC", "District Of Columbia");
		stateList.put("FL", "Florida");
		stateList.put("GA", "Georgia");
		stateList.put("HI", "Hawaii");
		stateList.put("ID", "Idaho");
		stateList.put("IL", "Illinois");
		stateList.put("IN", "Indiana");
		stateList.put("IA", "Iowa");
		stateList.put("KS", "Kansas");
		stateList.put("KY", "Kentucky");
		stateList.put("LA", "Louisiana");
		stateList.put("ME", "Maine");
		stateList.put("MD", "Maryland");
		stateList.put("MA", "Massachusetts");
		stateList.put("MI", "Michigan");
		stateList.put("MN", "Minnesota");
		stateList.put("MS", "Mississippi");
		stateList.put("MO", "Missouri");
		stateList.put("MT", "Montana");
		stateList.put("NE", "Nebraska");
		stateList.put("NV", "Nevada");
		stateList.put("NH", "New Hampshire");
		stateList.put("NJ", "New Jersey");
		stateList.put("NM", "New Mexico");
		stateList.put("NY", "New York");
		stateList.put("NC", "North Carolina");
		stateList.put("ND", "North Dakota");
		stateList.put("OH", "Ohio");
		stateList.put("OK", "Oklahoma");
		stateList.put("OR", "Oregon");
		stateList.put("PA", "Pennsylvania");
		stateList.put("RI", "Rhode Island");
		stateList.put("SC", "South Carolina");
		stateList.put("SD", "South Dakota");
		stateList.put("TN", "Tennessee");
		stateList.put("TX", "Texas");
		stateList.put("UT", "Utah");
		stateList.put("VT", "Vermont");
		stateList.put("VA", "Virginia");
		stateList.put("WA", "Washington");
		stateList.put("WV", "West Virginia");
		stateList.put("WI", "Wisconsin");
		stateList.put("WY", "Wyoming");

		ArrayList<String> parts = new ArrayList<String>();
		String state = "";
		String city = "";

		// lets see if the comma forms a valid state and city
		if(cityState.indexOf(",") != -1){
			parts = new ArrayList<String>();
			parts.addAll(Arrays.asList(cityState.split(",")));
			state = parts.get(1).trim();
			city = parts.get(0).trim();
		}
		if(!stateList.containsKey(state.toUpperCase()) && !stateList.containsValue(StringUtils.capitalize((state.toLowerCase())))){
			parts = new ArrayList<String>();
			parts.addAll(Arrays.asList(cityState.split(",")));
			state = parts.get(parts.size() - 1);
			parts.remove(parts.size() - 1);
			// first see if the last array element is a state abbreviation
			if(state.length() == 2 && stateList.containsKey(state.toUpperCase())){
				state = state.toUpperCase();
				city = StringUtils.capitalize(StringUtils.join(parts.toArray(), ' '));
			} else {
				// since it's not an abbreviation let's see if the last element is the full name of a state
				if(stateList.containsValue(state.toLowerCase())){
					state = StringUtils.capitalize(state.toLowerCase());
					city = StringUtils.join(parts, ' ');
					//check if this could be the wrong state (i.e. virginia could be west virginia)
					if(stateList.containsValue(StringUtils.capitalize(parts.get(parts.size() - 1).toLowerCase() + state))){
						state = StringUtils.capitalize((parts.get(parts.size() - 1) + state).toLowerCase());
						parts.remove(parts.size() - 1);
						city = StringUtils.join(parts.toArray(), ' ');
					}
				} else {
					// we need at least 2 words left to continue
					if(parts.size() < 2) return null;
					state = parts.get(parts.size() - 1) + ' ' + state;
					parts.remove(parts.size() - 1);
					if(stateList.containsValue(StringUtils.capitalize(state.toLowerCase()))) {
						state = StringUtils.capitalize(state.toLowerCase());
						city = StringUtils.join(parts.toArray(), ' ');
					} else {
						// we need at least 2 words left to continue
						if(parts.size() < 2) return null;
						// check if the 3rd word from the end forms a valid state name
						state = parts.get(parts.size() - 1) + ' ' + state;
						parts.remove(parts.size() - 1);
						if(stateList.containsValue(StringUtils.capitalize(state.toLowerCase()))){
							state = StringUtils.capitalize(state.toLowerCase());
							city = StringUtils.join(parts.toArray(), ' ');
						} else {
							return null;
						}
					}
				}
				state = this.getKeyByValue(stateList, state);
			}
		}
		if (state.length() > 2) {
			state = this.getKeyByValue(stateList, state);
		}
		String[] returnString = {city, state};
		return returnString;
	}

	//looks to see if a key exists in a HashMap
	private String getKeyByValue(HashMap<String, String> map, String value) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}
}
