package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.tdlive.a.x;
import com.xunlei.tdlive.control.TwinkieListView;

public class ChatListView extends TwinkieListView {
    private Handler a;
    private OnTouchListener b;
    private OnScrollListener c;

    public ChatListView(Context context) {
        super(context);
        this.a = new Handler();
        this.c = new e(this);
        b();
    }

    public ChatListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Handler();
        this.c = new e(this);
        b();
    }

    public ChatListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Handler();
        this.c = new e(this);
        b();
    }

    private void b() {
        setVerticalScrollBarEnabled(false);
        super.setOnScrollListener(this.c);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (this.b != null) {
            this.b.onTouch(this, motionEvent);
        }
        return onTouchEvent;
    }

    public void setExtraTouchEventHandler(OnTouchListener onTouchListener) {
        this.b = onTouchListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(new d(this, onItemClickListener));
    }

    public Handler getSingleTagUpHandler() {
        return this.a;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        if (onScrollListener != null) {
            super.setOnScrollListener(new f(this, onScrollListener));
        }
    }

    public void scrollToBottom(boolean z) {
        x xVar = (x) getAdapter();
        if (xVar != null) {
            postDelayed(new g(this, xVar, z), 1);
        }
    }
}
