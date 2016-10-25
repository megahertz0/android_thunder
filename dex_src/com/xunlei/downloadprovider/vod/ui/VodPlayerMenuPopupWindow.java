package com.xunlei.downloadprovider.vod.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize;

public final class VodPlayerMenuPopupWindow extends a {
    public View b;
    public RadioGroup c;
    public SeekBar d;
    public SeekBar e;
    public a f;
    private RadioButton g;
    private RadioButton h;
    private RadioButton i;
    private RadioButton j;

    public static interface a {
        void a(VideoSize videoSize);
    }

    public enum VideoSize {
        SIZE_FULL(0.0d),
        SIZE_100(1.0d),
        SIZE_75(0.75d),
        SIZE_50(0.5d);
        double a;

        static {
            SIZE_FULL = new com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize("SIZE_FULL", 0, 0.0d);
            SIZE_100 = new com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize("SIZE_100", 1, 1.0d);
            SIZE_75 = new com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize("SIZE_75", 2, 0.75d);
            SIZE_50 = new com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize("SIZE_50", 3, 0.5d);
            b = new com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize[]{SIZE_FULL, SIZE_100, SIZE_75, SIZE_50};
        }

        private VideoSize(double d) {
            this.a = d;
        }

        public final double getValue() {
            return this.a;
        }
    }

    public VodPlayerMenuPopupWindow(Context context) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(2130969045, null);
        setContentView(inflate);
        this.c = (RadioGroup) inflate.findViewById(2131757195);
        this.g = (RadioButton) inflate.findViewById(2131757196);
        this.h = (RadioButton) inflate.findViewById(2131757197);
        this.i = (RadioButton) inflate.findViewById(2131757198);
        this.j = (RadioButton) inflate.findViewById(2131757199);
        this.d = (SeekBar) inflate.findViewById(2131757200);
        this.e = (SeekBar) inflate.findViewById(2131757201);
        inflate.setFocusable(true);
        inflate.setFocusableInTouchMode(true);
        this.b = inflate;
    }

    public final void a(int i, int i2) {
        this.d.setMax(i2);
        this.d.setProgress(i);
    }
}
