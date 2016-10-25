package com.baidu.mobad.video;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import com.baidu.mobads.interfaces.IXAdContext;
import com.baidu.mobads.interfaces.IXAdManager;
import com.baidu.mobads.j.m;

public class XAdManager implements IXAdManager {
    private static IXAdManager d;
    private String a;
    private Location b;
    private Context c;

    public static IXAdManager getInstance(Context context) {
        if (d == null) {
            d = new XAdManager(context);
        }
        return d;
    }

    private XAdManager(Context context) {
        if (context instanceof Activity) {
            context = context.getApplicationContext();
        }
        this.c = context;
    }

    public void setAppSid(String str) {
        this.a = str;
        m.a().m().setAppId(str);
    }

    public void setLocation(Location location) {
        this.b = location;
    }

    public IXAdContext newAdContext() {
        return new XAdContext(this.c, this.a, this.b);
    }

    public String getVersion() {
        return "8.27";
    }
}
