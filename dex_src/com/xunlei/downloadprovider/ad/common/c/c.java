package com.xunlei.downloadprovider.ad.common.c;

import android.text.TextUtils;
import android.view.View;
import com.inmobi.ads.InMobiNative;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xllib.R;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: InmobiNavModel.java
public final class c extends a {
    private InMobiNative a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private float g;
    private String h;
    private String i;

    public c(String str, InMobiNative inMobiNative) {
        this.a = inMobiNative;
        this.i = str;
        try {
            JSONObject jSONObject = new JSONObject((String) inMobiNative.getAdContent());
        } catch (JSONException e) {
            jSONObject = null;
        }
        if (jSONObject != null) {
            this.b = jSONObject.optString(SetKey.TITLE);
            this.c = jSONObject.optString("description");
            if (TextUtils.isEmpty(this.c)) {
                this.c = this.b;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2);
            if (optJSONObject != null) {
                this.d = optJSONObject.optString(SHubBatchQueryKeys.url);
            }
            optJSONObject = jSONObject.optJSONObject("screenshots");
            if (optJSONObject != null) {
                this.e = optJSONObject.optString(SHubBatchQueryKeys.url);
            }
            this.h = jSONObject.optString("landingURL");
            this.g = (float) jSONObject.optDouble("rating", 4.5d);
            if (this.g <= 0.0f) {
                this.g = 4.5f;
            }
            String optString = jSONObject.optString("cta", "\u6253\u5f00");
            if (optString.equals("install") || optString.equals("\u4e0b\u8f7d")) {
                this.f = true;
            }
        }
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }

    public final String d() {
        return this.e;
    }

    public final boolean e() {
        return this.f;
    }

    public final float g() {
        return this.g;
    }

    public final String h() {
        return this.h;
    }

    public final AD_TYPE o() {
        return AD_TYPE.SOURCE_INMOBI_NATIVE_FLAG;
    }

    public final void onClick(View view) {
        super.onClick(view);
        if (this.a != null) {
            this.a.reportAdClick(null);
        }
        if (!this.f) {
            a(view.getContext());
        }
    }

    public final void a(View view) {
        super.a(view);
        if (this.a != null) {
            InMobiNative.bind(view, this.a);
        }
    }

    public final String r() {
        return this.i;
    }

    public final String s() {
        return b(R.styleable.AppCompatTheme_checkboxStyle);
    }
}
