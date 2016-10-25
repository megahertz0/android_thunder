package com.xunlei.common.member.c.c;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLHttpHeader;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.act.XLQQLoginActivity;
import com.xunlei.common.member.act.XLSinaLoginActivity;
import com.xunlei.common.member.b.h;
import com.xunlei.common.member.c.p;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.apache.http.Header;

// compiled from: UserGetOtherAccountInfoTask.java
public final class c extends p {
    private int a;
    private int b;
    private int c;
    private int d;
    private IWXAPI e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private h l;
    private XLThirdUserInfo m;
    private int n;

    public c(m mVar) {
        super(mVar);
        this.d = 1;
        this.e = null;
        this.f = a.d;
        this.g = a.d;
        this.h = a.d;
        this.i = a.d;
        this.l = null;
        this.m = null;
        this.n = -1;
    }

    public final boolean b() {
        int i;
        if (this.d == 1) {
            if (g().q()) {
                Intent intent;
                if (this.n == 3) {
                    this.e = WXAPIFactory.createWXAPI(g().h(), this.f, false);
                    this.e.registerApp(this.f);
                    if (this.e.isWXAppInstalled()) {
                        BaseReq req = new Req();
                        req.scope = "snsapi_userinfo";
                        req.transaction = new StringBuilder("xl_sdk_get_access_code#").append(j()).toString();
                        req.state = String.valueOf(j());
                        i = !this.e.sendReq(req) ? XLErrorCode.WX_REQ_FAIL : 0;
                    } else {
                        i = XLErrorCode.WX_NOT_INSTALLED;
                    }
                    if (i != 0) {
                        b(i);
                    }
                }
                if (this.n == 2) {
                    intent = new Intent(m.a().h(), XLSinaLoginActivity.class);
                    intent.putExtra("sina_task", j());
                    intent.putExtra("sina_app_id", this.f);
                    intent.addFlags(268435456);
                    g().h().startActivity(intent);
                }
                if (this.n == 5) {
                    intent = new Intent(m.a().h(), XLQQLoginActivity.class);
                    intent.putExtra("qq_app_id", this.f);
                    intent.putExtra("qq_task_id", j());
                    intent.addFlags(268435456);
                    g().h().startActivity(intent);
                    XLLog.v(getClass().getSimpleName(), "start XLQQLoginActivity");
                }
            } else {
                XLLog.v(getClass().getSimpleName(), "user is not login!");
                b((int) XLErrorCode.OPERATION_INVALID);
            }
        } else if (this.d == 2) {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2;
            if (this.n == 3) {
                stringBuffer2 = new StringBuffer();
                stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.g).append(this.f).append(R.styleable.AppCompatTheme_buttonStyle);
                stringBuffer.append("code=").append(this.g).append("&appid=").append(this.f).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
            } else if (this.n == 2) {
                stringBuffer2 = new StringBuffer();
                stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.h).append(this.i).append(this.f).append(R.styleable.AppCompatTheme_buttonStyle);
                stringBuffer.append("access_token=").append(this.i).append("&appid=").append(this.f).append("&third_uid=").append(this.h).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
            } else if (this.n == 5) {
                stringBuffer2 = new StringBuffer();
                stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.l.b).append(this.f).append(this.l.a).append(R.styleable.AppCompatTheme_buttonStyle);
                stringBuffer.append("access_token=").append(this.l.b).append("&appid=").append(this.f).append("&openid=").append(this.l.a).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
            }
            String toString = stringBuffer.toString();
            StringBuffer stringBuffer3 = new StringBuffer();
            StringBuffer append = stringBuffer3.append("module=get_third_info&session_id=").append(h().getStringValue(USERINFOKEY.SessionID)).append("&uid=").append(h().getIntValue(USERINFOKEY.UserID)).append("&business_id=").append(g().d()).append("&third_type=");
            i = -1;
            if (this.n == 3) {
                i = R.styleable.Toolbar_navigationContentDescription;
            } else if (this.n == 2) {
                i = 1;
            } else if (this.n == 5) {
                i = XZBDevice.Delete;
            } else if (this.n == 4) {
                i = XZBDevice.DOWNLOAD_LIST_ALL;
            } else if (this.n == 1) {
                i = -1;
            }
            append.append(i).append(com.alipay.sdk.sys.a.b).append(toString);
            XLLog.v(getClass().getSimpleName(), new StringBuilder("request body = ").append(stringBuffer3.toString()).toString());
            XLHttpHeader xLHttpHeader = new XLHttpHeader("Content-Type", "application/x-www-form-urlencoded");
            g().k().post(g().h(), "http://login.i.xunlei.com/thirdlogin4/entrance.php", new Header[]{xLHttpHeader}, stringBuffer3.toString().getBytes(), new AnonymousClass_1(this));
        }
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null) {
            return false;
        }
        return xLOnUserListener.onUserGetOtherAccountInfo(bundle.getInt(Constants.KEY_ERROR_CODE), this.n, this.m, i(), bundle.getString("errorDesc"), j());
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private int c() {
        if (this.n == 3) {
            return R.styleable.Toolbar_navigationContentDescription;
        }
        if (this.n == 2) {
            return 1;
        }
        if (this.n == 5) {
            return XZBDevice.Delete;
        }
        if (this.n == 4) {
            return 4;
        }
        return this.n == 1 ? -1 : -1;
    }

    private void d() {
        int i = 0;
        this.e = WXAPIFactory.createWXAPI(g().h(), this.f, false);
        this.e.registerApp(this.f);
        if (this.e.isWXAppInstalled()) {
            BaseReq req = new Req();
            req.scope = "snsapi_userinfo";
            req.transaction = new StringBuilder("xl_sdk_get_access_code#").append(j()).toString();
            req.state = String.valueOf(j());
            if (!this.e.sendReq(req)) {
                i = XLErrorCode.WX_REQ_FAIL;
            }
        } else {
            i = XLErrorCode.WX_NOT_INSTALLED;
        }
        if (i != 0) {
            b(i);
        }
    }

    private void o() {
        Intent intent = new Intent(m.a().h(), XLQQLoginActivity.class);
        intent.putExtra("qq_app_id", this.f);
        intent.putExtra("qq_task_id", j());
        intent.addFlags(268435456);
        g().h().startActivity(intent);
        XLLog.v(getClass().getSimpleName(), "start XLQQLoginActivity");
    }

    private void p() {
        Intent intent = new Intent(m.a().h(), XLSinaLoginActivity.class);
        intent.putExtra("sina_task", j());
        intent.putExtra("sina_app_id", this.f);
        intent.addFlags(268435456);
        g().h().startActivity(intent);
    }

    private String q() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2;
        if (this.n == 3) {
            stringBuffer2 = new StringBuffer();
            stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.g).append(this.f).append(R.styleable.AppCompatTheme_buttonStyle);
            stringBuffer.append("code=").append(this.g).append("&appid=").append(this.f).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
        } else if (this.n == 2) {
            stringBuffer2 = new StringBuffer();
            stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.h).append(this.i).append(this.f).append(R.styleable.AppCompatTheme_buttonStyle);
            stringBuffer.append("access_token=").append(this.i).append("&appid=").append(this.f).append("&third_uid=").append(this.h).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
        } else if (this.n == 5) {
            stringBuffer2 = new StringBuffer();
            stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.l.b).append(this.f).append(this.l.a).append(R.styleable.AppCompatTheme_buttonStyle);
            stringBuffer.append("access_token=").append(this.l.b).append("&appid=").append(this.f).append("&openid=").append(this.l.a).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
        }
        return stringBuffer.toString();
    }

    private void a(String str) {
        int i = XZBDevice.DOWNLOAD_LIST_ALL;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append = stringBuffer.append("module=get_third_info&session_id=").append(h().getStringValue(USERINFOKEY.SessionID)).append("&uid=").append(h().getIntValue(USERINFOKEY.UserID)).append("&business_id=").append(g().d()).append("&third_type=");
        if (this.n == 3) {
            i = R.styleable.Toolbar_navigationContentDescription;
        } else if (this.n == 2) {
            i = 1;
        } else if (this.n == 5) {
            i = XZBDevice.Delete;
        } else if (this.n != 4) {
            if (this.n == 1) {
                i = -1;
            } else {
                i = -1;
            }
        }
        append.append(i).append(com.alipay.sdk.sys.a.b).append(str);
        XLLog.v(getClass().getSimpleName(), new StringBuilder("request body = ").append(stringBuffer.toString()).toString());
        XLHttpHeader xLHttpHeader = new XLHttpHeader("Content-Type", "application/x-www-form-urlencoded");
        g().k().post(g().h(), "http://login.i.xunlei.com/thirdlogin4/entrance.php", new Header[]{xLHttpHeader}, stringBuffer.toString().getBytes(), new AnonymousClass_1(this));
    }

    public final void a(int i, String str) {
        XLLog.e("UserWxLoginTask", "recieve wx code.");
        if (i == 0) {
            this.g = str;
            this.d = 2;
            m.a().n().postDelayed(new AnonymousClass_2(this), 0);
        } else {
            b(i);
        }
        g().b(j());
    }

    public final void a(int i, h hVar) {
        if (i == 0 && hVar.a()) {
            this.l = hVar;
            this.d = 2;
            m.a().n().postDelayed(new AnonymousClass_3(this), 0);
            return;
        }
        b(i);
        this.d = 3;
        g().b(j());
    }

    public final void a(int i, String str, String str2, String str3, String str4) {
        if (i == 0) {
            this.h = str;
            this.i = str2;
            this.d = 2;
            m.a().n().postDelayed(new AnonymousClass_4(this), 0);
            return;
        }
        b(i);
        this.d = 3;
        g().b(j());
    }

    public final void b(int i, String str) {
        this.n = i;
        this.f = str;
    }

    private static int c(int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 600) {
            return XZBDevice.Upload;
        }
        if (i == 502) {
            return R.styleable.Toolbar_maxButtonHeight;
        }
        return i == 603 ? XLErrorCode.NOT_BIND_ERROR : XLErrorCode.BIND_ERROR;
    }

    static /* synthetic */ int a(c cVar, int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 600) {
            return XZBDevice.Upload;
        }
        if (i == 502) {
            return R.styleable.Toolbar_maxButtonHeight;
        }
        return i == 603 ? XLErrorCode.NOT_BIND_ERROR : XLErrorCode.BIND_ERROR;
    }
}
