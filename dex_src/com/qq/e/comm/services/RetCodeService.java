package com.qq.e.comm.services;

import com.alipay.sdk.packet.d;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.managers.status.SDKStatus;
import com.qq.e.comm.net.NetworkClient.Priority;
import com.qq.e.comm.net.NetworkClientImpl;
import com.qq.e.comm.net.rr.PlainRequest;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Request.Method;
import com.qq.e.comm.services.RetCodeService.RetCodeInfo;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.utils.ServerSetting;
import com.umeng.a;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Random;

public class RetCodeService {
    private final Random a;

    static class Holder {
        static final RetCodeService a;

        static {
            a = new RetCodeService();
        }

        private Holder() {
        }
    }

    public static class RetCodeInfo {
        final String a;
        final String b;
        final String c;
        final int d;
        final int e;
        final int f;
        final int g;
        final int h;

        public RetCodeInfo(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = i;
            this.e = i2;
            this.f = i3;
            this.g = i4;
            this.h = i5;
        }

        public String toString() {
            return new StringBuilder("RetCodeInfo [host=").append(this.a).append(", commandid=").append(this.b).append(", releaseversion=").append(this.c).append(", resultcode=").append(this.d).append(", tmcost=").append(this.e).append(", reqsize=").append(this.f).append(", rspsize=").append(this.g).append("]").toString();
        }
    }

    class SendTask implements Runnable {
        private RetCodeInfo a;
        private int b;

        SendTask(RetCodeInfo retCodeInfo) {
            this.a = retCodeInfo;
            this.b = 100;
        }

        public void run() {
            RetCodeService.a(RetCodeService.this, this.a, this.b);
        }
    }

    private RetCodeService() {
        this.a = new Random(System.currentTimeMillis());
    }

    private static String a(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            return "0.0.0.0";
        }
    }

    static /* synthetic */ void a(RetCodeService retCodeService, RetCodeInfo retCodeInfo, int i) {
        Request plainRequest;
        if (retCodeService.a(i)) {
            plainRequest = new PlainRequest(ServerSetting.DEFAULT_URL_REPORT, Method.GET, null);
            plainRequest.addQuery(SocialConstants.PARAM_APP_ID, "1000162");
            plainRequest.addQuery("apn", String.valueOf(GDTADManager.getInstance().getDeviceStatus().getNetworkType().getConnValue()));
            plainRequest.addQuery("resultcode", String.valueOf(retCodeInfo.d));
            plainRequest.addQuery("sdkversion", SDKStatus.getSDKVersion());
            plainRequest.addQuery("touin", a.d);
            plainRequest.addQuery("tmcost", String.valueOf(retCodeInfo.e));
            plainRequest.addQuery("reqsize", String.valueOf(retCodeInfo.f));
            plainRequest.addQuery("rspsize", String.valueOf(retCodeInfo.g));
            plainRequest.addQuery("frequency", String.valueOf(i));
            try {
                String encode = URLEncoder.encode(GDTADManager.getInstance().getDeviceStatus().model, "utf-8");
                plainRequest.addQuery("deviceinfo", encode);
                plainRequest.addQuery(d.n, encode);
                plainRequest.addQuery("commandid", URLEncoder.encode(retCodeInfo.b, "utf-8"));
                plainRequest.addQuery("releaseversion", URLEncoder.encode(retCodeInfo.c, "utf-8"));
                plainRequest.addQuery("serverip", URLEncoder.encode(a(retCodeInfo.a), "utf-8"));
                NetworkClientImpl.getInstance().submit(plainRequest, Priority.Low);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (retCodeService.a(i)) {
            plainRequest = new PlainRequest("http://c.isdspeed.qq.com/code.cgi", Method.GET, null);
            plainRequest.addQuery(anet.channel.strategy.dispatch.a.DOMAIN, retCodeInfo.a);
            plainRequest.addQuery("cgi", retCodeInfo.b);
            plainRequest.addQuery(JsInterface.FUNPLAY_AD_TRPE, String.valueOf(retCodeInfo.h));
            plainRequest.addQuery(Constants.KEY_HTTP_CODE, String.valueOf(retCodeInfo.d));
            plainRequest.addQuery("time", String.valueOf(retCodeInfo.e));
            plainRequest.addQuery("rate", String.valueOf(i));
            NetworkClientImpl.getInstance().submit(plainRequest, Priority.Low);
        }
    }

    private boolean a(int i) {
        return this.a.nextDouble() < 1.0d / ((double) i);
    }

    public static RetCodeService getInstance() {
        return Holder.a;
    }

    public void send(RetCodeInfo retCodeInfo) {
        new Thread(new SendTask(retCodeInfo)).start();
    }
}
