package com.xunlei.downloadprovider.frame.user.account.ui;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

// compiled from: UserAccountNicknameActivity.java
final class n implements TextWatcher {
    final /* synthetic */ UserAccountNicknameActivity a;
    private int b;
    private int c;
    private int d;

    n(UserAccountNicknameActivity userAccountNicknameActivity) {
        this.a = userAccountNicknameActivity;
        this.d = 30;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        this.b = this.a.b.getSelectionStart();
        this.c = this.a.b.getSelectionEnd();
        this.a.b.removeTextChangedListener(this.a.g);
        if (!TextUtils.isEmpty(this.a.b.getText())) {
            while (UserAccountNicknameActivity.a(editable.toString()) > this.d) {
                editable.delete(this.b - 1, this.c);
                this.b--;
                this.c--;
                new StringBuilder("editStart = ").append(this.b).append(" editEnd = ").append(this.c);
            }
        }
        this.a.a = editable.toString();
        this.a.b.setText(this.a.a);
        this.a.b.setSelection(this.b);
        this.a.b.addTextChangedListener(this.a.g);
        this.a.a();
        UserAccountNicknameActivity userAccountNicknameActivity = this.a;
        Intent intent = this.a.getIntent();
        intent.putExtra("newNickname", userAccountNicknameActivity.a);
        userAccountNicknameActivity.setResult(0, intent);
    }
}
