package com.xunlei.tdlive.a;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.tdlive.a.j.a;
import com.xunlei.tdlive.modal.JsonWrapper;
import org.android.spdy.TnetStatusCode;

// compiled from: JsonArrayPageAdapter.java
public abstract class k<T> extends PagerAdapter implements j<T> {
    protected a a;
    private boolean b;
    private JsonWrapper c;

    protected abstract void a(int i, View view);

    public k() {
        this.b = false;
    }

    public void a(T t) {
        a(t, true, false);
    }

    public void b(T t) {
        a(t, false, false);
    }

    public boolean b() {
        if (this.b) {
            return false;
        }
        this.b = true;
        return true;
    }

    public void c() {
        this.b = false;
    }

    public JsonWrapper d() {
        return this.c;
    }

    public void a(JsonWrapper jsonWrapper) {
        if (jsonWrapper != null && jsonWrapper.isArray()) {
            this.c = jsonWrapper;
            notifyDataSetChanged();
        }
    }

    public JsonWrapper d(int i) {
        if (this.c == null || !this.c.isArray() || i >= this.c.getLength()) {
            return null;
        }
        JsonWrapper object = this.c.getObject(i);
        return object == null ? this.c.getArray(i) : object;
    }

    public int getCount() {
        return this.c != null ? this.c.getLength() : 0;
    }

    public int getItemPosition(Object obj) {
        return TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        a(i, (View) obj);
        viewGroup.removeView((View) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = getView(i, null, viewGroup);
        if (view != null) {
            viewGroup.addView(view, -1, -1);
        }
        return view;
    }
}
