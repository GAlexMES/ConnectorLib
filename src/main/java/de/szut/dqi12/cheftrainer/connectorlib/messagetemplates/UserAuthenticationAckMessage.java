package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ServerToClient_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.utils.JSONUtils;

public class UserAuthenticationAckMessage extends MessageTemplate {
	private static final String ID = ServerToClient_MessageIDs.USER_AUTHENTICATION_ACK;
	private String type;

	private Map<String, Boolean> feedback = new HashMap<String, Boolean>();
	private int userID;

	public UserAuthenticationAckMessage(String type) {
		super(ID);
		this.type = type;
		init();
	}

	public UserAuthenticationAckMessage(JSONObject json) {
		super(ID);
		init();
		type = json.getString(MIDs.TYPE);
		for (String s : json.keySet()) {
			try {
				feedback.put(s, json.getBoolean(s));
			} 
			catch (JSONException jse) {}
		}
		userID = json.getInt(MIDs.USER_ID);
	}

	private void init() {
		feedback.put(MIDs.PASSWORD, false);
		feedback.put(MIDs.USER_EXISTS, false);
		feedback.put(MIDs.EMAIL_EXISTS, false);
		feedback.put(MIDs.AUTHENTICATE, false);
		userID = -1;
	}

	@Override
	public void createMessageContent() {
		JSONObject json = JSONUtils.mapToJSON(feedback);
		json.put(MIDs.TYPE, type);
		json.put(MIDs.USER_ID, userID);
		messageContent = json.toString();
	}

	public void setCorrectPassword(boolean flag) {
		feedback.put(MIDs.PASSWORD, flag);
	}

	public void setUserExists(boolean flag) {
		feedback.put(MIDs.USER_EXISTS, flag);
	}

	public void setEMailExists(boolean flag) {
		feedback.put(MIDs.EMAIL_EXISTS, flag);
	}

	public void setAuthentication(boolean flag) {
		feedback.put(MIDs.AUTHENTICATE, flag);
	}

	public boolean isPasswordCorrect() {
		return feedback.get(MIDs.PASSWORD);
	}

	public boolean existsUser() {
		return feedback.get(MIDs.USER_EXISTS);
	}

	public boolean existsEMail() {
		return feedback.get(MIDs.EMAIL_EXISTS);
	}

	public boolean isAuthentication() {
		return feedback.get(MIDs.AUTHENTICATE);
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getType() {
		return this.type;
	}

}
