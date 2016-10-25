package com.qq.e.comm.pi;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.qq.e.comm.adevent.ADListener;

public abstract class SPVI extends RelativeLayout {
    public SPVI(Context context, String str, String str2) {
        super(context);
    }

    public abstract void fetchAndShowIn(ViewGroup viewGroup);

    public abstract void setAdListener(ADListener aDListener);

    public abstract void setFetchDelay(int i);
}
