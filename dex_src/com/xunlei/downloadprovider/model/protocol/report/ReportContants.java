package com.xunlei.downloadprovider.model.protocol.report;

import com.alipay.sdk.util.e;
import com.umeng.message.MsgConstant;

public interface ReportContants {

    public static interface Vod {

        public enum VodReportPlayState {
            success,
            failed;

            static {
                success = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Vod.VodReportPlayState(MsgConstant.KEY_SUCCESS, 0);
                failed = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Vod.VodReportPlayState(e.b, 1);
                a = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Vod.VodReportPlayState[]{success, failed};
            }
        }
    }
}
