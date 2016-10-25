package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public final class InMobiStrandAdapter extends BaseAdapter {
    private static final String TAG;
    private boolean mIsInitialized;
    private Handler mListViewHandler;
    private NativeStrandAdListener mListener;
    private ad mNativeStrandController;
    private Adapter mOriginalAdapter;
    private WeakHashMap<View, Integer> mViewPositionMap;
    private ap mVisibilityTracker;

    class AnonymousClass_10 implements OnScrollListener {
        final /* synthetic */ ListView a;
        final /* synthetic */ int b;

        AnonymousClass_10(ListView listView, int i) {
            this.a = listView;
            this.b = i;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 0) {
                this.a.setOnScrollListener(null);
                new Handler().post(new Runnable() {
                    public void run() {
                        AnonymousClass_10.this.setSelection(AnonymousClass_10.this.b);
                    }
                });
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }
    }

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ ListView a;
        final /* synthetic */ int b;

        AnonymousClass_2(ListView listView, int i) {
            this.a = listView;
            this.b = i;
        }

        public void run() {
            this.a.smoothScrollToPosition(this.b);
        }
    }

    public static interface NativeStrandAdListener {
        void onAdLoadSucceeded(int i);

        void onAdRemoved(int i);
    }

    class AnonymousClass_7 implements OnItemClickListener {
        final /* synthetic */ OnItemClickListener a;

        AnonymousClass_7(OnItemClickListener onItemClickListener) {
            this.a = onItemClickListener;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (!InMobiStrandAdapter.this.mIsInitialized) {
                this.a.onItemClick(adapterView, view, i, j);
            } else if (!InMobiStrandAdapter.this.mNativeStrandController.b(i)) {
                this.a.onItemClick(adapterView, view, InMobiStrandAdapter.this.mNativeStrandController.e(i), j);
            }
        }
    }

    class AnonymousClass_8 implements OnItemLongClickListener {
        final /* synthetic */ OnItemLongClickListener a;

        AnonymousClass_8(OnItemLongClickListener onItemLongClickListener) {
            this.a = onItemLongClickListener;
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (!InMobiStrandAdapter.this.mIsInitialized) {
                return this.a.onItemLongClick(adapterView, view, i, j);
            }
            if (!InMobiStrandAdapter.this.isStrand(i)) {
                if (!this.a.onItemLongClick(adapterView, view, InMobiStrandAdapter.this.mNativeStrandController.e(i), j)) {
                    return false;
                }
            }
            return true;
        }
    }

    class AnonymousClass_9 implements OnItemSelectedListener {
        final /* synthetic */ OnItemSelectedListener a;

        AnonymousClass_9(OnItemSelectedListener onItemSelectedListener) {
            this.a = onItemSelectedListener;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (!InMobiStrandAdapter.this.mIsInitialized) {
                this.a.onItemSelected(adapterView, view, i, j);
            } else if (!InMobiStrandAdapter.this.isStrand(i)) {
                this.a.onItemSelected(adapterView, view, InMobiStrandAdapter.this.mNativeStrandController.e(i), j);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            this.a.onNothingSelected(adapterView);
        }
    }

    static {
        TAG = InMobiStrandAdapter.class.getSimpleName();
    }

    public InMobiStrandAdapter(Context context, long j, Adapter adapter, InMobiClientPositioning inMobiClientPositioning) {
        this.mIsInitialized = false;
        if (adapter == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Original Adapter is null!, InMobiStrandAdapter could not be initialized");
            throw new IllegalArgumentException("Invalid original adapter:null");
        }
        if (context == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Context is null, InMobiStrandAdapter could not be initialized");
            this.mIsInitialized = false;
        } else if (!(context instanceof Activity)) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Context is not Activity, InMobiStrandAdapter could not be initialized");
            this.mIsInitialized = false;
        } else if (inMobiClientPositioning == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "ClientPositioning is null, InMobiStrandAdapter could not be initialized");
            this.mIsInitialized = false;
        } else if (a.a()) {
            this.mIsInitialized = true;
        } else {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobi SDK is not initialized! InMobiStrandAdapter could not be initialized");
            this.mIsInitialized = false;
        }
        if (this.mIsInitialized) {
            initWithPlacer(context, j, adapter, inMobiClientPositioning);
        } else {
            initWithoutPlacer(adapter);
        }
    }

    private void initWithPlacer(Context context, long j, Adapter adapter, InMobiClientPositioning inMobiClientPositioning) {
        this.mNativeStrandController = new ad(context, j, inMobiClientPositioning);
        this.mOriginalAdapter = adapter;
        this.mOriginalAdapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                InMobiStrandAdapter.this.mNativeStrandController.c(InMobiStrandAdapter.this.mOriginalAdapter.getCount());
                InMobiStrandAdapter.this.notifyDataSetChanged();
            }

            public void onInvalidated() {
                InMobiStrandAdapter.this.notifyDataSetInvalidated();
            }
        });
        this.mListViewHandler = new Handler();
        this.mVisibilityTracker = new m(ap.a, (Activity) context);
        this.mVisibilityTracker.a(new c() {
            public void a(List<View> list, List<View> list2) {
                InMobiStrandAdapter.this.handleVisibilityChange(list);
            }
        });
        this.mViewPositionMap = new WeakHashMap();
        this.mOriginalAdapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                InMobiStrandAdapter.this.mNativeStrandController.c(InMobiStrandAdapter.this.mOriginalAdapter.getCount());
                InMobiStrandAdapter.this.notifyDataSetChanged();
            }

            public void onInvalidated() {
                InMobiStrandAdapter.this.notifyDataSetInvalidated();
            }
        });
        this.mNativeStrandController.a(new NativeStrandAdListener() {
            public void onAdLoadSucceeded(int i) {
                InMobiStrandAdapter.this.handleAdLoaded(i);
            }

            public void onAdRemoved(int i) {
                InMobiStrandAdapter.this.handleAdRemoved(i);
            }
        });
    }

    private void initWithoutPlacer(Adapter adapter) {
        this.mOriginalAdapter = adapter;
        this.mListViewHandler = new Handler();
        this.mOriginalAdapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                InMobiStrandAdapter.this.notifyDataSetChanged();
            }

            public void onInvalidated() {
                InMobiStrandAdapter.this.notifyDataSetInvalidated();
            }
        });
    }

    public final void setListener(NativeStrandAdListener nativeStrandAdListener) {
        if (!this.mIsInitialized) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, ignoring InMobiStrandAdapter.setListener()");
        } else if (nativeStrandAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand.NativeStrandAdListener supplied as null.");
        } else {
            this.mListener = nativeStrandAdListener;
        }
    }

    public final void load() {
        if (this.mIsInitialized) {
            this.mVisibilityTracker.d();
            this.mNativeStrandController.a();
            return;
        }
        Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, ignoring InMobiStrandAdapter.load()");
    }

    public final void setExtras(Map<String, String> map) {
        if (this.mIsInitialized) {
            this.mNativeStrandController.a((Map) map);
        } else {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, ignoring InMobiStrandAdapter.setExtras()");
        }
    }

    public final void setKeywords(String str) {
        if (this.mIsInitialized) {
            this.mNativeStrandController.a(str);
        } else {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, ignoring InMobiStrandAdapter.setKeywords()");
        }
    }

    public final boolean isStrand(int i) {
        if (this.mIsInitialized) {
            return this.mNativeStrandController.b(i);
        }
        Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, ignoring InMobiStrandAdapter.isStrand()");
        return false;
    }

    public final void clearStrands() {
        if (this.mIsInitialized) {
            this.mVisibilityTracker.c();
            this.mNativeStrandController.d();
            return;
        }
        Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, ignoring InMobiStrandAdapter.clearStrands()");
    }

    public final void destroy() {
        if (this.mIsInitialized) {
            this.mNativeStrandController.e();
            this.mVisibilityTracker.e();
            return;
        }
        Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, ignoring InMobiStrandAdapter.destroy()");
    }

    public final int getCount() {
        return !this.mIsInitialized ? this.mOriginalAdapter.getCount() : this.mNativeStrandController.h(this.mOriginalAdapter.getCount());
    }

    public final Object getItem(int i) {
        if (!this.mIsInitialized) {
            return this.mOriginalAdapter.getItem(i);
        }
        Object a = this.mNativeStrandController.a(i);
        return a == null ? this.mOriginalAdapter.getItem(this.mNativeStrandController.e(i)) : a;
    }

    public final long getItemId(int i) {
        if (!this.mIsInitialized) {
            return this.mOriginalAdapter.getItemId(i);
        }
        Object a = this.mNativeStrandController.a(i);
        return a != null ? (long) ((System.identityHashCode(a) ^ -1) + 1) : this.mOriginalAdapter.getItemId(this.mNativeStrandController.e(i));
    }

    public final boolean hasStableIds() {
        return this.mOriginalAdapter.hasStableIds();
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.mIsInitialized) {
            return this.mOriginalAdapter.getView(i, view, viewGroup);
        }
        View a = this.mNativeStrandController.a(i, view, viewGroup);
        if (a == null) {
            a = this.mOriginalAdapter.getView(this.mNativeStrandController.e(i), view, viewGroup);
        }
        this.mViewPositionMap.put(a, Integer.valueOf(i));
        if (this.mVisibilityTracker.f()) {
            return a;
        }
        this.mVisibilityTracker.a(a, null, 0);
        return a;
    }

    public final int getItemViewType(int i) {
        if (!this.mIsInitialized) {
            return this.mOriginalAdapter.getItemViewType(i);
        }
        int d = this.mNativeStrandController.d(i);
        return d != 0 ? (d + this.mOriginalAdapter.getViewTypeCount()) - 1 : this.mOriginalAdapter.getItemViewType(this.mNativeStrandController.e(i));
    }

    public final int getViewTypeCount() {
        return !this.mIsInitialized ? this.mOriginalAdapter.getViewTypeCount() : this.mOriginalAdapter.getViewTypeCount() + this.mNativeStrandController.c();
    }

    public final boolean isEmpty() {
        if (this.mIsInitialized) {
            return this.mOriginalAdapter.isEmpty() && this.mNativeStrandController.h(0) == 0;
        } else {
            return this.mOriginalAdapter.isEmpty();
        }
    }

    public final int getOriginalPosition(int i) {
        if (this.mIsInitialized) {
            return this.mNativeStrandController.e(i);
        }
        Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, Initialize InMobiSdk before creating InMobiStrandAdapter");
        return i;
    }

    public final int getAdjustedPosition(int i) {
        if (this.mIsInitialized) {
            return this.mNativeStrandController.f(i);
        }
        Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, Initialize InMobiSdk before creating InMobiStrandAdapter");
        return i;
    }

    public final void insertItem(int i) {
        if (this.mIsInitialized) {
            this.mNativeStrandController.i(i);
        } else {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, Initialize InMobiSdk before creating InMobiStrandAdapter ignoring InMobiStrandAdapter.insertItem()");
        }
    }

    public final void removeItem(int i) {
        if (this.mIsInitialized) {
            this.mNativeStrandController.j(i);
        } else {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, Initialize InMobiSdk before creating InMobiStrandAdapter,ignoring InMobiStrandAdapter.removeItem()");
        }
    }

    public final void setOnClickListener(ListView listView, OnItemClickListener onItemClickListener) {
        if (!this.mIsInitialized) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, Initialize InMobiSdk before creating InMobiStrandAdapter");
        }
        if (listView == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "ListView is null cannot set onClickListener");
        } else if (onItemClickListener == null) {
            listView.setOnItemClickListener(null);
        } else {
            listView.setOnItemClickListener(new AnonymousClass_7(onItemClickListener));
        }
    }

    public final void setOnItemLongClickListener(ListView listView, OnItemLongClickListener onItemLongClickListener) {
        if (!this.mIsInitialized) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, Initialize InMobiSdk before creating InMobiStrandAdapter");
        }
        if (listView == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "ListView is null cannot set OnItemLongClickListener");
        } else if (onItemLongClickListener == null) {
            listView.setOnItemLongClickListener(null);
        } else {
            listView.setOnItemLongClickListener(new AnonymousClass_8(onItemLongClickListener));
        }
    }

    public final void setOnItemSelectedListener(ListView listView, OnItemSelectedListener onItemSelectedListener) {
        if (!this.mIsInitialized) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, Initialize InMobiSdk before creating InMobiStrandAdapter");
        }
        if (listView == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "ListView is null cannot set OnItemSelectedListener");
        } else if (onItemSelectedListener == null) {
            listView.setOnItemSelectedListener(null);
        } else {
            listView.setOnItemSelectedListener(new AnonymousClass_9(onItemSelectedListener));
        }
    }

    public final void setSelection(ListView listView, int i) {
        if (listView == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "ListView is null cannot set selection");
        } else if (this.mIsInitialized) {
            listView.setSelection(this.mNativeStrandController.f(i));
        } else {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized!, Initialize InMobiSdk before creating InMobiStrandAdapter");
            listView.setSelection(i);
        }
    }

    public final void smoothScrollToPosition(ListView listView, int i) {
        if (listView == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, new StringBuilder("ListView is null cannot smoothScrollToPosition:").append(i).toString());
            return;
        }
        if (this.mIsInitialized) {
            i = this.mNativeStrandController.f(i);
        }
        listView.setOnScrollListener(new AnonymousClass_10(listView, i));
        this.mListViewHandler.post(new AnonymousClass_2(listView, i));
    }

    public final void refreshAds(ListView listView) {
        if (listView == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "ListView is null cannot refresh ads");
        } else if (this.mIsInitialized) {
            View childAt = listView.getChildAt(0);
            int top = childAt == null ? 0 : childAt.getTop();
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            int max = Math.max(firstVisiblePosition - 1, 0);
            while (this.mNativeStrandController.b(max) && max > 0) {
                max--;
            }
            int lastVisiblePosition = listView.getLastVisiblePosition();
            while (this.mNativeStrandController.b(lastVisiblePosition) && lastVisiblePosition < getCount() - 1) {
                lastVisiblePosition++;
            }
            max = this.mNativeStrandController.e(max);
            this.mNativeStrandController.b(this.mNativeStrandController.g(lastVisiblePosition + 1), this.mNativeStrandController.g(getCount()));
            int b = this.mNativeStrandController.b(0, max);
            if (b > 0) {
                listView.setSelectionFromTop(firstVisiblePosition - b, top);
            }
            load();
        } else {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiStrandAdapter is not initialized! ignoring InMobiStrandAdapter.refreshAds()");
        }
    }

    private void handleVisibilityChange(List<View> list) {
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        for (View view : list) {
            Integer num = (Integer) this.mViewPositionMap.get(view);
            if (num != null) {
                i = Math.min(num.intValue(), i);
                i2 = Math.max(num.intValue(), i2);
            }
        }
        this.mNativeStrandController.a(i, i2 + 1);
    }

    private void handleAdLoaded(int i) {
        notifyDataSetChanged();
        Map hashMap = new HashMap();
        hashMap.put("position", Integer.valueOf(i));
        com.inmobi.commons.core.c.a.a().a("ads", "StrandPlaced", hashMap);
        if (this.mListener != null) {
            this.mListener.onAdLoadSucceeded(i);
        }
    }

    private void handleAdRemoved(int i) {
        notifyDataSetChanged();
        Map hashMap = new HashMap();
        hashMap.put("position", Integer.valueOf(i));
        com.inmobi.commons.core.c.a.a().a("ads", "StrandRemoved", hashMap);
        if (this.mListener != null) {
            this.mListener.onAdRemoved(i);
        }
    }
}
