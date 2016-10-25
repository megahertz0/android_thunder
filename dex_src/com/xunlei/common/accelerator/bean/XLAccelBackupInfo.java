package com.xunlei.common.accelerator.bean;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class XLAccelBackupInfo implements Serializable {
    private static final long serialVersionUID = -6772164363300132399L;
    public int mAccelerateStatus;
    public long mBackupSystemTime;
    public long mCurrentBandwidth;
    public long mMaxBandwidth;
    public int mNumOfTry;
    public String mSessionId;
    public long mTryRemainTime;
    public int mTryTotalTime;
    public String mUserId;
    public int mUserType;

    public enum AccelStatus {
        NOT_ACCELERATE,
        IS_ACCELERATE,
        IS_TRYING
    }

    public void setUserInfo(XLAccelUser xLAccelUser) {
        if (xLAccelUser != null) {
            this.mUserId = xLAccelUser.mUserID;
            this.mUserType = xLAccelUser.mUserType;
            this.mSessionId = xLAccelUser.mSessionID;
        }
    }

    public XLAccelUser restoreUserInfo(XLAccelUser xLAccelUser) {
        if (xLAccelUser == null) {
            return null;
        }
        xLAccelUser.mSessionID = this.mSessionId;
        xLAccelUser.mUserID = this.mUserId;
        xLAccelUser.mUserType = this.mUserType;
        return xLAccelUser;
    }

    public XLAccelTryInfo restoreTryInfo(XLAccelTryInfo xLAccelTryInfo) {
        if (xLAccelTryInfo == null) {
            return null;
        }
        xLAccelTryInfo.mNumOfTry = this.mNumOfTry;
        xLAccelTryInfo.mTryDuration = this.mTryTotalTime;
        return xLAccelTryInfo;
    }

    public void setBandwidthInfo(XLAccelBandInfo xLAccelBandInfo) {
        if (xLAccelBandInfo != null) {
            this.mMaxBandwidth = xLAccelBandInfo.mMaxBandWidth.mDownStream;
            this.mCurrentBandwidth = xLAccelBandInfo.mCurrentBandWidth.mDownStream;
        }
    }

    public void setTryInfo(XLAccelTryInfo xLAccelTryInfo) {
        if (xLAccelTryInfo != null) {
            this.mTryTotalTime = xLAccelTryInfo.mTryDuration;
            this.mTryRemainTime = (long) xLAccelTryInfo.mRemainTime;
            this.mNumOfTry = xLAccelTryInfo.mNumOfTry;
        }
    }

    public void setAccelerateStatus(int i, int i2) {
        if (i == 1) {
            if (i2 == 1) {
                this.mAccelerateStatus = 0;
            } else if (i2 == 2) {
                this.mAccelerateStatus = 1;
            } else {
                this.mAccelerateStatus = -1;
            }
            this.mBackupSystemTime = System.currentTimeMillis();
            return;
        }
        this.mAccelerateStatus = -1;
    }

    public AccelStatus getAccelerateStatus() {
        if (this.mAccelerateStatus == 0) {
            return AccelStatus.IS_ACCELERATE;
        }
        return this.mAccelerateStatus == 1 ? AccelStatus.IS_TRYING : AccelStatus.NOT_ACCELERATE;
    }

    public void saveState(Context context) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(context.openFileOutput("data.s", MqttConnectOptions.MQTT_VERSION_3_1_1));
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            setSaveStateSuccessFlag(context, true);
            new StringBuilder("-------storeState successfully! \n XLAccelBackupInfo = \n").append(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xunlei.common.accelerator.bean.XLAccelBackupInfo restoreState(android.content.Context r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.accelerator.bean.XLAccelBackupInfo.restoreState(android.content.Context):com.xunlei.common.accelerator.bean.XLAccelBackupInfo");
        /*
        r0 = "data.s";
        r0 = r3.openFileInput(r0);	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
        r1 = new java.io.ObjectInputStream;	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
        r1.<init>(r0);	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
        r0 = r1.readObject();	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
        r0 = (com.xunlei.common.accelerator.bean.XLAccelBackupInfo) r0;	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
        r1.close();	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
        r1 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
        r2 = "-------restoreState successfully! \n XLAccelBackupInfo = \n";
        r1.<init>(r2);	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
        r1.append(r0);	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
        r1 = 0;
        setSaveStateSuccessFlag(r3, r1);	 Catch:{ FileNotFoundException -> 0x0025, OptionalDataException -> 0x002b, ClassNotFoundException -> 0x0030, IOException -> 0x0035 }
    L_0x0024:
        return r0;
    L_0x0025:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0029:
        r0 = 0;
        goto L_0x0024;
    L_0x002b:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0029;
    L_0x0030:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0029;
    L_0x0035:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0029;
        */
    }

    public static void setSaveStateSuccessFlag(Context context, boolean z) {
        Editor edit = context.getSharedPreferences("save_state", MqttConnectOptions.MQTT_VERSION_3_1_1).edit();
        edit.putBoolean("save_state_success", z);
        edit.commit();
    }

    public static boolean getSaveStateSuccessFlag(Context context) {
        return context.getSharedPreferences("save_state", MqttConnectOptions.MQTT_VERSION_3_1_1).getBoolean("save_state_success", false);
    }

    public String toString() {
        return new StringBuilder("mAccelerateStatus = ").append(this.mAccelerateStatus).append("\nmCurrentBandwidth = ").append(this.mCurrentBandwidth).append("\nmMaxBandwidth = ").append(this.mMaxBandwidth).append("\nmBackupSystemTime = ").append(this.mBackupSystemTime).append("\nmTryRemainTime = ").append(this.mTryRemainTime).append("\nmTryTotalTime = ").append(this.mTryTotalTime).append("\nmNumOfTry = ").append(this.mNumOfTry).append("\nmUserId = ").append(this.mUserId).append("\nmUserType = ").append(this.mUserType).append("\nmSessionId = ").append(this.mSessionId).toString();
    }
}
