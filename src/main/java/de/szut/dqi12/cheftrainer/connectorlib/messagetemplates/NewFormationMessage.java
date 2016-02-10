package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Formation;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Manager;
import de.szut.dqi12.cheftrainer.connectorlib.dataexchange.Player;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;

/**
 * The NewFormationMessage should be used by the client application, when the user changes the {@link Formation} of his team.
 * @author Alexander Brennecke
 *
 */
public class NewFormationMessage extends MessageTemplate{

	private static final String ID = ClientToServer_MessageIDs.NEW_FORMATION;
	private Manager manager;
	
	/**
	 * Constructor
	 * @param manager a {@link Manager} object, that contains a list of {@link Player}, the {@link Formation} and the general {@link Manager} information.
	 */
	public NewFormationMessage(Manager manager) {
		super(ID);
		this.manager = manager;
	}
	
	/**
	 * JSON Constructor
	 * @param json the {@link JSONObject}. Create it via "createMessageContent()".
	 */
	public NewFormationMessage(JSONObject json){
		super(ID);
		manager = new Manager(json);
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
