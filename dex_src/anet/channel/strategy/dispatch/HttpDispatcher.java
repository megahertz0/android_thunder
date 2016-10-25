package anet.channel.strategy.dispatch;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.entity.ENV;
import anet.channel.strategy.n;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

// compiled from: Taobao
public class HttpDispatcher {
    private e executor;
    private Set<String> initHosts;
    private volatile boolean isEnable;
    private AtomicBoolean isInitHostsFilled;
    private CopyOnWriteArraySet<IDispatchEventListener> listeners;
    private Set<String> uniqueIdSet;

    // compiled from: Taobao
    public static interface IDispatchEventListener {
        void onEvent(DispatchEvent dispatchEvent);
    }

    // compiled from: Taobao
    private static class Singleton {
        static HttpDispatcher instance;

        private Singleton() {
        }

        static {
            instance = new HttpDispatcher();
        }
    }

    public static HttpDispatcher getInstance() {
        return Singleton.instance;
    }

    private HttpDispatcher() {
        this.listeners = new CopyOnWriteArraySet();
        this.executor = new e();
        this.isEnable = true;
        this.uniqueIdSet = Collections.newSetFromMap(new ConcurrentHashMap());
        this.initHosts = new TreeSet();
        this.isInitHostsFilled = new AtomicBoolean();
        fillInitHosts();
    }

    public void sendHttpDispatchRequest(Set<String> set, String str, int i) {
        if (this.isEnable && set != null && !set.isEmpty()) {
            Map hashMap = new HashMap();
            hashMap.put(a.HOSTS, set);
            hashMap.put(a.PRE_IP, str);
            hashMap.put(a.CONFIG_VERSION, String.valueOf(i));
            this.executor.a(hashMap);
        }
    }

    public void addListener(IDispatchEventListener iDispatchEventListener) {
        this.listeners.add(iDispatchEventListener);
    }

    public void removeListener(IDispatchEventListener iDispatchEventListener) {
        this.listeners.remove(iDispatchEventListener);
    }

    void fireEvent(DispatchEvent dispatchEvent) {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((IDispatchEventListener) it.next()).onEvent(dispatchEvent);
        }
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public synchronized void addHosts(List<String> list) {
        if (list != null) {
            this.initHosts.addAll(list);
            this.uniqueIdSet.clear();
        }
    }

    public static void setInitHosts(List<String> list) {
        if (list != null) {
            a.a = (String[]) list.toArray(new String[0]);
        }
    }

    public synchronized Set<String> getInitHosts() {
        fillInitHosts();
        return new HashSet(this.initHosts);
    }

    private void fillInitHosts() {
        if (!this.isInitHostsFilled.get() && GlobalAppRuntimeInfo.getContext() != null && GlobalAppRuntimeInfo.isTargetProcess() && this.isInitHostsFilled.compareAndSet(false, true)) {
            this.initHosts.addAll(Arrays.asList(a.a));
            this.initHosts.add(n.a());
            this.initHosts.add(a.a());
            if (GlobalAppRuntimeInfo.getEnv() == ENV.ONLINE) {
                this.initHosts.add(n.c);
                this.initHosts.add(n.e);
            }
        }
    }

    public boolean isInitHostsChanged(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean contains = this.uniqueIdSet.contains(str);
        if (!contains) {
            this.uniqueIdSet.add(str);
        }
        return !contains;
    }

    public void switchENV() {
        this.uniqueIdSet.clear();
        this.initHosts.clear();
        this.isInitHostsFilled.set(false);
    }
}
