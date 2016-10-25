package com.xunlei.downloadprovider.member.register.ui;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import com.taobao.accs.common.Constants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.register.XLRegisterListener;
import com.xunlei.common.register.XLRegisterUtil;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.member.register.b;
import com.xunlei.downloadprovider.member.register.b.a;
import com.xunlei.downloadprovider.member.register.view.e;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import org.android.agoo.message.MessageService;

// compiled from: MobileSetupActivity.java
final class f implements XLRegisterListener {
    final /* synthetic */ MobileSetupActivity a;

    f(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final boolean onPhoneRegister(int i, String str, int i2, int i3, String str2) {
        new StringBuilder("Phone Login. onPhoneRegister() errorCode=").append(i).append(" msg=").append(str).append(" userId=").append(i3).append(" sessionID=").append(str2);
        boolean z = MobileSetupActivity.b(this.a) == 1;
        switch (i) {
            case Impl.STATUS_SUCCESS:
                MobileSetupActivity.a(this.a, i3);
                MobileSetupActivity.a(this.a, str2);
                this.a.a(true);
                MobileSetupActivity.i(this.a);
                if (!z) {
                    b.b(MobileSetupActivity.j(this.a));
                }
                break;
            case Constants.COMMAND_PING:
                MobileSetupActivity.a(this.a, i3);
                MobileSetupActivity.a(this.a, str2);
                this.a.a(false);
                break;
            default:
                this.a.b(a.a(i));
                break;
        }
        if (i == 600 || i == 601) {
            MobileSetupActivity.c(this.a).setText(com.umeng.a.d);
            MobileSetupActivity.c(this.a, MobileSetupActivity.c(this.a));
        }
        if (!(i == 200 || i == 201)) {
            b.a(i);
        }
        return false;
    }

    public final boolean onSendMessage(int i, String str, int i2, int i3, String str2) {
        new StringBuilder("onSendMessage() errorCode=").append(i).append(" msg=").append(str);
        this.a.f();
        StatReporter.reportMobilePhoneRegisterVertyCode(MobileSetupActivity.b(this.a) == 1 ? "login" : MiPushClient.COMMAND_REGISTER, i);
        switch (i) {
            case Impl.STATUS_SUCCESS:
                MobileSetupActivity.k(this.a);
                this.a.h();
                if (MobileSetupActivity.l(this.a) != null && MobileSetupActivity.l(this.a).isShowing()) {
                    this.a.a("\u8bf7\u7ee7\u7eed\u8f93\u5165\u77ed\u4fe1\u9a8c\u8bc1\u7801\uff01");
                }
                MobileSetupActivity.m(this.a);
                if (i == 200) {
                    if (MobileSetupActivity.b(this.a) == 2) {
                        b.b(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                    } else {
                        b.a(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                    }
                } else if (MobileSetupActivity.b(this.a) == 2) {
                    b.b(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                } else {
                    b.a(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                }
                return false;
            case XLErrorCode.OAUTH_APP_NOT_EXIST:
                MobileSetupActivity.m(this.a);
                this.a.b(a.a(i));
                if (i == 200) {
                    if (MobileSetupActivity.b(this.a) == 2) {
                        b.a(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                    } else {
                        b.b(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                    }
                } else if (MobileSetupActivity.b(this.a) == 2) {
                    b.a(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                } else {
                    b.b(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                }
                return false;
            case 409:
                MobileSetupActivity.n(this.a);
                MobileSetupActivity.b(this.a, str2);
                XLRegisterUtil.getInstance().getVerifyCodeByType(str2);
                if (i == 200) {
                    if (MobileSetupActivity.b(this.a) == 2) {
                        b.b(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                    } else {
                        b.a(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                    }
                } else if (MobileSetupActivity.b(this.a) == 2) {
                    b.b(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                } else {
                    b.a(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                }
                return false;
            case Impl.STATUS_LX_VIP_ERROR_START:
                if (MobileSetupActivity.l(this.a) != null) {
                    e l = MobileSetupActivity.l(this.a);
                    com.xunlei.downloadprovider.member.register.view.a.a(l.getContext(), this.a.getResources().getString(2131231677));
                    l.a();
                    XLRegisterUtil.getInstance().getVerifyCodeByType(str2);
                }
                if (i == 200) {
                    if (MobileSetupActivity.b(this.a) == 2) {
                        b.a(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                    } else {
                        b.b(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                    }
                } else if (MobileSetupActivity.b(this.a) == 2) {
                    b.a(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                } else {
                    b.b(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                }
                return false;
            default:
                MobileSetupActivity.m(this.a);
                MobileSetupActivity.i();
                String a = a.a(i);
                if (a.equals("\u7f51\u7edc\u4e0d\u7ed9\u529b\uff0c\u8bf7\u91cd\u8bd5")) {
                    this.a.b(a);
                    return false;
                }
                this.a.b(a);
                if (i == 200) {
                    if (MobileSetupActivity.b(this.a) == 2) {
                        b.a(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                    } else {
                        b.b(MessageService.MSG_DB_NOTIFY_REACHED, com.umeng.a.d);
                    }
                } else if (MobileSetupActivity.b(this.a) == 2) {
                    b.a(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                } else {
                    b.b(MessageService.MSG_DB_READY_REPORT, String.valueOf(i));
                }
                return false;
        }
    }

    public final boolean onGetVerifyCode(int i, String str, int i2, byte[] bArr, String str2, String str3, String str4, String str5) {
        new StringBuilder("onGetVerifyCode() errorCode=").append(i).append(" msg=").append(str);
        if (i != 200) {
            MobileSetupActivity.o(this.a);
        } else if (bArr == null) {
            MobileSetupActivity.o(this.a);
        } else {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            if (decodeByteArray == null) {
                MobileSetupActivity.o(this.a);
            } else {
                decodeByteArray.compress(CompressFormat.JPEG, R.styleable.AppCompatTheme_buttonBarStyle, new ByteArrayOutputStream());
                if (MobileSetupActivity.l(this.a) != null) {
                    e l = MobileSetupActivity.l(this.a);
                    l.b.setImageBitmap(decodeByteArray);
                    l.a();
                    l.c.requestFocus();
                    l.d.setVisibility(XZBDevice.Wait);
                }
                MobileSetupActivity.c(this.a, str4);
                MobileSetupActivity.d(this.a, str5);
            }
        }
        return false;
    }

    public final boolean onCheckBind(int i, String str, int i2, int i3) {
        new StringBuilder("onCheckBind() errorCode=").append(i).append(" msg=").append(str).append(" binded=").append(i3);
        switch (i) {
            case Impl.STATUS_SUCCESS:
                if (i3 == 1) {
                    MobileSetupActivity.p(this.a);
                } else {
                    MobileSetupActivity.e(this.a, com.umeng.a.d);
                }
                break;
            default:
                this.a.b(a.a(i));
                break;
        }
        return false;
    }

    public final boolean onCheckPassWordStrength(int i, String str, int i2, int i3) {
        return false;
    }

    public final boolean onMobileVerifyCodeAccept(String str, int i) {
        return false;
    }

    public final boolean onPhoneRegAndLogin(int i, String str, int i2, int i3, String str2) {
        Object obj = 1;
        new StringBuilder("Phone Fast Register. onPhoneRegAndLogin() errorCode=").append(i).append(" msg=").append(str).append(" sessionID=").append(str2);
        if (MobileSetupActivity.b(this.a) != 1) {
            boolean z = false;
        }
        this.a.f();
        switch (i) {
            case Impl.STATUS_SUCCESS:
                MobileSetupActivity.a(this.a, i3);
                MobileSetupActivity.a(this.a, str2);
                this.a.a(false);
                if (!z) {
                    b.b(MobileSetupActivity.j(this.a));
                }
                MobileSetupActivity.q(this.a);
                this.a.finish();
                break;
            case Constants.COMMAND_PING:
                MobileSetupActivity.a(this.a, i3);
                MobileSetupActivity.a(this.a, str2);
                this.a.a(false);
                break;
            case XLErrorCode.OAUTH_CID_INVALID:
                MobileSetupActivity.p(this.a);
                break;
            default:
                this.a.b(a.a(i));
                break;
        }
        if (i == 600 || i == 601) {
            MobileSetupActivity.c(this.a).setText(com.umeng.a.d);
            MobileSetupActivity.c(this.a, MobileSetupActivity.c(this.a));
        }
        if (!(i == 200 || i == 201)) {
            String str3 = "phone_register_fail";
            g a = g.a("android_phone_register", str3, str3);
            a.b("failtype", (long) i);
            b.a(a);
        }
        return false;
    }

    public final boolean onCheckNeedVerifyCode(int i, String str, int i2, int i3, String str2) {
        return false;
    }

    public final boolean onEmailRegister(int i, String str, int i2, int i3, String str2) {
        return false;
    }

    public final boolean onOldUserNameRegister(int i, String str, int i2, int i3, String str2) {
        return false;
    }
}
