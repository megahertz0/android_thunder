package com.aplayer.aplayerandroid;

// compiled from: APlayerAndroid.java
final class b implements Runnable {
    final /* synthetic */ APlayerAndroid a;

    b(APlayerAndroid aPlayerAndroid) {
        this.a = aPlayerAndroid;
    }

    public final void run() {
        APlayerAndroid.access$0();
        this.a.Close();
        if (this.a.IsSystemPlayer()) {
            APlayerAndroid.access$16(this.a).b();
        }
        while (this.a.GetState() != 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        APlayerAndroid.access$17(this.a, APlayerAndroid.access$2(this.a));
    }
}
