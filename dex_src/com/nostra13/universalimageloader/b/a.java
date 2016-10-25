package com.nostra13.universalimageloader.b;

import android.opengl.GLES10;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.assist.c;
import com.taobao.accs.data.Message;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: ImageSizeUtils.java
public final class a {
    private static c a;

    // compiled from: ImageSizeUtils.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[ViewScaleType.values().length];
            try {
                a[ViewScaleType.FIT_INSIDE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ViewScaleType.CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], Message.FLAG_RET);
        a = new c(max, max);
    }

    public static c a(com.nostra13.universalimageloader.core.c.a aVar, c cVar) {
        int width = aVar.getWidth();
        if (width <= 0) {
            width = cVar.a;
        }
        int height = aVar.getHeight();
        if (height <= 0) {
            height = cVar.b;
        }
        return new c(width, height);
    }

    public static int a(c cVar, c cVar2, ViewScaleType viewScaleType, boolean z) {
        int i;
        int i2 = 1;
        int i3 = cVar.a;
        int i4 = cVar.b;
        int i5 = cVar2.a;
        int i6 = cVar2.b;
        int i7;
        int i8;
        switch (AnonymousClass_1.a[viewScaleType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (z) {
                    i7 = i3 / 2;
                    i8 = i4 / 2;
                    i = 1;
                    while (true) {
                        if (i7 / i > i5 || i8 / i > i6) {
                        }
                        i *= 2;
                    }
                } else {
                    i = Math.max(i3 / i5, i4 / i6);
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (z) {
                    i7 = i3 / 2;
                    i8 = i4 / 2;
                    i = 1;
                    while (i7 / i > i5 && i8 / i > i6) {
                        i *= 2;
                    }
                } else {
                    i = Math.min(i3 / i5, i4 / i6);
                }
                break;
            default:
                i = 1;
                break;
        }
        if (i > 0) {
            i2 = i;
        }
        i = a.a;
        i5 = a.b;
        while (true) {
            if (i3 / i2 <= i && i4 / i2 <= i5) {
                return i2;
            }
            if (z) {
                i2 *= 2;
            } else {
                i2++;
            }
        }
    }

    public static int a(c cVar) {
        int i = cVar.a;
        int i2 = cVar.b;
        return Math.max((int) Math.ceil((double) (((float) i) / ((float) a.a))), (int) Math.ceil((double) (((float) i2) / ((float) a.b))));
    }

    public static float b(c cVar, c cVar2, ViewScaleType viewScaleType, boolean z) {
        int i;
        int i2 = cVar.a;
        int i3 = cVar.b;
        int i4 = cVar2.a;
        int i5 = cVar2.b;
        float f = ((float) i2) / ((float) i4);
        float f2 = ((float) i3) / ((float) i5);
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f < f2) && (viewScaleType != ViewScaleType.CROP || f >= f2)) {
            i = i5;
            i5 = (int) (((float) i2) / f2);
        } else {
            i = (int) (((float) i3) / f);
            i5 = i4;
        }
        return ((z || i5 >= i2 || i >= i3) && (!z || i5 == i2 || i == i3)) ? 1.0f : ((float) i5) / ((float) i2);
    }
}
