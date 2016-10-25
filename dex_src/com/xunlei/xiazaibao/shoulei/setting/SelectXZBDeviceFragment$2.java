package com.xunlei.xiazaibao.shoulei.setting;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

class SelectXZBDeviceFragment$2 implements OnItemClickListener {
    final /* synthetic */ SelectXZBDeviceFragment this$0;

    SelectXZBDeviceFragment$2(SelectXZBDeviceFragment selectXZBDeviceFragment) {
        this.this$0 = selectXZBDeviceFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        XZBDevice xZBDevice = (XZBDevice) SelectXZBDeviceFragment.access$100(this.this$0).get(i);
        SelectXZBDeviceFragment.access$202(this.this$0, xZBDevice);
        SelectXZBDeviceFragment.access$300(this.this$0).notifyDataSetChanged();
        XZBShouleiUtil.getInstance().storeDownloadPathTypeMsg(XZBShouleiUtil.getInstance().getDownloadPathTypeMsg().getDownloadPathType(), xZBDevice);
    }
}
