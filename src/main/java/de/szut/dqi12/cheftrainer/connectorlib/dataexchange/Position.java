package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class just stores the Position, which are possible.
 * @author Alexander Brennecke
 *
 */
public class Position {
	public static final String POSITION = "position";
	
	public static final String KEEPER = "Tor";
	public static final String DEFENCE = "Verteidigung";
	public static final String MIDDLE = "Mittelfeld";
	public static final String OFFENCE = "Angriff";
	
	/**
	 * This function creates a list of all possible positions.
	 * @return returns a {@link List} of possible Positions.
	 */
	public static List<String> getPositions(){
		String[] array = {KEEPER,DEFENCE,MIDDLE,OFFENCE};
		return new ArrayList<String>(Arrays.asList(array));
	}
}
