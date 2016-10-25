package com.xunlei.downloadprovider.xiazaibao.setting;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

// compiled from: SelectXZBDeviceFragment.java
final class g implements OnItemClickListener {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        XZBDevice xZBDevice = (XZBDevice) e.b(this.a).get(i);
        e.a(this.a, xZBDevice);
        e.c(this.a).notifyDataSetChanged();
        XZBShouleiUtil.getInstance().storeDownloadPathTypeMsg(XZBShouleiUtil.getInstance().getDownloadPathTypeMsg().getDownloadPathType(), xZBDevice);
    }
}
