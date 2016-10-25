package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.android.utils.StringUtils;

public class UTDevice {
    public static String getUtdid(Context context) {
        Device device = DeviceInfo.getDevice(context);
        return (device == null || StringUtils.isEmpty(device.getUtdid())) ? "ffffffffffffffffffffffff" : device.getUtdid();
    }
}
