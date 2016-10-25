package com.xunlei.tdlive;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.message.proguard.j;
import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;

// compiled from: PhotoSelectActivity.java
class dr extends CursorAdapter {
    final /* synthetic */ PhotoSelectActivity a;

    // compiled from: PhotoSelectActivity.java
    class a {
        ImageView a;
        TextView b;
        boolean c;

        a(ImageView imageView, TextView textView) {
            this.c = false;
            this.a = imageView;
            this.b = textView;
            this.c = false;
        }
    }

    dr(PhotoSelectActivity photoSelectActivity, Context context, Cursor cursor, boolean z) {
        this.a = photoSelectActivity;
        super(context, cursor, z);
    }

    public int getCount() {
        return super.getCount() + 1;
    }

    public Object getItem(int i) {
        return i == 0 ? null : super.getItem(i - 1);
    }

    public long getItemId(int i) {
        return i == 0 ? -1 : super.getItemId(i - 1);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i != 0) {
            return super.getView(i - 1, view, viewGroup);
        }
        if (view == null) {
            view = newView(this.mContext, null, viewGroup);
        }
        bindView(view, this.mContext, null);
        return view;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        boolean z = true;
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(R.styleable.Toolbar_maxButtonHeight);
        linearLayout.setLayoutParams(new LayoutParams(this.a.i, this.a.i));
        View imageView = new ImageView(context);
        imageView.setId(16908331);
        imageView.setScaleType(ScaleType.FIT_XY);
        linearLayout.addView(imageView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        View textView = new TextView(context);
        textView.setTextSize(10.0f);
        textView.setTextColor(-5658199);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        a aVar = new a(imageView, textView);
        if (cursor != null) {
            z = false;
        }
        aVar.c = z;
        linearLayout.setTag(aVar);
        return linearLayout;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        boolean z = false;
        try {
            a aVar = (a) view.getTag();
            if (cursor == null) {
                z = true;
            }
            if (z != aVar.c) {
                aVar.c = z;
            } else if (this.a.j) {
                return;
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) aVar.a.getLayoutParams();
            if (cursor == null) {
                layoutParams.width = -2;
                layoutParams.height = -2;
                layoutParams.weight = 0.0f;
                aVar.b.setVisibility(0);
                aVar.b.setText(this.a.h);
                aVar.a.setImageResource(R.drawable.xllive_image_catpure);
            } else {
                layoutParams.width = -1;
                layoutParams.height = 0;
                layoutParams.weight = 1.0f;
                aVar.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                aVar.a.setImageResource(R.drawable.xllive_image_catpure);
                com.xunlei.tdlive.util.a.a(context).a(aVar.a, Uri.withAppendedPath(Thumbnails.EXTERNAL_CONTENT_URI, String.valueOf(cursor.getLong(cursor.getColumnIndex(j.g)))).toString(), com.xunlei.tdlive.util.a.b(context));
                this.a.b.contains(Integer.valueOf(cursor.getPosition()));
            }
            aVar.a.setLayoutParams(layoutParams);
        } catch (Exception e) {
        }
    }
}
