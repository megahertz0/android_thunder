package com.xunlei.downloadprovider.bho;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThunderTaskBHOActivity extends ThunderTask {
    private c a;
    private Bundle b;
    private b c;
    private com.xunlei.downloadprovider.a.h.a d;
    private com.xunlei.downloadprovider.a.h.b e;

    static class a {
        public final ArrayList<DownData> a;
        public String b;
        public String c;
        public String d;

        a() {
            this.a = new ArrayList(1);
            this.b = com.umeng.a.d;
            this.c = com.umeng.a.d;
            this.d = com.umeng.a.d;
        }

        static a a(Intent intent) {
            Bundle bundle = new Bundle();
            Object stringExtra = intent.getStringExtra("urls");
            String stringExtra2 = intent.getStringExtra("partner_id");
            String stringExtra3 = intent.getStringExtra("app_id");
            String stringExtra4 = intent.getStringExtra("sdk_key");
            bundle.putString("app_id", intent.getStringExtra("app_id"));
            bundle.putString("partner_id", intent.getStringExtra("partner_id"));
            bundle.putString("sdk_key", intent.getStringExtra("sdk_key"));
            a aVar = new a();
            aVar.b = stringExtra2;
            aVar.c = stringExtra3;
            aVar.d = stringExtra4;
            if (TextUtils.isEmpty(stringExtra)) {
                return aVar;
            }
            try {
                JSONObject jSONObject = new JSONObject(stringExtra);
                stringExtra2 = jSONObject.optString(Impl.COLUMN_REFERER);
                JSONArray optJSONArray = jSONObject.optJSONArray("urls");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            Object optString = optJSONObject.optString(SocialConstants.PARAM_URL);
                            String optString2 = optJSONObject.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                            String optString3 = jSONObject.optString(Impl.COLUMN_REFERER);
                            if (!TextUtils.isEmpty(optString)) {
                                DownData downData = new DownData();
                                downData.e = optString;
                                if (optString3 == null) {
                                    optString3 = stringExtra2;
                                }
                                downData.s = optString3;
                                downData.a = optString2;
                                aVar.a.add(downData);
                            }
                        }
                    }
                }
                bundle.putParcelableArrayList("tasks", aVar.a);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return aVar.a.isEmpty() ? null : aVar;
        }

        public final String toString() {
            return new StringBuilder("AddTaskActionParam{mDownloadInfos=").append(this.a).append(", mPartnerId='").append(this.b).append('\'').append('}').toString();
        }
    }

    static class b implements DialogInterface {
        public View a;
        private TextView b;
        private TextView c;
        private TextView d;
        private TextView e;
        private OnClickListener f;
        private OnCancelListener g;
        private OnDismissListener h;

        public b(Activity activity) {
            this.a = activity.findViewById(2131755290);
            this.a.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            View view = this.a;
            this.b = (TextView) view.findViewById(R.id.dlg_title);
            this.c = (TextView) view.findViewById(R.id.dlg_content);
            this.d = (TextView) view.findViewById(R.id.dlg_cancel_btn);
            this.e = (TextView) view.findViewById(R.id.dlg_confirm_btn);
            this.d.setOnClickListener(new m(this));
            this.e.setOnClickListener(new n(this));
        }

        public final void cancel() {
            if (this.a != null) {
                this.a.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                if (this.g != null) {
                    this.g.onCancel(this);
                }
            }
        }

        public final void dismiss() {
            if (this.a != null) {
                this.a.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                if (this.h != null) {
                    this.h.onDismiss(this);
                }
            }
        }
    }

    private static class c {
        String a;
        String b;
        a c;

        private c() {
        }

        public final String toString() {
            return new StringBuilder("TaskData{mName='").append(this.b).append('\'').append(", mUrl='").append(this.a).append('\'').append('}').toString();
        }
    }

    public ThunderTaskBHOActivity() {
        this.d = new k(this);
        this.e = new com.xunlei.downloadprovider.a.h.b(this.d);
    }

    private void a() {
        finish();
        DownloadCenterActivity.a((Context) this, this.b);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968621);
        this.c = new b(this);
        a(getIntent());
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
        a(intent);
    }

    private void a(Intent intent) {
        c cVar;
        c cVar2 = new c();
        String action = intent.getAction();
        if (action == null) {
            cVar = null;
        } else {
            if (action.equals("android.intent.action.VIEW")) {
                cVar2.a = intent.getDataString();
                cVar2.b = intent.getStringExtra(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                if (!TextUtils.isEmpty(cVar2.a)) {
                    cVar2.a = cVar2.a.replace("\r\n", com.umeng.a.d);
                }
            } else if (action.equals("com.xunlei.downloadprovider.ADD_TASK")) {
                cVar2.a = intent.getStringExtra(SocialConstants.PARAM_URL);
                cVar2.b = intent.getStringExtra(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                cVar2.c = a.a(intent);
                if (TextUtils.isEmpty(cVar2.a) && cVar2.c != null && cVar2.c.a.size() > 0) {
                    cVar2.a = ((DownData) cVar2.c.a.get(0)).e;
                    cVar2.b = ((DownData) cVar2.c.a.get(0)).a;
                }
            }
            cVar = cVar2;
        }
        this.b = b(intent);
        this.a = cVar;
        if (DownloadService.a() == null) {
            DownloadService.a(new l(this));
        } else {
            a();
        }
        g a = g.a("android_launch", "launch_sdk", "launch_sdk");
        if (this.b != null) {
            a.a(Constants.PARAM_PLATFORM, anet.channel.strategy.dispatch.a.ANDROID, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            String str = com.umeng.a.d;
            action = com.umeng.a.d;
            if (this.b != null) {
                action = this.b.getString("app_id");
                str = this.b.getString("partner_id");
            }
            a.a(SocialConstants.PARAM_APP_ID, action, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("sdkid", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a(com.taobao.accs.common.Constants.KEY_IMEI, com.xunlei.downloadprovider.a.b.c(BrothersApplication.a()), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
            a.a("mac", com.xunlei.downloadprovider.a.b.e(), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        }
        ThunderReporter.a(a, true);
    }

    private static Bundle b(Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putString("app_id", intent.getStringExtra("app_id"));
        bundle.putString("partner_id", intent.getStringExtra("partner_id"));
        bundle.putString("sdk_key", intent.getStringExtra("sdk_key"));
        ArrayList arrayList = new ArrayList(1);
        Object stringExtra = intent.getStringExtra(SocialConstants.PARAM_URL);
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = intent.getStringExtra("urls");
            if (!TextUtils.isEmpty(stringExtra)) {
                try {
                    JSONObject jSONObject = new JSONObject(stringExtra);
                    String optString = jSONObject.optString(Impl.COLUMN_REFERER);
                    JSONArray optJSONArray = jSONObject.optJSONArray("urls");
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                Object optString2 = optJSONObject.optString(SocialConstants.PARAM_URL);
                                String optString3 = optJSONObject.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                                String optString4 = jSONObject.optString(Impl.COLUMN_REFERER);
                                if (!TextUtils.isEmpty(optString2)) {
                                    DownData downData = new DownData();
                                    downData.e = optString2;
                                    if (optString4 == null) {
                                        optString4 = optString;
                                    }
                                    downData.s = optString4;
                                    downData.a = optString3;
                                    arrayList.add(downData);
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            DownData downData2 = new DownData();
            downData2.e = stringExtra;
            downData2.s = intent.getStringExtra(Impl.COLUMN_REFERER);
            downData2.a = intent.getStringExtra(SelectCountryActivity.EXTRA_COUNTRY_NAME);
            arrayList.add(downData2);
        }
        bundle.putParcelableArrayList("tasks", arrayList);
        return bundle;
    }

    static /* synthetic */ void b(ThunderTaskBHOActivity thunderTaskBHOActivity) {
        String str = MessageService.MSG_DB_READY_REPORT;
        String str2 = MessageService.MSG_DB_READY_REPORT;
        if (!(thunderTaskBHOActivity.a == null || thunderTaskBHOActivity.a.c == null)) {
            str = thunderTaskBHOActivity.a.c.c;
            str2 = thunderTaskBHOActivity.a.c.b;
        }
        com.xunlei.downloadprovider.service.downloads.a.a.a("BHO_SDK", str, str2);
    }
}
