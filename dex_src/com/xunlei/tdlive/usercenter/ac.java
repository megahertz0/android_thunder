package com.xunlei.tdlive.usercenter;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.protocol.XLLiveDeleteLiveRecordRequest;
import com.xunlei.tdlive.protocol.XLLiveGetLiveRecordRequest.ReocodItem;
import com.xunlei.tdlive.user.f;

// compiled from: UserListFragment2.java
class ac implements OnClickListener {
    final /* synthetic */ ReocodItem a;
    final /* synthetic */ int b;
    final /* synthetic */ y c;

    ac(y yVar, ReocodItem reocodItem, int i) {
        this.c = yVar;
        this.a = reocodItem;
        this.b = i;
    }

    public void onClick(View view) {
        new XLLiveDeleteLiveRecordRequest(f.a().k(), f.a().l(), this.a.roomid).send(new ad(this));
    }
}
