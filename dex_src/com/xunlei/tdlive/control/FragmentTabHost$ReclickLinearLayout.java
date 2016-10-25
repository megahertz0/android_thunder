package com.xunlei.tdlive.control;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.xunlei.tdlive.base.i;

public class FragmentTabHost$ReclickLinearLayout extends LinearLayout implements OnClickListener {
    OnClickListener a;
    OnClickListener b;
    final /* synthetic */ FragmentTabHost c;

    public FragmentTabHost$ReclickLinearLayout(FragmentTabHost fragmentTabHost, Context context) {
        this.c = fragmentTabHost;
        super(context);
    }

    public FragmentTabHost$ReclickLinearLayout(FragmentTabHost fragmentTabHost, Context context, AttributeSet attributeSet) {
        this.c = fragmentTabHost;
        super(context, attributeSet);
    }

    public FragmentTabHost$ReclickLinearLayout(FragmentTabHost fragmentTabHost, Context context, AttributeSet attributeSet, int i) {
        this.c = fragmentTabHost;
        super(context, attributeSet, i);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.a = onClickListener;
        super.setOnClickListener(this);
    }

    public void setMyOnClickListener(OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public void onClick(View view) {
        String currentTabTag = this.c.getCurrentTabTag();
        if (this.a != null) {
            this.a.onClick(view);
        }
        if (this.b != null) {
            this.b.onClick(view);
        }
        if (currentTabTag.equals(this.c.getCurrentTabTag())) {
            Fragment findFragmentByTag = FragmentTabHost.a(this.c).findFragmentByTag(currentTabTag);
            if (findFragmentByTag instanceof i) {
                ((i) findFragmentByTag).a(true);
            }
        }
    }
}
