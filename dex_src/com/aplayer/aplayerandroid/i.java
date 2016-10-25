package com.aplayer.aplayerandroid;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.view.Surface;
import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

// compiled from: HardwareDecoder.java
public final class i {
    private static String d;
    private static HashMap<Integer, String> e;
    String a;
    int b;
    ByteBuffer c;
    private APlayerAndroid f;
    private MediaCodec g;
    private int h;

    static {
        d = "APlayerAndroid";
        e = new HashMap();
    }

    i(APlayerAndroid aPlayerAndroid) {
        this.g = null;
        this.a = null;
        this.b = 0;
        this.h = 0;
        e.put(Integer.valueOf(R.styleable.AppCompatTheme_actionModeCloseButtonStyle), "video/avc");
        e.put(Integer.valueOf(XZBDevice.Upload), "video/mp4v-es");
        this.f = aPlayerAndroid;
    }

    public final int a() {
        String str = (String) e.get(Integer.valueOf(this.b));
        new StringBuilder("mimeType = ").append(str).append("codeid = ").append(this.b);
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (!codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                for (int i2 = 0; i2 < supportedTypes.length; i2++) {
                    if (supportedTypes[i2].equalsIgnoreCase(str)) {
                        String name = codecInfoAt.getName();
                        if (name.indexOf("google") == -1) {
                            this.a = name;
                            return 1;
                        }
                    }
                }
                continue;
            }
        }
        return 0;
    }

    public final int b() {
        String str = (String) e.get(Integer.valueOf(this.b));
        new StringBuilder("mimeType = ").append(str).append("codeid = ").append(this.b);
        MediaCodecInfo[] codecInfos = new MediaCodecList(1).getCodecInfos();
        for (int i = 0; i < codecInfos.length; i++) {
            MediaCodecInfo mediaCodecInfo = codecInfos[i];
            if (!mediaCodecInfo.isEncoder()) {
                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                for (int i2 = 0; i2 < supportedTypes.length; i2++) {
                    if (supportedTypes[i2].equalsIgnoreCase(str)) {
                        String name = mediaCodecInfo.getName();
                        if (name.indexOf("google") == -1) {
                            this.a = name;
                            return 1;
                        }
                    }
                }
                continue;
            }
        }
        return 0;
    }

    public final int a(ByteBuffer byteBuffer) {
        int GetVideoHeight = this.f.GetVideoHeight();
        int GetVideoWidth = this.f.GetVideoWidth();
        long GetDuration = ((long) this.f.GetDuration()) * 1000;
        Surface innerSurface = this.f.getInnerSurface();
        this.c = byteBuffer;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setInteger("height", GetVideoHeight);
        mediaFormat.setInteger("width", GetVideoWidth);
        mediaFormat.setString("mime", (String) e.get(Integer.valueOf(this.b)));
        mediaFormat.setLong("durationUs", GetDuration);
        mediaFormat.setByteBuffer("csd-0", this.c);
        this.c.position(0);
        return a(mediaFormat, innerSurface) ? 1 : 0;
    }

    public final synchronized void c() {
        if (this.g != null) {
            try {
                this.g.stop();
            } catch (Exception e) {
                e.toString();
            }
            try {
                this.g.release();
                this.g = null;
            } catch (Exception e2) {
                e2.toString();
            }
        }
    }

    public final synchronized boolean d() {
        return this.g != null;
    }

    private boolean a(MediaFormat mediaFormat, Surface surface) {
        boolean z = false;
        if (this.a == null || this.a.length() == 0) {
            return false;
        }
        try {
            this.g = MediaCodec.createByCodecName(this.a);
            try {
                this.g.configure(mediaFormat, surface, null, 0);
                this.g.start();
                e();
                z = true;
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return z;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            new StringBuilder("createByCodecName fail mHWDecoderName = ").append(this.a);
            return false;
        }
    }

    public final void e() {
        if (this.g != null) {
            try {
                this.g.flush();
            } catch (Exception e) {
                e.toString();
            }
        }
    }

    public final synchronized int a(ByteBuffer byteBuffer, long j) {
        int i;
        if (VERSION.SDK_INT < 16) {
            i = 0;
        } else if (VERSION.SDK_INT < 21) {
            i = b(byteBuffer, j);
        } else {
            i = c(byteBuffer, j);
        }
        return i;
    }

    private synchronized int b(ByteBuffer byteBuffer, long j) {
        int i;
        if (this.g == null) {
            i = -1;
        } else {
            try {
                ByteBuffer[] inputBuffers = this.g.getInputBuffers();
                this.g.getOutputBuffers();
                long j2 = 0;
                BufferInfo bufferInfo = new BufferInfo();
                if (byteBuffer != null) {
                    long j3 = 0;
                    while (true) {
                        int dequeueInputBuffer = this.g.dequeueInputBuffer(1000);
                        if (dequeueInputBuffer >= 0) {
                            inputBuffers[dequeueInputBuffer].put(byteBuffer);
                            this.g.queueInputBuffer(dequeueInputBuffer, 0, byteBuffer.limit(), j, 0);
                            j2 = j3;
                        } else {
                            int dequeueOutputBuffer = this.g.dequeueOutputBuffer(bufferInfo, 1000);
                            switch (dequeueOutputBuffer) {
                                case AppbarJsBridge.Code_Java_Exception:
                                    inputBuffers = this.g.getInputBuffers();
                                    break;
                                case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                                    new StringBuilder("KEY_COLOR_FORMAT =  ").append(this.g.getOutputFormat().getInteger("color-format"));
                                    break;
                                case SniffingResourceGroup.PAGETYPE_NONE:
                                    break;
                                default:
                                    j2 = bufferInfo.presentationTimeUs;
                                    if (j2 < 0) {
                                        j2 = 0;
                                    }
                                    this.g.releaseOutputBuffer(dequeueOutputBuffer, true);
                                    j3 = j2;
                                    break;
                            }
                        }
                    }
                }
                int dequeueOutputBuffer2 = this.g.dequeueOutputBuffer(bufferInfo, 1000);
                switch (dequeueOutputBuffer2) {
                    case AppbarJsBridge.Code_Java_Exception:
                        this.g.getOutputBuffers();
                        i = 0;
                        break;
                    case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                        new StringBuilder("KEY_COLOR_FORMAT =  ").append(this.g.getOutputFormat().getInteger("color-format"));
                        i = 0;
                        break;
                    case SniffingResourceGroup.PAGETYPE_NONE:
                        i = (int) j2;
                        break;
                    default:
                        j2 = bufferInfo.presentationTimeUs;
                        if (j2 < 0) {
                            j2 = 0;
                        }
                        this.g.releaseOutputBuffer(dequeueOutputBuffer2, true);
                        i = (int) j2;
                        break;
                }
            } catch (Exception e) {
                i = -1;
            }
        }
        return i;
    }

    private synchronized int c(ByteBuffer byteBuffer, long j) {
        int i;
        new StringBuilder("HardwareDecoder21 Decode java enter timestamp = ").append(j).append("size = ").append(byteBuffer.remaining());
        if (this.g == null) {
            i = 0;
        } else {
            long j2 = 0;
            try {
                BufferInfo bufferInfo = new BufferInfo();
                if (byteBuffer != null) {
                    long j3 = 0;
                    while (true) {
                        int dequeueInputBuffer = this.g.dequeueInputBuffer(1000);
                        if (dequeueInputBuffer >= 0) {
                            this.g.getInputBuffer(dequeueInputBuffer).put(byteBuffer);
                            this.g.queueInputBuffer(dequeueInputBuffer, 0, byteBuffer.limit(), j, 0);
                            j2 = j3;
                        } else {
                            i = this.g.dequeueOutputBuffer(bufferInfo, 10000);
                            switch (i) {
                                case AppbarJsBridge.Code_Java_Exception:
                                case SniffingResourceGroup.PAGETYPE_NONE:
                                    break;
                                case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                                    new StringBuilder("HardwareDecoder21 Decode java KEY_COLOR_FORMAT =  ").append(this.g.getOutputFormat().getInteger("color-format"));
                                    break;
                                default:
                                    this.g.getOutputBuffer(i);
                                    this.g.releaseOutputBuffer(i, true);
                                    j2 = bufferInfo.presentationTimeUs;
                                    if (j2 < 0) {
                                        j2 = 0;
                                    }
                                    j3 = j2;
                                    break;
                            }
                        }
                    }
                }
                int dequeueOutputBuffer = this.g.dequeueOutputBuffer(bufferInfo, 10000);
                switch (dequeueOutputBuffer) {
                    case AppbarJsBridge.Code_Java_Exception:
                        i = 0;
                        break;
                    case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                        new StringBuilder("HardwareDecoder21 Decode java KEY_COLOR_FORMAT =  ").append(this.g.getOutputFormat().getInteger("color-format"));
                        i = 0;
                        break;
                    case SniffingResourceGroup.PAGETYPE_NONE:
                        i = (int) j2;
                        break;
                    default:
                        this.g.getOutputBuffer(dequeueOutputBuffer);
                        this.g.releaseOutputBuffer(dequeueOutputBuffer, true);
                        j2 = bufferInfo.presentationTimeUs;
                        if (j2 < 0) {
                            j2 = 0;
                        }
                        i = (int) j2;
                        break;
                }
            } catch (Exception e) {
                i = -1;
            }
        }
        return i;
    }
}
