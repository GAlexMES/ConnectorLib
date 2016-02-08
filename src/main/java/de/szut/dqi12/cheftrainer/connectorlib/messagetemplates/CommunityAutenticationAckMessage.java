package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ServerToClient_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.utils.JSONUtils;

public class CommunityAutenticationAckMessage extends MessageTemplate {

	private final static String ID = ServerToClient_MessageIDs.COMMUNITY_AUTHENTICATION_ACK;
	private Map<String, Boolean> enterFeedback = new HashMap<>();
	private String type;

	public CommunityAutenticationAckMessage() {
		super(ID);
		init();
	}

	public CommunityAutenticationAckMessage(JSONObject json) {
		super(ID);
		init();
		type = json.getString(MIDs.TYPE);
		Set<String> keys =  json.keySet();
		for(String s : keys){
			try{
				enterFeedback.put(s, json.getBoolean(s));
			}
			catch(JSONException jse){}
		}
		
	}

	private void init() {
		enterFeedback.put(MIDs.USER_EXISTS, false);
		enterFeedback.put(MIDs.COMMUNITY_EXISTS, false);
		enterFeedback.put(MIDs.CORRECT_PASSWORD, false);
		enterFeedback.put(MIDs.MANAGER_CREATED, false);
	}

	@Override
	public void createMessageContent() {
		JSONObject json = JSONUtils.mapToJSON(enterFeedback);
		json.put(MIDs.TYPE, type);
		messageContent = json.toString();
	}

	public void setEnterType() {
		this.type = MIDs.ENTER;
	}

	public void setCreationType() {
		this.type = MIDs.CREATION;
	}

	public void setUserExists(boolean flag) {
		enterFeedback.put(MIDs.USER_EXISTS, flag);
	}

	public void setCommunityExists(boolean flag) {
		enterFeedback.put(MIDs.COMMUNITY_EXISTS, flag);
	}

	public void setCorrectPassword(boolean flag) {
		enterFeedback.put(MIDs.CORRECT_PASSWORD, flag);
	}

	public void setManagerCreated(boolean flag) {
		enterFeedback.put(MIDs.MANAGER_CREATED, flag);
	}

	public boolean userExists() {
		return enterFeedback.get(MIDs.USER_EXISTS);
	}

	public boolean communityExists() {
		return enterFeedback.get(MIDs.COMMUNITY_EXISTS);
	}

	public boolean correctPassword() {
		return enterFeedback.get(MIDs.CORRECT_PASSWORD);
	}

	public boolean managerCreated() {
		return enterFeedback.get(MIDs.MANAGER_CREATED);
	}

	public String getType() {
		return type;
	}

}
