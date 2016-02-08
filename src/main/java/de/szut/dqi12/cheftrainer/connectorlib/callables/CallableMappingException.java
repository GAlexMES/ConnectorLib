package de.szut.dqi12.cheftrainer.connectorlib.callables;

/**
 * Exception, which should be used, when something with the ID <-> Class mapping fails.
 * @author Alexander Brennecke
 *
 */
public class CallableMappingException extends Exception {

	private static final long serialVersionUID = -8528870042800486787L;

	/**
	 * Constructor
	 */
	public CallableMappingException() {
		super();
	}

	/**
	 * Constructor
	 * @param message the message of the {@link CallableMappingException}
	 */
	public CallableMappingException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param message the message of the {@link CallableMappingException}
	 * @param cause the cause as a Throwable
	 */
	public CallableMappingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param cause the cause as a throwable
	 */
	public CallableMappingException(Throwable cause) {
		super(cause);
	}
}
