package com.xiaomi.push.service;

import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.util.h;
import com.qq.e.comm.constants.Constants.KEYS;
import com.taobao.accs.utl.BaseMonitor;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.x.c;
import com.xiaomi.smack.b;
import com.xiaomi.smack.l;
import com.xiaomi.smack.packet.a;
import com.xiaomi.smack.packet.d;
import com.xiaomi.smack.util.k;
import com.xiaomi.stats.g;
import com.xunlei.download.DownloadManager;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Date;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.message.MessageService;

public class PacketSync {
    private XMPushService a;

    public static interface PacketReceiveHandler extends Parcelable {
    }

    PacketSync(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    private void a(a aVar) {
        Object c = aVar.c();
        if (!TextUtils.isEmpty(c)) {
            String[] split = c.split(h.b);
            Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(b.b(), false);
            if (fallbacksByHost != null && split.length > 0) {
                fallbacksByHost.a(split);
                this.a.a((int) R.styleable.Toolbar_navigationIcon, null);
                this.a.a(true);
            }
        }
    }

    private void b(d dVar) {
        Object m = dVar.m();
        Object l = dVar.l();
        if (!TextUtils.isEmpty(m) && !TextUtils.isEmpty(l)) {
            x.b b = x.a().b(l, m);
            if (b != null) {
                k.a(this.a, b.a, (long) k.a(dVar.a()), true, System.currentTimeMillis());
            }
        }
    }

    public void a(d dVar) {
        if (!Constants.VIA_SHARE_TYPE_TEXT.equals(dVar.l())) {
            b(dVar);
        }
        x.b b;
        if (dVar instanceof com.xiaomi.smack.k.b) {
            com.xiaomi.smack.k.b bVar = (com.xiaomi.smack.k.b) dVar;
            com.xiaomi.smack.k.b.a b2 = bVar.b();
            String l = bVar.l();
            String m = bVar.m();
            if (!TextUtils.isEmpty(l)) {
                b = x.a().b(l, m);
                if (b == null) {
                    return;
                }
                if (b2 == com.xiaomi.smack.k.b.a.a) {
                    b.a(c.c, 1, 0, null, null);
                    com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("SMACK: channel bind succeeded, chid=").append(l).toString());
                    return;
                }
                com.xiaomi.smack.packet.h p = bVar.p();
                com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("SMACK: channel bind failed, error=").append(p.d()).toString());
                if (p != null) {
                    if (BaseMonitor.ALARM_POINT_AUTH.equals(p.b())) {
                        if ("invalid-sig".equals(p.a())) {
                            com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("SMACK: bind error invalid-sig token = ").append(b.c).append(" sec = ").append(b.i).toString());
                            g.a(0, com.xiaomi.push.thrift.a.Q.a(), 1, null, 0);
                        }
                        b.a(c.a, 1, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, p.a(), p.b());
                        x.a().a(l, m);
                    } else if ("cancel".equals(p.b())) {
                        b.a(c.a, 1, 7, p.a(), p.b());
                        x.a().a(l, m);
                    } else if ("wait".equals(p.b())) {
                        this.a.b(b);
                        b.a(c.a, 1, 7, p.a(), p.b());
                    }
                    com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("SMACK: channel bind failed, chid=").append(l).append(" reason=").append(p.a()).toString());
                    return;
                }
                return;
            }
            return;
        }
        String l2 = dVar.l();
        if (TextUtils.isEmpty(l2)) {
            l2 = MessageService.MSG_DB_NOTIFY_REACHED;
            dVar.l(l2);
        }
        a p2;
        String m2;
        String a;
        if (!l2.equals(MessageService.MSG_DB_READY_REPORT)) {
            if (dVar instanceof com.xiaomi.smack.packet.b) {
                p2 = dVar.p("kick");
                if (p2 != null) {
                    m2 = dVar.m();
                    a = p2.a(JsInterface.FUNPLAY_AD_TRPE);
                    String a2 = p2.a(DownloadManager.COLUMN_REASON);
                    com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("kicked by server, chid=").append(l2).append(" userid=").append(m2).append(" type=").append(a).append(" reason=").append(a2).toString());
                    if ("wait".equals(a)) {
                        b = x.a().b(l2, m2);
                        if (b != null) {
                            this.a.b(b);
                            b.a(c.a, 3, 0, a2, a);
                            return;
                        }
                        return;
                    }
                    this.a.a(l2, m2, 3, a2, a);
                    x.a().a(l2, m2);
                    return;
                }
            } else if (dVar instanceof com.xiaomi.smack.packet.c) {
                com.xiaomi.smack.packet.c cVar = (com.xiaomi.smack.packet.c) dVar;
                if ("redir".equals(cVar.b())) {
                    p2 = cVar.p(anet.channel.strategy.dispatch.a.HOSTS);
                    if (p2 != null) {
                        a(p2);
                        return;
                    }
                    return;
                }
            }
            this.a.d().a(this.a, l2, dVar);
        } else if (dVar instanceof com.xiaomi.smack.packet.b) {
            com.xiaomi.smack.packet.b bVar2 = (com.xiaomi.smack.packet.b) dVar;
            if (MessageService.MSG_DB_READY_REPORT.equals(dVar.k()) && "result".equals(bVar2.b().toString())) {
                com.xiaomi.smack.a g = this.a.g();
                if (g instanceof l) {
                    ((l) g).x();
                }
                g.b();
            } else if (IntentUtil.AGOO_COMMAND.equals(bVar2.b().toString())) {
                p2 = dVar.p("u");
                if (p2 != null) {
                    l2 = p2.a(SocialConstants.PARAM_URL);
                    m2 = p2.a("startts");
                    a = p2.a("endts");
                    try {
                        Date date = new Date(Long.parseLong(m2));
                        Date date2 = new Date(Long.parseLong(a));
                        m2 = p2.a("token");
                        boolean equals = "true".equals(p2.a("force"));
                        Object a3 = p2.a("maxlen");
                        com.xiaomi.push.log.b.a(this.a).a(l2, m2, date2, date, !TextUtils.isEmpty(a3) ? Integer.parseInt(a3) * 1024 : 0, equals);
                    } catch (NumberFormatException e) {
                        com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("parseLong fail ").append(e.getMessage()).toString());
                    }
                }
            }
            if (bVar2.a(KEYS.PLACEMENTS) != null) {
                try {
                    ag.a().a(com.xiaomi.push.protobuf.b.a.b(Base64.decode(bVar2.a(KEYS.PLACEMENTS), XZBDevice.Wait)));
                } catch (IllegalArgumentException e2) {
                    com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("invalid Base64 exception + ").append(e2.getMessage()).toString());
                } catch (com.google.protobuf.micro.c e3) {
                    com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("invalid pb exception + ").append(e3.getMessage()).toString());
                }
            }
        }
    }
}
