package com.xunlei.tdlive.usercenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.LivePlayerActivity;
import com.xunlei.tdlive.MainActivity;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.q.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyAgent;

// compiled from: ViewHelper.java
public class ah {
    public static void a(View view, String str, String str2, OnClickListener onClickListener) {
        TextView textView = (TextView) view.findViewById(R.id.tvEmptyBtn);
        ((TextView) view.findViewById(R.id.tvEmptyText)).setText(str);
        if (TextUtils.isEmpty(str2)) {
            textView.setVisibility(XZBDevice.Wait);
            return;
        }
        textView.setVisibility(0);
        textView.setText(str2);
        textView.setOnClickListener(onClickListener);
    }

    public static void a(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(603979776);
        intent.putExtra("MainActivity.EXTRA_PAGE_TAG", "main");
        intent.putExtra("MainActivity.EXTRA_TRACE_FLAG", XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        context.startActivity(intent);
    }

    public static void a(Activity activity) {
        LivePlayerActivity.a(activity, null, 0, e.j);
        Map hashMap = new HashMap();
        hashMap.put("network", String.valueOf(ac.b()));
        q.a("live_prepare", "centerlive", null, hashMap);
    }

    public static String a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "centerhome";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "centerfans";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "centerattention";
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return "roomhost";
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return "roompopup";
            case R.styleable.Toolbar_contentInsetEnd:
                return "liveending";
            case R.styleable.Toolbar_contentInsetLeft:
                return "list";
            default:
                return null;
        }
    }

    public static void a(int i, boolean z, String str) {
        String a = a(i);
        String str2 = z ? "attention" : "noattention";
        a aVar = new a();
        aVar.a("userid", str);
        q.a("user_attention", a, str2, aVar.a());
    }

    public static void a(String str, int i) {
        Object obj;
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                obj = "live_list";
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                obj = "fans_list";
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                obj = "attention_list";
                break;
            default:
                obj = null;
                break;
        }
        if (!TextUtils.isEmpty(obj)) {
            a aVar = new a();
            aVar.a("hostid", str);
            q.a(obj, null, null, aVar.a());
        }
    }

    public static void a(View view, OnClickListener onClickListener, OnDismissListener onDismissListener) {
        TextView textView = (TextView) LayoutInflater.from(view.getContext()).inflate(R.layout.xllive_delete_tip, null, false);
        PopupWindow popupWindow = new PopupWindow(textView, -2, -2);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(onDismissListener);
        textView.setOnClickListener(new ai(popupWindow, onClickListener));
        popupWindow.showAsDropDown(view, Impl.STATUS_SUCCESS, (0 - view.getHeight()) - 76);
    }

    public static Dialog a(Context context, String str, boolean z) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.xllive_loading, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.dialog_view);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.img);
        TextView textView = (TextView) inflate.findViewById(R.id.tipTextView);
        imageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.xllive_loading_circle_ani));
        textView.setText(str);
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        dialog.setCancelable(z);
        dialog.setContentView(linearLayout);
        return dialog;
    }
}
