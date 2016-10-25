package com.xunlei.downloadprovider.commonview.dialog;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

@Deprecated
public class XLAlarmDialog extends XLBaseDialog {
    public static final String TAG = "XLAlarmDialog";
    private TextView mContent;
    public CheckBox mContentCheckbox;
    public TextView mContentRight;
    private Context mContext;
    public TextView mLeftBtn;
    private ImageView mLeftIcon;
    public TextView mRightBtn;
    private TextView mTitle;
    private Object mUserData;

    public XLAlarmDialog(Context context) {
        super(context, R.style.bt_dialog);
        this.mContext = context;
        initUI();
    }

    public void setUserData(Object obj) {
        this.mUserData = obj;
    }

    public Object getUserData() {
        return this.mUserData;
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.mTitle.setText(charSequence);
        } else {
            this.mTitle.setText(R.string.tips);
        }
    }

    public void setContent(CharSequence charSequence) {
        if (charSequence != null) {
            this.mContent.setText(charSequence);
        }
    }

    public void setContentLineSpacing(int i) {
        this.mContent.setLineSpacing((float) i, 1.0f);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            this.mLeftIcon.setVisibility(0);
            this.mLeftIcon.setImageDrawable(drawable);
            return;
        }
        this.mLeftIcon.setVisibility(XZBDevice.Wait);
    }

    @Deprecated
    public void setLeftBtnStr(String str) {
        if (str != null) {
            this.mLeftBtn.setText(str);
        }
    }

    @Deprecated
    public void setRightBtnStr(String str) {
        if (str != null) {
            this.mRightBtn.setText(str);
        }
    }

    @Deprecated
    public void setRightBtnBackground(Drawable drawable) {
        if (drawable != null) {
            this.mRightBtn.setBackgroundDrawable(drawable);
        }
    }

    @Deprecated
    public void setRightBtnBackground(int i) {
        this.mRightBtn.setBackgroundResource(i);
    }

    @Deprecated
    public void setRightBtnTextColor(int i) {
        this.mRightBtn.setTextColor(i);
    }

    @Deprecated
    public void setLeftBtnListener(OnClickListener onClickListener) {
        this.mLeftBtn.setTag(onClickListener);
        this.mLeftBtn.setOnClickListener(new a(this));
    }

    @Deprecated
    public void setRightBtnListener(OnClickListener onClickListener) {
        this.mRightBtn.setTag(onClickListener);
        this.mRightBtn.setOnClickListener(new b(this));
    }

    private void initUI() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.xl_two_button_dialog, null);
        this.mTitle = (TextView) inflate.findViewById(R.id.dlg_title);
        this.mContentCheckbox = (CheckBox) inflate.findViewById(R.id.dlg_content_checkbox);
        this.mContent = (TextView) inflate.findViewById(R.id.dlg_content);
        this.mContentRight = (TextView) inflate.findViewById(R.id.dlg_content_right);
        this.mLeftIcon = (ImageView) inflate.findViewById(R.id.dlg_left_icon);
        this.mLeftBtn = (TextView) inflate.findViewById(R.id.dlg_left_btn);
        this.mRightBtn = (TextView) inflate.findViewById(R.id.dlg_right_btn);
        OnClickListener cVar = new c(this);
        setLeftBtnListener(cVar);
        setRightBtnListener(cVar);
        setContentView(inflate);
        setCanceledOnTouchOutside(true);
    }

    public void setContentGravity(int i) {
        this.mContent.setGravity(i);
    }

    public void setCancelButtonText(CharSequence charSequence) {
        if (this.mLeftBtn == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.mLeftBtn.setText(R.string.cancel);
        } else {
            this.mLeftBtn.setText(charSequence);
        }
    }

    public void setConfirmButtonText(CharSequence charSequence) {
        if (this.mRightBtn == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.mRightBtn.setText(R.string.ok);
        } else {
            this.mRightBtn.setText(charSequence);
        }
    }

    public void setOnClickCancelButtonListener(OnClickListener onClickListener) {
        setLeftBtnListener(onClickListener);
    }

    public void setOnClickConfirmButtonListener(OnClickListener onClickListener) {
        setRightBtnListener(onClickListener);
    }
}
