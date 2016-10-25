package com.inmobi.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import com.inmobi.commons.core.configs.c;
import com.inmobi.commons.core.utilities.info.e;
import com.inmobi.rendering.mraid.i;
import com.umeng.socialize.common.SocializeConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// compiled from: FileManager.java
final class a {
    @SuppressLint({"SdCardPath"})
    public static boolean a(Context context) {
        int i;
        List asList = Arrays.asList(new String[]{"carbpreference", "IMAdMLtvpRuleCache", "inmobiAppAnalyticsSession", "aeskeygenerate", "impref", "IMAdTrackerStatusUpload", "IMAdMMediationCache", "inmobiAppAnalyticsAppId", "inmobiAppAnalyticsSession", "inmobisdkaid", "IMAdTrackerStatusUpload", "testAppPref"});
        for (i = 0; i < asList.size(); i++) {
            File file = new File(new StringBuilder("/data/data/").append(context.getPackageName()).append("/shared_prefs/").append((String) asList.get(i)).append(".xml").toString());
            if (file.exists()) {
                file.delete();
            }
        }
        asList = Arrays.asList(new String[]{com.inmobi.signals.a.a(), c.a(), com.inmobi.commons.core.utilities.a.a.a(), i.a(), e.a()});
        for (i = 0; i < asList.size(); i++) {
            file = new File(new StringBuilder("/data/data/").append(context.getPackageName()).append("/shared_prefs/").append((String) asList.get(i)).append(".xml").toString());
            if (file.exists()) {
                file.delete();
            }
        }
        asList = Arrays.asList(new String[]{"inmobi.cache", "inmobi.cache.data", "inmobi.cache.data.events.number", "inmobi.cache.data.events.timestamp"});
        for (i = 0; i < asList.size(); i++) {
            if (context.getCacheDir() != null) {
                file = new File(context.getCacheDir(), (String) asList.get(i));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        asList = Arrays.asList(new String[]{"eventlog", "imai_click_events"});
        for (i = 0; i < asList.size(); i++) {
            if (context.getDir(SocializeConstants.JSON_DATA, 0) != null) {
                file = new File(context.getDir(SocializeConstants.JSON_DATA, 0), (String) asList.get(i));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        return b(context).size() != 0;
    }

    private static boolean a(Context context, String str) {
        File databasePath = context.getDatabasePath(str);
        return databasePath == null || !databasePath.exists() || context.deleteDatabase(str);
    }

    private static Set<String> a() {
        Set<String> hashSet = new HashSet();
        hashSet.add("adcache.db");
        hashSet.add("appengage.db");
        hashSet.add("im.db");
        hashSet.add("ltvp.db");
        hashSet.add("analytics.db");
        hashSet.add("com.im.db");
        return hashSet;
    }

    public static List<String> b(Context context) {
        List<String> arrayList = new ArrayList();
        Set a = a();
        String[] databaseList = context.databaseList();
        if (databaseList != null && databaseList.length > 0) {
            for (String str : databaseList) {
                if (a.contains(str) && !a(context, str)) {
                    arrayList.add(str);
                } else if (str.matches("com\\.im_([0-9]+\\.){3}db") && !str.equals(com.inmobi.commons.core.b.a.a) && !a(context, str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }
}
