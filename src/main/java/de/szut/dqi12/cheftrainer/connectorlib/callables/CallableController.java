package de.szut.dqi12.cheftrainer.connectorlib.callables;

import java.io.IOException;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.List;
import java.util.HashMap;
public class CallableController
{
	private static String FILE_PATH;
	private static String packagePath;
	private static HashMap<String, CallableAbstract> retval;
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

	private static CallableAbstract generateInstance(final String pathString, final String className) throws CallableMappingException {
		CallableAbstract classInstance = null;
		URLClassLoader cl = null;
		try {
			final URL path = CallableController.class.getClassLoader().getResource(pathString);
			final URL[] classLoaderUrls = { path };
			cl = new URLClassLoader(classLoaderUrls);
			final String fullQualifiedName = CallableController.packagePath + className;
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