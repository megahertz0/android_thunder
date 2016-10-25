package com.xunlei.tdlive;

import com.umeng.common.inter.ITagManager;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.im.GiftMessage;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.protocol.XLLiveSendGiftRequest.SendGiftResp;
import com.xunlei.tdlive.util.q;

// compiled from: LiveGiftDialog.java
class p implements ObjectCallBack {
    final /* synthetic */ i a;

    p(i iVar) {
        this.a = iVar;
    }

    public void onResponse(int i, String str, Object obj) {
        GiftMessage giftMessage = new GiftMessage();
        if (i == 0) {
            SendGiftResp sendGiftResp = (SendGiftResp) obj;
            giftMessage.userInfo.userid = sendGiftResp.data.userInfo.userid;
            giftMessage.userInfo.avatar = sendGiftResp.data.userInfo.avatar;
            giftMessage.userInfo.nickname = sendGiftResp.data.userInfo.nickname;
            giftMessage.userInfo.level.current = sendGiftResp.data.userInfo.level.current;
            giftMessage.userInfo.level.title = sendGiftResp.data.userInfo.level.title;
            giftMessage.userInfo.level.icon = sendGiftResp.data.userInfo.level.icon;
            giftMessage.userInfo.level.icon2 = sendGiftResp.data.userInfo.level.icon2;
            giftMessage.playerInfo.userid = sendGiftResp.data.playerInfo.userid;
            giftMessage.playerInfo.total_point = sendGiftResp.data.playerInfo.total_point;
            giftMessage.giftInfo.content = sendGiftResp.data.giftInfo.content;
            giftMessage.giftInfo.path = sendGiftResp.data.giftInfo.path;
            giftMessage.giftInfo.name = sendGiftResp.data.giftInfo.name;
            giftMessage.giftInfo.continue_num = sendGiftResp.data.giftInfo.continue_num;
            giftMessage.giftid = sendGiftResp.data.giftid;
            giftMessage.sendtime = sendGiftResp.data.sendtime;
            giftMessage.roomid = sendGiftResp.data.roomid;
            this.a.a((long) sendGiftResp.data.current_coin);
            this.a.f.a(sendGiftResp.data.giftid, sendGiftResp.data.giftInfo.giftnum, sendGiftResp.data.giftInfo.remaintime);
            q.e("send_gift").a("success").b(new String[0]);
        } else {
            if (i == -3004) {
                this.a.d();
            } else {
                n.a(this.a.getContext(), new StringBuilder("\u8d60\u9001\u5931\u8d25\uff0c").append(str).toString());
            }
            q.e("send_gift").a(ITagManager.FAIL).b(new String[0]);
        }
        if (this.a.b != null) {
            this.a.b.a(i, giftMessage);
        }
    }
}
