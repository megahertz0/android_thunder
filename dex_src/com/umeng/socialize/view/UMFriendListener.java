package com.umeng.socialize.view;

import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

public interface UMFriendListener {
    public static final UMFriendListener dummy;

    void onCancel(SHARE_MEDIA share_media, int i);

    void onComplete(SHARE_MEDIA share_media, int i, Map<String, Object> map);

    void onError(SHARE_MEDIA share_media, int i, Throwable th);

    static {
        dummy = new h();
    }
}
