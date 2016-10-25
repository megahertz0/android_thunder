package com.xunlei.downloadprovider.commonview.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;

@Deprecated
public class XLOneBtnDialogActivity extends Activity {
    private q a;
    private int b;
    private Intent c;

    class a implements OnClickListener {
        a() {
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    class b implements OnDismissListener {
        b() {
        }

        public void onDismiss(DialogInterface dialogInterface) {
            XLOneBtnDialogActivity.this.finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = getIntent();
        this.b = this.c.getIntExtra("dlg_type", 0);
        this.a = new q(this);
        switch (this.b) {
            case 406428:
                this.a.a(this.c.getStringExtra("vip_expire_title"));
                this.a.b(new a());
                this.a.setOnDismissListener(new b());
            case 406429:
                this.a.a(this.c.getStringExtra("vip_logout_title"));
                this.a.b(new a());
                this.a.setOnDismissListener(new p(this));
            default:
                break;
        }
    }

    protected void onResume() {
        super.onResume();
        this.a.show();
    }
}
