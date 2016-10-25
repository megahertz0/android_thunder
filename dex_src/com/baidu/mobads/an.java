package com.baidu.mobads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdContainerContext;
import com.baidu.mobads.interfaces.IXAdContainerEventListener;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.IXAdResource;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.error.IXAdErrorCode;
import com.baidu.mobads.interfaces.utils.IBase64;
import com.baidu.mobads.interfaces.utils.IXAdActivityUtils;
import com.baidu.mobads.interfaces.utils.IXAdBitmapUtils;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.interfaces.utils.IXAdIOUtils;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.interfaces.utils.IXAdViewUtils;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.d.b;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.openad.e.d;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloaderManager;
import com.baidu.mobads.openad.interfaces.utils.IOAdTimer;
import java.util.HashMap;

public class an extends c implements IXAdContainerContext {
    public IXAdContainerEventListener a;
    private Context b;
    private Activity d;
    private RelativeLayout e;
    private IXAdResponseInfo f;
    private IXAdInstanceInfo g;
    private View h;
    private IXAdProdInfo i;
    private long j;
    private HashMap<String, Integer> k;

    public class a extends b {
        private String b;
        private HashMap<String, Object> c;

        public a(String str, String str2, HashMap<String, Object> hashMap) {
            super(str);
            this.b = str2;
            this.c = hashMap;
        }

        public HashMap<String, Object> getData() {
            return this.c;
        }
    }

    public an(Context context, Activity activity, IXAdProdInfo iXAdProdInfo, RelativeLayout relativeLayout, IXAdContainerEventListener iXAdContainerEventListener, IXAdResponseInfo iXAdResponseInfo, View view) {
        this.j = 0;
        this.k = new HashMap();
        this.b = context;
        this.d = activity;
        this.i = iXAdProdInfo;
        this.e = relativeLayout;
        this.a = iXAdContainerEventListener;
        this.f = iXAdResponseInfo;
        this.g = iXAdResponseInfo.getPrimaryAdInstanceInfo();
        this.h = view;
    }

    public Context getApplicationContext() {
        return this.b;
    }

    public Activity getActivity() {
        if (this.d == null && this.e != null) {
            this.d = (Activity) this.e.getContext();
        }
        return this.d;
    }

    public IXAdContainerEventListener getAdContainerListener() {
        return this.a;
    }

    public RelativeLayout getAdProdBase() {
        return this.e;
    }

    public IXAdInstanceInfo getAdInstanceInfo() {
        return this.g;
    }

    public IXAdResponseInfo getAdResponseInfo() {
        return this.f;
    }

    public View getAdLeadingView() {
        return this.h;
    }

    public void processCommand(String str, HashMap<String, Object> hashMap) {
        if (System.currentTimeMillis() - this.j > 1000) {
            this.j = System.currentTimeMillis();
            dispatchEvent(new a("process_command", str, hashMap));
        }
    }

    public void registerAdService(String str, HashMap<String, Object> hashMap) {
        if (!this.k.containsKey(str)) {
            this.k.put(str, Integer.valueOf(1));
            dispatchEvent(new a("regsiter_adservice", str, hashMap));
        }
    }

    public void unregisterAdService(String str) {
        if (this.k.containsKey(str)) {
            this.k.remove(str);
            dispatchEvent(new a("unregsiter_adservice", str, new HashMap()));
        }
    }

    public void fireAdMetrics(String str, HashMap<String, String> hashMap) {
        String addParameters = m.a().i().addParameters(str, hashMap);
        com.baidu.mobads.openad.e.a aVar = new com.baidu.mobads.openad.e.a();
        d dVar = new d(addParameters, com.umeng.a.d);
        dVar.e = 1;
        aVar.a(dVar, Boolean.valueOf(true));
    }

    public IXAdConstants getAdConstants() {
        return m.a().p();
    }

    public IXAdURIUitls getAdUitls4URI() {
        return m.a().i();
    }

    public IXAdBitmapUtils getAdUtils4Bitmap() {
        return m.a().h();
    }

    public IXAdViewUtils getAdUtils4View() {
        return m.a().j();
    }

    public IXAdIOUtils getAdUtils4IO() {
        return m.a().k();
    }

    public IXAdPackageUtils getAdUtils4Package() {
        return m.a().l();
    }

    public IXAdActivityUtils getAdUtils4Activity() {
        return m.a().o();
    }

    public IXAdCommonUtils getAdUtils4Common() {
        return m.a().m();
    }

    public IXAdSystemUtils getAdUtils4System() {
        return m.a().n();
    }

    public IOAdDownloaderManager getDownloaderManager(Context context) {
        return m.a().b(context);
    }

    public IBase64 getBase64() {
        return m.a().e();
    }

    public IXAdLogger getAdLogger() {
        return m.a().f();
    }

    public IXAdResource getAdResource() {
        return m.a().g();
    }

    public IXAdErrorCode getErrorCode() {
        return m.a().q();
    }

    public IXAdProdInfo getAdProdInfo() {
        return this.i;
    }

    public String getProxyVersion() {
        return "8.27";
    }

    public IOAdTimer createOAdTimer(int i) {
        return new com.baidu.mobads.openad.f.a(i);
    }

    public IOAdTimer createOAdTimer(int i, int i2) {
        return new com.baidu.mobads.openad.f.a(i, i2);
    }
}
