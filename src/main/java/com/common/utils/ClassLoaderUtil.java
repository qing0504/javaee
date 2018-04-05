package com.common.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;

/**
 * 这个类主要用户将资源动态加入到classpath之中
 * @author wanchongyang
 * @date 2017/10/24
 */
public final class ClassLoaderUtil {
    /**
     * class path
     */
    private static String classPath = "";

    /**
     * loader
     */
    private static ClassLoader loader = Thread.currentThread().getContextClassLoader();

    static {

        if (loader == null) {
            System.out.println("using system class loader!");
            loader = ClassLoader.getSystemClassLoader();
        }

        try {

            URL url = loader.getResource("");
            // get class path
            File f = new File(url.toURI());
            classPath = f.getAbsolutePath();
            classPath = URLDecoder.decode(classPath, "utf-8");

            // 如果是jar包内的，则返回当前路径
            if (classPath.contains(".jar!")) {
                System.out.println("using config file inline jar!" + classPath);
                classPath = System.getProperty("user.dir");

                //
                addCurrentWorkingDir2Classpath(classPath);
            }

        } catch (Throwable e) {
            classPath = System.getProperty("user.dir");
            addCurrentWorkingDir2Classpath(classPath);
        }

        System.out.println("classpath: " + classPath);
    }

    private ClassLoaderUtil() {
    }

    /**
     * only support 1.7 or higher
     * http://stackoverflow.com/questions/252893/how-do-you-change-the-classpath
     * -within-java
     */
    private static void addCurrentWorkingDir2Classpath(String path2Added) {
        if (loader instanceof URLClassLoader) {
            System.out.println("use URLClassLoader");
            try {
                Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
                add.setAccessible(true);
                add.invoke(loader, new File(path2Added).toURI().toURL());
                overrideThreadContextClassLoader(loader);
                return;
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }

        // Add the conf dir to the classpath
        // Chain the current thread classloader
        URLClassLoader urlClassLoader;
        try {
            urlClassLoader = new URLClassLoader(new URL[]{new File(path2Added).toURI().toURL()}, loader);
            // Replace the thread classloader - assumes
            // you have permissions to do so
            Thread.currentThread().setContextClassLoader(urlClassLoader);

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private static ClassLoader overrideThreadContextClassLoader(ClassLoader classLoaderToUse) {
        Thread currentThread = Thread.currentThread();
        ClassLoader threadContextClassLoader = currentThread.getContextClassLoader();
        if (classLoaderToUse != null && !classLoaderToUse.equals(threadContextClassLoader)) {
            currentThread.setContextClassLoader(classLoaderToUse);
            return threadContextClassLoader;
        } else {
            return null;
        }
    }

    public static String getClassPath() {
        return classPath;
    }

    public static ClassLoader getLoader() {
        return loader;
    }
}
