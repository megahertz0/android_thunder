package com.inmobi.rendering.mraid;

import android.os.AsyncTask;
import android.os.Environment;
import android.webkit.URLUtil;
import com.alipay.sdk.util.h;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import com.xunlei.download.DownloadManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: DownloadTask.java
public final class c extends AsyncTask<Void, Void, String> {
    private static final String a;
    private int b;
    private File c;
    private String d;
    private String e;
    private String f;
    private WeakReference<RenderView> g;
    private a h;
    private List<String> i;
    private long j;
    private String k;

    // compiled from: DownloadTask.java
    public static interface a {
        void a();

        void a(int i);
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        a((String) obj);
    }

    static {
        a = c.class.getSimpleName();
    }

    public c(String str, File file, String str2, String str3, RenderView renderView) {
        this.k = str;
        this.c = file;
        this.d = str2;
        this.e = str3;
        this.i = renderView.getRenderingConfig().h();
        this.j = renderView.getRenderingConfig().g();
        this.g = new WeakReference(renderView);
    }

    protected final String a(Void... voidArr) {
        if (!com.inmobi.commons.core.utilities.c.a()) {
            this.b = 8;
            return MsgConstant.KEY_FAIL;
        } else if (!this.e.matches("[A-Za-z0-9]+") || this.e.equals(com.umeng.a.d)) {
            this.b = 2;
            return MsgConstant.KEY_FAIL;
        } else if (this.d.equals(com.umeng.a.d) || !URLUtil.isValidUrl(this.d)) {
            this.b = 3;
            return MsgConstant.KEY_FAIL;
        } else if (Environment.getExternalStorageState().equals("mounted")) {
            String[] strArr = (String[]) this.i.toArray(new String[this.i.size()]);
            try {
                long currentTimeMillis = System.currentTimeMillis();
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.d).openConnection();
                httpURLConnection.setRequestMethod(Constants.HTTP_GET);
                httpURLConnection.setConnectTimeout(5000);
                int responseCode = httpURLConnection.getResponseCode();
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Response code: ").append(responseCode).toString());
                if (responseCode < 400) {
                    Object obj;
                    String contentType = httpURLConnection.getContentType();
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Content Type: ").append(contentType).toString());
                    for (String str : strArr) {
                        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Allowed Type: ").append(str).toString());
                        if (contentType != null && str.toLowerCase(Locale.ENGLISH).equals(contentType.toLowerCase(Locale.ENGLISH))) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    if (obj == null) {
                        this.b = 6;
                        return MsgConstant.KEY_FAIL;
                    }
                }
                long contentLength = (long) httpURLConnection.getContentLength();
                if (contentLength >= 0) {
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("ContentSize: ").append(contentLength).append(" max size: ").append(this.j).toString());
                    if (contentLength > this.j) {
                        this.b = 7;
                        return MsgConstant.KEY_FAIL;
                    }
                }
                httpURLConnection.connect();
                FileOutputStream fileOutputStream = new FileOutputStream(this.c);
                InputStream inputStream = httpURLConnection.getInputStream();
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0 || isCancelled()) {
                        break;
                    }
                    j += (long) read;
                    if (j > this.j) {
                        this.b = 7;
                        return MsgConstant.KEY_FAIL;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.close();
                j = System.currentTimeMillis();
                if (isCancelled()) {
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("cancelSaveContent called.File: ").append(this.c.getAbsolutePath()).append(" deleted: ").append(this.c.delete()).toString());
                } else {
                    String toString = new StringBuilder("file://").append(this.c.getAbsolutePath()).toString();
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("file path of video: ").append(toString).toString());
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(SocialConstants.PARAM_URL, this.d);
                    jSONObject.put("saved_url", toString);
                    jSONObject.put("size_in_bytes", this.c.length());
                    jSONObject.put("download_started_at", currentTimeMillis);
                    jSONObject.put("download_ended_at", j);
                    this.f = jSONObject.toString().replace(h.f, "\\\"");
                }
                return MsgConstant.KEY_SUCCESS;
            } catch (SocketTimeoutException e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "SocketTimeoutException");
                this.b = 4;
                return MsgConstant.KEY_FAIL;
            } catch (FileNotFoundException e2) {
                Logger.a(InternalLogLevel.INTERNAL, a, "FileNotFoundException");
                this.b = 4;
                return MsgConstant.KEY_FAIL;
            } catch (MalformedURLException e3) {
                Logger.a(InternalLogLevel.INTERNAL, a, "MalformedURLException");
                this.b = 3;
                return MsgConstant.KEY_FAIL;
            } catch (ProtocolException e4) {
                Logger.a(InternalLogLevel.INTERNAL, a, "ProtocolException");
                this.b = 8;
                return MsgConstant.KEY_FAIL;
            } catch (IOException e5) {
                Logger.a(InternalLogLevel.INTERNAL, a, "IOException");
                this.b = 8;
                return MsgConstant.KEY_FAIL;
            } catch (JSONException e6) {
                Logger.a(InternalLogLevel.INTERNAL, a, "JSONException");
                this.b = 0;
                return MsgConstant.KEY_FAIL;
            }
        } else {
            this.b = 10;
            return MsgConstant.KEY_FAIL;
        }
    }

    protected final void onCancelled() {
        super.onCancelled();
    }

    protected final void a(String str) {
        if (str.equals(MsgConstant.KEY_SUCCESS)) {
            if (this.g.get() != null) {
                ((RenderView) this.g.get()).a(this.k, new StringBuilder("sendSaveContentResult(\"saveContent_").append(this.e).append("\", 'success', \"").append(this.f).append("\");").toString());
            }
            if (this.h != null) {
                this.h.a();
            }
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(SocialConstants.PARAM_URL, this.d);
                jSONObject.put(DownloadManager.COLUMN_REASON, this.b);
                String replace = jSONObject.toString().replace(h.f, "\\\"");
                if (this.g.get() != null) {
                    ((RenderView) this.g.get()).a(this.k, new StringBuilder("sendSaveContentResult(\"saveContent_").append(this.e).append("\", 'failed', \"").append(replace).append("\");").toString());
                }
                if (this.h != null) {
                    this.h.a(this.b);
                }
            } catch (JSONException e) {
                if (this.g.get() != null) {
                    ((RenderView) this.g.get()).a(this.k, new StringBuilder("sendSaveContentResult(\"saveContent_").append(this.e).append("\", 'failed', \"JSONException\");").toString());
                }
            }
        }
        super.onPostExecute(str);
    }

    public final String a() {
        return this.e;
    }
}
