package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * This class transforms a Position String to a {@link Formation} object.
 * @author Alexander Brennecke
 *
 */
public class FormationFactory {

	public static final String FOUR_FOUR_TWO = "4-4-2";
	public static final String FOUR_FIVE_ONE = "4-5-1";
	public static final String THREE_FOUR_THREE = "3-4-3";
	public static final String FOUR_THRE_THRE = "4-3-3";
	public static final String THRE_FIVE_TWO = "3-5-2";
	
	private List<String> formationsList;
	
	/**
	 * Constructor
	 */
	public FormationFactory(){
		formationsList = new ArrayList<>();
		createFormationList();
	}

	/**
	 * This function creates a Formation object, based n the given String.
	 * @param formation should be in the format "4-4-2"
	 * @return a {@link Formation} object.
	 */
	public Formation getFormation(String formation) {
		
		String[] sF = formation.split("-");
		return new Formation(formation, Integer.valueOf(sF[0]),
				Integer.valueOf(sF[1]), Integer.valueOf(sF[2]));
	}
	
	/**
	 * This function creates a {@link Formation} object, based on the given ints.
	 * @param defendersn number of defenders
	 * @param middfielders number of middfielders
	 * @param offensives number of offensives
	 * @return the generated {@link Formation} object.
	 * @throws IOException
	 */
	public Formation getFormation(int defenders, int middfielders,
			int offensives) throws IOException{
		String formationName = defenders+"-"+middfielders+"-"+offensives;
		if(formationsList.contains(formationName)){
			return getFormation(formationName);
		}
		else{
			throw new IOException("Invalid formation '"+formationName+"'!");
		}
	}
	
	/**
	 * This function creates a {@link List} of {@link Formation} objects, based on the static final Strings, which are defined in this class.
	 */
	public void createFormationList(){
		Field[] formations = FormationFactory.class.getFields();
		for (Field f : formations) {
			String formationName;
			try {
				formationName = (String) f.get(this);
				formationsList.add(formationName);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	// GETTER AND SETTER
	public List<String> getFormationsList() {
		return formationsList;
	}
	
	public List<Formation> getFormations() {
		List<Formation> retval = new ArrayList<Formation>();
		for (String s : formationsList) {
			retval.add(getFormation(s));
		}
		return retval;
	}
}
