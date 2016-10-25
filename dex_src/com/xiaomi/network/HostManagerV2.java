package com.xiaomi.network;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.a;
import com.xiaomi.channel.commonutils.network.c;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.network.HostManager.HttpGet;
import com.xunlei.common.encrypt.CharsetConvert;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HostManagerV2 extends HostManager {
    private final int a;
    private final int b;
    private int c;

    public HostManagerV2(Context context, HostFilter hostFilter, HttpGet httpGet, String str) {
        this(context, hostFilter, httpGet, str, null, null);
    }

    protected HostManagerV2(Context context, HostFilter hostFilter, HttpGet httpGet, String str, String str2, String str3) {
        super(context, hostFilter, httpGet, str, str2, str3);
        this.a = 80;
        this.b = 5222;
        this.c = 80;
        addReservedHost("resolver.msg.xiaomi.net", "resolver.msg.xiaomi.net:5222");
    }

    static String a(String str) {
        try {
            int length = str.length();
            byte[] bytes = str.getBytes(CharsetConvert.UTF_8);
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                if ((b & 240) != 240) {
                    bytes[i] = (byte) (((b & 15) ^ ((byte) (((b >> 4) + length) & 15))) | (b & 240));
                }
            }
            return new String(bytes);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    protected JSONObject a() {
        JSONObject jSONObject;
        synchronized (this.mHostsMapping) {
            jSONObject = new JSONObject();
            jSONObject.put("ver", SimpleLog.LOG_LEVEL_DEBUG);
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_DATA, toJSON());
        }
        return jSONObject;
    }

    protected void b(String str) {
        synchronized (this.mHostsMapping) {
            this.mHostsMapping.clear();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("ver") != 2) {
                throw new JSONException("Bad version");
            }
            JSONArray optJSONArray = jSONObject.optJSONArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
            for (int i = 0; i < optJSONArray.length(); i++) {
                Fallbacks fromJSON = new Fallbacks().fromJSON(optJSONArray.getJSONObject(i));
                this.mHostsMapping.put(fromJSON.getHost(), fromJSON);
            }
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
                    b(loadHosts);
                    b.b("loading the new hosts succeed");
                    return true;
                }
            } catch (Throwable th) {
                b.a(new StringBuilder("load bucket failure: ").append(th.getMessage()).toString());
            }
            return false;
        }
    }

    protected String getHost() {
        return "resolver.msg.xiaomi.net";
    }

    public String getRemoteFallbackJSON(ArrayList<String> arrayList, String str, String str2) {
        Iterator it;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList();
        List<c> arrayList4 = new ArrayList();
        arrayList4.add(new a(AgooConstants.MESSAGE_TYPE, str));
        if (str.equals("wap")) {
            arrayList4.add(new a("conpt", a(d.f(this.sAppContext))));
        }
        arrayList4.add(new a("uuid", str2));
        arrayList4.add(new a("list", join(arrayList, ",")));
        Fallback localFallback = getLocalFallback("resolver.msg.xiaomi.net");
        String format = String.format(Locale.US, "http://%1$s/gslb/?ver=3.0", new Object[]{new StringBuilder("resolver.msg.xiaomi.net:").append(this.c).toString()});
        if (localFallback == null) {
            arrayList3.add(format);
            synchronized (mReservedHosts) {
                it = ((ArrayList) mReservedHosts.get("resolver.msg.xiaomi.net")).iterator();
                while (it.hasNext()) {
                    String str3 = (String) it.next();
                    arrayList3.add(String.format(Locale.US, "http://%1$s/gslb/?ver=3.0", new Object[]{str3}));
                }
            }
            arrayList2 = arrayList3;
        } else {
            arrayList2 = localFallback.a(format);
        }
        Iterator it2 = arrayList2.iterator();
        IOException iOException = null;
        while (it2.hasNext()) {
            try {
                Builder buildUpon = Uri.parse((String) it2.next()).buildUpon();
                for (c cVar : arrayList4) {
                    buildUpon.appendQueryParameter(cVar.a(), cVar.b());
                }
                return this.sHttpGetter == null ? d.a(this.sAppContext, new URL(buildUpon.toString())) : this.sHttpGetter.a(buildUpon.toString());
            } catch (IOException e) {
                iOException = e;
            }
        }
        return iOException != null ? super.getRemoteFallbackJSON(arrayList, str, str2) : null;
    }

    public void persist() {
        synchronized (this.mHostsMapping) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.sAppContext.openFileOutput(getProcessName(), 0)));
                Object toString = a().toString();
                if (!TextUtils.isEmpty(toString)) {
                    bufferedWriter.write(toString);
                }
                bufferedWriter.close();
            } catch (Exception e) {
                b.a(new StringBuilder("persist bucket failure: ").append(e.getMessage()).toString());
            }
        }
    }

    public void purge() {
        synchronized (this.mHostsMapping) {
            for (Fallbacks fallbacks : this.mHostsMapping.values()) {
                fallbacks.purge(true);
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
}
