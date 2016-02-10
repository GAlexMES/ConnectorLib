package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.cipher.CipherFactory;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Community;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

/**
 * The {@link CommunityAuthenticationMessage} should be used by the client. It should be used to either register a new {@link Community} or to enter an existing one.
 * @author Alexander Brennecke
 *
 */
public class CommunityAuthenticationMessage extends MessageTemplate	 {

	private static final String ID = ClientToServer_MessageIDs.COMMUNITY_AUTHENTICATION;
	private String type;
	private String name;
	private String password;
	
	/**
	 * Constructor
	 * @param type should be MIDs.ENTER or MIDs.CREATION
	 */
	public CommunityAuthenticationMessage(String type) {
		super(ID);
		this.type= type;
	}
	
	/**
	 * JSON Constructor
	 * @param json the {@link JSONObject}. Create it via "createMessageContent()".
	 */
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

	/**
	 * The password will be set as MD5 hash. It is not possible to store the password in plain text.
	 * @param password
	 */
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
	