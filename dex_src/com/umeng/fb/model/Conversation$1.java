package com.umeng.fb.model;

import android.os.Handler;
import com.umeng.fb.SyncListener;
import com.umeng.fb.net.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class Conversation$1 implements Runnable {
    final /* synthetic */ Handler a;
    final /* synthetic */ SyncListener b;
    final /* synthetic */ Conversation c;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ Reply a;

        AnonymousClass_1(Reply reply) {
            this.a = reply;
        }

        public void run() {
            this.a.status = Reply.STATUS_SENDING;
            Conversation.a(Conversation$1.this.c);
        }
    }

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ Reply a;
        final /* synthetic */ Map b;

        AnonymousClass_2(Reply reply, Map map) {
            this.a = reply;
            this.b = map;
        }

        public void run() {
            this.a.created_at = ((Long) this.b.get("created_at")).longValue();
            this.a.status = Reply.STATUS_SENT;
            Conversation.a(Conversation$1.this);
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ Reply a;

        AnonymousClass_3(Reply reply) {
            this.a = reply;
        }

        public void run() {
            this.a.status = Reply.STATUS_NOT_SENT;
            Conversation.a(Conversation$1.this.c);
        }
    }

    class AnonymousClass_4 implements Runnable {
        final /* synthetic */ List a;
        final /* synthetic */ List b;

        AnonymousClass_4(List list, List list2) {
            this.a = list;
            this.b = list2;
        }

        public void run() {
            Conversation.d(Conversation$1.this).addAll(this.a);
            Collections.sort(Conversation.d(Conversation$1.this));
            Conversation.a(Conversation$1.this);
            if (Conversation$1.this.b != null) {
                Conversation$1.this.b.onReceiveDevReply(this.a);
                Conversation$1.this.b.onSendUserReply(this.b);
            }
        }
    }

    Conversation$1(Conversation conversation, Handler handler, SyncListener syncListener) {
        this.c = conversation;
        this.a = handler;
        this.b = syncListener;
    }

    public void run() {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        long j = 0;
        for (int i = 0; i < this.c.getReplyList().size(); i++) {
            Reply reply = (Reply) this.c.getReplyList().get(i);
            if (Reply.TYPE_USER_REPLY.equals(reply.type) || Reply.TYPE_NEW_FEEDBACK.equals(reply.type)) {
                if (!Reply.STATUS_SENT.equals(reply.status)) {
                    Map b;
                    arrayList.add(reply);
                    this.a.post(new AnonymousClass_1(reply));
                    if (Reply.TYPE_NEW_FEEDBACK.equals(reply.type)) {
                        b = new a(Conversation.c(this.c)).b(Conversation.b(this.c), reply);
                    } else {
                        b = new a(Conversation.c(this.c)).a(Conversation.b(this.c), reply);
                    }
                    if (b.size() == 2) {
                        this.a.post(new AnonymousClass_2(reply, b));
                    } else {
                        this.a.post(new AnonymousClass_3(reply));
                    }
                }
            } else if (a.g.equals(reply.type) && r2 == 0) {
                j = reply.created_at;
            }
        }
        try {
            for (Reply reply2 : new a(Conversation.c(this.c)).a(Conversation.b(this.c))) {
                if (!Conversation.a(this.c, reply2)) {
                    arrayList2.add(reply2);
                }
            }
            this.a.post(new AnonymousClass_4(arrayList2, arrayList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
