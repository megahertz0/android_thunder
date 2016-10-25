package com.xunlei.downloadprovider.personal.playrecord;

public enum PlayRecordFragment$PlayRecordListState {
    uninit,
    initing,
    inited,
    destroy;

    static {
        uninit = new PlayRecordFragment$PlayRecordListState("uninit", 0);
        initing = new PlayRecordFragment$PlayRecordListState("initing", 1);
        inited = new PlayRecordFragment$PlayRecordListState("inited", 2);
        destroy = new PlayRecordFragment$PlayRecordListState("destroy", 3);
        a = new PlayRecordFragment$PlayRecordListState[]{uninit, initing, inited, destroy};
    }
}
