package com.umeng.socialize;

import com.umeng.socialize.bean.SHARE_MEDIA;

public interface UMShareListener {
    void onCancel(SHARE_MEDIA share_media);

    void onError(SHARE_MEDIA share_media, Throwable th);

    void onResult(SHARE_MEDIA share_media);
}
