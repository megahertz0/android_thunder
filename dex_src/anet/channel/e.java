package anet.channel;

import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ConnType;
import anet.channel.entity.ConnType.TypeLevel;
import anet.channel.session.AccsSession;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

// compiled from: Taobao
public class e {
    private final Map<SessionRequest, List<Session>> a;
    private final ReentrantReadWriteLock b;
    private final ReadLock c;
    private final WriteLock d;

    // compiled from: Taobao
    private static class a {
        public static e a;

        private a() {
        }

        static {
            a = new e();
        }
    }

    private e() {
        this.a = new HashMap();
        this.b = new ReentrantReadWriteLock();
        this.c = this.b.readLock();
        this.d = this.b.writeLock();
    }

    public void a(SessionRequest sessionRequest, Session session) {
        if (sessionRequest != null && sessionRequest.a() != null && session != null) {
            this.d.lock();
            List list = (List) this.a.get(sessionRequest);
            if (list == null) {
                list = new ArrayList();
                this.a.put(sessionRequest, list);
            }
            if (list.indexOf(session) != -1) {
                this.d.unlock();
                return;
            }
            list.add(session);
            Collections.sort(list);
            this.d.unlock();
        }
    }

    public void b(SessionRequest sessionRequest, Session session) {
        this.d.lock();
        List list = (List) this.a.get(sessionRequest);
        if (list == null) {
            this.d.unlock();
            return;
        }
        list.remove(session);
        if (list.size() == 0) {
            this.a.remove(sessionRequest);
            SessionRequest.a(sessionRequest);
        }
        this.d.unlock();
    }

    public List<Session> a(String str) {
        return a(SessionRequest.a(str));
    }

    public List<Session> a(SessionRequest sessionRequest) {
        this.c.lock();
        List list = (List) this.a.get(sessionRequest);
        if (list != null) {
            ArrayList arrayList = new ArrayList(list);
            this.c.unlock();
            return arrayList;
        }
        List<Session> list2 = Collections.EMPTY_LIST;
        this.c.unlock();
        return list2;
    }

    public List<Session> a(ConnType connType) {
        List<Session> list = Collections.EMPTY_LIST;
        this.c.lock();
        if (this.a.isEmpty()) {
            this.c.unlock();
            return list;
        }
        List<Session> arrayList = new ArrayList();
        for (List<Session> list2 : this.a.values()) {
            if (list2 != null && !list2.isEmpty()) {
                for (Session session : list2) {
                    if (session != null && session.getConnType().equals(connType)) {
                        arrayList.add(session);
                    }
                }
            }
        }
        this.c.unlock();
        return arrayList;
    }

    public Session a(SessionRequest sessionRequest, TypeLevel typeLevel) {
        this.c.lock();
        List<Session> list = (List) this.a.get(sessionRequest);
        if (list == null || list.isEmpty()) {
            this.c.unlock();
            return null;
        }
        Session session;
        for (Session session2 : list) {
            if (session2 != null && session2.isAvailable()) {
                if (typeLevel == null || session2.mConnType.getTypeLevel() == typeLevel) {
                }
                session = session2;
                break;
            }
        }
        Object obj = null;
        String a = sessionRequest.a();
        if (a != null && a.endsWith(n.a()) && list.size() > 1) {
            int i = 0;
            for (Session session22 : list) {
                int i2;
                if (session22 instanceof AccsSession) {
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            if (i > 1) {
                ALog.e("awcn.SessionPool", "accs session count > 1", null, "sessions", list.toString());
                AppMonitor.getInstance().commitStat(new ExceptionStatistic(-107, null, "nw"));
            }
        }
        this.c.unlock();
        return session;
    }

    public List<SessionRequest> a() {
        List<SessionRequest> list = Collections.EMPTY_LIST;
        this.c.lock();
        if (this.a.isEmpty()) {
            this.c.unlock();
            return list;
        }
        list = new ArrayList(this.a.keySet());
        this.c.unlock();
        return list;
    }

    public boolean c(SessionRequest sessionRequest, Session session) {
        this.c.lock();
        List list = (List) this.a.get(sessionRequest);
        if (list == null) {
            this.c.unlock();
            return false;
        }
        boolean z;
        if (list.indexOf(session) != -1) {
            Object obj = 1;
        } else {
            z = false;
        }
        this.c.unlock();
        return z;
    }
}
