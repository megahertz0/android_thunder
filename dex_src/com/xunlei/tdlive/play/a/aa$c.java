package com.xunlei.tdlive.play.a;

import android.annotation.SuppressLint;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.c;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.LevelInfo;
import com.xunlei.tdlive.protocol.XLLiveGetReplayRecordRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.util.e;
import com.xunlei.tdlive.util.e.b;
import com.xunlei.tdlive.util.r;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;

@SuppressLint({"UseSparseArrays"})
// compiled from: ReplayDialogPresenter.java
class aa$c implements JsonCallBack, Runnable {
    final /* synthetic */ aa a;
    private final int b;
    private HashMap<String, Integer> c;
    private HashMap<Integer, JsonWrapper> d;
    private HashMap<Integer, JsonWrapper> e;
    private HashMap<Integer, JsonWrapper> f;
    private HashMap<Integer, JsonWrapper> g;
    private XLLiveGetReplayRecordRequest h;
    private r i;
    private r j;
    private String k;
    private int l;
    private boolean m;

    public aa$c(aa aaVar, String str, String str2, String str3) {
        this.a = aaVar;
        this.b = 60000;
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = new HashMap();
        this.l = -1;
        this.m = false;
        this.h = new XLLiveGetReplayRecordRequest(str, str2, str3);
        this.j = new r(1, this);
        this.i = new r(60000, new ao(this, aaVar));
    }

    public void a(int i) {
        if (!this.m) {
            this.m = true;
            if (this.l != i) {
                b(i);
            }
        }
    }

    public void a() {
        this.i.c();
        this.j.c();
        this.m = false;
    }

    public void b(int i) {
        if (this.m) {
            this.l = i;
            this.i.d();
            this.i.e();
        }
    }

    public void run() {
        JsonWrapper jsonWrapper = (JsonWrapper) this.d.get(Integer.valueOf(this.l));
        if (jsonWrapper != null) {
            int length;
            JsonWrapper array = jsonWrapper.getArray("chat", "[]");
            JsonWrapper array2 = jsonWrapper.getArray("gift", "[]");
            JsonWrapper array3 = jsonWrapper.getArray("like", "[]");
            if (array.getLength() > 0) {
                length = 1000 / array.getLength();
            } else {
                length = 1;
            }
            int i = 0;
            while (i < array.getLength() && i < 1000) {
                this.e.put(Integer.valueOf(this.l + (i * length)), array.getObject(i, "{}"));
                i++;
            }
            if (array2.getLength() > 0) {
                length = 1000 / array2.getLength();
            } else {
                length = 1;
            }
            i = 0;
            while (i < array2.getLength() && i < 1000) {
                this.f.put(Integer.valueOf(this.l + (i * length)), array2.getObject(i, "{}"));
                i++;
            }
            if (array3.getLength() > 0) {
                length = 1000 / array3.getLength();
            } else {
                length = 1;
            }
            i = 0;
            while (i < array3.getLength() && i < 1000) {
                this.g.put(Integer.valueOf(this.l + (i * length)), array3.getObject(i, "{}"));
                i++;
            }
            this.d.remove(Integer.valueOf(this.l));
        }
        jsonWrapper = (JsonWrapper) this.e.get(Integer.valueOf(this.l));
        if (jsonWrapper != null) {
            JsonWrapper object = jsonWrapper.getObject("userInfo", "{}").getObject("level", "{}");
            LevelInfo levelInfo = new LevelInfo();
            levelInfo.current = object.getInt("current", 0);
            levelInfo.title = object.getString(SetKey.TITLE, BuildConfig.VERSION_NAME);
            levelInfo.icon = object.getString(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2, BuildConfig.VERSION_NAME);
            levelInfo.icon2 = object.getString("icon2", BuildConfig.VERSION_NAME);
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.content = jsonWrapper.getString("content", BuildConfig.VERSION_NAME);
            chatMessage.flag = jsonWrapper.getInt(AgooConstants.MESSAGE_FLAG, 0);
            chatMessage.userid = jsonWrapper.getString("userid", BuildConfig.VERSION_NAME);
            chatMessage.user.userid = jsonWrapper.getString("userid", BuildConfig.VERSION_NAME);
            chatMessage.user.nickname = jsonWrapper.getString("nickname", BuildConfig.VERSION_NAME);
            chatMessage.user.level = levelInfo;
            aa.f(this.a).a(chatMessage);
            aa.e(this.a, true);
            this.e.remove(Integer.valueOf(this.l));
        }
        JsonWrapper jsonWrapper2 = (JsonWrapper) this.f.get(Integer.valueOf(this.l));
        if (jsonWrapper2 != null) {
            jsonWrapper = jsonWrapper2.getObject("userInfo", "{}").getObject("level", "{}");
            LevelInfo levelInfo2 = new LevelInfo();
            levelInfo2.current = jsonWrapper.getInt("current", 0);
            levelInfo2.title = jsonWrapper.getString(SetKey.TITLE, BuildConfig.VERSION_NAME);
            levelInfo2.icon = jsonWrapper.getString(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2, BuildConfig.VERSION_NAME);
            levelInfo2.icon2 = jsonWrapper.getString("icon2", BuildConfig.VERSION_NAME);
            aa.a(this.a, jsonWrapper2.getString("userid", BuildConfig.VERSION_NAME), jsonWrapper2.getString("nickname", BuildConfig.VERSION_NAME), jsonWrapper2.getString("avatar", BuildConfig.VERSION_NAME), levelInfo2, jsonWrapper2.getInt("gift_id", 0), jsonWrapper2.getString("gift_name", BuildConfig.VERSION_NAME), jsonWrapper2.getString("content", BuildConfig.VERSION_NAME), c.a(this.k, jsonWrapper2.getInt("gift_id", 0)), jsonWrapper2.getInt("continue_num", 0));
            this.f.remove(Integer.valueOf(this.l));
        }
        jsonWrapper = (JsonWrapper) this.g.get(Integer.valueOf(this.l));
        if (jsonWrapper != null) {
            b bVar;
            Integer num = (Integer) this.c.get(jsonWrapper.getString("userid", BuildConfig.VERSION_NAME));
            if (num == null) {
                num = Integer.valueOf(e.a());
                this.c.put(jsonWrapper.getString("userid", BuildConfig.VERSION_NAME), num);
            }
            b bVar2 = b.a;
            if (jsonWrapper.getString(AgooConstants.MESSAGE_TYPE, BuildConfig.VERSION_NAME).equals("monkey")) {
                bVar = b.b;
            } else {
                bVar = bVar2;
            }
            aa.n(this.a);
            aa.o(this.a).addFloatUnit(num.intValue(), bVar);
            this.g.remove(Integer.valueOf(this.l));
        }
        this.l++;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0 && jsonWrapper != null) {
            JsonWrapper object = jsonWrapper.getObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "{}");
            this.k = object.getString("path", BuildConfig.VERSION_NAME);
            JsonWrapper array = object.getArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]");
            for (int i2 = 0; i2 < array.getLength(); i2++) {
                JsonWrapper object2 = array.getObject(i2, "{}");
                this.d.put(Integer.valueOf(object2.getInt("second", 0) * 1000), object2);
            }
            this.j.d();
        }
    }
}
