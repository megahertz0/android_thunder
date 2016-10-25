package com.xunlei.tdlive.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;

public class LiveListUserFollowView extends LinearLayout implements OnClickListener {
    private LinearLayout a;
    private TextView b;
    private OnItemClickListener c;
    private AdapterView<?> d;
    private int e;
    private boolean f;
    private BaseAdapter g;
    private DataSetObserver h;

    public LiveListUserFollowView(Context context) {
        super(context);
        this.e = 3;
        this.f = false;
        this.h = new n(this);
    }

    public LiveListUserFollowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 3;
        this.f = false;
        this.h = new n(this);
    }

    public LiveListUserFollowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 3;
        this.f = false;
        this.h = new n(this);
    }

    @TargetApi(21)
    public LiveListUserFollowView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.e = 3;
        this.f = false;
        this.h = new n(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (LinearLayout) findViewById(R.id.live_user);
        this.b = (TextView) findViewById(R.id.live_count);
    }

    public void setShowLiveUser(int i) {
        this.e = i;
        this.h.onChanged();
    }

    public void setLogined(boolean z) {
        this.f = z;
        this.h.onChanged();
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        if (this.g != null) {
            this.g.unregisterDataSetObserver(this.h);
        }
        this.g = baseAdapter;
        this.g.registerDataSetObserver(this.h);
        this.g.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.c = onItemClickListener;
    }

    public void onClick(View view) {
        if (this.c != null) {
            if (this.d == null) {
                this.d = new o(this, view.getContext());
                this.d.setId(getId());
            }
            this.c.onItemClick(this.d, view, view.getId(), (long) view.getId());
        }
    }
}
