package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.uid.d;
import java.util.List;
import org.json.JSONArray;

// compiled from: CarbPostListNetworkRequest.java
public class f extends NetworkRequest {
    private int d;
    private int e;
    private c f;
    private List<d> g;

    public f(String str, int i, int i2, d dVar, List<d> list, c cVar) {
        super(RequestType.POST, str, true, dVar);
        this.d = i;
        this.e = i2;
        this.g = list;
        this.f = cVar;
        this.c.put("req_id", this.f.c());
        this.c.put("i_till", Integer.toString(this.f.d()));
        this.c.put("p_a_apps", d());
    }

    private String d() {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.g.size(); i++) {
            jSONArray.put(((d) this.g.get(i)).b());
        }
        return jSONArray.toString();
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }
}
