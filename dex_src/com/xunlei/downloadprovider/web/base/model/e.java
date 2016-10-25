package com.xunlei.downloadprovider.web.base.model;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.xllib.R;

// compiled from: ShortMovieDetailDataLoader.java
final class e implements a {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public final void a(Message message) {
        if (message.obj instanceof TaskInfo) {
            switch (message.what) {
                case R.styleable.AppCompatTheme_buttonStyle:
                    if (this.a.e != null) {
                        this.a.e.a(true, null);
                    }
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    if (this.a.e != null) {
                        this.a.e.a(false, message);
                    }
                default:
                    break;
            }
        }
    }
}
