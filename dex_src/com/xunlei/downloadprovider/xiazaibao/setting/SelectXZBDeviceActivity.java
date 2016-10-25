package com.xunlei.downloadprovider.xiazaibao.setting;

import android.app.Activity;
import android.os.Bundle;
import com.xunlei.xiazaibao.R;

public class SelectXZBDeviceActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_select_xzbdevice);
        getFragmentManager().beginTransaction().add(R.id.select_xzbdevice_container, e.a()).commit();
    }
}
