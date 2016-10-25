package com.taobao.accs.ut.monitor;

import anet.channel.statist.Dimension;
import anet.channel.statist.Measure;
import anet.channel.statist.Monitor;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.taobao.accs.utl.BaseMonitor;

@Monitor(module = "accs", monitorPoint = "session")
// compiled from: Taobao
public class SessionMonitor extends BaseMonitor {
    private long a;
    @Measure(constantValue = 0.0d, max = 15000.0d, min = 0.0d)
    public long auth_time;
    private long b;
    @Dimension
    public int close_connection_type;
    @Dimension
    public String close_reasons;
    @Dimension
    public String connect_type;
    public long connection_stop_date;
    @Dimension
    public int fail_reasons;
    @Dimension
    public boolean isProxy;
    @Measure(constantValue = 0.0d, max = 86400.0d, min = 0.0d)
    public long live_time;
    @Measure(constantValue = 0.0d)
    public int ping_rec_times;
    @Measure(constantValue = 0.0d)
    public int ping_send_times;
    @Dimension
    public boolean ret;
    @Dimension
    public int retry_times;
    @Dimension
    public String sdkv;
    @Measure(constantValue = 0.0d, max = 15000.0d, min = 0.0d)
    public long ssl_time;
    @Measure(constantValue = 0.0d, max = 15000.0d, min = 0.0d)
    public long tcp_time;

    public SessionMonitor() {
        this.ret = false;
        this.fail_reasons = 0;
        this.close_reasons = IXAdSystemUtils.NT_NONE;
        this.close_connection_type = 2;
        this.connect_type = IXAdSystemUtils.NT_NONE;
        this.isProxy = false;
        this.sdkv = "212";
    }

    public void setRet(boolean z) {
        this.ret = z;
    }

    public boolean getRet() {
        return this.ret;
    }

    public void setFailReason(int i) {
        this.fail_reasons = i;
    }

    public void onStartConnect() {
        this.a = System.currentTimeMillis();
    }

    public void onConnectStop() {
        this.connection_stop_date = System.currentTimeMillis();
    }

    public void onCloseConnect() {
        this.b = System.currentTimeMillis();
    }

    public void setCloseType(int i) {
        this.close_connection_type = i;
    }

    public void setCloseReason(String str) {
        this.close_reasons = str;
    }

    public void onSendPing() {
        this.ping_send_times++;
    }

    public void onPingCBReceive() {
        this.ping_rec_times++;
    }

    public String getCloseReason() {
        return this.close_reasons;
    }

    public void setRetryTimes(int i) {
        this.retry_times = i;
    }

    public void setConnectType(String str) {
        this.connect_type = str;
    }

    public long getConStopDate() {
        return this.connection_stop_date;
    }

    public long getConCloseDate() {
        return this.b;
    }
}
