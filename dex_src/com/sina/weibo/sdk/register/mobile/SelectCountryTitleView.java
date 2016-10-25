package com.sina.weibo.sdk.register.mobile;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class SelectCountryTitleView extends RelativeLayout {
    private TextView mTitleTv;

    public SelectCountryTitleView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setLayoutParams(new LayoutParams(-1, ResourceManager.dp2px(getContext(), R.styleable.Toolbar_subtitleTextColor)));
        setBackgroundDrawable(ResourceManager.getDrawable(getContext(), "tableview_sectionheader_background.png"));
        this.mTitleTv = new TextView(getContext());
        this.mTitleTv.setTextSize(14.0f);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(XZBDevice.Delete);
        layoutParams.leftMargin = ResourceManager.dp2px(getContext(), XZBDevice.Stop);
        this.mTitleTv.setLayoutParams(layoutParams);
        this.mTitleTv.setGravity(XZBDevice.DOWNLOAD_LIST_FAILED);
        this.mTitleTv.setTextColor(-7171438);
        this.mTitleTv.setGravity(R.styleable.Toolbar_titleMarginBottom);
        addView(this.mTitleTv);
    }

    public void setTitle(String str) {
        this.mTitleTv.setText(str);
    }

    public void update(String str) {
        setTitle(str);
    }
}
