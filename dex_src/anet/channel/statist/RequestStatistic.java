package anet.channel.statist;

import anet.channel.entity.ConnType;
import anet.channel.status.NetworkStatusHelper;
import com.umeng.a;

@Monitor(module = "networkPrefer", monitorPoint = "network")
// compiled from: Taobao
public class RequestStatistic extends StatObject {
    @Dimension
    public volatile String bizId;
    @Measure
    public volatile long firstDataTime;
    @Dimension
    public volatile String host;
    @Dimension
    public volatile String ip;
    @Dimension
    public volatile boolean isDNS;
    @Dimension
    public volatile boolean isProxy;
    @Dimension
    public volatile boolean isSSL;
    @Dimension
    public volatile String msg;
    @Dimension
    public volatile String netType;
    @Measure(max = 60000.0d)
    public volatile long oneWayTime;
    @Dimension
    public volatile int port;
    @Dimension
    public volatile String protocolType;
    @Dimension
    public volatile String proxyType;
    @Measure
    public volatile long recDataSize;
    @Measure
    public volatile long recDataTime;
    @Dimension
    public volatile boolean ret;
    @Dimension
    public volatile int retryTimes;
    @Measure
    public volatile long sendBeforeTime;
    @Measure
    public volatile long sendDataSize;
    @Measure
    public volatile long sendDataTime;
    @Measure
    public volatile long serverRT;
    @Measure
    public volatile long start;
    @Dimension
    public volatile int statusCode;
    @Deprecated
    public volatile long tcpLinkDate;
    public volatile String url;
    @Measure
    public volatile long waitingTime;

    public RequestStatistic(String str, String str2) {
        boolean z = false;
        this.proxyType = a.d;
        this.netType = a.d;
        this.isDNS = false;
        this.statusCode = 0;
        this.msg = a.d;
        this.start = 0;
        this.firstDataTime = 0;
        this.sendDataTime = 0;
        this.sendDataSize = 0;
        this.recDataTime = 0;
        this.recDataSize = 0;
        this.serverRT = 0;
        this.sendBeforeTime = 0;
        this.oneWayTime = 0;
        this.waitingTime = 0;
        this.tcpLinkDate = 0;
        this.host = str;
        this.proxyType = NetworkStatusHelper.g();
        if (!this.proxyType.isEmpty()) {
            z = true;
        }
        this.isProxy = z;
        this.netType = NetworkStatusHelper.b();
        this.bizId = str2;
    }

    public void setConnType(ConnType connType) {
        this.isSSL = connType.isSSL();
        this.protocolType = connType.toString();
    }

    public void setIPAndPort(String str, int i) {
        this.ip = str;
        this.port = i;
        if (str != null && i != 0) {
            this.isDNS = true;
        }
    }
}
