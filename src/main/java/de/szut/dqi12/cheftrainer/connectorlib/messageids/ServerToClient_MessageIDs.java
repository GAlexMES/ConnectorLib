package de.szut.dqi12.cheftrainer.connectorlib.messageids;

/**
 * IDs from Messages, which are sent by the server and received by the client.
 * @author Alexander Brennecke
 *
 */
public class ServerToClient_MessageIDs extends MessageIDAbstract {
	
	public static String USER_AUTHENTICATION_ACK ="UserAuthenticationACK";
	public static String USER_COMMUNITY_LIST = "UserCommunityList";
	public static String COMMUNITY_AUTHENTICATION_ACK = "CommunityAuthenticationAck";
	public static String SAVE_FORMATION_ACK = "SaveFormationAck";
	public static String SAVE_OFFER_ACK = "SaveOfferAck";
}
