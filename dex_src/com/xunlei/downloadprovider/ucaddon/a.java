package com.xunlei.downloadprovider.ucaddon;

import android.os.Message;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.xllib.R;

// compiled from: UcAddonEventRecevier.java
final class a implements com.xunlei.downloadprovider.a.h.a {
    final /* synthetic */ UcAddonEventRecevier a;

    a(UcAddonEventRecevier ucAddonEventRecevier) {
        this.a = ucAddonEventRecevier;
    }

    public final void a(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                this.a.onTaskAddSuccess((TaskInfo) message.obj);
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                this.a.onTaskAddFail((TaskInfo) message.obj, message.arg1);
            case R.styleable.AppCompatTheme_ratingBarStyleSmall:
                this.a.onTaskStatusChanged((TaskInfo) message.obj);
            default:
                break;
        }
    }
}
