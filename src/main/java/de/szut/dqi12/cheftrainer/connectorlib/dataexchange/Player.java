package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 * The player class stores all nested information about a player.
 * @author Robin
 * @custom.position /D0020/
 */
public class Player extends Sendable {
	private int worth;
	private String name;
	private int points;
	private int number;
	private String position;
	private int ID;
	private int goals;
	private boolean redCard;
	private boolean yellowRedCard;
	private String teamName;
	private boolean plays;
	private PlayerLabel label;
	private String absolutePictureURL;
	private int sportalID;
	private Date birthdate;

	private MarketPlayer marketPlayer;

	public Player() {
		sportalID = 0;
	}

	public Player(JSONObject playerJSON) {
		getPlayerFromJSON(playerJSON);
		marketPlayer = new MarketPlayer(name, String.valueOf(points), String.valueOf(worth), this);
	}

	public Player(int worth, String name, int points, String position) {
		this.worth = worth;
		this.name = name;
		this.points = points;

		marketPlayer = new MarketPlayer(name, String.valueOf(points), String.valueOf(worth), this);

		goals = 0;
		redCard = false;
		yellowRedCard = false;
		sportalID = 0;
	}

	public Player(String name, String teamName, int points) {
		this.name = name;
		this.points = points;
		this.teamName = teamName;

		marketPlayer = new MarketPlayer(name, String.valueOf(points), "no information available", this);
		sportalID = 0;
	}

	public Player(String name, int points) {
		this.name = name;
		this.points = points;
		sportalID = 0;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public boolean isRedCard() {
		return redCard;
	}

	public void setRedCard(boolean redCard) {
		this.redCard = redCard;
	}

	public boolean isYellowRedCard() {
		return yellowRedCard;
	}

	public void setYellowRedCard(boolean yellowRedCard) {
		this.yellowRedCard = yellowRedCard;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getWorth() {
		return worth;
	}

	public String getPosition() {
		return position;
	}

	public void setWorth(int worth) {
		this.worth = worth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setPlays(boolean plays) {
		this.plays = plays;
	}

	public boolean plays() {
		return this.plays;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAbsolutePictureURL() {
		return absolutePictureURL;
	}

	public void setAbsolutePictureURL(String absolutePictureURL) {
		this.absolutePictureURL = absolutePictureURL;
	}

	public int getSportalID() {
		return sportalID;
	}

	public void setSportalID(int sportalID) {
		this.sportalID = sportalID;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthdateString() {
		String retval = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(birthdate);
			retval = retval + cal.get(Calendar.DAY_OF_MONTH) + ".";
			retval = retval + cal.get(Calendar.MONTH) + ".";
			retval = retval + cal.get(Calendar.YEAR);
			return retval;
		} catch (NullPointerException npe) {
			return "";
		}
	}

	public void setBirthdate(String birthday) {
		Pattern p = Pattern.compile("\\d+\\.\\d+\\.\\d+");
		Matcher m = p.matcher(birthday);
		boolean correctBirthday = m.matches();

		if (correctBirthday) {
			String[] splittedBirthday = birthday.split("\\.");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.valueOf(splittedBirthday[2]));
			cal.set(Calendar.MONTH, Integer.valueOf(splittedBirthday[1]));
			cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(splittedBirthday[0]));
			this.birthdate = cal.getTime();
		}
	}

	@Override
	public JSONObject toJSON() {
		JSONObject retval = new JSONObject();
		retval.put("name", getDefault(this.getName()));
		retval.put("id", getDefault(this.getID()));
		retval.put("number", getDefault(this.getNumber()));
		retval.put("points", getDefault(this.getPoints()));
		retval.put("worth", getDefault(this.getWorth()));
		retval.put("position", getDefault(this.getPosition()));
		retval.put("team", getDefault(this.getTeamName()));
		retval.put("plays", getDefault(this.plays()));
		retval.put("pictureURL", getDefault(this.getAbsolutePictureURL()));
		retval.put("sportalID", getDefault(this.getSportalID()));
		retval.put("birthday", getDefault(this.getBirthdateString()));
		return retval;
	}

	public void getPlayerFromJSON(JSONObject playerJSON) {
		this.setName(playerJSON.getString("name"));
		this.setID(playerJSON.getInt("id"));
		this.setNumber(playerJSON.getInt("number"));
		this.setPoints(playerJSON.getInt("points"));
		this.setWorth(playerJSON.getInt("worth"));
		this.setPosition(playerJSON.getString("position"));
		this.setTeamName(playerJSON.getString("team"));
		this.setPlays(playerJSON.getBoolean("plays"));
		this.setAbsolutePictureURL(playerJSON.getString("pictureURL"));
		this.setSportalID(playerJSON.getInt("sportalID"));
		this.setBirthdate(playerJSON.getString("birthday"));
		marketPlayer = new MarketPlayer(name, String.valueOf(points), "no information available", this);
	}

	private String getDefault(Object o) {
		return getDefault(o, "");
	}

	private String getDefault(Object o, Object defaultValue) {
		if (o != null) {
			return String.valueOf(o);
		} else {
			return String.valueOf(defaultValue);
		}
	}

	public PlayerLabel getLabel() {
		return label;
	}

	public void setLabel(PlayerLabel label) {
		this.label = label;
	}

	public boolean isPlays() {
		return plays;
	}

	public MarketPlayer getMarketPlayer() {
		return marketPlayer;
	}

}
