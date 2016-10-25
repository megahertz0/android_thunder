package com.xunlei.tdlive.view;

import android.content.Context;
import android.util.AttributeSet;
import com.xunlei.tdlive.aniengine.AniSurfaceView;
import com.xunlei.tdlive.aniengine.y;
import com.xunlei.tdlive.modal.b.a;
import com.xunlei.tdlive.util.e;
import com.xunlei.tdlive.util.e.b;
import com.xunlei.tdlive.util.v;
import java.util.LinkedList;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

public class AnimationSurfaceView extends AniSurfaceView implements Runnable {
    private LinkedList<y> a;

    public AnimationSurfaceView(Context context) {
        super(context);
        this.a = new LinkedList();
    }

    public AnimationSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new LinkedList();
    }

    public AnimationSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new LinkedList();
    }

    public AnimationSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = new LinkedList();
    }

    public void setChatState(boolean z) {
    }

    public boolean addSeniorGift(a aVar) {
        Object obj = null;
        String str = v.a(aVar.b, SpdyProtocol.PUBKEY_SEQ_OPEN, SimpleLog.LOG_LEVEL_DEBUG, 1, "..") + " \u00b7 \u9001" + aVar.i;
        if (aVar.h == 201) {
            obj = new j(str, aVar.g, aVar.c);
        } else if (aVar.h == 203) {
            obj = new b(str, aVar.g, aVar.c);
        } else if (aVar.h == 204) {
            obj = new c(str, aVar.g, aVar.c);
        } else if (aVar.h == 205) {
            obj = new p(str, aVar.g, aVar.c);
        }
        if (obj == null) {
            return false;
        }
        this.a.add(obj);
        removeCallbacks(this);
        post(this);
        return true;
    }

    public void addFloatUnit(int i) {
        addFloatUnit(i, b.a);
    }

    public void addFloatUnit(int i, b bVar) {
        if (i == 0) {
            i = e.a();
        }
        if (getAniEngine().a(k.class) <= 200) {
            getAniEngine().a(new k(e.a(getContext(), i, bVar)));
        }
    }

    public void run() {
        if (!this.a.isEmpty()) {
            if (getAniEngine().a(a.class) <= 0) {
                getAniEngine().a((y) this.a.pop());
            }
            postDelayed(this, 500);
        }
    }
}
