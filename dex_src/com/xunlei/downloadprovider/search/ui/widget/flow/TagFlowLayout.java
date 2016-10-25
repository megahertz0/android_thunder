package com.xunlei.downloadprovider.search.ui.widget.flow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.android.spdy.SpdyProtocol;

public class TagFlowLayout extends FlowLayout implements a {
    private a c;
    private boolean d;
    private int e;
    private MotionEvent f;
    private Set<Integer> g;
    private a h;
    private b i;

    public static interface a {
    }

    public static interface b {
        boolean a();
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = true;
        this.e = -1;
        this.g = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.d = obtainStyledAttributes.getBoolean(0, true);
        this.e = obtainStyledAttributes.getInt(1, -1);
        obtainStyledAttributes.recycle();
        if (this.d) {
            setClickable(true);
        }
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            b bVar = (b) getChildAt(i3);
            if (bVar.getVisibility() != 8 && bVar.getTagView().getVisibility() == 8) {
                bVar.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
        }
        super.onMeasure(i, i2);
    }

    public void setOnSelectListener(a aVar) {
        this.h = aVar;
        if (this.h != null) {
            setClickable(true);
        }
    }

    public void setOnTagClickListener(b bVar) {
        this.i = bVar;
        if (bVar != null) {
            setClickable(true);
        }
    }

    public void setAdapter(a aVar) {
        this.c = aVar;
        this.c.b = this;
        removeAllViews();
        a aVar2 = this.c;
        Collection collection = this.c.c;
        int i = 0;
        while (true) {
            int i2;
            if (aVar2.a == null) {
                i2 = 0;
            } else {
                i2 = aVar2.a.size();
            }
            if (i < i2) {
                aVar2.a.get(i);
                View a = aVar2.a();
                View bVar = new b(getContext());
                a.setDuplicateParentStateEnabled(true);
                LayoutParams layoutParams = a.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = generateDefaultLayoutParams();
                }
                bVar.setLayoutParams(layoutParams);
                bVar.addView(a);
                addView(bVar);
                if (collection.contains(Integer.valueOf(i))) {
                    bVar.setChecked(true);
                }
                i++;
            } else {
                this.g.addAll(collection);
                return;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            this.f = MotionEvent.obtain(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean performClick() {
        if (this.f == null) {
            return super.performClick();
        }
        View view;
        int x = (int) this.f.getX();
        int y = (int) this.f.getY();
        this.f = null;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view2 = (b) getChildAt(i);
            if (view2.getVisibility() != 8) {
                Rect rect = new Rect();
                view2.getHitRect(rect);
                if (rect.contains(x, y)) {
                    view = view2;
                    break;
                }
            }
        }
        view = null;
        x = a(view);
        if (view != null) {
            if (this.d) {
                if (view.isChecked()) {
                    view.setChecked(false);
                    this.g.remove(Integer.valueOf(x));
                } else if (this.e == 1 && this.g.size() == 1) {
                    Integer num = (Integer) this.g.iterator().next();
                    ((b) getChildAt(num.intValue())).setChecked(false);
                    view.setChecked(true);
                    this.g.remove(num);
                    this.g.add(Integer.valueOf(x));
                } else if (this.e <= 0 || this.g.size() < this.e) {
                    view.setChecked(true);
                    this.g.add(Integer.valueOf(x));
                }
                if (this.h != null) {
                    HashSet hashSet = new HashSet(this.g);
                }
            }
            if (this.i != null) {
                b bVar = this.i;
                view.getTagView();
                return bVar.a();
            }
        }
        return super.performClick();
    }

    public void setMaxSelectCount(int i) {
        if (this.g.size() > i) {
            new StringBuilder("you has already select more than ").append(i).append(" views , so it will be clear .");
            this.g.clear();
        }
        this.e = i;
    }

    public Set<Integer> getSelectedList() {
        return new HashSet(this.g);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("key_default", super.onSaveInstanceState());
        String str = BuildConfig.VERSION_NAME;
        if (this.g.size() > 0) {
            String str2 = str;
            for (Integer num : this.g) {
                str2 = str2 + num.intValue() + "|";
            }
            str = str2.substring(0, str2.length() - 1);
        }
        bundle.putString("key_choose_pos", str);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            Object string = bundle.getString("key_choose_pos");
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split("\\|");
                int length = split.length;
                for (int i = 0; i < length; i++) {
                    int parseInt = Integer.parseInt(split[i]);
                    this.g.add(Integer.valueOf(parseInt));
                    ((b) getChildAt(parseInt)).setChecked(true);
                }
            }
            super.onRestoreInstanceState(bundle.getParcelable("key_default"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    private int a(View view) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i) == view) {
                return i;
            }
        }
        return -1;
    }
}
