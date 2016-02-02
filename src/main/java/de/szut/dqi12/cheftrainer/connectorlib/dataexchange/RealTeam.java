package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.ArrayList;
import java.util.List;

/**
 * The RealTeam class is used to present a RealTeam, not the team of a manager.
 * @author Alexander Brennecke
 *
 */
public class RealTeam {
	public static final String TEAM = "team";
	
	private String teamUrl;
	private String teamName;
	private String logoURL;
	private List<Player> playerList = new ArrayList<Player>();

	 // GETTER AND SETTER
	public String getTeamUrl() {
		return teamUrl;
	}
	public void setTeamUrl(String teamUrl) {
		this.teamUrl = teamUrl;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public List<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
	public String getLogoURL() {
		return logoURL;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}
	
}
