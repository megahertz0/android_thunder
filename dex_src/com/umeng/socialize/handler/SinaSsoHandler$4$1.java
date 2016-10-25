package com.umeng.socialize.handler;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.SinaSsoHandler.AnonymousClass_4;
import com.umeng.socialize.net.RestAPI;
import com.umeng.socialize.net.ShareFriendsRequest;
import com.umeng.socialize.net.ShareFriendsResponse;
import com.umeng.socialize.utils.Log;
import com.xunlei.download.proguard.c;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

class SinaSsoHandler$4$1 implements Runnable {
    final /* synthetic */ AnonymousClass_4 this$1;
    final /* synthetic */ Map val$data;

    SinaSsoHandler$4$1(AnonymousClass_4 anonymousClass_4, Map map) {
        this.this$1 = anonymousClass_4;
        this.val$data = map;
    }

    public void run() {
        ShareFriendsResponse queryFriendsList = RestAPI.queryFriendsList(new ShareFriendsRequest(this.this$1.val$act, SHARE_MEDIA.SINA, (String) this.val$data.get(c.f)));
        if (queryFriendsList == null) {
            Log.e("follow", "resp = null");
        } else if (queryFriendsList.isOk()) {
            Map hashMap = new HashMap();
            hashMap.put("friend", queryFriendsList.mFriends);
            hashMap.put("json", queryFriendsList.getJsonData());
            this.this$1.val$listener.onComplete(SHARE_MEDIA.SINA, SimpleLog.LOG_LEVEL_DEBUG, hashMap);
        } else {
            Log.e("follow", new StringBuilder("follow fail e =").append(queryFriendsList.mMsg).toString());
        }
    }
}
