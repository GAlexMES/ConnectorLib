package de.szut.dqi12.cheftrainer.connectorlib.messages;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.Handshake_MessageIDs;

public class HandshakeMapperCreator {

	/**
	 * 
	 * @return a new IDClass_Path_Mapper for the Handshake Message IDs
	 */
	public static IDClass_Path_Mapper getIDClassPathMapperForHandshake() {

		Handshake_MessageIDs hm_messageIDs = new Handshake_MessageIDs();
		
		String pathToHandshakeDir = "/de/szut/dqi12/cheftrainer/connectorlib/callables/";
		
		String pathToHandshakePackage = "de.szut.dqi12.cheftrainer.connectorlib.callables.CallableController";

		return new IDClass_Path_Mapper(hm_messageIDs, pathToHandshakeDir, pathToHandshakePackage);
	}

}
