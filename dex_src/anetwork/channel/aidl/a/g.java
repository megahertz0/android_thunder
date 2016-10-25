package anetwork.channel.aidl.a;

// compiled from: Taobao
final class g implements Runnable {
    final /* synthetic */ byte a;
    final /* synthetic */ Object b;
    final /* synthetic */ f c;

    g(f fVar, byte b, Object obj) {
        this.c = fVar;
        this.a = b;
        this.b = obj;
    }

    public final void run() {
        f.a(this.c, this.a, this.b);
    }
}
