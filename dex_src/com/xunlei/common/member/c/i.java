package com.xunlei.common.member.c;

import android.os.Bundle;
import anet.channel.strategy.dispatch.a;
import com.taobao.accs.common.Constants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.b;
import com.xunlei.common.member.a.l;
import com.xunlei.common.member.a.m;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UserPingTask.java
public final class i extends p {
    private static int a = 1;
    private static int b = 2;
    private static int c = 3;
    private static int f;
    private int d;
    private l e;

    static {
        f = 0;
    }

    public i(m mVar) {
        super(mVar);
        this.d = 1;
        this.e = null;
    }

    public final void b(int i) {
        this.d = 1;
    }

    private void a(l lVar) {
        if (lVar == null) {
            this.e = h();
        } else {
            this.e = lVar;
        }
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        if (xLOnUserListener == null || bundle == null || bundle.getString(JsInterface.KEY_ACTION) != "userPingTask") {
            return false;
        }
        if (bundle.getInt(JsInterface.FUNPLAY_AD_TRPE) == 1) {
            int i = bundle.getInt(Constants.KEY_ERROR_CODE);
            if (i == 0) {
                return xLOnUserListener.onUserResumed(0);
            }
            if (i != 4 && i != 5) {
                return xLOnUserListener.onUserSuspended(0);
            }
            boolean onUserLogout = xLOnUserListener.onUserLogout(i, h(), i(), j());
            g().a(false, 0);
            return onUserLogout;
        } else if (bundle.getInt(JsInterface.FUNPLAY_AD_TRPE) != 2) {
            return bundle.getInt(JsInterface.FUNPLAY_AD_TRPE) == 3 ? xLOnUserListener.onUserPing(bundle.getInt(Constants.KEY_ERROR_CODE), i(), bundle.getString("errorDesc"), j()) : false;
        } else {
            return xLOnUserListener.onUserActivated(bundle.getInt(Constants.KEY_ERROR_CODE), h(), i(), bundle.getString("errorDesc"), j());
        }
    }

    private void g(int i) {
        if (this.d == 1) {
            int i2 = f;
            if (this.d == 1) {
                if (i == 0) {
                    f = 1;
                    XLLog.v("UserPingTask", "user login state resume!");
                } else if (i == 4 || i == 5) {
                    f = 0;
                } else {
                    XLLog.v("UserPingTask", "user login state suspended!");
                    f = 2;
                }
            }
            if (i2 == f) {
                return;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(JsInterface.KEY_ACTION, "userPingTask");
        bundle.putInt(JsInterface.FUNPLAY_AD_TRPE, this.d);
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private boolean h(int i) {
        if (this.d != 1) {
            return false;
        }
        if (i == 0) {
            f = 1;
            XLLog.v("UserPingTask", "user login state resume!");
            return true;
        } else if (i == 4 || i == 5) {
            f = 0;
            return true;
        } else {
            XLLog.v("UserPingTask", "user login state suspended!");
            f = 2;
            return true;
        }
    }

    public final void a() {
        super.a();
    }

    public static void c() {
    }

    public final boolean b() {
        int i = XZBDevice.DOWNLOAD_LIST_ALL;
        if (this.d == 2 || g().q()) {
            d(a.b);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("protocolVersion", R.styleable.AppCompatTheme_spinnerStyle);
                jSONObject.put("sequenceNo", j());
                jSONObject.put(a.PLATFORM_VERSION, 1);
                jSONObject.put("peerID", k());
                jSONObject.put("businessType", g().d());
                jSONObject.put("clientVersion", g().e());
                jSONObject.put("isCompressed", 0);
                jSONObject.put("cmdID", XZBDevice.Success);
                jSONObject.put("userID", h().getLongValue(USERINFOKEY.UserID));
                jSONObject.put("sessionID", h().getStringValue(USERINFOKEY.SessionID));
                if (this.d != 1) {
                    if (this.d == 2) {
                        i = XZBDevice.Wait;
                    } else if (this.d == 3) {
                        i = XZBDevice.Fail;
                    }
                }
                String toString = jSONObject.toString();
                XLLog.v("UserPingTask", new StringBuilder("user ping task content = ").append(toString).toString());
                g().j().a(toString.getBytes(), i, new b() {
                    public final void a(String str) {
                        XLLog.v("UserPingTask", str);
                        try {
                            JSONObject jSONObject = new JSONObject(str.toString());
                            int i = jSONObject.getInt(Constants.KEY_ERROR_CODE);
                            if (i == 0) {
                                long longValue;
                                int optInt = jSONObject.optInt("userID");
                                if (i.this.d == 2) {
                                    longValue = i.this.e.getLongValue(USERINFOKEY.UserID);
                                } else {
                                    longValue = i.this.h().getLongValue(USERINFOKEY.UserID);
                                }
                                if (((long) optInt) != longValue) {
                                    i.this.g(XLErrorCode.USER_NO_MATCH);
                                    i.this.d(a.c);
                                    return;
                                }
                                i = jSONObject.optInt("shouldKick");
                                if (i == 1) {
                                    i.this.g(XZBDevice.DOWNLOAD_LIST_ALL);
                                    XLLog.v("UserPingTask", "user kick out.");
                                    i.this.d(a.c);
                                    return;
                                } else if (i == 2) {
                                    i.this.g(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                                    XLLog.v("UserPingTask", "session id time out.");
                                    i.this.d(a.c);
                                    return;
                                } else {
                                    if (i.this.d == 2) {
                                        i.this.h().a(i.this.e);
                                    }
                                    if (jSONObject.optInt(com.alipay.sdk.authjs.a.h) == 1) {
                                        new e(i.this.g()).b();
                                    }
                                    if (i.this.d != 1) {
                                        i.this.g(0);
                                    } else if (f == 2) {
                                        i.this.g(0);
                                    } else {
                                        f = 1;
                                    }
                                }
                            } else {
                                XLLog.e("UserPingTask", new StringBuilder("keep alive error = ").append(i).toString());
                                i.this.g(XLErrorCode.PROTOCOL_ERROR);
                            }
                            i.this.d(a.c);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            XLLog.e("UserPingTask", new StringBuilder("keep alive error = ").append(e.getMessage()).toString());
                            i.this.g(XLErrorCode.UNPACKAGE_ERROR);
                            i.this.d(a.c);
                        }
                    }

                    public final void a(Throwable th) {
                        XLLog.e("UserPingTask", new StringBuilder("keep alive error = ").append(th.getMessage()).toString());
                        i.this.g(XLErrorCode.SOCKET_ERROR);
                    }
                }, j());
                return true;
            } catch (JSONException e) {
                XLLog.e("UserPingTask", new StringBuilder("pack param error = ").append(e.getMessage()).toString());
                g(XLErrorCode.PACKAGE_ERROR);
                return false;
            }
        }
        g(XLErrorCode.USER_NO_LOGIN);
        return false;
    }
}
