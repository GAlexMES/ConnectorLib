package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.cipher.CipherFactory;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

public class CommunityAuthenticationMessage extends MessageTemplate	 {

	private static final String ID = ClientToServer_MessageIDs.COMMUNITY_AUTHENTICATION;
	private String type;
	private String name;
	private String password;
	
	public CommunityAuthenticationMessage(String type) {
		super(ID);
		this.type= type;
	}
	
	public CommunityAuthenticationMessage(JSONObject json) {
		super(ID);
		type = json.getString(MIDs.TYPE);
		name = json.getString(MIDs.COMMUNITY_NAME);
		password = json.getString(MIDs.PASSWORD);
	}

	@Override
	public void createMessageContent() {
		JSONObject message = new JSONObject();
		message.put(MIDs.TYPE, type);
		message.put(MIDs.COMMUNITY_NAME, name);
		message.put(MIDs.PASSWORD, password);
		messageContent = message.toString();
	}

	//GETTER AND SETTER
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			this.password = CipherFactory.getMD5(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public String getType(){
		return this.type;
	}
	
}
	