package com.umeng.socialize.utils;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

public interface ShareBoardlistener {
    void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media);
}
