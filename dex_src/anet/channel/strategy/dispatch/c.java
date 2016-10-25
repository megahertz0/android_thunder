package anet.channel.strategy.dispatch;

import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.SessionCenter;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.entity.e;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.strategy.IConnStrategy;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.qq.e.comm.constants.Constants.KEYS;
import com.taobao.accs.common.Constants;
import com.taobao.accs.internal.b;
import com.tencent.open.SocialConstants;
import com.xunlei.download.DownloadManager;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: Taobao
public class c {
    private static LinkedBlockingQueue<a> a;

    // compiled from: Taobao
    private static class a {
        String a;
        String b;
        int c;
        String d;
        boolean e;
        String f;
        String g;
        long h;
        String i;

        public JSONObject a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(b.ELECTION_KEY_HOST, this.a);
                jSONObject.put("ip", this.b);
                jSONObject.put("port", this.c);
                jSONObject.put("protocol", this.d);
                jSONObject.put(KEYS.RET, this.e);
                jSONObject.put("netIp", this.g);
                jSONObject.put(DownloadManager.COLUMN_REASON, this.i);
                jSONObject.put(KEYS.RET, this.e);
                jSONObject.put("rt", this.h);
                int indexOf = this.f.indexOf("$");
                if (indexOf != -1) {
                    jSONObject.put(a.NET_TYPE, this.f.substring(0, indexOf));
                    return jSONObject;
                }
                jSONObject.put(a.NET_TYPE, this.f);
                return jSONObject;
            } catch (Exception e) {
                return null;
            }
        }

        a(String str, String str2, String str3, IConnStrategy iConnStrategy, EventType eventType, d dVar) {
            this.f = str;
            this.g = str2;
            this.a = str3;
            this.b = iConnStrategy.getIp();
            this.c = iConnStrategy.getPort();
            this.d = iConnStrategy.getConnType().toProtocol();
            if (eventType == EventType.CONNECTED) {
                this.e = true;
                if (dVar != null && (dVar instanceof anet.channel.entity.b)) {
                    this.h = ((anet.channel.entity.b) dVar).a;
                    return;
                }
                return;
            }
            if (eventType == EventType.CONNECT_FAIL) {
                this.e = false;
                if (dVar == null) {
                    return;
                }
            } else if (eventType == EventType.HORSE_RIDE && dVar != null && (dVar instanceof e)) {
                this.e = ((e) dVar).a;
                if (this.e) {
                    this.h = ((e) dVar).b;
                    return;
                }
            } else {
                return;
            }
            this.i = String.format("errorCode:%s errorDetail:%s", new Object[]{Integer.valueOf(dVar.d), dVar.e});
        }
    }

    static {
        a = new LinkedBlockingQueue();
    }

    public static Map<String, Object> a(Map<String, Object> map) {
        map.put(IXAdRequestInfo.V, a.VER_CODE);
        if (!TextUtils.isEmpty(GlobalAppRuntimeInfo.getAppKey())) {
            map.put(Constants.SP_KEY_APPKEY, GlobalAppRuntimeInfo.getAppKey());
        }
        map.put(com.tencent.connect.common.Constants.PARAM_PLATFORM, a.ANDROID);
        map.put(a.PLATFORM_VERSION, VERSION.RELEASE);
        if (!TextUtils.isEmpty(GlobalAppRuntimeInfo.getUserId())) {
            map.put(Constants.KEY_SID, GlobalAppRuntimeInfo.getUserId());
        }
        if (!TextUtils.isEmpty(GlobalAppRuntimeInfo.getUtdid())) {
            map.put(org.android.agoo.common.b.KEY_DEVICE_TOKEN, GlobalAppRuntimeInfo.getUtdid());
        }
        NetworkStatus a = NetworkStatusHelper.a();
        map.put(a.NET_TYPE, a.toString());
        if (a.isWifi()) {
            map.put(a.BSSID, NetworkStatusHelper.d());
        }
        map.put(a.SIGNTYPE, SessionCenter.SECURITYGUARD_OFF ? "noSec" : com.taobao.accs.antibrush.b.KEY_SEC);
        c(map);
        b(map);
        return map;
    }

    private static void b(Map<String, Object> map) {
        try {
            String ttid = GlobalAppRuntimeInfo.getTtid();
            if (!TextUtils.isEmpty(ttid)) {
                int indexOf = ttid.indexOf("@");
                if (indexOf != -1) {
                    map.put(LogBuilder.KEY_CHANNEL, ttid.substring(0, indexOf));
                }
                ttid = ttid.substring(indexOf + 1);
                indexOf = ttid.lastIndexOf("_");
                if (indexOf != -1) {
                    map.put(SocialConstants.PARAM_APPNAME, ttid.substring(0, indexOf));
                    map.put(Constants.KEY_APP_VERSION, ttid.substring(indexOf + 1));
                    return;
                }
                map.put(SocialConstants.PARAM_APPNAME, ttid);
            }
        } catch (Exception e) {
        }
    }

    private static void c(Map<String, Object> map) {
        try {
            JSONArray jSONArray = new JSONArray();
            while (true) {
                a aVar = (a) a.poll();
                if (aVar == null) {
                    break;
                }
                JSONObject a = aVar.a();
                if (a == null) {
                    break;
                }
                jSONArray.put(a);
            }
            map.put(a.CONN_MSG, jSONArray.toString());
        } catch (Exception e) {
        }
    }

    public static void a(String str, String str2, String str3, IConnStrategy iConnStrategy, EventType eventType, d dVar) {
        if (eventType == EventType.CONNECTED || eventType == EventType.CONNECT_FAIL || eventType == EventType.HORSE_RIDE) {
            a.offer(new a(str, str2, str3, iConnStrategy, eventType, dVar));
        }
    }
}
