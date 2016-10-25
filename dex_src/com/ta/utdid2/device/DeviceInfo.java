package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import java.util.zip.Adler32;

public class DeviceInfo {
    static final Object CREATE_DEVICE_METADATA_LOCK;
    static String HMAC_KEY = null;
    static final byte UTDID_VERSION_CODE = (byte) 1;
    private static Device mDevice;

    static {
        mDevice = null;
        HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
        CREATE_DEVICE_METADATA_LOCK = new Object();
    }

    static long getMetadataCheckSum(Device device) {
        if (device != null) {
            String format = String.format("%s%s%s%s%s", new Object[]{device.getUtdid(), device.getDeviceId(), Long.valueOf(device.getCreateTimestamp()), device.getImsi(), device.getImei()});
            if (!StringUtils.isEmpty(format)) {
                Adler32 adler32 = new Adler32();
                adler32.reset();
                adler32.update(format.getBytes());
                return adler32.getValue();
            }
        }
        return 0;
    }

    private static Device _initDeviceMetadata(Context context) {
        if (context != null) {
            Device device = new Device();
            synchronized (CREATE_DEVICE_METADATA_LOCK) {
                String value = UTUtdid.instance(context).getValue();
                if (StringUtils.isEmpty(value)) {
                } else {
                    String substring;
                    if (value.endsWith("\n")) {
                        substring = value.substring(0, value.length() - 1);
                    } else {
                        substring = value;
                    }
                    device = new Device();
                    long currentTimeMillis = System.currentTimeMillis();
                    String imei = PhoneInfoUtils.getImei(context);
                    String imsi = PhoneInfoUtils.getImsi(context);
                    device.setDeviceId(imei);
                    device.setImei(imei);
                    device.setCreateTimestamp(currentTimeMillis);
                    device.setImsi(imsi);
                    device.setUtdid(substring);
                    device.setCheckSum(getMetadataCheckSum(device));
                    return device;
                }
            }
        }
        return null;
    }

    public static synchronized Device getDevice(Context context) {
        Device device;
        synchronized (DeviceInfo.class) {
            if (mDevice != null) {
                device = mDevice;
            } else if (context != null) {
                device = _initDeviceMetadata(context);
                mDevice = device;
            } else {
                device = null;
            }
        }
        return device;
    }
}
