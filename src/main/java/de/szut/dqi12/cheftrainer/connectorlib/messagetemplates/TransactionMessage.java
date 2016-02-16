package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Player;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Transaction;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;

/**
 * The TransactionMessage should be used by the client application.
 * It should be send, when the user wants to remove an offer or accept an offer to one of his {@link Player}s.
 * @author Alexander Brennecke
 *
 */
public class TransactionMessage extends TransfermarktUpdateMessageTemplate {

	private final static String ID = MIDs.TRANSACTION;
	public static final String ACCEPT = "accept";
	public static final String REMOVE = "remove";
	
	
	private Transaction transaction;
	private boolean accept;
	private boolean remove;

	/**
	 * Constructor
	 * @param tr a {@link Transaction} object, which is full initialized and represents the offer, that should be accepted/removed etc.
	 * @param accept true = the offer is accepted.
	 * @param remove true= the offer should be removed out of the database.
	 */
	public TransactionMessage(Transaction tr, boolean accept, boolean remove) {
		super(ID);
		this.transaction = tr;
		this.accept = accept;
		this.remove = remove;
		messageContent = createJSON().toString();
	}

	/**
	 * JSON Constructor
	 * @param json the {@link JSONObject}. Create it via "createMessageContent()".
	 */
	public TransactionMessage(JSONObject json) {
		super(ID);
		JSONObject information = json.getJSONObject(MIDs.INFORMATION);
		accept = information.getBoolean(ACCEPT);
		remove = information.getBoolean(REMOVE);
		transaction = new Transaction(information.getJSONObject(MIDs.TRANSACTION));
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
		messageContent = createJSON().toString();
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
		messageContent = createJSON().toString();
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
		messageContent = createJSON().toString();
	}
}
