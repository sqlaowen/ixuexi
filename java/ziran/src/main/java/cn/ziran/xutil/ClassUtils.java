package cn.ziran.xutil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.ziran.xtest.ICat;

public class ClassUtils {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		List<Class<?>> listClass = getClassFromPackage("com.ziran.xtest");
		listClass = getClassFromPackage("src/xtest-0.0.1-SNAPSHOT.jar");
		System.out.println(listClass);

		List<Class<?>> listImp = getAllClassByInterface(ICat.class);
		System.out.println(listImp);
	}

	public static List<Class<?>> getAllClassByInterface(Class<?> c) {
		List<Class<?>> returnClassList = new ArrayList<Class<?>>();

		if (c.isInterface()) {
			String packageName = c.getPackage().getName();
			try {
				List<Class<?>> allClass = getClassFromPackage(packageName);
				for (int i = 0; i < allClass.size(); i++) {
					if (c.isAssignableFrom(allClass.get(i))) {
						if (!c.equals(allClass.get(i))) {
							returnClassList.add(allClass.get(i));
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnClassList;
	}

	public static List<Class<?>> getClassFromPackage(String packageName) throws ClassNotFoundException, IOException {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace(".", "/");

		Enumeration<URL> resources = classLoader.getResources(path);
//		if (resources.hasMoreElements()) {
//			URL url=resources.nextElement();
//			System.out.println(url);
//		}
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}

	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {

		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!directory.exists()) {
			return classes;
		}

		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

}