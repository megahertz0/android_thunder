package com.xunlei.downloadprovider.homepage.interest.ui;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.interest.a.j;
import com.xunlei.downloadprovider.search.b.b;
import java.util.List;

// compiled from: InterestPickerActivity.java
final class f implements b<Boolean> {
    final /* synthetic */ List a;
    final /* synthetic */ e b;

    f(e eVar, List list) {
        this.b = eVar;
        this.a = list;
    }

    public final /* synthetic */ void a(Object obj) {
        Boolean bool = (Boolean) obj;
        InterestPickerActivity.c(this.b.a);
        if (bool.booleanValue()) {
            new j().c();
            ChoicenessReporter.a(this.a);
            this.b.a.finish();
            return;
        }
        XLToast.b(this.b.a, XLToastType.XLTOAST_TYPE_ALARM, this.b.a.getString(R.string.interest_submit_fail_text));
    }
}
