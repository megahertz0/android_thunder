package com.xunlei.downloadprovider.vod.protocol;

import android.os.Handler.Callback;
import android.os.Message;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.xllib.R;

// compiled from: DownloadVodUtil.java
final class g implements Callback {
    final /* synthetic */ b a;

    g(b bVar) {
        this.a = bVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                XLToast.b(BrothersApplication.a(), XLToastType.XLTOAST_TYPE_SUC, "\u521b\u5efa\u4e0b\u8f7d\u6210\u529f");
                break;
        }
        return false;
    }
}
