package com.xunlei.XLStat.Net;

import com.umeng.message.util.HttpRequest;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.android.spdy.SpdyRequest;

public class a {
    public static String a;
    public static String b;
    private static String c;

    static {
        c = "HttpHelper";
        a = BuildConfig.VERSION_NAME;
        b = BuildConfig.VERSION_NAME;
    }

    public static int a(String str, String str2) throws IOException {
        XLStatLog.d(c, "doGet", new StringBuilder("GET_URL: ").append(str).append(", data: ").append(str2).toString());
        if (str == null || BuildConfig.VERSION_NAME.equals(str)) {
            XLStatLog.d(c, "doGet", "monitor url is null ... ");
            return -1;
        }
        a = str;
        String str3 = a + "?" + str2;
        XLStatLog.d(c, "doGet", new StringBuilder("getUrlData: ").append(str3).toString());
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str3).openConnection();
        httpURLConnection.setRequestMethod(SpdyRequest.GET_METHOD);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setRequestProperty(HttpRequest.l, "text/html; charset=UTF-8");
        httpURLConnection.setDoOutput(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        int responseCode = httpURLConnection.getResponseCode();
        bufferedReader.close();
        httpURLConnection.disconnect();
        XLStatLog.d(c, "doGet", new StringBuilder("responseCode: ").append(responseCode).toString());
        return responseCode;
    }
}
