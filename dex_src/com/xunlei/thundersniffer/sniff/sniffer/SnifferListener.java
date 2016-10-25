package com.xunlei.thundersniffer.sniff.sniffer;

import com.xunlei.thundersniffer.sniff.SniffingPageResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;

public interface SnifferListener {
    void onSnifferFinishSniffing(ThunderSniffer thunderSniffer, SniffingPageResource sniffingPageResource);

    void onSnifferProgress(ThunderSniffer thunderSniffer, float f, Object obj);

    void onSnifferResourceFound(ThunderSniffer thunderSniffer, SniffingResourceGroup sniffingResourceGroup);

    void onSnifferStartPreSniffing(ThunderSniffer thunderSniffer, Object obj);

    void onSnifferStartSniffing(ThunderSniffer thunderSniffer, Object obj);
}
