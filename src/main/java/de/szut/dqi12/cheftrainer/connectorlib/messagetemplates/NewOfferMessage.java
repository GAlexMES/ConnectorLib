package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Market;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Player;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Transaction;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

/**
 * The NewOfferMessage should be used by the client application. It should be used, when a user made an offer for a {@link Player} on the {@link Market}.
 * @author Alexander Brennecke
 *
 */
public class NewOfferMessage extends TransfermarktUpdateMessageTemplate{
	private final static String ID = MIDs.NEW_OFFER;
	
	private Transaction transaction;
	
	/**
	 * Constructor
	 * @param tr a full initialized {@link Transaction} object, which represents the offer of the user.
	 */
	public NewOfferMessage(Transaction tr) {
		super(ID);
		this.transaction = tr;
	}
	
	/**
	 * JSON Constructor
	 * @param json the {@link JSONObject}. Create it via "createMessageContent()".
	 */
	public NewOfferMessage(JSONObject json){
		super(ID);
		transaction = new Transaction(json);
	}
	
	@Override
	public JSONObject createJSON(){
		return transaction.toJSON();
	}
	
	//GETTER AND SETTER
	public Transaction getTransaction(){
		return transaction;
	}
}
