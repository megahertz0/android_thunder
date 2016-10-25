package com.xunlei.downloadprovider.personal.playrecord;

public enum PlayRecordFragment$OntainState {
    idle,
    obtaining,
    refreshing,
    auto_refreshing,
    auto_obtaining;

    static {
        idle = new PlayRecordFragment$OntainState("idle", 0);
        obtaining = new PlayRecordFragment$OntainState("obtaining", 1);
        refreshing = new PlayRecordFragment$OntainState("refreshing", 2);
        auto_refreshing = new PlayRecordFragment$OntainState("auto_refreshing", 3);
        auto_obtaining = new PlayRecordFragment$OntainState("auto_obtaining", 4);
        a = new PlayRecordFragment$OntainState[]{idle, obtaining, refreshing, auto_refreshing, auto_obtaining};
    }
}
