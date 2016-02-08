package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ServerToClient_MessageIDs;

public class SaveFormationAckMessage extends MessageTemplate {

	private final static String ID= ServerToClient_MessageIDs.SAVE_FORMATION_ACK;
	private boolean successful;
	
	public SaveFormationAckMessage(boolean successful) {
		super(ID);
		this.successful=successful;
	}

	
	public SaveFormationAckMessage(JSONObject json) {
		super(ID);
		successful = json.getBoolean(MIDs.SUCCESFULL);
	}
	
	
	@Override
	public void createMessageContent() {
		JSONObject json = new JSONObject();
		json.put(MIDs.SUCCESFULL, successful);
		messageContent = json.toString();
	}
	
	public boolean isSuccessful(){
		return successful;
	}

}
