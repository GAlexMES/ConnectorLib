package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Robin
 *
 */
public class Manager extends Sendable{

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

		this.name = managerJSON.getString("Name");
		this.points = managerJSON.getInt("Points");
		this.setID(managerJSON.getInt("ID"));

		JSONArray managersTeam = managerJSON.getJSONArray("Team");
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

		JSONObject formationJSON = managerJSON.getJSONObject("Formation");
		this.setFormation(new Formation(formationJSON));

		communityNameProperty = new SimpleStringProperty(communityName);
		teamWorthProperty = new SimpleStringProperty(teamWorth + "€");
		rangProperty = new SimpleStringProperty(String.valueOf(0));
	}

	public JSONObject toJSON() {
		JSONObject managerJSON = new JSONObject();
		managerJSON.put("Points", this.getPoints());
		managerJSON.put("Money", this.getMoney());
		managerJSON.put("Name", this.getName());
		managerJSON.put("ID", this.getID());
		managerJSON.put("Team", teamToJson(this.getPlayers()));
		managerJSON.put("Formation", this.getFormation().toJSON());
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
