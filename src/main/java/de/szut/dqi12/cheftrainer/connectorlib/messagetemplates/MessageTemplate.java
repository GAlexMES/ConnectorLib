package de.szut.dqi12.cheftrainer.connectorlib.messagetemplates;

import de.szut.dqi12.cheftrainer.connectorlib.messages.Message;

abstract class MessageTemplate extends Message{

	public MessageTemplate(String messageID) {
		super(messageID);
	}

	@Override
	abstract public void createMessageContent();
	
}

