package com.xunlei.downloadprovider.member.payment.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.uc.addon.sdk.remote.Tabs;
import java.util.List;

// compiled from: PayPagerAdapter.java
public final class ah extends FragmentPagerAdapter {
    SparseArray<Fragment> a;
    List<x> b;
    private FragmentManager c;

    public ah(FragmentManager fragmentManager, List<x> list) {
        super(fragmentManager);
        this.a = new SparseArray();
        this.c = fragmentManager;
        this.b = list;
    }

    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        Object instantiateItem = super.instantiateItem(viewGroup, i);
        new StringBuilder("instantiateItem--position=").append(i).append("|object=").append(instantiateItem);
        return instantiateItem;
    }

    public final Fragment getItem(int i) {
        Fragment fragment;
        x xVar = (x) this.b.get(i);
        try {
            fragment = (Fragment) xVar.b.newInstance();
            try {
                Bundle bundle = xVar.c;
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putInt("position", i);
                fragment.setArguments(bundle);
            } catch (InstantiationException e) {
                InstantiationException e2 = e;
                e2.printStackTrace();
                if (fragment != null) {
                    this.a.put(i, fragment);
                }
                new StringBuilder("getItem--position=").append(i).append("|fragment=").append(fragment);
                return fragment;
            } catch (IllegalAccessException e3) {
                IllegalAccessException e4 = e3;
                e4.printStackTrace();
                if (fragment != null) {
                    this.a.put(i, fragment);
                }
                new StringBuilder("getItem--position=").append(i).append("|fragment=").append(fragment);
                return fragment;
            }
        } catch (InstantiationException e5) {
            e2 = e5;
            fragment = null;
            e2.printStackTrace();
            if (fragment != null) {
                this.a.put(i, fragment);
            }
            new StringBuilder("getItem--position=").append(i).append("|fragment=").append(fragment);
            return fragment;
        } catch (IllegalAccessException e6) {
            e4 = e6;
            fragment = null;
            e4.printStackTrace();
            if (fragment != null) {
                this.a.put(i, fragment);
            }
            new StringBuilder("getItem--position=").append(i).append("|fragment=").append(fragment);
            return fragment;
        }
        if (fragment != null) {
            this.a.put(i, fragment);
        }
        new StringBuilder("getItem--position=").append(i).append("|fragment=").append(fragment);
        return fragment;
    }

    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
        FragmentTransaction beginTransaction = this.c.beginTransaction();
        beginTransaction.remove((Fragment) obj);
        beginTransaction.commit();
    }

    public final int getCount() {
        return this.b == null ? 0 : this.b.size();
    }

    public final int getItemPosition(Object obj) {
        return Tabs.TAB_CREATE_REACH_MAX_COUNT;
    }
}
