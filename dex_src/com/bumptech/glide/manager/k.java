package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import com.bumptech.glide.e;
import com.bumptech.glide.g;
import java.util.HashSet;

@TargetApi(11)
// compiled from: RequestManagerFragment.java
public final class k extends Fragment {
    public final a a;
    public final m b;
    public g c;
    private final HashSet<k> d;
    private k e;

    // compiled from: RequestManagerFragment.java
    private class a implements m {
        private a() {
        }
    }

    public k() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    private k(a aVar) {
        this.b = new a();
        this.d = new HashSet();
        this.a = aVar;
    }

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        this.e = l.a().a(getActivity().getFragmentManager());
        if (this.e != this) {
            this.e.d.add(this);
        }
    }

    public final void onDetach() {
        super.onDetach();
        if (this.e != null) {
            this.e.d.remove(this);
            this.e = null;
        }
    }

    public final void onStart() {
        super.onStart();
        this.a.a();
    }

    public final void onStop() {
        super.onStop();
        this.a.b();
    }

    public final void onDestroy() {
        super.onDestroy();
        this.a.c();
    }

    public final void onTrimMemory(int i) {
        if (this.c != null) {
            e eVar = this.c.d;
            eVar.b.a(i);
            eVar.c.a(i);
        }
    }

    public final void onLowMemory() {
        if (this.c != null) {
            this.c.a();
        }
    }
}
