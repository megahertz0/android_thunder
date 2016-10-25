package com.xunlei.downloadprovider.homepage.choiceness.a;

import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.tdlive.XLLiveHelpers;
import com.xunlei.xllib.R;
import java.util.HashSet;
import java.util.Set;

// compiled from: ChoicenessNetworkDataFilter.java
public final class g {
    public static g b;
    Set<Integer> a;

    public g() {
        this.a = new HashSet();
        if (!XLLiveHelpers.showXLLiveTable(BrothersApplication.a)) {
            this.a.add(Integer.valueOf(R.styleable.Toolbar_maxButtonHeight));
        }
    }

    public final boolean a(a aVar) {
        return this.a.contains(Integer.valueOf(aVar.b));
    }
}
