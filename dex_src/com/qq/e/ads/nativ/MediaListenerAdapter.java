package com.qq.e.ads.nativ;

import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.util.GDTLogger;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class MediaListenerAdapter implements ADListener {
    private MediaListener a;

    public MediaListenerAdapter(MediaListener mediaListener) {
        this.a = mediaListener;
    }

    public void onADEvent(ADEvent aDEvent) {
        switch (aDEvent.getType()) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                    this.a.onVideoReady((long) ((Integer) aDEvent.getParas()[0]).intValue());
                } else {
                    GDTLogger.e("NativeMedia ADEvent Paras error!");
                }
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.a.onVideoStart();
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.a.onVideoPause();
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.a.onVideoComplete();
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                    this.a.onVideoError(((Integer) aDEvent.getParas()[0]).intValue());
                } else {
                    GDTLogger.e("NativeMedia ADEvent Paras error!");
                }
            case R.styleable.Toolbar_contentInsetEnd:
                this.a.onReplayButtonClicked();
            case R.styleable.Toolbar_contentInsetLeft:
                this.a.onADButtonClicked();
            default:
                break;
        }
    }
}
