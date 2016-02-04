package de.szut.dqi12.cheftrainer.connectorlib.messageids;

/**
 * IDs from Messages, which are sent by the client and received by the server.
 * @author Alexander Brennecke
 *
 */
public class ClientToServer_MessageIDs extends MessageIDAbstract{
	
	public static String USER_AUTHENTICATION ="UserAuthentication";
	public static String COMMUNITY_AUTHENTICATION = "CommunityAuthentication";
	public static String REQUEST_UPDATE = "UpdateRequest";
	public static String NEW_FORMATION = "NewFormationUpdate";
	public static String TRANSFER_MARKET = "TransferMarketUpdate";
}
