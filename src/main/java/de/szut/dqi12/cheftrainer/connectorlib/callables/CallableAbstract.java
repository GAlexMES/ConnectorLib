package de.szut.dqi12.cheftrainer.connectorlib.callables;

import de.szut.dqi12.cheftrainer.connectorlib.messages.Message;
import de.szut.dqi12.cheftrainer.connectorlib.messages.MessageController;

/**
 * Abstract Class, which should be extended from all Callables.
 * @author Alexander Brennecke
 *
 */
public abstract class CallableAbstract {

	protected MessageController mesController;

	/**
	 * Is called from the MessageController whenever a new Message with arrives.
	 * @param message
	 */
	public void messageArrived(Message message) {
	}

	/**
	 * Must be overridden by every class, extends from this class. Is called by the CallableController, which creates a new instance with this function.
	 * @return a CallableAbstract instance
	 */
	public static CallableAbstract newInstance() {
		return null;
	}

	/**
	 * Is called from the MessageController to set itself to the class.
	 * @param mesController
	 */
	public void setMessageController(MessageController mesController) {
		this.mesController = mesController;
	}
}
