package com.xunlei.downloadprovider.util.sniff;

import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xllib.b.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: SnifferRules.java
final class g {
    boolean a;
    HashMap<String, a> b;
    long c;
    private String d;

    // compiled from: SnifferRules.java
    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;

        public a(String str) {
            this.a = str;
        }
    }

    g() {
        this.a = false;
        this.b = new HashMap();
        this.c = 0;
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("sniffer_rules");
            if (optJSONObject != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("sites");
                if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                    Iterator keys = optJSONObject2.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        JSONObject optJSONObject3 = optJSONObject2.optJSONObject(str);
                        if (optJSONObject3 != null) {
                            a aVar = new a(str);
                            aVar.b = optJSONObject3.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
                            aVar.c = optJSONObject3.optString("search_url");
                            Object optString = optJSONObject3.optString("sniff_js");
                            if (!TextUtils.isEmpty(optString)) {
                                aVar.d = b.a(optString, "utf-8");
                            }
                            this.b.put(str, aVar);
                        }
                    }
                }
            }
            this.a = true;
        }
    }

    final String a() {
        if (TextUtils.isEmpty(this.d)) {
            this.d = BrothersApplication.a().getCacheDir().getAbsolutePath() + com.xunlei.downloadprovider.a.b.x() + "athunder_sniffer_rules.json";
        }
        return this.d;
    }

    final boolean b() {
        return System.currentTimeMillis() - this.c >= 5000;
    }

    public final boolean c() {
        InputStream open;
        InputStreamReader inputStreamReader;
        IOException e;
        Throwable th;
        JSONObject jSONObject;
        try {
            File file = new File(a());
            FileInputStream fileInputStream;
            if (file.exists()) {
                new StringBuilder("[athunder_sniff_rules]loadConfigureFromFile - cache : ").append(file.getAbsolutePath());
                fileInputStream = new FileInputStream(file);
            } else {
                fileInputStream = null;
            }
            if (r2 == null) {
                try {
                    open = BrothersApplication.a().getAssets().open("athunder_sniffer_rules.json");
                } catch (Exception e2) {
                    e2.printStackTrace();
                    open = r2;
                }
            } else {
                open = r2;
            }
        } catch (Exception e22) {
            try {
                e22.printStackTrace();
                try {
                    open = BrothersApplication.a().getAssets().open("athunder_sniffer_rules.json");
                } catch (Exception e222) {
                    e222.printStackTrace();
                    open = null;
                }
            } catch (Throwable th2) {
                try {
                    BrothersApplication.a().getAssets().open("athunder_sniffer_rules.json");
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (open != null) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                inputStreamReader = new InputStreamReader(open);
                try {
                    char[] cArr = new char[1024];
                    while (true) {
                        int read = inputStreamReader.read(cArr, 0, 1024);
                        if (read > 0) {
                            stringBuilder.append(cArr, 0, read);
                        } else {
                            try {
                                break;
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                    inputStreamReader.close();
                    try {
                        open.close();
                    } catch (IOException e42) {
                        e42.printStackTrace();
                    }
                } catch (IOException e5) {
                    e42 = e5;
                    try {
                        e42.printStackTrace();
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException e422) {
                                e422.printStackTrace();
                            }
                        }
                        open.close();
                    } catch (Throwable th3) {
                        th = th3;
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        try {
                            open.close();
                        } catch (IOException e62) {
                            e62.printStackTrace();
                        }
                        throw th;
                    }
                    jSONObject = new JSONObject(stringBuilder.toString());
                    a(jSONObject);
                    return true;
                }
            } catch (IOException e7) {
                e422 = e7;
                inputStreamReader = null;
                e422.printStackTrace();
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                try {
                    open.close();
                } catch (IOException e4222) {
                    e4222.printStackTrace();
                }
                try {
                    jSONObject = new JSONObject(stringBuilder.toString());
                } catch (JSONException e8) {
                    e8.printStackTrace();
                    jSONObject = null;
                }
                a(jSONObject);
                return true;
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader = null;
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                open.close();
                throw th;
            }
            jSONObject = new JSONObject(stringBuilder.toString());
            a(jSONObject);
        }
        return true;
    }
}
