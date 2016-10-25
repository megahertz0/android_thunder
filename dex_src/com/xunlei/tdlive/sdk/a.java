package com.xunlei.tdlive.sdk;

import android.content.Context;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.tdlive.user.e;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.y;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: LiveClient.java
public class a implements IClient {
    public void firePayEvent(Context context, int i, int i2, String str, String str2, int i3) {
        e.a().a(i, i2, str, str2, i3);
    }

    public void fireLogoutEvent(Context context) {
        f.a(context).a(false, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME);
    }

    public void fireLoginEvent(Context context, int i, String str, String str2, String str3, String str4) {
        f.a(context).a(true, str, str2, str3, str4);
    }

    public void fireSetUserInfoEvent(Context context, int i, String str, String str2) {
        f.a(context).a(false, i, str, str2);
    }

    public void fireSetUserAvatarEvent(Context context, int i) {
        f.a(context).a(true, i, null, null);
    }

    public void fireShareCompleteEvent(Context context, int i, SHARE_MEDIA share_media) {
        y.a(i, share_media);
    }
}
