package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.Map;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

/**
 * 
 * @author Robin
 *
 */
public class Transaction extends Sendable {
	
	private long offeredPrice;
	private int playerSportalID;
	private int communityID;
	private int userID;
	private int managerID;
	
	private boolean outgoing;
	private Player player;
	
	public Transaction(){}
	
	public Transaction(long price, int playerID, int communityID, int userID){
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

	public long getOfferedPrice() {
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

	public void setOfferedPrice(long offeredPrice) {
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
//	
	public Transaction(JSONObject json){
		offeredPrice = json.getInt(Player.PRICE);
		playerSportalID = json.getInt(Player.SPORTAL_ID);
		communityID = json.getInt(Community.COMMUNITY_ID);
		userID = json.getInt(MIDs.USER_ID);
		managerID = json.getInt(Manager.MANAGER_ID);
	}
	
	public JSONObject toJSON(){
		JSONObject retval = new JSONObject();
		retval.put(Player.PRICE, offeredPrice);
		retval.put(Player.SPORTAL_ID,  playerSportalID);
		retval.put(Community.COMMUNITY_ID, communityID);
		retval.put(MIDs.USER_ID, userID);
		retval.put(Manager.MANAGER_ID, managerID);
		return retval;
	}

	public boolean isOutgoing() {
		return outgoing;
	}
	
	
}
