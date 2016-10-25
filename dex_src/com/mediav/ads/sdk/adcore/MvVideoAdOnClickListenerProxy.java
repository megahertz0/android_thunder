package com.mediav.ads.sdk.adcore;

import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvVideoAdOnClickListener;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;

class MvVideoAdOnClickListenerProxy implements DynamicObject {
    private final IMvVideoAdOnClickListener listener;

    public MvVideoAdOnClickListenerProxy(IMvVideoAdOnClickListener iMvVideoAdOnClickListener) {
        this.listener = iMvVideoAdOnClickListener;
    }

    public Object invoke(int i, Object obj) {
        switch (i) {
            case R.styleable.AppCompatTheme_dialogPreferredPadding:
                MVLog.d("ADSUPDATE", "MVVIDEOADONCLICKLISTENER_onDownloadConfirmed");
                this.listener.onDownloadConfirmed();
                break;
            case R.styleable.AppCompatTheme_listDividerAlertDialog:
                MVLog.d("ADSUPDATE", "MVVIDEOADONCLICKLISTENER_onDownloadCancelled");
                this.listener.onDownloadCancelled();
                break;
            case R.styleable.AppCompatTheme_actionDropDownStyle:
                MVLog.d("ADSUPDATE", "MVVIDEOADONCLICKLISTENER_onLandingpageOpened");
                this.listener.onLandingpageOpened();
                break;
            case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight:
                MVLog.d("ADSUPDATE", "MVVIDEOADONCLICKLISTENER_onLandingpageClosed");
                this.listener.onLandingpageClosed();
                break;
        }
        return null;
    }
}
