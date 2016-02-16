package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.ClientToServer_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messageids.ServerToClient_MessageIDs;
import de.szut.dqi12.cheftrainer.connectorlib.messages.Message;

/**
 * The Message Template Interface should be used by Message Templates.
 * @author Alexander Brennecke
 *
 */
abstract class MessageTemplate extends Message{

	/**
	 * Constructor
	 * @param messageID the message ID of the {@link Message}. Should come either from the {@link ServerToClient_MessageIDs} or {@link ClientToServer_MessageIDs}.
	 */
	public MessageTemplate(String messageID) {
		super(messageID);
	}
	
	@Override
	abstract public void createMessageContent();
	
}

