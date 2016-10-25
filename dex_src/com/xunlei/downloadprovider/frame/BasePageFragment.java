package com.xunlei.downloadprovider.frame;

public abstract class BasePageFragment extends BaseCacheViewFragment {
    private static final String TAG = "PageFragment";
    private boolean mIsFirstInvisible;
    private boolean mIsFirstVisible;
    private boolean mIsResume;
    protected boolean mIsUserVisible;

    public BasePageFragment() {
        this.mIsResume = false;
        this.mIsUserVisible = false;
        this.mIsFirstVisible = true;
        this.mIsFirstInvisible = true;
    }

    public void onMainTabClick(boolean z) {
    }

    public void onPageSelected() {
    }

    public void onPageOff() {
    }

    public void onResume() {
        super.onResume();
        this.mIsResume = true;
        if (getUserVisibleHint()) {
            visible();
        }
    }

    public void onPause() {
        super.onPause();
        this.mIsResume = false;
        if (getUserVisibleHint()) {
            invisible();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            visible();
        } else {
            invisible();
        }
    }

    private void visible() {
        if (!this.mIsUserVisible && this.mIsResume) {
            this.mIsUserVisible = true;
            if (this.mIsFirstVisible) {
                onUserVisible(true);
                this.mIsFirstVisible = false;
                return;
            }
            onUserVisible(false);
        }
    }

    private void invisible() {
        if (this.mIsUserVisible) {
            this.mIsUserVisible = false;
            if (this.mIsFirstInvisible) {
                onUserInvisible(true);
                this.mIsFirstInvisible = false;
                return;
            }
            onUserInvisible(false);
        }
    }

    public void onUserVisible(boolean z) {
    }

    public void onUserInvisible(boolean z) {
    }

    public void onFullScreenChange(boolean z) {
    }
}
