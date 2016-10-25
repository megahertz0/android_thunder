package com.xunlei.downloadprovider.xiazaibao.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.xunlei.xiazaibao.R;

public class DownloadDevieSettingActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_downloaddevicesetting);
        getFragmentManager().beginTransaction().add(R.id.downloaddevice_setting_container, a.a()).commit();
    }

    public static void a(Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, DownloadDevieSettingActivity.class));
        }
    }
}
