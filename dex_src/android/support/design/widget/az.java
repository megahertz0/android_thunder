package android.support.design.widget;

import android.text.Editable;
import android.text.TextWatcher;

// compiled from: TextInputLayout.java
final class az implements TextWatcher {
    final /* synthetic */ TextInputLayout a;

    az(TextInputLayout textInputLayout) {
        this.a = textInputLayout;
    }

    public final void afterTextChanged(Editable editable) {
        this.a.a(true);
        if (this.a.l) {
            this.a.a(editable.length());
        }
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
