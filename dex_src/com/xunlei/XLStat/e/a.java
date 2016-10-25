package com.xunlei.XLStat.e;

import android.content.Context;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.e;
import com.xunlei.XLStat.f;
import com.xunlei.XLStat.f.b;
import com.xunlei.XLStat.g;
import com.xunlei.tdlive.protocol.PushTagRequest;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class a {
    private static String a;
    private boolean b;
    private b c;
    private Context d;
    private final String e;
    private String f;

    static {
        a = "DBManager";
    }

    public a() {
        this.b = false;
        this.e = "50";
        this.f = BuildConfig.VERSION_NAME;
    }

    public boolean a(Context context, String str) {
        this.b = false;
        if (str == null) {
            str = BuildConfig.VERSION_NAME;
        }
        this.f = str;
        if (context == null) {
            XLStatLog.d(a, "init", "init DBManager failed ... ");
            return this.b;
        }
        this.d = context;
        this.c = new b(this.d, b.a(), this.f);
        XLStatLog.d(a, "init", new StringBuilder("mHelper: ").append(this.c).toString());
        if (this.c == null) {
            this.b = false;
            return this.b;
        }
        this.b = true;
        return this.b;
    }

    public void a(String str) {
        if (str == null) {
            str = BuildConfig.VERSION_NAME;
        }
        this.f = str;
    }

    private boolean c() {
        if (this.b) {
            try {
                if (b(b.b()) && b(b.c()) && b(b.d()) && b(b.e())) {
                    XLStatLog.d(a, "varifyInit", "verifyInit successfull");
                    return true;
                }
                XLStatLog.d(a, "varifyInit", " varifyInit invalid ... ");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        XLStatLog.d(a, "varifyInit", new StringBuilder("mInited: ").append(this.b).toString());
        return false;
    }

    private boolean b(String str) {
        XLStatLog.d(a, "createTable", new StringBuilder("mInit: ").append(this.b).append("  create tables: ").append(str).toString());
        if (str != null && b.b().equals(str)) {
            this.c.a(str);
            return true;
        } else if (str != null && b.c().equals(str)) {
            this.c.b(str);
            return true;
        } else if (str != null && b.d().equals(str)) {
            this.c.c(str);
            return true;
        } else if (str == null || !b.e().equals(str)) {
            return false;
        } else {
            this.c.d(str);
            return true;
        }
    }

    public void a(Object obj, int i) {
        XLStatLog.d(a, PushTagRequest.T_ADD, new StringBuilder("mInit: ").append(this.b).append(obj).append("  type: ").append(i).toString());
        if (obj == null) {
            XLStatLog.d(a, PushTagRequest.T_ADD, "statObject is null");
        } else if (c()) {
            String str = BuildConfig.VERSION_NAME;
            switch (i) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    str = b.b();
                    break;
                case SimpleLog.LOG_LEVEL_TRACE:
                    str = b.c();
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    str = b.d();
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    str = b.e();
                    break;
            }
            if (BuildConfig.VERSION_NAME.equals(str) || str.length() <= 0) {
                XLStatLog.d(a, PushTagRequest.T_ADD, "table name error ... ");
                return;
            }
            try {
                this.c.a(str, obj);
            } catch (Exception e) {
                XLStatLog.d(a, PushTagRequest.T_ADD, "insert data exception ... ");
                e.printStackTrace();
            }
        }
    }

    public int a(int i, Object obj) {
        XLStatLog.d(a, "delete", new StringBuilder("type: ").append(i).toString());
        if (!c() || obj == null) {
            return 0;
        }
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return a(obj);
            case SimpleLog.LOG_LEVEL_TRACE:
                return b(obj);
            case SimpleLog.LOG_LEVEL_DEBUG:
                return 0;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return c(obj);
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return d(obj);
            default:
                return 0;
        }
    }

    public int a(Object obj) {
        Exception exception;
        int i = 0;
        if (!c() || obj == null) {
            return 0;
        }
        if (obj instanceof ArrayList) {
            ArrayList arrayList = (ArrayList) obj;
            String b = b.b();
            if (BuildConfig.VERSION_NAME.equals(b) || b.length() <= 0) {
                return -1;
            }
            try {
                Iterator it = arrayList.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    try {
                        e eVar = (e) it.next();
                        i2 += this.c.a(b, "EVENT_ORDER=?", new String[]{eVar.k});
                    } catch (Exception e) {
                        Exception exception2 = e;
                        i = i2;
                        exception = exception2;
                    }
                }
                XLStatLog.d(a, "deleteEvent", new StringBuilder("delete ").append(i2).append("events").toString());
                return i2;
            } catch (Exception e2) {
                exception = e2;
                exception.printStackTrace();
                return i;
            }
        }
        XLStatLog.d(a, "deleteEvent", "event obj error ... ");
        return 0;
    }

    public int b(Object obj) {
        Exception exception;
        int i = 0;
        if (obj == null || !c()) {
            return 0;
        }
        if (obj instanceof ArrayList) {
            ArrayList arrayList = (ArrayList) obj;
            String c = b.c();
            if (BuildConfig.VERSION_NAME.equals(c) || c.length() <= 0) {
                return -1;
            }
            try {
                Iterator it = arrayList.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    try {
                        b bVar = (b) it.next();
                        i2 += this.c.a(c, "CONTEXT_ORDER=?", new String[]{bVar.g});
                    } catch (Exception e) {
                        Exception exception2 = e;
                        i = i2;
                        exception = exception2;
                    }
                }
                XLStatLog.d(a, "deleteContext", new StringBuilder("delete ").append(i2).append(" contexts").toString());
                return i2;
            } catch (Exception e2) {
                exception = e2;
                exception.printStackTrace();
                return i;
            }
        }
        XLStatLog.d(a, "deleteEvent", "context obj error ... ");
        return 0;
    }

    public int c(Object obj) {
        Exception exception;
        int i = 0;
        if (obj == null || !c()) {
            return 0;
        }
        if (obj instanceof ArrayList) {
            ArrayList arrayList = (ArrayList) obj;
            String d = b.d();
            if (d == null || BuildConfig.VERSION_NAME.equals(d) || d.length() <= 0) {
                return -1;
            }
            try {
                Iterator it = arrayList.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    try {
                        f fVar = (f) it.next();
                        i2 += this.c.a(d, "HEARTBEAT_ORDER=?", new String[]{fVar.g});
                    } catch (Exception e) {
                        Exception exception2 = e;
                        i = i2;
                        exception = exception2;
                    }
                }
                XLStatLog.d(a, "deleteHeartbeat", new StringBuilder("delete ").append(i2).append(" heartbeats").toString());
                return i2;
            } catch (Exception e2) {
                exception = e2;
                exception.printStackTrace();
                return i;
            }
        }
        XLStatLog.d(a, "deleteHeartbeat", "heartbeat obj error ... ");
        return 0;
    }

    public int d(Object obj) {
        if (obj == null || !c() || !(obj instanceof ArrayList)) {
            return 0;
        }
        ArrayList arrayList = (ArrayList) obj;
        String e = b.e();
        if (e == null || BuildConfig.VERSION_NAME.equals(e) || e.length() <= 0) {
            return -1;
        }
        try {
            Iterator it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                g gVar = (g) it.next();
                i += this.c.a(e, "SessionDataId=?", new String[]{gVar.a});
            }
            return i;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public Object a(int i, int i2) {
        Object obj = null;
        XLStatLog.d(a, "query", new StringBuilder("type: ").append(i).append("  strategy: ").append(i2).toString());
        if (!c()) {
            XLStatLog.d(a, "query", "varifyInit failed ... ");
        } else if (this.b) {
            String str = BuildConfig.VERSION_NAME;
            switch (i) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    str = b.b();
                    XLStatLog.d(a, "query", new StringBuilder(" table name: ").append(str).toString());
                    if (str.equals(BuildConfig.VERSION_NAME)) {
                    }
                case SimpleLog.LOG_LEVEL_TRACE:
                    str = b.c();
                    XLStatLog.d(a, "query", new StringBuilder(" table name: ").append(str).toString());
                    if (BuildConfig.VERSION_NAME.equals(str)) {
                    }
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    str = b.d();
                    break;
            }
            try {
                obj = this.c.a(str, i2, "50");
            } catch (Exception e) {
                e.printStackTrace();
            }
            XLStatLog.d(a, "query", new StringBuilder("obj: ").append(obj).toString());
        } else {
            XLStatLog.d(a, "query", new StringBuilder("mInited is: ").append(this.b).toString());
        }
        return obj;
    }

    public ArrayList<g> a() {
        return this.c.e("50");
    }

    public void b() {
        if (this.b) {
            this.d = null;
            this.b = false;
        }
    }
}
