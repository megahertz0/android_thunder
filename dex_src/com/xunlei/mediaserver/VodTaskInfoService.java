package com.xunlei.mediaserver;

import android.os.Handler;
import android.os.Message;
import com.umeng.message.util.HttpRequest;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

public class VodTaskInfoService {
    private static final byte CR = (byte) 13;
    private static final String TAG = "VodTaskInfoService";
    private static Socket mSocket;
    public final int MSG_VODINFO;
    private boolean isStop;
    private Handler mCallBackHandler;
    private Handler mHandler;
    private InputStream mInputStream;
    private MediaServer$OnVodTaskInfoListener mOnVodTaskInfoListener;
    private OutputStream mOutputStream;
    private int mPort;
    private String mServer;
    private VodTaskInfo mVodTaskInfo;
    Thread receiveThead;

    static {
        mSocket = null;
    }

    public VodTaskInfoService(MediaServer$OnVodTaskInfoListener mediaServer$OnVodTaskInfoListener, String str, int i) {
        this.isStop = false;
        this.MSG_VODINFO = 1;
        this.mCallBackHandler = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (VodTaskInfoService.this.mOnVodTaskInfoListener != null) {
                            VodTaskInfoService.this.mOnVodTaskInfoListener.onGetVodTask((VodTaskInfo) message.obj);
                        }
                    default:
                        break;
                }
            }
        };
        this.receiveThead = new Thread() {
            String msgStr;

            {
                this.msgStr = null;
            }

            public void run() {
                while (!VodTaskInfoService.this.isStop) {
                    try {
                        this.msgStr = VodTaskInfoService.this.readResponse(VodTaskInfoService.this.mInputStream);
                        if (this.msgStr != null) {
                            if (VodTaskInfoService.this.mVodTaskInfo.setJsonToObject(this.msgStr.toString(), VodTaskInfoService.this.mVodTaskInfo)) {
                                Message message = new Message();
                                message.what = 1;
                                message.obj = VodTaskInfoService.this.mVodTaskInfo;
                                VodTaskInfoService.this.mCallBackHandler.sendMessage(message);
                            }
                            this.msgStr = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        VodTaskInfoService.this.uninit();
                    }
                }
            }
        };
        this.mOnVodTaskInfoListener = mediaServer$OnVodTaskInfoListener;
        this.mServer = str;
        this.mPort = i;
        this.mVodTaskInfo = new VodTaskInfo();
    }

    public void init(String str, int i) {
        new StringBuilder("init socket client andr server=").append(str).append(":").append(i);
        try {
            mSocket = new Socket(str, i);
            this.mInputStream = mSocket.getInputStream();
            this.mOutputStream = mSocket.getOutputStream();
            this.receiveThead.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            uninit();
        } catch (IOException e2) {
            e2.printStackTrace();
            uninit();
        }
    }

    public void sendCommand(String str) {
        try {
            if (this.mOutputStream == null) {
                if (mSocket != null) {
                    this.mOutputStream = mSocket.getOutputStream();
                } else {
                    init(this.mServer, this.mPort);
                }
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.mOutputStream);
            bufferedOutputStream.write(buildHttpHead(str).getBytes());
            bufferedOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            uninit();
        }
    }

    public String buildHttpHead(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("GET ");
        stringBuffer.append(str);
        stringBuffer.append(" HTTP/1.1\r\n");
        stringBuffer.append("Connection: Keep-Alive\r\n");
        stringBuffer.append("\r\n");
        return stringBuffer.toString();
    }

    private String readResponse(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        Map readHeaders = readHeaders(inputStream);
        String str = (String) readHeaders.get(HttpRequest.k);
        if (str == null) {
            return null;
        }
        byte[] readResponseBody = readResponseBody(inputStream, Integer.valueOf(str).intValue());
        str = (String) readHeaders.get(HttpRequest.l);
        return new String(readResponseBody, str.matches(".+;charset=.+") ? str.split(";")[1].split("=")[1] : CharsetConvert.ISO_8859_1);
    }

    private Map<String, String> readHeaders(InputStream inputStream) throws IOException {
        Map<String, String> hashMap = new HashMap();
        while (true) {
            String str = BuildConfig.VERSION_NAME;
            String readLine = readLine(inputStream);
            if (str.equals(readLine)) {
                return hashMap;
            }
            String[] split = readLine.split(": ");
            if (split.length >= 2) {
                hashMap.put(split[0], split[1]);
            }
        }
    }

    private byte[] readResponseBody(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (i2 >= i) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(inputStream.read());
            i2 = i3;
        }
    }

    private String readLine(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read = inputStream.read();
        while (read != 13 && read != -1) {
            byteArrayOutputStream.write(read);
            read = inputStream.read();
        }
        if (read != -1) {
            inputStream.read();
        }
        return byteArrayOutputStream.toString();
    }

    public void uninit() {
        try {
            this.isStop = true;
            if (mSocket != null) {
                mSocket.shutdownInput();
                mSocket.shutdownOutput();
                mSocket.close();
                mSocket = null;
            }
            if (mSocket != null) {
                this.mInputStream.close();
                this.mInputStream = null;
            }
            if (this.mOutputStream != null) {
                this.mOutputStream.close();
                this.mOutputStream = null;
            }
            this.mCallBackHandler.removeMessages(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
