package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import javafx.beans.property.SimpleStringProperty;

/**
 * The Manager class stores all information about an {@link User}s manager in one {@link Community}.
 * 
 * @author Robin
 * @custom.position /D0030/ 
 */
public class Manager extends Sendable{
	
	public static final String MANAGER_ID = "managerID";
	public static final String MANAGER_LIST = "managerList";
	public static final String POINTS = "points";
	public static final String MONEY ="money";
	public static final String NAME ="name";
	public static final String FORMATION ="formation";
	

	private int id;
	private String name;
	private Double money;
	private Integer teamWorth;
	private List<Player> players;
	private List<Player> lineUp;
	private Formation formation;
	private int points;
	private int rang;
	private List<Transaction> transactions;
	private Market market;

	private final SimpleStringProperty communityNameProperty;
	private final SimpleStringProperty teamWorthProperty;
	private final SimpleStringProperty rangProperty;

	public Manager() {
		this(null, 0, 0, null);
	}

	public Manager(String managerName, Integer teamWorth, Integer rang, String communityName) {
		communityNameProperty = new SimpleStringProperty(communityName);
		teamWorthProperty = new SimpleStringProperty(teamWorth + "€");
		rangProperty = new SimpleStringProperty(String.valueOf(rang));

		this.name = managerName;
		this.teamWorth = teamWorth;
		this.rang = rang;
		this.players = new ArrayList<Player>();
		this.lineUp = new ArrayList<Player>();
	}

	public Manager(JSONObject managerJSON) {
		this(managerJSON, "");
	}

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

		communityNameProperty = new SimpleStringProperty(communityName);
		teamWorthProperty = new SimpleStringProperty(teamWorth + "€");
		rangProperty = new SimpleStringProperty(String.valueOf(0));
	}

	public JSONObject toJSON() {
		JSONObject managerJSON = new JSONObject();
		managerJSON.put(MIDs.POINTS, this.getPoints());
		managerJSON.put(MONEY, this.getMoney());
		managerJSON.put(NAME, this.getName());
		managerJSON.put(MIDs.ID, this.getID());
		managerJSON.put(RealTeam.TEAM, teamToJson(this.getPlayers()));
		managerJSON.put(FORMATION, this.getFormation().toJSON());
		return managerJSON;
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

	public List<Player> getLineUp() {
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
}
