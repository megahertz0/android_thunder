package com.xunlei.downloadprovider.vod;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.taobao.accs.common.Constants;
import com.xunlei.downloadprovider.vod.TouchListenerProxy.TouchOperateType;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.d;
import org.android.spdy.SpdyAgent;

public class TouchListenerProxy implements OnTouchListener {
    private static final String c;
    public int a;
    a b;
    private long d;
    private float e;
    private float f;
    private TouchOperateType g;
    private float h;
    private float i;
    private Runnable j;

    public enum TouchOperateType {
        touch_singleTap,
        touch_doubleTap,
        touch_moveHorizontal,
        touch_moveVerticalLeft,
        touch_moveVerticalRight;

        static {
            touch_singleTap = new com.xunlei.downloadprovider.vod.TouchListenerProxy.TouchOperateType("touch_singleTap", 0);
            touch_doubleTap = new com.xunlei.downloadprovider.vod.TouchListenerProxy.TouchOperateType("touch_doubleTap", 1);
            touch_moveHorizontal = new com.xunlei.downloadprovider.vod.TouchListenerProxy.TouchOperateType("touch_moveHorizontal", 2);
            touch_moveVerticalLeft = new com.xunlei.downloadprovider.vod.TouchListenerProxy.TouchOperateType("touch_moveVerticalLeft", 3);
            touch_moveVerticalRight = new com.xunlei.downloadprovider.vod.TouchListenerProxy.TouchOperateType("touch_moveVerticalRight", 4);
            a = new com.xunlei.downloadprovider.vod.TouchListenerProxy.TouchOperateType[]{touch_singleTap, touch_doubleTap, touch_moveHorizontal, touch_moveVerticalLeft, touch_moveVerticalRight};
        }
    }

    public static interface a {
        void onTouchDoubleTap();

        void onTouchMoveHorizontal(float f, float f2, float f3, float f4);

        void onTouchMoveStart(TouchOperateType touchOperateType);

        void onTouchMoveUp(TouchOperateType touchOperateType);

        void onTouchMoveVerticalLeft(float f, float f2, float f3, float f4);

        void onTouchMoveVerticalRight(float f, float f2, float f3, float f4);

        void onTouchSingleTap();
    }

    public TouchListenerProxy() {
        this.d = 0;
        this.e = 0.0f;
        this.f = 0.0f;
        this.g = TouchOperateType.touch_singleTap;
        this.a = 7;
        this.b = null;
        this.j = new g(this);
    }

    static {
        c = TouchListenerProxy.class.getName();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        Object obj = null;
        new StringBuilder("onTouch=>").append(motionEvent.getAction());
        switch (motionEvent.getAction() & 255) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.g = TouchOperateType.touch_singleTap;
                this.e = motionEvent.getX();
                this.f = motionEvent.getY();
                this.h = this.e;
                this.i = this.f;
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                new StringBuilder("MotionEvent.ACTION_UP mTouchFlag: ").append(this.a);
                switch (AnonymousClass_1.a[this.g.ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.d <= 200) {
                            this.d = 0;
                            view.removeCallbacks(this.j);
                            if (!(this.b == null || (this.a & 2) == 0)) {
                                this.b.onTouchDoubleTap();
                            }
                        } else {
                            this.d = currentTimeMillis;
                            view.postDelayed(this.j, Constants.ST_UPLOAD_MAX_COUNT);
                        }
                        break;
                    default:
                        if (!(this.b == null || (this.a & 4) == 0)) {
                            this.b.onTouchMoveUp(this.g);
                        }
                        break;
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                boolean z;
                Context context = view.getContext();
                float f = this.h;
                float f2 = this.i;
                float f3 = 12.0f * context.getResources().getDisplayMetrics().density;
                if (f <= f3 || f >= ((float) d.a(context)) - f3 || f2 <= f3 || f2 >= ((float) d.b(context)) - f3) {
                    z = true;
                } else {
                    Object obj2 = null;
                }
                if (z) {
                    this.g = TouchOperateType.touch_moveHorizontal;
                } else {
                    float x = motionEvent.getX();
                    f = motionEvent.getY();
                    if (this.g == TouchOperateType.touch_singleTap) {
                        boolean z2;
                        f2 = (float) view.getWidth();
                        f3 = this.e;
                        float f4 = this.f;
                        if (this.g == TouchOperateType.touch_singleTap) {
                            if (Math.abs(x - f3) > 50.0f) {
                                this.g = TouchOperateType.touch_moveHorizontal;
                                if (!(this.b == null || (this.a & 4) == 0)) {
                                    this.b.onTouchMoveStart(this.g);
                                    z2 = true;
                                }
                            } else if (Math.abs(f - f4) > 50.0f) {
                                if (x < f2 / 2.0f) {
                                    this.g = TouchOperateType.touch_moveVerticalLeft;
                                    if (!(this.b == null || (this.a & 4) == 0)) {
                                        this.b.onTouchMoveStart(this.g);
                                        z2 = true;
                                    }
                                } else {
                                    this.g = TouchOperateType.touch_moveVerticalRight;
                                    if (!(this.b == null || (this.a & 4) == 0)) {
                                        this.b.onTouchMoveStart(this.g);
                                    }
                                }
                            }
                            z2 = true;
                        }
                        if (z2) {
                            this.e = x;
                            this.f = f;
                        }
                    } else {
                        if (!(this.b == null || (this.a & 4) == 0)) {
                            if (this.g == TouchOperateType.touch_moveHorizontal) {
                                this.b.onTouchMoveHorizontal(x, f, this.e, this.f);
                            } else if (this.g == TouchOperateType.touch_moveVerticalLeft) {
                                this.b.onTouchMoveVerticalLeft(x, f, this.e, this.f);
                            } else if (this.g == TouchOperateType.touch_moveVerticalRight) {
                                this.b.onTouchMoveVerticalRight(x, f, this.e, this.f);
                            }
                        }
                        this.e = x;
                        this.f = f;
                    }
                }
                break;
        }
        return true;
    }
}
