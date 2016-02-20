package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

/**
 * The Manager class stores all information about an {@link User}s manager in
 * one {@link Community}.
 * 
 * @author Robin
 * @see /D0030/
 */
public class Manager extends Sendable {

	public static final String MANAGER_ID = "managerID";
	public static final String MANAGER_LIST = "managerList";
	public static final String POINTS = "points";
	public static final String MONEY = "money";
	public static final String NAME = "name";
	public static final String FORMATION = "formation";
	public static final String STATS = "statistics";
	public static final String PLACE = "place";

	private int id;
	private String name;
	private Double money;
	private Integer teamWorth;
	private List<Player> players;
	private List<Player> lineUp;
	private Formation formation;
	private int points;
	private int place;
	private List<Transaction> transactions;
	private Market market;
	private Map<Integer, Integer> history;

	private final SimpleStringProperty communityNameProperty;
	private final SimpleStringProperty teamWorthProperty;
	private final SimpleStringProperty rangProperty;

	/**
	 * Default Constructor, creates an empty object.
	 */
	public Manager() {
		this(null, 0, 0, null);
	}

	/**
	 * This constructor should be used, when the Manager should be initialized
	 * by a {@link JSONObject}.
	 * 
	 * @param managerJSON
	 *            the {@link JSONObject}, which includes all neccessary
	 *            information, to initialize a Manager.
	 */
	public Manager(JSONObject managerJSON) {
		this(managerJSON, "");
	}

	/**
	 * Constructor
	 * 
	 * @param managerName
	 *            the name of the manager (should be the name of the
	 *            {@link User}, who owns him.
	 * @param teamWorth
	 *            the worth of the team, which is owned by this manager
	 * @param place
	 *            the place in the community
	 * @param communityName
	 *            the name of the community, in which this manager plays.
	 */
	public Manager(String managerName, Integer teamWorth, Integer place, String communityName) {
		communityNameProperty = new SimpleStringProperty(communityName);
		teamWorthProperty = new SimpleStringProperty(teamWorth + "€");
		rangProperty = new SimpleStringProperty(String.valueOf(place));

		this.name = managerName;
		this.teamWorth = teamWorth;
		this.place = place;
		this.players = new ArrayList<Player>();
		this.lineUp = new ArrayList<Player>();
	}

	/**
	 * This constructor is used, when a manager should be declared by the a
	 * {@link JSONObject} and an {@link Community} name
	 * 
	 * @param managerJSON
	 *            the {@link JSONObject} with all necessary information
	 * @param communityName
	 *            the name of the {@link Community}, in which this manager
	 *            should play.
	 */
	public Manager(JSONObject managerJSON, String communityName) {
		this.players = new ArrayList<Player>();
		this.lineUp = new ArrayList<Player>();

		this.name = managerJSON.getString(NAME);
		this.points = managerJSON.getInt(MIDs.POINTS);
		this.setID(managerJSON.getInt(MIDs.ID));

		JSONArray managersTeam = managerJSON.getJSONArray(RealTeam.TEAM);
		teamWorth = 0;
		for (int m = 0; m < managersTeam.length(); m++) {
			JSONObject playerJSON = managersTeam.getJSONObject(m);
			Player tempPlayer = new Player(playerJSON);
			players.add(tempPlayer);
			if (tempPlayer.isPlays()) {
				lineUp.add(tempPlayer);
			}
			teamWorth = teamWorth + tempPlayer.getWorth();
		}

		JSONObject formationJSON = managerJSON.getJSONObject(FORMATION);
		this.setFormation(new Formation(formationJSON));

		
		try {
			String stats = managerJSON.getJSONObject(STATS).toString();
			if (!stats.equals("")) {
				ObjectMapper objectMapper = new ObjectMapper();
				TypeReference<Map<Integer, Integer>> typeRef = new TypeReference<Map<Integer, Integer>>() {
				};
				Map<Integer, Integer> jsonHistory;
				jsonHistory = objectMapper.readValue(stats, typeRef);
				this.setHistory(jsonHistory);
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

		place = managerJSON.getInt(PLACE);
		communityNameProperty = new SimpleStringProperty(communityName);
		teamWorthProperty = new SimpleStringProperty(teamWorth + "€");
		rangProperty = new SimpleStringProperty(String.valueOf(place));
	}

	@Override
	public JSONObject toJSON() {
		JSONObject managerJSON = new JSONObject();
		managerJSON.put(MIDs.POINTS, this.getPoints());
		managerJSON.put(MONEY, this.getMoney());
		managerJSON.put(NAME, this.getName());
		managerJSON.put(MIDs.ID, this.getID());
		managerJSON.put(RealTeam.TEAM, teamToJson(this.getPlayers()));
		managerJSON.put(FORMATION, this.getFormation().toJSON());
		managerJSON.put(PLACE, place);

		String stats = "{}";
		if (history != null) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				stats = objectMapper.writeValueAsString(history);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		managerJSON.put(STATS, new JSONObject(stats));

		return managerJSON;
	}

	
	//GETTER AND SETTER
	public Map<Integer, Integer> getHistory() {
		return history;
	}

	public void setHistory(Map<Integer, Integer> history) {
		this.history = history;
	}

	private static JSONArray teamToJson(List<Player> playerList) {
		JSONArray teamJSON = new JSONArray();
		for (Player p : playerList) {
			teamJSON.put(p.toJSON());
		}
		return teamJSON;
	}

	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void addPlayer(List<Player> players) {
		Player[] playerArray = players.toArray(new Player[players.size()]);
		addPlayer(playerArray);
	}

	public void addPlayer(Player... player) {
		for (Player p : player) {
			this.players.add(p);
		}
	}

	public List<Player> getLineUp(boolean calculate) {
		if(calculate){
			List<Player> retval = new ArrayList<>();
			for(Player p : players){
				if(p.plays()){
					retval.add(p);
				}
			}
			return retval;
		}
		return lineUp;
	}

	public void setLineUp(List<Player> lineUp) {
		this.lineUp = lineUp;

		List<Integer> sportalIDList = new ArrayList<>();
		for (Player p : lineUp) {
			sportalIDList.add(p.getSportalID());
		}

		for (int i = 0; i < players.size(); i++) {
			if (sportalIDList.contains(players.get(i).getSportalID())) {
				players.get(i).setPlays(true);
			} else {
				players.get(i).setPlays(false);
			}
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMoney() {
		return money;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int getPoints() {
		return points;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public Integer getTeamWorth() {
		return teamWorth;
	}

	public void setTeamWorth(Integer teamWorth) {
		this.teamWorth = teamWorth;
	}

	public SimpleStringProperty getCommunityNameProperty() {
		return communityNameProperty;
	}

	public SimpleStringProperty getTeamWorthProperty() {
		return teamWorthProperty;
	}

	public SimpleStringProperty getRangProperty() {
		return rangProperty;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
