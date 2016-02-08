package de.szut.dqi12.cheftrainer.connectorlib.messages;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ServerToClient_MessageIDs;

/**
 * A Message object contains and ID and the Content. The ID should be one of the
 * IDs, defined in an class extends MessageIDAbstract.
 * 
 * @author Alexander Brennecke
 *
 */
public class Message {

	private String messageID;
	protected String messageContent;

	/**
	 * Constructor
	 * @param messageID the ID of the message, should be either in {@link ServerToClient_MessageIDs} or {@link ClientToServer_MessageIDs}
	 */
	public Message(String messageID) {
		this.messageID = messageID;
	}

	/**
	 * Constructor
	 * @param messageID the ID of the message, should be either in {@link ServerToClient_MessageIDs} or {@link ClientToServer_MessageIDs}
	 * @param content the content of the message.
	 */
	public Message(String messageID, String content) {
		this.messageID = messageID;
		this.messageContent = content;
	}

	//GETTER AND SETTER
	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getMessageContent() {
		createMessageContent();
		return messageContent;
	}

	public void setMessageContent(JSONObject authentificationInfo) {
		this.messageContent = authentificationInfo.toString();
	}
	
	public void setMessageContent(String messageContent) {
		this.messageContent=messageContent;
	}

	public void createMessageContent() {}


}
