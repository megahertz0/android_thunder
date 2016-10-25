package com.taobao.accs.ut.monitor;

import anet.channel.statist.Dimension;
import anet.channel.statist.Measure;
import anet.channel.statist.Monitor;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.taobao.accs.utl.BaseMonitor;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;

@Monitor(module = "accs", monitorPoint = "netperformance")
// compiled from: Taobao
public class NetPerformanceMonitor extends BaseMonitor {
    private long a;
    @Dimension
    public int accs_sdk_version;
    @Dimension
    public int accs_type;
    private long b;
    private long c;
    private long d;
    public String data_id;
    public String device_id;
    @Dimension
    public int error_code;
    @Dimension
    public String fail_reasons;
    @Dimension
    public String host;
    @Measure(constantValue = 0.0d, max = 60000.0d, min = 0.0d)
    public long in_queue_time;
    @Measure(constantValue = 0.0d, max = 60000.0d, min = 0.0d)
    public long receive_to_call_back_time;
    @Dimension
    public String ret;
    @Dimension
    public int retry_times;
    @Measure(constantValue = 0.0d, max = 60000.0d, min = 0.0d)
    public long send_to_receive_time;
    @Dimension
    public String service_id;
    @Measure(constantValue = 0.0d, max = 60000.0d, min = 0.0d)
    public long start_to_enter_queue_time;
    public long take_date;
    @Measure(constantValue = 0.0d, max = 60000.0d, min = 0.0d)
    public long talk_to_send_time;
    public long to_tnet_date;
    @Measure(constantValue = 0.0d, max = 60000.0d, min = 0.0d)
    public long total_time;

    public NetPerformanceMonitor() {
        this.service_id = IXAdSystemUtils.NT_NONE;
    }

    public void setDeviceId(String str) {
        this.device_id = str;
    }

    public void setServiceId(String str) {
        this.service_id = str;
    }

    public void setDataId(String str) {
        this.data_id = str;
    }

    public void onSend() {
        this.a = System.currentTimeMillis();
    }

    public void onEnterQueueData() {
        this.b = System.currentTimeMillis();
    }

    public void onTakeFromQueue() {
        this.take_date = System.currentTimeMillis();
    }

    public void onSendData() {
        this.to_tnet_date = System.currentTimeMillis();
    }

    public void setRet(boolean z) {
        this.ret = z ? "y" : IXAdRequestInfo.AD_COUNT;
    }

    public void setFailReason(String str) {
        this.fail_reasons = str;
    }

    public void setFailReason(int i) {
        this.error_code = i;
        switch (i) {
            case ErrCode.ERR_AUTH_DENIED:
                setFailReason("msg too large");
            case AppbarJsBridge.Code_Java_Exception:
                setFailReason("service not available");
            case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                setFailReason("param error");
            case SniffingResourceGroup.PAGETYPE_NONE:
                setFailReason("network fail");
            case Impl.STATUS_SUCCESS:
                break;
            case XLErrorCode.OAUTH_PARAM_ERROR:
                setFailReason("app not bind");
            default:
                setFailReason(String.valueOf(i));
        }
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void onRecAck() {
        this.c = System.currentTimeMillis();
    }

    public void onToBizDate() {
        this.d = System.currentTimeMillis();
    }

    public void setConnType(int i) {
        this.accs_type = i;
    }

    public boolean beforeCommit() {
        this.accs_sdk_version = 212;
        this.total_time = a(this.a, this.d);
        this.start_to_enter_queue_time = a(this.a, this.b);
        this.in_queue_time = a(this.b, this.take_date);
        this.talk_to_send_time = a(this.take_date, this.to_tnet_date);
        this.send_to_receive_time = a(this.to_tnet_date, this.c);
        this.receive_to_call_back_time = a(this.c, this.d);
        return super.beforeCommit();
    }

    private long a(long j, long j2) {
        return (j <= 0 || j2 <= 0) ? 0 : j2 - j;
    }
}
