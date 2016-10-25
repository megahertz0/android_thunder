package com.xiaomi.push.protobuf;

import com.google.protobuf.micro.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public final class b {

    public static final class a extends d {
        private boolean a;
        private boolean b;
        private boolean c;
        private int d;
        private boolean e;
        private int f;
        private boolean g;
        private int h;
        private int i;

        public a() {
            this.b = false;
            this.d = 0;
            this.f = 0;
            this.h = 0;
            this.i = -1;
        }

        public static com.xiaomi.push.protobuf.b.a b(byte[] bArr) {
            return (com.xiaomi.push.protobuf.b.a) new com.xiaomi.push.protobuf.b.a().a(bArr);
        }

        public final int a() {
            int i = 0;
            if (d()) {
                i = com.google.protobuf.micro.b.b(1, c()) + 0;
            }
            if (f()) {
                i += com.google.protobuf.micro.b.c(XZBDevice.DOWNLOAD_LIST_FAILED, e());
            }
            if (h()) {
                i += com.google.protobuf.micro.b.c(XZBDevice.DOWNLOAD_LIST_ALL, g());
            }
            if (j()) {
                i += com.google.protobuf.micro.b.c(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, i());
            }
            this.i = i;
            return i;
        }

        public final /* synthetic */ d a(com.google.protobuf.micro.a aVar) {
            return b(aVar);
        }

        public final com.xiaomi.push.protobuf.b.a a(int i) {
            this.c = true;
            this.d = i;
            return this;
        }

        public final com.xiaomi.push.protobuf.b.a a(boolean z) {
            this.a = true;
            this.b = z;
            return this;
        }

        public final void a(com.google.protobuf.micro.b bVar) {
            if (d()) {
                bVar.a(1, c());
            }
            if (f()) {
                bVar.a((int) XZBDevice.DOWNLOAD_LIST_FAILED, e());
            }
            if (h()) {
                bVar.a((int) XZBDevice.DOWNLOAD_LIST_ALL, g());
            }
            if (j()) {
                bVar.a((int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, i());
            }
        }

        public final com.xiaomi.push.protobuf.b.a b(int i) {
            this.e = true;
            this.f = i;
            return this;
        }

        public final com.xiaomi.push.protobuf.b.a b(com.google.protobuf.micro.a aVar) {
            while (true) {
                int a = aVar.a();
                switch (a) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        return this;
                    case XZBDevice.Wait:
                        a(aVar.d());
                        break;
                    case R.styleable.Toolbar_subtitleTextColor:
                        a(aVar.c());
                        break;
                    case R.styleable.AppCompatTheme_actionModeCutDrawable:
                        b(aVar.c());
                        break;
                    case R.styleable.AppCompatTheme_textAppearanceLargePopupMenu:
                        c(aVar.c());
                        break;
                    default:
                        if (!a(aVar, a)) {
                            return this;
                        }
                }
            }
        }

        public final com.xiaomi.push.protobuf.b.a c(int i) {
            this.g = true;
            this.h = i;
            return this;
        }

        public final boolean c() {
            return this.b;
        }

        public final boolean d() {
            return this.a;
        }

        public final int e() {
            return this.d;
        }

        public final boolean f() {
            return this.c;
        }

        public final int g() {
            return this.f;
        }

        public final boolean h() {
            return this.e;
        }

        public final int i() {
            return this.h;
        }

        public final boolean j() {
            return this.g;
        }
    }
}
