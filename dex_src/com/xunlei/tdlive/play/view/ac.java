package com.xunlei.tdlive.play.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.d;
import org.android.spdy.TnetStatusCode;

// compiled from: ShareBubbleWindowHelper.java
public class ac extends b {
    private View e;
    private TextView f;

    public ac(Activity activity) {
        super(activity);
    }

    protected View a(LayoutInflater layoutInflater) {
        this.e = layoutInflater.inflate(R.layout.xllive_share_bubble, null);
        this.f = (TextView) this.e.findViewById(R.id.text);
        this.f.setText((String) e());
        return this.e;
    }

    protected void a(PopupWindow popupWindow) {
        popupWindow.setWidth(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
        this.a.setAnimationStyle(R.style.PopupAnimation);
    }

    protected void d() {
        Context context = (Context) this.b.get();
        if (context != null) {
            View view = this.c.b;
            float measuredWidth = (float) view.getMeasuredWidth();
            float measuredHeight = (float) view.getMeasuredHeight();
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(9999, Integer.MIN_VALUE);
            this.d.measure(makeMeasureSpec, makeMeasureSpec);
            measuredWidth /= 2.0f;
            this.a.showAsDropDown(view, (int) (measuredWidth + ((-((float) this.d.getMeasuredWidth())) / 2.0f)), (int) (((-((float) this.d.getMeasuredHeight())) - measuredHeight) - d.a(context, 3.0f)));
        }
    }

    public void a(long j) {
        if (this.e != null) {
            this.e.postDelayed(new ad(this), j);
        }
    }
}
