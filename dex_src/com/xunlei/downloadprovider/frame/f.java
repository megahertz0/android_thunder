package com.xunlei.downloadprovider.frame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.TnetStatusCode;

// compiled from: FragmentPagerAdapterNormal.java
public final class f extends FragmentStatePagerAdapter {
    SparseArray<Fragment> a;
    private List<Class<? extends Fragment>> b;
    private List<Bundle> c;

    private f(FragmentManager fragmentManager, Class<?>[] clsArr) {
        super(fragmentManager);
        this.a = new SparseArray();
        this.b = new ArrayList();
        if (clsArr != null && clsArr.length > 0) {
            for (Object obj : clsArr) {
                this.b.add(obj);
            }
            this.c = null;
        }
    }

    public f(FragmentManager fragmentManager, Class<?>[] clsArr, byte b) {
        this(fragmentManager, clsArr);
    }

    public final Fragment getItem(int i) {
        Fragment fragment;
        Fragment fragment2 = (Fragment) this.a.get(i);
        if (fragment2 == null) {
            try {
                fragment2 = (Fragment) ((Class) this.b.get(i)).newInstance();
                Bundle bundle = null;
                if (this.c != null && this.c.size() > i) {
                    bundle = (Bundle) this.c.get(i);
                }
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putInt("position", i);
                fragment2.setArguments(bundle);
            } catch (InstantiationException e) {
                InstantiationException instantiationException = e;
                fragment = fragment2;
                instantiationException.printStackTrace();
                fragment2 = fragment;
            } catch (IllegalAccessException e2) {
                IllegalAccessException illegalAccessException = e2;
                fragment = fragment2;
                illegalAccessException.printStackTrace();
                fragment2 = fragment;
            }
            if (fragment2 != null) {
                this.a.put(i, fragment2);
            }
        }
        return fragment2;
    }

    public final int getCount() {
        return this.b.size();
    }

    public final int getItemPosition(Object obj) {
        return TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL;
    }
}
