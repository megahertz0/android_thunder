package anet.channel.statist;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.status.NetworkStatusHelper;

@Monitor(module = "networkPrefer", monitorPoint = "amdc")
// compiled from: Taobao
public class AmdcStatistic extends StatObject {
    @Dimension
    public String errorCode;
    @Dimension
    public String errorMsg;
    @Dimension
    public String host;
    @Dimension
    public String netType;
    @Dimension
    public String proxyType;
    @Dimension
    public int retryTimes;
    @Dimension
    public String ttid;
    @Dimension
    public String url;

    public AmdcStatistic() {
        this.netType = NetworkStatusHelper.a().toString();
        this.proxyType = NetworkStatusHelper.g();
        this.ttid = GlobalAppRuntimeInfo.getTtid();
    }
}
