package com.xunlei.mediaserver;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.sina.weibo.sdk.component.GameManager;
import com.umeng.socialize.common.SocializeConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Timer;

public class MediaServer {
    private static final String TAG = "MediaServer";
    public static MediaServer mMediaServer;
    private final int DEBAULT_PLATFORM;
    private final int DEBAULT_SCREENW;
    private final int DEBAULT_SECTION_TYPE;
    private final String GET_PLAY_INFO_CMD;
    private final String GET_VOD_ERROR_INFO;
    private final int MEDIA_SERVER_PORT;
    private final String MEDIA_SERVER_URL_PREFIX;
    private final String PLAY_URL_CMD;
    private final String POST_STAT_INFO;
    private final int SHOULEI_APPID;
    private final String START_DOWNLOAD_CMD;
    private final String STOP_ALL_VOD_TASK_CMD;
    private final String STOP_DOWNLOAD_CMD;
    private int TIME_OUT;
    private Timer mGetSpeedTimer;
    private GetSpeedTimerTask mGetSpeedTimerTask;
    private UtilityHandlerThread mHandlerThread;
    Runnable mMediaServerRunnable;
    private Object mSyncObj;
    private String mSystemPath;

    public static native int getHttpListenPort();

    private native int getVodTaskInfo();

    private static native int init(int i, int i2, String str);

    private static native void run();

    private static native int setLocalInfo(String str, String str2, String str3, String str4);

    private static native void uninit();

    public static MediaServer getInstance(Context context, int i, String str) {
        if (mMediaServer == null) {
            mMediaServer = new MediaServer(context, i, str);
        }
        return mMediaServer;
    }

    private MediaServer(Context context, int i, String str) {
        this.mHandlerThread = null;
        this.SHOULEI_APPID = 0;
        this.MEDIA_SERVER_PORT = 17626;
        this.DEBAULT_SCREENW = 800;
        this.DEBAULT_SECTION_TYPE = 0;
        this.DEBAULT_PLATFORM = 0;
        this.MEDIA_SERVER_URL_PREFIX = "http://127.0.0.1:";
        this.PLAY_URL_CMD = "playVodUrl";
        this.GET_PLAY_INFO_CMD = "getVodPlayInfo";
        this.START_DOWNLOAD_CMD = "startDownload";
        this.STOP_DOWNLOAD_CMD = "stopDownload";
        this.GET_VOD_ERROR_INFO = "getVodErrorInfo";
        this.STOP_ALL_VOD_TASK_CMD = "stopAllVodSession";
        this.POST_STAT_INFO = "postSdkStaticInfo";
        this.mSystemPath = null;
        this.mSyncObj = new Object();
        this.TIME_OUT = 1000;
        this.mMediaServerRunnable = new AnonymousClass_1(this);
        InitMediaServer(context, i, str);
    }

    public boolean InitMediaServer(Context context, int i, String str) {
        this.mHandlerThread = new UtilityHandlerThread();
        this.mHandlerThread.init();
        String str2 = Utility.getSDCardDir(context) + "CLOUDPLAY/temp/";
        Utility.ensureDir(str2);
        int init = init(i, 17626, str2);
        setLocalInfo(context);
        this.mHandlerThread.ExecuteRunnable(this.mMediaServerRunnable);
        return init == 0;
    }

    public int setLocalInfo(Context context) {
        String imei = Utility.getIMEI(context);
        String networkType = Utility.getNetworkType(context);
        if (imei == null) {
            imei = "imei_not_know";
        }
        if (networkType == null) {
            networkType = "net_not_know";
        }
        String replace = Build.MODEL.replace(" ", SocializeConstants.OP_DIVIDER_MINUS);
        String str = VERSION.RELEASE;
        new StringBuilder("mediaserver init setLocalInfo imei=").append(imei).append(", deviceType=").append(replace).append(", osVersion=").append(str).append(", nettype=").append(networkType);
        return setLocalInfo(imei, replace, str, networkType);
    }

    public void UninitMediaServer() {
        uninit();
        synchronized (this.mMediaServerRunnable) {
            try {
                this.mMediaServerRunnable.wait((long) this.TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getEncodeUrl(String str) {
        if (str == null || str.length() < 5) {
            return null;
        }
        try {
            return URLEncoder.encode(str, GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void startPlayTask(String str) {
        new MediaServerCommunicateThread(this, str, "playVodUrl").start();
    }

    public String getVodPlayURL(String str, String str2, String str3, String str4, long j) {
        String encodeUrl = getEncodeUrl(str);
        if (encodeUrl == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(GetMediaServerURLPrefix()).append("/playVodUrl?src_url=").append(encodeUrl).append("&platform=0&gcid=").append(str3).append("&cid=").append(str4).append("&file_size=").append(j);
        if (str2 != null) {
            stringBuffer.append("&cookie=").append(getEncodeUrl(str2));
        }
        new StringBuilder("getVodPlayURL url=").append(str).append(", finalUrl=").append(stringBuffer.toString()).append(" and cookie=").append(str2);
        return stringBuffer.toString();
    }

    public void startGetVodTaskInfo(OnVodTaskInfoListener onVodTaskInfoListener, String str) {
        if (this.mGetSpeedTimer != null) {
            this.mGetSpeedTimer.cancel();
            if (this.mGetSpeedTimerTask != null) {
                this.mGetSpeedTimerTask.release();
                this.mGetSpeedTimerTask = null;
            }
        }
        this.mGetSpeedTimer = new Timer();
        this.mGetSpeedTimerTask = new GetSpeedTimerTask(this, onVodTaskInfoListener, str);
        this.mGetSpeedTimer.schedule(this.mGetSpeedTimerTask, 0, 1000);
    }

    public void stopGetVodTaskInfo() {
        if (this.mGetSpeedTimer != null) {
            this.mGetSpeedTimer.cancel();
            this.mGetSpeedTimer = null;
        }
        if (this.mGetSpeedTimerTask != null) {
            this.mGetSpeedTimerTask.release();
            this.mGetSpeedTimerTask = null;
        }
    }

    public String generateGetVodPlayInfoURL(String str) {
        String encodeUrl = getEncodeUrl(str);
        return encodeUrl == null ? null : new StringBuilder("/getVodPlayInfo?src_url=").append(encodeUrl).toString();
    }

    public String GetMediaServerURLPrefix() {
        new StringBuilder("GetMediaServerURLPrefix=http://127.0.0.1:").append(getHttpListenPort());
        return new StringBuilder("http://127.0.0.1:").append(getHttpListenPort()).toString();
    }

    private String generateStopVodTaskDownloadURL(String str) {
        String encodeUrl = getEncodeUrl(str);
        if (encodeUrl == null) {
            return null;
        }
        encodeUrl = GetMediaServerURLPrefix() + "/stopDownload?src_url=" + encodeUrl;
        new StringBuilder("generateStopVodTaskDownloadURL url=").append(str).append(", finalUrl=").append(encodeUrl);
        return encodeUrl;
    }

    private String generateStartVodTaskDownloadURL(String str) {
        String encodeUrl = getEncodeUrl(str);
        if (encodeUrl == null) {
            return null;
        }
        encodeUrl = GetMediaServerURLPrefix() + "/startDownload?src_url=" + encodeUrl;
        new StringBuilder("generateStartVodTaskDownloadURL url=").append(str).append(", finalUrl=").append(encodeUrl);
        return encodeUrl;
    }

    private String generateStopAllVodTaskURL() {
        return GetMediaServerURLPrefix() + "/stopAllVodSession?src_url=";
    }

    private String generateStartVodTaskErrorInfoURL(String str) {
        String encodeUrl = getEncodeUrl(str);
        if (encodeUrl == null) {
            return null;
        }
        encodeUrl = GetMediaServerURLPrefix() + "/getVodErrorInfo?src_url=" + encodeUrl;
        new StringBuilder("generateStartVodTaskErroInfoURL url=").append(str).append(", finalUrl=").append(encodeUrl);
        return encodeUrl;
    }

    public boolean stopVodTaskDownload(String str) {
        new MediaServerCommunicateThread(this, generateStopVodTaskDownloadURL(str), "stopDownload").start();
        return true;
    }

    public boolean startVodTaskDownload(String str) {
        new MediaServerCommunicateThread(this, generateStartVodTaskDownloadURL(str), "startDownload").start();
        return true;
    }

    public boolean stopAllVodTask() {
        new MediaServerCommunicateThread(this, generateStopAllVodTaskURL(), "stopAllVodSession").start();
        return true;
    }

    public void getVodTaskErrorInfo(String str, OnVodTaskErrorListener onVodTaskErrorListener) {
        new MediaServerCommunicateThread(this, generateStartVodTaskErrorInfoURL(str), onVodTaskErrorListener).start();
    }

    public void postStatInfo(String str, String str2, long j, long j2, int i, int i2, int i3, int i4, int i5) {
        String encodeUrl = getEncodeUrl(str);
        StringBuffer stringBuffer = new StringBuffer(GetMediaServerURLPrefix() + "/postSdkStaticInfo?");
        stringBuffer.append("src_url=").append(encodeUrl).append("&gcid=").append(str2).append("&launch_time=").append(j).append("&begin_play_time=").append(j2).append("&interrupt_times=").append(i).append("&total_interrupt_duration=").append(i2).append("&total_play_duration=").append(i3).append("&drag_times=").append(i4).append("&cache_duration_after_drag=").append(i5);
        new MediaServerCommunicateThread(this, stringBuffer.toString(), "postSdkStaticInfo").start();
    }

    static {
        System.loadLibrary("mediaserver");
        System.loadLibrary("mediaserver_jni");
    }
}
