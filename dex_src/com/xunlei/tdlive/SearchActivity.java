package com.xunlei.tdlive;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.xiazaibao.BuildConfig;

public class SearchActivity extends BaseActivity implements OnClickListener {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_search);
        setTitle(BuildConfig.VERSION_NAME);
        setLeftVisible(true);
        setLeftClickListener(this);
        setLeftDrawable(getResources().getDrawable(R.drawable.xllive_ic_back));
        setRightVisible(true);
        setRightText("\u641c\u7d22");
    }

    public void onClick(View view) {
        if (view == this.mTitleBarLeft) {
            finish();
        }
    }
}
