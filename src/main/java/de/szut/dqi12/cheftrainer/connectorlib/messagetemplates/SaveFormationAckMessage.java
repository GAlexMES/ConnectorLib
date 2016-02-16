package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Formation;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ServerToClient_MessageIDs;

/**
 * The SaveFormationAckMessage should be used by the server application to send the acknowledgement for a {@link Formation} change.
 * @author Alexander Brennecke
 *
 */
public class SaveFormationAckMessage extends MessageTemplate {

	private final static String ID= ServerToClient_MessageIDs.SAVE_FORMATION_ACK;
	private static final String SUCCESFUL = "successful";
	private boolean successful;
	
	/**
	 * Constructor
	 * @param successful true= save was successful, false otherwise
	 */
	public SaveFormationAckMessage(boolean successful) {
		super(ID);
		this.successful=successful;
	}

	
	/**
	 * JSON Constructor
	 * @param json the {@link JSONObject}. Create it via "createMessageContent()".
	 */
	public SaveFormationAckMessage(JSONObject json) {
		super(ID);
		successful = json.getBoolean(SUCCESFUL);
	}
	
	
	@Override
	public void createMessageContent() {
		JSONObject json = new JSONObject();
		json.put(SUCCESFUL, successful);
		messageContent = json.toString();
	}
	
	//GETTER AND SETTER
	public boolean isSuccessful(){
		return successful;
	}

}
