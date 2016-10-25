package com.sina.weibo.sdk.api.share.ui;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class EditBlogView extends EditText {
    private boolean canSelectionChanged;
    private int count;
    private Context ctx;
    private List<OnSelectionListener> listeners;
    private OnEnterListener mOnEnterListener;

    class AnonymousClass_1 extends InputConnectionWrapper {
        AnonymousClass_1(InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
        }

        public boolean commitText(CharSequence charSequence, int i) {
            Object editableText = EditBlogView.this.getEditableText();
            String str = new String(editableText.toString());
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            if (!(selectionStart == -1 || selectionEnd == -1)) {
                int correctPosition = EditBlogView.this.correctPosition(selectionStart);
                int correctPosition2 = EditBlogView.this.correctPosition(selectionEnd);
                if (correctPosition <= correctPosition2) {
                    int i2 = correctPosition2;
                    correctPosition2 = correctPosition;
                    correctPosition = i2;
                }
                if (!(correctPosition2 == selectionStart && correctPosition == selectionEnd)) {
                    Selection.setSelection(editableText, correctPosition2, correctPosition);
                }
                if (correctPosition2 != correctPosition) {
                    EditBlogView.this.getText().delete(correctPosition2, correctPosition);
                }
            }
            return super.commitText(charSequence, i);
        }

        public boolean setComposingText(CharSequence charSequence, int i) {
            Object editableText = EditBlogView.this.getEditableText();
            String str = new String(editableText.toString());
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            if (!(selectionStart == -1 || selectionEnd == -1)) {
                int correctPosition = EditBlogView.this.correctPosition(selectionStart);
                int correctPosition2 = EditBlogView.this.correctPosition(selectionEnd);
                if (correctPosition <= correctPosition2) {
                    int i2 = correctPosition2;
                    correctPosition2 = correctPosition;
                    correctPosition = i2;
                }
                if (!(correctPosition2 == selectionStart && correctPosition == selectionEnd)) {
                    Selection.setSelection(editableText, correctPosition2, correctPosition);
                }
                if (correctPosition2 != correctPosition) {
                    EditBlogView.this.getText().delete(correctPosition2, correctPosition);
                }
            }
            return super.setComposingText(charSequence, i);
        }
    }

    public static interface OnEnterListener {
        void onEnterKey();
    }

    public static interface OnSelectionListener {
        void onSelectionChanged(int i, int i2);
    }

    public EditBlogView(Context context) {
        super(context);
        this.canSelectionChanged = true;
        init();
    }

    public EditBlogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.canSelectionChanged = true;
        init();
    }

    public EditBlogView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.canSelectionChanged = true;
        init();
    }

    private void init() {
        this.ctx = getContext();
        this.listeners = new ArrayList();
    }

    public void setOnSelectionListener(OnSelectionListener onSelectionListener) {
        this.listeners.add(onSelectionListener);
    }

    protected void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        if (this.canSelectionChanged && this.listeners != null && !this.listeners.isEmpty()) {
            for (OnSelectionListener onSelectionListener : this.listeners) {
                onSelectionListener.onSelectionChanged(i, i2);
            }
        }
    }

    public void enableSelectionChanged(boolean z) {
        this.canSelectionChanged = z;
    }

    public void setOnEnterListener(OnEnterListener onEnterListener) {
        this.mOnEnterListener = onEnterListener;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 66 && this.mOnEnterListener != null) {
            this.mOnEnterListener.onEnterKey();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public int correctPosition(int i) {
        if (i == -1) {
            return i;
        }
        Editable text = getText();
        if (i >= text.length()) {
            return i;
        }
        Object[] spans = text.getSpans(i, i, ImageSpan.class);
        return (spans == null || spans.length == 0 || i == text.getSpanStart(spans[0])) ? i : text.getSpanEnd(spans[0]);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return new AnonymousClass_1(super.onCreateInputConnection(editorInfo), false);
    }
}
