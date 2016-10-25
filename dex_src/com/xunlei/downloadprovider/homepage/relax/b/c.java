package com.xunlei.downloadprovider.homepage.relax.b;

import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.RelaxDataType;
import com.xunlei.downloadprovider.model.protocol.b.d;
import java.io.Serializable;
import java.util.ArrayList;

// compiled from: RelaxListResult.java
public final class c implements Serializable {
    public int a;
    public RelaxDataType b;
    public GuestureType c;
    public ArrayList<d> d;

    public c(int i, RelaxDataType relaxDataType, GuestureType guestureType, ArrayList<d> arrayList) {
        this.b = relaxDataType;
        this.a = i;
        this.c = guestureType;
        this.d = arrayList;
    }
}
