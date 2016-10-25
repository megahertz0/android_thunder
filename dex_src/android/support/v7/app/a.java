package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.appcompat.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import org.android.spdy.SpdyAgent;

// compiled from: AlertController.java
public final class a {
    TextView A;
    public TextView B;
    public View C;
    public ListAdapter D;
    public int E;
    int F;
    int G;
    public int H;
    public int I;
    public int J;
    public int K;
    int L;
    Handler M;
    final OnClickListener N;
    public final Context a;
    final aa b;
    final Window c;
    CharSequence d;
    public CharSequence e;
    public ListView f;
    public View g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    Button n;
    CharSequence o;
    Message p;
    Button q;
    CharSequence r;
    Message s;
    Button t;
    CharSequence u;
    Message v;
    NestedScrollView w;
    public int x;
    public Drawable y;
    public ImageView z;

    // compiled from: AlertController.java
    public static class a {
        public int A;
        public boolean B;
        public boolean[] C;
        public boolean D;
        public boolean E;
        public int F;
        public OnMultiChoiceClickListener G;
        public Cursor H;
        public String I;
        public String J;
        public OnItemSelectedListener K;
        public boolean L;
        public final Context a;
        public final LayoutInflater b;
        public int c;
        public Drawable d;
        public int e;
        public CharSequence f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public DialogInterface.OnClickListener j;
        public CharSequence k;
        public DialogInterface.OnClickListener l;
        public CharSequence m;
        public DialogInterface.OnClickListener n;
        public boolean o;
        public OnCancelListener p;
        public OnDismissListener q;
        public OnKeyListener r;
        public CharSequence[] s;
        public ListAdapter t;
        public DialogInterface.OnClickListener u;
        public int v;
        public View w;
        public int x;
        public int y;
        public int z;

        public a(Context context) {
            this.c = 0;
            this.e = 0;
            this.B = false;
            this.F = -1;
            this.L = true;
            this.a = context;
            this.o = true;
            this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        }
    }

    // compiled from: AlertController.java
    private static final class b extends Handler {
        private WeakReference<DialogInterface> a;

        public b(DialogInterface dialogInterface) {
            this.a = new WeakReference(dialogInterface);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case AppbarJsBridge.Code_Java_Exception:
                case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                case SniffingResourceGroup.PAGETYPE_NONE:
                    ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.a.get(), message.what);
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    ((DialogInterface) message.obj).dismiss();
                default:
                    break;
            }
        }
    }

    // compiled from: AlertController.java
    private static class c extends ArrayAdapter<CharSequence> {
        public c(Context context, int i, CharSequence[] charSequenceArr) {
            super(context, i, 16908308, charSequenceArr);
        }

        public final boolean hasStableIds() {
            return true;
        }

        public final long getItemId(int i) {
            return (long) i;
        }
    }

    public a(Context context, aa aaVar, Window window) {
        this.m = false;
        this.x = 0;
        this.E = -1;
        this.L = 0;
        this.N = new b(this);
        this.a = context;
        this.b = aaVar;
        this.c = window;
        this.M = new b(aaVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.AlertDialog, R.attr.alertDialogStyle, 0);
        this.F = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_android_layout, 0);
        this.G = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.H = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_listLayout, 0);
        this.I = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.J = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.K = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_listItemLayout, 0);
        obtainStyledAttributes.recycle();
        aaVar.a();
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public final void a(CharSequence charSequence) {
        this.d = charSequence;
        if (this.A != null) {
            this.A.setText(charSequence);
        }
    }

    public final void a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        if (onClickListener != null) {
            message = this.M.obtainMessage(i, onClickListener);
        }
        switch (i) {
            case AppbarJsBridge.Code_Java_Exception:
                this.u = charSequence;
                this.v = message;
            case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                this.r = charSequence;
                this.s = message;
            case SniffingResourceGroup.PAGETYPE_NONE:
                this.o = charSequence;
                this.p = message;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public final void a(int i) {
        this.y = null;
        this.x = i;
        if (this.z == null) {
            return;
        }
        if (i != 0) {
            this.z.setVisibility(0);
            this.z.setImageResource(this.x);
            return;
        }
        this.z.setVisibility(XZBDevice.Wait);
    }

    static ViewGroup a(View view, View view2) {
        View inflate;
        if (view == null) {
            if (view2 instanceof ViewStub) {
                inflate = ((ViewStub) view2).inflate();
            } else {
                inflate = view2;
            }
            return (ViewGroup) inflate;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            inflate = ((ViewStub) view).inflate();
        } else {
            inflate = view;
        }
        return (ViewGroup) inflate;
    }

    static /* synthetic */ void a(View view, View view2, View view3) {
        int i = 0;
        if (view2 != null) {
            view2.setVisibility(ViewCompat.canScrollVertically(view, -1) ? 0 : 4);
        }
        if (view3 != null) {
            if (!ViewCompat.canScrollVertically(view, 1)) {
                i = 4;
            }
            view3.setVisibility(i);
        }
    }
}
