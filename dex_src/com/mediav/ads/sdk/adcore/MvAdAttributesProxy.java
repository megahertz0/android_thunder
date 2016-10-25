package com.mediav.ads.sdk.adcore;

import com.mediav.ads.sdk.interfaces.DynamicObject;
import com.mediav.ads.sdk.interfaces.IMvAdAttributes;
import com.mediav.ads.sdk.interfaces.IMvProductAdAttributes;
import com.mediav.ads.sdk.interfaces.IMvVideoAdAttributes;
import com.mediav.ads.sdk.interfaces.ObjectDescriptor;
import com.mediav.ads.sdk.log.MVLog;
import com.xunlei.tdlive.R;

class MvAdAttributesProxy implements DynamicObject, ObjectDescriptor {
    private final IMvAdAttributes adAttributes;
    private final Object descriptor;

    public MvAdAttributesProxy(IMvAdAttributes iMvAdAttributes) {
        this.adAttributes = iMvAdAttributes;
        if (iMvAdAttributes instanceof IMvProductAdAttributes) {
            MVLog.d("ADSUPDATE", "TYPE_MVPRODUCTADATTRIBUTES");
            this.descriptor = Integer.valueOf(R.styleable.AppCompatTheme_toolbarStyle);
        } else if (iMvAdAttributes instanceof IMvVideoAdAttributes) {
            MVLog.d("ADSUPDATE", "TYPE_MVVIDEOADATTRIBUTES");
            this.descriptor = Integer.valueOf(R.styleable.AppCompatTheme_activityChooserViewStyle);
        } else {
            this.descriptor = null;
        }
    }

    public Object invoke(int i, Object obj) {
        switch (i) {
            case R.styleable.AppCompatTheme_dividerHorizontal:
                MVLog.d("ADSUPDATE", "MVADATTRIBUTES_getAttributes");
                return this.adAttributes.getAttributes();
            default:
                return null;
        }
    }

    public Object getDescriptor() {
        return this.descriptor;
    }
}
