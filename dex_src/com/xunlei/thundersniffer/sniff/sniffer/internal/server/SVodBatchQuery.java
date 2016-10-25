package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import com.android.volley.f;
import com.xunlei.thundersniffer.context.ClientInfo;
import com.xunlei.thundersniffer.context.ThunderSnifferContext;
import com.xunlei.thundersniffer.context.volley.Request.JsonObjectProtocolRequest;
import com.xunlei.thundersniffer.context.volley.RequestManager;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.misc.ResourceOperationMonitor;
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

public class SVodBatchQuery extends BatchQueryOperation<SniffingResource> {
    final ArrayList<SniffingResource> a;
    final HashMap<String, Integer> b;
    public ResourceOperationMonitor mResourceOperationMonitor;

    public SVodBatchQuery(SvrOperationManager svrOperationManager, List<SniffingResource> list, ResourceOperationMonitor resourceOperationMonitor) {
        super(svrOperationManager, list);
        this.a = new ArrayList();
        this.b = new HashMap();
        this.mResourceOperationMonitor = resourceOperationMonitor;
    }

    public void start() {
        RequestManager.executorService().execute(new g(this));
    }

    public void onFinish() {
        if (this.mResourceOperationMonitor != null) {
            this.mResourceOperationMonitor.setVodplayStatusUpdating(false);
            this.mResourceOperationMonitor.notifyVodplayStatusUpdated();
        }
        if (!(this.b == null || this.b.isEmpty())) {
            if (!SvrCacheDatabase.a().b()) {
                SvrCacheDatabase.a().a(ThunderSnifferContext.getApplicationContext());
            }
            SvrCacheDatabase.a().a(this.b);
            this.b.clear();
        }
        SvrCacheDatabase.a().c();
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
        SvrCacheDatabase.a().a(new HashSet(arrayList), new h(this));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            SniffingResource sniffingResource = (SniffingResource) it.next();
            if (sniffingResource.vodplay == -1) {
                this.b.put(sniffingResource.downloadUrl, Integer.valueOf(-1));
                arrayList2.add(sniffingResource.downloadUrl);
                arrayList3.add(sniffingResource);
            }
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
            if (!SvrCacheDatabase.a().b()) {
                SvrCacheDatabase.a().a(ThunderSnifferContext.getApplicationContext());
            }
            SvrCacheDatabase.a().a(new HashSet(list), new i(this));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SniffingResource sniffingResource = (SniffingResource) it.next();
                if (sniffingResource.vodplay != -1) {
                    this.a.add(sniffingResource);
                    it.remove();
                }
            }
        }
    }

    private String a() {
        return new StringBuilder("http://interface.m.sjzhushou.com/svod/batch_query_url?rd=").append(System.currentTimeMillis()).toString();
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
            for (int i = 0; i < size; i++) {
                jSONArray.put(arrayList.get(i));
            }
            try {
                jSONObject.put(SVodBatchQueryKeys.urllist, jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectProtocolRequest jsonObjectProtocolRequest = new JsonObjectProtocolRequest(1, a(), jSONObject, new j(this, arrayList2), new k(this));
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
