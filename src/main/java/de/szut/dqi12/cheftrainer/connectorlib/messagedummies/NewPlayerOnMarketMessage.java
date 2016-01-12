package de.szut.dqi12.cheftrainer.connectorlib.messagedummies;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Community;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Manager;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Player;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messages.Message;

public class NewPlayerOnMarketMessage extends Message{
	
	private final static String ID = ClientToServer_MessageIDs.TRANSFER_MARKET;
	private Player player;
	private int managerID;
	private int communityID;
	
	public NewPlayerOnMarketMessage(JSONObject json){
		super(ID);
		managerID = json.getInt(Manager.MANAGER_ID);
		communityID = json.getInt(Community.COMMUNITY_ID);
		JSONObject playerJSON= json.getJSONObject(Player.PLAYER);
		player = new Player(playerJSON);
	}
	
	public NewPlayerOnMarketMessage(){
		super(ID);
	}
	
	public void createMessage(){
		JSONObject playerObject = player.toJSON();
		
		JSONObject messageContent = new JSONObject();
		messageContent.put(MIDs.TYPE, MIDs.NEW_MARKET_PLAYER);
		
		messageContent.put(Manager.MANAGER_ID, managerID);
		messageContent.put(Community.COMMUNITY_ID, communityID);
		messageContent.put(Player.PLAYER, playerObject);
		
		setMessageContent(messageContent);
	}
	
	@Override
	public String getMessageContent() {
		createMessage();
		return messageContent;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public int getCommunityID() {
		return communityID;
	}

	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}
	
	
}
