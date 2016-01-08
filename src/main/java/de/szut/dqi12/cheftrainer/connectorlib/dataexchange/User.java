package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.AdditionalMessageIDs;

/**
 * The user class saves all information about the user, who has been logged in at the client.
 * @author Alexander Brennecke
 * @custom.position /D0010/
 */
public class User {
	
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME ="lastName";
	public static final String E_MAIL = "mail";
	
	String firstName = "";
	String lastName = "";
	String userName = "";
	String eMail = "";
	String password = "";
	
	int userID;
	
	public void setWithJSON(JSONObject json){
		firstName = json.getString(FIRST_NAME);
		lastName = json.getString(LAST_NAME);
		eMail = json.getString(E_MAIL);
		userName = json.getString(AdditionalMessageIDs.LOGIN);
		password = json.getString(AdditionalMessageIDs.PASSWORD);
	}
	public JSONObject toJSON(){
		JSONObject retval = new JSONObject();
		retval.put(FIRST_NAME, firstName);
		retval.put(LAST_NAME, lastName);
		retval.put(E_MAIL, eMail);
		retval.put(AdditionalMessageIDs.PASSWORD, password);
		retval.put(AdditionalMessageIDs.LOGIN, userName);
		return retval;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserID(){
		return userID;
	}
	public void setUserId(int ID){
		this.userID=ID;
	}
	public String getAllForSQL(){
		String retval = "'"+firstName +"', '" + lastName +"', '" + userName +"', '" + eMail +"', '" + password+"'";
		return retval;
	}
	

	
	

}
