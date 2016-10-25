package com.xunlei.tdlive.control;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.xunlei.tdlive.util.d;

public class VisitorHListView extends RecyclerView {
    public VisitorHListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public VisitorHListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public VisitorHListView(Context context) {
        super(context);
        a();
    }

    private void a() {
        setOrientation(false);
        setSpacing(d.a(getContext(), 4.0f));
        setClipChildren(false);
    }

    public void setOrientation(boolean z) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.a(0);
        setLayoutManager(linearLayoutManager);
    }

    public void setSpacing(float f) {
        addItemDecoration(new s(this, f));
    }
}
