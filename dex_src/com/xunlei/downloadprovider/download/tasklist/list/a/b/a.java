package com.xunlei.downloadprovider.download.tasklist.list.a.b;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ListADDataHolder.java
public final class a {
    public static a j;
    public Set<String> a;
    public Set<String> b;
    public Set<String> c;
    public b d;
    public b e;
    public b f;
    public b g;
    public b h;
    public b i;
    public boolean[] k;
    public int[] l;
    private Map<String, b> m;
    private Map<String, b> n;
    private Map<String, b> o;

    private a() {
        this.m = new HashMap();
        this.n = new HashMap();
        this.o = new HashMap();
        this.a = new HashSet();
        this.b = new HashSet();
        this.c = new HashSet();
        this.k = new boolean[3];
        this.l = new int[3];
    }

    public static a a() {
        if (j == null) {
            j = new a();
        }
        return j;
    }

    public final b a(int i) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return this.d;
            case SimpleLog.LOG_LEVEL_TRACE:
                return this.e;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return this.f;
            default:
                return null;
        }
    }

    public final Map<String, b> b(int i) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return this.m;
            case SimpleLog.LOG_LEVEL_TRACE:
                return this.n;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return this.o;
            default:
                return null;
        }
    }
}
