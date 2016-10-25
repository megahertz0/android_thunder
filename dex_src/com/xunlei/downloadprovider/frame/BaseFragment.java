package com.xunlei.downloadprovider.frame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.downloadprovider.app.BrothersApplication;

public class BaseFragment extends Fragment {
    public static final String TAG = "BaseFragment";
    private Bundle extras;
    public FragmentActivity mActivity;
    protected TextView mDefaultText;
    public ViewGroup mPageRoot;

    public BaseFragment() {
        this.extras = null;
    }

    public void onAttach(Activity activity) {
        new StringBuilder("onAttach. to ").append(activity.getClass().getSimpleName());
        this.mActivity = (FragmentActivity) activity;
        getArguments();
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mPageRoot != null) {
            return this.mPageRoot;
        }
        View inflate = layoutInflater.inflate(2130968711, viewGroup, false);
        this.mDefaultText = (TextView) inflate.findViewById(2131755696);
        return inflate;
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View findViewById(int i) {
        return this.mPageRoot != null ? this.mPageRoot.findViewById(i) : null;
    }

    protected void startActivityWithAnimation(Class<?> cls, boolean z) {
        startActivity(new Intent(this.mActivity, cls));
    }

    protected void startActivityWithAnimation(Class<?> cls, boolean z, Intent intent) {
        intent.setClassName(this.mActivity, cls.getName());
        startActivity(intent);
    }

    public void startActivityWithAnimation(Class<?> cls) {
        startActivityWithAnimation((Class) cls, true);
    }

    protected void startActivityWithAnimation(Intent intent) {
        if (intent != null) {
            startActivityWithAnimation(intent, true);
        }
    }

    protected void startActivityWithAnimation(Intent intent, boolean z) {
        startActivity(intent);
    }

    protected void startActivityWithExtraAnimation(Class<?> cls, int i, int i2) {
        startActivity(new Intent(this.mActivity, cls));
        this.mActivity.overridePendingTransition(i, i2);
    }

    public boolean onMenuPressed() {
        return false;
    }

    public void onCreateTask(boolean z, int i) {
    }

    public String getFragmentTAG() {
        return TAG;
    }

    public String getResouceString(int i) {
        return BrothersApplication.a().getString(i);
    }

    public String getResouceString(int i, Object... objArr) {
        return BrothersApplication.a().getString(i, objArr);
    }

    public Drawable getResouceDrawable(int i) {
        return BrothersApplication.a().getResources().getDrawable(i);
    }

    public Context getApplicationContext() {
        return BrothersApplication.a().getApplicationContext();
    }

    public boolean isIntercept() {
        return true;
    }

    public boolean onBackPressed() {
        return false;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle bundle) {
        this.extras = bundle;
    }
}
