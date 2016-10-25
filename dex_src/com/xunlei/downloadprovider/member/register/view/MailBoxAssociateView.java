package com.xunlei.downloadprovider.member.register.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import com.tencent.open.yyb.TitleBar;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import java.lang.reflect.Field;

public class MailBoxAssociateView extends AutoCompleteTextView {
    public c a;
    private boolean b;

    public MailBoxAssociateView(Context context) {
        super(context);
        setDropDownBackgroundResource(2130837639);
    }

    public MailBoxAssociateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDropDownBackgroundResource(2130837639);
    }

    public MailBoxAssociateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setDropDownBackgroundResource(2130837639);
    }

    protected void replaceText(CharSequence charSequence) {
        super.replaceText(charSequence);
        String toString = charSequence.toString();
        int indexOf = toString.indexOf("@");
        if (indexOf != -1) {
            StatReporter.reportMaildoMainClick(toString.substring(indexOf));
        }
    }

    @TargetApi(11)
    public void showDropDown() {
        super.showDropDown();
        setDropDownWidth(getWidth() + g.a(getContext(), TitleBar.SHAREBTN_RIGHT_MARGIN));
        setDropDownVerticalOffset(-g.a(getContext(), 4.0f));
        setDropDownHorizontalOffset(-g.a(getContext(), 5.0f));
        if (VERSION.SDK_INT >= 11) {
            try {
                Field declaredField = getClass().getSuperclass().getDeclaredField("mPopup");
                declaredField.setAccessible(true);
                if (declaredField.getType() == ListPopupWindow.class) {
                    try {
                        ListView listView = ((ListPopupWindow) declaredField.get(this)).getListView();
                        if (listView != null) {
                            listView.setDivider(getContext().getResources().getDrawable(2130838772));
                            listView.setDividerHeight(g.a(getContext(), 3.0f));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
            }
        }
    }

    public boolean enoughToFilter() {
        return this.b ? getText().length() > 0 : getText().toString().contains("@") && getText().toString().indexOf("@") > 0;
    }
}
