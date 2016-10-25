package com.taobao.accs.net;

import com.taobao.accs.data.Message;

// compiled from: Taobao
class m implements Runnable {
    final /* synthetic */ Message a;
    final /* synthetic */ boolean b;
    final /* synthetic */ l c;

    m(l lVar, Message message, boolean z) {
        this.c = lVar;
        this.a = message;
        this.b = z;
    }

    public void run() {
        synchronized (l.a(this.c)) {
            l.a(this.c, this.a);
            if (l.a(this.c).size() == 0) {
                l.a(this.c).add(this.a);
            } else {
                Message message = (Message) l.a(this.c).getFirst();
                if (this.a.getType() == 1 || this.a.getType() == 0) {
                    l.a(this.c).addLast(this.a);
                    if (message.getType() == 2) {
                        l.a(this.c).removeFirst();
                    }
                } else if (this.a.getType() != 2 || message.getType() != 2) {
                    l.a(this.c).addLast(this.a);
                } else if (!message.force && this.a.force) {
                    l.a(this.c).removeFirst();
                    l.a(this.c).addFirst(this.a);
                }
            }
            if (this.b || l.b(this.c) == 3) {
                try {
                    l.a(this.c).notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
