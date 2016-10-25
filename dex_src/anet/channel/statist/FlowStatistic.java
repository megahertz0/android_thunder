package anet.channel.statist;

import anet.channel.GlobalAppRuntimeInfo;

@Monitor(module = "networkPrefer", monitorPoint = "flow")
// compiled from: Taobao
public class FlowStatistic extends StatObject {
    public static final String F_REFER_PARAM = "f-refer";
    @Measure
    public long f_downstream;
    @Dimension
    public boolean f_isbackground;
    @Dimension
    public String f_protocoltype;
    @Dimension
    public String f_refer;
    @Measure
    public long f_upstream;

    public FlowStatistic() {
        this.f_isbackground = GlobalAppRuntimeInfo.isAppBackground();
    }

    public FlowStatistic(String str, RequestStatistic requestStatistic) {
        this.f_refer = str;
        this.f_protocoltype = requestStatistic.protocolType;
        this.f_upstream = requestStatistic.sendDataSize;
        this.f_downstream = requestStatistic.recDataSize;
    }
}
