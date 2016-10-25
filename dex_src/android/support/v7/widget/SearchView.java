package android.support.v7.widget;

import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.ResultReceiver;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView.OnEditorActionListener;
import com.alipay.sdk.util.h;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.android.spdy.SpdyAgent;

public class SearchView extends LinearLayoutCompat implements android.support.v7.view.c {
    static final a a;
    private static final boolean c;
    private CursorAdapter A;
    private boolean B;
    private CharSequence C;
    private boolean D;
    private boolean E;
    private int F;
    private boolean G;
    private CharSequence H;
    private CharSequence I;
    private boolean J;
    private int K;
    private SearchableInfo L;
    private Bundle M;
    private final r N;
    private Runnable O;
    private final Runnable P;
    private Runnable Q;
    private final WeakHashMap<String, ConstantState> R;
    private final OnClickListener S;
    private final OnEditorActionListener T;
    private final OnItemClickListener U;
    private final OnItemSelectedListener V;
    private TextWatcher W;
    OnKeyListener b;
    private final SearchAutoComplete d;
    private final View e;
    private final View f;
    private final View g;
    private final ImageView h;
    private final ImageView i;
    private final ImageView j;
    private final ImageView k;
    private final View l;
    private final ImageView m;
    private final Drawable n;
    private final int o;
    private final int p;
    private final Intent q;
    private final Intent r;
    private final CharSequence s;
    private c t;
    private b u;
    private OnFocusChangeListener v;
    private d w;
    private OnClickListener x;
    private boolean y;
    private boolean z;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        boolean a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.a = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.a));
        }

        public String toString() {
            return new StringBuilder("SearchView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" isIconified=").append(this.a).append(h.d).toString();
        }

        static {
            CREATOR = new ca();
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        private int a;
        private SearchView b;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.a = getThreshold();
        }

        void setSearchView(SearchView searchView) {
            this.b = searchView;
        }

        public void setThreshold(int i) {
            super.setThreshold(i);
            this.a = i;
        }

        protected void replaceText(CharSequence charSequence) {
        }

        public void performCompletion() {
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.b.hasFocus() && getVisibility() == 0) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                if (SearchView.a(getContext())) {
                    a.a(this);
                }
            }
        }

        protected void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.b.d();
        }

        public boolean enoughToFilter() {
            return this.a <= 0 || super.enoughToFilter();
        }

        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                DispatcherState keyDispatcherState;
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState == null) {
                        return true;
                    }
                    keyDispatcherState.startTracking(keyEvent, this);
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.b.clearFocus();
                        this.b.setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        static /* synthetic */ boolean a(android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete) {
            return TextUtils.getTrimmedLength(searchAutoComplete.getText()) == 0;
        }
    }

    private static class a {
        Method a;
        Method b;
        Method c;
        private Method d;

        a() {
            try {
                this.a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.a.setAccessible(true);
            } catch (NoSuchMethodException e) {
            }
            try {
                this.b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.b.setAccessible(true);
            } catch (NoSuchMethodException e2) {
            }
            try {
                this.d = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[]{Boolean.TYPE});
                this.d.setAccessible(true);
            } catch (NoSuchMethodException e3) {
            }
            try {
                this.c = InputMethodManager.class.getMethod("showSoftInputUnchecked", new Class[]{Integer.TYPE, ResultReceiver.class});
                this.c.setAccessible(true);
            } catch (NoSuchMethodException e4) {
            }
        }

        final void a(AutoCompleteTextView autoCompleteTextView) {
            if (this.d != null) {
                try {
                    this.d.invoke(autoCompleteTextView, new Object[]{Boolean.valueOf(true)});
                } catch (Exception e) {
                }
            }
        }
    }

    public static interface b {
        boolean a();
    }

    public static interface c {
        boolean a();
    }

    public static interface d {
        boolean a();

        boolean b();
    }

    static /* synthetic */ void a(SearchView searchView, CharSequence charSequence) {
        boolean z = true;
        CharSequence text = searchView.d.getText();
        searchView.I = text;
        boolean z2 = !TextUtils.isEmpty(text);
        searchView.b(z2);
        if (z2) {
            z = false;
        }
        searchView.c(z);
        searchView.g();
        searchView.f();
        if (!(searchView.t == null || TextUtils.equals(charSequence, searchView.H))) {
            charSequence.toString();
        }
        searchView.H = charSequence.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void l(android.support.v7.widget.SearchView r11) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.SearchView.l(android.support.v7.widget.SearchView):void");
        /*
        r0 = 0;
        r1 = r11.L;
        if (r1 == 0) goto L_0x0027;
    L_0x0005:
        r5 = r11.L;
        r1 = r5.getVoiceSearchLaunchWebSearch();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        if (r1 == 0) goto L_0x002d;
    L_0x000d:
        r1 = r11.q;	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r2 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r2.<init>(r1);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r1 = r5.getSearchActivity();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r3 = "calling_package";
        if (r1 != 0) goto L_0x0028;
    L_0x001d:
        r2.putExtra(r3, r0);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r0 = r11.getContext();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r0.startActivity(r2);	 Catch:{ ActivityNotFoundException -> 0x00dc }
    L_0x0027:
        return;
    L_0x0028:
        r0 = r1.flattenToShortString();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        goto L_0x001d;
    L_0x002d:
        r1 = r5.getVoiceSearchLaunchRecognizer();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        if (r1 == 0) goto L_0x0027;
    L_0x0033:
        r1 = r11.r;	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r6 = r5.getSearchActivity();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r2 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r3 = "android.intent.action.SEARCH";
        r2.<init>(r3);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r2.setComponent(r6);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r3 = r11.getContext();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r4 = 0;
        r7 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = android.app.PendingIntent.getActivity(r3, r4, r2, r7);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r8 = new android.os.Bundle;	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r8.<init>();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r2 = r11.M;	 Catch:{ ActivityNotFoundException -> 0x00dc }
        if (r2 == 0) goto L_0x0060;
    L_0x0058:
        r2 = "app_data";
        r3 = r11.M;	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r8.putParcelable(r2, r3);	 Catch:{ ActivityNotFoundException -> 0x00dc }
    L_0x0060:
        r9 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r9.<init>(r1);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r2 = "free_form";
        r1 = 1;
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r4 = 8;
        if (r3 < r4) goto L_0x00ea;
    L_0x006f:
        r10 = r11.getResources();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r3 = r5.getVoiceLanguageModeId();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        if (r3 == 0) goto L_0x00e8;
    L_0x0079:
        r2 = r5.getVoiceLanguageModeId();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r4 = r10.getString(r2);	 Catch:{ ActivityNotFoundException -> 0x00dc }
    L_0x0081:
        r2 = r5.getVoicePromptTextId();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        if (r2 == 0) goto L_0x00e6;
    L_0x0087:
        r2 = r5.getVoicePromptTextId();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r3 = r10.getString(r2);	 Catch:{ ActivityNotFoundException -> 0x00dc }
    L_0x008f:
        r2 = r5.getVoiceLanguageId();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        if (r2 == 0) goto L_0x00e4;
    L_0x0095:
        r2 = r5.getVoiceLanguageId();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r2 = r10.getString(r2);	 Catch:{ ActivityNotFoundException -> 0x00dc }
    L_0x009d:
        r10 = r5.getVoiceMaxResults();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        if (r10 == 0) goto L_0x00a7;
    L_0x00a3:
        r1 = r5.getVoiceMaxResults();	 Catch:{ ActivityNotFoundException -> 0x00dc }
    L_0x00a7:
        r5 = "android.speech.extra.LANGUAGE_MODEL";
        r9.putExtra(r5, r4);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r4 = "android.speech.extra.PROMPT";
        r9.putExtra(r4, r3);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r3 = "android.speech.extra.LANGUAGE";
        r9.putExtra(r3, r2);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r2 = "android.speech.extra.MAX_RESULTS";
        r9.putExtra(r2, r1);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r1 = "calling_package";
        if (r6 != 0) goto L_0x00df;
    L_0x00c4:
        r9.putExtra(r1, r0);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r0 = "android.speech.extra.RESULTS_PENDINGINTENT";
        r9.putExtra(r0, r7);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r0 = "android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE";
        r9.putExtra(r0, r8);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r0 = r11.getContext();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        r0.startActivity(r9);	 Catch:{ ActivityNotFoundException -> 0x00dc }
        goto L_0x0027;
    L_0x00dc:
        r0 = move-exception;
        goto L_0x0027;
    L_0x00df:
        r0 = r6.flattenToShortString();	 Catch:{ ActivityNotFoundException -> 0x00dc }
        goto L_0x00c4;
    L_0x00e4:
        r2 = r0;
        goto L_0x009d;
    L_0x00e6:
        r3 = r0;
        goto L_0x008f;
    L_0x00e8:
        r4 = r2;
        goto L_0x0081;
    L_0x00ea:
        r3 = r0;
        r4 = r2;
        r2 = r0;
        goto L_0x00a7;
        */
    }

    static {
        boolean z;
        if (VERSION.SDK_INT >= 8) {
            z = true;
        } else {
            z = false;
        }
        c = z;
        a = new a();
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.O = new bo(this);
        this.P = new bs(this);
        this.Q = new bt(this);
        this.R = new WeakHashMap();
        this.S = new bx(this);
        this.b = new by(this);
        this.T = new bz(this);
        this.U = new bp(this);
        this.V = new bq(this);
        this.W = new br(this);
        this.N = r.a();
        cm a = cm.a(context, attributeSet, R.styleable.SearchView, i);
        LayoutInflater.from(context).inflate(a.e(R.styleable.SearchView_layout, R.layout.abc_search_view), this, true);
        this.d = (SearchAutoComplete) findViewById(R.id.search_src_text);
        this.d.setSearchView(this);
        this.e = findViewById(R.id.search_edit_frame);
        this.f = findViewById(R.id.search_plate);
        this.g = findViewById(R.id.submit_area);
        this.h = (ImageView) findViewById(R.id.search_button);
        this.i = (ImageView) findViewById(R.id.search_go_btn);
        this.j = (ImageView) findViewById(R.id.search_close_btn);
        this.k = (ImageView) findViewById(R.id.search_voice_btn);
        this.m = (ImageView) findViewById(R.id.search_mag_icon);
        this.f.setBackgroundDrawable(a.a(R.styleable.SearchView_queryBackground));
        this.g.setBackgroundDrawable(a.a(R.styleable.SearchView_submitBackground));
        this.h.setImageDrawable(a.a(R.styleable.SearchView_searchIcon));
        this.i.setImageDrawable(a.a(R.styleable.SearchView_goIcon));
        this.j.setImageDrawable(a.a(R.styleable.SearchView_closeIcon));
        this.k.setImageDrawable(a.a(R.styleable.SearchView_voiceIcon));
        this.m.setImageDrawable(a.a(R.styleable.SearchView_searchIcon));
        this.n = a.a(R.styleable.SearchView_searchHintIcon);
        this.o = a.e(R.styleable.SearchView_suggestionRowLayout, R.layout.abc_search_dropdown_item_icons_2line);
        this.p = a.e(R.styleable.SearchView_commitIcon, 0);
        this.h.setOnClickListener(this.S);
        this.j.setOnClickListener(this.S);
        this.i.setOnClickListener(this.S);
        this.k.setOnClickListener(this.S);
        this.d.setOnClickListener(this.S);
        this.d.addTextChangedListener(this.W);
        this.d.setOnEditorActionListener(this.T);
        this.d.setOnItemClickListener(this.U);
        this.d.setOnItemSelectedListener(this.V);
        this.d.setOnKeyListener(this.b);
        this.d.setOnFocusChangeListener(new bu(this));
        setIconifiedByDefault(a.a(R.styleable.SearchView_iconifiedByDefault, true));
        int c = a.c(R.styleable.SearchView_android_maxWidth, -1);
        if (c != -1) {
            setMaxWidth(c);
        }
        this.s = a.c(R.styleable.SearchView_defaultQueryHint);
        this.C = a.c(R.styleable.SearchView_queryHint);
        c = a.a(R.styleable.SearchView_android_imeOptions, -1);
        if (c != -1) {
            setImeOptions(c);
        }
        c = a.a(R.styleable.SearchView_android_inputType, -1);
        if (c != -1) {
            setInputType(c);
        }
        setFocusable(a.a(R.styleable.SearchView_android_focusable, true));
        a.a.recycle();
        this.q = new Intent("android.speech.action.WEB_SEARCH");
        this.q.addFlags(268435456);
        this.q.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.r = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.r.addFlags(268435456);
        this.l = findViewById(this.d.getDropDownAnchor());
        if (this.l != null) {
            if (VERSION.SDK_INT >= 11) {
                this.l.addOnLayoutChangeListener(new bv(this));
            } else {
                this.l.getViewTreeObserver().addOnGlobalLayoutListener(new bw(this));
            }
        }
        a(this.y);
        i();
    }

    int getSuggestionRowLayout() {
        return this.o;
    }

    int getSuggestionCommitIconResId() {
        return this.p;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSearchableInfo(android.app.SearchableInfo r9) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.SearchView.setSearchableInfo(android.app.SearchableInfo):void");
        /*
        this = this;
        r4 = 0;
        r7 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r3 = 0;
        r2 = 1;
        r8.L = r9;
        r0 = r8.L;
        if (r0 == 0) goto L_0x0079;
    L_0x000b:
        r0 = c;
        if (r0 == 0) goto L_0x0076;
    L_0x000f:
        r0 = r8.d;
        r1 = r8.L;
        r1 = r1.getSuggestThreshold();
        r0.setThreshold(r1);
        r0 = r8.d;
        r1 = r8.L;
        r1 = r1.getImeOptions();
        r0.setImeOptions(r1);
        r0 = r8.L;
        r0 = r0.getInputType();
        r1 = r0 & 15;
        if (r1 != r2) goto L_0x003f;
    L_0x002f:
        r1 = -65537; // 0xfffffffffffeffff float:NaN double:NaN;
        r0 = r0 & r1;
        r1 = r8.L;
        r1 = r1.getSuggestAuthority();
        if (r1 == 0) goto L_0x003f;
    L_0x003b:
        r0 = r0 | r7;
        r1 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
        r0 = r0 | r1;
    L_0x003f:
        r1 = r8.d;
        r1.setInputType(r0);
        r0 = r8.A;
        if (r0 == 0) goto L_0x004d;
    L_0x0048:
        r0 = r8.A;
        r0.changeCursor(r4);
    L_0x004d:
        r0 = r8.L;
        r0 = r0.getSuggestAuthority();
        if (r0 == 0) goto L_0x0076;
    L_0x0055:
        r0 = new android.support.v7.widget.cg;
        r1 = r8.getContext();
        r5 = r8.L;
        r6 = r8.R;
        r0.<init>(r1, r8, r5, r6);
        r8.A = r0;
        r0 = r8.d;
        r1 = r8.A;
        r0.setAdapter(r1);
        r0 = r8.A;
        r0 = (android.support.v7.widget.cg) r0;
        r1 = r8.D;
        if (r1 == 0) goto L_0x00ba;
    L_0x0073:
        r1 = 2;
    L_0x0074:
        r0.a = r1;
    L_0x0076:
        r8.i();
    L_0x0079:
        r0 = c;
        if (r0 == 0) goto L_0x00cb;
    L_0x007d:
        r0 = r8.L;
        if (r0 == 0) goto L_0x00c9;
    L_0x0081:
        r0 = r8.L;
        r0 = r0.getVoiceSearchEnabled();
        if (r0 == 0) goto L_0x00c9;
    L_0x0089:
        r0 = r8.L;
        r0 = r0.getVoiceSearchLaunchWebSearch();
        if (r0 == 0) goto L_0x00bc;
    L_0x0091:
        r0 = r8.q;
    L_0x0093:
        if (r0 == 0) goto L_0x00c9;
    L_0x0095:
        r1 = r8.getContext();
        r1 = r1.getPackageManager();
        r0 = r1.resolveActivity(r0, r7);
        if (r0 == 0) goto L_0x00c7;
    L_0x00a3:
        r0 = r2;
    L_0x00a4:
        if (r0 == 0) goto L_0x00cb;
    L_0x00a6:
        r8.G = r2;
        r0 = r8.G;
        if (r0 == 0) goto L_0x00b4;
    L_0x00ac:
        r0 = r8.d;
        r1 = "nm";
        r0.setPrivateImeOptions(r1);
    L_0x00b4:
        r0 = r8.z;
        r8.a(r0);
        return;
    L_0x00ba:
        r1 = r2;
        goto L_0x0074;
    L_0x00bc:
        r0 = r8.L;
        r0 = r0.getVoiceSearchLaunchRecognizer();
        if (r0 == 0) goto L_0x00cd;
    L_0x00c4:
        r0 = r8.r;
        goto L_0x0093;
    L_0x00c7:
        r0 = r3;
        goto L_0x00a4;
    L_0x00c9:
        r0 = r3;
        goto L_0x00a4;
    L_0x00cb:
        r2 = r3;
        goto L_0x00a6;
    L_0x00cd:
        r0 = r4;
        goto L_0x0093;
        */
    }

    public void setAppSearchData(Bundle bundle) {
        this.M = bundle;
    }

    public void setImeOptions(int i) {
        this.d.setImeOptions(i);
    }

    public int getImeOptions() {
        return this.d.getImeOptions();
    }

    public void setInputType(int i) {
        this.d.setInputType(i);
    }

    public int getInputType() {
        return this.d.getInputType();
    }

    public boolean requestFocus(int i, Rect rect) {
        if (this.E || !isFocusable()) {
            return false;
        }
        if (this.z) {
            return super.requestFocus(i, rect);
        }
        boolean requestFocus = this.d.requestFocus(i, rect);
        if (requestFocus) {
            a(false);
        }
        return requestFocus;
    }

    public void clearFocus() {
        this.E = true;
        setImeVisibility(false);
        super.clearFocus();
        this.d.clearFocus();
        this.E = false;
    }

    public void setOnQueryTextListener(c cVar) {
        this.t = cVar;
    }

    public void setOnCloseListener(b bVar) {
        this.u = bVar;
    }

    public void setOnQueryTextFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.v = onFocusChangeListener;
    }

    public void setOnSuggestionListener(d dVar) {
        this.w = dVar;
    }

    public void setOnSearchClickListener(OnClickListener onClickListener) {
        this.x = onClickListener;
    }

    public CharSequence getQuery() {
        return this.d.getText();
    }

    public void setQueryHint(CharSequence charSequence) {
        this.C = charSequence;
        i();
    }

    public CharSequence getQueryHint() {
        if (this.C != null) {
            return this.C;
        }
        return (!c || this.L == null || this.L.getHintId() == 0) ? this.s : getContext().getText(this.L.getHintId());
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.y != z) {
            this.y = z;
            a(z);
            i();
        }
    }

    public void setIconified(boolean z) {
        if (z) {
            j();
        } else {
            k();
        }
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.B = z;
        a(this.z);
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.D = z;
        if (this.A instanceof cg) {
            ((cg) this.A).a = z ? XZBDevice.DOWNLOAD_LIST_RECYCLE : 1;
        }
    }

    public void setSuggestionsAdapter(CursorAdapter cursorAdapter) {
        this.A = cursorAdapter;
        this.d.setAdapter(this.A);
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.A;
    }

    public void setMaxWidth(int i) {
        this.F = i;
        requestLayout();
    }

    public int getMaxWidth() {
        return this.F;
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
    }

    private void a(boolean z) {
        boolean z2;
        boolean z3 = true;
        int i = XZBDevice.Wait;
        this.z = z;
        int i2 = z ? 0 : 8;
        if (TextUtils.isEmpty(this.d.getText())) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.h.setVisibility(i2);
        b(z2);
        View view = this.e;
        if (z) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        view.setVisibility(i2);
        if (!(this.m.getDrawable() == null || this.y)) {
            i = 0;
        }
        this.m.setVisibility(i);
        g();
        if (z2) {
            z3 = false;
        }
        c(z3);
        f();
    }

    private boolean e() {
        return (this.B || this.G) && !this.z;
    }

    private void b(boolean z) {
        int i = XZBDevice.Wait;
        if (this.B && e() && hasFocus()) {
            if (z || !this.G) {
                i = 0;
            }
        }
        this.i.setVisibility(i);
    }

    private void f() {
        int i = XZBDevice.Wait;
        if (e()) {
            if (this.i.getVisibility() == 0 || this.k.getVisibility() == 0) {
                i = 0;
            }
        }
        this.g.setVisibility(i);
    }

    private void g() {
        Object obj = 1;
        int i = 0;
        int i2 = !TextUtils.isEmpty(this.d.getText()) ? 1 : 0;
        if (i2 == 0) {
            if (!this.y || this.J) {
                int i3 = 0;
            }
        }
        ImageView imageView = this.j;
        if (i3 == 0) {
            i = XZBDevice.Wait;
        }
        imageView.setVisibility(i);
        Drawable drawable = this.j.getDrawable();
        if (drawable != null) {
            drawable.setState(i2 != 0 ? ENABLED_STATE_SET : EMPTY_STATE_SET);
        }
    }

    private void h() {
        post(this.P);
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.P);
        post(this.Q);
        super.onDetachedFromWindow();
    }

    private void setImeVisibility(boolean z) {
        if (z) {
            post(this.O);
            return;
        }
        removeCallbacks(this.O);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    private boolean a(int i, KeyEvent keyEvent) {
        if (this.L == null || this.A == null || keyEvent.getAction() != 0 || !KeyEventCompat.hasNoModifiers(keyEvent)) {
            return false;
        }
        if (i == 66 || i == 84 || i == 61) {
            return a(this.d.getListSelection());
        }
        if (i != 21 && i != 22) {
            return (i == 19 && this.d.getListSelection() == 0) ? false : false;
        } else {
            int i2;
            if (i == 21) {
                i2 = 0;
            } else {
                i2 = this.d.length();
            }
            this.d.setSelection(i2);
            this.d.setListSelection(0);
            this.d.clearListSelection();
            a.a(this.d);
            return true;
        }
    }

    private void i() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.d;
        if (queryHint == null) {
            queryHint = com.umeng.a.d;
        }
        if (this.y && this.n != null) {
            int textSize = (int) (((double) this.d.getTextSize()) * 1.25d);
            this.n.setBounds(0, 0, textSize, textSize);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.setSpan(new ImageSpan(this.n), 1, XZBDevice.DOWNLOAD_LIST_RECYCLE, com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCopyDrawable);
            spannableStringBuilder.append(queryHint);
            SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        }
        searchAutoComplete.setHint(queryHint);
    }

    private void c(boolean z) {
        int i;
        if (this.G && !this.z && z) {
            i = 0;
            this.i.setVisibility(XZBDevice.Wait);
        } else {
            i = 8;
        }
        this.k.setVisibility(i);
    }

    private void j() {
        if (!TextUtils.isEmpty(this.d.getText())) {
            this.d.setText(com.umeng.a.d);
            this.d.requestFocus();
            setImeVisibility(true);
        } else if (!this.y) {
        } else {
            if (this.u == null || !this.u.a()) {
                clearFocus();
                a(true);
            }
        }
    }

    private void k() {
        a(false);
        this.d.requestFocus();
        setImeVisibility(true);
        if (this.x != null) {
            this.x.onClick(this);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        h();
    }

    public final void b() {
        CharSequence charSequence = com.umeng.a.d;
        this.d.setText(charSequence);
        this.d.setSelection(this.d.length());
        this.I = charSequence;
        clearFocus();
        a(true);
        this.d.setImeOptions(this.K);
        this.J = false;
    }

    public final void a() {
        if (!this.J) {
            this.J = true;
            this.K = this.d.getImeOptions();
            this.d.setImeOptions(this.K | 33554432);
            this.d.setText(com.umeng.a.d);
            setIconified(false);
        }
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.z;
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            a(savedState.a);
            requestLayout();
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    private boolean a(int i) {
        if (this.w != null && this.w.b()) {
            return false;
        }
        Cursor cursor = this.A.getCursor();
        if (cursor != null && cursor.moveToPosition(i)) {
            Intent a = a(cursor);
            if (a != null) {
                try {
                    getContext().startActivity(a);
                } catch (RuntimeException e) {
                    new StringBuilder("Failed launch activity: ").append(a);
                }
            }
        }
        setImeVisibility(false);
        this.d.dismissDropDown();
        return true;
    }

    void setQuery(CharSequence charSequence) {
        this.d.setText(charSequence);
        this.d.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    private void a(String str) {
        getContext().startActivity(a("android.intent.action.SEARCH", null, null, str));
    }

    private Intent a(String str, Uri uri, String str2, String str3) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.I);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        if (this.M != null) {
            intent.putExtra("app_data", this.M);
        }
        if (c) {
            intent.setComponent(this.L.getSearchActivity());
        }
        return intent;
    }

    private Intent a(Cursor cursor) {
        try {
            String str;
            String a = cg.a(cursor, "suggest_intent_action");
            if (a == null && VERSION.SDK_INT >= 8) {
                a = this.L.getSuggestIntentAction();
            }
            if (a == null) {
                str = "android.intent.action.SEARCH";
            } else {
                str = a;
            }
            a = cg.a(cursor, "suggest_intent_data");
            if (c && a == null) {
                a = this.L.getSuggestIntentData();
            }
            if (a != null) {
                String a2 = cg.a(cursor, "suggest_intent_data_id");
                if (a2 != null) {
                    a = a + "/" + Uri.encode(a2);
                }
            }
            return a(str, a == null ? null : Uri.parse(a), cg.a(cursor, "suggest_intent_extra_data"), cg.a(cursor, "suggest_intent_query"));
        } catch (RuntimeException e) {
            try {
                int position = cursor.getPosition();
            } catch (RuntimeException e2) {
                position = -1;
            }
            new StringBuilder("Search suggestions cursor at row ").append(position).append(" returned exception.");
            return null;
        }
    }

    private void l() {
        a aVar = a;
        SearchAutoComplete searchAutoComplete = this.d;
        if (aVar.a != null) {
            try {
                aVar.a.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception e) {
            }
        }
        aVar = a;
        searchAutoComplete = this.d;
        if (aVar.b != null) {
            try {
                aVar.b.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception e2) {
            }
        }
    }

    static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    protected void onMeasure(int i, int i2) {
        if (this.z) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID:
                size = this.F > 0 ? Math.min(this.F, size) : Math.min(getPreferredWidth(), size);
                break;
            case SpdyAgent.ACCS_TEST_SERVER:
                size = this.F > 0 ? this.F : getPreferredWidth();
                break;
            case 1073741824:
                if (this.F > 0) {
                    size = Math.min(this.F, size);
                }
                break;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), i2);
    }

    final void d() {
        a(this.z);
        h();
        if (this.d.hasFocus()) {
            l();
        }
    }

    static /* synthetic */ void a(SearchView searchView) {
        int[] iArr = searchView.d.hasFocus() ? FOCUSED_STATE_SET : EMPTY_STATE_SET;
        Drawable background = searchView.f.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        background = searchView.g.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        searchView.invalidate();
    }

    static /* synthetic */ void d(SearchView searchView) {
        if (searchView.l.getWidth() > 1) {
            int dimensionPixelSize;
            int i;
            Resources resources = searchView.getContext().getResources();
            int paddingLeft = searchView.f.getPaddingLeft();
            Rect rect = new Rect();
            boolean a = cw.a(searchView);
            if (searchView.y) {
                dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width);
            } else {
                dimensionPixelSize = 0;
            }
            searchView.d.getDropDownBackground().getPadding(rect);
            if (a) {
                i = -rect.left;
            } else {
                i = paddingLeft - (rect.left + dimensionPixelSize);
            }
            searchView.d.setDropDownHorizontalOffset(i);
            searchView.d.setDropDownWidth((dimensionPixelSize + ((searchView.l.getWidth() + rect.left) + rect.right)) - paddingLeft);
        }
    }

    static /* synthetic */ void j(SearchView searchView) {
        CharSequence text = searchView.d.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            if (searchView.t != null) {
                c cVar = searchView.t;
                text.toString();
                if (cVar.a()) {
                    return;
                }
            }
            if (searchView.L != null) {
                searchView.a(text.toString());
            }
            searchView.setImeVisibility(false);
            searchView.d.dismissDropDown();
        }
    }

    static /* synthetic */ boolean b(SearchView searchView, int i) {
        if (searchView.w != null && searchView.w.a()) {
            return false;
        }
        CharSequence text = searchView.d.getText();
        Cursor cursor = searchView.A.getCursor();
        if (cursor != null) {
            if (cursor.moveToPosition(i)) {
                CharSequence convertToString = searchView.A.convertToString(cursor);
                if (convertToString != null) {
                    searchView.setQuery(convertToString);
                } else {
                    searchView.setQuery(text);
                }
            } else {
                searchView.setQuery(text);
            }
        }
        return true;
    }
}
