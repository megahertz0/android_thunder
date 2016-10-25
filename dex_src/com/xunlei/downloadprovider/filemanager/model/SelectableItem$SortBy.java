package com.xunlei.downloadprovider.filemanager.model;

public enum SelectableItem$SortBy {
    SORT_BY_NAME_ASC,
    SORT_BY_NAME_DES,
    SORT_BY_TIME_ASC,
    SORT_BY_TIME_DES;

    static {
        SORT_BY_NAME_ASC = new SelectableItem$SortBy("SORT_BY_NAME_ASC", 0);
        SORT_BY_NAME_DES = new SelectableItem$SortBy("SORT_BY_NAME_DES", 1);
        SORT_BY_TIME_ASC = new SelectableItem$SortBy("SORT_BY_TIME_ASC", 2);
        SORT_BY_TIME_DES = new SelectableItem$SortBy("SORT_BY_TIME_DES", 3);
        a = new SelectableItem$SortBy[]{SORT_BY_NAME_ASC, SORT_BY_NAME_DES, SORT_BY_TIME_ASC, SORT_BY_TIME_DES};
    }
}
