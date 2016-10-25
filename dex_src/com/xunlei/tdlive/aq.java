package com.xunlei.tdlive;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.tdlive.protocol.XLLiveCreatePublishRoomRequest;
import com.xunlei.tdlive.util.w;

// compiled from: LivePlayerActivity.java
class aq implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ap c;

    aq(ap apVar, String str, String str2) {
        this.c = apVar;
        this.a = str;
        this.b = str2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
        if (i == 1) {
            w wVar = new w("stream_get");
            wVar.c();
            LivePlayerActivity.e(this.c.c).a("\u6b63\u5728\u521b\u5efa\u76f4\u64ad...", false);
            new XLLiveCreatePublishRoomRequest(this.c.a, this.c.b, this.a, 0).send(new ar(this, wVar));
        }
    }
}
