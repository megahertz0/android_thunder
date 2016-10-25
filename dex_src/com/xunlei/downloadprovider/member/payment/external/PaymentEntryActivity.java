package com.xunlei.downloadprovider.member.payment.external;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.c;
import com.xunlei.downloadprovider.member.payment.ui.PayActivity;
import com.xunlei.downloadprovider.member.payment.ui.PaymentH5Activity;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.util.r.f;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class PaymentEntryActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onNewIntent(getIntent());
    }

    protected void onNewIntent(Intent intent) {
        boolean z;
        super.onNewIntent(intent);
        String g = b.g();
        f fVar = r.c().f;
        if (fVar.b == null || fVar.b.isEmpty() || g == null) {
            z = false;
        } else {
            z = fVar.b.contains(g);
        }
        if (z) {
            j.a();
            if (LoginHelper.c()) {
                intent.setClass(this, PaymentH5Activity.class);
                startActivity(intent);
                finish();
                return;
            }
            intent.setClass(this, LoginActivity.class);
            intent.putExtra("SuccessDestination", PaymentEntryActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        intent.setClass(this, PayActivity.class);
        startActivity(intent);
        finish();
    }

    public static void a(Context context, PayEntryParam payEntryParam) {
        context.startActivity(b(context, payEntryParam));
        payEntryParam.toString();
    }

    public static Intent b(Context context, PayEntryParam payEntryParam) {
        Object obj = 1;
        if (payEntryParam == null || payEntryParam.b == null) {
            throw new NullPointerException("PayEntryParam should not has null property!");
        }
        if (TextUtils.isEmpty(payEntryParam.d)) {
            Object obj2;
            String str;
            int d = j.a().d();
            PayFrom payFrom = payEntryParam.a;
            int i = payEntryParam.c;
            if (d > 1) {
                d = 1;
            } else {
                obj2 = null;
            }
            String str2 = a.d;
            switch (AnonymousClass_1.a[payFrom.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    str = obj2 != null ? "v_an_shoulei_ggong_grzx_fast" : "v_an_shoulei_ggong_grzx_fast";
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (c.a(i)) {
                        str = "v_an_shoulei_push_grzx_107days";
                    } else {
                        if (i != 0) {
                            obj = null;
                        }
                        str = obj != null ? "v_an_shoulei_push_grzx_0day" : c.b(i) ? "v_an_shoulei_push_grzx_0107days" : a.d;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    str = obj2 != null ? "v_an_shoulei_ggong_tx_xf" : "v_an_shoulei_ggong_tx_kt";
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    str = str2;
                    break;
                case R.styleable.Toolbar_contentInsetEnd:
                    str = "v_an_shoulei_hytq_lx_ykj";
                    break;
                case R.styleable.Toolbar_contentInsetLeft:
                    if (c.a(i)) {
                        str = "v_an_shoulei_push_lx_0301days";
                    } else {
                        if (i != 0) {
                            obj = null;
                        }
                        str = obj != null ? "v_an_shoulei_push_lx_0day" : c.b(i) ? "v_an_shoulei_push_lx_103days" : a.d;
                    }
                    break;
                case XZBDevice.Wait:
                    str = "v_an_shoulei_hytq_yb_ykj";
                    break;
                case XZBDevice.Pause:
                    if (c.a(i)) {
                        str = "v_an_shoulei_push_yb_0301days";
                    } else {
                        if (i != 0) {
                            obj = null;
                        }
                        str = obj != null ? "v_an_shoulei_push_yb_0day" : c.b(i) ? "v_an_shoulei_push_yb_103days" : a.d;
                    }
                    break;
                case XZBDevice.Stop:
                    str = obj2 != null ? "v_an_shoulei_hytq_fj_zy_xf" : "v_an_shoulei_hytq_fj_zy_kt";
                    break;
                case XZBDevice.Success:
                    str = obj2 != null ? "v_an_shoulei_hytq_fj_xz_xf" : "v_an_shoulei_hytq_fj_xz_kt";
                    break;
                case XZBDevice.Fail:
                    str = "v_an_shoulei_hytq_xzlb_top";
                    break;
                case XZBDevice.Upload:
                    str = "v_an_shoulei_hytq_xzxq_js";
                    break;
                case XZBDevice.Predownload:
                    str = "v_an_shoulei_hytq_xzlb_single";
                    break;
                case XZBDevice.Delete:
                    int i2;
                    Object obj3;
                    bx bxVar = new bx();
                    str = null;
                    if (bxVar.a()) {
                        if (c.a(i)) {
                            str = "v_an_shoulei_push_xzzx_03pt";
                        } else {
                            if (i == 0) {
                                i2 = 1;
                            } else {
                                obj3 = null;
                            }
                            if (obj3 != null) {
                                str = "v_an_shoulei_push_xzzx_0pt";
                            } else if (c.b(i)) {
                                str = "v_an_shoulei_push_xzzx_4pt";
                            } else if (c.c(i)) {
                                str = "v_an_shoulei_push_xzzx_15pt";
                            }
                        }
                    }
                    if (bxVar.b()) {
                        if (c.a(i)) {
                            str = "v_an_shoulei_push_xzzx_03bj";
                        } else {
                            if (i == 0) {
                                i2 = 1;
                            } else {
                                obj3 = null;
                            }
                            if (obj3 != null) {
                                str = "v_an_shoulei_push_xzzx_0bj";
                            } else if (c.b(i)) {
                                str = "v_an_shoulei_push_xzzx_4bj";
                            } else if (c.c(i)) {
                                str = "v_an_shoulei_push_xzzx_15bj";
                            }
                        }
                    }
                    if (bxVar.d()) {
                        if (c.a(i)) {
                            str = "v_an_shoulei_push_xzzx_03sp";
                        } else {
                            if (i != 0) {
                                obj = null;
                            }
                            if (obj != null) {
                                str = "v_an_shoulei_push_xzzx_0sp";
                            } else if (c.b(i)) {
                                str = "v_an_shoulei_push_xzzx_4sp";
                            } else if (c.c(i)) {
                                str = "v_an_shoulei_push_xzzx_15sp";
                            }
                        }
                    }
                    break;
                case R.styleable.Toolbar_titleMarginBottom:
                    str = "v_an_shoulei_hytq_noti";
                    break;
                case R.styleable.Toolbar_maxButtonHeight:
                    str = "v_an_shoulei_hytq_lx_xzxq";
                    break;
                case R.styleable.Toolbar_collapseIcon:
                    str = "v_an_shoulei_hytq_gs_xzxq";
                    break;
                case R.styleable.Toolbar_collapseContentDescription:
                    str = "v_an_shoulei_hytq_kn_h5";
                    break;
                case R.styleable.Toolbar_navigationIcon:
                    str = "v_an_shoulei_hytq_kn_noti";
                    break;
                case R.styleable.Toolbar_navigationContentDescription:
                    str = "v_an_shoulei_hytq_kn_popup";
                    break;
                case R.styleable.Toolbar_logoDescription:
                    str = "v_an_shoulei_acti_hycj_zfcg";
                    break;
                default:
                    str = str2;
                    break;
            }
            payEntryParam.d = str;
        }
        if (TextUtils.isEmpty(payEntryParam.d) || "null".equalsIgnoreCase(payEntryParam.d)) {
            payEntryParam.d = "v_an_shoulei_hytq_normal";
        }
        Intent intent = new Intent(context, PaymentEntryActivity.class);
        intent.putExtra("PayEntryParam", payEntryParam);
        return intent;
    }
}
