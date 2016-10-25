package com.xunlei.tdlive.play.view;

import android.os.Bundle;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.play.a.a;

public abstract class BaseMvpActivity extends BaseActivity {
    protected abstract a a();

    protected abstract void a(Bundle bundle);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(bundle);
        a().a(bundle);
    }

    protected void onDestroy() {
        super.onDestroy();
        a().a();
    }

    protected void onResume() {
        super.onResume();
        a().b();
    }

    protected void onPause() {
        super.onPause();
        a().c();
    }

    protected void onStop() {
        super.onStop();
        a().e();
    }

    protected void onStart() {
        super.onStart();
        a().d();
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        a().h();
    }
}
