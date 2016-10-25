package com.xunlei.downloadprovider.member.register.view;

// compiled from: LoginRegisterToast.java
final class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        a aVar = this.a;
        if (aVar.a) {
            try {
                aVar.c.invoke(aVar.b, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            aVar.a = false;
        }
    }
}
