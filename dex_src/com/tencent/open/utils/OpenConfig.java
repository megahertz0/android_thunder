package com.tencent.open.utils;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.umeng.a;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class OpenConfig {
    private static Map<String, OpenConfig> a;
    private static String b;
    private Context c;
    private String d;
    private JSONObject e;
    private long f;
    private int g;
    private boolean h;

    // compiled from: ProGuard
    class AnonymousClass_1 extends Thread {
        final /* synthetic */ Bundle a;

        AnonymousClass_1(Bundle bundle) {
            this.a = bundle;
        }

        public void run() {
            try {
                OpenConfig.this.a(Util.parseJson(HttpUtils.openUrl2(OpenConfig.this.c, "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", Constants.HTTP_GET, this.a).response));
            } catch (Exception e) {
                e.printStackTrace();
            }
            OpenConfig.this.g = 0;
        }
    }

    static {
        a = Collections.synchronizedMap(new HashMap());
        b = null;
    }

    public static OpenConfig getInstance(Context context, String str) {
        OpenConfig openConfig;
        synchronized (a) {
            f.a("openSDK_LOG.OpenConfig", "getInstance begin");
            if (str != null) {
                b = str;
            }
            if (str == null) {
                str = b != null ? b : MessageService.MSG_DB_READY_REPORT;
            }
            openConfig = (OpenConfig) a.get(str);
            if (openConfig == null) {
                openConfig = new OpenConfig(context, str);
                a.put(str, openConfig);
            }
            f.a("openSDK_LOG.OpenConfig", "getInstance end");
        }
        return openConfig;
    }

    private OpenConfig(Context context, String str) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = true;
        this.c = context.getApplicationContext();
        this.d = str;
        a();
        b();
    }

    private void a() {
        try {
            this.e = new JSONObject(a("com.tencent.open.config.json"));
        } catch (JSONException e) {
            this.e = new JSONObject();
        }
    }

    private String a(String str) {
        InputStream openFileInput;
        String str2 = a.d;
        try {
            String str3;
            if (this.d != null) {
                str3 = str + "." + this.d;
            } else {
                str3 = str;
            }
            openFileInput = this.c.openFileInput(str3);
        } catch (FileNotFoundException e) {
            try {
                openFileInput = this.c.getAssets().open(str);
            } catch (IOException e2) {
                e2.printStackTrace();
                return str2;
            }
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput, Charset.forName(GameManager.DEFAULT_CHARSET)));
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    str2 = stringBuffer.toString();
                    try {
                        openFileInput.close();
                        bufferedReader.close();
                        return str2;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        return str2;
                    }
                }
            } catch (IOException e3) {
                try {
                    e3.printStackTrace();
                    try {
                        openFileInput.close();
                        bufferedReader.close();
                        return str2;
                    } catch (IOException e222) {
                        e222.printStackTrace();
                        return str2;
                    }
                } catch (Throwable th) {
                    try {
                        openFileInput.close();
                        bufferedReader.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
            }
        }
    }

    private void a(String str, String str2) {
        try {
            if (this.d != null) {
                str = str + "." + this.d;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.c.openFileOutput(str, 0), Charset.forName(GameManager.DEFAULT_CHARSET));
            outputStreamWriter.write(str2);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void b() {
        if (this.g != 0) {
            b("update thread is running, return");
            return;
        }
        this.g = 1;
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_APP_ID, this.d);
        bundle.putString("appid_for_getting_config", this.d);
        bundle.putString("status_os", VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", VERSION.SDK);
        bundle.putString(com.taobao.accs.common.Constants.KEY_ELECTION_SDKV, Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
        new AnonymousClass_1(bundle).start();
    }

    private void a(JSONObject jSONObject) {
        b("cgi back, do update");
        this.e = jSONObject;
        a("com.tencent.open.config.json", jSONObject.toString());
        this.f = SystemClock.elapsedRealtime();
    }

    private void c() {
        int optInt = this.e.optInt("Common_frequency");
        if (optInt == 0) {
            optInt = 1;
        }
        if (SystemClock.elapsedRealtime() - this.f >= ((long) (optInt * 3600000))) {
            b();
        }
    }

    public int getInt(String str) {
        b(new StringBuilder("get ").append(str).toString());
        c();
        return this.e.optInt(str);
    }

    public long getLong(String str) {
        b(new StringBuilder("get ").append(str).toString());
        c();
        return this.e.optLong(str);
    }

    public boolean getBoolean(String str) {
        b(new StringBuilder("get ").append(str).toString());
        c();
        Object opt = this.e.opt(str);
        if (opt == null) {
            return false;
        }
        return opt instanceof Integer ? !opt.equals(Integer.valueOf(0)) : opt instanceof Boolean ? ((Boolean) opt).booleanValue() : false;
    }

    private void b(String str) {
        if (this.h) {
            f.a("openSDK_LOG.OpenConfig", str + "; appid: " + this.d);
        }
    }
}
