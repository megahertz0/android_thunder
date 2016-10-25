package com.baidu.mobads.openad.d;

import android.os.Handler;
import android.os.Looper;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventDispatcher;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class c implements IOAdEventDispatcher {
    private Handler a;
    protected Map<String, ArrayList<IOAdEventListener>> c;

    public c() {
        removeAllListeners();
        this.a = new Handler(Looper.getMainLooper());
    }

    public void addEventListener(String str, IOAdEventListener iOAdEventListener) {
        if (str != null && iOAdEventListener != null) {
            removeEventListener(str, iOAdEventListener);
            ArrayList arrayList = (ArrayList) this.c.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.c.put(str, arrayList);
            }
            arrayList.add(iOAdEventListener);
        }
    }

    public boolean hasEventListener(String str) {
        ArrayList arrayList = (ArrayList) this.c.get(str);
        return (arrayList == null || arrayList.isEmpty()) ? false : true;
    }

    public void removeEventListener(String str, IOAdEventListener iOAdEventListener) {
        if (str != null && iOAdEventListener != null) {
            ArrayList arrayList = (ArrayList) this.c.get(str);
            if (arrayList != null) {
                arrayList.remove(iOAdEventListener);
                if (arrayList.isEmpty()) {
                    this.c.remove(str);
                }
            }
        }
    }

    public void removeEventListeners(String str) {
        this.c.remove(str);
    }

    public void removeAllListeners() {
        this.c = new ConcurrentHashMap();
    }

    public void dispatchEvent(IOAdEvent iOAdEvent) {
        if (iOAdEvent != null) {
            iOAdEvent.setTarget(this);
            ArrayList arrayList = (ArrayList) this.c.get(iOAdEvent.getType());
            if (arrayList != null) {
                IOAdEventListener[] iOAdEventListenerArr = new IOAdEventListener[arrayList.size()];
                arrayList.toArray(iOAdEventListenerArr);
                for (IOAdEventListener iOAdEventListener : iOAdEventListenerArr) {
                    iOAdEventListener.run(iOAdEvent);
                }
            }
        }
    }

    public void dispose() {
        removeAllListeners();
    }
}
