package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Manager;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;

public class NewFormationMessage extends MessageTemplate{

	private static final String ID = ClientToServer_MessageIDs.NEW_FORMATION;
	private Manager manager;
	
	public NewFormationMessage(Manager manager) {
		super(ID);
		this.manager = manager;
	}
	
	public NewFormationMessage(JSONObject json){
		super(ID);
		manager = new Manager(json);
	}
	
	public NewFormationMessage create(){
		return new NewFormationMessage(new JSONObject(getMessageContent()));
	}
	
	@Override
	public void createMessageContent() {
		messageContent = manager.toJSON().toString();
	}
	
	//GETTER AND SETTER
	
	public Manager getManager(){
		return manager;
	}

}
