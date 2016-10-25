package com.xunlei.downloadprovider.ad.common;

import com.umeng.socialize.PlatformConfig.TencentWeibo;
import com.xunlei.downloadprovider.ad.common.CommonConst.AnonymousClass_1;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public final class CommonConst {

    public enum AD_TYPE {
        SOURCE_GDT_FLAG,
        SOURCE_BAIDU_FLAG,
        SOURCE_QIHU_FLAG,
        SOURCE_INMOBI_NATIVE_FLAG,
        SOURCE_SSP_FLAG,
        SOURCE_SSP_DEFAULT_FLAG;

        static {
            SOURCE_GDT_FLAG = new com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE("SOURCE_GDT_FLAG", 0);
            SOURCE_BAIDU_FLAG = new com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE("SOURCE_BAIDU_FLAG", 1);
            SOURCE_QIHU_FLAG = new com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE("SOURCE_QIHU_FLAG", 2);
            SOURCE_INMOBI_NATIVE_FLAG = new com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE("SOURCE_INMOBI_NATIVE_FLAG", 3);
            SOURCE_SSP_FLAG = new com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE("SOURCE_SSP_FLAG", 4);
            SOURCE_SSP_DEFAULT_FLAG = new com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE("SOURCE_SSP_DEFAULT_FLAG", 5);
            a = new com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE[]{SOURCE_GDT_FLAG, SOURCE_BAIDU_FLAG, SOURCE_QIHU_FLAG, SOURCE_INMOBI_NATIVE_FLAG, SOURCE_SSP_FLAG, SOURCE_SSP_DEFAULT_FLAG};
        }

        public final String getSourceTagString() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return BrothersApplication.a().getString(2131231015);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return BrothersApplication.a().getString(2131231014);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return BrothersApplication.a().getString(2131231017);
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    return BrothersApplication.a().getString(2131231016);
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    return BrothersApplication.a().getString(2131231018);
                default:
                    return BrothersApplication.a().getString(2131231018);
            }
        }

        public final String getSourceName() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return TencentWeibo.Name;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return "baidu";
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return "360";
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    return "inmobi";
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    return "shangwu";
                case R.styleable.Toolbar_contentInsetEnd:
                    return "moren";
                default:
                    return "ssp";
            }
        }

        public final String getSourceCompanyName() {
            switch (AnonymousClass_1.a[ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return BrothersApplication.a().getString(2131230820);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return BrothersApplication.a().getString(2131230819);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return BrothersApplication.a().getString(2131230822);
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    return BrothersApplication.a().getString(2131230821);
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    return BrothersApplication.a().getString(2131230823);
                default:
                    return BrothersApplication.a().getString(2131230823);
            }
        }
    }
}
