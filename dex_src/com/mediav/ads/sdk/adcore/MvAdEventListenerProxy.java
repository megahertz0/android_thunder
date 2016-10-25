package com.mediav.ads.sdk.adcore;

import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvAdEventListener;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class MvAdEventListenerProxy implements DynamicObject {
    private final IMvAdEventListener adEventListener;

    public MvAdEventListenerProxy(IMvAdEventListener iMvAdEventListener) {
        this.adEventListener = iMvAdEventListener;
    }

    public Object invoke(int i, Object obj) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_ALL:
                MVLog.d("ADSUPDATE", "MVADEVENTLISTENER_onAdviewGotAdSucceed");
                this.adEventListener.onAdviewGotAdSucceed();
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                MVLog.d("ADSUPDATE", "MVADEVENTLISTENER_onAdviewGotAdFail");
                this.adEventListener.onAdviewGotAdFail();
                break;
            case R.styleable.Toolbar_contentInsetEnd:
                MVLog.d("ADSUPDATE", "MVADEVENTLISTENER_onAdviewIntoLandpage");
                this.adEventListener.onAdviewIntoLandpage();
                break;
            case R.styleable.Toolbar_contentInsetLeft:
                MVLog.d("ADSUPDATE", "MVADEVENTLISTENER_onAdviewDismissedLandpage");
                this.adEventListener.onAdviewDismissedLandpage();
                break;
            case XZBDevice.Wait:
                MVLog.d("ADSUPDATE", "MVADEVENTLISTENER_onAdviewClicked");
                this.adEventListener.onAdviewClicked();
                break;
            case XZBDevice.Pause:
                MVLog.d("ADSUPDATE", "MVADEVENTLISTENER_onAdviewClosed");
                this.adEventListener.onAdviewClosed();
                break;
            case XZBDevice.Stop:
                MVLog.d("ADSUPDATE", "MVADEVENTLISTENER_onAdviewDestroyed");
                this.adEventListener.onAdviewDestroyed();
                break;
            case R.styleable.AppCompatTheme_imageButtonStyle:
                MVLog.d("ADSUPDATE", "MVADEVENTLISTENER_onAdviewRendered");
                this.adEventListener.onAdviewRendered();
                break;
        }
        return null;
    }
}
