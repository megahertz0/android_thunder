package com.uc.addon.sdk.remote.protocol;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Filter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.uc.addon.sdk.remote.protocol.RemoteView.Action;
import com.uc.addon.sdk.remote.protocol.RemoteView.ActionInfo;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import org.android.spdy.SpdyAgent;

public class RemoteView implements Parcelable, Filter {
    public static final Creator CREATOR;
    protected Handler a;
    protected ArrayList b;
    private String c;
    private int d;
    private int e;

    public abstract class Action implements Parcelable {
        protected int a;
        public ActionInfo actionInfo;

        public Action(RemoteView remoteView) {
            a();
        }

        protected abstract void a();

        public abstract void apply(View view, ViewGroup viewGroup);

        public int describeContents() {
            return 0;
        }
    }

    public class ActionInfo {
        int a;
        int b;
        String c;
        boolean d;

        protected ActionInfo() {
            this.a = 0;
            this.b = 0;
            this.d = false;
        }
    }

    public class ActionReflection extends Action {
        private int b;
        private String c;
        private int d;
        private Object e;

        public ActionReflection(RemoteView remoteView, int i, String str, int i2, Object obj) {
            super(remoteView);
            this.b = i;
            this.c = str;
            this.d = i2;
            this.e = obj;
        }

        public ActionReflection(RemoteView remoteView, Parcel parcel) {
            super(remoteView);
            this.b = parcel.readInt();
            this.c = parcel.readString();
            this.d = parcel.readInt();
            this.e = RemoteView.a(parcel, this.d);
        }

        protected final void a() {
            this.a = 6;
        }

        public void apply(View view, ViewGroup viewGroup) {
            View findViewById = view.findViewById(this.b);
            if (findViewById == null) {
                new StringBuilder("ActionReflection findViewById error viewId = 0x").append(Integer.toHexString(this.b));
                return;
            }
            Class a = RemoteView.a(this.d);
            if (a == null) {
                new StringBuilder("ActionReflection bad type: ").append(this.d);
                return;
            }
            Class cls = findViewById.getClass();
            Method method = null;
            try {
                method = cls.getMethod(this.c, new Class[]{RemoteView.a(this.d)});
            } catch (NoSuchMethodException e) {
                new StringBuilder("view: ").append(cls.getName()).append(" doesn't have method: ").append(this.c).append(SocializeConstants.OP_OPEN_PAREN).append(a.getName()).append(SocializeConstants.OP_CLOSE_PAREN);
            }
            if (method != null) {
                try {
                    method.invoke(findViewById, new Object[]{this.e});
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeString(this.c);
            parcel.writeInt(this.d);
            RemoteView.a(parcel, this.e, this.d, i);
        }
    }

    public class ActionReflectionWithoutParams extends Action {
        private int b;
        private String c;

        public ActionReflectionWithoutParams(RemoteView remoteView, int i, String str) {
            super(remoteView);
            this.b = i;
            this.c = str;
        }

        public ActionReflectionWithoutParams(RemoteView remoteView, Parcel parcel) {
            super(remoteView);
            this.b = parcel.readInt();
            this.c = parcel.readString();
        }

        protected final void a() {
            this.a = 7;
        }

        public void apply(View view, ViewGroup viewGroup) {
            View findViewById = view.findViewById(this.b);
            if (findViewById == null) {
                new StringBuilder("ReflectionActionWithoutParams findViewById error viewId = 0x").append(Integer.toHexString(this.b));
                return;
            }
            Class cls = findViewById.getClass();
            Method method = null;
            try {
                method = cls.getMethod(this.c, new Class[0]);
            } catch (NoSuchMethodException e) {
                new StringBuilder("view: ").append(cls.getName()).append(" doesn't have method: ").append(this.c).append("()");
            }
            if (method != null) {
                try {
                    method.invoke(findViewById, new Object[0]);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeString(this.c);
        }
    }

    public class ActionSetBaseObjectValue extends Action {
        private Object b;
        private int c;
        private int d;

        public ActionSetBaseObjectValue(Parcel parcel) {
            super(RemoteView.this);
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.b = RemoteView.a(parcel, this.c);
        }

        public ActionSetBaseObjectValue(Object obj, int i, int i2) {
            super(RemoteView.this);
            this.b = obj;
            this.c = i;
            this.d = i2;
        }

        protected final void a() {
            this.a = 8;
        }

        public void apply(View view, ViewGroup viewGroup) {
            RemoteView.this.a(this.d, this.b);
        }

        public Object getValue() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            RemoteView.a(parcel, this.b, this.c, i);
        }
    }

    public class ActionSetDrawableParameters extends Action {
        private int b;
        private boolean c;
        private int d;
        private int e;
        private Mode f;
        private int g;

        public ActionSetDrawableParameters(RemoteView remoteView, int i, boolean z, int i2, int i3, Mode mode, int i4) {
            super(remoteView);
            this.b = i;
            this.c = z;
            this.d = i2;
            this.e = i3;
            this.f = mode;
            this.g = i4;
        }

        public ActionSetDrawableParameters(RemoteView remoteView, Parcel parcel) {
            Object obj = 1;
            super(remoteView);
            this.b = parcel.readInt();
            this.c = parcel.readInt() != 0;
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            if (parcel.readInt() == 0) {
                obj = null;
            }
            if (obj != null) {
                this.f = Mode.valueOf(parcel.readString());
            } else {
                this.f = null;
            }
            this.g = parcel.readInt();
        }

        protected final void a() {
            this.a = 5;
        }

        public void apply(View view, ViewGroup viewGroup) {
            View findViewById = view.findViewById(this.b);
            if (findViewById == null) {
                new StringBuilder("ActionSetDrawableParameters findViewById error viewId = 0x").append(Integer.toHexString(this.b));
                return;
            }
            Drawable background = this.c ? findViewById.getBackground() : findViewById instanceof ImageView ? ((ImageView) findViewById).getDrawable() : null;
            if (background != null) {
                if (this.d != -1) {
                    background.setAlpha(this.d);
                }
                if (!(this.e == -1 || this.f == null)) {
                    background.setColorFilter(this.e, this.f);
                }
                if (this.g != -1) {
                    background.setLevel(this.g);
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c ? 1 : 0);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            if (this.f != null) {
                parcel.writeInt(1);
                parcel.writeString(this.f.toString());
            } else {
                parcel.writeInt(0);
            }
            parcel.writeInt(this.g);
        }
    }

    public class ActionSetEmptyView extends Action {
        private int b;
        private int c;

        ActionSetEmptyView(RemoteView remoteView, Parcel parcel) {
            super(remoteView);
            this.b = parcel.readInt();
            this.c = parcel.readInt();
        }

        protected final void a() {
            this.a = 4;
        }

        public void apply(View view, ViewGroup viewGroup) {
            View findViewById = view.findViewById(this.b);
            if (findViewById instanceof AdapterView) {
                AdapterView adapterView = (AdapterView) findViewById;
                View findViewById2 = view.findViewById(this.c);
                if (findViewById2 == null) {
                    new StringBuilder("ActionSetOnClick findViewById error viewId = 0x").append(Integer.toHexString(this.b));
                } else {
                    adapterView.setEmptyView(findViewById2);
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
        }
    }

    public class ActionSetOnClick extends Action {
        IOnClickListener b;
        private int c;

        public ActionSetOnClick(RemoteView remoteView, int i, IOnClickListener iOnClickListener) {
            super(remoteView);
            this.c = i;
            this.b = iOnClickListener;
            ((OnClickListener) this.b).a(remoteView.a);
        }

        public ActionSetOnClick(RemoteView remoteView, Parcel parcel) {
            super(remoteView);
            this.c = parcel.readInt();
            this.b = OnClickListener.asInterface(parcel.readStrongBinder());
        }

        protected final void a() {
            this.a = 0;
        }

        public void apply(View view, ViewGroup viewGroup) {
            View findViewById = view.findViewById(this.c);
            if (findViewById == null) {
                new StringBuilder("ActionSetOnClick findViewById error viewId = 0x").append(Integer.toHexString(this.c));
            } else if (findViewById != null && this.b != null) {
                findViewById.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        try {
                            com.uc.addon.sdk.remote.protocol.RemoteView.ActionSetOnClick.this.b.onClickRemote();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.c);
            parcel.writeStrongBinder((IBinder) this.b);
        }
    }

    public class ActionSetOnLongClick extends Action {
        IOnLongClickListener b;
        boolean c;
        private int d;

        public ActionSetOnLongClick(RemoteView remoteView, int i, boolean z, IOnLongClickListener iOnLongClickListener) {
            super(remoteView);
            this.c = true;
            this.d = i;
            this.b = iOnLongClickListener;
            this.c = z;
            ((OnLongClickListener) this.b).a(remoteView.a);
        }

        public ActionSetOnLongClick(RemoteView remoteView, Parcel parcel) {
            boolean z = true;
            super(remoteView);
            this.c = true;
            this.d = parcel.readInt();
            if (parcel.readInt() == 0) {
                z = false;
            }
            this.c = z;
            this.b = OnLongClickListener.asInterface(parcel.readStrongBinder());
        }

        protected final void a() {
            this.a = 1;
        }

        public void apply(View view, ViewGroup viewGroup) {
            View findViewById = view.findViewById(this.d);
            if (findViewById == null) {
                new StringBuilder("ActionSetOnLongClick findViewById error viewId = 0x").append(Integer.toHexString(this.d));
            } else if (findViewById != null && this.b != null) {
                findViewById.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        try {
                            com.uc.addon.sdk.remote.protocol.RemoteView.ActionSetOnLongClick.this.b.onLongClickRemote();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        return com.uc.addon.sdk.remote.protocol.RemoteView.ActionSetOnLongClick.this.c;
                    }
                });
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.d);
            parcel.writeInt(this.c ? 1 : 0);
            parcel.writeStrongBinder((IBinder) this.b);
        }
    }

    public class ActionViewGroup extends Action {
        private int b;
        private int c;
        private int d;

        public ActionViewGroup(int i, int i2, int i3) {
            super(RemoteView.this);
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }

        public ActionViewGroup(Parcel parcel) {
            super(RemoteView.this);
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
        }

        private boolean a(View view) {
            for (int indexOf = RemoteView.this.b.indexOf(this) + 1; indexOf < RemoteView.this.b.size(); indexOf++) {
                Action action = (Action) RemoteView.this.b.get(indexOf);
                if (action instanceof com.uc.addon.sdk.remote.protocol.RemoteView.ActionViewGroup) {
                    com.uc.addon.sdk.remote.protocol.RemoteView.ActionViewGroup actionViewGroup = (com.uc.addon.sdk.remote.protocol.RemoteView.ActionViewGroup) action;
                    if (actionViewGroup.d == 11 || actionViewGroup.d == 12) {
                        if (actionViewGroup.b == this.b) {
                            return true;
                        }
                        View findViewById = view.findViewById(actionViewGroup.b);
                        if (findViewById != null && findViewById.findViewById(this.b) != null) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        protected final void a() {
            this.a = 2;
        }

        public void apply(View view, ViewGroup viewGroup) {
            Context context = view.getContext();
            ViewGroup viewGroup2 = (ViewGroup) view.findViewById(this.b);
            if (viewGroup2 == null) {
                new StringBuilder("ViewGroupAction findViewById error viewId = 0x").append(Integer.toHexString(this.b));
            } else if (this.d == 10) {
                if (!a(view)) {
                    Context a = RemoteView.this.a(context);
                    if (a != null) {
                        LayoutInflater cloneInContext = ((LayoutInflater) a.getSystemService("layout_inflater")).cloneInContext(a);
                        cloneInContext.setFilter(RemoteView.this);
                        viewGroup2.addView(cloneInContext.inflate(this.c, null, false));
                    }
                }
            } else if (this.d == 11) {
                if (this.c > 0) {
                    View findViewById = viewGroup2.findViewById(this.c);
                    if (findViewById != null) {
                        viewGroup2.removeView(findViewById);
                    }
                }
            } else if (this.d == 12) {
                viewGroup2.removeAllViews();
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
        }
    }

    public class BaseObjectParameter {
        protected BaseObjectParameter() {
        }
    }

    public class BaseType {
        protected BaseType() {
        }
    }

    static {
        CREATOR = new Creator() {
            public final RemoteView createFromParcel(Parcel parcel) {
                return new RemoteView(parcel);
            }

            public final RemoteView[] newArray(int i) {
                return new RemoteView[i];
            }
        };
    }

    public RemoteView(Parcel parcel) {
        this.e = 85;
        this.b = new ArrayList();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.b = new ArrayList(readInt);
            for (int i = 0; i < readInt; i++) {
                int readInt2 = parcel.readInt();
                switch (readInt2) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        this.b.add(new ActionSetOnClick(this, parcel));
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        this.b.add(new ActionSetOnLongClick(this, parcel));
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        this.b.add(new ActionViewGroup(parcel));
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        this.b.add(new ActionSetEmptyView(this, parcel));
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        this.b.add(new ActionSetDrawableParameters(this, parcel));
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        this.b.add(new ActionReflection(this, parcel));
                        break;
                    case R.styleable.Toolbar_contentInsetLeft:
                        this.b.add(new ActionReflectionWithoutParams(this, parcel));
                        break;
                    case XZBDevice.Wait:
                        this.b.add(new ActionSetBaseObjectValue(parcel));
                        break;
                    default:
                        new StringBuilder("Tag ").append(readInt2).append(" not found");
                        break;
                }
            }
        }
    }

    public RemoteView(String str, int i, Handler handler) {
        this.e = 85;
        this.b = new ArrayList();
        this.a = handler;
        this.c = str;
        this.d = i;
    }

    protected static Class a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return Boolean.TYPE;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return Byte.TYPE;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return Short.TYPE;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return Integer.TYPE;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return Long.TYPE;
            case R.styleable.Toolbar_contentInsetEnd:
                return Float.TYPE;
            case R.styleable.Toolbar_contentInsetLeft:
                return Double.TYPE;
            case XZBDevice.Wait:
                return Character.TYPE;
            case XZBDevice.Pause:
                return String.class;
            case XZBDevice.Stop:
                return CharSequence.class;
            case XZBDevice.Success:
                return Uri.class;
            case XZBDevice.Fail:
                return Bitmap.class;
            case XZBDevice.Upload:
                return Bundle.class;
            case XZBDevice.Predownload:
                return Intent.class;
            default:
                return null;
        }
    }

    protected static Object a(Parcel parcel, int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return Boolean.valueOf(parcel.readInt() != 0);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return Byte.valueOf(parcel.readByte());
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return Short.valueOf((short) parcel.readInt());
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return Integer.valueOf(parcel.readInt());
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return Long.valueOf(parcel.readLong());
            case R.styleable.Toolbar_contentInsetEnd:
                return Float.valueOf(parcel.readFloat());
            case R.styleable.Toolbar_contentInsetLeft:
                return Double.valueOf(parcel.readDouble());
            case XZBDevice.Wait:
                return Character.valueOf((char) parcel.readInt());
            case XZBDevice.Pause:
                return parcel.readString();
            case XZBDevice.Stop:
                return TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            case XZBDevice.Success:
                return Uri.CREATOR.createFromParcel(parcel);
            case XZBDevice.Fail:
                return Bitmap.CREATOR.createFromParcel(parcel);
            case XZBDevice.Upload:
                return parcel.readBundle();
            case XZBDevice.Predownload:
                return Intent.CREATOR.createFromParcel(parcel);
            default:
                return null;
        }
    }

    static /* synthetic */ void a(Parcel parcel, Object obj, int i, int i2) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                parcel.writeInt(((Boolean) obj).booleanValue() ? 1 : 0);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                parcel.writeByte(((Byte) obj).byteValue());
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                parcel.writeInt(((Short) obj).shortValue());
            case XZBDevice.DOWNLOAD_LIST_ALL:
                parcel.writeInt(((Integer) obj).intValue());
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                parcel.writeLong(((Long) obj).longValue());
            case R.styleable.Toolbar_contentInsetEnd:
                parcel.writeFloat(((Float) obj).floatValue());
            case R.styleable.Toolbar_contentInsetLeft:
                parcel.writeDouble(((Double) obj).doubleValue());
            case XZBDevice.Wait:
                parcel.writeInt(((Character) obj).charValue());
            case XZBDevice.Pause:
                parcel.writeString((String) obj);
            case XZBDevice.Stop:
                TextUtils.writeToParcel((CharSequence) obj, parcel, i2);
            case XZBDevice.Success:
                ((Uri) obj).writeToParcel(parcel, i2);
            case XZBDevice.Fail:
                ((Bitmap) obj).writeToParcel(parcel, i2);
            case XZBDevice.Upload:
                parcel.writeBundle((Bundle) obj);
            case XZBDevice.Predownload:
                ((Intent) obj).writeToParcel(parcel, i2);
            default:
                break;
        }
    }

    private void a(View view, ViewGroup viewGroup) {
        if (view == null) {
            new StringBuilder("=========performApply error ------>").append(view);
            return;
        }
        try {
            if (this.b != null) {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    ((Action) it.next()).apply(view, viewGroup);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Action b(ActionInfo actionInfo) {
        if (actionInfo != null) {
            synchronized (this.b) {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    Action action = (Action) it.next();
                    if (action.actionInfo != null && actionInfo.c != null && actionInfo.c.equals(action.actionInfo.c) && actionInfo.b == action.actionInfo.b) {
                        return action;
                    }
                }
            }
        }
        return null;
    }

    private Action c(ActionInfo actionInfo) {
        if (actionInfo != null) {
            synchronized (this.b) {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    Action action = (Action) it.next();
                    if (action.actionInfo != null && actionInfo.c != null && actionInfo.c.equals(action.actionInfo.c) && actionInfo.b == action.actionInfo.b && actionInfo.a == action.actionInfo.a) {
                        return action;
                    }
                }
            }
        }
        return null;
    }

    protected final Context a(Context context) {
        String str = this.c;
        if (str == null || context == null) {
            return context;
        }
        try {
            return context.createPackageContext(str, XZBDevice.DOWNLOAD_LIST_ALL);
        } catch (NameNotFoundException e) {
            new StringBuilder("Package name ").append(str).append(" not found");
            return context;
        }
    }

    protected final Action a(ActionInfo actionInfo) {
        if (actionInfo != null) {
            synchronized (this.b) {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    Action action = (Action) it.next();
                    if (action.actionInfo != null && actionInfo.c != null && actionInfo.c.equals(action.actionInfo.c)) {
                        return action;
                    }
                }
            }
        }
        return null;
    }

    protected void a(int i, Object obj) {
        if (i == 4096 && obj != null) {
            this.e = ((Integer) obj).intValue();
        }
    }

    protected final void a(Action action) {
        if (action != null) {
            synchronized (this.b) {
                this.b.remove(action);
            }
        }
    }

    protected final void a(Action action, ActionInfo actionInfo) {
        if (action != null) {
            synchronized (this.b) {
                action.actionInfo = actionInfo;
                this.b.add(action);
            }
        }
    }

    public void addView(int i, int i2) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "addView";
        actionInfo.b = i;
        actionInfo.a = i2;
        a(c(actionInfo));
        a(new ActionViewGroup(i, i2, 10), actionInfo);
    }

    public View apply(Context context, ViewGroup viewGroup) {
        Context a = a(context);
        if (a == null) {
            return null;
        }
        try {
            LayoutInflater cloneInContext = ((LayoutInflater) a.getSystemService("layout_inflater")).cloneInContext(a);
            cloneInContext.setFilter(this);
            View inflate = cloneInContext.inflate(this.d, viewGroup, false);
        } catch (Exception e) {
            e.printStackTrace();
            inflate = null;
        }
        if (inflate == null) {
            return inflate;
        }
        a(inflate, viewGroup);
        return inflate;
    }

    public RemoteView clone() {
        RemoteView remoteView = new RemoteView(this.c, this.d, this.a);
        if (this.b != null) {
            remoteView.b = (ArrayList) this.b.clone();
        }
        return remoteView;
    }

    public int describeContents() {
        return 0;
    }

    public int getGravity() {
        return this.e;
    }

    public int getLayoutId() {
        return this.d;
    }

    public String getPackage() {
        return this.c;
    }

    public boolean onLoadClass(Class cls) {
        return true;
    }

    public void playSoundEffect(int i, int i2) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "playSoundEffect";
        actionInfo.b = i;
        actionInfo.d = true;
        a(a(actionInfo));
        a(new ActionReflection(this, i, "playSoundEffect", 4, Integer.valueOf(i2)), actionInfo);
    }

    public void reapply(Context context, View view) {
        a(context);
        a(view, (ViewGroup) view.getParent());
    }

    public void removeAllViews(int i) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "removeAllViews";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionViewGroup(i, 0, 12), actionInfo);
    }

    public void removeView(int i, int i2) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "removeView";
        actionInfo.b = i;
        actionInfo.a = i2;
        a(c(actionInfo));
        a(new ActionViewGroup(i, i2, 11), actionInfo);
    }

    public void setBackgroundColor(int i, int i2) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setBackgroundColor";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setBackgroundColor", 4, Integer.valueOf(i2)), actionInfo);
    }

    public void setBackgroundResource(int i, int i2) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setBackgroundResource";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setBackgroundResource", 4, Integer.valueOf(i2)), actionInfo);
    }

    public void setClickable(int i, boolean z) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setClickable";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setClickable", 1, Boolean.valueOf(z)), actionInfo);
    }

    public void setDrawableParameters(int i, boolean z, int i2, int i3, Mode mode, int i4) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setDrawableParameters";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionSetDrawableParameters(this, i, z, i2, i3, mode, i4), actionInfo);
    }

    public void setEnabled(int i, boolean z) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setEnabled";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setEnabled", 1, Boolean.valueOf(z)), actionInfo);
    }

    public void setFocusable(int i, boolean z) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setFocusable";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setFocusable", 1, Boolean.valueOf(z)), actionInfo);
    }

    public void setGravity(int i) {
        this.e = i;
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setGravity";
        a(a(actionInfo));
        a(new ActionSetBaseObjectValue(Integer.valueOf(i), 4, 4096), actionInfo);
    }

    public void setImageViewBitmap(int i, Bitmap bitmap) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setImageViewBitmap";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setImageBitmap", 12, bitmap), actionInfo);
    }

    public void setImageViewResource(int i, int i2) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setImageViewResource";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setImageResource", 4, Integer.valueOf(i2)), actionInfo);
    }

    public void setLongClickable(int i, boolean z) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setLongClickable";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setLongClickable", 1, Boolean.valueOf(z)), actionInfo);
    }

    public void setOnClickListener(int i, IOnClickListener iOnClickListener) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setOnClickListener";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionSetOnClick(this, i, iOnClickListener), actionInfo);
    }

    public void setOnLongClickListener(int i, boolean z, IOnLongClickListener iOnLongClickListener) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setOnLongClickListener";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionSetOnLongClick(this, i, z, iOnLongClickListener), actionInfo);
    }

    public void setPressed(int i, boolean z) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setPressed";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setPressed", 1, Boolean.valueOf(z)), actionInfo);
    }

    public void setProgressBar(int i, int i2, int i3, boolean z) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setIndeterminate";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setIndeterminate", 1, Boolean.valueOf(z)), actionInfo);
        if (!z) {
            actionInfo = new ActionInfo();
            actionInfo.c = "setMax";
            actionInfo.b = i;
            a(b(actionInfo));
            a(new ActionReflection(this, i, "setMax", 4, Integer.valueOf(i2)), actionInfo);
            actionInfo = new ActionInfo();
            actionInfo.c = "setProgress";
            actionInfo.b = i;
            a(b(actionInfo));
            a(new ActionReflection(this, i, "setProgress", 4, Integer.valueOf(i2)), actionInfo);
        }
    }

    public void setSoundEffectsEnabled(int i, boolean z) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setSoundEffectsEnabled";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setSoundEffectsEnabled", 1, Boolean.valueOf(z)), actionInfo);
    }

    public void setTextColor(int i, int i2) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setTextColor";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setTextColor", 4, Integer.valueOf(i2)), actionInfo);
    }

    public void setTextViewText(int i, CharSequence charSequence) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setTextViewText";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setText", 10, charSequence), actionInfo);
    }

    public void setViewVisibility(int i, int i2) {
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setViewVisibility";
        actionInfo.b = i;
        a(b(actionInfo));
        a(new ActionReflection(this, i, "setVisibility", 4, Integer.valueOf(i2)), actionInfo);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        int size = this.b != null ? this.b.size() : 0;
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            ((Action) this.b.get(i2)).writeToParcel(parcel, 0);
        }
        Iterator it = ((ArrayList) this.b.clone()).iterator();
        while (it.hasNext()) {
            Action action = (Action) it.next();
            if (action.actionInfo != null && action.actionInfo.d) {
                this.b.remove(action);
            }
        }
    }
}
