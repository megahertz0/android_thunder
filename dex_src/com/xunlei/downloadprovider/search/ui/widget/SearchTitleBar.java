package com.xunlei.downloadprovider.search.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.xunlei.downloadprovider.model.protocol.d.a;

public class SearchTitleBar extends FrameLayout {
    public EditText a;
    public TextWatcher b;
    private ImageView c;
    private TextView d;
    private TextWatcher e;
    private OnClickListener f;

    public SearchTitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new a(this);
        a(context);
    }

    public SearchTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new a(this);
        a(context);
    }

    public SearchTitleBar(Context context) {
        super(context);
        this.e = new a(this);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(2130968956, this, true);
        this.a = (EditText) findViewById(2131756878);
        this.a.addTextChangedListener(this.e);
        CharSequence charSequence = a.a().c;
        if (!TextUtils.isEmpty(charSequence)) {
            this.a.setHint(charSequence);
        }
        this.c = (ImageView) findViewById(2131756877);
        this.c.setOnClickListener(new f(this));
        this.d = (TextView) findViewById(2131756876);
    }

    public String getEditTextContent() {
        return this.a.getEditableText().toString().trim();
    }

    public void setEditText(String str) {
        this.a.setText(str);
        if (!TextUtils.isEmpty(str)) {
            this.a.setSelection(str.length());
        }
    }

    public void setEditClickListener(OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }

    public void setEditHint(String str) {
        this.a.setHint(str);
    }

    public void setCancelListener(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public final void a() {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
    }

    public void setOnEditorActionListener(OnEditorActionListener onEditorActionListener) {
        this.a.setOnEditorActionListener(onEditorActionListener);
    }

    public void setDeleteClickListener(OnClickListener onClickListener) {
        this.f = onClickListener;
    }
}
