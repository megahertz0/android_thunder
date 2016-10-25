package anet.channel;

import anet.channel.a.a;
import anet.channel.statist.RequestStatistic;
import java.util.List;
import java.util.Map;

// compiled from: Taobao
public interface RequestCb {
    void onDataReceive(a aVar, boolean z);

    void onFinish(int i, String str, RequestStatistic requestStatistic);

    void onResponseCode(int i, Map<String, List<String>> map);
}
