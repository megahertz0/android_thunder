package com.xiaomi.network;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import com.taobao.accs.data.Message;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.c;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.common.logger.thrift.mfs.e;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.network.HostManager.HttpGet;
import com.xiaomi.network.UploadHostStatHelper.HttpRecordCallback;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HostManager {
    private static HostManagerFactory factory;
    protected static boolean hostLoaded;
    protected static Map<String, ArrayList<String>> mReservedHosts;
    private static String sAppName;
    private static String sAppVersion;
    private static HostManager sInstance;
    private final long MAX_REQUEST_FAILURE_CNT;
    private String currentISP;
    private HttpRecordCallback httpRecordCallback;
    private long lastRemoteRequestTimestamp;
    protected Map<String, Fallbacks> mHostsMapping;
    private long remoteRequestFailureCount;
    public Context sAppContext;
    private HostFilter sHostFilter;
    protected HttpGet sHttpGetter;
    private String sUserId;

    public static interface HostManagerFactory {
        HostManager a(Context context, HostFilter hostFilter, HttpGet httpGet, String str);
    }

    public static interface HttpGet {
        String a(String str);
    }

    static {
        mReservedHosts = new HashMap();
        hostLoaded = false;
    }

    protected HostManager(Context context, HostFilter hostFilter, HttpGet httpGet, String str, String str2, String str3) {
        this.mHostsMapping = new HashMap();
        this.sUserId = MessageService.MSG_DB_READY_REPORT;
        this.remoteRequestFailureCount = 0;
        this.MAX_REQUEST_FAILURE_CNT = 15;
        this.lastRemoteRequestTimestamp = 0;
        this.currentISP = "isp_prov_city_country_ip";
        this.httpRecordCallback = new a(this);
        this.sAppContext = context.getApplicationContext();
        if (this.sAppContext == null) {
            this.sAppContext = context;
        }
        this.sHttpGetter = httpGet;
        if (hostFilter == null) {
            this.sHostFilter = new b(this);
        } else {
            this.sHostFilter = hostFilter;
        }
        this.sUserId = str;
        if (str2 == null) {
            str2 = context.getPackageName();
        }
        sAppName = str2;
        if (str3 == null) {
            str3 = getVersionName();
        }
        sAppVersion = str3;
    }

    public static void addReservedHost(String str, String str2) {
        ArrayList arrayList = (ArrayList) mReservedHosts.get(str);
        synchronized (mReservedHosts) {
            if (arrayList == null) {
                arrayList = new ArrayList();
                arrayList.add(str2);
                mReservedHosts.put(str, arrayList);
            } else if (!arrayList.contains(str2)) {
                arrayList.add(str2);
            }
        }
    }

    public static synchronized HostManager getInstance() {
        HostManager hostManager;
        synchronized (HostManager.class) {
            if (sInstance == null) {
                throw new IllegalStateException("the host manager is not initialized yet.");
            }
            hostManager = sInstance;
        }
        return hostManager;
    }

    private String getVersionName() {
        try {
            PackageInfo packageInfo = this.sAppContext.getPackageManager().getPackageInfo(this.sAppContext.getPackageName(), Message.FLAG_REQ_BIT1);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (Exception e) {
        }
        return MessageService.MSG_DB_READY_REPORT;
    }

    public static synchronized void init(Context context, HostFilter hostFilter, HttpGet httpGet, String str, String str2, String str3) {
        synchronized (HostManager.class) {
            if (sInstance == null) {
                if (factory == null) {
                    sInstance = new HostManager(context, hostFilter, httpGet, str, str2, str3);
                } else {
                    sInstance = factory.a(context, hostFilter, httpGet, str);
                }
                if (sInstance != null) {
                    if (UploadHostStatHelper.a() == null) {
                        UploadHostStatHelper.a(context);
                    }
                    UploadHostStatHelper.a().a(sInstance.httpRecordCallback);
                }
            }
        }
    }

    public static <T> String join(Collection<T> collection, String str) {
        if (collection == null || collection.isEmpty()) {
            return a.d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            stringBuilder.append(it.next());
            if (it.hasNext()) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    public static String join(String[] strArr, String str) {
        if (strArr == null || strArr.length == 0) {
            return a.d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            stringBuilder.append(str);
            stringBuilder.append(strArr[i]);
        }
        return stringBuilder.toString();
    }

    private String processNetwork(String str) {
        return TextUtils.isEmpty(str) ? UtilityImpl.NET_TYPE_UNKNOWN : str.startsWith("WIFI") ? "WIFI" : str;
    }

    private ArrayList<Fallback> requestRemoteFallbacks(ArrayList<String> arrayList) {
        String str;
        int i;
        purge();
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            for (String str2 : this.mHostsMapping.keySet()) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        synchronized (mReservedHosts) {
            for (String str22 : mReservedHosts.keySet()) {
                if (!arrayList.contains(str22)) {
                    arrayList.add(str22);
                }
            }
        }
        if (!arrayList.contains(getHost())) {
            arrayList.add(getHost());
        }
        ArrayList<Fallback> arrayList2 = new ArrayList(arrayList.size());
        for (i = 0; i < arrayList.size(); i++) {
            arrayList2.add(null);
        }
        try {
            str22 = d.e(this.sAppContext) ? UtilityImpl.NET_TYPE_WIFI : "wap";
            Object remoteFallbackJSON = getRemoteFallbackJSON(arrayList, str22, this.sUserId);
            if (!TextUtils.isEmpty(remoteFallbackJSON)) {
                JSONObject jSONObject = new JSONObject(remoteFallbackJSON);
                if ("OK".equalsIgnoreCase(jSONObject.getString("S"))) {
                    jSONObject = jSONObject.getJSONObject("R");
                    String string = jSONObject.getString("province");
                    String string2 = jSONObject.getString("city");
                    String string3 = jSONObject.getString("isp");
                    String string4 = jSONObject.getString("ip");
                    String string5 = jSONObject.getString("country");
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str22);
                    if (str22.equals("wap")) {
                        str22 = getActiveNetworkLabel();
                    }
                    b.a(new StringBuilder("get bucket: ip = ").append(string4).append(" net = ").append(string3).append(str22).append(" hosts = ").append(jSONObject2.toString()).toString());
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        str22 = (String) arrayList.get(i2);
                        JSONArray optJSONArray = jSONObject2.optJSONArray(str22);
                        if (optJSONArray == null) {
                            b.a(new StringBuilder("no bucket found for ").append(str22).toString());
                        } else {
                            Fallback fallback = new Fallback(str22);
                            for (i = 0; i < optJSONArray.length(); i++) {
                                Object string6 = optJSONArray.getString(i);
                                if (!TextUtils.isEmpty(string6)) {
                                    fallback.a(new e(string6, optJSONArray.length() - i));
                                }
                            }
                            arrayList2.set(i2, fallback);
                            fallback.g = string5;
                            fallback.c = string;
                            fallback.e = string3;
                            fallback.f = string4;
                            fallback.d = string2;
                            if (jSONObject.has("stat-percent")) {
                                fallback.a(jSONObject.getDouble("stat-percent"));
                            }
                            if (jSONObject.has("stat-domain")) {
                                fallback.b(jSONObject.getString("stat-domain"));
                            }
                            if (jSONObject.has("ttl")) {
                                fallback.a(((long) jSONObject.getInt("ttl")) * 1000);
                            }
                            setCurrentISP(fallback.e());
                        }
                    }
                }
            }
        } catch (Exception e) {
            b.a(new StringBuilder("failed to get bucket ").append(e.getMessage()).toString());
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            Fallback fallback2 = (Fallback) arrayList2.get(i3);
            if (fallback2 != null) {
                updateFallbacks((String) arrayList.get(i3), fallback2);
            }
        }
        persist();
        return arrayList2;
    }

    public static synchronized void setHostManagerFactory(HostManagerFactory hostManagerFactory) {
        synchronized (HostManager.class) {
            factory = hostManagerFactory;
            if (!(UploadHostStatHelper.a() == null || sInstance == null)) {
                UploadHostStatHelper.a().b(sInstance.httpRecordCallback);
            }
            sInstance = null;
        }
    }

    protected boolean checkHostMapping() {
        synchronized (this.mHostsMapping) {
            if (hostLoaded) {
                return true;
            }
            hostLoaded = true;
            this.mHostsMapping.clear();
            try {
                Object loadHosts = loadHosts();
                if (!TextUtils.isEmpty(loadHosts)) {
                    fromJSON(loadHosts);
                    b.a("loading the new hosts succeed");
                    return true;
                }
            } catch (Throwable th) {
                b.a(new StringBuilder("load host exception ").append(th.getMessage()).toString());
            }
            return false;
        }
    }

    public void clear() {
        synchronized (this.mHostsMapping) {
            this.mHostsMapping.clear();
        }
    }

    protected void fromJSON(String str) {
        synchronized (this.mHostsMapping) {
            this.mHostsMapping.clear();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                Fallbacks fromJSON = new Fallbacks().fromJSON(jSONArray.getJSONObject(i));
                this.mHostsMapping.put(fromJSON.getHost(), fromJSON);
            }
        }
    }

    public ArrayList<com.xiaomi.common.logger.thrift.mfs.b> generateHostStats() {
        ArrayList<com.xiaomi.common.logger.thrift.mfs.b> arrayList;
        synchronized (this.mHostsMapping) {
            Map hashMap = new HashMap();
            for (String str : this.mHostsMapping.keySet()) {
                com.xiaomi.common.logger.thrift.mfs.b bVar;
                Fallbacks fallbacks = (Fallbacks) this.mHostsMapping.get(str);
                if (fallbacks != null) {
                    Iterator it = fallbacks.getFallbacks().iterator();
                    while (it.hasNext()) {
                        com.xiaomi.common.logger.thrift.mfs.b bVar2;
                        Fallback fallback = (Fallback) it.next();
                        bVar = (com.xiaomi.common.logger.thrift.mfs.b) hashMap.get(fallback.e());
                        if (bVar == null) {
                            bVar = new com.xiaomi.common.logger.thrift.mfs.b();
                            bVar.a("httpapi");
                            bVar.e(fallback.f);
                            bVar.d(processNetwork(fallback.a));
                            bVar.b(this.sUserId);
                            bVar.c(sAppVersion);
                            bVar.f(sAppName);
                            bVar.g(this.sAppContext.getPackageName());
                            bVar.h(getVersionName());
                            e eVar = new e();
                            eVar.c(fallback.d);
                            eVar.a(fallback.g);
                            eVar.b(fallback.c);
                            eVar.d(fallback.e);
                            bVar.a(eVar);
                            hashMap.put(fallback.e(), bVar);
                            bVar2 = bVar;
                        } else {
                            bVar2 = bVar;
                        }
                        com.xiaomi.common.logger.thrift.mfs.a aVar = new com.xiaomi.common.logger.thrift.mfs.a();
                        aVar.a(fallback.b);
                        List arrayList2 = new ArrayList();
                        Iterator it2 = fallback.f().iterator();
                        while (it2.hasNext()) {
                            e eVar2 = (e) it2.next();
                            ArrayList a = eVar2.a();
                            if (!a.isEmpty()) {
                                com.xiaomi.common.logger.thrift.mfs.d dVar = new com.xiaomi.common.logger.thrift.mfs.d();
                                dVar.a(eVar2.a);
                                Map hashMap2 = new HashMap();
                                Iterator it3 = a.iterator();
                                int i = 0;
                                int i2 = 0;
                                long j = 0;
                                int i3 = 0;
                                while (it3.hasNext()) {
                                    AccessHistory accessHistory = (AccessHistory) it3.next();
                                    if (accessHistory.a() >= 0) {
                                        i++;
                                        j += accessHistory.b();
                                        i3 = (int) (accessHistory.d() + ((long) i3));
                                    } else {
                                        CharSequence e = accessHistory.e();
                                        if (!TextUtils.isEmpty(e)) {
                                            hashMap2.put(e, Integer.valueOf(hashMap2.containsKey(e) ? ((Integer) hashMap2.get(e)).intValue() + 1 : 1));
                                        }
                                        i2++;
                                    }
                                }
                                dVar.a(hashMap2);
                                dVar.b(i);
                                dVar.a(i2);
                                dVar.a(j);
                                dVar.c(i3);
                                arrayList2.add(dVar);
                            }
                        }
                        if (!arrayList2.isEmpty()) {
                            aVar.a(arrayList2);
                            bVar2.a(aVar);
                        }
                    }
                    continue;
                }
            }
            arrayList = new ArrayList();
            for (com.xiaomi.common.logger.thrift.mfs.b bVar3 : hashMap.values()) {
                if (bVar3.g() > 0) {
                    arrayList.add(bVar3);
                }
            }
        }
        return arrayList;
    }

    public String getActiveNetworkLabel() {
        if (this.sAppContext == null) {
            return UtilityImpl.NET_TYPE_UNKNOWN;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.sAppContext.getSystemService("connectivity");
            if (connectivityManager == null) {
                return UtilityImpl.NET_TYPE_UNKNOWN;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return UtilityImpl.NET_TYPE_UNKNOWN;
            }
            if (activeNetworkInfo.getType() != 1) {
                return activeNetworkInfo.getTypeName() + SocializeConstants.OP_DIVIDER_MINUS + activeNetworkInfo.getSubtypeName();
            }
            WifiManager wifiManager = (WifiManager) this.sAppContext.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            if (!(wifiManager == null || wifiManager.getConnectionInfo() == null)) {
                return new StringBuilder("WIFI-").append(wifiManager.getConnectionInfo().getSSID()).toString();
            }
            return UtilityImpl.NET_TYPE_UNKNOWN;
        } catch (Exception e) {
        }
    }

    public Fallback getFallbacksByHost(String str) {
        return getFallbacksByHost(str, true);
    }

    public Fallback getFallbacksByHost(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        } else if (!this.sHostFilter.a(str)) {
            return null;
        } else {
            Fallback localFallback = getLocalFallback(str);
            if (localFallback != null && localFallback.b()) {
                return localFallback;
            }
            if (z && d.d(this.sAppContext)) {
                Fallback requestRemoteFallback = requestRemoteFallback(str);
                if (requestRemoteFallback != null) {
                    return requestRemoteFallback;
                }
            }
            return new c(this, str, localFallback);
        }
    }

    public Fallback getFallbacksByURL(String str) {
        if (!TextUtils.isEmpty(str)) {
            return getFallbacksByHost(new URL(str).getHost(), true);
        }
        throw new IllegalArgumentException("the url is empty");
    }

    protected String getHost() {
        return "resolver.gslb.mi-idc.com";
    }

    protected Fallback getLocalFallback(String str) {
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            Fallbacks fallbacks = (Fallbacks) this.mHostsMapping.get(str);
        }
        if (fallbacks != null) {
            Fallback fallback = fallbacks.getFallback();
            if (fallback != null) {
                return fallback;
            }
        }
        return null;
    }

    protected String getProcessName() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.sAppContext.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return "com.xiaomi";
    }

    public String getRemoteFallbackJSON(ArrayList<String> arrayList, String str, String str2) {
        ArrayList arrayList2 = new ArrayList();
        List<c> arrayList3 = new ArrayList();
        arrayList3.add(new com.xiaomi.channel.commonutils.network.a(JsInterface.FUNPLAY_AD_TRPE, str));
        arrayList3.add(new com.xiaomi.channel.commonutils.network.a("uuid", str2));
        arrayList3.add(new com.xiaomi.channel.commonutils.network.a("list", join((Collection) arrayList, MiPushClient.ACCEPT_TIME_SEPARATOR)));
        Fallback localFallback = getLocalFallback("resolver.gslb.mi-idc.com");
        String format = String.format("http://%1$s/gslb/gslb/getbucket.asp?ver=3.0", new Object[]{"resolver.gslb.mi-idc.com"});
        if (localFallback == null) {
            arrayList2.add(format);
        } else {
            arrayList2 = localFallback.a(format);
        }
        Iterator it = arrayList2.iterator();
        IOException iOException = null;
        while (it.hasNext()) {
            try {
                Builder buildUpon = Uri.parse((String) it.next()).buildUpon();
                for (c cVar : arrayList3) {
                    buildUpon.appendQueryParameter(cVar.a(), cVar.b());
                }
                return this.sHttpGetter == null ? d.a(this.sAppContext, new URL(buildUpon.toString())) : this.sHttpGetter.a(buildUpon.toString());
            } catch (IOException e) {
                iOException = e;
                b.a(new StringBuilder("network ioErr: ").append(iOException.getMessage()).toString());
            }
        }
        if (iOException == null) {
            return null;
        }
        throw iOException;
    }

    protected String loadHosts() {
        Reader bufferedReader;
        Throwable th;
        try {
            File file = new File(this.sAppContext.getFilesDir(), getProcessName());
            if (file.isFile()) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            stringBuilder.append(readLine);
                        } else {
                            String toString = stringBuilder.toString();
                            com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                            return toString;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                com.xiaomi.channel.commonutils.file.a.a(null);
                return null;
            }
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
            throw th;
        }
    }

    public void persist() {
        purge();
        synchronized (this.mHostsMapping) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.sAppContext.openFileOutput(getProcessName(), 0)));
                Object toString = toJSON().toString();
                if (!TextUtils.isEmpty(toString)) {
                    bufferedWriter.write(toString);
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void purge() {
        synchronized (this.mHostsMapping) {
            for (Fallbacks fallbacks : this.mHostsMapping.values()) {
                fallbacks.purge(false);
            }
            while (true) {
                Object obj = null;
                while (obj == null) {
                    String str;
                    Iterator it = this.mHostsMapping.keySet().iterator();
                    do {
                        if (it.hasNext()) {
                            str = (String) it.next();
                        } else {
                            int i = 1;
                        }
                    } while (!((Fallbacks) this.mHostsMapping.get(str)).getFallbacks().isEmpty());
                    this.mHostsMapping.remove(str);
                }
            }
        }
    }

    public void refreshFallbacks() {
        ArrayList arrayList;
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            arrayList = new ArrayList(this.mHostsMapping.keySet());
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Fallbacks fallbacks = (Fallbacks) this.mHostsMapping.get(arrayList.get(size));
                if (fallbacks != null && fallbacks.getFallback() != null) {
                    arrayList.remove(size);
                }
            }
        }
        ArrayList requestRemoteFallbacks = requestRemoteFallbacks(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (requestRemoteFallbacks.get(i) != null) {
                updateFallbacks((String) arrayList.get(i), (Fallback) requestRemoteFallbacks.get(i));
            }
        }
    }

    protected Fallback requestRemoteFallback(String str) {
        if (System.currentTimeMillis() - this.lastRemoteRequestTimestamp > (this.remoteRequestFailureCount * 60) * 1000) {
            this.lastRemoteRequestTimestamp = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            Fallback fallback = (Fallback) requestRemoteFallbacks(arrayList).get(0);
            if (fallback != null) {
                this.remoteRequestFailureCount = 0;
                return fallback;
            } else if (this.remoteRequestFailureCount < 15) {
                this.remoteRequestFailureCount++;
            }
        }
        return null;
    }

    public void setCurrentISP(String str) {
        this.currentISP = str;
    }

    protected JSONArray toJSON() {
        JSONArray jSONArray;
        synchronized (this.mHostsMapping) {
            jSONArray = new JSONArray();
            for (Fallbacks fallbacks : this.mHostsMapping.values()) {
                jSONArray.put(fallbacks.toJSON());
            }
        }
        return jSONArray;
    }

    public void updateFallbacks(String str, Fallback fallback) {
        if (TextUtils.isEmpty(str) || fallback == null) {
            throw new IllegalArgumentException(new StringBuilder("the argument is invalid ").append(str).append(", ").append(fallback).toString());
        } else if (this.sHostFilter.a(str)) {
            synchronized (this.mHostsMapping) {
                checkHostMapping();
                if (this.mHostsMapping.containsKey(str)) {
                    ((Fallbacks) this.mHostsMapping.get(str)).addFallback(fallback);
                } else {
                    Fallbacks fallbacks = new Fallbacks(str);
                    fallbacks.addFallback(fallback);
                    this.mHostsMapping.put(str, fallbacks);
                }
            }
        }
    }
}
