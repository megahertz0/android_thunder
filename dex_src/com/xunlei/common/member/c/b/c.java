package com.xunlei.common.member.c.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.p;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

// compiled from: UserSetAvatarTask.java
public final class c extends p {
    private Bitmap a;
    private String b;

    static /* synthetic */ int a(c cVar, int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 402) {
            return XZBDevice.Upload;
        }
        return (i == 406 || i != 500) ? XLErrorCode.UNKNOWN_ERROR : XLErrorCode.UNKNOWN_ERROR;
    }

    public c(m mVar) {
        super(mVar);
        this.a = null;
        this.b = "http://img.user.kanimg.com/back/36063/avatar/set_avatar?width=%d&height=%d";
    }

    public final boolean b() {
        return c();
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        return (xLOnUserListener == null || bundle == null) ? false : xLOnUserListener.onUserSetAvatar(bundle.getInt(Constants.KEY_ERROR_CODE), i(), bundle.getString("errorDesc"), j());
    }

    public final void a(Bitmap bitmap) {
        this.a = bitmap;
    }

    private void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private boolean c() {
        if (this.a == null) {
            XLLog.v(getClass().getSimpleName(), "upload avatar file invalid picture!");
            b((int) XLErrorCode.INVALID_BITMAP);
        } else {
            String str = "------------swb3ef7ab26eea1cd9fe3dd7ba4ef5aaswb";
            StringBuffer stringBuffer = new StringBuffer();
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(5242880);
            stringBuffer.append(a("sessionid", h().getStringValue(USERINFOKEY.SessionID), str));
            stringBuffer.append(a("userid", h().getStringValue(USERINFOKEY.UserID), str));
            stringBuffer.append(a("businesstype", String.valueOf(g().d()), str));
            if (a(byteArrayOutputStream, stringBuffer.toString().getBytes(), 0, stringBuffer.toString().getBytes().length)) {
                String str2;
                boolean a;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(str + "\r\n");
                stringBuffer2.append("Content-Disposition:form-data;filename=avatar.jpeg;name=\"").append("upload_file").append("\"\r\n");
                stringBuffer2.append("Content-Type:image/jpeg\r\n\r\n");
                if (a(byteArrayOutputStream, stringBuffer2.toString().getBytes(), 0, stringBuffer2.toString().getBytes().length)) {
                    this.a.compress(CompressFormat.JPEG, R.styleable.AppCompatTheme_buttonStyle, byteArrayOutputStream);
                    str2 = "\r\n";
                    a = a(byteArrayOutputStream, str2.getBytes(), 0, str2.getBytes().length);
                } else {
                    a = false;
                }
                if (a) {
                    str2 = str + "--\r\n";
                    if (a(byteArrayOutputStream, str2.getBytes(), 0, str2.getBytes().length)) {
                        XLLog.v(getClass().getSimpleName(), new StringBuilder("set avatar buffer = ").append(stringBuffer).toString());
                        Header[] headerArr = new Header[]{new BasicHeader("Content-Type", new StringBuilder("multipart/form-data;boundary=").append(str).toString())};
                        g().k().post(g().h(), String.format(this.b, new Object[]{Integer.valueOf(this.a.getWidth()), Integer.valueOf(this.a.getHeight())}), headerArr, byteArrayOutputStream.toByteArray(), new AnonymousClass_1(this));
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        XLLog.v(getClass().getSimpleName(), "write stream buffer end boundary error.");
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        b((int) XLErrorCode.PACKAGE_ERROR);
                    }
                } else {
                    XLLog.v(getClass().getSimpleName(), "write stream buffer bitmap error.");
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                    b((int) XLErrorCode.PACKAGE_ERROR);
                }
            } else {
                XLLog.v(getClass().getSimpleName(), "write stream buffer param error.");
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
                b((int) XLErrorCode.PACKAGE_ERROR);
            }
        }
        return false;
    }

    private static StringBuffer a(String str, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str3 + "\r\n");
        stringBuffer.append("Content-Disposition:form-data;name=\"").append(str).append("\"\r\n\r\n");
        stringBuffer.append(str2).append("\r\n");
        return stringBuffer;
    }

    private boolean a(ByteArrayOutputStream byteArrayOutputStream, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2 + "\r\n");
        stringBuffer.append("Content-Disposition:form-data;filename=avatar.jpeg;name=\"").append(str).append("\"\r\n");
        stringBuffer.append("Content-Type:image/jpeg\r\n\r\n");
        if (!a(byteArrayOutputStream, stringBuffer.toString().getBytes(), 0, stringBuffer.toString().getBytes().length)) {
            return false;
        }
        this.a.compress(CompressFormat.JPEG, R.styleable.AppCompatTheme_buttonStyle, byteArrayOutputStream);
        String str3 = "\r\n";
        return a(byteArrayOutputStream, str3.getBytes(), 0, str3.getBytes().length);
    }

    private static boolean a(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, int i, int i2) {
        boolean z = false;
        try {
            byteArrayOutputStream.write(bArr, 0, i2);
            byteArrayOutputStream.flush();
            z = true;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return z;
        }
    }

    private static int c(int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 402) {
            return XZBDevice.Upload;
        }
        return (i == 406 || i != 500) ? XLErrorCode.UNKNOWN_ERROR : XLErrorCode.UNKNOWN_ERROR;
    }
}
