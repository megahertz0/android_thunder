package com.xunlei.tdlive.user;

import android.app.Activity;
import android.content.Context;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLWxPayParam;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.util.ac;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;
import java.util.Iterator;

// compiled from: PayHelper.java
public class e implements XLOnPayListener {
    private static e a;
    private HashSet<XLOnPayListener> b;
    private boolean c;
    private boolean d;

    static {
        a = null;
    }

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    private e() {
        this.b = new HashSet();
    }

    public void a(Context context, boolean z, boolean z2) {
        this.c = z;
        this.d = z2;
        if (!this.c) {
            XLPayUtil.getInstance().init(context, R.styleable.AppCompatTheme_colorControlNormal, ac.d(), ac.h(), "e91aa83000867e97cbe6dd7f9944b5cf");
        } else if (!this.d) {
            XLPayUtil.getInstance().init(context, R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, ac.d(), ac.h(), "34a062aaa22f906fca4fefe9fb3a3021");
        }
    }

    public void b() {
        if (!this.c || !this.d) {
            XLPayUtil.getInstance().unInit();
        }
    }

    public void a(XLOnPayListener xLOnPayListener) {
        if (xLOnPayListener != null) {
            this.b.add(xLOnPayListener);
        }
        if (!this.b.isEmpty()) {
            XLPayUtil.getInstance().attachListener(this);
        }
    }

    public void b(XLOnPayListener xLOnPayListener) {
        if (xLOnPayListener != null) {
            this.b.remove(xLOnPayListener);
        }
        if (this.b.isEmpty()) {
            XLPayUtil.getInstance().detachListener(this);
        }
    }

    public void a(int i, int i2, String str, String str2, int i3) {
        Iterator it;
        if (i == 0) {
            it = this.b.iterator();
            while (it.hasNext()) {
                ((XLOnPayListener) it.next()).onWxPay(i2, str, str2, i3);
            }
        } else if (i == 1) {
            it = this.b.iterator();
            while (it.hasNext()) {
                ((XLOnPayListener) it.next()).onAliPay(i2, str, str2, i3);
            }
        } else if (i == 2) {
            it = this.b.iterator();
            while (it.hasNext()) {
                ((XLOnPayListener) it.next()).onNbPay(i2, str, str2, i3);
            }
        } else if (i == 3) {
            Iterator it2 = this.b.iterator();
            while (it2.hasNext()) {
                it2.next();
            }
        }
    }

    public void a(Activity activity, int i, int i2, int i3, String str, int i4, String str2) {
        if (this.c && this.d) {
            XLLiveSDK.getInstance(activity).host().pay(activity, i2, i3, str, i4, str2);
        } else if (i2 == 0) {
            XLWxPayParam xLWxPayParam = new XLWxPayParam();
            xLWxPayParam.mAppId = this.c ? "wx3e6556568beeebdd" : "wx18eada9ea7fbf76c";
            xLWxPayParam.mVasType = i3;
            xLWxPayParam.mSource = str;
            xLWxPayParam.mMonth = i4;
            xLWxPayParam.mUserId = i;
            XLPayUtil.getInstance().userWxPay(xLWxPayParam, str2);
        } else if (i2 == 1) {
            XLAlipayParam xLAlipayParam = new XLAlipayParam();
            xLAlipayParam.mVasType = i3;
            xLAlipayParam.mSource = str;
            xLAlipayParam.mMonth = i4;
            xLAlipayParam.mUserId = i;
            xLAlipayParam.mActivity = activity;
            XLPayUtil.getInstance().userAliPay(xLAlipayParam, str2);
        }
    }

    public void onWxPay(int i, String str, Object obj, int i2) {
        a(0, i, str, obj.toString(), i2);
    }

    public void onAliPay(int i, String str, Object obj, int i2) {
        a(1, i, str, obj.toString(), i2);
    }

    public void onNbPay(int i, String str, Object obj, int i2) {
        a(XZBDevice.DOWNLOAD_LIST_RECYCLE, i, str, obj.toString(), i2);
    }

    public void onGetPrice(int i, String str, Object obj, int i2, String str2) {
    }

    public void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
    }
}
