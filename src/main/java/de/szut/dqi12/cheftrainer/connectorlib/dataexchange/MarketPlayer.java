package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class is used, to present a {@link Player} in a JavaFX Table
 * @author Alexander Brennecke
 *
 */
public class MarketPlayer {
	private final StringProperty playerName;
	private final StringProperty points;
	private final StringProperty worth;
	private final Player player;

	/**
	 * Constructor
	 * @param playerName the name of the player
	 * @param points the points of the player
	 * @param price the price/worth of the player
	 * @param p the {@link Player} object itselfs
	 */
	public MarketPlayer(String playerName, String points,
			String price, Player p) {
		super();
		this.playerName =  new SimpleStringProperty(playerName);
		this.points =  new SimpleStringProperty(points);
		this.worth =  new SimpleStringProperty(price);
		this.player=p;
	}
	

	//GETTER AND SETTER
	public StringProperty getPlayerName() {
		return playerName;
	}

	public StringProperty getPoints() {
		return points;
	}

	public StringProperty getWorth() {
		return worth;
	}
	
	public Player getPlayer(){
		return player;
	}


}