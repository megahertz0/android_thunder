package com.sina.weibo.sdk.register.mobile;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class SelectCountryItemView extends RelativeLayout {
    private TextView mCountryCode;
    private TextView mCountryName;

    public SelectCountryItemView(Context context, String str, String str2) {
        super(context);
        initView(str, str2);
    }

    private void initView(String str, String str2) {
        setLayoutParams(new LayoutParams(-1, ResourceManager.dp2px(getContext(), R.styleable.AppCompatTheme_textAppearanceLargePopupMenu)));
        this.mCountryName = new TextView(getContext());
        this.mCountryName.setTextSize(16.0f);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = ResourceManager.dp2px(getContext(), XZBDevice.Delete);
        layoutParams.addRule(XZBDevice.Pause);
        layoutParams.addRule(XZBDevice.Delete);
        this.mCountryName.setGravity(R.styleable.Toolbar_titleMarginBottom);
        this.mCountryName.setLayoutParams(layoutParams);
        this.mCountryName.setText(str);
        this.mCountryName.setTextColor(-13421773);
        addView(this.mCountryName);
        this.mCountryCode = new TextView(getContext());
        this.mCountryCode.setTextSize(16.0f);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(XZBDevice.Delete);
        layoutParams.addRule(XZBDevice.Success);
        layoutParams.rightMargin = ResourceManager.dp2px(getContext(), R.styleable.AppCompatTheme_textAppearanceLargePopupMenu);
        this.mCountryCode.setLayoutParams(layoutParams);
        this.mCountryCode.setText(str2);
        this.mCountryCode.setTextColor(-11502161);
        this.mCountryCode.setGravity(R.styleable.Toolbar_titleMarginBottom);
        addView(this.mCountryCode);
        setContent(str, str2);
    }

    public void updateContent(String str, String str2) {
        setContent(str, str2);
    }

    private void setContent(String str, String str2) {
        this.mCountryName.setText(str);
        this.mCountryCode.setText(str2);
    }
}
