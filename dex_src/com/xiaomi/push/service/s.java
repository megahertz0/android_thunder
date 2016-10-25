package com.xiaomi.push.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.xiaomi.channel.commonutils.string.d;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class s {
    private static Object a;
    private static Map<String, Queue<String>> b;

    static {
        a = new Object();
        b = new HashMap();
    }

    public static boolean a(XMPushService xMPushService, String str, String str2) {
        synchronized (a) {
            Collection linkedList;
            SharedPreferences sharedPreferences = xMPushService.getSharedPreferences("push_message_ids", 0);
            Queue queue = (Queue) b.get(str);
            if (queue == null) {
                String[] split = sharedPreferences.getString(str, BuildConfig.VERSION_NAME).split(",");
                linkedList = new LinkedList();
                int length = split.length;
                for (int i = 0; i < length; i++) {
                    linkedList.add(split[i]);
                }
                b.put(str, linkedList);
            }
            if (linkedList.contains(str2)) {
                return true;
            }
            linkedList.add(str2);
            if (linkedList.size() > 25) {
                linkedList.poll();
            }
            String a = d.a(linkedList, ",");
            Editor edit = sharedPreferences.edit();
            edit.putString(str, a);
            edit.commit();
            return false;
        }
    }
}
