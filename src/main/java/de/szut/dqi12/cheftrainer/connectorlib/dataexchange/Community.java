package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Community Model class, which saves all necessary for a {@link Community}.
 * @author Robin
 */
public class Community extends Sendable {
	
	public static final String COMMUNITY_ID = "communityID";
	public static final String COMMUNITY_NAME = "communityNAME";

	private int communityID;
	private String name;
	private List<Manager> managers;
	private Map<Integer, Manager> managerDictionary;
	private Market market;
	private Manager usersManager;

	public Community() {
		managers = new ArrayList<>();
		managerDictionary = new HashMap<Integer, Manager>();
	}

	/**
	 * To initialize a {@link Community} via a JSON String.
	 * @param json the JSONObject, which contains all information for the community.
	 */
	public Community(JSONObject json) {
		this();
		setCommunityID(json.getInt(COMMUNITY_ID));
		setName(json.getString(COMMUNITY_NAME));
		JSONArray managersJSON = json.getJSONArray(Manager.MANAGER_LIST);
		for (int i = 0; i < managersJSON.length(); i++) {
			JSONObject managerJSON = new JSONObject(managersJSON.get(i).toString());
			Manager manager = new Manager(managerJSON,name);
			addManager(manager);
		}
		setMarket(new Market(json.getJSONObject(Market.MARKET)));
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject retval = new JSONObject();
		retval.put(COMMUNITY_ID, communityID);
		retval.put(COMMUNITY_NAME, name);
		JSONArray managersJSON = new JSONArray();
		for (Manager m : managers) {
			managersJSON.put(m.toJSON());
		}
		retval.put(Manager.MANAGER_LIST, managersJSON);
		retval.put(Market.MARKET, market.toJSON());
		return retval;
	}

	/**
	 * This function sets the userManager variable to the {@link Manager} object, which will be found by the the given UserName in this Community
	 * @param userName the Name of the {@link User}, who ownes the {@link Manager}
	 */
	public void findeUsersManager(String userName) {
		for (Integer i : managerDictionary.keySet()) {
			if (managerDictionary.get(i).getName().equals(userName)) {
				usersManager = managerDictionary.get(i);
				break;
			}
		}
	}

	// GETTER AND SETTER
	
	public Community(Market market) {
		this();
		this.market = market;
	}

	public void addManager(Manager manager) {
		this.managers.add(manager);
		managerDictionary.put(manager.getID(), manager);
	}

	public void addManagers(List<Manager> managerList) {
		managers.addAll(managerList);
		for (Manager m : managerList) {
			managerDictionary.put(m.getID(), m);
		}
	}

	public void setUsersManager(Manager m) {
		this.usersManager = m;
	}

	public Manager getUsersManager() {
		return usersManager;
	}

	public Manager getManager(int ID) {
		return managerDictionary.get(ID);
	}

	public List<Manager> getManagers() {
		return managers;
	}

	public Market getMarket() {
		return market;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCommunityID() {
		return communityID;
	}

	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

}
