package com.baidu.mobads.openad.e;

import android.os.Build.VERSION;
import anet.channel.util.HttpConstant;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.d.d;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.bugly.Bugly;
import com.tencent.connect.common.Constants;
import com.umeng.a;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

class b extends Thread {
    final /* synthetic */ d a;
    final /* synthetic */ double b;
    final /* synthetic */ a c;

    b(a aVar, d dVar, double d) {
        this.c = aVar;
        this.a = dVar;
        this.b = d;
    }

    public void run() {
        InputStream inputStream = null;
        try {
            String str;
            if (this.a.c > 0) {
                Thread.sleep(this.a.c);
            }
            this.c.e.set(true);
            this.c.g = (HttpURLConnection) new URL(this.a.a).openConnection();
            this.c.g.setConnectTimeout((int) this.b);
            this.c.g.setUseCaches(false);
            if (this.a.b != null && this.a.b.length() > 0) {
                this.c.g.setRequestProperty("User-Agent", this.a.b);
            }
            this.c.g.setRequestProperty("Content-type", this.a.d);
            this.c.g.setRequestProperty(HttpConstant.CONNECTION, "keep-alive");
            this.c.g.setRequestProperty(HttpConstant.CACHE_CONTROL, "no-cache");
            if (Integer.parseInt(VERSION.SDK) < 8) {
                System.setProperty("http.keepAlive", Bugly.SDK_IS_DEV);
            }
            if (a.b != null) {
                str = a.d;
                if (this.c.d != null) {
                    str = this.c.d;
                }
                String a = a.b.a(this.a.a);
                if (a != null) {
                    str = str + MiPushClient.ACCEPT_TIME_SEPARATOR + a;
                }
                if (str.length() > 0) {
                    this.c.g.setRequestProperty("Cookie", str);
                }
            }
            if (this.a.e == 1) {
                this.c.g.setRequestMethod(Constants.HTTP_GET);
                this.c.g.connect();
                inputStream = this.c.g.getInputStream();
                Map headerFields = this.c.g.getHeaderFields();
                if (headerFields.containsKey(HttpConstant.SET_COOKIE)) {
                    List<String> list = (List) headerFields.get(HttpConstant.SET_COOKIE);
                    if (list != null) {
                        for (String str2 : list) {
                            a.b.a(this.a.a, str2);
                        }
                    }
                }
                if (!this.c.f.booleanValue()) {
                    this.c.dispatchEvent(new d("URLLoader.Load.Complete", a.b(inputStream), this.a.a()));
                }
            } else if (this.a.e == 0) {
                this.c.g.setRequestMethod(Constants.HTTP_POST);
                this.c.g.setDoInput(true);
                this.c.g.setDoOutput(true);
                if (this.a.b() != null) {
                    str2 = this.a.b().build().getEncodedQuery();
                    OutputStream outputStream = this.c.g.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, GameManager.DEFAULT_CHARSET));
                    bufferedWriter.write(str2);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                }
                this.c.g.connect();
                this.c.g.getResponseCode();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    m.a().f().e("OAdURLLoader", e.getMessage());
                }
                try {
                    this.c.g.disconnect();
                } catch (Exception e2) {
                }
            }
        } catch (Throwable th) {
            try {
                if (!(this.c.f.booleanValue() || this.c.h.get())) {
                    this.c.dispatchEvent(new com.baidu.mobads.openad.d.a("URLLoader.Load.Error", new StringBuilder("RuntimeError: ").append(th.toString()).toString()));
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        m.a().f().e("OAdURLLoader", e3.getMessage());
                    }
                    try {
                        this.c.g.disconnect();
                    } catch (Exception e4) {
                    }
                }
            } catch (Throwable th2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        m.a().f().e("OAdURLLoader", e5.getMessage());
                    }
                    try {
                        this.c.g.disconnect();
                    } catch (Exception e6) {
                    }
                }
            }
        }
    }
}
