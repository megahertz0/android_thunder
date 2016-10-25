package com.xunlei.downloadprovider.personal.settings;

import android.content.SharedPreferences.Editor;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.xunlei.downloadprovider.player.a.c;

// compiled from: AutoPlaySettingActivity.java
final class i implements OnCheckedChangeListener {
    final /* synthetic */ AutoPlaySettingActivity a;

    i(AutoPlaySettingActivity autoPlaySettingActivity) {
        this.a = autoPlaySettingActivity;
    }

    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.a.f = Integer.parseInt((String) this.a.findViewById(i).getTag());
        c a = c.a();
        String str = c.c;
        int a2 = this.a.f;
        if (a.a != null) {
            a.b = a2;
            Editor edit = a.a.edit();
            edit.putInt(str, a2);
            edit.apply();
        }
        AutoPlaySettingActivity.a(this.a.f);
    }
}
