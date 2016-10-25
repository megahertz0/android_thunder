package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import android.text.TextUtils;
import com.android.volley.f;
import com.xunlei.c.b;
import com.xunlei.c.b.a;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.thundersniffer.context.ClientInfo;
import com.xunlei.thundersniffer.context.ThunderSnifferContext;
import com.xunlei.thundersniffer.context.volley.Request.JsonObjectProtocolRequest;
import com.xunlei.thundersniffer.context.volley.RequestManager;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.misc.ResourceOperationMonitor;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSnifferUtil;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SVodBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SHubBatchQuery extends BatchQueryOperation<SniffingResource> {
    final ArrayList<SniffingResource> a;
    final HashMap<String, JSONObject> b;
    public ResourceOperationMonitor mResourceOperationMonitor;

    public SHubBatchQuery(SvrOperationManager svrOperationManager, List<SniffingResource> list, ResourceOperationMonitor resourceOperationMonitor) {
        super(svrOperationManager, list);
        this.a = new ArrayList();
        this.b = new HashMap();
        this.mResourceOperationMonitor = resourceOperationMonitor;
    }

    public void start() {
        RequestManager.executorService().execute(new b(this));
    }

    public void onFinish() {
        if (this.mResourceOperationMonitor != null) {
            this.mResourceOperationMonitor.setFileInfoUpdating(false);
            this.mResourceOperationMonitor.notifyFileInfoUpdated();
        }
        if (this.b != null && !this.b.isEmpty()) {
            if (!SvrCacheDatabase.a().b()) {
                SvrCacheDatabase.a().a(ThunderSnifferContext.getApplicationContext());
            }
            SvrCacheDatabase.a().a(new ArrayList(this.b.values()), new c(this));
            this.b.clear();
        }
    }

    public boolean onHandleQueryBatch(ArrayList<SniffingResource> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        this.a.addAll(arrayList);
        if (!SvrCacheDatabase.a().b()) {
            SvrCacheDatabase.a().a(ThunderSnifferContext.getApplicationContext());
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            SniffingResource sniffingResource = (SniffingResource) it.next();
            arrayList2.add(sniffingResource.downloadUrl);
            arrayList3.add(sniffingResource);
        }
        if (arrayList2.isEmpty()) {
            checkNext();
        } else {
            a(arrayList2, arrayList3);
        }
        return true;
    }

    public void preprocessTodoBatch(List<SniffingResource> list) {
        if (list != null && !list.isEmpty()) {
            HashSet hashSet = new HashSet();
            if (!SvrCacheDatabase.a().b()) {
                SvrCacheDatabase.a().a(ThunderSnifferContext.getApplicationContext());
            }
            SvrCacheDatabase.a().a((List) list, new d(this, hashSet));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SniffingResource sniffingResource = (SniffingResource) it.next();
                if (sniffingResource.fileSize > 0 || hashSet.contains(sniffingResource.downloadUrl)) {
                    this.a.add(sniffingResource);
                    it.remove();
                }
            }
        }
    }

    private void a(SniffingResource sniffingResource, JSONObject jSONObject) {
        if (TextUtils.isEmpty(sniffingResource.decodeUrl)) {
            sniffingResource.decodeUrl = jSONObject.optString(SHubBatchQueryKeys.decode_url, BuildConfig.VERSION_NAME);
        }
        sniffingResource.fileSize = jSONObject.optLong(SHubBatchQueryKeys.filesize, 0);
        sniffingResource.fileCid = jSONObject.optString(SHubBatchQueryKeys.cid, BuildConfig.VERSION_NAME);
        sniffingResource.fileGcid = jSONObject.optString(SHubBatchQueryKeys.gcid, BuildConfig.VERSION_NAME);
        sniffingResource.format = jSONObject.optString(SHubBatchQueryKeys.format, BuildConfig.VERSION_NAME);
        String optString = jSONObject.optString(SHubBatchQueryKeys.filename, BuildConfig.VERSION_NAME);
        if (!TextUtils.isEmpty(optString) && !optString.equalsIgnoreCase("unknown")) {
            if (optString.contains("%")) {
                optString = b.g(optString);
            }
            Object trimResourceNameAD = ThunderSnifferUtil.trimResourceNameAD(optString);
            if (!TextUtils.isEmpty(trimResourceNameAD)) {
                sniffingResource.resourceName = trimResourceNameAD;
            }
        }
    }

    private String a() {
        return new StringBuilder("http://interface.m.sjzhushou.com/shub/query?rd=").append(System.currentTimeMillis()).toString();
    }

    private void a(ArrayList<String> arrayList, ArrayList<SniffingResource> arrayList2) {
        if (!isCancelled()) {
            if (arrayList == null || arrayList.isEmpty()) {
                checkNext();
                return;
            }
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            int size = arrayList.size();
            HashMap hashMap = new HashMap();
            for (int i = 0; i < size; i++) {
                String str = (String) arrayList.get(i);
                SniffingResource sniffingResource = (SniffingResource) arrayList2.get(i);
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.putOpt(SHubBatchQueryKeys.url, str);
                    try {
                        str = a.a(str.getBytes(CharsetConvert.UTF_8));
                        hashMap.put(str, sniffingResource);
                        jSONObject2.putOpt(SHubBatchQueryKeys.hash, str);
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                jSONArray.put(jSONObject2);
            }
            try {
                jSONObject.put(SVodBatchQueryKeys.urllist, jSONArray);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            new StringBuilder("shub request: ").append(jSONObject.toString());
            JsonObjectProtocolRequest jsonObjectProtocolRequest = new JsonObjectProtocolRequest(1, a(), jSONObject, new e(this, hashMap), new f(this));
            jsonObjectProtocolRequest.setRetryPolicy(new f(3000, 1, 1.0f));
            jsonObjectProtocolRequest.setShouldCache(false);
            ClientInfo clientInfo = ThunderSnifferContext.getClientInfo();
            if (!(clientInfo == null || jsonObjectProtocolRequest.getCookie() == null)) {
                jsonObjectProtocolRequest.getCookie().put("version", clientInfo.version != null ? clientInfo.version : BuildConfig.VERSION_NAME);
                jsonObjectProtocolRequest.getCookie().put("versionCode", Integer.toString(clientInfo.versionCode));
                jsonObjectProtocolRequest.getCookie().put("productId", clientInfo.productId != null ? clientInfo.productId : BuildConfig.VERSION_NAME);
                jsonObjectProtocolRequest.getCookie().put("channelId", clientInfo.channelId != null ? clientInfo.channelId : BuildConfig.VERSION_NAME);
            }
            RequestManager.requestQueue().a(jsonObjectProtocolRequest);
        }
    }
}
