package com.xunlei.common.lixian;

import com.xunlei.mediaserver.Utility;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xllib.R;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class XLLixianFileType implements Serializable {
    public static final int ARCHIVE = 103;
    public static final int AUDIO = 101;
    public static final int EXECUTION = 105;
    public static final int PICTURE = 102;
    public static final int TEXT = 104;
    public static final int TORRENT = 106;
    public static final int UNKNOWN = 108;
    public static final int URL = 107;
    public static final int VIDEO = 100;
    private static Map s_mapFiletype = null;
    private static final long serialVersionUID = -4019071855952560609L;
    private int value;

    static {
        Map hashMap = new HashMap();
        s_mapFiletype = hashMap;
        hashMap.put(Integer.valueOf(SpdyProtocol.PUBKEY_SEQ_OPEN), "rmvb");
        s_mapFiletype.put(Integer.valueOf(SpdyProtocol.PUBKEY_PSEQ_OPEN), "rm");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_titleMargins), "avi");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_titleMarginStart), "mkv");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_titleMarginEnd), "wmv");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_titleMarginTop), "mp4");
        s_mapFiletype.put(Integer.valueOf(SpdyProtocol.CUSTOM), "3gp");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_maxButtonHeight), "m4v");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_collapseIcon), "flv");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_collapseContentDescription), "ts");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_navigationContentDescription), "mov");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_logoDescription), "mpg");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_titleTextColor), "mpeg");
        s_mapFiletype.put(Integer.valueOf(R.styleable.Toolbar_subtitleTextColor), "asf");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_actionMenuTextAppearance), "swf");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_actionMenuTextColor), "xlmv");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_actionModeStyle), "vob");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_actionModeCloseButtonStyle), "mpe");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_actionModeBackground), "dat");
        s_mapFiletype.put(Integer.valueOf(MqttConnectOptions.CONNECTION_TIMEOUT_DEFAULT), "clpi");
        s_mapFiletype.put(Integer.valueOf(120), "xv");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_textAppearanceLargePopupMenu), "mp3");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu), "flac");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_dialogTheme), "ape");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_dialogPreferredPadding), "aac");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_listDividerAlertDialog), "wma");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_actionDropDownStyle), "wav");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_buttonBarStyle), "jpg");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_buttonBarButtonStyle), "jpeg");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_selectableItemBackground), "gif");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_selectableItemBackgroundBorderless), "png");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_borderlessButtonStyle), "bmp");
        s_mapFiletype.put(Integer.valueOf(MqttConnectOptions.KEEP_ALIVE_INTERVAL_DEFAULT), "rar");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_popupWindowStyle), "zip");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_editTextColor), "iso");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_editTextBackground), "img");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_imageButtonStyle), "mds");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_textAppearanceSearchResultTitle), "mdf");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle), "gz");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_listPreferredItemHeightSmall), "txt");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_listPreferredItemHeightLarge), "ppt");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_listPreferredItemPaddingLeft), "pptx");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_listPreferredItemPaddingRight), "xls");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_dropDownListViewStyle), "xlsx");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_listPopupWindowStyle), "pdf");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_textAppearanceListItem), "ass");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_textAppearanceListItemSmall), "htm");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_panelBackground), "html");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_panelMenuListWidth), "mpls");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_panelMenuListTheme), "srt");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_listChoiceBackgroundIndicator), "doc");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_colorPrimary), "docx");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_colorPrimaryDark), "cpp");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_colorAccent), "c");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_colorControlNormal), "h");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_controlBackground), "exe");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_alertDialogStyle), "msi");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_alertDialogButtonGroupStyle), "apk");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_textColorAlertDialogListItem), "torrent");
        s_mapFiletype.put(Integer.valueOf(R.styleable.AppCompatTheme_colorControlActivated), SHubBatchQueryKeys.url);
    }

    public XLLixianFileType(int i) {
        this.value = 0;
        this.value = i;
    }

    public int getClassType() {
        return ((this.value < 10 || this.value > 30 || this.value == 20) && this.value != 120) ? (this.value < 40 || this.value > 45) ? (this.value < 50 || this.value > 54) ? (this.value < 60 || this.value > 66) ? (this.value < 70 || this.value > 85) ? (this.value < 90 || this.value > 92) ? this.value == 95 ? TORRENT : this.value == 86 ? URL : UNKNOWN : EXECUTION : TEXT : ARCHIVE : PICTURE : AUDIO : VIDEO;
    }

    public int getId() {
        return this.value;
    }

    public String getSuffix() {
        return s_mapFiletype.containsKey(Integer.valueOf(this.value)) ? (String) s_mapFiletype.get(Integer.valueOf(this.value)) : Utility.NETWORK_OTHER;
    }
}
