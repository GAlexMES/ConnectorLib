package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;


import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Market;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Transaction;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

/**
 * The {@link TransfermarktUpdateMessageTemplate} is used by various messages, that do something with the {@link Market} or {@link Transaction}s.
 * @author Alexander Brennecke
 *
 */
abstract class TransfermarktUpdateMessageTemplate extends MessageTemplate{
	private final static String ID = ClientToServer_MessageIDs.TRANSFER_MARKET;
	private String messageType;
	
	/**
	 * Constructor
	 * @param messageType the ID of the message, the extends the {@link TransfermarktUpdateMessageTemplate}
	 */
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
