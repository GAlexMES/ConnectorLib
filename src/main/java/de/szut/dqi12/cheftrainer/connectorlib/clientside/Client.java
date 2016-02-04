package de.szut.dqi12.cheftrainer.connectorlib.clientside;

import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import de.szut.dqi12.cheftrainer.connectorlib.messages.Message;

/**
 * Handles the Server Connection
 * @author Alexander Brennecke
 *
 */
public class Client {

	private Socket socket;
	private ClientProperties clientProps;
	private ServerHandler servHandler;
	
	private final static Logger LOGGER = Logger.getLogger(Client.class);
	/**
	 * Constructor
	 * @param clientProps 
	 * @param conInterface
	 * @throws IOException 
	 */
	public Client(ClientProperties clientProps) throws IOException{
		this.clientProps = clientProps;
		startConnection(clientProps.getServerIP(),clientProps.getPort(),clientProps.getConnectionDiedListener());
	}
	
	

	/**
	 * Builds a new Connection to a java server socket
	 * @param serverIP the IP of the server
	 * @param serverPort the port of the server
	 * @param cdl a {@link ConnectionDiedListener}
	 * @throws IOException 
	 */
	private void startConnection(String serverIP, int serverPort, ConnectionDiedListener cdl) throws IOException {
		try {
			socket = new Socket(serverIP,serverPort);
			servHandler = new ServerHandler(socket,clientProps,cdl);
			Thread readerThread = new Thread(servHandler);
			readerThread.start();
		} catch (IOException ex) {
			throw ex;
		} catch (Exception e) {
		}
	}
	
	
	/**
	 * Is used to forward a message to the serverHandler
	 * @param message the decrypted message that should be send.
	 */
	public void sendMessage(Message message){
		message.createMessageContent();
		if(servHandler!=null){
			servHandler.sendMessage(message);
		}
		else{
			LOGGER.error("No ServerHandler defined. Message could not be sent.");
		}
	}
	
	public String getServerIP(){
		return socket.getInetAddress().toString();
	}
	
	public int getServerPort(){
		return socket.getPort();
	}
	
	public ServerHandler getServerHandler(){
		return servHandler;
	}
	
	/**
	 * This function waits for the completion of the handshake.
	 * @param timeOut the time out in seconds
	 * @return true ,when the handshake was completed
	 * @throws TimeoutException
	 */
	public boolean waitForConnect (int timeOut) throws TimeoutException{
		Date current = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		cal.add(Calendar.SECOND, timeOut);
		while(cal.getTime().after(new Date())){
			boolean handshakeComplete = servHandler.getMessageController().isHandshakeComplete();
			if(handshakeComplete){
				return true;
			}
			// wait to pretend thread asynchrony
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		throw new TimeoutException("No connection after "+timeOut+" seconds.");
	}
	
	
}
