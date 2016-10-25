package com.xunlei.downloadprovider.vod.ui;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize;

// compiled from: VodPlayerMenuPopupWindow.java
public final class b implements OnCheckedChangeListener {
    final /* synthetic */ VodPlayerMenuPopupWindow a;

    public b(VodPlayerMenuPopupWindow vodPlayerMenuPopupWindow) {
        this.a = vodPlayerMenuPopupWindow;
    }

    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (VodPlayerMenuPopupWindow.a(this.a) != null) {
            switch (i) {
                case 2131757196:
                    VodPlayerMenuPopupWindow.b(this.a).setChecked(true);
                    VodPlayerMenuPopupWindow.a(this.a).a(VideoSize.SIZE_FULL);
                case 2131757197:
                    VodPlayerMenuPopupWindow.c(this.a).setChecked(true);
                    VodPlayerMenuPopupWindow.a(this.a).a(VideoSize.SIZE_100);
                case 2131757198:
                    VodPlayerMenuPopupWindow.a(this.a).a(VideoSize.SIZE_75);
                case 2131757199:
                    VodPlayerMenuPopupWindow.a(this.a).a(VideoSize.SIZE_50);
                default:
                    break;
            }
        }
    }
}
