package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import com.bumptech.glide.g;
import java.util.HashSet;

public class SupportRequestManagerFragment extends Fragment {
    public g a;
    public final a b;
    public final m c;
    private final HashSet<SupportRequestManagerFragment> d;
    private SupportRequestManagerFragment e;

    private class a implements m {
        private a() {
        }
    }

    public SupportRequestManagerFragment() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    private SupportRequestManagerFragment(a aVar) {
        this.c = new a();
        this.d = new HashSet();
        this.b = aVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.e = l.a().a(getActivity().getSupportFragmentManager());
        if (this.e != this) {
            this.e.d.add(this);
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.e != null) {
            this.e.d.remove(this);
            this.e = null;
        }
    }

    public void onStart() {
        super.onStart();
        this.b.a();
    }

    public void onStop() {
        super.onStop();
        this.b.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.c();
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.a != null) {
            this.a.a();
        }
    }
}
