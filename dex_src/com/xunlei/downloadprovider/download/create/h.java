package com.xunlei.downloadprovider.download.create;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: CreateUrlTask.java
final class h implements OnItemClickListener {
    final /* synthetic */ CreateUrlTask a;

    h(CreateUrlTask createUrlTask) {
        this.a = createUrlTask;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.f.setText(this.a.b.a(i));
        this.a.f.setSelection(this.a.f.getText().toString().length());
        this.a.a.setVisibility(XZBDevice.Wait);
        this.a.b.c.clear();
    }
}
