package com.inmobi.commons.core.configs;

import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: ConfigNetworkClient.java
class d implements Runnable {
    private static final String a;
    private e b;
    private int c;
    private a d;

    // compiled from: ConfigNetworkClient.java
    public static interface a {
        void a(ConfigResponse configResponse);

        void b();
    }

    static {
        a = d.class.getName();
    }

    public d(a aVar, e eVar) {
        this.d = aVar;
        this.b = eVar;
        this.c = 0;
    }

    private void a() throws InterruptedException {
        while (this.c <= this.b.c()) {
            Map a = new ConfigNetworkResponse(this.b.b(), new com.inmobi.commons.core.network.d(this.b).a()).a();
            for (Entry entry : a.entrySet()) {
                ConfigResponse configResponse = (ConfigResponse) entry.getValue();
                String str = (String) entry.getKey();
                if (!configResponse.d()) {
                    this.d.a(configResponse);
                    this.b.a(str);
                }
            }
            if (this.b.b().isEmpty()) {
                break;
            }
            this.c++;
            if (this.c > this.b.c()) {
                for (Entry entry2 : this.b.b().entrySet()) {
                    str = (String) entry2.getKey();
                    if (a.containsKey(str)) {
                        this.d.a((ConfigResponse) a.get(str));
                    }
                }
            } else {
                Thread.sleep((long) (this.b.d() * 1000));
            }
        }
        this.d.b();
    }

    public void run() {
        try {
            a();
        } catch (InterruptedException e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Fetching config interrupted by the component de-initialization.");
        }
    }
}
