package com.tencent.mm.sdk.diffdev.a;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Base64;
import com.tencent.mm.sdk.diffdev.OAuthErrCode;
import com.tencent.mm.sdk.diffdev.OAuthListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;

public final class d extends AsyncTask<Void, Void, a> {
    private static final boolean ai;
    private static final String aj;
    private static String ak;
    private String al;
    private String am;
    private OAuthListener an;
    private f ao;
    private String appId;
    private String scope;
    private String signature;

    static class a {
        public OAuthErrCode ap;
        public String aq;
        public String ar;
        public String as;
        public int at;
        public String au;
        public byte[] av;

        private a() {
        }

        private static boolean a(String str, byte[] bArr) {
            Exception e;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                try {
                    fileOutputStream.write(bArr);
                    fileOutputStream.flush();
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return true;
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return false;
                }
            } catch (Exception e4) {
                e = e4;
                fileOutputStream = null;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                th2 = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th2;
            }
        }

        public static a d(byte[] bArr) {
            a aVar = new a();
            if (bArr == null || bArr.length == 0) {
                aVar.ap = OAuthErrCode.WechatAuth_Err_NetworkErr;
            } else {
                try {
                    try {
                        JSONObject jSONObject = new JSONObject(new String(bArr, "utf-8"));
                        int i = jSONObject.getInt("errcode");
                        if (i != 0) {
                            String.format("resp errcode = %d", new Object[]{Integer.valueOf(i)});
                            aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                            aVar.at = i;
                            aVar.au = jSONObject.optString("errmsg");
                        } else {
                            String string = jSONObject.getJSONObject("qrcode").getString("qrcodebase64");
                            if (string == null || string.length() == 0) {
                                aVar.ap = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                            } else {
                                byte[] decode = Base64.decode(string, 0);
                                if (decode == null || decode.length == 0) {
                                    aVar.ap = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                                } else if (ai) {
                                    File file = new File(aj);
                                    file.mkdirs();
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    if (a(aj, decode)) {
                                        aVar.ap = OAuthErrCode.WechatAuth_Err_OK;
                                        aVar.as = aj;
                                        aVar.aq = jSONObject.getString("uuid");
                                        aVar.ar = jSONObject.getString("appname");
                                        String.format("parse succ, save in external storage, uuid = %s, appname = %s, imgPath = %s", new Object[]{aVar.aq, aVar.ar, aVar.as});
                                    } else {
                                        String.format("writeToFile fail, qrcodeBuf length = %d", new Object[]{Integer.valueOf(decode.length)});
                                        aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                                    }
                                } else {
                                    aVar.ap = OAuthErrCode.WechatAuth_Err_OK;
                                    aVar.av = decode;
                                    aVar.aq = jSONObject.getString("uuid");
                                    aVar.ar = jSONObject.getString("appname");
                                    String.format("parse succ, save in memory, uuid = %s, appname = %s, imgBufLength = %d", new Object[]{aVar.aq, aVar.ar, Integer.valueOf(aVar.av.length)});
                                }
                            }
                        }
                    } catch (Exception e) {
                        String.format("parse json fail, ex = %s", new Object[]{e.getMessage()});
                        aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                    }
                } catch (Exception e2) {
                    String.format("parse fail, build String fail, ex = %s", new Object[]{e2.getMessage()});
                    aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                }
            }
            return aVar;
        }
    }

    static {
        boolean z = Environment.getExternalStorageState().equals("mounted") && new File(Environment.getExternalStorageDirectory().getAbsolutePath()).canWrite();
        ai = z;
        aj = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tencent/MicroMsg/oauth_qrcode.png";
        ak = null;
        ak = "http://open.weixin.qq.com/connect/sdk/qrconnect?appid=%s&noncestr=%s&timestamp=%s&scope=%s&signature=%s";
    }

    public d(String str, String str2, String str3, String str4, String str5, OAuthListener oAuthListener) {
        this.appId = str;
        this.scope = str2;
        this.al = str3;
        this.am = str4;
        this.signature = str5;
        this.an = oAuthListener;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        new StringBuilder("external storage available = ").append(ai);
        String format = String.format(ak, new Object[]{this.appId, this.al, this.am, this.scope, this.signature});
        long currentTimeMillis = System.currentTimeMillis();
        byte[] b = e.b(format, -1);
        String.format("doInBackground, url = %s, time consumed = %d(ms)", new Object[]{format, Long.valueOf(System.currentTimeMillis() - currentTimeMillis)});
        return a.d(b);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        a aVar = (a) obj;
        if (aVar.ap == OAuthErrCode.WechatAuth_Err_OK) {
            this.an.onAuthGotQrcode(aVar.as, aVar.av);
            this.ao = new f(aVar.aq, this.an);
            f fVar = this.ao;
            if (VERSION.SDK_INT >= 11) {
                fVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                return;
            } else {
                fVar.execute(new Void[0]);
                return;
            }
        }
        String.format("onPostExecute, get qrcode fail, OAuthErrCode = %s", new Object[]{aVar.ap});
        this.an.onAuthFinish(aVar.ap, null);
    }

    public final boolean q() {
        return this.ao == null ? cancel(true) : this.ao.cancel(true);
    }
}
