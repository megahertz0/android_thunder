package com.xunlei.tdlive.sdk;

import android.content.Context;
import com.umeng.socialize.bean.SHARE_MEDIA;

public interface IClient {
    void fireLoginEvent(Context context, int i, String str, String str2, String str3, String str4);

    void fireLogoutEvent(Context context);

    void firePayEvent(Context context, int i, int i2, String str, String str2, int i3);

    void fireSetUserAvatarEvent(Context context, int i);

    void fireSetUserInfoEvent(Context context, int i, String str, String str2);

    void fireShareCompleteEvent(Context context, int i, SHARE_MEDIA share_media);
}
