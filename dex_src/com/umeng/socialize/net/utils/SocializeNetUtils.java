package com.umeng.socialize.net.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.fb.model.Constants;
import com.umeng.socialize.Config;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.download.proguard.c;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SocializeNetUtils {
    private static final String TAG = "SocializeNetUtils";

    public static String generateGetURL(String str, Map<String, Object> map) {
        if (TextUtils.isEmpty(str) || map == null || map.size() == 0) {
            return str;
        }
        if (!str.endsWith("?")) {
            str = str + "?";
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = stringBuilder;
        for (String toString : map.keySet()) {
            String toString2;
            if (map.get(toString2) != null) {
                stringBuilder2 = stringBuilder2.append(toString2 + "=" + URLEncoder.encode(map.get(toString2).toString()) + "&");
            }
        }
        StringBuilder stringBuilder3 = new StringBuilder(str);
        try {
            toString2 = stringBuilder2.substring(0, stringBuilder2.length() - 1).toString();
            Log.d(TAG, new StringBuilder("##### \u672a\u52a0\u5bc6\u53c2\u6570 : ").append(toString2).toString());
            stringBuilder3.append(new StringBuilder("ud_get=").append(AesHelper.encryptNoPadding(toString2, CharsetConvert.UTF_8)).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, new StringBuilder("#### \u5b8c\u6574\u8bf7\u6c42\u94fe\u63a5 : ").append(stringBuilder3.toString()).toString());
        return stringBuilder3.toString();
    }

    public static Map<String, Object> getBaseQuery(Context context) {
        Map<String, Object> hashMap = new HashMap();
        Object deviceId = DeviceConfig.getDeviceId(context);
        if (!TextUtils.isEmpty(deviceId)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_IMEI, deviceId);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MD5IMEI, AesHelper.md5(deviceId));
        }
        CharSequence mac = DeviceConfig.getMac(context);
        if (TextUtils.isEmpty(mac)) {
            Log.w(TAG, new StringBuilder("Get MacAddress failed. Check permission android.permission.ACCESS_WIFI_STATE [").append(DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")).append("]").toString());
        } else {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, mac);
        }
        if (!TextUtils.isEmpty(SocializeConstants.UID)) {
            hashMap.put(c.f, SocializeConstants.UID);
        }
        try {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_EN, DeviceConfig.getNetworkAccessMode(context)[0]);
        } catch (Exception e) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_EN, "Unknown");
        }
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_DE, Build.MODEL);
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_VERSION, "5.2.1");
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_OS, Constants.SDK_TYPE);
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_DT, Long.valueOf(System.currentTimeMillis()));
        mac = SocializeUtils.getAppkey(context);
        if (TextUtils.isEmpty(mac)) {
            throw new SocializeException("No found appkey.");
        }
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_AK, mac);
        hashMap.put(SocializeProtocolConstants.PROTOCOL_VERSION, "2.0");
        if (!TextUtils.isEmpty(Config.EntityKey)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_KEY, Config.EntityKey);
            Log.v("10.13", new StringBuilder("ek = ").append(Config.EntityKey).toString());
        }
        if (!TextUtils.isEmpty(Config.SessionId)) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_SID, Config.SessionId);
        }
        try {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE, Integer.valueOf(0));
        } catch (Exception e2) {
        }
        return hashMap;
    }

    public static byte[] getNetData(String str) {
        Object obj;
        InputStream inputStream = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                InputStream inputStream2 = (InputStream) new URL(str).openConnection().getContent();
                try {
                    Log.d(WeiXinShareContent.TYPE_IMAGE, new StringBuilder("getting image from url").append(str).toString());
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e) {
                            }
                        } catch (IOException e2) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e3) {
                            }
                        } catch (Throwable th) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                    }
                    return toByteArray;
                } catch (Exception e5) {
                    Exception exception = e5;
                    r2 = inputStream2;
                    Exception exception2 = exception;
                    try {
                        Log.e(new StringBuilder("xxxxx e=").append(obj).toString());
                        if (r2 != null) {
                            try {
                                r2.close();
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (IOException e6) {
                                    }
                                }
                            } catch (IOException e7) {
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (IOException e8) {
                                    }
                                }
                            } catch (Throwable th2) {
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (IOException e9) {
                                    }
                                }
                            }
                        }
                        return null;
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        inputStream = r2;
                        if (inputStream != null) {
                            inputStream.close();
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                        }
                        throw th4;
                    }
                } catch (Throwable th5) {
                    Throwable th6 = th5;
                    inputStream = inputStream2;
                    th4 = th6;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e10) {
                                }
                            }
                        } catch (IOException e11) {
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e12) {
                                }
                            }
                        } catch (Throwable th7) {
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e13) {
                                }
                            }
                        }
                    }
                    throw th4;
                }
            } catch (Exception e14) {
                obj = e14;
                r2 = null;
                Log.e(new StringBuilder("xxxxx e=").append(obj).toString());
                if (r2 != null) {
                    r2.close();
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                }
                return null;
            } catch (Throwable th8) {
                th4 = th8;
                if (inputStream != null) {
                    inputStream.close();
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                }
                throw th4;
            }
        } catch (Exception e15) {
            obj = e15;
            r2 = null;
            byteArrayOutputStream = null;
            Log.e(new StringBuilder("xxxxx e=").append(obj).toString());
            InputStream inputStream3;
            if (inputStream3 != null) {
                inputStream3.close();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            }
            return null;
        } catch (Throwable th9) {
            th4 = th9;
            byteArrayOutputStream = null;
            if (inputStream != null) {
                inputStream.close();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            }
            throw th4;
        }
    }

    public static boolean startWithHttp(String str) {
        return str.startsWith("http://") || str.startsWith("https://");
    }
}
