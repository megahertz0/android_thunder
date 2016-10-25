package com.xunlei.downloadprovider.player.wrapper.a;

import android.text.TextUtils;
import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.wrapper.d;
import java.io.IOException;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SetDataSourceAndPrepareMessage.java
public final class g extends c {
    private String b;

    public g(d dVar, String str) {
        super(dVar);
        this.b = str;
    }

    public final void a() {
        d dVar = this.a;
        String str = this.b;
        while (true) {
            d.c();
            if (!TextUtils.isEmpty(str)) {
                switch (AnonymousClass_1.a[dVar.a.a().ordinal()]) {
                    case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                        new StringBuilder("setDataSource--state=").append(dVar.a.a()).append("|path=").append(str);
                        try {
                            dVar.b.setDataSource(str);
                            dVar.a(MediaPlayerState.INITIALIZED);
                        } catch (IOException e) {
                            new StringBuilder("setDataSource error --- ").append(e).append("|path=").append(str);
                            dVar.a();
                        }
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_OPEN:
                        dVar.a();
                        break;
                    default:
                        new StringBuilder("setDataSource--state=").append(dVar.a.a());
                        break;
                }
            }
            d dVar2 = this.a;
            d.c();
            switch (AnonymousClass_1.a[dVar2.a.a().ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                case SimpleLog.LOG_LEVEL_OFF:
                    new StringBuilder("prepare--state=").append(dVar2.a.a());
                    try {
                        dVar2.b.prepareAsync();
                        dVar2.a(MediaPlayerState.PREPARING);
                        return;
                    } catch (IllegalStateException e2) {
                        dVar2.a();
                    }
                default:
                    new StringBuilder("prepare--state=").append(dVar2.a.a());
                    return;
            }
        }
    }
}
