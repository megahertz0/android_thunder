package com.sina.weibo.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import com.taobao.accs.data.Message;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WeiboAppManager {
    private static final String SDK_INT_FILE_NAME = "weibo_for_sdk.json";
    private static final String TAG;
    private static final String WEIBO_IDENTITY_ACTION = "com.sina.weibo.action.sdkidentity";
    private static final Uri WEIBO_NAME_URI;
    private static WeiboAppManager sInstance;
    private Context mContext;

    public static class WeiboInfo {
        private String mPackageName;
        private int mSupportApi;

        private void setPackageName(String str) {
            this.mPackageName = str;
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        private void setSupportApi(int i) {
            this.mSupportApi = i;
        }

        public int getSupportApi() {
            return this.mSupportApi;
        }

        public boolean isLegal() {
            return !TextUtils.isEmpty(this.mPackageName) && this.mSupportApi > 0;
        }

        public String toString() {
            return new StringBuilder("WeiboInfo: PackageName = ").append(this.mPackageName).append(", supportApi = ").append(this.mSupportApi).toString();
        }
    }

    static {
        TAG = WeiboAppManager.class.getName();
        WEIBO_NAME_URI = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    }

    private WeiboAppManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static synchronized WeiboAppManager getInstance(Context context) {
        WeiboAppManager weiboAppManager;
        synchronized (WeiboAppManager.class) {
            if (sInstance == null) {
                sInstance = new WeiboAppManager(context);
            }
            weiboAppManager = sInstance;
        }
        return weiboAppManager;
    }

    public synchronized WeiboInfo getWeiboInfo() {
        return queryWeiboInfoInternal(this.mContext);
    }

    private WeiboInfo queryWeiboInfoInternal(Context context) {
        Object obj = 1;
        WeiboInfo queryWeiboInfoByProvider = queryWeiboInfoByProvider(context);
        WeiboInfo queryWeiboInfoByAsset = queryWeiboInfoByAsset(context);
        if (queryWeiboInfoByProvider != null) {
            int i = 1;
        } else {
            Object obj2 = null;
        }
        if (queryWeiboInfoByAsset == null) {
            obj = null;
        }
        if (obj2 != null && obj != null) {
            return queryWeiboInfoByProvider.getSupportApi() >= queryWeiboInfoByAsset.getSupportApi() ? queryWeiboInfoByProvider : queryWeiboInfoByAsset;
        } else {
            if (obj2 == null) {
                return obj != null ? queryWeiboInfoByAsset : null;
            } else {
                return queryWeiboInfoByProvider;
            }
        }
    }

    private WeiboInfo queryWeiboInfoByProvider(Context context) {
        Exception e;
        try {
            Cursor query = context.getContentResolver().query(WEIBO_NAME_URI, null, null, null, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            try {
                int columnIndex = query.getColumnIndex("support_api");
                int columnIndex2 = query.getColumnIndex("package");
                if (query.moveToFirst()) {
                    try {
                        columnIndex = Integer.parseInt(query.getString(columnIndex));
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                        columnIndex = -1;
                    }
                    String string = query.getString(columnIndex2);
                    if (!TextUtils.isEmpty(string) && ApiUtils.validateWeiboSign(context, string)) {
                        WeiboInfo weiboInfo = new WeiboInfo();
                        weiboInfo.setPackageName(string);
                        weiboInfo.setSupportApi(columnIndex);
                        if (query == null) {
                            return weiboInfo;
                        }
                        query.close();
                        return weiboInfo;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    LogUtil.e(TAG, e.getMessage());
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (query != null) {
                        query.close();
                    }
                    throw th2;
                }
                return null;
            }
            return null;
        } catch (Exception e4) {
            e = e4;
            query = null;
            LogUtil.e(TAG, e.getMessage());
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th2 = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th2;
        }
    }

    private WeiboInfo queryWeiboInfoByAsset(Context context) {
        Intent intent = new Intent(WEIBO_IDENTITY_ACTION);
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        WeiboInfo weiboInfo = null;
        for (ResolveInfo resolveInfo : queryIntentServices) {
            if (resolveInfo.serviceInfo != null && resolveInfo.serviceInfo.applicationInfo != null && !TextUtils.isEmpty(resolveInfo.serviceInfo.applicationInfo.packageName)) {
                WeiboInfo parseWeiboInfoByAsset = parseWeiboInfoByAsset(resolveInfo.serviceInfo.applicationInfo.packageName);
                if (parseWeiboInfoByAsset != null) {
                    if (weiboInfo == null) {
                        weiboInfo = parseWeiboInfoByAsset;
                    } else if (weiboInfo.getSupportApi() < parseWeiboInfoByAsset.getSupportApi()) {
                        weiboInfo = parseWeiboInfoByAsset;
                    }
                }
            }
        }
        return weiboInfo;
    }

    public WeiboInfo parseWeiboInfoByAsset(String str) {
        IOException e;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] bArr = new byte[4096];
            InputStream open = this.mContext.createPackageContext(str, XZBDevice.DOWNLOAD_LIST_RECYCLE).getAssets().open(SDK_INT_FILE_NAME);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    int read = open.read(bArr, 0, Message.FLAG_ERR);
                    if (read == -1) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read));
                }
                if (!TextUtils.isEmpty(stringBuilder.toString()) && ApiUtils.validateWeiboSign(this.mContext, str)) {
                    int optInt = new JSONObject(stringBuilder.toString()).optInt("support_api", -1);
                    WeiboInfo weiboInfo = new WeiboInfo();
                    weiboInfo.setPackageName(str);
                    weiboInfo.setSupportApi(optInt);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e2) {
                            LogUtil.e(TAG, e2.getMessage());
                        }
                    }
                    return weiboInfo;
                } else if (open == null) {
                    return null;
                } else {
                    try {
                        open.close();
                        return null;
                    } catch (IOException e3) {
                        LogUtil.e(TAG, e3.getMessage());
                        return null;
                    }
                }
            } catch (NameNotFoundException e4) {
                NameNotFoundException e5 = e4;
            } catch (IOException e6) {
                e3 = e6;
            } catch (JSONException e7) {
                JSONException e8 = e7;
            } catch (Exception e9) {
                Exception e10 = e9;
            }
        } catch (NameNotFoundException e11) {
            e5 = e11;
            open = null;
            LogUtil.e(TAG, e5.getMessage());
            if (open == null) {
                return null;
            }
            try {
                open.close();
                return null;
            } catch (IOException e32) {
                LogUtil.e(TAG, e32.getMessage());
                return null;
            }
        } catch (IOException e12) {
            e32 = e12;
            open = null;
            LogUtil.e(TAG, e32.getMessage());
            if (open == null) {
                return null;
            }
            try {
                open.close();
                return null;
            } catch (IOException e322) {
                LogUtil.e(TAG, e322.getMessage());
                return null;
            }
        } catch (JSONException e13) {
            e8 = e13;
            open = null;
            LogUtil.e(TAG, e8.getMessage());
            if (open == null) {
                return null;
            }
            try {
                open.close();
                return null;
            } catch (IOException e3222) {
                LogUtil.e(TAG, e3222.getMessage());
                return null;
            }
        } catch (Exception e14) {
            e10 = e14;
            open = null;
            LogUtil.e(TAG, e10.getMessage());
            if (open == null) {
                return null;
            }
            try {
                open.close();
                return null;
            } catch (IOException e32222) {
                LogUtil.e(TAG, e32222.getMessage());
                return null;
            }
        } catch (Throwable th2) {
            open = null;
            th = th2;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }
}
