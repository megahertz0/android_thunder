package com.xunlei.downloadprovider.filemanager.model;

public abstract class SelectableItem implements Comparable<i> {
    public static SortBy b;
    public boolean a;

    public SelectableItem() {
        this.a = false;
    }

    static {
        b = SortBy.SORT_BY_NAME_ASC;
    }
}
