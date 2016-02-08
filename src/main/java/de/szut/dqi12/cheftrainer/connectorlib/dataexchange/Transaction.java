package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.Map;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

/**
 * This class is the model class for each transaction. It stores all necessary data.
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
	
	/**
	 * Default costructor.
	 */
	public Transaction(){}
	
	/**
	 * This constructor is used to set a few parameters directly via the construcot.r
	 * @param price the price, that was offered
	 * @param playerID the ID of the {@link Player}, who is on the {@link Market}. The {@link Player}, who the {@link User} wants to buy.
	 * @param communityID the ID of the {@link Community}, in which the Player plays
	 * @param userID the ID of the {@link User}.
	 */
	public Transaction(long price, int playerID, int communityID, int userID){
		this.offeredPrice = price;
		this.playerSportalID = playerID;
		this.communityID = communityID;
		this.userID = userID;
	}
	
	/**
	 * This constructor should be used, when this object should be initialized by a {@link JSONObject}.
	 * @param json a {@link JSONObject}, which contains all neccessary information
	 */
	public Transaction(JSONObject json){
		offeredPrice = json.getInt(Player.PRICE);
		playerSportalID = json.getInt(Player.SPORTAL_ID);
		communityID = json.getInt(Community.COMMUNITY_ID);
		userID = json.getInt(MIDs.USER_ID);
		managerID = json.getInt(Manager.MANAGER_ID);
	}
	
	@Override
	public JSONObject toJSON(){
		JSONObject retval = new JSONObject();
		retval.put(Player.PRICE, offeredPrice);
		retval.put(Player.SPORTAL_ID,  playerSportalID);
		retval.put(Community.COMMUNITY_ID, communityID);
		retval.put(MIDs.USER_ID, userID);
		retval.put(Manager.MANAGER_ID, managerID);
		return retval;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void takeInformation(Session s){
		outgoing = s.getCurrentManager().getID() == managerID;
		Map<Integer,Player> players = s.getCurrentCommunity().getMarket().getPlayerMap();
		player= players.get(playerSportalID);
	}
	
	//GETTER AND SETTER
	
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
	
	public boolean isOutgoing() {
		return outgoing;
	}
}
