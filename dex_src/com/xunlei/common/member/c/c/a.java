package com.xunlei.common.member.c.c;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
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

// compiled from: UserBindOtherAccountTask.java
public final class a extends p {
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
    private String l;
    private h m;
    private XLThirdUserInfo n;
    private int o;

    public a(m mVar) {
        super(mVar);
        this.d = 1;
        this.e = null;
        this.f = com.umeng.a.d;
        this.g = com.umeng.a.d;
        this.h = com.umeng.a.d;
        this.i = com.umeng.a.d;
        this.l = com.umeng.a.d;
        this.m = null;
        this.n = null;
        this.o = -1;
    }

    public final boolean b() {
        int i;
        if (this.d == 1) {
            if (g().q()) {
                Intent intent;
                if (this.o == 3) {
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
                if (this.o == 2) {
                    intent = new Intent(m.a().h(), XLSinaLoginActivity.class);
                    intent.putExtra("sina_task", j());
                    intent.putExtra("sina_app_id", this.f);
                    intent.putExtra("sina_app_redirect", this.l);
                    intent.addFlags(268435456);
                    g().h().startActivity(intent);
                }
                if (this.o == 5) {
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
            if (this.o == 3) {
                stringBuffer2 = new StringBuffer();
                stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.g).append(this.f).append(R.styleable.AppCompatTheme_buttonStyle);
                stringBuffer.append("code=").append(this.g).append("&appid=").append(this.f).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
            } else if (this.o == 2) {
                stringBuffer2 = new StringBuffer();
                stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.h).append(this.i).append(this.f).append(R.styleable.AppCompatTheme_buttonStyle);
                stringBuffer.append("access_token=").append(this.i).append("&appid=").append(this.f).append("&third_uid=").append(this.h).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
            } else if (this.o == 5) {
                stringBuffer2 = new StringBuffer();
                stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.m.b).append(this.f).append(this.m.a).append(R.styleable.AppCompatTheme_buttonStyle);
                stringBuffer.append("access_token=").append(this.m.b).append("&appid=").append(this.f).append("&openid=").append(this.m.a).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
            }
            String toString = stringBuffer.toString();
            StringBuffer stringBuffer3 = new StringBuffer();
            StringBuffer append = stringBuffer3.append("module=bind&session_id=").append(h().getStringValue(USERINFOKEY.SessionID)).append("&uid=").append(h().getIntValue(USERINFOKEY.UserID)).append("&business_id=").append(g().d()).append("&third_type=");
            i = -1;
            if (this.o == 3) {
                i = R.styleable.Toolbar_navigationContentDescription;
            } else if (this.o == 2) {
                i = 1;
            } else if (this.o == 5) {
                i = XZBDevice.Delete;
            } else if (this.o == 4) {
                i = XZBDevice.DOWNLOAD_LIST_ALL;
            } else if (this.o == 1) {
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
        return xLOnUserListener.onUserBindedOtherAccount(bundle.getInt(Constants.KEY_ERROR_CODE), this.o, this.n, i(), bundle.getString("errorDesc"), j());
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private int c() {
        if (this.o == 3) {
            return R.styleable.Toolbar_navigationContentDescription;
        }
        if (this.o == 2) {
            return 1;
        }
        if (this.o == 5) {
            return XZBDevice.Delete;
        }
        if (this.o == 4) {
            return 4;
        }
        return this.o == 1 ? -1 : -1;
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
        intent.putExtra("sina_app_redirect", this.l);
        intent.addFlags(268435456);
        g().h().startActivity(intent);
    }

    private String q() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2;
        if (this.o == 3) {
            stringBuffer2 = new StringBuffer();
            stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.g).append(this.f).append(R.styleable.AppCompatTheme_buttonStyle);
            stringBuffer.append("code=").append(this.g).append("&appid=").append(this.f).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
        } else if (this.o == 2) {
            stringBuffer2 = new StringBuffer();
            stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.h).append(this.i).append(this.f).append(R.styleable.AppCompatTheme_buttonStyle);
            stringBuffer.append("access_token=").append(this.i).append("&appid=").append(this.f).append("&third_uid=").append(this.h).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
        } else if (this.o == 5) {
            stringBuffer2 = new StringBuffer();
            stringBuffer2.append("nglei^*(352l{eltLNEGwwn").append(this.m.b).append(this.f).append(this.m.a).append(R.styleable.AppCompatTheme_buttonStyle);
            stringBuffer.append("access_token=").append(this.m.b).append("&appid=").append(this.f).append("&openid=").append(this.m.a).append("&version=100&sign=").append(MD5.encrypt(stringBuffer2.toString()));
        }
        return stringBuffer.toString();
    }

    private void a(String str) {
        int i = XZBDevice.DOWNLOAD_LIST_ALL;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append = stringBuffer.append("module=bind&session_id=").append(h().getStringValue(USERINFOKEY.SessionID)).append("&uid=").append(h().getIntValue(USERINFOKEY.UserID)).append("&business_id=").append(g().d()).append("&third_type=");
        if (this.o == 3) {
            i = R.styleable.Toolbar_navigationContentDescription;
        } else if (this.o == 2) {
            i = 1;
        } else if (this.o == 5) {
            i = XZBDevice.Delete;
        } else if (this.o != 4) {
            if (this.o == 1) {
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
            this.m = hVar;
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

    public final void a(int i, String str, String str2) {
        this.o = i;
        this.f = str;
        this.l = str2;
    }

    private static int c(int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 602) {
            return XLErrorCode.BIND_DUPLICATE_ERROR;
        }
        if (i == 600) {
            return XZBDevice.Upload;
        }
        return i == 502 ? R.styleable.Toolbar_maxButtonHeight : XLErrorCode.BIND_ERROR;
    }

    static /* synthetic */ int a(a aVar, int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 602) {
            return XLErrorCode.BIND_DUPLICATE_ERROR;
        }
        if (i == 600) {
            return XZBDevice.Upload;
        }
        return i == 502 ? R.styleable.Toolbar_maxButtonHeight : XLErrorCode.BIND_ERROR;
    }
}
