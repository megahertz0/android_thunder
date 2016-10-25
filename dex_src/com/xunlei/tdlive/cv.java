package com.xunlei.tdlive;

import com.xunlei.tdlive.LivePublishEndingActivity.b.a;
import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest.GetLiveListResp.Room;
import com.xunlei.tdlive.util.q;
import java.util.List;

// compiled from: LivePublishEndingActivity.java
class cv implements a {
    final /* synthetic */ LivePublishEndingActivity a;

    cv(LivePublishEndingActivity livePublishEndingActivity) {
        this.a = livePublishEndingActivity;
    }

    public void a(int i, List<Room> list) {
        String str;
        this.a.i.a((List) list);
        LivePublishEndingActivity.j.c();
        String str2 = com.umeng.a.d;
        try {
            int i2 = 0;
            for (Room room : list) {
                int i3;
                StringBuilder append = new StringBuilder().append(str2).append("roomid=").append(room.roomid).append(",hostid=").append(room.userid).append(",hosttype=");
                if (room.seq2 == null) {
                    i3 = 0;
                } else {
                    i3 = room.seq2.hot_level;
                }
                append = append.append(i3).append(",recommend=");
                if (room.seq2 == null) {
                    i3 = 0;
                } else {
                    i3 = room.seq2.is_recommend;
                }
                append = append.append(i3).append(",follow=");
                if (room.seq2 == null) {
                    i3 = 0;
                } else {
                    i3 = room.seq2.is_follow;
                }
                append = append.append(i3).append(",sign=");
                if (room.seq2 == null) {
                    i3 = 0;
                } else {
                    i3 = room.seq2.is_sign;
                }
                append = append.append(i3).append(",viewernum=").append(room.onlinenum).append(",rn=");
                i3 = i2 + 1;
                StringBuilder append2 = append.append(i2).append(",livestat=");
                if (room.status == 2) {
                    str = "replay";
                } else {
                    str = "live";
                }
                str = append2.append(str).append(",is_dl=0;").toString();
                if (i3 >= 4) {
                    break;
                }
                i2 = i3;
                str2 = str;
            }
            str = str2;
        } catch (Exception e) {
            str = str2;
        }
        q.e("play_end_content_show").c("viewid").a("grayid", i).a("contentlist", str).b("contentlist");
    }
}
