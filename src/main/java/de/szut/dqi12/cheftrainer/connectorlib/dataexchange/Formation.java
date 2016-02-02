package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import org.json.JSONObject;

/**
 * The Formation class is used to represent the Formation of a {@link Manager}. 
 * @author Alexander Brennecke
 *
 */
public class Formation extends Sendable{
	
	private int defenders;
	private int middfielders;
	private int offensives;
	private String name;
	
/**
 * 
 * @param name
 * @param defenders
 * @param middfielders
 * @param offensives
 */
	public Formation(String name, int defenders, int middfielders, int offensives){
		this.name = name;
		this.defenders = defenders;
		this.middfielders = middfielders;
		this.offensives = offensives;
	}
	
	/**
	 * This function looks for the number of playing players on the given {@link Position}
	 * @param position use {@link Position} for this.
	 * @return a int, which represents the number of {@link Player}s, which play on this {@link Position}.
	 */
	public int getPlayersForPosition(String position){
		switch(position){
		case Position.DEFENCE:
			return defenders;
		case Position.MIDDLE:
			return middfielders;
		case Position.OFFENCE:
			return offensives;
		case Position.KEEPER:
			return 1;
		}
		return 0;
	}
	
	/**
	 * Constructor, when the {@link Formation} should be initialized via {@link JSONObject}.
	 * @param json a {@link JSONObject}, which includes all information, which are required to initialized a {@link JSONObject}
	 */
	public Formation(JSONObject json){
		this.defenders = json.getInt(Position.DEFENCE);
		this.middfielders = json.getInt(Position.MIDDLE);
		this.offensives= json.getInt(Position.OFFENCE);
		this.name=defenders+"-"+middfielders+"-"+offensives;
	}
	
	@Override
	public JSONObject toJSON(){
		JSONObject retval = new JSONObject();
		retval.put(Position.DEFENCE, defenders);
		retval.put(Position.MIDDLE, middfielders);
		retval.put(Position.OFFENCE, offensives);
		return retval;
	}
	
	// GETTER AND SETTER
	public int getDefenders() {
		return defenders;
	}
	public void setDefenders(int defenders) {
		this.defenders = defenders;
	}
	public int getMiddfielders() {
		return middfielders;
	}
	public void setMiddfielders(int middfielders) {
		this.middfielders = middfielders;
	}
	public int getOffensives() {
		return offensives;
	}
	public void setOffensives(int offensives) {
		this.offensives = offensives;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
