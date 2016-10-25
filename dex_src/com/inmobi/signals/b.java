package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.uid.d;

// compiled from: CarbGetListNetworkRequest.java
public class b extends NetworkRequest {
    private int d;
    private int e;

    public b(String str, int i, int i2, d dVar) {
        super(RequestType.POST, str, true, dVar);
        this.d = i;
        this.e = i2;
    }
}
