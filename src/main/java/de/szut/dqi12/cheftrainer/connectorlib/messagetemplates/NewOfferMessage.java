package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Transaction;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

public class NewOfferMessage extends TransfermarktUpdateMessageTemplate{
	private final static String ID = MIDs.NEW_OFFER;
	
	private Transaction transaction;
	
	public NewOfferMessage(Transaction tr) {
		super(ID);
		this.transaction = tr;
	}
	
	public NewOfferMessage(JSONObject json){
		super(ID);
		transaction = new Transaction(json);
	}
	
	public JSONObject createJSON(){
		return transaction.toJSON();
	}
	
	//GETTER AND SETTER
	public Transaction getTransaction(){
		return transaction;
	}
}
