package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Transaction;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

public class TransactionMessage extends TransfermarktUpdateMessageTemplate {

	private final static String ID = MIDs.TRANSACTION;
	public static final String ACCEPT = "accept";
	public static final String REMOVE = "remove";
	
	
	private Transaction transaction;
	private boolean accept;
	private boolean remove;

	public TransactionMessage(Transaction tr, boolean accept, boolean remove) {
		super(ID);
		this.transaction = tr;
		this.accept = accept;
		this.remove = remove;
	}

	public TransactionMessage(JSONObject json) {
		super(ID);
		accept = json.getBoolean(ACCEPT);
		remove = json.getBoolean(REMOVE);
		transaction = new Transaction(json.getJSONObject(MIDs.TRANSACTION));
	}

	@Override
	JSONObject createJSON() {
		JSONObject transactionJSON = new JSONObject();
		transactionJSON.put(MIDs.TRANSACTION, transaction.toJSON());
		transactionJSON.put(ACCEPT, accept);
		transactionJSON.put(REMOVE, remove);

		return transactionJSON;
	}
	
	//GETTER AND SETTER
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}
}
