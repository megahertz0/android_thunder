package com.xunlei.downloadprovider.dlnaplugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import dalvik.system.DexClassLoader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class PluginStatic {
    public static final String PARAM_LAUNCH_ACTIVITY = "pluginsdk_launchActivity";
    public static final String PARAM_LAUNCH_SERVICE = "pluginsdk_launchService";
    public static final String PARAM_PATH = "pluginsdk_pluginpath";
    public static final String PARAM_PLUGIN_LOCATION = "pluginsdk_pluginLocation";
    public static final String PARAM_PLUGIN_RECEIVER_CLASS_NAME = "pluginsdk_launchReceiver";
    public static final HashMap<String, DexClassLoader> sClassLoaderMap;
    private static ArrayList<WeakReference<IPluginActivity>> sInstances;
    public static final ConcurrentHashMap<String, PackageInfo> sPackageInfoMap;

    static {
        sClassLoaderMap = new HashMap();
        sPackageInfoMap = new ConcurrentHashMap();
        sInstances = new ArrayList();
    }

    static synchronized DexClassLoader getOrCreateClassLoaderByPath(Context context, String str, String str2) throws Exception {
        DexClassLoader dexClassLoader;
        synchronized (PluginStatic.class) {
            dexClassLoader = (DexClassLoader) sClassLoaderMap.get(str);
            if (dexClassLoader == null) {
                dexClassLoader = new DexClassLoader(str2, context.getDir("pluginCdnodex", 0).getCanonicalPath(), null, context.getClassLoader());
                sClassLoaderMap.put(str, dexClassLoader);
            }
        }
        return dexClassLoader;
    }

    public static synchronized DexClassLoader getClassLoader(String str) {
        DexClassLoader dexClassLoader;
        synchronized (PluginStatic.class) {
            dexClassLoader = (DexClassLoader) sClassLoaderMap.get(str);
        }
        return dexClassLoader;
    }

    static List<WeakReference<IPluginActivity>> getActivitys() {
        return sInstances;
    }

    static void add(IPluginActivity iPluginActivity) {
        updateReference();
        synchronized (sInstances) {
            sInstances.add(new WeakReference(iPluginActivity));
        }
    }

    static void remove(IPluginActivity iPluginActivity) {
        updateReference();
        removeActivity(iPluginActivity);
    }

    static void updateReference() {
        synchronized (sInstances) {
            int i = 0;
            while (i < sInstances.size()) {
                int i2;
                if (((WeakReference) sInstances.get(i)).get() == null) {
                    sInstances.remove(i);
                    i2 = i - 1;
                } else {
                    i2 = i;
                }
                i = i2 + 1;
            }
        }
    }

    private static boolean removeActivity(IPluginActivity iPluginActivity) {
        synchronized (sInstances) {
            for (int i = 0; i < sInstances.size(); i++) {
                if (((WeakReference) sInstances.get(i)).get() == iPluginActivity) {
                    sInstances.remove(i);
                    return true;
                }
            }
            return false;
        }
    }
}
