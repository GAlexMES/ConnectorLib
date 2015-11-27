package de.szut.dqi12.cheftrainer.connectorlib.callables;

import java.io.IOException;

import java.net.URLClassLoader;
import java.net.URL;
import java.util.List;
import java.util.HashMap;

/**
 * The CallableController class creates instances of classes, which extends a {@link CallableController}
 *  and are saved in the given director.
 * @author Alexander Brennecke
 *
 */
public class CallableController
{
	
	private static String FILE_PATH;
	private static String packagePath;
	private static HashMap<String, CallableAbstract> retval;
	
	/**
	 * This function creates a list of {@link CallableAbstract} objects. 
	 * Each String in the fieldList has to match to a class in the pathToCallableDir director.
	 * For example:
	 * When the String is "AESKey" and the pathToCallableDir is "de/szut/dqi12/cheftrainer/callables/", then there must
	 *  be a AESKey.java/.class file in the "de/szut/dqi12/cheftrainer/callables/" director.
	 *  
	 * @param fieldList a List of Strings, which can be mapped to a class in the pathToCallableDir
	 * @param pathToCallableDir a path to a director, in which the classes are saved, that can be mapped to the fieldList Strings.
	 * @param packagePathToCallableDir the package path of the mapped classes. For example: "de.szut.dqi12.cheftrainer.callables"
	 * @return a Map, where the key is a element of the fieldList Strings, and the value is a created matching instance of a class extending {@link CallableAbstract}
	 */
	public static HashMap<String, CallableAbstract> getInstancesForIDs(final List<String> fieldList, final String pathToCallableDir, final String packagePathToCallableDir) {
		CallableController.packagePath = "";
		CallableController.FILE_PATH = pathToCallableDir;
		final String[] packagePathSplitted = packagePathToCallableDir.split("\\.");
		for (int i = 0; i < packagePathSplitted.length - 1; ++i) {
			CallableController.packagePath = CallableController.packagePath + packagePathSplitted[i] + ".";
		}
		CallableController.retval = new HashMap<String, CallableAbstract>();
		fieldList.forEach(element -> mapClassToID(element));
		return CallableController.retval;
	}

	/**
	 * This method tries to call the generateInstance() method. When the generateInstance() method 
	 * returns a result, this result will put in the HashMap, which will be returned by the getInstanceForIDs() function.
	 * @param messageID The ID, which should match to a class int the FILE_PATH
	 */
	private static void mapClassToID(final String messageID) {
		try {
			final CallableAbstract tempCallable = generateInstance(CallableController.FILE_PATH, messageID);
			CallableController.retval.put(messageID, tempCallable);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (CallableMappingException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * This function creates a {@link ClassLoader} and tries to create a new instance of a {@link CallableAbstract} for the given parameters.
	 * @param pathString The absolute path to the director, in which the file is saved
	 * @param className The name of the class, that should be loaded.
	 * @return a new {@link CallableAbstract} instance for the given class.
	 * @throws CallableMappingException
	 */
	private static CallableAbstract generateInstance(final String pathString, final String className) throws CallableMappingException {
		CallableAbstract classInstance = null;
		URLClassLoader cl = null;
		try {
			final URL path = CallableController.class.getClassLoader().getResource(pathString);
			final URL[] classLoaderUrls = { path };
			cl = new URLClassLoader(classLoaderUrls);
			final String fullQualifiedName = CallableController.packagePath + className;
			@SuppressWarnings("unchecked")
			final Class<CallableAbstract> c = (Class<CallableAbstract>) cl.loadClass(fullQualifiedName);
			classInstance = c.newInstance();
			if (classInstance == null) {
				throw new CallableMappingException("The class: " + className + "\n " + "doesn't return a valid instance of itself. Pleas override the newInstance() function correct!");
			}
		}
		catch (ClassNotFoundException cne) {
			throw new CallableMappingException("The class: " + className + "  Was not found in: " + pathString);
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e2) {
			e2.printStackTrace();
		}
		finally {
			try {
				cl.close();
			}
			catch (IOException e3) {
				e3.printStackTrace();
			}
			catch (NullPointerException ex) {
			}
		}
		return classInstance;
	}
	
	static {
		CallableController.packagePath = "";
	}
}