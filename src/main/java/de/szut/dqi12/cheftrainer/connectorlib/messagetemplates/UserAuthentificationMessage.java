package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.User;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messages.Message;

/**
 * The {@link UserAuthentificationMessage} should be used, when a user wants to login or registrate to the server.
 * @author Alexander Brennecke
 *
 */
public class UserAuthentificationMessage extends Message{
	private final static String ID = ClientToServer_MessageIDs.USER_AUTHENTICATION;
	private final static String USER  = "User";
	private User user;
	private String authentificationType;
	
	public UserAuthentificationMessage(JSONObject json){
		super(ID);
		user = new User(json.getJSONObject(USER));
		authentificationType = json.getString(MIDs.AUTHENTIFICATION_TYPE);
	}
	
	public UserAuthentificationMessage() {
		super(ID);
	}

	
	@Override
	public void createMessageContent(){
		JSONObject message = new JSONObject();
		message.put(MIDs.AUTHENTIFICATION_TYPE, authentificationType);
		message.put(USER,user.toJSON());
		
		messageContent = message.toString();
	}


	// GETTER AND SETTER
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getAuthentificationType() {
		return authentificationType;
	}


	public void setAuthentificationType(String authentificationType) {
		this.authentificationType = authentificationType;
	}
	
	
}
