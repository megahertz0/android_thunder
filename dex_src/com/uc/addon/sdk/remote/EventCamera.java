package com.uc.addon.sdk.remote;

import android.os.Bundle;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.protocol.CameraCallbackArg;
import com.uc.addon.sdk.remote.protocol.IValueCallback;

public class EventCamera implements EventBase {
    private IValueCallback a;

    EventCamera(IValueCallback iValueCallback) {
        this.a = iValueCallback;
    }

    public int getEventId() {
        return EventIds.EVENT_CAMERA;
    }

    public void setCaptureImagePath(String str) {
        CameraCallbackArg cameraCallbackArg = new CameraCallbackArg();
        cameraCallbackArg.bitmapPath = str;
        Bundle bundle = new Bundle();
        cameraCallbackArg.toBundle(bundle);
        try {
            this.a.onReceiveValue(bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
