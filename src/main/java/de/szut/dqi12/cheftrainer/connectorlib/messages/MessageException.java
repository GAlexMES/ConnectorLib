package de.szut.dqi12.cheftrainer.connectorlib.messages;

/**
 * The MessageException should be used, when something failed during the parsing process of an incoming message.
 * @author Alexander Brennecke
 *
 */
public class MessageException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default empty constructor
	 */
	public MessageException(){
		super();
	}
	
	/**
	 * Constructor
	 * @param message the message of the Exception
	 */
	public MessageException(String message){
		super(message);
	}

	/**
	 * Constructor
	 * @param message the message of the Exception
	 * @param cause the cause of the message as {@link Throwable}
	 */
	public MessageException(String message, Throwable cause){
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param cause the cause of the message as {@link Throwable}
	 */
	public MessageException(Throwable cause){
		super(cause);
	}
}
