package com.ta.utdid2.device;

import android.content.Context;
import android.provider.Settings.System;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.android.utils.IntUtils;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.core.persistent.PersistentConfiguration;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class UTUtdid {
    private static final Object CREATE_LOCK;
    private static final String HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
    private static final String S_GLOBAL_PERSISTENT_CONFIG_DIR;
    private static final String S_GLOBAL_PERSISTENT_CONFIG_KEY = "Alvin2";
    private static final String S_LOCAL_STORAGE_KEY = "ContextData";
    private static final String S_LOCAL_STORAGE_NAME = ".DataStorage";
    static final String UM_SETTINGS_STORAGE = "dxCRMxhQkdGePGnp";
    static final String UM_SETTINGS_STORAGE_NEW = "mqBRboGZkQPcAkyk";
    private static UTUtdid s_umutdid;
    private String mCBDomain;
    private String mCBKey;
    private Context mContext;
    private PersistentConfiguration mPC;
    private Pattern mPattern;
    private PersistentConfiguration mTaoPC;
    private String mUtdid;
    private UTUtdidHelper mUtdidHelper;

    static {
        CREATE_LOCK = new Object();
        s_umutdid = null;
        S_GLOBAL_PERSISTENT_CONFIG_DIR = new StringBuilder(".UTSystemConfig").append(File.separator).append("Global").toString();
    }

    public UTUtdid(Context context) {
        this.mContext = null;
        this.mUtdid = null;
        this.mUtdidHelper = null;
        this.mCBKey = "xx_utdid_key";
        this.mCBDomain = "xx_utdid_domain";
        this.mPC = null;
        this.mTaoPC = null;
        this.mPattern = Pattern.compile("[^0-9a-zA-Z=/+]+");
        this.mContext = context;
        this.mTaoPC = new PersistentConfiguration(context, S_GLOBAL_PERSISTENT_CONFIG_DIR, S_GLOBAL_PERSISTENT_CONFIG_KEY, false, true);
        this.mPC = new PersistentConfiguration(context, S_LOCAL_STORAGE_NAME, S_LOCAL_STORAGE_KEY, false, true);
        this.mUtdidHelper = new UTUtdidHelper();
        this.mCBKey = String.format("K_%d", new Object[]{Integer.valueOf(StringUtils.hashCode(this.mCBKey))});
        this.mCBDomain = String.format("D_%d", new Object[]{Integer.valueOf(StringUtils.hashCode(this.mCBDomain))});
    }

    private void _removeIllegalKeys() {
        Object obj = 1;
        if (this.mTaoPC != null) {
            int i;
            if (StringUtils.isEmpty(this.mTaoPC.getString("UTDID2"))) {
                String string = this.mTaoPC.getString("UTDID");
                if (!StringUtils.isEmpty(string)) {
                    saveUtdidToTaoPPC(string);
                }
            }
            Object obj2 = null;
            if (!StringUtils.isEmpty(this.mTaoPC.getString("DID"))) {
                this.mTaoPC.remove("DID");
                i = 1;
            }
            if (!StringUtils.isEmpty(this.mTaoPC.getString("EI"))) {
                this.mTaoPC.remove("EI");
                i = 1;
            }
            if (StringUtils.isEmpty(this.mTaoPC.getString("SI"))) {
                obj = obj2;
            } else {
                this.mTaoPC.remove("SI");
            }
            if (obj != null) {
                this.mTaoPC.commit();
            }
        }
    }

    public static UTUtdid instance(Context context) {
        if (context != null && s_umutdid == null) {
            synchronized (CREATE_LOCK) {
                if (s_umutdid == null) {
                    UTUtdid uTUtdid = new UTUtdid(context);
                    s_umutdid = uTUtdid;
                    uTUtdid._removeIllegalKeys();
                }
            }
        }
        return s_umutdid;
    }

    private void saveUtdidToTaoPPC(String str) {
        if (isValidUTDID(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() == 24 && this.mTaoPC != null) {
                this.mTaoPC.putString("UTDID2", str);
                this.mTaoPC.commit();
            }
        }
    }

    private void saveUtdidToLocalStorage(String str) {
        if (str != null && this.mPC != null && !str.equals(this.mPC.getString(this.mCBKey))) {
            this.mPC.putString(this.mCBKey, str);
            this.mPC.commit();
        }
    }

    private void saveUtdidToNewSettings(String str) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && isValidUTDID(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length()) {
                String str2 = null;
                try {
                    str2 = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW);
                } catch (Exception e) {
                }
                if (!isValidUTDID(str2)) {
                    try {
                        System.putString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW, str);
                    } catch (Exception e2) {
                    }
                }
            }
        }
    }

    private void syncUTDIDToSettings(String str) {
        Object obj = null;
        try {
            obj = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE);
        } catch (Exception e) {
        }
        if (!str.equals(obj)) {
            try {
                System.putString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE, str);
            } catch (Exception e2) {
            }
        }
    }

    private void saveUtdidToSettings(String str) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && str != null) {
            syncUTDIDToSettings(str);
        }
    }

    private String getUtdidFromTaoPPC() {
        if (this.mTaoPC != null) {
            String string = this.mTaoPC.getString("UTDID2");
            if (!(StringUtils.isEmpty(string) || this.mUtdidHelper.packUtdidStr(string) == null)) {
                return string;
            }
        }
        return null;
    }

    private boolean isValidUTDID(String str) {
        if (str == null) {
            return false;
        }
        if (str.endsWith("\n")) {
            str = str.substring(0, str.length() - 1);
        }
        return 24 == str.length() && !this.mPattern.matcher(str).find();
    }

    public synchronized String getValue() {
        String str;
        if (this.mUtdid != null) {
            str = this.mUtdid;
        } else {
            str = a.d;
            try {
                str = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW);
            } catch (Exception e) {
            }
            if (!isValidUTDID(str)) {
                String string;
                UTUtdidHelper2 uTUtdidHelper2 = new UTUtdidHelper2();
                Object obj = null;
                try {
                    string = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE);
                } catch (Exception e2) {
                    string = null;
                }
                if (StringUtils.isEmpty(string)) {
                    int i = 1;
                } else {
                    str = uTUtdidHelper2.dePackWithBase64(string);
                    if (isValidUTDID(str)) {
                        saveUtdidToNewSettings(str);
                    } else {
                        str = uTUtdidHelper2.dePack(string);
                        if (isValidUTDID(str)) {
                            str = this.mUtdidHelper.packUtdidStr(str);
                            if (!StringUtils.isEmpty(str)) {
                                saveUtdidToSettings(str);
                                try {
                                    str = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE);
                                } catch (Exception e3) {
                                }
                                string = this.mUtdidHelper.dePack(str);
                                if (isValidUTDID(string)) {
                                    this.mUtdid = string;
                                    saveUtdidToTaoPPC(string);
                                    saveUtdidToLocalStorage(str);
                                    saveUtdidToNewSettings(this.mUtdid);
                                    str = this.mUtdid;
                                }
                            }
                        }
                        str = string;
                        string = this.mUtdidHelper.dePack(str);
                        if (isValidUTDID(string)) {
                            this.mUtdid = string;
                            saveUtdidToTaoPPC(string);
                            saveUtdidToLocalStorage(str);
                            saveUtdidToNewSettings(this.mUtdid);
                            str = this.mUtdid;
                        }
                    }
                }
                str = getUtdidFromTaoPPC();
                if (isValidUTDID(str)) {
                    String packUtdidStr = this.mUtdidHelper.packUtdidStr(str);
                    if (obj != null) {
                        saveUtdidToSettings(packUtdidStr);
                    }
                    saveUtdidToNewSettings(str);
                    saveUtdidToLocalStorage(packUtdidStr);
                    this.mUtdid = str;
                } else {
                    string = this.mPC.getString(this.mCBKey);
                    if (!StringUtils.isEmpty(string)) {
                        str = uTUtdidHelper2.dePack(string);
                        if (!isValidUTDID(str)) {
                            str = this.mUtdidHelper.dePack(string);
                        }
                        if (isValidUTDID(str)) {
                            string = this.mUtdidHelper.packUtdidStr(str);
                            if (!StringUtils.isEmpty(str)) {
                                this.mUtdid = str;
                                if (obj != null) {
                                    saveUtdidToSettings(string);
                                }
                                saveUtdidToTaoPPC(this.mUtdid);
                                str = this.mUtdid;
                            }
                        }
                    }
                    try {
                        byte[] _generateUtdid = _generateUtdid();
                        if (_generateUtdid != null) {
                            this.mUtdid = Base64.encodeToString(_generateUtdid, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                            saveUtdidToTaoPPC(this.mUtdid);
                            str = this.mUtdidHelper.pack(_generateUtdid);
                            if (str != null) {
                                if (obj != null) {
                                    saveUtdidToSettings(str);
                                }
                                saveUtdidToLocalStorage(str);
                            }
                            str = this.mUtdid;
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    str = null;
                }
            }
        }
        return str;
    }

    private final byte[] _generateUtdid() throws Exception {
        String imei;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nextInt = new Random().nextInt();
        byte[] bytes = IntUtils.getBytes(currentTimeMillis);
        byte[] bytes2 = IntUtils.getBytes(nextInt);
        byteArrayOutputStream.write(bytes, 0, XZBDevice.DOWNLOAD_LIST_ALL);
        byteArrayOutputStream.write(bytes2, 0, XZBDevice.DOWNLOAD_LIST_ALL);
        byteArrayOutputStream.write(XZBDevice.DOWNLOAD_LIST_FAILED);
        byteArrayOutputStream.write(0);
        try {
            imei = PhoneInfoUtils.getImei(this.mContext);
        } catch (Exception e) {
            imei = new Random().nextInt();
        }
        byteArrayOutputStream.write(IntUtils.getBytes(StringUtils.hashCode(imei)), 0, XZBDevice.DOWNLOAD_LIST_ALL);
        byteArrayOutputStream.write(IntUtils.getBytes(StringUtils.hashCode(_calcHmac(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    private static String _calcHmac(byte[] bArr) throws Exception {
        String str = HMAC_KEY;
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(str.getBytes(), instance.getAlgorithm()));
        return Base64.encodeToString(instance.doFinal(bArr), XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }
}
