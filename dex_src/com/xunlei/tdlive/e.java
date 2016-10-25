package com.xunlei.tdlive;

import android.content.Context;
import android.content.Intent;
import com.sina.weibo.sdk.constant.WBConstants;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.play.view.LiveReplayActivity;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import org.android.agoo.message.MessageService;

// compiled from: DispatcherActivity.java
final class e implements JsonCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ Context c;

    e(int i, String str, Context context) {
        this.a = i;
        this.b = str;
        this.c = context;
    }

    public final void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0 && jsonWrapper != null) {
            JsonWrapper object = jsonWrapper.getObject(SocializeConstants.JSON_DATA, "{}").getObject("roominfo", "{}");
            JsonWrapper object2 = object.getObject("userinfo", "{}");
            Intent putExtra = new Intent().addFlags(this.a).putExtra("roomid", object.getString("roomid", a.d)).putExtra(WBConstants.GAME_PARAMS_GAME_IMAGE_URL, object.getString(WBConstants.GAME_PARAMS_GAME_IMAGE_URL, a.d)).putExtra("onlinenum", object.getString("onlinenum", MessageService.MSG_DB_READY_REPORT)).putExtra("userid", object2.getString("userid", object.getString("userid", a.d))).putExtra("avatar", object2.getString("avatar", a.d)).putExtra("nickname", object2.getString("nickname", a.d)).putExtra("from", this.b);
            if (object.getInt(Impl.COLUMN_STATUS, 0) == 2) {
                LiveReplayActivity.a(this.c, putExtra.putExtra("stream_pull", object.getString("play_hls_url", a.d)));
            } else {
                LivePlayerActivity.a(this.c, putExtra.putExtra("stream_pull", object.getString("stream_pull", a.d)));
            }
        }
    }
}
