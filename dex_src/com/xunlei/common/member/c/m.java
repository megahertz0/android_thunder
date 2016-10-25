package com.xunlei.common.member.c;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.member.XLAvatarItem;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLHspeedCapacity;
import com.xunlei.common.member.XLLixianCapacity;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.SocketTimeoutException;
import java.util.List;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserQRCodeLoginTask.java
public class m extends p {
    private static final int e = 1024;
    private static final int f = 1025;
    private static final int g = 1026;
    private XLOnUserListener a;
    private long b;
    private long c;
    private String d;
    private int h;
    private int i;
    private String j;
    private boolean k;
    private final String l;
    private String m;

    public m(com.xunlei.common.member.a.m mVar) {
        super(mVar);
        this.a = new XLOnUserListener() {
            public final boolean onUserVerifyedCode(int i, Object obj, String str, int i2) {
                return false;
            }

            public final boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3) {
                return false;
            }

            public final boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
                return false;
            }

            public final boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
                return false;
            }

            public final boolean onUserSuspended(int i) {
                return false;
            }

            public final boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
                if (i == 0) {
                    m.a(m.this, 0);
                } else {
                    m.a(m.this, (int) XLErrorCode.QR_LOGIN_ERROR);
                }
                return false;
            }

            public final boolean onUserResumed(int i) {
                return false;
            }

            public final boolean onUserQRCodeLoginAuth(int i, Object obj, String str, int i2) {
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

            public final boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2) {
                return false;
            }

            public final boolean onUserPing(int i, Object obj, String str, int i2) {
                return false;
            }

            public final boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
                return false;
            }

            public final boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
                return false;
            }

            public final boolean onUserInfoCatched(int i, List<USERINFOKEY> list, XLUserInfo xLUserInfo, Object obj, int i2) {
                if (i == 0) {
                    m.a(m.this, 0);
                } else {
                    m.a(m.this, (int) XLErrorCode.QR_LOGIN_ERROR);
                }
                return false;
            }

            public final boolean onUserGetCityInfo(int i, JSONObject jSONObject, Object obj, String str, int i2) {
                return false;
            }

            public final boolean onUserSetInfo(int i, Object obj, String str, int i2) {
                return false;
            }

            public final boolean onUserGetQRCode(int i, String str, byte[] bArr, Object obj, String str2, int i2) {
                return false;
            }

            public final boolean onUserActivated(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
                return false;
            }

            public final boolean onLixianCatched(int i, XLUserInfo xLUserInfo, XLLixianCapacity xLLixianCapacity, Object obj, int i2) {
                return false;
            }

            public final boolean onHighSpeedCatched(int i, XLUserInfo xLUserInfo, XLHspeedCapacity xLHspeedCapacity, Object obj, int i2) {
                return false;
            }

            public final boolean onUserAqSendMessage(int i, String str, String str2, Object obj, int i2) {
                return false;
            }

            public final boolean onUserAqBindMobile(int i, String str, String str2, Object obj, int i2) {
                return false;
            }
        };
        this.b = 120000;
        this.c = 0;
        this.d = a.d;
        this.h = 0;
        this.i = 0;
        this.j = a.d;
        this.k = false;
        this.m = m.class.getSimpleName();
    }

    public final boolean b() {
        XLLog.v(this.m, new StringBuilder("execute step = ").append(this.h).toString());
        if (1024 == this.h) {
            o();
        } else if (1025 == this.h) {
            o();
        }
        return true;
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "UserQRCodeLoginTask") {
            return false;
        }
        return xLOnUserListener.onUserQRCodeLogin(bundle.getInt(Constants.KEY_ERROR_CODE), h(), i(), bundle.getString("errorDesc"), j());
    }

    public final void a(String str, int i) {
        this.d = str;
        if (i > 0) {
            this.b = ((long) (i * 60)) * 1000;
        }
        this.h = 1024;
        this.c = System.currentTimeMillis();
    }

    private String d() {
        return String.format("https://qrcode.xunlei.com/sub_message?ch=%s&business=%d", new Object[]{this.d, Integer.valueOf(g().d())});
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "UserQRCodeLoginTask");
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
        g().x();
    }

    private void o() {
        if (this.k) {
            XLLog.v(this.m, "task is uninist,so go back!");
            g().x();
            return;
        }
        String format = String.format("https://qrcode.xunlei.com/sub_message?ch=%s&business=%d", new Object[]{this.d, Integer.valueOf(g().d())});
        XLLog.v(this.m, new StringBuilder("sub message address = ").append(format).toString());
        g().k().get(g().h(), format, null, new BaseHttpClientListener() {
            public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                XLLog.v(m.this.m, new StringBuilder("sub message return code = ").append(i).toString());
                if (i == 200) {
                    JSONObject jSONObject;
                    String str = new String(bArr);
                    XLLog.v(m.this.m, new StringBuilder("sub message body = ").append(str).toString());
                    try {
                        jSONObject = new JSONObject(str);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        jSONObject = null;
                    }
                    if (jSONObject == null) {
                        m.a(m.this, (int) XLErrorCode.QR_LOGIN_ERROR);
                        return;
                    } else if (1024 == m.this.h) {
                        m.this.h = f;
                        m.this.c = System.currentTimeMillis();
                        if ("scan".equals(jSONObject.optString(JsInterface.KEY_ACTION))) {
                            m.this.g().n().post(new AnonymousClass_1(this));
                            return;
                        } else {
                            m.a(m.this, (int) XLErrorCode.QR_LOGIN_ERROR);
                            return;
                        }
                    } else if (1025 == m.this.h) {
                        m.this.h = g;
                        if (jSONObject.optInt("result") != 200) {
                            m.a(m.this, (int) XLErrorCode.QR_LOGIN_ERROR);
                            return;
                        }
                        m.this.i = jSONObject.optInt("userid");
                        m.this.j = jSONObject.optString("clientsessionid");
                        m.this.h().a(USERINFOKEY.UserID, Integer.valueOf(m.this.i));
                        m.this.h().a(USERINFOKEY.SessionID, m.this.j);
                        m.this.g().a(m.this.i, m.this.j, m.this.g().d(), 1, m.this, (Object) "xl-inner-login-with-session", true);
                        return;
                    } else {
                        return;
                    }
                }
                m.a(m.this, (int) XLErrorCode.QR_LOGIN_ERROR);
            }

            public final void onFailure(Throwable th, byte[] bArr) {
                if (!(th instanceof SocketTimeoutException) || System.currentTimeMillis() - m.this.c >= m.this.b) {
                    XLLog.v(m.this.m, new StringBuilder("qr login error msg = ").append(th.getMessage()).toString());
                    m.a(m.this, (int) XLErrorCode.QR_LOGIN_ERROR);
                    return;
                }
                m.this.g().n().post(new AnonymousClass_2(this));
            }
        });
    }

    private void p() {
        g().a(null, this.a, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, true, (Object) "xl-inner-get-user-info");
    }

    private void q() {
        g().a(this.i, this.j, g().d(), 1, this.a, (Object) "xl-inner-login-with-session", true);
    }

    public final void c() {
        this.k = true;
    }

    static /* synthetic */ void a(m mVar, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "UserQRCodeLoginTask");
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        mVar.g().a((p) mVar, bundle);
        mVar.g().x();
    }
}
