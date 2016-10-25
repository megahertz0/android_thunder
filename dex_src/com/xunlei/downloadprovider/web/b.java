package com.xunlei.downloadprovider.web;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: CopyrightIntermediatePageActivity.java
final class b implements OnClickListener {
    final /* synthetic */ CopyrightIntermediatePageActivity a;

    b(CopyrightIntermediatePageActivity copyrightIntermediatePageActivity) {
        this.a = copyrightIntermediatePageActivity;
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case 2131755659:
                CopyrightIntermediatePageActivity.f(this.a).loadUrl(CopyrightIntermediatePageActivity.e(this.a));
                CopyrightIntermediatePageActivity.f(this.a).setVisibility(0);
                CopyrightIntermediatePageActivity.g(this.a).setVisibility(XZBDevice.Wait);
            case 2131755680:
                this.a.finish();
            default:
                break;
        }
    }
}
