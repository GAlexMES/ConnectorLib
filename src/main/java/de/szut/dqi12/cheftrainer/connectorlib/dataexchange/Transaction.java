package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

/**
 * 
 * @author Robin
 *
 */
public class Transaction {
	
	//von Robin
	private Double price;
	private boolean outgoing;
	private String tenderer;
	private String receiver;
	private Player player;
	
	
	//von Alex
	private int offeredPrice;
	private int playerSportalID;
	private int communityID;
	private int userID;
	private int managerID;
	
	public Transaction(){}
	
	//von Robin
	public Transaction(Double price, boolean outgoing, String tenderer,
			String receiver, Player player) {
		this.price = price;
		this.outgoing = outgoing;
		this.tenderer = tenderer;
		this.receiver = receiver;
		this.player = player;
	}
	
	public Double getPrice() {
		return price;
	}
	public boolean isOutgoing() {
		return outgoing;
	}
	public String getTenderer() {
		return tenderer;
	}
	public String getReceiver() {
		return receiver;
	}
	public Player getPlayer() {
		return player;
	}
	
	//von Alex
	public Transaction(int price, int playerID, int communityID, int userID){
		this.offeredPrice = price;
		this.playerSportalID = playerID;
		this.communityID = communityID;
		this.userID = userID;
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
	
	
}
