package com.xunlei.tdlive.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.util.HashMap;

public class TabBar extends LinearLayout implements OnClickListener {
    private a a;
    private HashMap<String, View> b;
    private String c;

    public static interface a {
        void a(String str);

        void b(String str);

        void c(String str);
    }

    public TabBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = new HashMap();
        this.c = null;
    }

    public TabBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = new HashMap();
        this.c = null;
    }

    public TabBar(Context context) {
        super(context);
        this.a = null;
        this.b = new HashMap();
        this.c = null;
    }

    public void setOnTabBarListener(a aVar) {
        this.a = aVar;
    }

    public View addTab(String str, String str2, float f, int i, int i2) {
        View textView = new TextView(getContext());
        textView.setText(str2);
        textView.setLayoutParams(new LayoutParams(0, -1, 1.0f));
        textView.setGravity(R.styleable.Toolbar_maxButtonHeight);
        textView.setTextColor(getResources().getColor(i2));
        textView.setTextSize(f);
        textView.setBackgroundResource(i);
        return addTab(str, textView, false);
    }

    public void select(String str) {
        if (str != null && !str.equals(BuildConfig.VERSION_NAME) && this.b.containsKey(str)) {
            onClick((View) this.b.get(str));
        }
    }

    public String getSelected() {
        return this.c;
    }

    public View getSelectedTab() {
        return (View) this.b.get(this.c);
    }

    public View addTab(String str, View view, boolean z) {
        if (view == null) {
            return null;
        }
        if (this.b.containsKey(str)) {
            return view;
        }
        this.b.put(str, view);
        view.setOnClickListener(this);
        addView(view);
        if (!z) {
            return view;
        }
        view.post(new r(this, view));
        return view;
    }

    public View getTab(String str) {
        return (View) this.b.get(str);
    }

    public String getTag(View view) {
        for (String str : this.b.keySet()) {
            if (this.b.get(str) == view) {
                return str;
            }
        }
        return null;
    }

    public void removeTab(String str) {
        View view = (View) this.b.remove(str);
        if (view != null) {
            removeView(view);
        }
    }

    public void onClick(View view) {
        View view2 = (View) this.b.get(this.c);
        if (this.c == null || view != view2) {
            String tag = getTag(view);
            if (tag != null) {
                if (view2 != null) {
                    view2.setSelected(false);
                }
                view.setSelected(true);
                if (this.a != null) {
                    this.a.a(this.c);
                    a aVar = this.a;
                    this.c = tag;
                    aVar.b(tag);
                    return;
                }
                return;
            }
            return;
        }
        view.setSelected(true);
        if (this.a != null) {
            this.a.c(this.c);
        }
    }
}
