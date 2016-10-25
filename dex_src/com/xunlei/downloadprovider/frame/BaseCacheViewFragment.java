package com.xunlei.downloadprovider.frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseCacheViewFragment extends BaseFragment {
    private View mRootView;

    public abstract View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = createView(layoutInflater, viewGroup, bundle);
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.mRootView.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.mRootView);
            }
        }
        return this.mRootView;
    }
}
