package com.xunlei.common.member.c.b;

import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.p;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

// compiled from: UserSelectRecommendAvatarTask.java
public final class b extends p {
    private final String a;
    private int b;

    static /* synthetic */ int a(b bVar, int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 402) {
            return XZBDevice.Upload;
        }
        return (i == 406 || i != 500) ? XLErrorCode.UNKNOWN_ERROR : XLErrorCode.UNKNOWN_ERROR;
    }

    public b(m mVar) {
        super(mVar);
        this.b = -1;
    }

    public final void b(int i) {
        this.b = i;
    }

    public final boolean b() {
        boolean z = true;
        String str = "------------swb3ef7ab26eea1cd9fe3dd7ba4ef5aaswb";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a("sessionid", h().getStringValue(USERINFOKEY.SessionID), str));
        stringBuffer.append(a("userid", h().getStringValue(USERINFOKEY.UserID), str));
        stringBuffer.append(a("businesstype", String.valueOf(g().d()), str));
        stringBuffer.append(a("recommendid", String.valueOf(this.b), str));
        stringBuffer.append(str + "--\r\n");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(stringBuffer.toString().getBytes(), 0, stringBuffer.toString().getBytes().length);
            byteArrayOutputStream.flush();
            XLLog.v(getClass().getSimpleName(), new StringBuilder("select recommend avatar buffer = ").append(stringBuffer).toString());
            Header[] headerArr = new Header[]{new BasicHeader("Content-Type", new StringBuilder("multipart/form-data;boundary=").append(str).toString())};
            z = g().k();
            z.post(g().h(), "http://img.user.kanimg.com/back/36063/avatar/select_recommend_avatar", headerArr, byteArrayOutputStream.toByteArray(), new AnonymousClass_1(this));
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            c(XLErrorCode.PACKAGE_ERROR);
            return z;
        }
    }

    public final boolean a(XLOnUserListener xLOnUserListener, Bundle bundle) {
        return (xLOnUserListener == null || bundle == null) ? false : xLOnUserListener.onUserSelectRecommendAvatar(bundle.getInt(Constants.KEY_ERROR_CODE), i(), bundle.getString("errorDesc"), j());
    }

    private void c(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_ERROR_CODE, i);
        bundle.putString("errorDesc", XLErrorCode.getErrorDesc(i));
        g().a((p) this, bundle);
    }

    private static StringBuffer a(String str, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str3 + "\r\n");
        stringBuffer.append("Content-Disposition:form-data;name=\"").append(str).append("\"\r\n\r\n");
        stringBuffer.append(str2).append("\r\n");
        return stringBuffer;
    }

    private static int g(int i) {
        if (i == 200) {
            return 0;
        }
        if (i == 402) {
            return XZBDevice.Upload;
        }
        return (i == 406 || i != 500) ? XLErrorCode.UNKNOWN_ERROR : XLErrorCode.UNKNOWN_ERROR;
    }
}
