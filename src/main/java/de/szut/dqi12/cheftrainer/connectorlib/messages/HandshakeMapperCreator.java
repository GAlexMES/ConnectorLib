package de.szut.dqi12.cheftrainer.connectorlib.messages;

import java.net.URL;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.Handshake_MessageIDs;

public class HandshakeMapperCreator {

	/**
	 * 
	 * @return a new IDClass_Path_Mapper for the Handshake Message IDs
	 */
	public static IDClass_Path_Mapper getIDClassPathMapperForHandshake() {

		Handshake_MessageIDs hm_messageIDs = new Handshake_MessageIDs();
		
		URL pathToHandshakeDir = HandshakeMapperCreator.class.getResource("/de/szut/dqi12/cheftrainer/connectorlib/callables");
		System.out.println(pathToHandshakeDir.toString());
		
		String pathToHandshakePackage = "de.szut.dqi12.cheftrainer.connectorlib.callables.CallableController";
		System.out.println(pathToHandshakePackage.toString());

		return new IDClass_Path_Mapper(hm_messageIDs, pathToHandshakeDir, pathToHandshakePackage);
	}

}
