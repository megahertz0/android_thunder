package com.xunlei.tdlive.view;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

// compiled from: LiveListUserFollowView.java
class o extends AdapterView<Adapter> {
    final /* synthetic */ LiveListUserFollowView a;

    o(LiveListUserFollowView liveListUserFollowView, Context context) {
        this.a = liveListUserFollowView;
        super(context);
    }

    public Adapter getAdapter() {
        return this.a.g;
    }

    public void setAdapter(Adapter adapter) {
    }

    public View getSelectedView() {
        return null;
    }

    public void setSelection(int i) {
    }
}
