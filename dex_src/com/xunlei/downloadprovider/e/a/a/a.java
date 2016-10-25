package com.xunlei.downloadprovider.e.a.a;

import android.database.DataSetObserver;
import android.widget.BaseAdapter;

// compiled from: XLBaseAdapter.java
public abstract class a extends BaseAdapter {
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (dataSetObserver != null) {
            try {
                super.unregisterDataSetObserver(dataSetObserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
