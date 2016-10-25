package com.xunlei.downloadprovider.download.create;

import android.text.Editable;
import android.text.TextWatcher;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: CreateUrlTask.java
final class g implements TextWatcher {
    final /* synthetic */ CreateUrlTask a;

    g(CreateUrlTask createUrlTask) {
        this.a = createUrlTask;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        this.a.i = false;
        if (editable.length() == 0) {
            this.a.h.setVisibility(XZBDevice.Wait);
            this.a.a.setVisibility(XZBDevice.Wait);
            return;
        }
        this.a.h.setVisibility(0);
        CreateUrlTask createUrlTask = this.a;
        createUrlTask.b.c = createUrlTask.c;
        createUrlTask.a.setAdapter(createUrlTask.b);
        createUrlTask.b.notifyDataSetChanged();
        if (createUrlTask.c == null || createUrlTask.c.size() <= 0) {
            createUrlTask.a.setVisibility(XZBDevice.Wait);
        } else {
            createUrlTask.a.setVisibility(0);
        }
    }
}
