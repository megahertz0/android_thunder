package com.xunlei.downloadprovider.personal.lixianspace;

public enum LixianSpaceFragment$OntainState {
    idle,
    obtaining,
    refreshing,
    auto_refreshing,
    auto_obtaining;

    static {
        idle = new LixianSpaceFragment$OntainState("idle", 0);
        obtaining = new LixianSpaceFragment$OntainState("obtaining", 1);
        refreshing = new LixianSpaceFragment$OntainState("refreshing", 2);
        auto_refreshing = new LixianSpaceFragment$OntainState("auto_refreshing", 3);
        auto_obtaining = new LixianSpaceFragment$OntainState("auto_obtaining", 4);
        a = new LixianSpaceFragment$OntainState[]{idle, obtaining, refreshing, auto_refreshing, auto_obtaining};
    }
}
