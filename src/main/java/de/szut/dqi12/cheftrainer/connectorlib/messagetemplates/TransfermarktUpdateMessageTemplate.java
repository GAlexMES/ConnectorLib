package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;


import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messages.Message;

abstract class TransfermarktUpdateMessageTemplate extends MessageTemplate{
	private final static String ID = ClientToServer_MessageIDs.TRANSFER_MARKET;
	private String messageType;
	private JSONObject content;
	private Message message;
	
	public TransfermarktUpdateMessageTemplate(String messageType) {
		super(ID);
		this.messageType = messageType;
	}
	
	@Override
	public void createMessageContent(){
		JSONObject information = createJSON();
		
		JSONObject message = new JSONObject();
		message.put(MIDs.TYPE, messageType);
		message.put(MIDs.INFORMATION, information);
		
		messageContent = message.toString();
	}
	
	abstract JSONObject createJSON();


}
