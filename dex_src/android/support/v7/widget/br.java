package android.support.v7.widget;

import android.text.Editable;
import android.text.TextWatcher;

// compiled from: SearchView.java
final class br implements TextWatcher {
    final /* synthetic */ SearchView a;

    br(SearchView searchView) {
        this.a = searchView;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        SearchView.a(this.a, charSequence);
    }

    public final void afterTextChanged(Editable editable) {
    }
}
