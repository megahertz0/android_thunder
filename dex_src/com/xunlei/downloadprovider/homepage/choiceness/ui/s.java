package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.downloadprovider.homepage.interest.a.i;
import com.xunlei.downloadprovider.homepage.interest.a.j;
import com.xunlei.downloadprovider.search.b.b;

// compiled from: HomeChoicenessFragment.java
final class s implements b<i> {
    final /* synthetic */ HomeChoicenessFragment a;

    s(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final /* synthetic */ void a(Object obj) {
        i iVar = (i) obj;
        if (iVar != null) {
            j t = this.a.t;
            t.a.edit().putBoolean("key_is_recommend", iVar.b).apply();
            if (iVar.a.size() > 0) {
                this.a.t.c();
            }
            if (iVar.a.size() > 0 || !iVar.b) {
                if (this.a.r) {
                    this.a.c();
                }
            } else if (!this.a.a.isEmpty() && this.a.t.a() < 10) {
                HomeChoicenessFragment.v(this.a);
            }
            this.a.q = true;
        }
    }
}
