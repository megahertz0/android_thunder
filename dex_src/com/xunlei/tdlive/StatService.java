package com.xunlei.tdlive;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.xunlei.XLStat.CommonStruct.XLStatInitStruct;
import com.xunlei.XLStat.XLStat;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.tdlive.StatService.c;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.xllib.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.android.spdy.SpdyProtocol;

public class StatService extends Service implements Callback {
    private HandlerThread a;
    private Handler b;
    private a c;

    static class BaseData implements Parcelable {
        public static final Creator<BaseData> CREATOR;
        String a;
        Map<String, String> b;

        BaseData() {
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            if (this.b == null || this.b.size() <= 0) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(this.b.size());
            for (Entry entry : this.b.entrySet()) {
                parcel.writeString((String) entry.getKey());
                parcel.writeString((String) entry.getValue());
            }
        }

        static {
            CREATOR = new en();
        }
    }

    static class EventData extends BaseData {
        public static final Creator<EventData> CREATOR;
        String c;
        String d;

        EventData() {
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            if (this.c != null && this.d != null) {
                parcel.writeInt(R.styleable.Toolbar_maxButtonHeight);
                parcel.writeString(this.c);
                parcel.writeString(this.d);
            } else if (this.c != null) {
                parcel.writeInt(1);
                parcel.writeString(this.c);
            } else if (this.d != null) {
                parcel.writeInt(SpdyProtocol.CUSTOM);
                parcel.writeString(this.d);
            } else {
                parcel.writeInt(0);
            }
        }

        static {
            CREATOR = new eo();
        }
    }

    static class ValueData extends BaseData {
        public static final Creator<ValueData> CREATOR;
        int c;

        ValueData() {
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.c);
        }

        static {
            CREATOR = new ep();
        }
    }

    public static interface c {
        void a();

        void a(String str);

        void a(String str, String str2, String str3);

        void a(String str, String str2, String str3, Map<String, String> map);

        void a(String str, Map<String, String> map, int i);

        boolean a(Context context);

        void b();

        void b(String str);

        void c();

        void c(String str);

        void d(String str);
    }

    static class a implements c {
        private ArrayList<c> a;

        public a() {
            this.a = new ArrayList();
            if (XLog.enableLog()) {
                this.a.add(new d());
            }
            this.a.add(new g());
            this.a.add(new b());
        }

        public boolean a(Context context) {
            Iterator it = this.a.iterator();
            boolean z = false;
            while (it.hasNext()) {
                z = ((c) it.next()).a(context) | z;
            }
            return z;
        }

        public void a() {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a();
            }
        }

        public void a(String str) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(str);
            }
        }

        public void b() {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).b();
            }
        }

        public void c() {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).c();
            }
        }

        public void b(String str) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).b(str);
            }
        }

        public void c(String str) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).c(str);
            }
        }

        public void d(String str) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).d(str);
            }
        }

        public void a(String str, String str2, String str3) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(str, str2, str3);
            }
        }

        public void a(String str, String str2, String str3, Map<String, String> map) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(str, str2, str3, map);
            }
        }

        public void a(String str, Map<String, String> map, int i) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(str, (Map) map, i);
            }
        }
    }

    static class b implements c {
        private static Object a;
        private String b;
        private long c;

        public b() {
            this.b = UUID.randomUUID().toString();
            this.c = SystemClock.elapsedRealtime();
        }

        public boolean a(Context context) {
            if (a == null) {
                XLStatLog.closeLog = true;
                XLStatInitStruct xLStatInitStruct = new XLStatInitStruct();
                xLStatInitStruct.productName = ac.j() ? ac.d("HUBBLE_APP_NAME") : "zbapp_sl_android";
                xLStatInitStruct.productKey = ac.j() ? ac.d("HUBBLE_APP_KEY") : "emJhcHBfc2xfYW5kcm9pZAA9AAE=";
                xLStatInitStruct.productVersion = ac.j() ? ac.d() : "1.0.13";
                xLStatInitStruct.serviceName = ac.j() ? null : "shoulei";
                xLStatInitStruct.serviceKey = null;
                xLStatInitStruct.serviceVersion = ac.j() ? null : ac.d();
                xLStatInitStruct.extData = null;
                xLStatInitStruct.peerID = null;
                xLStatInitStruct.userID = 0;
                xLStatInitStruct.installchannel = ac.j() ? ac.d("UMENG_CHANNEL") : "ThunderSDK";
                xLStatInitStruct.startupchannel = null;
                xLStatInitStruct.configPath = ac.j() ? ac.d("HUBBLE_CONFIG") : "sdkStatXml.xml";
                xLStatInitStruct.dataTag = xLStatInitStruct.productName;
                xLStatInitStruct.heartbeatInterval = 600000;
                if (ac.j() && xLStatInitStruct.installchannel.equals("Test")) {
                    xLStatInitStruct.configPath = ac.d("HUBBLE_TEST_CONFIG");
                }
                try {
                    Object initXLStat = XLStat.initXLStat(context, xLStatInitStruct);
                    a = initXLStat;
                    if (initXLStat != null) {
                        XLStat.beginOnlineHeartbeatEx(a, R.styleable.AppCompatTheme_buttonStyle, null, true);
                        context.getApplicationContext().getSharedPreferences("__device_info__", 0).edit().putString("device_id", XLStat.getGuid(a)).commit();
                    }
                } catch (Exception e) {
                }
            }
            return true;
        }

        public void a() {
            try {
                if (a != null) {
                    XLStat.endOnlineHeartbeatEx(a, R.styleable.AppCompatTheme_buttonStyle, null);
                    XLStat.unInitXLStat(a);
                    a = null;
                }
            } catch (Exception e) {
            }
        }

        public void a(String str) {
            long j = 0;
            try {
                j = Long.parseLong(str);
            } catch (Exception e) {
            }
            try {
                if (a != null) {
                    XLStat.setUserID(a, j);
                }
            } catch (Exception e2) {
            }
        }

        public void b() {
            if (SystemClock.elapsedRealtime() - this.c > 300000) {
                this.b = UUID.randomUUID().toString();
                this.c = SystemClock.elapsedRealtime();
            }
        }

        public void c() {
            this.c = SystemClock.elapsedRealtime();
        }

        public void b(String str) {
        }

        public void c(String str) {
        }

        public void d(String str) {
            a(str, null, null, null);
        }

        public void a(String str, String str2, String str3) {
            a(str, str2, str3, null);
        }

        public void a(String str, String str2, String str3, Map<String, String> map) {
            if (map == null) {
                map = new HashMap();
            }
            map.put("sessionid", this.b);
            ArrayList arrayList = new ArrayList();
            for (Entry entry : map.entrySet()) {
                arrayList.add(((String) entry.getKey()) + "=" + ((String) entry.getValue()));
            }
            try {
                XLStat.traceEventCost(a, new StringBuilder("xllive_").append(str).toString(), str2, str3, 0, 0, 0, 0, arrayList);
            } catch (Exception e) {
            }
        }

        public void a(String str, Map<String, String> map, int i) {
        }
    }

    static class d implements c {
        d() {
        }

        public boolean a(Context context) {
            return true;
        }

        public void a() {
        }

        public void a(String str) {
        }

        public void b() {
        }

        public void c() {
        }

        public void b(String str) {
        }

        public void c(String str) {
        }

        public void d(String str) {
            a(str, null, null, null);
        }

        public void a(String str, String str2, String str3) {
            a(str, str2, str3, null);
        }

        public void a(String str, String str2, String str3, Map<String, String> map) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(new StringBuilder("---------------------- name = ").append(str).append(", attr1 = ").append(str2).append(", attr2 = ").append(str3).append("\n").toString());
            if (map != null && map.size() > 0) {
                for (Entry entry : map.entrySet()) {
                    stringBuilder.append(((String) entry.getKey()) + " = " + ((String) entry.getValue()) + "\n");
                }
            }
            stringBuilder.append("----------------------");
            XLog.d("StatService", stringBuilder.toString());
        }

        public void a(String str, Map<String, String> map, int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(new StringBuilder("---------------------- name = ").append(str).append(", value = ").append(i).append("\n").toString());
            if (map != null && map.size() > 0) {
                for (Entry entry : map.entrySet()) {
                    stringBuilder.append(((String) entry.getKey()) + " = " + ((String) entry.getValue()) + "\n");
                }
            }
            stringBuilder.append("----------------------");
            XLog.d("StatService", stringBuilder.toString());
        }
    }

    public static class e implements c {
        private Context a;

        public boolean a(Context context) {
            this.a = context.getApplicationContext();
            return true;
        }

        public void a() {
        }

        public void a(String str) {
            try {
                this.a.startService(new Intent(this.a, StatService.class).setAction("com.xunlei.tdlive.sdk.StatService.ACTION_SETUID_EVENT").putExtra("com.xunlei.tdlive.EXTRA_STAT_DATA", str));
            } catch (Exception e) {
            }
        }

        public void b() {
            try {
                this.a.startService(new Intent(this.a, StatService.class).setAction("com.xunlei.tdlive.sdk.StatService.ACTION_RESUME_EVENT"));
            } catch (Exception e) {
            }
        }

        public void c() {
            try {
                this.a.startService(new Intent(this.a, StatService.class).setAction("com.xunlei.tdlive.sdk.StatService.ACTION_PAUSE_VALUE"));
            } catch (Exception e) {
            }
        }

        public void b(String str) {
            try {
                this.a.startService(new Intent(this.a, StatService.class).setAction("com.xunlei.tdlive.sdk.StatService.ACTION_PAGESTART_EVENT").putExtra("com.xunlei.tdlive.EXTRA_STAT_DATA", str));
            } catch (Exception e) {
            }
        }

        public void c(String str) {
            try {
                this.a.startService(new Intent(this.a, StatService.class).setAction("com.xunlei.tdlive.sdk.StatService.ACTION_PAGEEND_EVENT").putExtra("com.xunlei.tdlive.EXTRA_STAT_DATA", str));
            } catch (Exception e) {
            }
        }

        public void d(String str) {
            a(str, null, null, null);
        }

        public void a(String str, String str2, String str3) {
            a(str, str2, str3, null);
        }

        public void a(String str, String str2, String str3, Map<String, String> map) {
            try {
                Parcelable eventData = new EventData();
                eventData.a = str;
                eventData.c = str2;
                eventData.d = str3;
                eventData.b = map;
                this.a.startService(new Intent(this.a, StatService.class).setAction("com.xunlei.tdlive.sdk.StatService.ACTION_TRACE_EVENT").putExtra("com.xunlei.tdlive.EXTRA_STAT_DATA", eventData));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void a(String str, Map<String, String> map, int i) {
            try {
                Parcelable valueData = new ValueData();
                valueData.a = str;
                valueData.c = i;
                valueData.b = map;
                this.a.startService(new Intent(this.a, StatService.class).setAction("com.xunlei.tdlive.sdk.StatService.ACTION_TRACE_VALUE").putExtra("com.xunlei.tdlive.EXTRA_STAT_DATA", valueData));
            } catch (Exception e) {
            }
        }
    }

    public static class f implements c {
        private Context a;

        public boolean a(Context context) {
            this.a = context.getApplicationContext();
            return true;
        }

        public void a() {
        }

        public void a(String str) {
        }

        public void b() {
            d("__onresume__");
        }

        public void c() {
            d("__onpause__");
        }

        public void b(String str) {
        }

        public void c(String str) {
        }

        public void d(String str) {
            a(str, null, null, null);
        }

        public void a(String str, String str2, String str3) {
            a(str, str2, str3, null);
        }

        public void a(String str, String str2, String str3, Map<String, String> map) {
            Map hashMap;
            if (map == null) {
                hashMap = new HashMap();
            } else {
                Map<String, String> map2 = map;
            }
            hashMap.put("version", "1.0.13");
            try {
                XLLiveSDK.getInstance(this.a).host().traceEvent(this.a, str, str2, str3, hashMap);
            } catch (Exception e) {
            }
        }

        public void a(String str, Map<String, String> map, int i) {
        }
    }

    static class g implements c {
        private static Context a;

        g() {
        }

        public boolean a(Context context) {
            a = context.getApplicationContext();
            try {
                MobclickAgent.setDebugMode(XLog.enableLog());
                MobclickAgent.setCatchUncaughtExceptions(false);
                if (!ac.j()) {
                    AnalyticsConfig.setAppkey(null, "575e97e067e58e2a36000342");
                    AnalyticsConfig.setChannel("ThunderSDK");
                } else if (ac.d("UMENG_CHANNEL").equals("Test")) {
                    AnalyticsConfig.setAppkey(null, ac.d("UMENG_TEST_APPKEY"));
                }
            } catch (Exception e) {
            }
            return true;
        }

        public void a() {
        }

        public void a(String str) {
        }

        public void b() {
            try {
                MobclickAgent.onResume(a);
            } catch (Exception e) {
            }
        }

        public void c() {
            try {
                MobclickAgent.onPause(a);
            } catch (Exception e) {
            }
        }

        public void b(String str) {
            try {
                MobclickAgent.onPageStart(str);
            } catch (Exception e) {
            }
        }

        public void c(String str) {
            try {
                MobclickAgent.onPageEnd(str);
            } catch (Exception e) {
            }
        }

        public void d(String str) {
            try {
                MobclickAgent.onEvent(a, str);
            } catch (Exception e) {
            }
        }

        public void a(String str, String str2, String str3) {
            a(str, str2, str3, null);
        }

        public void a(String str, String str2, String str3, Map<String, String> map) {
            if (map == null) {
                map = new HashMap();
            }
            if (!TextUtils.isEmpty(str2)) {
                map.put("attr1", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                map.put("attr2", str3);
            }
            if (map.size() > 0) {
                try {
                    MobclickAgent.onEvent(a, str, map);
                    return;
                } catch (Exception e) {
                }
            }
            try {
                MobclickAgent.onEvent(a, str);
            } catch (Exception e2) {
            }
        }

        public void a(String str, Map<String, String> map, int i) {
            try {
                MobclickAgent.onEventValue(a, str, map, i);
            } catch (Exception e) {
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        XLog.d("StatService", "onCreate()");
        this.a = new HandlerThread("xlive-stat");
        this.a.start();
        this.b = new Handler(this.a.getLooper(), this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.a.quit();
        XLog.d("StatService", "onDestroy()");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.b.obtainMessage(9013, intent).sendToTarget();
        return 1;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 9013) {
            if (message.obj != null) {
                if (this.c == null) {
                    this.c = new a();
                    this.c.a((Context) this);
                }
                try {
                    a(this.c, (Intent) message.obj);
                } catch (Exception e) {
                }
            }
        } else if (message.what == 9014) {
            if (this.c != null) {
                this.c.a();
                this.c = null;
            }
            stopSelf();
        }
        return true;
    }

    private void a(c cVar, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.length() > 0) {
            if (action.equals("com.xunlei.tdlive.sdk.StatService.ACTION_TRACE_EVENT")) {
                EventData eventData = (EventData) intent.getParcelableExtra("com.xunlei.tdlive.EXTRA_STAT_DATA");
                cVar.a(eventData.a, eventData.c, eventData.d, eventData.b);
            } else if (action.equals("com.xunlei.tdlive.sdk.StatService.ACTION_TRACE_VALUE")) {
                ValueData valueData = (ValueData) intent.getParcelableExtra("com.xunlei.tdlive.EXTRA_STAT_DATA");
                cVar.a(valueData.a, valueData.b, valueData.c);
            } else if (action.equals("com.xunlei.tdlive.sdk.StatService.ACTION_PAUSE_VALUE")) {
                cVar.c();
            } else if (action.equals("com.xunlei.tdlive.sdk.StatService.ACTION_RESUME_EVENT")) {
                cVar.b();
            } else if (action.equals("com.xunlei.tdlive.sdk.StatService.ACTION_PAGESTART_EVENT")) {
                cVar.b(intent.getStringExtra("com.xunlei.tdlive.EXTRA_STAT_DATA"));
            } else if (action.equals("com.xunlei.tdlive.sdk.StatService.ACTION_PAGEEND_EVENT")) {
                cVar.c(intent.getStringExtra("com.xunlei.tdlive.EXTRA_STAT_DATA"));
            } else if (action.equals("com.xunlei.tdlive.sdk.StatService.ACTION_SETUID_EVENT")) {
                cVar.a(intent.getStringExtra("com.xunlei.tdlive.EXTRA_STAT_DATA"));
            }
        }
    }
}
