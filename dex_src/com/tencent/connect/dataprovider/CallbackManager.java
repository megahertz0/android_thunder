package com.tencent.connect.dataprovider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import com.tencent.connect.dataprovider.DataType.TextAndMediaPath;
import com.tencent.connect.dataprovider.DataType.TextOnly;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.lang.ref.WeakReference;

// compiled from: ProGuard
public final class CallbackManager {
    private WeakReference<Context> a;
    private Uri b;
    private String c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    private int h;

    public CallbackManager(Activity activity) {
        this.g = false;
        this.a = new WeakReference(activity.getApplicationContext());
        Intent intent = activity.getIntent();
        if (intent != null) {
            this.b = intent.getData();
            this.c = intent.getStringExtra(Constants.SRC_PACKAGE_NAME);
            this.d = intent.getStringExtra(Constants.SRC_ACTIVITY_CLASS_NAME);
            this.e = intent.getStringExtra(Constants.SRC_ACTIVITY_ACTION);
            this.h = intent.getIntExtra(Constants.REQUEST_TYPE, 0);
            this.f = intent.getStringExtra(Constants.APPID);
            if (this.b != null && this.d != null) {
                this.g = true;
            }
        }
    }

    public final boolean isCallFromTencentApp() {
        return this.g;
    }

    private int a(Bundle bundle) {
        if (!this.g) {
            return Tabs.TAB_CREATE_REACH_MAX_COUNT;
        }
        if (this.a == null) {
            return AppbarJsBridge.Code_Java_Exception;
        }
        Intent intent = new Intent();
        intent.setClassName(this.c, this.d);
        intent.setAction(this.e);
        bundle.putString(Constants.APPID, this.f);
        intent.putExtras(bundle);
        intent.setFlags(268435456);
        ((Context) this.a.get()).startActivity(intent);
        return 0;
    }

    public final int getRequestDateTypeFlag() {
        return this.h;
    }

    public final boolean isSupportType(int i) {
        return (getRequestDateTypeFlag() & i) != 0;
    }

    public final int sendTextAndImagePath(String str, String str2) {
        if (!isSupportType(1)) {
            return -1;
        }
        int a = a(str2);
        if (a != 0) {
            return a;
        }
        Parcelable textAndMediaPath = new TextAndMediaPath(str, str2);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA_TYPE, 1);
        bundle.putParcelable(Constants.CONTENT_DATA, textAndMediaPath);
        return a(bundle);
    }

    public final int sendTextAndVideoPath(String str, String str2) {
        if (!isSupportType(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            return -1;
        }
        int a = a(str2);
        if (a != 0) {
            return a;
        }
        Parcelable textAndMediaPath = new TextAndMediaPath(str, str2);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA_TYPE, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        bundle.putParcelable(Constants.CONTENT_DATA, textAndMediaPath);
        return a(bundle);
    }

    public final int sendTextOnly(String str) {
        if (!isSupportType(XZBDevice.DOWNLOAD_LIST_ALL)) {
            return -1;
        }
        Parcelable textOnly = new TextOnly(str);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA_TYPE, XZBDevice.DOWNLOAD_LIST_ALL);
        bundle.putParcelable(Constants.CONTENT_DATA, textOnly);
        return a(bundle);
    }

    private int a(String str) {
        if (str == null) {
            return ErrorCode.PathIsNull;
        }
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.startsWith("http://")) {
            return 0;
        }
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return ErrorCode.SdCardNotExist;
        }
        if (!toLowerCase.startsWith(Environment.getExternalStorageDirectory().toString().toLowerCase())) {
            return AppbarJsBridge.AUTHORIZE_FAIL;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return ErrorCode.FileNotExist;
        }
        long length = file.length();
        if (length == 0) {
            return ErrorCode.FileIsEmpty;
        }
        return length > 1073741824 ? ErrCode.ERR_BAN : 0;
    }
}
