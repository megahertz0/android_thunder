package com.baidu.mobads;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.baidu.mobads.j.m;
import com.umeng.a;

public class AdService {
    protected static String channelId;
    protected static int instanceCount;
    private AdView a;

    static {
        channelId = a.d;
        instanceCount = -1;
    }

    public static void setChannelId(String str) {
        channelId = str;
        m.a().m().setChannelId(str);
    }

    public AdService(Context context, ViewGroup viewGroup, LayoutParams layoutParams, AdViewListener adViewListener) {
        this(context, viewGroup, layoutParams, adViewListener, AdSize.Banner, a.d);
    }

    public AdService(Context context, ViewGroup viewGroup, LayoutParams layoutParams, AdViewListener adViewListener, AdSize adSize, String str) {
        if (context == null || viewGroup == null || layoutParams == null || adViewListener == null || adSize == null) {
            throw new IllegalArgumentException("One of arguments is null");
        }
        this.a = new AdView(context, false, adSize, str);
        this.a.setListener(adViewListener);
        a(viewGroup, layoutParams);
        instanceCount++;
    }

    private void a(ViewGroup viewGroup, LayoutParams layoutParams) {
        try {
            if (this.a.getParent() != viewGroup) {
                if (this.a.getParent() != null) {
                    ((ViewGroup) this.a.getParent()).removeView(this.a);
                }
                viewGroup.addView(this.a, layoutParams);
            }
        } catch (Throwable e) {
            m.a().f().d(e);
        }
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
            this.a = null;
        }
    }
}
