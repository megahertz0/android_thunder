package com.umeng.message;

import android.annotation.TargetApi;
import com.umeng.message.entity.UNotificationItem;
import java.util.LinkedList;

public class MessageNotificationQueue {
    private static MessageNotificationQueue b;
    private LinkedList<UNotificationItem> a;

    private MessageNotificationQueue() {
        this.a = new LinkedList();
    }

    public static synchronized MessageNotificationQueue getInstance() {
        MessageNotificationQueue messageNotificationQueue;
        synchronized (MessageNotificationQueue.class) {
            if (b == null) {
                b = new MessageNotificationQueue();
            }
            messageNotificationQueue = b;
        }
        return messageNotificationQueue;
    }

    public void addLast(UNotificationItem uNotificationItem) {
        this.a.addLast(uNotificationItem);
    }

    @TargetApi(9)
    public UNotificationItem pollFirst() {
        return (UNotificationItem) this.a.pollFirst();
    }

    public void remove(UNotificationItem uNotificationItem) {
        this.a.remove(uNotificationItem);
    }

    public int size() {
        return this.a.size();
    }
}
