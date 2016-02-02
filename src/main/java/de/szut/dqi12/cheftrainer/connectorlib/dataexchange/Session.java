package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import de.szut.dqi12.cheftrainer.connectorlib.clientside.Client;
import de.szut.dqi12.cheftrainer.connectorlib.serverside.ClientHandler;
import de.szut.dqi12.cheftrainer.connectorlib.serverside.Server;

/**
 * This class is used to save session specific attributes. It can be used on
 * server and client side.
 * 
 * @author Alexander Brennecke
 *
 */
public class Session {

	private int userID;
	private User user;
	private Client clientSocket;
	private int currentManagerID;
	private int currentCommunityID;

	private ClientHandler clientHandler;

	private ObservableList<Manager> managerTableData = FXCollections
			.observableArrayList();

	private HashMap<Integer, Community> communityIDMap;
	private HashMap<String, Community> communityNameMap;
	

	/**
	 * Default constructor
	 */
	public Session() {
		communityIDMap = new HashMap<>();
		communityNameMap = new HashMap<>();
	}

	
	/**
	 * Should only be used on the server side.
	 * 
	 * @return
	 */
	public ClientHandler getClientHandler() {
		return clientHandler;
	}

	/**
	 * Should only be used on the server side.
	 * @param clientHandler  a {@link ClientHandler} object, which is used to send messages to the {@link Server}
	 */
	public void setClientHandler(ClientHandler clientHandler) {
		this.clientHandler = clientHandler;
	}


	/**
	 * This function sets the currentManager variable to the given parameter
	 * @param currentManager a {@link Manager} object, which should be set as currentManager. This {@link Manager} is used for every operation in the ClientApplication.
	 */
	public void setCurrentManager(Manager currentManager) {
		setCurrentManager(currentManager.getID());
		String communityName = currentManager.getCommunityNameProperty()
				.getValue();
		Community currentCommunity = communityNameMap.get(communityName);
		currentCommunityID = currentCommunity.getCommunityID();
	}

	
	/**
	 * This function is used to add a {@link Community} to the session. It will be displayed in the communiies table in the client.
	 * @param community a {@link Community} object.
	 */
	public void addCommunity(Community community) {
		communityIDMap.put(community.getCommunityID(), community);
		communityNameMap.put(community.getName(), community);
		community.findeUsersManager(user.getUserName());
		addManagerToTable(community.getUsersManager());
	}

	/**
	 * This function is used to add multiple {@link Community} objects.
	 * @param communities
	 */
	public void addCommunities(List<Community> communities) {
		for (Community c : communities) {
			addCommunity(c);
		}
	}

	/**
	 * This function is used to add an {@link Manager} object to the {@link Manager} observable list, which will be used by the community controler, to display the manager in the table.
	 * @param managers
	 */
	public void addManagerToTable(Manager... managers) {
		for (Manager m : managers) {
			if (m != null) {
				managerTableData.add(m);
			}
		}
	}

	/**
	 * Should only be used on the client side.
	 * 
	 * @return
	 */
	public Client getClientSocket() {
		return clientSocket;
	}

	/**
	 * Should only be used on the client side.
	 * @param clientSocket a {@link Client} object, which is used to send a message to the {@link Server}.
	 */
	public void setClientSocket(Client clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	//GETTER AND SETTER

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public HashMap<Integer, Community> getCommunityIDMap() {
		return communityIDMap;
	}

	public HashMap<String, Community> getCommunityNameMap() {
		return communityNameMap;
	}

	public List<Community> getCommunities() {
		List<Community> retval = new ArrayList<Community>();
		for (Integer s : communityIDMap.keySet()) {
			retval.add(communityIDMap.get(s));
		}
		return retval;
	}

	public ObservableList<Manager> getManagerObservable() {
		return managerTableData;
	}
	
	public int getCurrentManagerID() {
		return currentManagerID;
	}
	
	public Manager getCurrentManager(){
		Community com = communityIDMap.get(currentCommunityID);
		return com.getManager(currentManagerID);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		userID = user.getUserID();
		this.user = user;
	}
	
	public Community getCurrentCommunity() {
		return communityIDMap.get(currentCommunityID);
	}

	public Community getCommunity(Integer id) {
		return communityIDMap.get(id);
	}

	public Community getCommunity(String name) {
		return communityNameMap.get(name);
	}

	public void updateCommunities(List<Community> communities) {
		communityIDMap = new HashMap<>();
		addCommunities(communities);
	}
	
	public void setCurrentManager(int currentManager) {
		this.currentManagerID = currentManager;
	}

	public int getCurrentCommunityID() {
		return currentCommunityID;
	}

	public void setCurrentCommunityID(int currentCommunity) {
		this.currentCommunityID = currentCommunity;
	}

}
