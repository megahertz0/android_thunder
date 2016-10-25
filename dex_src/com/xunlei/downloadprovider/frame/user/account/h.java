package com.xunlei.downloadprovider.frame.user.account;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.c.a;
import com.nostra13.universalimageloader.core.d;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.a.b;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.android.spdy.SpdyAgent;

// compiled from: SetAccountPortraitHelper.java
public final class h {
    File a;
    final Activity b;
    public String c;

    public h(Activity activity) {
        this.b = activity;
        File file;
        if (k.d()) {
            file = new File(Environment.getExternalStorageDirectory() + "/xunlei/picture");
            if (!file.exists()) {
                file.mkdir();
            }
            this.a = new File(file, b());
            return;
        }
        file = new File(k.b() + "/xunlei/picture");
        if (!file.exists()) {
            file.mkdir();
        }
        this.a = new File(file, b());
    }

    public final void a(String str) {
        Context context = this.b;
        b iVar = new i(this, str);
        CharSequence string = context.getString(2131232955);
        CharSequence string2 = context.getString(2131232954);
        Dialog a = a.a(context, 2130968628);
        a.findViewById(R.id.cancel).setOnClickListener(new e(a, iVar));
        TextView textView = (TextView) a.findViewById(2131755330);
        textView.setText(string);
        textView.setOnClickListener(new f(a, iVar));
        textView = (TextView) a.findViewById(2131755331);
        textView.setText(string2);
        textView.setOnClickListener(new g(a, iVar));
        a.show();
    }

    public final void a(int i, int i2, Intent intent, String str) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (i2 == -1) {
                    a(Uri.fromFile(this.a));
                } else if (i2 != 0) {
                    k.a(str, this.c, "cancel");
                }
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (i2 == -1) {
                    if (intent != null) {
                        a(intent.getData());
                    }
                } else if (i2 == 0) {
                    k.a(str, this.c, "cancel");
                }
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                if (i2 == -1) {
                    if (intent != null) {
                        Bundle extras = intent.getExtras();
                        if (extras != null) {
                            LoginHelper.a().a((Bitmap) extras.getParcelable(SocializeConstants.JSON_DATA), this.c);
                            if (com.xunlei.xllib.a.b.a(this.b)) {
                                XLToast.b(this.b, XLToastType.XLTOAST_TYPE_ALARM, "\u5934\u50cf\u4e0a\u4f20\u4e2d");
                            }
                            LoginHelper.a().s();
                        }
                    }
                } else if (i2 == 0) {
                    k.a(str, this.c, "cancel");
                }
            default:
                break;
        }
    }

    private void a(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", XLErrorCode.OAUTH_PARAM_ERROR);
        intent.putExtra("outputY", XLErrorCode.OAUTH_PARAM_ERROR);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", true);
        this.b.startActivityForResult(intent, XZBDevice.DOWNLOAD_LIST_FAILED);
    }

    private static String b() {
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss").format(date) + ".png";
    }

    public final void a(String str, boolean z, ImageView imageView, boolean z2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim())) {
            String replace = str.replace("/50x50", "/300x300");
            a aVar = new a();
            aVar.a = 2130838392;
            aVar.h = true;
            aVar.i = true;
            d.a().a(replace, imageView, aVar.b(), new j(this, imageView, z, z2));
        }
    }

    public static void a() {
        d a = d.a();
        a.b();
        a.b.o.a();
        a = d.a();
        a.b();
        a.b.n.b();
    }
}
