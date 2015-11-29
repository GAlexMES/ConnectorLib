package de.szut.dqi12.cheftrainer.connectorlib.logging;

/**
 * Messages, which should be used by the Logger, to print them in the log file.
 * @author Alexander Brennecke
 *
 */
public class LoggingMessages {
	public static String SERVER_STARTED = "The server started just now!";
	public static String SERVER_SHUTDOWN = "The server shutdown just now!";
	public static String CLIENT_CONNECTED = "A new client with the following IP connected to the server: ";
	public static String CLIENT_DISCONNECTED = "The client with the following IP disconnected from the server: ";
	public static String HANDSHAKE_COMPLETED = "The handshake was completed!";
	public static String NEW_MESSAGE = "A new message with the following ID arrived: ";
}
