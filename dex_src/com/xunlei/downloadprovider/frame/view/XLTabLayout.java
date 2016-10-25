package com.xunlei.downloadprovider.frame.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.frame.user.bn;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;

public class XLTabLayout extends LinearLayout implements OnClickListener {
    private a a;
    private b b;
    private String c;

    public static interface a {
        void a(XLTabView xLTabView);
    }

    public static interface b {
        void a(XLTabView xLTabView);
    }

    @SuppressLint({"NewApi"})
    public XLTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public XLTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public XLTabLayout(Context context) {
        super(context);
    }

    public void setOnTabChangeListener(a aVar) {
        this.a = aVar;
    }

    public void setOnClickListener(b bVar) {
        this.b = bVar;
    }

    public void setSelection(String str) {
        for (int i = 0; i < getChildCount(); i++) {
            XLTabView xLTabView = (XLTabView) getChildAt(i);
            if (str.equals(xLTabView.getTabTag())) {
                xLTabView.setSelection(true);
                this.c = str;
            } else {
                xLTabView.setSelection(false);
            }
        }
    }

    public String getCurrentTag() {
        return this.c;
    }

    public XLTabView getCurrentTabView() {
        return a(this.c);
    }

    public final XLTabView a(String str) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            XLTabView xLTabView = (XLTabView) getChildAt(i);
            if (str.equals(xLTabView.getTabTag())) {
                return xLTabView;
            }
        }
        return null;
    }

    public final XLTabView a(int i) {
        return (XLTabView) getChildAt(i);
    }

    public void onClick(View view) {
        XLTabView xLTabView = (XLTabView) view;
        if (this.b != null) {
            this.b.a(xLTabView);
        }
        if (!this.c.equals(xLTabView.getTabTag())) {
            setSelection(xLTabView.getTabTag());
            String tabTag = xLTabView.getTabTag();
            g gVar = new g();
            gVar.a = "android_tabBottom_click";
            if ("thunder".equals(tabTag)) {
                gVar.b = "tabBottom_home_click";
                gVar.c = "tabBottom_home_click";
            } else if ("search".equals(tabTag)) {
                gVar.b = "tabBottom_search_click";
                gVar.c = "tabBottom_search_click";
            } else if ("find".equals(tabTag)) {
                gVar.b = "tabBottom_find_click";
                gVar.c = "tabBottom_find_click";
            } else {
                gVar.b = "tabBottom_personal_click";
                gVar.c = "tabBottom_personal_click";
                gVar.a("if_login", bn.a().a ? 1 : 0);
            }
            ThunderReporter.a(gVar, true);
            if (this.a != null) {
                this.a.a(xLTabView);
            }
        }
    }

    public static BaseFragment a(int i, FragmentManager fragmentManager, a aVar, String str) {
        if (i <= 0 || fragmentManager == null || aVar == null || str == null) {
            return null;
        }
        Fragment a = aVar.a(str);
        if (a == null) {
            return null;
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(i, a).addToBackStack(null);
        beginTransaction.commitAllowingStateLoss();
        return a;
    }
}
