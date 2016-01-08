package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.AdditionalMessageIDs;

/**
 * 
 * @author Robin
 *
 */
public class Market extends Sendable{
	private List<Player> players;
	private Map<Integer,Player> playerMap;
	private List<Transaction> transactionList;

	public Market() {
		players = new ArrayList<>();
		transactionList = new ArrayList<>();
		playerMap = new HashMap<>();
	}
	
	public Market(JSONObject json) {
		this();
		JSONArray playerList = json.getJSONArray(AdditionalMessageIDs.PLAYER);
		for (int i = 0; i < playerList.length(); i++) {
			Player p = new Player();
			p.getPlayerFromJSON(playerList.getJSONObject(i));
			addPlayer(p);
		}
		
		JSONArray transactions = json.getJSONArray(AdditionalMessageIDs.TRANSACTIONS);
		for (int i = 0; i < transactions.length(); i++) {
			Transaction t = new Transaction(transactions.getJSONObject(i));
			transactionList.add(t);
		}
	}
	
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
	
	public JSONObject toJSON(){
		JSONArray playerList = listToJSON(players);
		JSONArray transactions = listToJSON(transactionList);
		
		JSONObject retval = new JSONObject();
		retval.put(AdditionalMessageIDs.PLAYER, playerList);
		retval.put(AdditionalMessageIDs.TRANSACTIONS, transactions);
		
		return retval;
	}
	
	private JSONArray listToJSON(List<? extends Sendable> valueList){
		JSONArray retval = new JSONArray();
		for(Sendable s: valueList){
			retval.put(s.toJSON());
		}
		return retval;
	}

	public void addTransaction(Transaction tr){
		transactionList.add(tr);
	}
}