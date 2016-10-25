package com.xunlei.tdlive.view;

import android.graphics.Bitmap;
import com.xunlei.tdlive.aniengine.aa;
import com.xunlei.tdlive.aniengine.aa.c;
import com.xunlei.tdlive.aniengine.y;
import java.util.Random;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: FloatHeartSprite.java
public class k extends y {
    public k(Bitmap bitmap) {
        a(new Bitmap[]{bitmap});
        aa.a((y) this, c.a(0).a(0.0f, 0.0f).c(1.0f, 1.0f).a("90%p", "92%p")).a(c.a(15).a(1.0f, 1.0f).a((new Random().nextInt(SimpleLog.LOG_LEVEL_OFF) + 92) + "%p", (new Random().nextInt(SimpleLog.LOG_LEVEL_ERROR) + 85) + "%p")).a(c.a(35).a(0.3f).a((new Random().nextInt(SpdyProtocol.PUBKEY_PSEQ_OPEN) + 90) + "%p", "65%p").b(com.xunlei.tdlive.aniengine.c.n));
    }

    protected float a(float f) {
        return 1.0f;
    }
}
