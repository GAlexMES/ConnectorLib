package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.Map;

import org.json.JSONObject;

/**
 * 
 * @author Robin
 *
 */
public class Transaction extends Sendable {
	
	private int offeredPrice;
	private int playerSportalID;
	private int communityID;
	private int userID;
	private int managerID;
	
	private boolean outgoing;
	private Player player;
	
	public Transaction(){}
	
	public Transaction(int price, int playerID, int communityID, int userID){
		this.offeredPrice = price;
		this.playerSportalID = playerID;
		this.communityID = communityID;
		this.userID = userID;
	}
	
	public void takeInformation(Session s){
		outgoing = s.getCurrentManager().getID() == managerID;
		Map<Integer,Player> players = s.getCurrentCommunity().getMarket().getPlayerMap();
		player= players.get(playerSportalID);
	}
	
	public Player getPlayer() {
		return player;
	}

	public int getOfferedPrice() {
		return offeredPrice;
	}

	public int getPlayerSportalID() {
		return playerSportalID;
	}

	public int getCommunityID() {
		return communityID;
	}

	public int getUserID() {
		return userID;
	}
	
	public int getManagerID() {
		return managerID;
	}

	public void setOfferedPrice(int offeredPrice) {
		this.offeredPrice = offeredPrice;
	}

	public void setPlayerSportalID(int playerSportalID) {
		this.playerSportalID = playerSportalID;
	}

	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
	
	public Transaction(JSONObject json){
		offeredPrice = json.getInt("Preis");
		playerSportalID = json.getInt("SportalID");
		communityID = json.getInt("SpielrundenID");
		userID = json.getInt("UserID");
		managerID = json.getInt("ManagerID");
	}
	
	public JSONObject toJSON(){
		JSONObject retval = new JSONObject();
		retval.put("Preis", offeredPrice);
		retval.put("SportalID",  playerSportalID);
		retval.put("SpielrundenID", communityID);
		retval.put("UserID", userID);
		retval.put("ManagerID", managerID);
		return retval;
	}

	public boolean isOutgoing() {
		return outgoing;
	}
	
	
}
