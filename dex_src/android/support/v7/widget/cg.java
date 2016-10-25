package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.appcompat.R;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;
import org.android.agoo.message.MessageService;

// compiled from: SuggestionsAdapter.java
final class cg extends ResourceCursorAdapter implements OnClickListener {
    int a;
    private final SearchManager b;
    private final SearchView c;
    private final SearchableInfo d;
    private final Context e;
    private final WeakHashMap<String, ConstantState> f;
    private final int g;
    private boolean h;
    private ColorStateList i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;

    // compiled from: SuggestionsAdapter.java
    private static final class a {
        public final TextView a;
        public final TextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;

        public a(View view) {
            this.a = (TextView) view.findViewById(16908308);
            this.b = (TextView) view.findViewById(16908309);
            this.c = (ImageView) view.findViewById(16908295);
            this.d = (ImageView) view.findViewById(16908296);
            this.e = (ImageView) view.findViewById(R.id.edit_query);
        }
    }

    public cg(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.h = false;
        this.a = 1;
        this.j = -1;
        this.k = -1;
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.o = -1;
        this.b = (SearchManager) this.mContext.getSystemService("search");
        this.c = searchView;
        this.d = searchableInfo;
        this.g = searchView.getSuggestionCommitIconResId();
        this.e = context;
        this.f = weakHashMap;
    }

    public final boolean hasStableIds() {
        return false;
    }

    public final Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String toString = charSequence == null ? com.umeng.a.d : charSequence.toString();
        if (this.c.getVisibility() != 0 || this.c.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor cursor;
            SearchableInfo searchableInfo = this.d;
            if (searchableInfo == null) {
                cursor = null;
            } else {
                String suggestAuthority = searchableInfo.getSuggestAuthority();
                if (suggestAuthority == null) {
                    cursor = null;
                } else {
                    String[] strArr;
                    Builder fragment = new Builder().scheme(ParamKey.CONTENT).authority(suggestAuthority).query(com.umeng.a.d).fragment(com.umeng.a.d);
                    String suggestPath = searchableInfo.getSuggestPath();
                    if (suggestPath != null) {
                        fragment.appendEncodedPath(suggestPath);
                    }
                    fragment.appendPath("search_suggest_query");
                    suggestPath = searchableInfo.getSuggestSelection();
                    if (suggestPath != null) {
                        strArr = new String[]{toString};
                    } else {
                        fragment.appendPath(toString);
                        strArr = null;
                    }
                    fragment.appendQueryParameter("limit", "50");
                    cursor = this.mContext.getContentResolver().query(fragment.build(), null, suggestPath, strArr, null);
                }
            }
            if (cursor != null) {
                cursor.getCount();
                return cursor;
            }
        } catch (RuntimeException e) {
        }
        return null;
    }

    public final void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        a(getCursor());
    }

    public final void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        a(getCursor());
    }

    private static void a(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null && !extras.getBoolean("in_progress")) {
        }
    }

    public final void changeCursor(Cursor cursor) {
        if (!this.h) {
            try {
                super.changeCursor(cursor);
                if (cursor != null) {
                    this.j = cursor.getColumnIndex("suggest_text_1");
                    this.k = cursor.getColumnIndex("suggest_text_2");
                    this.l = cursor.getColumnIndex("suggest_text_2_url");
                    this.m = cursor.getColumnIndex("suggest_icon_1");
                    this.n = cursor.getColumnIndex("suggest_icon_2");
                    this.o = cursor.getColumnIndex("suggest_flags");
                }
            } catch (Exception e) {
            }
        } else if (cursor != null) {
            cursor.close();
        }
    }

    public final View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View newView = super.newView(context, cursor, viewGroup);
        newView.setTag(new a(newView));
        ((ImageView) newView.findViewById(R.id.edit_query)).setImageResource(this.g);
        return newView;
    }

    public final void bindView(View view, Context context, Cursor cursor) {
        Drawable drawable;
        a aVar = (a) view.getTag();
        int i;
        if (this.o != -1) {
            i = cursor.getInt(this.o);
        } else {
            i = 0;
        }
        if (aVar.a != null) {
            a(aVar.a, a(cursor, this.j));
        }
        if (aVar.b != null) {
            CharSequence charSequence;
            CharSequence a = a(cursor, this.l);
            if (a != null) {
                if (this.i == null) {
                    TypedValue typedValue = new TypedValue();
                    this.mContext.getTheme().resolveAttribute(R.attr.textColorSearchUrl, typedValue, true);
                    this.i = this.mContext.getResources().getColorStateList(typedValue.resourceId);
                }
                CharSequence spannableString = new SpannableString(a);
                spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.i, null), 0, a.length(), com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCopyDrawable);
                charSequence = spannableString;
            } else {
                charSequence = a(cursor, this.k);
            }
            if (TextUtils.isEmpty(charSequence)) {
                if (aVar.a != null) {
                    aVar.a.setSingleLine(false);
                    aVar.a.setMaxLines(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                }
            } else if (aVar.a != null) {
                aVar.a.setSingleLine(true);
                aVar.a.setMaxLines(1);
            }
            a(aVar.b, charSequence);
        }
        if (aVar.c != null) {
            ImageView imageView = aVar.c;
            if (this.m == -1) {
                drawable = null;
            } else {
                drawable = a(cursor.getString(this.m));
                if (drawable == null) {
                    ComponentName searchActivity = this.d.getSearchActivity();
                    String flattenToShortString = searchActivity.flattenToShortString();
                    if (this.f.containsKey(flattenToShortString)) {
                        ConstantState constantState = (ConstantState) this.f.get(flattenToShortString);
                        if (constantState == null) {
                            drawable = null;
                        } else {
                            drawable = constantState.newDrawable(this.e.getResources());
                        }
                    } else {
                        Drawable a2 = a(searchActivity);
                        this.f.put(flattenToShortString, a2 == null ? null : a2.getConstantState());
                        drawable = a2;
                    }
                    if (drawable == null) {
                        drawable = this.mContext.getPackageManager().getDefaultActivityIcon();
                    }
                }
            }
            a(imageView, drawable, XZBDevice.DOWNLOAD_LIST_ALL);
        }
        if (aVar.d != null) {
            ImageView imageView2 = aVar.d;
            if (this.n == -1) {
                drawable = null;
            } else {
                drawable = a(cursor.getString(this.n));
            }
            a(imageView2, drawable, XZBDevice.Wait);
        }
        if (this.a == 2 || (this.a == 1 && (r7 & 1) != 0)) {
            aVar.e.setVisibility(0);
            aVar.e.setTag(aVar.a.getText());
            aVar.e.setOnClickListener(this);
            return;
        }
        aVar.e.setVisibility(XZBDevice.Wait);
    }

    public final void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.c.setQuery((CharSequence) tag);
        }
    }

    private static void a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(XZBDevice.Wait);
        } else {
            textView.setVisibility(0);
        }
    }

    private static void a(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    public final CharSequence convertToString(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        String a = a(cursor, "suggest_intent_query");
        if (a != null) {
            return a;
        }
        if (this.d.shouldRewriteQueryFromData()) {
            a = a(cursor, "suggest_intent_data");
            if (a != null) {
                return a;
            }
        }
        if (!this.d.shouldRewriteQueryFromText()) {
            return null;
        }
        a = a(cursor, "suggest_text_1");
        return a != null ? a : null;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e) {
            View newView = newView(this.mContext, this.mCursor, viewGroup);
            if (newView != null) {
                ((a) newView.getTag()).a.setText(e.toString());
            }
            return newView;
        }
    }

    private Drawable a(String str) {
        if (str == null || str.length() == 0 || MessageService.MSG_DB_READY_REPORT.equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String toString = new StringBuilder("android.resource://").append(this.e.getPackageName()).append("/").append(parseInt).toString();
            Drawable b = b(toString);
            if (b != null) {
                return b;
            }
            b = ContextCompat.getDrawable(this.e, parseInt);
            a(toString, b);
            return b;
        } catch (NumberFormatException e) {
            b = b(str);
            if (b != null) {
                return b;
            }
            b = a(Uri.parse(str));
            a(str, b);
            return b;
        } catch (NotFoundException e2) {
            return null;
        }
    }

    private Drawable a(Uri uri) {
        try {
            InputStream openInputStream;
            if ("android.resource".equals(uri.getScheme())) {
                return b(uri);
            }
            openInputStream = this.e.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                throw new FileNotFoundException(new StringBuilder("Failed to open ").append(uri).toString());
            }
            Drawable createFromStream = Drawable.createFromStream(openInputStream, null);
            try {
                openInputStream.close();
                return createFromStream;
            } catch (IOException e) {
                new StringBuilder("Error closing icon stream for ").append(uri);
                return createFromStream;
            }
        } catch (NotFoundException e2) {
            throw new FileNotFoundException(new StringBuilder("Resource does not exist: ").append(uri).toString());
        } catch (FileNotFoundException e3) {
            new StringBuilder("Icon not found: ").append(uri).append(", ").append(e3.getMessage());
            return null;
        } catch (Throwable th) {
            try {
                openInputStream.close();
            } catch (IOException e4) {
                new StringBuilder("Error closing icon stream for ").append(uri);
            }
        }
    }

    private Drawable b(String str) {
        ConstantState constantState = (ConstantState) this.f.get(str);
        return constantState == null ? null : constantState.newDrawable();
    }

    private void a(String str, Drawable drawable) {
        if (drawable != null) {
            this.f.put(str, drawable.getConstantState());
        }
    }

    private Drawable a(ComponentName componentName) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            new StringBuilder("Invalid icon resource ").append(iconResource).append(" for ").append(componentName.flattenToShortString());
            return null;
        } catch (NameNotFoundException e) {
            e.toString();
            return null;
        }
    }

    public static String a(Cursor cursor, String str) {
        return a(cursor, cursor.getColumnIndex(str));
    }

    private static String a(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e) {
            return null;
        }
    }

    private Drawable b(Uri uri) throws FileNotFoundException {
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException(new StringBuilder("No authority: ").append(uri).toString());
        }
        try {
            Resources resourcesForApplication = this.mContext.getPackageManager().getResourcesForApplication(authority);
            List pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException(new StringBuilder("No path: ").append(uri).toString());
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    size = Integer.parseInt((String) pathSegments.get(0));
                } catch (NumberFormatException e) {
                    throw new FileNotFoundException(new StringBuilder("Single path segment is not a resource ID: ").append(uri).toString());
                }
            } else if (size == 2) {
                size = resourcesForApplication.getIdentifier((String) pathSegments.get(1), (String) pathSegments.get(0), authority);
            } else {
                throw new FileNotFoundException(new StringBuilder("More than two path segments: ").append(uri).toString());
            }
            if (size != 0) {
                return resourcesForApplication.getDrawable(size);
            }
            throw new FileNotFoundException(new StringBuilder("No resource found for: ").append(uri).toString());
        } catch (NameNotFoundException e2) {
            throw new FileNotFoundException(new StringBuilder("No package found for authority: ").append(uri).toString());
        }
    }
}
