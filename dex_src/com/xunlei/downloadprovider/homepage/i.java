package com.xunlei.downloadprovider.homepage;

import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.choiceness.ui.HomeChoicenessFragment;
import com.xunlei.downloadprovider.homepage.recommend.SummaryMoviesListFragment;
import com.xunlei.downloadprovider.homepage.relax.RelaxListFragment;
import com.xunlei.tdlive.XLLiveFragment;
import com.xunlei.tdlive.XLLiveHelpers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: HomeTabController.java
public final class i {
    private static i h;
    List<String> a;
    public Class<?>[] b;
    String[] c;
    private List<Class<?>> d;
    private List<String> e;
    private Map<String, Integer> f;
    private int g;

    static {
        h = new i();
    }

    public static i a() {
        return h;
    }

    private i() {
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.a = new ArrayList();
        this.f = new HashMap();
        this.g = -1;
        a(HomeChoicenessFragment.class, "\u7cbe\u9009", "choiceness");
        a(SummaryMoviesListFragment.class, "\u77ed\u7247", "short_movie");
        if (XLLiveHelpers.showXLLiveTable(BrothersApplication.a)) {
            a(XLLiveFragment.class, "\u76f4\u64ad", "livestream");
        }
        a(RelaxListFragment.class, "\u8da3\u56fe", "fun_pic");
        this.b = new Class[(this.g + 1)];
        this.c = new String[(this.g + 1)];
        this.d.toArray(this.b);
        this.e.toArray(this.c);
    }

    private void a(Class<?> cls, String str, String str2) {
        this.d.add(cls);
        this.e.add(str);
        this.a.add(str2);
        Map map = this.f;
        int i = this.g + 1;
        this.g = i;
        map.put(str, Integer.valueOf(i));
    }

    public final int a(String str) {
        Integer num = (Integer) this.f.get(str);
        return num == null ? -1 : num.intValue();
    }
}
