package com.taobao.accs.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Environment;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.client.AccsConfig;
import com.taobao.accs.client.AccsConfig.ACCS_GROUP;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.internal.b;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.c;
import com.taobao.accs.utl.h;
import com.umeng.socialize.common.SocializeConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: Taobao
public class a {
    public static final String ACTION_ACCS_CUSTOM_ELECTION;
    public static final String ACTION_ACCS_ELECTION = "com.taobao.accs.intent.action.ELECTION";
    public static final String TAG = "ElectionServiceUtil";
    public static String a;
    private static File b;
    private static File c;
    private static long d;
    private static final Random e;

    // compiled from: Taobao
    public static class a {
        public String a;
        public int b;

        public a() {
            this.a = com.umeng.a.d;
            this.b = 0;
        }
    }

    static {
        a = null;
        b = null;
        c = null;
        d = 0;
        e = new Random();
        ACTION_ACCS_CUSTOM_ELECTION = new StringBuilder("com.taobao.accs.intent.action.").append(AccsConfig.mGroup).append("ELECTION").toString();
    }

    public static void a() {
        try {
            b = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + c());
            ALog.i(TAG, new StringBuilder("path=").append(b).toString(), new Object[0]);
            File file = new File(b, b.ELECTION_SERVICE_ID);
            c = file;
            a = file.getPath();
        } catch (Throwable th) {
            ALog.e(TAG, TAG, th, new Object[0]);
        }
    }

    public static final void a(Context context, a aVar) {
        if (aVar != null) {
            GlobalClientInfo.getInstance(context).setElectionReslt(aVar);
            com.taobao.accs.common.a.a(new b(context, aVar));
        }
    }

    public static final a a(Context context) {
        if (!h.c()) {
            return new a();
        }
        a electionResult = GlobalClientInfo.getInstance(context).getElectionResult();
        if (electionResult == null) {
            return b(context);
        }
        ALog.i(TAG, "getElectionResult from mem", b.ELECTION_KEY_HOST, electionResult.a, "retry", Integer.valueOf(electionResult.b));
        return electionResult;
    }

    public static final a b(Context context) {
        Throwable th;
        a aVar = new a();
        FileInputStream fileInputStream = null;
        try {
            if (b == null) {
                a();
            }
            File file = new File(a);
            if (file.exists()) {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[fileInputStream2.available()];
                    fileInputStream2.read(bArr);
                    JSONObject jSONObject = new JSONObject(new String(bArr, GameManager.DEFAULT_CHARSET));
                    String string = jSONObject.getString("package");
                    if (UtilityImpl.packageExist(context, string)) {
                        aVar.a = string;
                        d = jSONObject.getLong("lastFlushTime");
                    }
                    if (System.currentTimeMillis() < d + 86400000) {
                        aVar.b = jSONObject.getInt("retry");
                        fileInputStream = fileInputStream2;
                    } else {
                        d = 0;
                        fileInputStream = fileInputStream2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
            ALog.i(TAG, "getElectionResult", b.ELECTION_KEY_HOST, aVar.a, "retry", Integer.valueOf(aVar.b));
            GlobalClientInfo.getInstance(context).setElectionReslt(aVar);
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Throwable th3) {
            th = th3;
            try {
                ALog.e(TAG, "readFile is error", th, new Object[0]);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
            return aVar;
        }
        return aVar;
    }

    public static final void a(Context context, byte[] bArr) {
        if (bArr == null || b == null) {
            ALog.e(TAG, "saveBlackList null", SocializeConstants.JSON_DATA, bArr, "path", b);
            return;
        }
        ALog.i(TAG, "saveBlackList", "path", b + "/accs_blacklist");
        GlobalClientInfo.getInstance(context).setElectionBlackList(a(bArr));
        com.taobao.accs.common.a.a(new c(context, bArr, r0));
    }

    public static final void c(Context context) {
        ALog.i(TAG, "clearBlackList", new Object[0]);
        GlobalClientInfo.getInstance(context).setElectionBlackList(null);
        if (b == null) {
            ALog.e(TAG, "clearBlackList path null", new Object[0]);
            return;
        }
        try {
            File file = new File(b + "accs_blacklist");
            if (file.exists()) {
                file.delete();
            }
        } catch (Throwable th) {
            ALog.e(TAG, "clearBlackList", th, new Object[0]);
        }
    }

    public static final Map<String, Set<Integer>> d(Context context) {
        Map<String, Set<Integer>> electionBlackList = GlobalClientInfo.getInstance(context).getElectionBlackList();
        if (electionBlackList != null) {
            ALog.i(TAG, "getBlackList from mem", electionBlackList.toString());
            return electionBlackList;
        } else if (b == null) {
            ALog.e(TAG, "getBlackList path null", new Object[0]);
            return null;
        } else {
            try {
                byte[] a = c.a(new File(b + "accs_blacklist"));
                if (a != null) {
                    electionBlackList = a(a);
                }
            } catch (Throwable th) {
                ALog.e(TAG, "getBlackList", th, new Object[0]);
            }
            GlobalClientInfo.getInstance(context).setElectionBlackList(electionBlackList);
            return electionBlackList;
        }
    }

    private static Map<String, Set<Integer>> a(byte[] bArr) {
        Throwable th;
        Map<String, Set<Integer>> map = null;
        if (bArr != null) {
            try {
                JSONArray jSONArray = new JSONObject(new String(bArr)).getJSONArray(b.ELECTION_KEY_BLACKLIST);
                if (jSONArray != null && jSONArray.length() > 0) {
                    Map map2;
                    Map hashMap = new HashMap();
                    int i = 0;
                    while (i < jSONArray.length()) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            JSONArray jSONArray2 = jSONObject.getJSONArray(b.ELECTION_KEY_SDKVS);
                            String string = jSONObject.getString(Constants.KEY_ELECTION_PKG);
                            if (jSONArray2 != null && jSONArray2.length() > 0) {
                                Set hashSet = new HashSet();
                                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                    hashSet.add(Integer.valueOf(jSONArray2.getInt(i)));
                                }
                                hashMap.put(string, hashSet);
                            }
                            i++;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            map2 = hashMap;
                            th = th3;
                        }
                    }
                    map2 = hashMap;
                }
                String str = TAG;
                String str2 = "praseBlackList";
                Object[] objArr = new Object[2];
                objArr[0] = b.ELECTION_KEY_BLACKLIST;
                objArr[1] = map == null ? "null" : map.toString();
                ALog.i(str, str2, objArr);
            } catch (Throwable th4) {
                th = th4;
                ALog.e(TAG, "praseBlackList", th, new Object[0]);
                return map;
            }
        }
        return map;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean a(android.content.Context r8, java.lang.String r9, int r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.a.a.a(android.content.Context, java.lang.String, int):boolean");
        /*
        r1 = 1;
        r2 = 0;
        r0 = android.text.TextUtils.isEmpty(r9);
        if (r0 == 0) goto L_0x0009;
    L_0x0008:
        return r2;
    L_0x0009:
        if (r10 == r1) goto L_0x004b;
    L_0x000b:
        r0 = "ElectionServiceUtil";
        r1 = "checkApp election version not match";
        r3 = 6;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x003c }
        r4 = 0;
        r5 = "package";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 1;
        r3[r4] = r9;	 Catch:{ Throwable -> 0x003c }
        r4 = 2;
        r5 = "require";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 3;
        r5 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x003c }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 4;
        r5 = "self ver";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 5;
        r5 = 1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x003c }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        com.taobao.accs.utl.ALog.w(r0, r1, r3);	 Catch:{ Throwable -> 0x003c }
        goto L_0x0008;
    L_0x003c:
        r0 = move-exception;
        r1 = "ElectionServiceUtil";
        r3 = "checkApp error";
        r4 = new java.lang.Object[r2];
        com.taobao.accs.utl.ALog.e(r1, r3, r0, r4);
        r0 = r2;
    L_0x0049:
        r2 = r0;
        goto L_0x0008;
    L_0x004b:
        r0 = com.taobao.accs.client.b.a(r8);	 Catch:{ Throwable -> 0x003c }
        r0 = r0.c(r9);	 Catch:{ Throwable -> 0x003c }
        if (r0 != 0) goto L_0x006b;
    L_0x0055:
        r0 = "ElectionServiceUtil";
        r1 = "checkApp is unbinded";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x003c }
        r4 = 0;
        r5 = "package";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 1;
        r3[r4] = r9;	 Catch:{ Throwable -> 0x003c }
        com.taobao.accs.utl.ALog.w(r0, r1, r3);	 Catch:{ Throwable -> 0x003c }
        goto L_0x0008;
    L_0x006b:
        r0 = r8.getPackageManager();	 Catch:{ Throwable -> 0x003c }
        r3 = 0;
        r0 = r0.getApplicationInfo(r9, r3);	 Catch:{ Throwable -> 0x003c }
        if (r0 != 0) goto L_0x008d;
    L_0x0076:
        r0 = "ElectionServiceUtil";
        r1 = "checkApp applicaton info null";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x003c }
        r4 = 0;
        r5 = "package";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 1;
        r3[r4] = r9;	 Catch:{ Throwable -> 0x003c }
        com.taobao.accs.utl.ALog.w(r0, r1, r3);	 Catch:{ Throwable -> 0x003c }
        goto L_0x0008;
    L_0x008d:
        r0 = r0.enabled;	 Catch:{ Throwable -> 0x003c }
        if (r0 != 0) goto L_0x00a8;
    L_0x0091:
        r0 = "ElectionServiceUtil";
        r1 = "checkApp is disabled";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x003c }
        r4 = 0;
        r5 = "package";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 1;
        r3[r4] = r9;	 Catch:{ Throwable -> 0x003c }
        com.taobao.accs.utl.ALog.i(r0, r1, r3);	 Catch:{ Throwable -> 0x003c }
        goto L_0x0008;
    L_0x00a8:
        r3 = d(r8);	 Catch:{ Throwable -> 0x003c }
        if (r3 == 0) goto L_0x0154;
    L_0x00ae:
        r0 = "ElectionServiceUtil";
        r4 = "checkApp";
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x003c }
        r6 = 0;
        r7 = "blackList";
        r5[r6] = r7;	 Catch:{ Throwable -> 0x003c }
        r6 = 1;
        r7 = r3.toString();	 Catch:{ Throwable -> 0x003c }
        r5[r6] = r7;	 Catch:{ Throwable -> 0x003c }
        com.taobao.accs.utl.ALog.i(r0, r4, r5);	 Catch:{ Throwable -> 0x003c }
        r0 = r3.get(r9);	 Catch:{ Throwable -> 0x003c }
        r0 = (java.util.Set) r0;	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x010c;
    L_0x00cf:
        r4 = 212; // 0xd4 float:2.97E-43 double:1.047E-321;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x003c }
        r4 = r0.contains(r4);	 Catch:{ Throwable -> 0x003c }
        if (r4 != 0) goto L_0x00e6;
    L_0x00db:
        r4 = -1;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x003c }
        r0 = r0.contains(r4);	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x010c;
    L_0x00e6:
        r0 = "ElectionServiceUtil";
        r1 = "checkApp in blacklist";
        r3 = 4;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x003c }
        r4 = 0;
        r5 = "package";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 1;
        r3[r4] = r9;	 Catch:{ Throwable -> 0x003c }
        r4 = 2;
        r5 = "sdkv";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 3;
        r5 = 212; // 0xd4 float:2.97E-43 double:1.047E-321;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x003c }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        com.taobao.accs.utl.ALog.w(r0, r1, r3);	 Catch:{ Throwable -> 0x003c }
        goto L_0x0008;
    L_0x010c:
        r0 = "*";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x003c }
        r0 = (java.util.Set) r0;	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x0154;
    L_0x0117:
        r3 = 212; // 0xd4 float:2.97E-43 double:1.047E-321;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x003c }
        r3 = r0.contains(r3);	 Catch:{ Throwable -> 0x003c }
        if (r3 != 0) goto L_0x012e;
    L_0x0123:
        r3 = -1;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x003c }
        r0 = r0.contains(r3);	 Catch:{ Throwable -> 0x003c }
        if (r0 == 0) goto L_0x0154;
    L_0x012e:
        r0 = "ElectionServiceUtil";
        r1 = "checkApp in blacklist *";
        r3 = 4;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x003c }
        r4 = 0;
        r5 = "package";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 1;
        r3[r4] = r9;	 Catch:{ Throwable -> 0x003c }
        r4 = 2;
        r5 = "sdkv";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        r4 = 3;
        r5 = 212; // 0xd4 float:2.97E-43 double:1.047E-321;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x003c }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x003c }
        com.taobao.accs.utl.ALog.w(r0, r1, r3);	 Catch:{ Throwable -> 0x003c }
        goto L_0x0008;
    L_0x0154:
        r0 = r1;
        goto L_0x0049;
        */
    }

    public static String a(Context context, Map<String, Integer> map) {
        if (map == null || map.size() <= 0) {
            ALog.e(TAG, "localElection failed, packMap null", new Object[0]);
            return null;
        }
        List arrayList = new ArrayList();
        long j = -1;
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            long intValue = (long) ((Integer) entry.getValue()).intValue();
            if (intValue > j) {
                arrayList.clear();
                j = intValue;
            }
            if (intValue == j) {
                arrayList.add(str);
            }
        }
        String str2 = (String) arrayList.get(e.nextInt(10000) % arrayList.size());
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        ALog.i(TAG, "localElection localResult null, user curr app", Constants.KEY_ELECTION_PKG, context.getPackageName());
        return context.getPackageName();
    }

    public static final String e(Context context) {
        try {
            ResolveInfo resolveService = context.getPackageManager().resolveService(new Intent(b()), 0);
            if (resolveService == null) {
                ALog.e(TAG, "getResolveService resolveInfo null", new Object[0]);
                return null;
            }
            ServiceInfo serviceInfo = resolveService.serviceInfo;
            if (serviceInfo == null || !serviceInfo.isEnabled()) {
                ALog.e(TAG, "getResolveService serviceinfo null or disabled", new Object[0]);
                return null;
            }
            String str = serviceInfo.packageName;
            if (TextUtils.isEmpty(str)) {
                ALog.e(TAG, "getResolveService clientPack null", new Object[0]);
                return null;
            }
            try {
                ALog.i(TAG, "getResolveService", "package", str);
                return str;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str = null;
            th2 = th4;
            ALog.e(TAG, "getResolveService error", th2, new Object[0]);
            return str;
        }
    }

    public static String b() {
        return AccsConfig.mGroup == ACCS_GROUP.TAOBAO ? ACTION_ACCS_ELECTION : ACTION_ACCS_CUSTOM_ELECTION;
    }

    public static String c() {
        return AccsConfig.mGroup == ACCS_GROUP.TAOBAO ? "/accs/accs_main/1" : new StringBuilder("/accs/").append(AccsConfig.mGroup).append("/1").toString();
    }
}
