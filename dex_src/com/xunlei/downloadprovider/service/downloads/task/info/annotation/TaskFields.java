package com.xunlei.downloadprovider.service.downloads.task.info.annotation;

public enum TaskFields {
    CORE,
    EXTRA;

    static {
        CORE = new TaskFields("CORE", 0);
        EXTRA = new TaskFields("EXTRA", 1);
        a = new TaskFields[]{CORE, EXTRA};
    }
}
