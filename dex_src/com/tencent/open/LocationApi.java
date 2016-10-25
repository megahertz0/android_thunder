package com.tencent.open;

import android.app.Activity;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.qq.e.comm.constants.Constants.KEYS;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.dataprovider.ErrorCode;
import com.tencent.open.a.f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.android.agoo.message.MessageService;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class LocationApi extends BaseApi implements com.tencent.open.c.a {
    private HandlerThread a;
    private Handler b;
    private Handler c;
    private c d;
    private Bundle e;
    private IUiListener f;

    // compiled from: ProGuard
    class AnonymousClass_1 extends Handler {
        AnonymousClass_1(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    f.b("openSDK_LOG.LocationApi", "location: get location timeout.");
                    LocationApi.this.a((int) Constants.ERROR_LOCATION_TIMEOUT, Constants.MSG_LOCATION_TIMEOUT_ERROR);
                    break;
                case R.styleable.AppCompatTheme_checkedTextViewStyle:
                    f.b("openSDK_LOG.LocationApi", "location: verify sosocode success.");
                    LocationApi.this.d.a(Global.getContext(), LocationApi.this);
                    LocationApi.this.c.sendEmptyMessageDelayed(R.styleable.AppCompatTheme_buttonStyleSmall, 10000);
                    break;
                case R.styleable.AppCompatTheme_editTextStyle:
                    f.b("openSDK_LOG.LocationApi", "location: verify sosocode failed.");
                    LocationApi.this.a((int) Constants.ERROR_LOCATION_VERIFY_FAILED, Constants.MSG_LOCATION_VERIFY_ERROR);
                    break;
            }
            super.handleMessage(message);
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ String[] a;
        final /* synthetic */ String b;

        AnonymousClass_3(String[] strArr, String str) {
            this.a = strArr;
            this.b = str;
        }

        public void run() {
            if (this.a != null && this.a.length != 0) {
                com.tencent.connect.a.a.a(Global.getContext(), LocationApi.this.mToken, "search_nearby".equals(this.b) ? "id_search_nearby" : "id_delete_location", this.a);
            }
        }
    }

    // compiled from: ProGuard
    private abstract class a implements IRequestListener {
        protected abstract void a(Exception exception);

        private a() {
        }

        public void onIOException(IOException iOException) {
            a(iOException);
        }

        public void onMalformedURLException(MalformedURLException malformedURLException) {
            a(malformedURLException);
        }

        public void onJSONException(JSONException jSONException) {
            a(jSONException);
        }

        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            a(connectTimeoutException);
        }

        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            a(socketTimeoutException);
        }

        public void onNetworkUnavailableException(NetworkUnavailableException networkUnavailableException) {
            a(networkUnavailableException);
        }

        public void onHttpStatusException(HttpStatusException httpStatusException) {
            a(httpStatusException);
        }

        public void onUnknowException(Exception exception) {
            a(exception);
        }
    }

    // compiled from: ProGuard
    private class b extends a {
        private IUiListener c;

        public b(IUiListener iUiListener) {
            super(null);
            this.c = iUiListener;
        }

        public void onComplete(JSONObject jSONObject) {
            if (this.c != null) {
                this.c.onComplete(jSONObject);
            }
            f.b("openSDK_LOG.LocationApi", new StringBuilder("TaskRequestListener onComplete GetNearbySwitchEnd:").append(SystemClock.elapsedRealtime()).toString());
        }

        protected void a(Exception exception) {
            if (this.c != null) {
                this.c.onError(new UiError(100, exception.getMessage(), null));
            }
        }
    }

    public LocationApi(QQToken qQToken) {
        super(qQToken);
        a();
    }

    public LocationApi(QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
        a();
    }

    private void a() {
        this.d = new c();
        this.a = new HandlerThread("get_location");
        this.a.start();
        this.b = new Handler(this.a.getLooper());
        this.c = new AnonymousClass_1(Global.getContext().getMainLooper());
    }

    public void searchNearby(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (c()) {
            this.e = bundle;
            this.f = iUiListener;
            this.b.post(new Runnable() {
                public void run() {
                    if (LocationApi.this.d.a()) {
                        Message.obtain(LocationApi.this.c, R.styleable.AppCompatTheme_checkedTextViewStyle).sendToTarget();
                    } else {
                        Message.obtain(LocationApi.this.c, R.styleable.AppCompatTheme_editTextStyle).sendToTarget();
                    }
                }
            });
        } else if (iUiListener != null) {
            iUiListener.onComplete(d());
        }
    }

    public void deleteLocation(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (c()) {
            Bundle bundle2;
            if (bundle != null) {
                bundle2 = new Bundle(bundle);
                bundle2.putAll(composeCGIParams());
            } else {
                bundle2 = composeCGIParams();
            }
            bundle2.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
            bundle2.putString("timestamp", String.valueOf(System.currentTimeMillis()));
            String str = "encrytoken";
            bundle2.putString(str, Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
            f.a("openSDK_LOG.LocationApi", new StringBuilder("location: delete params: ").append(bundle2).toString());
            HttpUtils.requestAsync(this.mToken, Global.getContext(), "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_delete.cgi", bundle2, Constants.HTTP_GET, new b(iUiListener));
            a("delete_location", MsgConstant.KEY_SUCCESS);
        } else if (iUiListener != null) {
            iUiListener.onComplete(d());
        }
    }

    private void a(Location location) {
        Bundle bundle;
        f.a("openSDK_LOG.LocationApi", new StringBuilder("doSearchNearby location: search mParams: ").append(this.e).toString());
        if (this.e != null) {
            bundle = new Bundle(this.e);
            bundle.putAll(composeCGIParams());
        } else {
            bundle = composeCGIParams();
        }
        String valueOf = String.valueOf(location.getLatitude());
        String valueOf2 = String.valueOf(location.getLongitude());
        bundle.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
        if (!bundle.containsKey(ParamKey.LATITUDE)) {
            bundle.putString(ParamKey.LATITUDE, valueOf);
        }
        if (!bundle.containsKey(ParamKey.LONGITUDE)) {
            bundle.putString(ParamKey.LONGITUDE, valueOf2);
        }
        if (!bundle.containsKey(JsInterface.KEY_PAGE)) {
            bundle.putString(JsInterface.KEY_PAGE, MessageService.MSG_DB_NOTIFY_REACHED);
        }
        valueOf2 = "encrytoken";
        bundle.putString(valueOf2, Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
        f.a("openSDK_LOG.LocationApi", new StringBuilder("location: search params: ").append(bundle).toString());
        f.b("openSDK_LOG.LocationApi", new StringBuilder("GetNearbySwitchStart:").append(SystemClock.elapsedRealtime()).toString());
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_getnear.cgi", bundle, Constants.HTTP_GET, new b(this.f));
    }

    private void a(int i, String str) {
        this.d.b();
        if (this.f != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(KEYS.RET, i);
                jSONObject.put("errMsg", str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f.onComplete(jSONObject);
        }
    }

    private void b() {
        this.d.b();
    }

    private boolean c() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Global.getContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    private JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEYS.RET, ErrorCode.FileIsEmpty);
            jSONObject.put("errMsg", Constants.MSG_IO_ERROR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void a(String str, String... strArr) {
        this.b.post(new AnonymousClass_3(strArr, str));
    }

    public void onLocationUpdate(Location location) {
        a(location);
        b();
        this.c.removeMessages(R.styleable.AppCompatTheme_buttonStyleSmall);
    }
}
