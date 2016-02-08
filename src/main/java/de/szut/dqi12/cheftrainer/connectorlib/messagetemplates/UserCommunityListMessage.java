package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Community;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Transaction;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ServerToClient_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.utils.JSONUtils;

public class UserCommunityListMessage extends MessageTemplate {

	private static final String ID = ServerToClient_MessageIDs.USER_COMMUNITY_LIST;
	private String type;
	private List<Transaction> transactions = new ArrayList<>();
	private List<Community> communityList = new ArrayList<>();

	public UserCommunityListMessage(String type) {
		super(ID);
		this.type = type;
	}

	public UserCommunityListMessage(JSONObject json) {
		super(ID);
		type = json.getString(MIDs.TYPE);
		switch (type) {
		case MIDs.UPDATE_COMMUNITY:
			JSONArray transactionJSON = json.getJSONArray(MIDs.TRANSACTIONS);
			for(int i = 0; i<transactionJSON.length();i++){
				transactions.add(new Transaction(transactionJSON.getJSONObject(i)));
			}
			break;
		case MIDs.NEW_COMMUNITY:
		case MIDs.INIT:
			JSONArray communityJSON = json.getJSONArray(MIDs.COMMUNITY);
			for(int i = 0; i<communityJSON.length();i++){
				communityList.add(new Community(communityJSON.getJSONObject(i)));
			}
			break;
		}
	}

	@Override
	public void createMessageContent() {
		JSONObject json = new JSONObject();
		json.put(MIDs.TYPE, type);

		switch (type) {
		case MIDs.UPDATE_COMMUNITY:
			JSONArray transactionJSON = JSONUtils.listToJSON(transactions);
			json.put(MIDs.TRANSACTIONS, transactionJSON);
			break;
		case MIDs.NEW_COMMUNITY:
		case MIDs.INIT:
			JSONArray communityJSON = JSONUtils.listToJSON(communityList);
			json.put(MIDs.COMMUNITY, communityJSON);
			break;
		}
		messageContent = json.toString();
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public List<Community> getCommunityList() {
		return communityList;
	}

	public void setCommunityList(List<Community> communityList) {
		this.communityList = communityList;
	}
	
	public void addCommunity(Community com){
		communityList.add(com);
	}
	
	public Community getCommunity(){
		return communityList.get(0);
	}
	
	public String getType(){
		return type;
	}
	
	

}
