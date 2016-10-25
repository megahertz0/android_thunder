package com.inmobi.ads;

import android.view.View;
import com.inmobi.ads.InMobiNative.NativeAdListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.net.URL;
import java.util.Map;

public final class InMobiCustomNative extends InMobiNative {
    private static final String TAG;

    static {
        TAG = InMobiCustomNative.class.getSimpleName();
    }

    public InMobiCustomNative(long j, NativeAdListener nativeAdListener) {
        super(j, nativeAdListener);
    }

    public static void bind(View view, InMobiNative inMobiNative, URL url) {
        if (view == null || inMobiNative == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please pass non-null instances of a view and InMobiNative to bind.");
            return;
        }
        if (((p) sMappedAdUnits.get(view)) != null) {
            unbind(view);
        }
        p pVar = inMobiNative.mNativeAdUnit;
        if (pVar != null) {
            sMappedAdUnits.remove(view);
            sMappedAdUnits.put(view, pVar);
            pVar.a(view, url, null);
        }
    }

    public static void bind(View view, InMobiNative inMobiNative, String str) {
        if (view == null || inMobiNative == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please pass non-null instances of a view and InMobiNative to bind.");
            return;
        }
        if (((p) sMappedAdUnits.get(view)) != null) {
            unbind(view);
        }
        p pVar = inMobiNative.mNativeAdUnit;
        if (pVar != null) {
            sMappedAdUnits.remove(view);
            sMappedAdUnits.put(view, pVar);
            pVar.a(view, null, str);
        }
    }

    public final void reportAdClick(URL url, Map<String, String> map) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.a((Map) map, url, null);
        }
    }

    public final void reportAdClick(String str, Map<String, String> map) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.a((Map) map, null, str);
        }
    }

    public final void reportAdClickAndOpenLandingPage(URL url, Map<String, String> map) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.a((Map) map, url, null);
            this.mNativeAdUnit.B();
        }
    }

    public final void reportAdClickAndOpenLandingPage(String str, Map<String, String> map) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.a((Map) map, null, str);
            this.mNativeAdUnit.B();
        }
    }
}
