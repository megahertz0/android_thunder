package com.qq.e.comm.managers.setting;

import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.bugly.BuglyStrategy.a;
import com.xunlei.tdlive.R;

public final class b extends c {
    public b() {
        a(KEYS.SDKServerGetADReportSamplingRate, Integer.valueOf(1));
        a(KEYS.SDKServerExpReportSamplingRate, Integer.valueOf(1));
        a(KEYS.SDKServerClickReportSamplingRate, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle));
        a(KEYS.RequireWindowFocus, Integer.valueOf(1));
        a(KEYS.SHOW_LOGO, Integer.valueOf(0));
        a(KEYS.INNER_BROWSER_SCHEME, "weixin,tel,openapp.jdmobile");
        a(KEYS.THIRD_PARTY_BROWSER, "com.android.browser,com.android.chrome,com.baidu.browser.apps,com.UCMobile,com.tencent.mtt");
        a(KEYS.Banner_RF, Integer.valueOf(a.MAX_USERDATA_VALUE_LENGTH));
        a(KEYS.SPLASH_LOADTIMEOUT, Integer.valueOf(3000));
        a(KEYS.SPLASH_EXPOSURE_TIME, Integer.valueOf(5000));
        a(KEYS.SPLASH_NETWORK_PERMISION, Integer.valueOf(R.styleable.AppCompatTheme_actionMenuTextColor));
        a(KEYS.SPLASH_MAX_REQUEST_NUM, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle));
        a(KEYS.FORCE_EXPOSURE, Integer.valueOf(1));
    }
}
