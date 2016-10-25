package org.android.spdy;

import java.util.List;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

final class SpdySessionCallBack implements Intenalcb {
    SpdySessionCallBack() {
    }

    public final void spdyDataChunkRecvCB(SpdySession spdySession, boolean z, long j, SpdyByteArray spdyByteArray, int i) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdyDataChunkRecvCB] - ").append(spdySession).toString());
        long begin = NetTimeGaurd.begin();
        SpdyStreamContext spdyStream = spdySession.getSpdyStream(i);
        if (spdyStream == null || spdyStream.callBack == null) {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyDataChunkRecvCB] - no sessionCallBack.");
        } else {
            spdyStream.callBack.spdyDataChunkRecvCB(spdySession, z, j, spdyByteArray, spdyStream.streamContext);
        }
        NetTimeGaurd.end("spdyDataChunkRecvCB", MqttConnectOptions.MQTT_VERSION_3_1, begin);
    }

    public final void spdyDataRecvCallback(SpdySession spdySession, boolean z, long j, int i, int i2) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdyDataRecvCallback] - ").append(spdySession).toString());
        long begin = NetTimeGaurd.begin();
        SpdyStreamContext spdyStream = spdySession.getSpdyStream(i2);
        if (spdyStream == null || spdyStream.callBack == null) {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyDataRecvCallback] - no sessionCallBack.");
        } else {
            spdyStream.callBack.spdyDataRecvCallback(spdySession, z, j, i, spdyStream.streamContext);
        }
        NetTimeGaurd.end("spdyDataRecvCallback", MqttConnectOptions.MQTT_VERSION_3_1, begin);
    }

    public final void spdyDataSendCallback(SpdySession spdySession, boolean z, long j, int i, int i2) {
        spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdyDataSendCallback] - ");
        SpdyStreamContext spdyStream = spdySession.getSpdyStream(i2);
        if (spdyStream == null || spdyStream.callBack == null) {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyDataSendCallback] - no sessionCallBack.");
            return;
        }
        spdyStream.callBack.spdyDataSendCallback(spdySession, z, j, i, spdyStream.streamContext);
    }

    public final void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, int i2, SuperviseData superviseData) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdyStreamCloseCallback] - ").append(spdySession).toString());
        long begin = NetTimeGaurd.begin();
        SpdyStreamContext spdyStream = spdySession.getSpdyStream(i2);
        if (spdyStream == null || spdyStream.callBack == null) {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyStreamCloseCallback] - no sessionCallBack.");
        } else {
            spduLog.Logi("tnet-jni", new StringBuilder("index=").append(i2).append("    endtime=").append(System.currentTimeMillis()).toString());
            spdyStream.callBack.spdyStreamCloseCallback(spdySession, j, i, spdyStream.streamContext, superviseData);
            spdySession.removeSpdyStream(i2);
        }
        NetTimeGaurd.end("spdyStreamCloseCallback", MqttConnectOptions.MQTT_VERSION_3_1, begin);
        NetTimeGaurd.finish(MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public final void spdyRequestRecvCallback(SpdySession spdySession, long j, int i) {
        spduLog.Logd("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdyOnStreamResponse] - ").append(spdySession).toString());
        long begin = NetTimeGaurd.begin();
        SpdyStreamContext spdyStream = spdySession.getSpdyStream(i);
        if (spdyStream == null || spdyStream.callBack == null) {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyRequestRecvCallback] - no sessionCallBack.");
        } else {
            spdyStream.callBack.spdyRequestRecvCallback(spdySession, j, spdyStream.streamContext);
        }
        NetTimeGaurd.end("spdyPingRecvCallback", MqttConnectOptions.MQTT_VERSION_3_1, begin);
    }

    public final void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, int i) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdyOnStreamResponse] - ").append(spdySession).toString());
        NetTimeGaurd.start(MqttConnectOptions.MQTT_VERSION_3_1);
        long begin = NetTimeGaurd.begin();
        SpdyStreamContext spdyStream = spdySession.getSpdyStream(i);
        if (spdyStream == null || spdyStream.callBack == null) {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyOnStreamResponse] - no sessionCallBack.");
        } else {
            spdyStream.callBack.spdyOnStreamResponse(spdySession, j, map, spdyStream.streamContext);
        }
        NetTimeGaurd.end("spdyOnStreamResponse", MqttConnectOptions.MQTT_VERSION_3_1, begin);
    }

    public final void spdySessionConnectCB(SpdySession spdySession, SuperviseConnectInfo superviseConnectInfo) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdySessionConnectCB] - ").append(spdySession).toString());
        NetTimeGaurd.start(0);
        if (spdySession.sessionCallBack != null) {
            long begin = NetTimeGaurd.begin();
            spdySession.sessionCallBack.spdySessionConnectCB(spdySession, superviseConnectInfo);
            NetTimeGaurd.end("spdySessionConnectCB", 0, begin);
        } else {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdySessionConnectCB] - no sessionCallBack.");
        }
        NetTimeGaurd.finish(0);
    }

    public final void spdyPingRecvCallback(SpdySession spdySession, long j, Object obj) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdyPingRecvCallback] - ").append(spdySession).toString());
        NetTimeGaurd.start(1);
        if (spdySession.sessionCallBack != null) {
            long begin = NetTimeGaurd.begin();
            spdySession.sessionCallBack.spdyPingRecvCallback(spdySession, j, obj);
            NetTimeGaurd.end("spdyPingRecvCallback", 1, begin);
        } else {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyPingRecvCallback] - no sessionCallBack.");
        }
        NetTimeGaurd.finish(1);
    }

    public final void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdyCustomControlFrameRecvCallback] - ").append(spdySession).toString());
        if (spdySession.sessionCallBack != null) {
            spdySession.sessionCallBack.spdyCustomControlFrameRecvCallback(spdySession, obj, i, i2, i3, i4, bArr);
        } else {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyCustomControlFrameRecvCallback] - no sessionCallBack.");
        }
    }

    public final void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdyCustomControlFrameFailCallback] - ").append(spdySession).toString());
        if (spdySession.sessionCallBack != null) {
            spdySession.sessionCallBack.spdyCustomControlFrameFailCallback(spdySession, obj, i, i2);
        } else {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyCustomControlFrameFailCallback] - no sessionCallBack.");
        }
    }

    public final void spdySessionFailedError(SpdySession spdySession, int i, Object obj) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdySessionFailedError] - ").append(spdySession).toString());
        NetTimeGaurd.start(SimpleLog.LOG_LEVEL_DEBUG);
        if (spdySession.sessionCallBack != null) {
            long begin = NetTimeGaurd.begin();
            spdySession.sessionCallBack.spdySessionFailedError(spdySession, i, obj);
            spdySession.clearAllStreamCb();
            NetTimeGaurd.end("spdySessionFailedError", SimpleLog.LOG_LEVEL_DEBUG, begin);
        } else {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdySessionFailedError] - no sessionCallBack.");
        }
        NetTimeGaurd.finish(SimpleLog.LOG_LEVEL_DEBUG);
    }

    public final void spdySessionOnWritable(SpdySession spdySession, Object obj, int i) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdySessionOnWritable] - ").append(spdySession).toString());
        NetTimeGaurd.start(SimpleLog.LOG_LEVEL_DEBUG);
        if (spdySession.sessionCallBack == null || !(spdySession.sessionCallBack instanceof SessionExtraCb)) {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdySessionOnWritable] - no sessionCallBack.");
        } else {
            long begin = NetTimeGaurd.begin();
            ((SessionExtraCb) spdySession.sessionCallBack).spdySessionOnWritable(spdySession, obj, i);
            NetTimeGaurd.end("spdySessionOnWritable", SimpleLog.LOG_LEVEL_DEBUG, begin);
        }
        NetTimeGaurd.finish(SimpleLog.LOG_LEVEL_DEBUG);
    }

    public final void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.spdySessionCloseCallback] - ").append(spdySession).toString());
        if (spdySession.sessionCallBack != null) {
            spdySession.sessionCallBack.spdySessionCloseCallback(spdySession, obj, superviseConnectInfo, i);
        } else {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdySessionCloseCallback] - no sessionCallBack.");
        }
    }

    public final void bioPingRecvCallback(SpdySession spdySession, int i) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.bioPingRecvCallback] - ").append(spdySession).toString());
        if (spdySession.sessionCallBack != null) {
            spdySession.sessionCallBack.bioPingRecvCallback(spdySession, i);
        } else {
            spduLog.Loge("tnet-jni", "[SpdySessionCallBack.bioPingRecvCallback] - no sessionCallBack.");
        }
    }

    public final byte[] getSSLMeta(SpdySession spdySession) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.getSSLMeta] - ").append(spdySession).toString());
        if (spdySession.sessionCallBack != null) {
            return spdySession.sessionCallBack.getSSLMeta(spdySession);
        }
        spduLog.Loge("tnet-jni", "[SpdySessionCallBack.getSSLMeta] - no sessionCallBack.");
        return null;
    }

    public final int putSSLMeta(SpdySession spdySession, byte[] bArr) {
        spduLog.Logi("tnet-jni", new StringBuilder("[SpdySessionCallBack.putSSLMeta] - ").append(spdySession).toString());
        if (spdySession.sessionCallBack != null) {
            return spdySession.sessionCallBack.putSSLMeta(spdySession, bArr);
        }
        spduLog.Loge("tnet-jni", "[SpdySessionCallBack.putSSLMeta] - no sessionCallBack.");
        return -1;
    }
}
