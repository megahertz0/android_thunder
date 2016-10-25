package com.taobao.accs;

import android.content.Context;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.h;
import com.umeng.a;
import java.io.Serializable;
import java.net.URL;
import java.util.Map;

// compiled from: Taobao
public final class ACCSManager {
    private static final String TAG = "ACCSManager";
    private static IACCSManager accsManager;

    // compiled from: Taobao
    public static class AccsRequest implements Serializable {
        public String businessId;
        public byte[] data;
        public String dataId;
        public URL host;
        public boolean isUnitBusiness;
        public String serviceId;
        public String tag;
        public String target;
        public String targetServiceName;
        public int timeout;
        public String userId;

        public AccsRequest(String str, String str2, byte[] bArr, String str3, String str4, URL url, String str5) {
            this.isUnitBusiness = false;
            this.userId = str;
            this.serviceId = str2;
            this.data = bArr;
            this.dataId = str3;
            this.target = str4;
            this.host = url;
            this.businessId = str5;
        }

        public AccsRequest(String str, String str2, byte[] bArr, String str3) {
            this.isUnitBusiness = false;
            this.userId = str;
            this.serviceId = str2;
            this.data = bArr;
            this.dataId = str3;
        }

        public void setTag(String str) {
            this.tag = str;
        }

        public void setTimeOut(int i) {
            this.timeout = i;
        }

        public void setIsUnitBusiness(boolean z) {
            this.isUnitBusiness = z;
        }

        public void setTarget(String str) {
            this.target = str;
        }

        public void setTargetServiceName(String str) {
            this.targetServiceName = str;
        }

        public void setHost(URL url) {
            this.host = url;
        }

        public void setBusinessId(String str) {
            this.businessId = str;
        }
    }

    private ACCSManager() {
    }

    public static void bindApp(Context context, String str, String str2, String str3, IAppReceiver iAppReceiver) {
        getManagerImpl(context).bindApp(context, str, str2, str3, iAppReceiver);
    }

    public static void bindApp(Context context, String str, String str2, IAppReceiver iAppReceiver) {
        getManagerImpl(context).bindApp(context, str, a.d, str2, iAppReceiver);
    }

    @Deprecated
    public static void unbindApp(Context context) {
        ALog.e(TAG, "unbindApp", new Object[0]);
        getManagerImpl(context).unbindApp(context);
    }

    public static void bindUser(Context context, String str) {
        getManagerImpl(context).bindUser(context, str);
    }

    public static void bindUser(Context context, String str, boolean z) {
        getManagerImpl(context).bindUser(context, str, z);
    }

    public static void unbindUser(Context context) {
        getManagerImpl(context).unbindUser(context);
    }

    public static void bindService(Context context, String str) {
        getManagerImpl(context).bindService(context, str);
    }

    public static void unbindService(Context context, String str) {
        getManagerImpl(context).unbindService(context, str);
    }

    public static String sendData(Context context, String str, String str2, byte[] bArr, String str3) {
        return getManagerImpl(context).sendData(context, str, str2, bArr, str3);
    }

    public static String sendData(Context context, String str, String str2, byte[] bArr, String str3, String str4, URL url) {
        return getManagerImpl(context).sendData(context, str, str2, bArr, str3, str4, url);
    }

    public static String sendData(Context context, String str, String str2, byte[] bArr, String str3, String str4) {
        return getManagerImpl(context).sendData(context, str, str2, bArr, str3, str4);
    }

    public static String sendData(Context context, AccsRequest accsRequest) {
        return getManagerImpl(context).sendData(context, accsRequest);
    }

    public static String sendRequest(Context context, String str, String str2, byte[] bArr, String str3, String str4, URL url) {
        return getManagerImpl(context).sendRequest(context, str, str2, bArr, str3, str4, url);
    }

    public static String sendRequest(Context context, String str, String str2, byte[] bArr, String str3) {
        return sendRequest(context, str, str2, bArr, str3, null);
    }

    public static String sendRequest(Context context, String str, String str2, byte[] bArr, String str3, String str4) {
        return getManagerImpl(context).sendRequest(context, str, str2, bArr, str3, str4);
    }

    public static String sendRequest(Context context, AccsRequest accsRequest) {
        return getManagerImpl(context).sendRequest(context, accsRequest);
    }

    public static String sendPushResponse(Context context, AccsRequest accsRequest, ExtraInfo extraInfo) {
        return getManagerImpl(context).sendPushResponse(context, accsRequest, extraInfo);
    }

    public static boolean isNetworkReachable(Context context) {
        return getManagerImpl(context).isNetworkReachable(context);
    }

    @Deprecated
    public static void setServiceListener(Context context, String str, IServiceReceiver iServiceReceiver) {
    }

    public static void forceEnableService(Context context) {
        getManagerImpl(context).forceEnableService(context);
    }

    public static void forceDisableService(Context context) {
        getManagerImpl(context).forceDisableService(context);
    }

    public static void setMode(Context context, int i) {
        getManagerImpl(context).setMode(context, i);
    }

    public static void setProxy(Context context, String str, int i) {
        getManagerImpl(context).setProxy(context, str, i);
    }

    public static void startInAppConnection(Context context, String str, String str2, String str3, IAppReceiver iAppReceiver) {
        getManagerImpl(context).startInAppConnection(context, str, str2, str3, iAppReceiver);
    }

    public static void startInAppConnection(Context context, String str, String str2, IAppReceiver iAppReceiver) {
        getManagerImpl(context).startInAppConnection(context, str, h.NAMESPACE, str2, iAppReceiver);
    }

    public static void setLoginInfoImpl(Context context, ILoginInfo iLoginInfo) {
        getManagerImpl(context).setLoginInfo(context, iLoginInfo);
    }

    public static void clearLoginInfoImpl(Context context) {
        getManagerImpl(context).clearLoginInfo(context);
    }

    public static Map<String, Boolean> getChannelState(Context context) throws Exception {
        return getManagerImpl(context).getChannelState();
    }

    public static Map<String, Boolean> forceReConnectChannel(Context context) throws Exception {
        return getManagerImpl(context).forceReConnectChannel();
    }

    public static String getUserUnit(Context context) {
        return getManagerImpl(context).getUserUnit();
    }

    public static boolean isChannelError(Context context, int i) {
        return getManagerImpl(context).isChannelError(i);
    }

    public static void registerSerivce(Context context, String str, String str2) {
        getManagerImpl(context).registerSerivce(context, str, str2);
    }

    public static void unregisterService(Context context, String str) {
        getManagerImpl(context).unRegisterSerivce(context, str);
    }

    public static void registerDataListener(Context context, String str, AccsAbstractDataListener accsAbstractDataListener) {
        if (getManagerImpl(context) == null) {
            ALog.e(TAG, "getManagerImpl null, return", new Object[0]);
        } else {
            getManagerImpl(context).registerDataListener(context, str, accsAbstractDataListener);
        }
    }

    public static void unRegisterDataListener(Context context, String str) {
        getManagerImpl(context).unRegisterDataListener(context, str);
    }

    public static synchronized IACCSManager getManagerImpl(Context context) {
        IACCSManager iACCSManager;
        synchronized (ACCSManager.class) {
            try {
                if (accsManager == null) {
                    accsManager = (IACCSManager) com.taobao.accs.d.a.a().a(context).loadClass(Constants.ACCS_IMPL_NAME).newInstance();
                    if (accsManager == null) {
                        try {
                            accsManager = (IACCSManager) Class.forName(Constants.ACCS_IMPL_NAME).newInstance();
                        } catch (Exception e) {
                        }
                    }
                }
            } catch (Exception e2) {
                if (accsManager == null) {
                    try {
                        accsManager = (IACCSManager) Class.forName(Constants.ACCS_IMPL_NAME).newInstance();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th) {
            }
            iACCSManager = accsManager;
        }
        return iACCSManager;
    }
}
