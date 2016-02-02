package de.szut.dqi12.cheftrainer.connectorlib.clientside;

import de.szut.dqi12.cheftrainer.connectorlib.serverside.Server;

/**
 * This listener should be used, when the connection to the {@link Server} or {@link Client} died.
 * @author Alexander Brennecke
 *
 */
public interface ConnectionDiedListener {
	void connectionDied();  
}
