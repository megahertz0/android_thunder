package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.tdlive.protocol.PushTagRequest;

public enum SniffSuffixTypeDataManager$OperSniffSuffixSet {
    add,
    delete;

    static {
        add = new SniffSuffixTypeDataManager$OperSniffSuffixSet(PushTagRequest.T_ADD, 0);
        delete = new SniffSuffixTypeDataManager$OperSniffSuffixSet("delete", 1);
        a = new SniffSuffixTypeDataManager$OperSniffSuffixSet[]{add, delete};
    }
}
