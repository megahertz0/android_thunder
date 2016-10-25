package anet.channel.strategy;

import android.os.AsyncTask;

// compiled from: Taobao
class j extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    protected Void a() {
        if (!i.a(this.a)) {
            this.a.a.b();
        }
        return null;
    }
}
