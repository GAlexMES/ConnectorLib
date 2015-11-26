package de.szut.dqi12.cheftrainer.connectorlib.messages;

import de.szut.dqi12.cheftrainer.connectorlib.messageids.MessageIDAbstract;


/**
 * A Object of this class displays the connection between an MessageIDAbstract and an the director/package, which contains the Callables, that should be mapped to the MessageIDAbstract.
 * @author Alexander Brennecke
 *
 */
public class IDClass_Path_Mapper {
	
	private MessageIDAbstract mesIDabs;
	private String pathToDir;
	private String packagePathToDir;
	
	public IDClass_Path_Mapper(MessageIDAbstract mesIDAbs, String pathToDir, String packagePathToDir){
		this.mesIDabs = mesIDAbs;
		this.pathToDir = pathToDir;
		this.packagePathToDir = packagePathToDir;
	}
	
	public MessageIDAbstract getMesIDabs() {
		return mesIDabs;
	}
	public void setMesIDabs(MessageIDAbstract mesIDabs) {
		this.mesIDabs = mesIDabs;
	}
	public String getPathToDir() {
		return pathToDir;
	}
	public void setPathToDir(String pathToDir) {
		this.pathToDir = pathToDir;
	}
	public String getPackagePathToDir() {
		return packagePathToDir;
	}
	public void setPackagePathToDir(String packagePathToDir) {
		this.packagePathToDir = packagePathToDir;
	}
	
	

}
