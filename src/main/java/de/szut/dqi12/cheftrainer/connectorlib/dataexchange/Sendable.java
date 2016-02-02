package de.szut.dqi12.cheftrainer.connectorlib.dataexchange;

import org.json.JSONObject;

import de.szut.dqi12.cheftrainer.connectorlib.clientside.Client;
import de.szut.dqi12.cheftrainer.connectorlib.serverside.Server;

/**
 * 
 * This class should be used for dataexchange objects, which should be send to the {@link Server} or {@link Client}
 * @author Alexander Brennecke
 *
 */
public abstract class Sendable {
	public JSONObject toJSON() {
		return null;
	}
}
