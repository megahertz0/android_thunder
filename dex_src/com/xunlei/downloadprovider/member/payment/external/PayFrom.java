package com.xunlei.downloadprovider.member.payment.external;

import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public enum PayFrom {
    PERSONAL_CENTER_ICON,
    PERSONAL_CENTER_TOP,
    PERSONAL_CENTER_RENEWTIP,
    VIP_CENTER,
    ACCOUNT_CENTER,
    LIXIAN_SPACE,
    LIXIAN_SPACE_RENEWTIP,
    PLAY_LIST,
    PLAY_LIST_RENEWTIP,
    NEARBY_RESOURCE,
    NEARBY_STATION,
    DOWNLOAD_TOTAL,
    DOWNLOAD_TASK,
    DOWNLOAD_TASK_DETAIL,
    DOWNLOAD_TASK_RENEWTIP,
    DOWNLOAD_TASK_LIXIAN,
    DOWNLOAD_TASK_HIGH_SPEED,
    DOWNLOAD_NOTIFICATION,
    BIRD_PAGE,
    BIRD_NOTICE,
    BIRD_TIP,
    PROMOTION_CHOU_JIANG,
    DOWNLOAD_TASK_NEW,
    DOWNLOAD_TASK_NEW_SLIDE,
    DOWNLOAD_TASK_SPEED_UP,
    DOWNLOAD_TASK_FREE_TRIAL;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[PayFrom.values().length];
            try {
                a[PERSONAL_CENTER_ICON.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[PERSONAL_CENTER_TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[PERSONAL_CENTER_RENEWTIP.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[VIP_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ACCOUNT_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[LIXIAN_SPACE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[LIXIAN_SPACE_RENEWTIP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[PLAY_LIST.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[PLAY_LIST_RENEWTIP.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[NEARBY_RESOURCE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[NEARBY_STATION.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[DOWNLOAD_TOTAL.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[DOWNLOAD_TASK.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[DOWNLOAD_TASK_HIGH_SPEED.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[DOWNLOAD_TASK_LIXIAN.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[DOWNLOAD_TASK_DETAIL.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[DOWNLOAD_TASK_RENEWTIP.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[DOWNLOAD_NOTIFICATION.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[BIRD_PAGE.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[BIRD_NOTICE.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[BIRD_TIP.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                a[PROMOTION_CHOU_JIANG.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                a[DOWNLOAD_TASK_NEW.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                a[DOWNLOAD_TASK_NEW_SLIDE.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                a[DOWNLOAD_TASK_SPEED_UP.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
        }
    }

    static {
        PERSONAL_CENTER_ICON = new PayFrom("PERSONAL_CENTER_ICON", 0);
        PERSONAL_CENTER_TOP = new PayFrom("PERSONAL_CENTER_TOP", 1);
        PERSONAL_CENTER_RENEWTIP = new PayFrom("PERSONAL_CENTER_RENEWTIP", 2);
        VIP_CENTER = new PayFrom("VIP_CENTER", 3);
        ACCOUNT_CENTER = new PayFrom("ACCOUNT_CENTER", 4);
        LIXIAN_SPACE = new PayFrom("LIXIAN_SPACE", 5);
        LIXIAN_SPACE_RENEWTIP = new PayFrom("LIXIAN_SPACE_RENEWTIP", 6);
        PLAY_LIST = new PayFrom("PLAY_LIST", 7);
        PLAY_LIST_RENEWTIP = new PayFrom("PLAY_LIST_RENEWTIP", 8);
        NEARBY_RESOURCE = new PayFrom("NEARBY_RESOURCE", 9);
        NEARBY_STATION = new PayFrom("NEARBY_STATION", 10);
        DOWNLOAD_TOTAL = new PayFrom("DOWNLOAD_TOTAL", 11);
        DOWNLOAD_TASK = new PayFrom("DOWNLOAD_TASK", 12);
        DOWNLOAD_TASK_DETAIL = new PayFrom("DOWNLOAD_TASK_DETAIL", 13);
        DOWNLOAD_TASK_RENEWTIP = new PayFrom("DOWNLOAD_TASK_RENEWTIP", 14);
        DOWNLOAD_TASK_LIXIAN = new PayFrom("DOWNLOAD_TASK_LIXIAN", 15);
        DOWNLOAD_TASK_HIGH_SPEED = new PayFrom("DOWNLOAD_TASK_HIGH_SPEED", 16);
        DOWNLOAD_NOTIFICATION = new PayFrom("DOWNLOAD_NOTIFICATION", 17);
        BIRD_PAGE = new PayFrom("BIRD_PAGE", 18);
        BIRD_NOTICE = new PayFrom("BIRD_NOTICE", 19);
        BIRD_TIP = new PayFrom("BIRD_TIP", 20);
        PROMOTION_CHOU_JIANG = new PayFrom("PROMOTION_CHOU_JIANG", 21);
        DOWNLOAD_TASK_NEW = new PayFrom("DOWNLOAD_TASK_NEW", 22);
        DOWNLOAD_TASK_NEW_SLIDE = new PayFrom("DOWNLOAD_TASK_NEW_SLIDE", 23);
        DOWNLOAD_TASK_SPEED_UP = new PayFrom("DOWNLOAD_TASK_SPEED_UP", 24);
        DOWNLOAD_TASK_FREE_TRIAL = new PayFrom("DOWNLOAD_TASK_FREE_TRIAL", 25);
        a = new PayFrom[]{PERSONAL_CENTER_ICON, PERSONAL_CENTER_TOP, PERSONAL_CENTER_RENEWTIP, VIP_CENTER, ACCOUNT_CENTER, LIXIAN_SPACE, LIXIAN_SPACE_RENEWTIP, PLAY_LIST, PLAY_LIST_RENEWTIP, NEARBY_RESOURCE, NEARBY_STATION, DOWNLOAD_TOTAL, DOWNLOAD_TASK, DOWNLOAD_TASK_DETAIL, DOWNLOAD_TASK_RENEWTIP, DOWNLOAD_TASK_LIXIAN, DOWNLOAD_TASK_HIGH_SPEED, DOWNLOAD_NOTIFICATION, BIRD_PAGE, BIRD_NOTICE, BIRD_TIP, PROMOTION_CHOU_JIANG, DOWNLOAD_TASK_NEW, DOWNLOAD_TASK_NEW_SLIDE, DOWNLOAD_TASK_SPEED_UP, DOWNLOAD_TASK_FREE_TRIAL};
    }

    public final String toFrom() {
        switch (AnonymousClass_1.a[ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "personal_center_icon";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "personal_center_top";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "personal_center_renewtip";
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return "vip_center";
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return "account_center";
            case R.styleable.Toolbar_contentInsetEnd:
                return "lixian_space";
            case R.styleable.Toolbar_contentInsetLeft:
                return "lixian_space_renewtip";
            case XZBDevice.Wait:
                return "play_list";
            case XZBDevice.Pause:
                return "play_list_renewtip";
            case XZBDevice.Stop:
                return "nearby_source";
            case XZBDevice.Success:
                return "nearby_station";
            case XZBDevice.Fail:
                return "download_total";
            case XZBDevice.Upload:
            case XZBDevice.Predownload:
            case XZBDevice.Delete:
            case R.styleable.Toolbar_titleMarginBottom:
                return "download_task";
            case R.styleable.Toolbar_maxButtonHeight:
                return "download_task_renewtip";
            case R.styleable.Toolbar_collapseIcon:
                return "download_notification";
            case R.styleable.Toolbar_collapseContentDescription:
                return "bird_page";
            case R.styleable.Toolbar_navigationIcon:
                return "bird_notice";
            case R.styleable.Toolbar_navigationContentDescription:
                return "bird_tip";
            case R.styleable.Toolbar_logoDescription:
                return "promotion_chou_jiang";
            case R.styleable.Toolbar_titleTextColor:
                return "download_task";
            case R.styleable.Toolbar_subtitleTextColor:
                return "download_task_slide";
            case R.styleable.AppCompatTheme_actionMenuTextAppearance:
                return "download_task_speedup";
            default:
                return a.d;
        }
    }

    public final boolean isFromKuaiNiao() {
        return this == BIRD_PAGE || this == BIRD_NOTICE || this == BIRD_TIP;
    }
}
