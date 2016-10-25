package com.xunlei.common.member.c;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLAvatarItem;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLHspeedCapacity;
import com.xunlei.common.member.XLLixianCapacity;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLQRCodeAuthHandler;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.act.XLQRCodeLoginAuthActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.List;
import org.json.JSONObject;

// compiled from: UserQRCodeLoginAuthTask.java
public class l extends p {
    private static final int e = 1024;
    private static final int f = 1025;
    private static final int g = 1026;
    private String a;
    private XLQRCodeAuthHandler b;
    private String c;
    private a d;
    private int h;
    private int i;
    private long j;

    // compiled from: UserQRCodeLoginAuthTask.java
    class a implements XLOnUserListener {
        private a() {
        }

        public final boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            l.a(l.this, i);
            return false;
        }

        public final boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
            l.a(l.this, i);
            return false;
        }

        public final boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            l.a(l.this, i);
            return false;
        }

        public final boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
            l.a(l.this, i);
            return false;
        }

        public final boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
            return false;
        }

        public final boolean onUserInfoCatched(int i, List<USERINFOKEY> list, XLUserInfo xLUserInfo, Object obj, int i2) {
            return false;
        }

        public final boolean onUserGetCityInfo(int i, JSONObject jSONObject, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserSetInfo(int i, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserActivated(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserPing(int i, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3) {
            return false;
        }

        public final boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserVerifyedCode(int i, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onHighSpeedCatched(int i, XLUserInfo xLUserInfo, XLHspeedCapacity xLHspeedCapacity, Object obj, int i2) {
            return false;
        }

        public final boolean onLixianCatched(int i, XLUserInfo xLUserInfo, XLLixianCapacity xLLixianCapacity, Object obj, int i2) {
            return false;
        }

        public final boolean onUserSuspended(int i) {
            return false;
        }

        public final boolean onUserResumed(int i) {
            return false;
        }

        public final boolean onUserQRCodeLoginAuth(int i, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserGetQRCode(int i, String str, byte[] bArr, Object obj, String str2, int i2) {
            return false;
        }

        public final boolean onUserQRCodeLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserGetRecommendAvatars(int i, XLAvatarItem[] xLAvatarItemArr, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserSelectRecommendAvatar(int i, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserSetAvatar(int i, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserGetBindedOtherAccount(int i, XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr, Object obj, String str, int i2) {
            return false;
        }

        public final boolean onUserBindedOtherAccount(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
            return false;
        }

        public final boolean onUserGetOtherAccountInfo(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
            return false;
        }

        public final boolean onUserUnBindeOtherAccount(int i, int i2, Object obj, String str, int i3) {
            return false;
        }

        public final boolean onUserAqSendMessage(int i, String str, String str2, Object obj, int i2) {
            return false;
        }

        public final boolean onUserAqBindMobile(int i, String str, String str2, Object obj, int i2) {
            return false;
        }
    }

    public l(m mVar) {
        super(mVar);
        this.a = l.class.getSimpleName();
        this.b = null;
        this.c = com.umeng.a.d;
        this.d = null;
        this.h = 0;
        this.i = 2;
        this.j = 0;
    }

    public final void a(XLQRCodeAuthHandler xLQRCodeAuthHandler, String str) {
        this.b = xLQRCodeAuthHandler;
        this.c = str;
        int handleLoginTimeOut = this.b.handleLoginTimeOut();
        if (handleLoginTimeOut != 0) {
            this.i = handleLoginTimeOut;
        }
        this.j = System.currentTimeMillis();
        this.h = 1024;
    }

    public final boolean b() {
        XLLog.v(this.a, new StringBuilder("execute step = ").append(this.h).toString());
        if (1024 == this.h) {
            if (g().q()) {
                this.h = 1025;
                g().n().postDelayed(new Runnable() {
                    public final void run() {
                        l.this.b();
                    }
                }, 100);
            } else if (this.b.handleLoginWindow()) {
                this.d = new a();
                g().a(this.d);
                this.h = 1026;
            } else {
                b(XLErrorCode.QR_LOGIN_AUTH_HANDLE_LOGIN_WIN_ERROR);
            }
        } else if (1026 == this.h) {
            d();
            this.h = 1025;
            g().n().postDelayed(new Runnable() {
                public final void run() {
                    l.this.b();
                }
            }, 100);
        } else if (1025 == this.h) {
            XLLog.v(this.a, new StringBuilder("startAuthWebActivity url = ").append(this.c).toString());
            Intent intent = new Intent(g().h(), XLQRCodeLoginAuthActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("xl_task_id", j());
            intent.putExtra("xl_qr_auth_url", this.c);
            g().h().startActivity(intent);
        }
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        return (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "UserQRCodeLoginAuthTask") ? false : xLOnUserListener.onUserQRCodeLoginAuth(bundle.getInt(Constants.KEY_ERROR_CODE), i(), bundle.getString("errorDesc"), j());
    }

    public final void c() {
        d();
    }

    public final void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "UserQRCodeLoginAuthTask");
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
        g().b(j());
        g().w();
    }

    private void d() {
        if (this.d != null) {
            g().b(this.d);
            this.d = null;
        }
    }

    private void o() {
        XLLog.v(this.a, new StringBuilder("startAuthWebActivity url = ").append(this.c).toString());
        Intent intent = new Intent(g().h(), XLQRCodeLoginAuthActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("xl_task_id", j());
        intent.putExtra("xl_qr_auth_url", this.c);
        g().h().startActivity(intent);
    }

    private void c(int i) {
        if (i != 0) {
            return;
        }
        if (System.currentTimeMillis() - this.j < ((long) (this.i * 60)) * 1000) {
            b();
        } else {
            b(XLErrorCode.QR_LOGIN_OP_TIMEOUT_ERROR);
        }
    }

    static /* synthetic */ void a(l lVar, int i) {
        if (i != 0) {
            return;
        }
        if (System.currentTimeMillis() - lVar.j < ((long) (lVar.i * 60)) * 1000) {
            lVar.b();
        } else {
            lVar.b(XLErrorCode.QR_LOGIN_OP_TIMEOUT_ERROR);
        }
    }
}
