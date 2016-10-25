package com.xunlei.tdlive;

import com.xunlei.tdlive.a.ae.d;
import com.xunlei.tdlive.im.MessageDispatcher.OnMessageCallback;
import com.xunlei.tdlive.im.RoomUserListMessage;
import com.xunlei.tdlive.im.RoomUserListMessage.User;
import com.xunlei.tdlive.util.XLog;
import java.util.ArrayList;
import java.util.List;

// compiled from: LivePlayerDialog.java
class bq extends OnMessageCallback<RoomUserListMessage> {
    final /* synthetic */ au a;

    bq(au auVar) {
        this.a = auVar;
    }

    public void onMessage(RoomUserListMessage roomUserListMessage) {
        XLog.d("LivePlayerDialog", new StringBuilder("onRoomUserListMessage populariy=").append(roomUserListMessage.total_point).append(", roomid=").append(roomUserListMessage.roomid).toString());
        long j = roomUserListMessage.total_point;
        long j2 = roomUserListMessage.user_count;
        List arrayList = new ArrayList();
        for (int i = 0; i < roomUserListMessage.user_lists.size(); i++) {
            d dVar = new d();
            dVar.a = ((User) roomUserListMessage.user_lists.get(i)).userid;
            dVar.b = ((User) roomUserListMessage.user_lists.get(i)).nickname;
            dVar.c = ((User) roomUserListMessage.user_lists.get(i)).avatar;
            dVar.d = ((User) roomUserListMessage.user_lists.get(i)).sign;
            dVar.e = ((User) roomUserListMessage.user_lists.get(i)).level;
            dVar.f = ((User) roomUserListMessage.user_lists.get(i)).day_coin;
            arrayList.add(dVar);
        }
        this.a.a.getPresenter().b(j);
        this.a.a.getPresenter().a(j2);
        this.a.a.getPresenter().a(arrayList, j2);
    }
}
