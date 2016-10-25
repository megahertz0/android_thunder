package com.xunlei.tdlive.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.xunlei.tdlive.control.TabBar;
import com.xunlei.tdlive.control.TabBar.a;

// compiled from: TabFragmentManager.java
public class m implements a {
    private FragmentManager a;
    private int b;
    private TabBar c;
    private String d;

    public m(FragmentManager fragmentManager, TabBar tabBar, int i) {
        this.a = fragmentManager;
        this.c = tabBar;
        this.b = i;
        if (this.c != null) {
            this.c.setOnTabBarListener(this);
        }
    }

    public void a(String str) {
        if (str != null && this.a != null) {
            i iVar = (i) this.a.findFragmentByTag(str);
            if (iVar != null) {
                this.d = str;
                this.a.beginTransaction().remove(iVar).commit();
                iVar.a();
            }
        }
    }

    public void b(String str) {
        if (this.a != null) {
            Fragment fragment;
            i iVar = (i) this.a.findFragmentByTag(str);
            if (iVar == null) {
                try {
                    fragment = (i) ((Class) this.c.getTab(str).getTag()).newInstance();
                    fragment.a(this);
                    this.a.beginTransaction().add(this.b, fragment, str).commit();
                } catch (Exception e) {
                }
            }
            if (fragment != null) {
                this.a.beginTransaction().show(fragment).commit();
            }
        }
    }

    public void c(String str) {
        i iVar = (i) this.a.findFragmentByTag(str);
        if (iVar != null) {
            iVar.a(true);
        }
    }
}
