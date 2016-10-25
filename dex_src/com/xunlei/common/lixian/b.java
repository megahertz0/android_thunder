package com.xunlei.common.lixian;

import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public final class b {
    private static Map a;
    private int b;

    static {
        Map hashMap = new HashMap();
        a = hashMap;
        hashMap.put(Integer.valueOf(SpdyProtocol.PUBKEY_SEQ_OPEN), "rmvb");
        a.put(Integer.valueOf(SpdyProtocol.PUBKEY_PSEQ_OPEN), "rm");
        a.put(Integer.valueOf(R.styleable.Toolbar_titleMargins), "avi");
        a.put(Integer.valueOf(R.styleable.Toolbar_titleMarginStart), "mkv");
        a.put(Integer.valueOf(R.styleable.Toolbar_titleMarginEnd), "wmv");
        a.put(Integer.valueOf(R.styleable.Toolbar_titleMarginTop), "mp4");
        a.put(Integer.valueOf(SpdyProtocol.CUSTOM), "3gp");
        a.put(Integer.valueOf(R.styleable.Toolbar_maxButtonHeight), "m4v");
        a.put(Integer.valueOf(R.styleable.Toolbar_collapseIcon), "flv");
        a.put(Integer.valueOf(R.styleable.Toolbar_collapseContentDescription), "ts");
        a.put(Integer.valueOf(R.styleable.Toolbar_navigationIcon), "xv");
        a.put(Integer.valueOf(R.styleable.Toolbar_navigationContentDescription), "mov");
        a.put(Integer.valueOf(R.styleable.Toolbar_logoDescription), "mpg");
        a.put(Integer.valueOf(R.styleable.Toolbar_titleTextColor), "mpeg");
        a.put(Integer.valueOf(R.styleable.Toolbar_subtitleTextColor), "asf");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_actionMenuTextAppearance), "swf");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_actionMenuTextColor), "xlmv");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_actionModeStyle), "vob");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_actionModeCloseButtonStyle), "mpe");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_actionModeBackground), "dat");
        a.put(Integer.valueOf(MqttConnectOptions.CONNECTION_TIMEOUT_DEFAULT), "clpi");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_textAppearanceLargePopupMenu), "mp3");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu), "flac");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_dialogTheme), "ape");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_dialogPreferredPadding), "aac");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_listDividerAlertDialog), "wma");
        a.put(Integer.valueOf(R.styleable.AppCompatTheme_actionDropDownStyle), "wav");
    }

    private b(int i) {
        this.b = 0;
        this.b = i;
    }

    private int a() {
        return this.b;
    }

    private String b() {
        return a.containsKey(Integer.valueOf(this.b)) ? (String) a.get(Integer.valueOf(this.b)) : BuildConfig.VERSION_NAME;
    }
}
