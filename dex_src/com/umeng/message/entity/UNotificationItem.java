package com.umeng.message.entity;

public class UNotificationItem {
    public int id;
    public UMessage message;

    public UNotificationItem(int i, UMessage uMessage) {
        this.id = i;
        this.message = uMessage;
    }

    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass() && this.id == ((UNotificationItem) obj).id;
    }
}
