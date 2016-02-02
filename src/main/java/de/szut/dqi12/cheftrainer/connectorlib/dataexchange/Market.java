package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MIDs;
import de.szut.dqi12.cheftrainer.connectorlib.utils.JSONUtils;

/**
 * This class is the model class for the transfer market.
 * @author Robin
 *
 */
public class Market extends Sendable{
	
	public static final String MARKET = "market";
	
	private List<Player> players;
	private Map<Integer,Player> playerMap;
	private List<Transaction> transactionList;

	/**
	 * default Constructor
	 */
	public Market() {
		players = new ArrayList<>();
		transactionList = new ArrayList<>();
		playerMap = new HashMap<>();
	}
	
	/**
	 * This function should be used to initialize the market with a {@link JSONObject}.
	 * @param json a {@link JSONObject}, which includes all necessary information to initialize a {@link Market}.
	 */
	public Market(JSONObject json) {
		this();
		JSONArray playerList = json.getJSONArray(Player.PLAYER);
		for (int i = 0; i < playerList.length(); i++) {
			Player p = new Player();
			p.getPlayerFromJSON(playerList.getJSONObject(i));
			addPlayer(p);
		}
		
		JSONArray transactions = json.getJSONArray(MIDs.TRANSACTIONS);
		for (int i = 0; i < transactions.length(); i++) {
			Transaction t = new Transaction(transactions.getJSONObject(i));
			transactionList.add(t);
		}
	}
	
	/**
	 * This function is used to remove one or more {@link Player}s from the {@link Market}.
	 * @param players a List of {@link Player} objects, which should be removed from the market.
	 */
	public void removePlayer(Player... players) {
		for (Player p : players) {
			playerMap.remove(p.getSportalID());
			
			for(int  i = 0; i<this.players.size();i++){
				Player player = this.players.get(i);
				if(player.getSportalID() == p.getSportalID()){
					this.players.remove(player);
				}
			}
			
			for(int  i = 0; i<transactionList.size();i++){
				Transaction t = transactionList.get(i);
				if(t.getPlayerSportalID() == p.getSportalID()){
					transactionList.remove(i);
				}
			}
		}
	}
	
	/***
	 * This function is used to remove a {@link Transaction} from the {@link Market}.
	 * @param transaction a {@link Transaction} object, which represents the {@link Transaction}, that should removed from the market.
	 */
	public void removeTransactions(Transaction transaction){
		Player p  = playerMap.get(transaction.getPlayerSportalID());
		players.remove(p);
		playerMap.remove(transaction.getPlayerSportalID());
		
		for(int i = 0; i<transactionList.size();i++){
			Transaction t = transactionList.get(i);
			if(t.getPlayerSportalID() == transaction.getPlayerSportalID()){
				transactionList.remove(i);
				break;
			}
		}
		
	}
	
	@Override
	public JSONObject toJSON(){
		JSONArray playerList = JSONUtils.listToJSON(players);
		JSONArray transactions = JSONUtils.listToJSON(transactionList);
		
		JSONObject retval = new JSONObject();
		retval.put(Player.PLAYER, playerList);
		retval.put(MIDs.TRANSACTIONS, transactions);
		
		return retval;
	}
	
	//GETTER AND SETTER
	public void addPlayer(Player... players) {
		for (Player p : players) {
			this.players.add(p);
			playerMap.put(p.getSportalID(),p);
		}
	}

	public List<Player> getPlayers() {
		return this.players;
	}
	
	public Map<Integer,Player> getPlayerMap(){
		return playerMap;
	}
	
	public void setTransactions(List<Transaction> transactions){
		this.transactionList=transactions;
	}
	
	
	public List<Transaction> getTransactions(){
		return this.transactionList;
	}
	
	public void addTransaction(Transaction tr){
		transactionList.add(tr);
	}
}