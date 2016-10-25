package com.baidu.mobads.production.a;

import android.content.Context;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.j.m;
import com.baidu.mobads.production.t;
import com.baidu.mobads.vo.b;
import com.baidu.mobads.vo.c;
import com.baidu.mobads.vo.d;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends com.baidu.mobads.production.a implements IXNonLinearAdSlot {
    private c w;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return m();
    }

    public a(Context context, RelativeLayout relativeLayout, String str, boolean z) {
        JSONObject jSONObject;
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.p = SlotType.SLOT_TYPE_BANNER;
        m.a().p();
        this.w = new c(getApplicationContext(), getActivity(), this.p);
        this.w.c(AdSize.Banner.getValue());
        this.w.d(str);
        b bVar = (b) this.w.d();
        bVar.a(z);
        JSONObject attribute = bVar.getAttribute();
        if (attribute == null) {
            jSONObject = new JSONObject();
        } else {
            jSONObject = attribute;
        }
        try {
            jSONObject.put("ABILITY", "BANNER_CLOSE,");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bVar.a(jSONObject);
        c(str);
    }

    public void c() {
        load();
    }

    protected void d() {
        this.n = 10000;
    }

    public void request() {
        a(this.w);
        try {
            WebView webView = new WebView(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void b(d dVar) {
        this.l = dVar;
        g();
        a(null, null, 5000);
    }

    protected void a(com.baidu.mobads.openad.e.d dVar, t tVar, int i) {
        String toString = new StringBuilder("{'ad':[{'id':99999999,'url':'").append(this.w.b()).append("', type='").append(CreativeType.HTML.getValue()).append("'}],'n':1}").toString();
        this.b = Boolean.valueOf(true);
        try {
            setAdResponseInfo(new c(toString));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a("XAdMouldeLoader ad-server requesting success");
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        start();
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    public d m() {
        return this.w;
    }

    protected void a() {
        new Thread(new b(this)).start();
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.l();
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_USER_CLOSE));
    }
}
