package com.xunlei.xiazaibao.shoulei;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDevice.UrlData;
import com.xunlei.xiazaibao.sdk.XZBDownloadTaskSet;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import java.util.List;

public class XZBShouleiCallback {
    public void cb_UpdateDeviceList(int i, int i2, XZBDevice[] xZBDeviceArr, String str, Object obj) {
    }

    public void cb_showDeviceTaskList(int i, int i2, XZBDevice xZBDevice, Object obj) {
    }

    public void cb_showXZBGuide(int i, int i2, Object obj) {
    }

    public void cb_CreateTask(int i, int i2, UrlData[] urlDataArr, String str, Object obj) {
    }

    public void cb_QueryTaskList(int i, int i2, XZBDevice xZBDevice, XZBDownloadTaskSet xZBDownloadTaskSet, String str, Object obj) {
    }

    public void cb_StartTask(int i, int i2, XZBDevice xZBDevice, List<DownloadTaskResult> list, String str, Object obj) {
    }

    public void cb_PauseTask(int i, int i2, XZBDevice xZBDevice, List<DownloadTaskResult> list, String str, Object obj) {
    }

    public void cb_DeleteTask(int i, int i2, XZBDevice xZBDevice, List<DownloadTaskResult> list, String str, Object obj) {
    }

    public void cb_createBtTask(int i, int i2, XZBDevice xZBDevice, String str, Object obj) {
    }
}
