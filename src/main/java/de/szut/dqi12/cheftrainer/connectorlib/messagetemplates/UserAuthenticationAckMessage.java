package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ServerToClient_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.utils.JSONUtils;

/**
 * The {@link UserAuthenticationAckMessage} is used by the server application to inform the client application.
 * It contains a map of information, which can give a positive or a negative feedback to the client.
 * The Map also provides information, that help the client application to inform the user about what exactly went wrong.
 * @author Alexander Brennecke
 *
 */
public class UserAuthenticationAckMessage extends MessageTemplate {
	private static final String ID = ServerToClient_MessageIDs.USER_AUTHENTICATION_ACK;
	private String type;

	private Map<String, Boolean> feedback = new HashMap<String, Boolean>();
	private int userID;

	/**
	 * Constructor
	 * @param type Should be MIDs.REGISTER or MIDs.LOGIN
	 */
	public UserAuthenticationAckMessage(String type) {
		super(ID);
		this.type = type;
		init();
	}
	
	/**
	 * JSON Constructor
	 * @param json the {@link JSONObject}. Create it via "createMessageContent()".
	 */
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

	/**
	 * Defines the feedback with all Key/Value pairs, that will be used, to provide NPE.
	 * Defines the userID with -1.
	 */
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
	
	//GETTER AND SETTER
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
