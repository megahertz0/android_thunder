package com.xiaomi.smack;

import com.qq.e.comm.constants.Constants.KEYS;
import com.taobao.accs.common.Constants;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.smack.packet.b;
import com.xiaomi.smack.packet.d;
import com.xiaomi.smack.util.c;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.agoo.message.MessageService;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

class g {
    private Thread a;
    private l b;
    private XmlPullParser c;
    private boolean d;

    protected g(l lVar) {
        this.b = lVar;
        a();
    }

    private void a(d dVar) {
        if (dVar != null) {
            for (a aVar : this.b.g.values()) {
                aVar.a(dVar);
            }
        }
    }

    private void e() {
        this.c = XmlPullParserFactory.newInstance().newPullParser();
        this.c.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        this.c.setInput(this.b.j);
    }

    private void f() {
        try {
            e();
            int eventType = this.c.getEventType();
            String str = a.d;
            do {
                this.b.p();
                if (eventType == 2) {
                    String name = this.c.getName();
                    if (this.c.getName().equals(Constants.SHARED_MESSAGE_ID_FILE)) {
                        a(c.a(this.c));
                        str = name;
                    } else if (this.c.getName().equals("iq")) {
                        a(c.a(this.c, this.b));
                        str = name;
                    } else if (this.c.getName().equals("presence")) {
                        a(c.b(this.c));
                        str = name;
                    } else if (this.c.getName().equals("stream")) {
                        str = a.d;
                        for (int i = 0; i < this.c.getAttributeCount(); i++) {
                            if (this.c.getAttributeName(i).equals("from")) {
                                this.b.o.a(this.c.getAttributeValue(i));
                            } else if (this.c.getAttributeName(i).equals("challenge")) {
                                str = this.c.getAttributeValue(i);
                            } else if (KEYS.PLACEMENTS.equals(this.c.getAttributeName(i))) {
                                String attributeValue = this.c.getAttributeValue(i);
                                d bVar = new b();
                                bVar.l(MessageService.MSG_DB_READY_REPORT);
                                bVar.k(MessageService.MSG_DB_READY_REPORT);
                                bVar.a(KEYS.PLACEMENTS, attributeValue);
                                bVar.a(b.a.b);
                                a(bVar);
                            }
                        }
                        this.b.a(str);
                        str = name;
                    } else if (this.c.getName().equals("error")) {
                        throw new p(c.d(this.c));
                    } else {
                        if (this.c.getName().equals("warning")) {
                            this.c.next();
                            if (this.c.getName().equals("multi-login")) {
                                a(R.styleable.Toolbar_contentInsetEnd, null);
                                str = name;
                            }
                        } else if (this.c.getName().equals("bind")) {
                            a(c.c(this.c));
                            str = name;
                        }
                        str = name;
                    }
                } else if (eventType == 3 && this.c.getName().equals("stream")) {
                    a(XZBDevice.Upload, null);
                }
                eventType = this.c.next();
                if (this.d) {
                    break;
                }
            } while (eventType != 1);
            if (eventType == 1) {
                throw new Exception(new StringBuilder("SMACK: server close the connection or timeout happened, last element name=").append(str).append(" host=").append(this.b.c()).toString());
            }
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            if (this.d) {
                com.xiaomi.channel.commonutils.logger.b.c("reader is shutdown, ignore the exception.");
            } else {
                a(XZBDevice.Pause, e);
            }
        }
    }

    protected void a() {
        this.d = false;
        this.a = new h(this, new StringBuilder("Smack Packet Reader (").append(this.b.n).append(SocializeConstants.OP_CLOSE_PAREN).toString());
    }

    void a(int i, Exception exception) {
        this.d = true;
        this.b.a(i, exception);
    }

    public void b() {
        this.a.start();
    }

    public void c() {
        this.d = true;
    }

    void d() {
        this.b.g.clear();
    }
}
