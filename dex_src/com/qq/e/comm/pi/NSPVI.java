package com.qq.e.comm.pi;

import android.view.ViewGroup;
import com.qq.e.comm.adevent.ADListener;

public interface NSPVI {
    void fetchAndShowIn(ViewGroup viewGroup);

    void setAdListener(ADListener aDListener);

    void setFetchDelay(int i);
}
