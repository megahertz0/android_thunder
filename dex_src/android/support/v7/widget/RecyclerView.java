package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.os.TraceCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.recyclerview.R;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.c;
import android.support.v7.widget.RecyclerView.e;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.RecyclerView.q;
import android.support.v7.widget.RecyclerView.r;
import android.support.v7.widget.RecyclerView.t;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.taobao.accs.data.Message;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements NestedScrollingChild, ScrollingView {
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    private static final boolean DEBUG = false;
    static final boolean DISPATCH_TEMP_DETACH = false;
    private static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    public static final int HORIZONTAL = 0;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    private static final int MAX_SCROLL_DURATION = 2000;
    private static final int[] NESTED_SCROLLING_ATTRS;
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    private static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    private static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    private static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int VERTICAL = 1;
    private static final Interpolator sQuinticInterpolator;
    private bh mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private j mActiveOnItemTouchListener;
    private a mAdapter;
    o mAdapterHelper;
    private boolean mAdapterUpdateDuringMeasure;
    private EdgeEffectCompat mBottomGlow;
    private d mChildDrawingOrderCallback;
    ac mChildHelper;
    private boolean mClipToPadding;
    private boolean mDataSetHasChangedAfterLayout;
    private int mEatRequestLayout;
    private int mEatenAccessibilityChangeFlags;
    private boolean mFirstLayoutComplete;
    private boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private boolean mIsAttached;
    e mItemAnimator;
    private a mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    private final ArrayList<g> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    h mLayout;
    private boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    private boolean mLayoutRequestEaten;
    private EdgeEffectCompat mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final o mObserver;
    private List<i> mOnChildAttachStateListeners;
    private final ArrayList<j> mOnItemTouchListeners;
    private SavedState mPendingSavedState;
    private final boolean mPostUpdatesOnAnimation;
    private boolean mPostedAnimatorRunner;
    final m mRecycler;
    private n mRecyclerListener;
    private EdgeEffectCompat mRightGlow;
    private final int[] mScrollConsumed;
    private float mScrollFactor;
    private k mScrollListener;
    private List<k> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private NestedScrollingChildHelper mScrollingChildHelper;
    final q mState;
    private final Rect mTempRect;
    private EdgeEffectCompat mTopGlow;
    private int mTouchSlop;
    private final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    private final s mViewFlinger;
    private final b mViewInfoProcessCallback;
    final cv mViewInfoStore;

    public static abstract class t {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS;
        public final View itemView;
        private int mFlags;
        private boolean mInChangeScrap;
        private int mIsRecyclableCount;
        long mItemId;
        int mItemViewType;
        int mOldPosition;
        RecyclerView mOwnerRecyclerView;
        List<Object> mPayloads;
        int mPosition;
        int mPreLayoutPosition;
        private m mScrapContainer;
        android.support.v7.widget.RecyclerView.t mShadowedHolder;
        android.support.v7.widget.RecyclerView.t mShadowingHolder;
        List<Object> mUnmodifiedPayloads;
        private int mWasImportantForAccessibilityBeforeHidden;

        static {
            FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        }

        public t(View view) {
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1;
            this.mItemViewType = -1;
            this.mPreLayoutPosition = -1;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.mPayloads = null;
            this.mUnmodifiedPayloads = null;
            this.mIsRecyclableCount = 0;
            this.mScrapContainer = null;
            this.mInChangeScrap = false;
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = view;
        }

        void flagRemovedAndOffsetPosition(int i, int i2, boolean z) {
            addFlags(FLAG_REMOVED);
            offsetPosition(i2, z);
            this.mPosition = i;
        }

        void offsetPosition(int i, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z) {
                this.mPreLayoutPosition += i;
            }
            this.mPosition += i;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).e = true;
            }
        }

        void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        boolean shouldIgnore() {
            return (this.mFlags & 128) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        @Deprecated
        public final int getPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        public final int getLayoutPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        public final int getAdapterPosition() {
            return this.mOwnerRecyclerView == null ? NO_POSITION : this.mOwnerRecyclerView.getAdapterPositionFor(this);
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        boolean isScrap() {
            return this.mScrapContainer != null ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        void unScrap() {
            this.mScrapContainer.b(this);
        }

        boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        void stopIgnoring() {
            this.mFlags &= -129;
        }

        void setScrapContainer(m mVar, boolean z) {
            this.mScrapContainer = mVar;
            this.mInChangeScrap = z;
        }

        boolean isInvalid() {
            return (this.mFlags & 4) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        boolean needsUpdate() {
            return (this.mFlags & 2) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        boolean isBound() {
            return (this.mFlags & 1) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        boolean isRemoved() {
            return (this.mFlags & 8) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        boolean hasAnyOfTheFlags(int i) {
            return (this.mFlags & i) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        boolean isTmpDetached() {
            return (this.mFlags & 256) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        boolean isAdapterPositionUnknown() {
            return ((this.mFlags & 512) != 0 || isInvalid()) ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        void setFlags(int i, int i2) {
            this.mFlags = (this.mFlags & (i2 ^ -1)) | (i & i2);
        }

        void addFlags(int i) {
            this.mFlags |= i;
        }

        void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(FLAG_ADAPTER_FULLUPDATE);
            } else if ((this.mFlags & 1024) == 0) {
                createPayloadsIfNeeded();
                this.mPayloads.add(obj);
            }
        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                this.mPayloads = new ArrayList();
                this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
            }
        }

        void clearPayload() {
            if (this.mPayloads != null) {
                this.mPayloads.clear();
            }
            this.mFlags &= -1025;
        }

        List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) == 0) {
                return (this.mPayloads == null || this.mPayloads.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
            } else {
                return FULLUPDATE_PAYLOADS;
            }
        }

        void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        private void onEnteredHiddenState() {
            this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            ViewCompat.setImportantForAccessibility(this.itemView, FLAG_INVALID);
        }

        private void onLeftHiddenState() {
            ViewCompat.setImportantForAccessibility(this.itemView, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(new StringBuilder("ViewHolder{").append(Integer.toHexString(hashCode())).append(" position=").append(this.mPosition).append(" id=").append(this.mItemId).append(", oldPos=").append(this.mOldPosition).append(", pLpos:").append(this.mPreLayoutPosition).toString());
            if (isScrap()) {
                stringBuilder.append(" scrap ").append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                stringBuilder.append(" invalid");
            }
            if (!isBound()) {
                stringBuilder.append(" unbound");
            }
            if (needsUpdate()) {
                stringBuilder.append(" update");
            }
            if (isRemoved()) {
                stringBuilder.append(" removed");
            }
            if (shouldIgnore()) {
                stringBuilder.append(" ignored");
            }
            if (isTmpDetached()) {
                stringBuilder.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                stringBuilder.append(new StringBuilder(" not recyclable(").append(this.mIsRecyclableCount).append(SocializeConstants.OP_CLOSE_PAREN).toString());
            }
            if (isAdapterPositionUnknown()) {
                stringBuilder.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                stringBuilder.append(" no parent");
            }
            stringBuilder.append(com.alipay.sdk.util.h.d);
            return stringBuilder.toString();
        }

        public final void setIsRecyclable(boolean z) {
            this.mIsRecyclableCount = z ? this.mIsRecyclableCount - 1 : this.mIsRecyclableCount + 1;
            if (this.mIsRecyclableCount < 0) {
                this.mIsRecyclableCount = 0;
                new StringBuilder("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ").append(this);
            } else if (!z && this.mIsRecyclableCount == 1) {
                this.mFlags |= 16;
            } else if (z && this.mIsRecyclableCount == 0) {
                this.mFlags &= -17;
            }
        }

        public final boolean isRecyclable() {
            return ((this.mFlags & 16) != 0 || ViewCompat.hasTransientState(this.itemView)) ? FORCE_INVALIDATE_DISPLAY_LIST : true;
        }

        private boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        private boolean doesTransientStatePreventRecycling() {
            return ((this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView)) ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        boolean isUpdated() {
            return (this.mFlags & 2) != 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }
    }

    public static abstract class a<VH extends t> {
        private boolean mHasStableIds;
        private final b mObservable;

        public abstract int getItemCount();

        public abstract void onBindViewHolder(VH vh, int i);

        public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

        public a() {
            this.mObservable = new b();
            this.mHasStableIds = false;
        }

        public void onBindViewHolder(VH vh, int i, List<Object> list) {
            onBindViewHolder(vh, i);
        }

        public final VH createViewHolder(ViewGroup viewGroup, int i) {
            TraceCompat.beginSection(TRACE_CREATE_VIEW_TAG);
            VH onCreateViewHolder = onCreateViewHolder(viewGroup, i);
            onCreateViewHolder.mItemViewType = i;
            TraceCompat.endSection();
            return onCreateViewHolder;
        }

        public final void bindViewHolder(VH vh, int i) {
            vh.mPosition = i;
            if (hasStableIds()) {
                vh.mItemId = getItemId(i);
            }
            vh.setFlags(VERTICAL, 519);
            TraceCompat.beginSection(TRACE_BIND_VIEW_TAG);
            onBindViewHolder(vh, i, vh.getUnmodifiedPayloads());
            vh.clearPayload();
            TraceCompat.endSection();
        }

        public int getItemViewType(int i) {
            return TOUCH_SLOP_DEFAULT;
        }

        public void setHasStableIds(boolean z) {
            if (hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.mHasStableIds = z;
        }

        public long getItemId(int i) {
            return NO_ID;
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public void onViewRecycled(VH vh) {
        }

        public boolean onFailedToRecycleView(VH vh) {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }

        public void onViewAttachedToWindow(VH vh) {
        }

        public void onViewDetachedFromWindow(VH vh) {
        }

        public final boolean hasObservers() {
            return this.mObservable.a();
        }

        public void registerAdapterDataObserver(c cVar) {
            this.mObservable.registerObserver(cVar);
        }

        public void unregisterAdapterDataObserver(c cVar) {
            this.mObservable.unregisterObserver(cVar);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        }

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        }

        public final void notifyDataSetChanged() {
            this.mObservable.b();
        }

        public final void notifyItemChanged(int i) {
            this.mObservable.a(i, VERTICAL);
        }

        public final void notifyItemChanged(int i, Object obj) {
            this.mObservable.a(i, VERTICAL, obj);
        }

        public final void notifyItemRangeChanged(int i, int i2) {
            this.mObservable.a(i, i2);
        }

        public final void notifyItemRangeChanged(int i, int i2, Object obj) {
            this.mObservable.a(i, i2, obj);
        }

        public final void notifyItemInserted(int i) {
            this.mObservable.b(i, VERTICAL);
        }

        public final void notifyItemMoved(int i, int i2) {
            this.mObservable.d(i, i2);
        }

        public final void notifyItemRangeInserted(int i, int i2) {
            this.mObservable.b(i, i2);
        }

        public final void notifyItemRemoved(int i) {
            this.mObservable.c(i, VERTICAL);
        }

        public final void notifyItemRangeRemoved(int i, int i2) {
            this.mObservable.c(i, i2);
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        t c;
        final Rect d;
        boolean e;
        boolean f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }

        public LayoutParams(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.d = new Rect();
            this.e = true;
            this.f = false;
        }
    }

    public static abstract class h {
        ac p;
        RecyclerView q;
        p r;
        boolean s;
        boolean t;
        boolean u;
        boolean v;
        int w;
        int x;
        int y;
        int z;

        public static class a {
            public int a;
            public int b;
            public boolean c;
            public boolean d;
        }

        public abstract LayoutParams b();

        public h() {
            this.s = false;
            this.t = false;
            this.u = false;
            this.v = true;
        }

        final void a(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.q = null;
                this.p = null;
                this.y = 0;
                this.z = 0;
            } else {
                this.q = recyclerView;
                this.p = recyclerView.mChildHelper;
                this.y = recyclerView.getWidth();
                this.z = recyclerView.getHeight();
            }
            this.w = 1073741824;
            this.x = 1073741824;
        }

        final void f(int i, int i2) {
            this.y = MeasureSpec.getSize(i);
            this.w = MeasureSpec.getMode(i);
            if (this.w == 0 && !ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.y = 0;
            }
            this.z = MeasureSpec.getSize(i2);
            this.x = MeasureSpec.getMode(i2);
            if (this.x == 0 && !ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.z = 0;
            }
        }

        final void g(int i, int i2) {
            int i3 = InMobiClientPositioning.NO_REPEAT;
            int i4 = ExploreByTouchHelper.INVALID_ID;
            int n = n();
            if (n == 0) {
                this.q.defaultOnMeasure(i, i2);
                return;
            }
            int i5 = 0;
            int i6 = Integer.MIN_VALUE;
            int i7 = Integer.MAX_VALUE;
            while (i5 < n) {
                View e = e(i5);
                LayoutParams layoutParams = (LayoutParams) e.getLayoutParams();
                int e2 = e(e) - layoutParams.leftMargin;
                int g = layoutParams.rightMargin + g(e);
                int f = f(e) - layoutParams.topMargin;
                int h = layoutParams.bottomMargin + h(e);
                if (e2 >= i7) {
                    e2 = i7;
                }
                if (g <= i6) {
                    g = i6;
                }
                if (f >= i3) {
                    f = i3;
                }
                if (h <= i4) {
                    h = i4;
                }
                i5++;
                i6 = g;
                i3 = f;
                i7 = e2;
                i4 = h;
            }
            this.q.mTempRect.set(i7, i3, i6, i4);
            a(this.q.mTempRect, i, i2);
        }

        public void a(Rect rect, int i, int i2) {
            i(a(i, (rect.width() + o()) + q(), ViewCompat.getMinimumWidth(this.q)), a(i2, (rect.height() + p()) + r(), ViewCompat.getMinimumHeight(this.q)));
        }

        public final void l() {
            if (this.q != null) {
                this.q.requestLayout();
            }
        }

        public static int a(int i, int i2, int i3) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            switch (mode) {
                case ExploreByTouchHelper.INVALID_ID:
                    return Math.min(size, Math.max(i2, i3));
                case 1073741824:
                    return size;
                default:
                    return Math.max(i2, i3);
            }
        }

        public void a(String str) {
            if (this.q != null) {
                this.q.assertNotInLayoutOrScroll(str);
            }
        }

        public boolean c() {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }

        final void b(RecyclerView recyclerView, m mVar) {
            this.t = false;
            a(recyclerView, mVar);
        }

        public final boolean a(Runnable runnable) {
            return this.q != null ? this.q.removeCallbacks(runnable) : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        public void a(RecyclerView recyclerView, m mVar) {
        }

        public void c(m mVar, q qVar) {
        }

        public boolean a(LayoutParams layoutParams) {
            return layoutParams != null ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        public LayoutParams a(android.view.ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
        }

        public LayoutParams a(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public int a(int i, m mVar, q qVar) {
            return TOUCH_SLOP_DEFAULT;
        }

        public int b(int i, m mVar, q qVar) {
            return TOUCH_SLOP_DEFAULT;
        }

        public boolean e() {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }

        public boolean f() {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }

        public void c(int i) {
        }

        public void a(RecyclerView recyclerView, int i) {
        }

        public final void a(p pVar) {
            if (!(this.r == null || pVar == this.r || !this.r.k)) {
                this.r.b();
            }
            this.r = pVar;
            p pVar2 = this.r;
            pVar2.h = this.q;
            pVar2.i = this;
            if (pVar2.g == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            pVar2.h.mState.a = pVar2.g;
            pVar2.k = true;
            pVar2.j = true;
            pVar2.l = pVar2.h.mLayout.b(pVar2.g);
            pVar2.h.mViewFlinger.a();
        }

        public final boolean m() {
            return (this.r == null || !this.r.k) ? FORCE_INVALIDATE_DISPLAY_LIST : true;
        }

        final void a(View view, int i, boolean z) {
            t childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (z || childViewHolderInt.isRemoved()) {
                this.q.mViewInfoStore.b(childViewHolderInt);
            } else {
                this.q.mViewInfoStore.c(childViewHolderInt);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.unScrap();
                } else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.p.a(view, i, view.getLayoutParams(), FORCE_INVALIDATE_DISPLAY_LIST);
            } else if (view.getParent() == this.q) {
                int c = this.p.c(view);
                if (i == -1) {
                    i = this.p.a();
                }
                if (c == -1) {
                    throw new IllegalStateException(new StringBuilder("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:").append(this.q.indexOfChild(view)).toString());
                } else if (c != i) {
                    android.support.v7.widget.RecyclerView.h hVar = this.q.mLayout;
                    View e = hVar.e(c);
                    if (e == null) {
                        throw new IllegalArgumentException(new StringBuilder("Cannot move a child from non-existing index:").append(c).toString());
                    }
                    hVar.d(c);
                    LayoutParams layoutParams2 = (LayoutParams) e.getLayoutParams();
                    t childViewHolderInt2 = RecyclerView.getChildViewHolderInt(e);
                    if (childViewHolderInt2.isRemoved()) {
                        hVar.q.mViewInfoStore.b(childViewHolderInt2);
                    } else {
                        hVar.q.mViewInfoStore.c(childViewHolderInt2);
                    }
                    hVar.p.a(e, i, layoutParams2, childViewHolderInt2.isRemoved());
                }
            } else {
                this.p.a(view, i, FORCE_INVALIDATE_DISPLAY_LIST);
                layoutParams.e = true;
                if (this.r != null && this.r.k) {
                    p pVar = this.r;
                    if (pVar.a(view) == pVar.g) {
                        pVar.l = view;
                    }
                }
            }
            if (layoutParams.f) {
                childViewHolderInt.itemView.invalidate();
                layoutParams.f = false;
            }
        }

        private void a(int i) {
            if (e(i) != null) {
                ac acVar = this.p;
                int a = acVar.a(i);
                View b = acVar.a.b(a);
                if (b != null) {
                    if (acVar.b.d(a)) {
                        acVar.b(b);
                    }
                    acVar.a.a(a);
                }
            }
        }

        public static int a(View view) {
            return ((LayoutParams) view.getLayoutParams()).c.getLayoutPosition();
        }

        public final View b(View view) {
            if (this.q == null) {
                return null;
            }
            View findContainingItemView = this.q.findContainingItemView(view);
            return (findContainingItemView == null || this.p.d(findContainingItemView)) ? null : findContainingItemView;
        }

        public View b(int i) {
            int n = n();
            for (int i2 = 0; i2 < n; i2++) {
                View e = e(i2);
                t childViewHolderInt = RecyclerView.getChildViewHolderInt(e);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore()) {
                    if (this.q.mState.g || !childViewHolderInt.isRemoved()) {
                        return e;
                    }
                }
            }
            return null;
        }

        private void d(int i) {
            e(i);
            this.p.d(i);
        }

        public final void a(int i, m mVar) {
            View e = e(i);
            a(i);
            mVar.a(e);
        }

        public final int n() {
            return this.p != null ? this.p.a() : TOUCH_SLOP_DEFAULT;
        }

        public final View e(int i) {
            return this.p != null ? this.p.b(i) : null;
        }

        public final int o() {
            return this.q != null ? this.q.getPaddingLeft() : TOUCH_SLOP_DEFAULT;
        }

        public final int p() {
            return this.q != null ? this.q.getPaddingTop() : TOUCH_SLOP_DEFAULT;
        }

        public final int q() {
            return this.q != null ? this.q.getPaddingRight() : TOUCH_SLOP_DEFAULT;
        }

        public final int r() {
            return this.q != null ? this.q.getPaddingBottom() : TOUCH_SLOP_DEFAULT;
        }

        public final int s() {
            android.support.v7.widget.RecyclerView.a adapter = this.q != null ? this.q.getAdapter() : null;
            return adapter != null ? adapter.getItemCount() : TOUCH_SLOP_DEFAULT;
        }

        public void f(int i) {
            if (this.q != null) {
                this.q.offsetChildrenHorizontal(i);
            }
        }

        public void g(int i) {
            if (this.q != null) {
                this.q.offsetChildrenVertical(i);
            }
        }

        public final void a(m mVar) {
            for (int n = n() - 1; n >= 0; n--) {
                View e = e(n);
                t childViewHolderInt = RecyclerView.getChildViewHolderInt(e);
                if (!childViewHolderInt.shouldIgnore()) {
                    if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || this.q.mAdapter.hasStableIds()) {
                        d(n);
                        mVar.c(e);
                        this.q.mViewInfoStore.c(childViewHolderInt);
                    } else {
                        a(n);
                        mVar.a(childViewHolderInt);
                    }
                }
            }
        }

        final void b(m mVar) {
            int size = mVar.a.size();
            for (int i = size - 1; i >= 0; i--) {
                View view = ((t) mVar.a.get(i)).itemView;
                t childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(FORCE_INVALIDATE_DISPLAY_LIST);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.q.removeDetachedView(view, FORCE_INVALIDATE_DISPLAY_LIST);
                    }
                    if (this.q.mItemAnimator != null) {
                        this.q.mItemAnimator.c(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    mVar.b(view);
                }
            }
            mVar.a.clear();
            if (mVar.b != null) {
                mVar.b.clear();
            }
            if (size > 0) {
                this.q.invalidate();
            }
        }

        final boolean a(View view, int i, int i2, LayoutParams layoutParams) {
            return (!view.isLayoutRequested() && this.v && b(view.getWidth(), i, layoutParams.width) && b(view.getHeight(), i2, layoutParams.height)) ? FORCE_INVALIDATE_DISPLAY_LIST : true;
        }

        static boolean b(int i, int i2, int i3) {
            int mode = MeasureSpec.getMode(i2);
            int size = MeasureSpec.getSize(i2);
            if (i3 > 0 && i != i3) {
                return FORCE_INVALIDATE_DISPLAY_LIST;
            }
            switch (mode) {
                case ExploreByTouchHelper.INVALID_ID:
                    return size >= i ? true : FORCE_INVALIDATE_DISPLAY_LIST;
                case TOUCH_SLOP_DEFAULT:
                    return true;
                case 1073741824:
                    return size == i ? true : FORCE_INVALIDATE_DISPLAY_LIST;
                default:
                    return FORCE_INVALIDATE_DISPLAY_LIST;
            }
        }

        public static int a(int i, int i2, int i3, int i4, boolean z) {
            int i5 = TOUCH_SLOP_DEFAULT;
            int max = Math.max(TOUCH_SLOP_DEFAULT, i - i3);
            if (z) {
                if (i4 >= 0) {
                    i5 = 1073741824;
                    max = i4;
                } else if (i4 == -1) {
                    switch (i2) {
                        case ExploreByTouchHelper.INVALID_ID:
                        case 1073741824:
                            i5 = i2;
                            break;
                        default:
                            max = 0;
                            break;
                    }
                } else {
                    if (i4 == -2) {
                        max = 0;
                    }
                    max = 0;
                }
            } else if (i4 >= 0) {
                i5 = 1073741824;
                max = i4;
            } else if (i4 == -1) {
                i5 = i2;
            } else {
                if (i4 == -2) {
                    if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                        i5 = Integer.MIN_VALUE;
                    }
                }
                max = 0;
            }
            return MeasureSpec.makeMeasureSpec(max, i5);
        }

        public static int c(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).d;
            return rect.right + (view.getMeasuredWidth() + rect.left);
        }

        public static int d(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).d;
            return rect.bottom + (view.getMeasuredHeight() + rect.top);
        }

        public static void a(View view, int i, int i2, int i3, int i4) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).d;
            view.layout(rect.left + i, rect.top + i2, i3 - rect.right, i4 - rect.bottom);
        }

        public static int e(View view) {
            return view.getLeft() - ((LayoutParams) view.getLayoutParams()).d.left;
        }

        public static int f(View view) {
            return view.getTop() - ((LayoutParams) view.getLayoutParams()).d.top;
        }

        public static int g(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.right + view.getRight();
        }

        public static int h(View view) {
            return ((LayoutParams) view.getLayoutParams()).d.bottom + view.getBottom();
        }

        public final void a(View view, Rect rect) {
            if (this.q == null) {
                rect.set(TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT);
            } else {
                rect.set(this.q.getItemDecorInsetsForChild(view));
            }
        }

        public View a(View view, int i, m mVar, q qVar) {
            return null;
        }

        public void a() {
        }

        public void a(int i, int i2) {
        }

        public void b(int i, int i2) {
        }

        public void c(int i, int i2) {
        }

        public void d(int i, int i2) {
        }

        public int c(q qVar) {
            return TOUCH_SLOP_DEFAULT;
        }

        public int a(q qVar) {
            return TOUCH_SLOP_DEFAULT;
        }

        public int e(q qVar) {
            return TOUCH_SLOP_DEFAULT;
        }

        public int d(q qVar) {
            return TOUCH_SLOP_DEFAULT;
        }

        public int b(q qVar) {
            return TOUCH_SLOP_DEFAULT;
        }

        public int f(q qVar) {
            return TOUCH_SLOP_DEFAULT;
        }

        public final void h(int i, int i2) {
            this.q.defaultOnMeasure(i, i2);
        }

        public final void i(int i, int i2) {
            this.q.setMeasuredDimension(i, i2);
        }

        public Parcelable d() {
            return null;
        }

        public void a(Parcelable parcelable) {
        }

        final void t() {
            if (this.r != null) {
                this.r.b();
            }
        }

        public void h(int i) {
        }

        public final void c(m mVar) {
            for (int n = n() - 1; n >= 0; n--) {
                if (!RecyclerView.getChildViewHolderInt(e(n)).shouldIgnore()) {
                    a(n, mVar);
                }
            }
        }

        public void a(AccessibilityEvent accessibilityEvent) {
            boolean z = true;
            m mVar = this.q.mRecycler;
            q qVar = this.q.mState;
            AccessibilityRecordCompat asRecord = AccessibilityEventCompat.asRecord(accessibilityEvent);
            if (this.q != null && asRecord != null) {
                if (!(ViewCompat.canScrollVertically(this.q, VERTICAL) || ViewCompat.canScrollVertically(this.q, NO_POSITION) || ViewCompat.canScrollHorizontally(this.q, NO_POSITION) || ViewCompat.canScrollHorizontally(this.q, VERTICAL))) {
                    z = FORCE_INVALIDATE_DISPLAY_LIST;
                }
                asRecord.setScrollable(z);
                if (this.q.mAdapter != null) {
                    asRecord.setItemCount(this.q.mAdapter.getItemCount());
                }
            }
        }

        final void a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            t childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && !this.p.d(childViewHolderInt.itemView)) {
                a(this.q.mRecycler, this.q.mState, view, accessibilityNodeInfoCompat);
            }
        }

        public void a(m mVar, q qVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int a;
            int a2 = f() ? a(view) : 0;
            if (e()) {
                a = a(view);
            } else {
                a = 0;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(a2, VERTICAL, a, 1, FORCE_INVALIDATE_DISPLAY_LIST, false));
        }

        public int a(m mVar, q qVar) {
            return (this.q == null || this.q.mAdapter == null || !f()) ? VERTICAL : this.q.mAdapter.getItemCount();
        }

        public int b(m mVar, q qVar) {
            return (this.q == null || this.q.mAdapter == null || !e()) ? VERTICAL : this.q.mAdapter.getItemCount();
        }

        public static a a(Context context, AttributeSet attributeSet, int i, int i2) {
            a aVar = new a();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RecyclerView, i, i2);
            aVar.a = obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_orientation, VERTICAL);
            aVar.b = obtainStyledAttributes.getInt(R.styleable.RecyclerView_spanCount, VERTICAL);
            aVar.c = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_reverseLayout, FORCE_INVALIDATE_DISPLAY_LIST);
            aVar.d = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_stackFromEnd, FORCE_INVALIDATE_DISPLAY_LIST);
            obtainStyledAttributes.recycle();
            return aVar;
        }

        final void b(RecyclerView recyclerView) {
            f(MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        boolean i() {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }

        public final void a(View view, m mVar) {
            ac acVar = this.p;
            int a = acVar.a.a(view);
            if (a >= 0) {
                if (acVar.b.d(a)) {
                    acVar.b(view);
                }
                acVar.a.a(a);
            }
            mVar.a(view);
        }

        static /* synthetic */ void a(android.support.v7.widget.RecyclerView.h hVar, p pVar) {
            if (hVar.r == pVar) {
                hVar.r = null;
            }
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<android.support.v7.widget.RecyclerView.SavedState> CREATOR;
        Parcelable a;

        SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readParcelable(h.class.getClassLoader());
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.a, TOUCH_SLOP_DEFAULT);
        }

        static {
            CREATOR = new bg();
        }
    }

    static class b extends Observable<c> {
        b() {
        }

        public final boolean a() {
            return !this.mObservers.isEmpty() ? true : FORCE_INVALIDATE_DISPLAY_LIST;
        }

        public final void b() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a();
            }
        }

        public final void a(int i, int i2) {
            a(i, i2, null);
        }

        public final void a(int i, int i2, Object obj) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a(i, i2, obj);
            }
        }

        public final void b(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).a(i, i2);
            }
        }

        public final void c(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).b(i, i2);
            }
        }

        public final void d(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((c) this.mObservers.get(size)).c(i, i2);
            }
        }
    }

    public static abstract class c {
        public void a() {
        }

        public void a(int i, int i2, Object obj) {
        }

        public void a(int i, int i2) {
        }

        public void b(int i, int i2) {
        }

        public void c(int i, int i2) {
        }
    }

    public static interface d {
        int a();
    }

    public static abstract class e {
        private ArrayList<Object> a;
        a h;
        long i;
        long j;
        long k;
        long l;

        static interface a {
            void a(t tVar);
        }

        public static class b {
            public int a;
            public int b;
            public int c;
            public int d;

            public final android.support.v7.widget.RecyclerView.e.b a(t tVar) {
                View view = tVar.itemView;
                this.a = view.getLeft();
                this.b = view.getTop();
                this.c = view.getRight();
                this.d = view.getBottom();
                return this;
            }
        }

        public abstract void a();

        public abstract boolean a(t tVar, b bVar, b bVar2);

        public abstract boolean a(t tVar, t tVar2, b bVar, b bVar2);

        public abstract boolean b();

        public abstract boolean b(t tVar, b bVar, b bVar2);

        public abstract void c(t tVar);

        public abstract boolean c(t tVar, b bVar, b bVar2);

        public abstract void d();

        public e() {
            this.h = null;
            this.a = new ArrayList();
            this.i = 120;
            this.j = 120;
            this.k = 250;
            this.l = 250;
        }

        static int d(t tVar) {
            int access$6500 = tVar.mFlags & 14;
            if (tVar.isInvalid()) {
                return XZBDevice.DOWNLOAD_LIST_ALL;
            }
            if ((access$6500 & 4) != 0) {
                return access$6500;
            }
            int oldPosition = tVar.getOldPosition();
            int adapterPosition = tVar.getAdapterPosition();
            return (oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition) ? access$6500 : access$6500 | 2048;
        }

        public final void e(t tVar) {
            if (this.h != null) {
                this.h.a(tVar);
            }
        }

        public boolean f(t tVar) {
            return true;
        }

        public boolean a(t tVar, List<Object> list) {
            return f(tVar);
        }

        public final void e() {
            int size = this.a.size();
            for (int i = TOUCH_SLOP_DEFAULT; i < size; i++) {
                this.a.get(i);
            }
            this.a.clear();
        }
    }

    private class f implements a {
        private f() {
        }

        public final void a(t tVar) {
            tVar.setIsRecyclable(true);
            if (tVar.mShadowedHolder != null && tVar.mShadowingHolder == null) {
                tVar.mShadowedHolder = null;
            }
            tVar.mShadowingHolder = null;
            if (!tVar.shouldBeKeptAsChild() && !RecyclerView.this.removeAnimatingView(tVar.itemView) && tVar.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(tVar.itemView, FORCE_INVALIDATE_DISPLAY_LIST);
            }
        }
    }

    public static abstract class g {
        public void onDraw(Canvas canvas, RecyclerView recyclerView, q qVar) {
            onDraw(canvas, recyclerView);
        }

        @Deprecated
        public void onDraw(Canvas canvas, RecyclerView recyclerView) {
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, q qVar) {
            onDrawOver(canvas, recyclerView);
        }

        @Deprecated
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
        }

        @Deprecated
        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, q qVar) {
            getItemOffsets(rect, ((LayoutParams) view.getLayoutParams()).c.getLayoutPosition(), recyclerView);
        }
    }

    public static interface i {
    }

    public static interface j {
        boolean a(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    public static abstract class k {
        public void a(RecyclerView recyclerView, int i) {
        }
    }

    public static class l {
        SparseArray<ArrayList<t>> a;
        SparseIntArray b;
        int c;

        public l() {
            this.a = new SparseArray();
            this.b = new SparseIntArray();
            this.c = 0;
        }

        final void a() {
            this.c++;
        }

        final void b() {
            this.c--;
        }
    }

    public final class m {
        final ArrayList<t> a;
        ArrayList<t> b;
        final ArrayList<t> c;
        final List<t> d;
        int e;
        l f;
        r g;

        public m() {
            this.a = new ArrayList();
            this.b = null;
            this.c = new ArrayList();
            this.d = Collections.unmodifiableList(this.a);
            this.e = 2;
        }

        public final void a() {
            this.a.clear();
            b();
        }

        public final int a(int i) {
            if (i >= 0 && i < RecyclerView.this.mState.a()) {
                return !RecyclerView.this.mState.g ? i : RecyclerView.this.mAdapterHelper.b(i);
            } else {
                throw new IndexOutOfBoundsException(new StringBuilder("invalid position ").append(i).append(". State item count is ").append(RecyclerView.this.mState.a()).toString());
            }
        }

        public final View b(int i) {
            boolean z = true;
            if (i < 0 || i >= RecyclerView.this.mState.a()) {
                throw new IndexOutOfBoundsException(new StringBuilder("Invalid item position ").append(i).append(SocializeConstants.OP_OPEN_PAREN).append(i).append("). Item count:").append(RecyclerView.this.mState.a()).toString());
            }
            boolean z2;
            t tVar;
            boolean z3;
            boolean z4;
            t createViewHolder;
            LayoutParams layoutParams;
            if (RecyclerView.this.mState.g) {
                t d = d(i);
                t tVar2 = d;
                z2 = d != null;
                tVar = tVar2;
            } else {
                tVar = null;
                z2 = false;
            }
            if (tVar == null) {
                tVar = e(i);
                if (tVar != null) {
                    if (tVar.isRemoved()) {
                        z3 = RecyclerView.this.mState.g;
                    } else if (tVar.mPosition < 0 || tVar.mPosition >= RecyclerView.this.mAdapter.getItemCount()) {
                        throw new IndexOutOfBoundsException(new StringBuilder("Inconsistency detected. Invalid view holder adapter position").append(tVar).toString());
                    } else {
                        z3 = (RecyclerView.this.mState.g || RecyclerView.this.mAdapter.getItemViewType(tVar.mPosition) == tVar.getItemViewType()) ? !RecyclerView.this.mAdapter.hasStableIds() || tVar.getItemId() == RecyclerView.this.mAdapter.getItemId(tVar.mPosition) : false;
                    }
                    if (z3) {
                        z2 = true;
                    } else {
                        tVar.addFlags(XZBDevice.DOWNLOAD_LIST_ALL);
                        if (tVar.isScrap()) {
                            RecyclerView.this.removeDetachedView(tVar.itemView, FORCE_INVALIDATE_DISPLAY_LIST);
                            tVar.unScrap();
                        } else if (tVar.wasReturnedFromScrap()) {
                            tVar.clearReturnedFromScrapFlag();
                        }
                        a(tVar);
                        tVar = null;
                    }
                }
            }
            if (tVar == null) {
                int b = RecyclerView.this.mAdapterHelper.b(i);
                if (b < 0 || b >= RecyclerView.this.mAdapter.getItemCount()) {
                    throw new IndexOutOfBoundsException(new StringBuilder("Inconsistency detected. Invalid item position ").append(i).append("(offset:").append(b).append(").state:").append(RecyclerView.this.mState.a()).toString());
                }
                View a;
                ArrayList arrayList;
                int itemViewType = RecyclerView.this.mAdapter.getItemViewType(b);
                if (RecyclerView.this.mAdapter.hasStableIds()) {
                    tVar = a(RecyclerView.this.mAdapter.getItemId(b), itemViewType);
                    if (tVar != null) {
                        tVar.mPosition = b;
                        z3 = true;
                        if (tVar == null && this.g != null) {
                            a = this.g.a();
                            if (a != null) {
                                tVar = RecyclerView.this.getChildViewHolder(a);
                                if (tVar == null) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                                } else if (tVar.shouldIgnore()) {
                                    throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                                }
                            }
                        }
                        if (tVar == null) {
                            arrayList = (ArrayList) c().a.get(itemViewType);
                            if (arrayList != null || arrayList.isEmpty()) {
                                d = null;
                            } else {
                                int size = arrayList.size() - 1;
                                d = (t) arrayList.get(size);
                                arrayList.remove(size);
                            }
                            if (d != null) {
                                d.resetInternal();
                                if (FORCE_INVALIDATE_DISPLAY_LIST && (d.itemView instanceof ViewGroup)) {
                                    a((ViewGroup) d.itemView, (boolean) FORCE_INVALIDATE_DISPLAY_LIST);
                                }
                            }
                            tVar = d;
                        }
                        if (tVar != null) {
                            z4 = z3;
                            createViewHolder = RecyclerView.this.mAdapter.createViewHolder(RecyclerView.this, itemViewType);
                        } else {
                            z4 = z3;
                            createViewHolder = tVar;
                        }
                    }
                }
                z3 = z2;
                a = this.g.a();
                if (a != null) {
                    tVar = RecyclerView.this.getChildViewHolder(a);
                    if (tVar == null) {
                        throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                    } else if (tVar.shouldIgnore()) {
                        throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                    }
                }
                if (tVar == null) {
                    arrayList = (ArrayList) c().a.get(itemViewType);
                    if (arrayList != null) {
                    }
                    d = null;
                    if (d != null) {
                        d.resetInternal();
                        a((ViewGroup) d.itemView, (boolean) FORCE_INVALIDATE_DISPLAY_LIST);
                    }
                    tVar = d;
                }
                if (tVar != null) {
                    z4 = z3;
                    createViewHolder = tVar;
                } else {
                    z4 = z3;
                    createViewHolder = RecyclerView.this.mAdapter.createViewHolder(RecyclerView.this, itemViewType);
                }
            } else {
                createViewHolder = tVar;
                z4 = z2;
            }
            if (z4 && !RecyclerView.this.mState.g && createViewHolder.hasAnyOfTheFlags(Message.FLAG_REQ_BIT2)) {
                createViewHolder.setFlags(TOUCH_SLOP_DEFAULT, Message.FLAG_REQ_BIT2);
                if (RecyclerView.this) {
                    e.d(createViewHolder);
                    e eVar = RecyclerView.this.mItemAnimator;
                    q qVar = RecyclerView.this.mState;
                    createViewHolder.getUnmodifiedPayloads();
                    RecyclerView.this.recordAnimationInfoIfBouncedHiddenView(createViewHolder, new android.support.v7.widget.RecyclerView.e.b().a(createViewHolder));
                }
            }
            if (RecyclerView.this.mState.g && createViewHolder.isBound()) {
                createViewHolder.mPreLayoutPosition = i;
                z2 = false;
            } else if (!createViewHolder.isBound() || createViewHolder.needsUpdate() || createViewHolder.isInvalid()) {
                int b2 = RecyclerView.this.mAdapterHelper.b(i);
                createViewHolder.mOwnerRecyclerView = RecyclerView.this;
                RecyclerView.this.mAdapter.bindViewHolder(createViewHolder, b2);
                View view = createViewHolder.itemView;
                if (RecyclerView.this.isAccessibilityEnabled()) {
                    if (ViewCompat.getImportantForAccessibility(view) == 0) {
                        ViewCompat.setImportantForAccessibility(view, VERTICAL);
                    }
                    if (!ViewCompat.hasAccessibilityDelegate(view)) {
                        ViewCompat.setAccessibilityDelegate(view, RecyclerView.this.mAccessibilityDelegate.b);
                    }
                }
                if (RecyclerView.this.mState.g) {
                    createViewHolder.mPreLayoutPosition = i;
                }
                z2 = true;
            } else {
                z2 = false;
            }
            android.view.ViewGroup.LayoutParams layoutParams2 = createViewHolder.itemView.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams = (LayoutParams) RecyclerView.this.generateDefaultLayoutParams();
                createViewHolder.itemView.setLayoutParams(layoutParams);
            } else if (RecyclerView.this.checkLayoutParams(layoutParams2)) {
                layoutParams = (LayoutParams) layoutParams2;
            } else {
                layoutParams = (LayoutParams) RecyclerView.this.generateLayoutParams(layoutParams2);
                createViewHolder.itemView.setLayoutParams(layoutParams);
            }
            layoutParams.c = createViewHolder;
            if (!(z4 && r1)) {
                z = false;
            }
            layoutParams.f = z;
            return createViewHolder.itemView;
        }

        private void a(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, true);
                }
            }
            if (!z) {
                return;
            }
            if (viewGroup.getVisibility() == 4) {
                viewGroup.setVisibility(TOUCH_SLOP_DEFAULT);
                viewGroup.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                return;
            }
            int visibility = viewGroup.getVisibility();
            viewGroup.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            viewGroup.setVisibility(visibility);
        }

        public final void a(View view) {
            t childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, FORCE_INVALIDATE_DISPLAY_LIST);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            a(childViewHolderInt);
        }

        final void b() {
            for (int size = this.c.size() - 1; size >= 0; size--) {
                c(size);
            }
            this.c.clear();
        }

        final void c(int i) {
            c((t) this.c.get(i));
            this.c.remove(i);
        }

        final void a(t tVar) {
            boolean z = true;
            int i = TOUCH_SLOP_DEFAULT;
            if (tVar.isScrap() || tVar.itemView.getParent() != null) {
                StringBuilder append = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:").append(tVar.isScrap()).append(" isAttached:");
                if (tVar.itemView.getParent() == null) {
                    z = false;
                }
                throw new IllegalArgumentException(append.append(z).toString());
            } else if (tVar.isTmpDetached()) {
                throw new IllegalArgumentException(new StringBuilder("Tmp detached view should be removed from RecyclerView before it can be recycled: ").append(tVar).toString());
            } else if (tVar.shouldIgnore()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            } else {
                boolean z2;
                int i2;
                boolean access$4900 = tVar.doesTransientStatePreventRecycling();
                if (RecyclerView.this.mAdapter != null && access$4900 && RecyclerView.this.mAdapter.onFailedToRecycleView(tVar)) {
                    z2 = true;
                } else {
                    i2 = 0;
                }
                if (z2 || tVar.isRecyclable()) {
                    if (!tVar.hasAnyOfTheFlags(XZBDevice.Predownload)) {
                        i2 = this.c.size();
                        if (i2 == this.e && i2 > 0) {
                            c((int) TOUCH_SLOP_DEFAULT);
                        }
                        if (i2 < this.e) {
                            this.c.add(tVar);
                            z2 = true;
                            if (z2) {
                                c(tVar);
                                i = 1;
                                z = z2;
                            } else {
                                z = z2;
                            }
                        }
                    }
                    i2 = 0;
                    if (z2) {
                        z = z2;
                    } else {
                        c(tVar);
                        i = 1;
                        z = z2;
                    }
                } else {
                    int i3 = 0;
                }
                RecyclerView.this.mViewInfoStore.d(tVar);
                if (!z && r1 == 0 && access$4900) {
                    tVar.mOwnerRecyclerView = null;
                }
            }
        }

        private void c(t tVar) {
            ViewCompat.setAccessibilityDelegate(tVar.itemView, null);
            if (RecyclerView.this.mRecyclerListener != null) {
                RecyclerView.this.mRecyclerListener;
            }
            if (RecyclerView.this.mAdapter != null) {
                RecyclerView.this.mAdapter.onViewRecycled(tVar);
            }
            if (RecyclerView.this.mState != null) {
                RecyclerView.this.mViewInfoStore.d(tVar);
            }
            tVar.mOwnerRecyclerView = null;
            l c = c();
            int itemViewType = tVar.getItemViewType();
            ArrayList arrayList = (ArrayList) c.a.get(itemViewType);
            if (arrayList == null) {
                arrayList = new ArrayList();
                c.a.put(itemViewType, arrayList);
                if (c.b.indexOfKey(itemViewType) < 0) {
                    c.b.put(itemViewType, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                }
            }
            if (c.b.get(itemViewType) > arrayList.size()) {
                tVar.resetInternal();
                arrayList.add(tVar);
            }
        }

        final void b(View view) {
            t childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.mScrapContainer = null;
            childViewHolderInt.mInChangeScrap = FORCE_INVALIDATE_DISPLAY_LIST;
            childViewHolderInt.clearReturnedFromScrapFlag();
            a(childViewHolderInt);
        }

        final void c(View view) {
            t childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.hasAnyOfTheFlags(XZBDevice.Fail) && childViewHolderInt.isUpdated() && !RecyclerView.this.canReuseUpdatedViewHolder(childViewHolderInt)) {
                if (this.b == null) {
                    this.b = new ArrayList();
                }
                childViewHolderInt.setScrapContainer(this, true);
                this.b.add(childViewHolderInt);
            } else if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || RecyclerView.this.mAdapter.hasStableIds()) {
                childViewHolderInt.setScrapContainer(this, FORCE_INVALIDATE_DISPLAY_LIST);
                this.a.add(childViewHolderInt);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
            }
        }

        final void b(t tVar) {
            if (tVar.mInChangeScrap) {
                this.b.remove(tVar);
            } else {
                this.a.remove(tVar);
            }
            tVar.mScrapContainer = null;
            tVar.mInChangeScrap = FORCE_INVALIDATE_DISPLAY_LIST;
            tVar.clearReturnedFromScrapFlag();
        }

        private t d(int i) {
            int i2 = TOUCH_SLOP_DEFAULT;
            if (this.b != null) {
                int size = this.b.size();
                if (size != 0) {
                    t tVar;
                    for (int i3 = 0; i3 < size; i3++) {
                        tVar = (t) this.b.get(i3);
                        if (!tVar.wasReturnedFromScrap() && tVar.getLayoutPosition() == i) {
                            tVar.addFlags(com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCutDrawable);
                            return tVar;
                        }
                    }
                    if (RecyclerView.this.mAdapter.hasStableIds()) {
                        int a = RecyclerView.this.mAdapterHelper.a(i, (int) TOUCH_SLOP_DEFAULT);
                        if (a > 0 && a < RecyclerView.this.mAdapter.getItemCount()) {
                            long itemId = RecyclerView.this.mAdapter.getItemId(a);
                            while (i2 < size) {
                                tVar = (t) this.b.get(i2);
                                if (!tVar.wasReturnedFromScrap() && tVar.getItemId() == itemId) {
                                    tVar.addFlags(com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCutDrawable);
                                    return tVar;
                                }
                                i2++;
                            }
                        }
                    }
                    return null;
                }
            }
            return null;
        }

        private t e(int i) {
            int i2;
            View view;
            int i3 = TOUCH_SLOP_DEFAULT;
            int size = this.a.size();
            for (i2 = 0; i2 < size; i2++) {
                t tVar = (t) this.a.get(i2);
                if (!tVar.wasReturnedFromScrap() && tVar.getLayoutPosition() == i && !tVar.isInvalid()) {
                    if (RecyclerView.this.mState.g || !tVar.isRemoved()) {
                    }
                    tVar.addFlags(com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCutDrawable);
                    return tVar;
                }
            }
            ac acVar = RecyclerView.this.mChildHelper;
            int size2 = acVar.c.size();
            for (i2 = 0; i2 < size2; i2++) {
                View view2 = (View) acVar.c.get(i2);
                t b = acVar.a.b(view2);
                if (b.getLayoutPosition() == i && !b.isInvalid() && !b.isRemoved()) {
                    view = view2;
                    break;
                }
            }
            view = null;
            if (view != null) {
                tVar = RecyclerView.getChildViewHolderInt(view);
                ac acVar2 = RecyclerView.this.mChildHelper;
                i3 = acVar2.a.a(view);
                if (i3 < 0) {
                    throw new IllegalArgumentException(new StringBuilder("view is not a child, cannot hide ").append(view).toString());
                } else if (acVar2.b.c(i3)) {
                    acVar2.b.b(i3);
                    acVar2.b(view);
                    int c = RecyclerView.this.mChildHelper.c(view);
                    if (c == -1) {
                        throw new IllegalStateException(new StringBuilder("layout index should not be -1 after unhiding a view:").append(tVar).toString());
                    }
                    RecyclerView.this.mChildHelper.d(c);
                    c(view);
                    tVar.addFlags(8224);
                    return tVar;
                } else {
                    throw new RuntimeException(new StringBuilder("trying to unhide a view that was not hidden").append(view).toString());
                }
            }
            i2 = this.c.size();
            while (i3 < i2) {
                tVar = (t) this.c.get(i3);
                if (!tVar.isInvalid() && tVar.getLayoutPosition() == i) {
                    this.c.remove(i3);
                    return tVar;
                }
                i3++;
            }
            return null;
        }

        private t a(long j, int i) {
            int size;
            for (size = this.a.size() - 1; size >= 0; size--) {
                t tVar = (t) this.a.get(size);
                if (tVar.getItemId() == j && !tVar.wasReturnedFromScrap()) {
                    if (i == tVar.getItemViewType()) {
                        tVar.addFlags(com.xunlei.tdlive.R.styleable.AppCompatTheme_actionModeCutDrawable);
                        if (!tVar.isRemoved() || RecyclerView.this.mState.g) {
                            return tVar;
                        }
                        tVar.setFlags(SCROLL_STATE_SETTLING, XZBDevice.Predownload);
                        return tVar;
                    }
                    this.a.remove(size);
                    RecyclerView.this.removeDetachedView(tVar.itemView, FORCE_INVALIDATE_DISPLAY_LIST);
                    b(tVar.itemView);
                }
            }
            for (size = this.c.size() - 1; size >= 0; size--) {
                tVar = (t) this.c.get(size);
                if (tVar.getItemId() == j) {
                    if (i == tVar.getItemViewType()) {
                        this.c.remove(size);
                        return tVar;
                    }
                    c(size);
                }
            }
            return null;
        }

        final l c() {
            if (this.f == null) {
                this.f = new l();
            }
            return this.f;
        }
    }

    public static interface n {
    }

    private class o extends c {
        private o() {
        }

        public final void a() {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            RecyclerView.this.mAdapter.hasStableIds();
            RecyclerView.this.mState.f = true;
            RecyclerView.this.setDataSetChangedAfterLayout();
            if (!RecyclerView.this.mAdapterHelper.d()) {
                RecyclerView.this.requestLayout();
            }
        }

        public final void a(int i, int i2, Object obj) {
            Object obj2 = VERTICAL;
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            o oVar = RecyclerView.this.mAdapterHelper;
            RecyclerView.this.add(oVar.a(XZBDevice.DOWNLOAD_LIST_ALL, i, i2, obj));
            oVar.g |= 4;
            if (RecyclerView.this.size() != 1) {
                obj2 = null;
            }
            if (obj2 != null) {
                b();
            }
        }

        public final void a(int i, int i2) {
            int i3 = VERTICAL;
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            o oVar = RecyclerView.this.mAdapterHelper;
            RecyclerView.this.add(oVar.a(VERTICAL, i, i2, null));
            oVar.g |= 1;
            if (RecyclerView.this.size() != 1) {
                i3 = TOUCH_SLOP_DEFAULT;
            }
            if (i3 != 0) {
                b();
            }
        }

        public final void b(int i, int i2) {
            Object obj = VERTICAL;
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            o oVar = RecyclerView.this.mAdapterHelper;
            RecyclerView.this.add(oVar.a(SCROLL_STATE_SETTLING, i, i2, null));
            oVar.g |= 2;
            if (RecyclerView.this.size() != 1) {
                obj = null;
            }
            if (obj != null) {
                b();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void c(int r6, int r7) {
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.o.c(int, int):void");
            /*
            this = this;
            r4 = 0;
            r0 = 1;
            r1 = android.support.v7.widget.RecyclerView.this;
            r1.assertNotInLayoutOrScroll(r4);
            r1 = android.support.v7.widget.RecyclerView.this;
            r1 = r1.mAdapterHelper;
            if (r6 == r7) goto L_0x002c;
        L_0x000d:
            r2 = android.support.v7.widget.RecyclerView.this;
            r3 = 8;
            r3 = r1.a(r3, r6, r7, r4);
            r2.add(r3);
            r2 = r1.g;
            r2 = r2 | 8;
            r1.g = r2;
            r1 = android.support.v7.widget.RecyclerView.this;
            r1 = r1.size();
            if (r1 != r0) goto L_0x002c;
        L_0x0026:
            if (r0 == 0) goto L_0x002b;
        L_0x0028:
            r5.b();
        L_0x002b:
            return;
        L_0x002c:
            r0 = 0;
            goto L_0x0026;
            */
        }

        private void b() {
            if (RecyclerView.this.mPostUpdatesOnAnimation && RecyclerView.this.mHasFixedSize && RecyclerView.this.mIsAttached) {
                ViewCompat.postOnAnimation(RecyclerView.this, RecyclerView.this.mUpdateChildViewsRunnable);
                return;
            }
            RecyclerView.this.mAdapterUpdateDuringMeasure = true;
            RecyclerView.this.requestLayout();
        }
    }

    public static abstract class p {
        private final a a;
        int g;
        RecyclerView h;
        h i;
        boolean j;
        boolean k;
        View l;

        public static class a {
            int a;
            private int b;
            private int c;
            private int d;
            private Interpolator e;
            private boolean f;
            private int g;

            static /* synthetic */ void a(android.support.v7.widget.RecyclerView.p.a aVar, RecyclerView recyclerView) {
                if (aVar.a >= 0) {
                    int i = aVar.a;
                    aVar.a = -1;
                    recyclerView.jumpToPositionForSmoothScroller(i);
                    aVar.f = false;
                } else if (!aVar.f) {
                    aVar.g = 0;
                } else if (aVar.e != null && aVar.d <= 0) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                } else if (aVar.d <= 0) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                } else {
                    if (aVar.e != null) {
                        recyclerView.mViewFlinger.a(aVar.b, aVar.c, aVar.d, aVar.e);
                    } else if (aVar.d == Integer.MIN_VALUE) {
                        recyclerView.mViewFlinger.a(aVar.b, aVar.c);
                    } else {
                        recyclerView.mViewFlinger.a(aVar.b, aVar.c, aVar.d);
                    }
                    aVar.g++;
                    aVar.f = false;
                }
            }

            public a() {
                this((byte) 0);
            }

            private a(byte b) {
                this.a = -1;
                this.f = false;
                this.g = 0;
                this.b = 0;
                this.c = 0;
                this.d = Integer.MIN_VALUE;
                this.e = null;
            }

            public final void a(int i, int i2, int i3, Interpolator interpolator) {
                this.b = i;
                this.c = i2;
                this.d = i3;
                this.e = interpolator;
                this.f = true;
            }
        }

        protected abstract void a();

        protected abstract void a(int i, int i2, a aVar);

        protected abstract void a(View view, a aVar);

        static /* synthetic */ void a(android.support.v7.widget.RecyclerView.p pVar, int i, int i2) {
            boolean z = FORCE_INVALIDATE_DISPLAY_LIST;
            RecyclerView recyclerView = pVar.h;
            if (!pVar.k || pVar.g == -1 || recyclerView == null) {
                pVar.b();
            }
            pVar.j = false;
            if (pVar.l != null) {
                if (pVar.a(pVar.l) == pVar.g) {
                    View view = pVar.l;
                    q qVar = recyclerView.mState;
                    pVar.a(view, pVar.a);
                    a.a(pVar.a, recyclerView);
                    pVar.b();
                } else {
                    pVar.l = null;
                }
            }
            if (pVar.k) {
                q qVar2 = recyclerView.mState;
                pVar.a(i, i2, pVar.a);
                if (pVar.a.a >= 0) {
                    z = true;
                }
                a.a(pVar.a, recyclerView);
                if (!z) {
                    return;
                }
                if (pVar.k) {
                    pVar.j = true;
                    recyclerView.mViewFlinger.a();
                    return;
                }
                pVar.b();
            }
        }

        public p() {
            this.g = -1;
            this.a = new a();
        }

        protected final void b() {
            if (this.k) {
                a();
                this.h.mState.a = -1;
                this.l = null;
                this.g = -1;
                this.j = false;
                this.k = false;
                h.a(this.i, this);
                this.i = null;
                this.h = null;
            }
        }

        public final int a(View view) {
            return this.h.getChildLayoutPosition(view);
        }
    }

    public static class q {
        int a;
        int b;
        int c;
        int d;
        int e;
        boolean f;
        boolean g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        private SparseArray<Object> l;

        public q() {
            this.a = -1;
            this.b = 1;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = false;
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = false;
            this.k = false;
        }

        final void a(int i) {
            if ((this.b & i) == 0) {
                throw new IllegalStateException(new StringBuilder("Layout state should be one of ").append(Integer.toBinaryString(i)).append(" but it is ").append(Integer.toBinaryString(this.b)).toString());
            }
        }

        public final int a() {
            return this.g ? this.d - this.e : this.c;
        }

        public final String toString() {
            return new StringBuilder("State{mTargetPosition=").append(this.a).append(", mData=").append(this.l).append(", mItemCount=").append(this.c).append(", mPreviousLayoutItemCount=").append(this.d).append(", mDeletedInvisibleItemCountSincePreviousLayout=").append(this.e).append(", mStructureChanged=").append(this.f).append(", mInPreLayout=").append(this.g).append(", mRunSimpleAnimations=").append(this.h).append(", mRunPredictiveAnimations=").append(this.i).append('}').toString();
        }
    }

    public static abstract class r {
        public abstract View a();
    }

    private class s implements Runnable {
        int a;
        int b;
        ScrollerCompat c;
        private Interpolator e;
        private boolean f;
        private boolean g;

        public s() {
            this.e = sQuinticInterpolator;
            this.f = false;
            this.g = false;
            this.c = ScrollerCompat.create(RecyclerView.this.getContext(), sQuinticInterpolator);
        }

        public final void run() {
            if (RecyclerView.this.mLayout == null) {
                b();
                return;
            }
            this.g = false;
            this.f = true;
            RecyclerView.this.consumePendingUpdateOperations();
            ScrollerCompat scrollerCompat = this.c;
            p pVar = RecyclerView.this.mLayout.r;
            if (scrollerCompat.computeScrollOffset()) {
                int a;
                int i;
                int currVelocity;
                int i2;
                Object obj;
                Object obj2;
                int currX = scrollerCompat.getCurrX();
                int currY = scrollerCompat.getCurrY();
                int i3 = currX - this.a;
                int i4 = currY - this.b;
                int i5 = TOUCH_SLOP_DEFAULT;
                int i6 = TOUCH_SLOP_DEFAULT;
                this.a = currX;
                this.b = currY;
                int i7 = TOUCH_SLOP_DEFAULT;
                int i8 = TOUCH_SLOP_DEFAULT;
                if (RecyclerView.this.mAdapter != null) {
                    RecyclerView.this.eatRequestLayout();
                    RecyclerView.this.onEnterLayoutOrScroll();
                    TraceCompat.beginSection(TRACE_SCROLL_TAG);
                    if (i3 != 0) {
                        i5 = RecyclerView.this.mLayout.a(i3, RecyclerView.this.mRecycler, RecyclerView.this.mState);
                        i7 = i3 - i5;
                    }
                    if (i4 != 0) {
                        i6 = RecyclerView.this.mLayout.b(i4, RecyclerView.this.mRecycler, RecyclerView.this.mState);
                        i8 = i4 - i6;
                    }
                    TraceCompat.endSection();
                    RecyclerView.this.repositionShadowingViews();
                    RecyclerView.this.onExitLayoutOrScroll();
                    RecyclerView.this.resumeRequestLayout(FORCE_INVALIDATE_DISPLAY_LIST);
                    if (!(pVar == null || pVar.j || !pVar.k)) {
                        a = RecyclerView.this.mState.a();
                        if (a == 0) {
                            pVar.b();
                            i = i7;
                            i7 = i6;
                            i6 = i;
                            if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                                RecyclerView.this.invalidate();
                            }
                            if (ViewCompat.getOverScrollMode(RecyclerView.this) != 2) {
                                RecyclerView.this.considerReleasingGlowsOnScroll(i3, i4);
                            }
                            if (!(i6 == 0 && i8 == 0)) {
                                currVelocity = (int) scrollerCompat.getCurrVelocity();
                                if (i6 == currX) {
                                    a = i6 >= 0 ? -currVelocity : i6 <= 0 ? currVelocity : TOUCH_SLOP_DEFAULT;
                                    i2 = a;
                                } else {
                                    i2 = 0;
                                }
                                if (i8 != currY) {
                                    currVelocity = 0;
                                } else if (i8 < 0) {
                                    currVelocity = -currVelocity;
                                } else if (i8 <= 0) {
                                    currVelocity = TOUCH_SLOP_DEFAULT;
                                }
                                if (ViewCompat.getOverScrollMode(RecyclerView.this) != 2) {
                                    RecyclerView.this.absorbGlows(i2, currVelocity);
                                }
                                if (i2 != 0 || i6 == currX || scrollerCompat.getFinalX() == 0) {
                                    if (currVelocity != 0 || i8 == currY || scrollerCompat.getFinalY() == 0) {
                                        scrollerCompat.abortAnimation();
                                    }
                                }
                            }
                            if (!(i5 == 0 && i7 == 0)) {
                                RecyclerView.this.dispatchOnScrolled(i5, i7);
                            }
                            if (!RecyclerView.this.awakenScrollBars()) {
                                RecyclerView.this.invalidate();
                            }
                            if (i4 == 0 && RecyclerView.this.mLayout.f() && i7 == i4) {
                                i6 = 1;
                            } else {
                                obj = null;
                            }
                            obj2 = (i3 == 0 && RecyclerView.this.mLayout.e() && i5 == i3) ? VERTICAL : null;
                            obj2 = ((i3 == 0 || i4 != 0) && obj2 == null && obj == null) ? null : VERTICAL;
                            if (!scrollerCompat.isFinished() || obj2 == null) {
                                RecyclerView.this.setScrollState(TOUCH_SLOP_DEFAULT);
                            } else {
                                a();
                            }
                        } else {
                            if (pVar.g >= a) {
                                pVar.g = a - 1;
                            }
                            p.a(pVar, i3 - i7, i4 - i8);
                        }
                    }
                }
                i = i7;
                i7 = i6;
                i6 = i;
                if (RecyclerView.this.mItemDecorations.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                if (ViewCompat.getOverScrollMode(RecyclerView.this) != 2) {
                    RecyclerView.this.considerReleasingGlowsOnScroll(i3, i4);
                }
                currVelocity = (int) scrollerCompat.getCurrVelocity();
                if (i6 == currX) {
                    i2 = 0;
                } else {
                    if (i6 >= 0) {
                        if (i6 <= 0) {
                        }
                    }
                    i2 = a;
                }
                if (i8 != currY) {
                    currVelocity = 0;
                } else if (i8 < 0) {
                    currVelocity = -currVelocity;
                } else if (i8 <= 0) {
                    currVelocity = TOUCH_SLOP_DEFAULT;
                }
                if (ViewCompat.getOverScrollMode(RecyclerView.this) != 2) {
                    RecyclerView.this.absorbGlows(i2, currVelocity);
                }
                scrollerCompat.abortAnimation();
                RecyclerView.this.dispatchOnScrolled(i5, i7);
                if (RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                if (i4 == 0) {
                }
                obj = null;
                if (i3 == 0) {
                }
                if (i3 == 0) {
                }
                if (scrollerCompat.isFinished()) {
                }
                RecyclerView.this.setScrollState(TOUCH_SLOP_DEFAULT);
            }
            if (pVar != null) {
                if (pVar.j) {
                    p.a(pVar, (int) TOUCH_SLOP_DEFAULT, (int) TOUCH_SLOP_DEFAULT);
                }
                if (!this.g) {
                    pVar.b();
                }
            }
            this.f = false;
            if (this.g) {
                a();
            }
        }

        final void a() {
            if (this.f) {
                this.g = true;
                return;
            }
            RecyclerView.this.removeCallbacks(this);
            ViewCompat.postOnAnimation(RecyclerView.this, this);
        }

        public final void a(int i, int i2) {
            int round;
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            Object obj = abs > abs2 ? VERTICAL : null;
            int sqrt = (int) Math.sqrt(0.0d);
            int sqrt2 = (int) Math.sqrt((double) ((i * i) + (i2 * i2)));
            int width = obj != null ? RecyclerView.this.getWidth() : RecyclerView.this.getHeight();
            int i3 = width / 2;
            float sin = (((float) Math.sin((double) ((float) (((double) (Math.min(1.0f, (((float) sqrt2) * 1.0f) / ((float) width)) - 0.5f)) * 0.4712389167638204d)))) * ((float) i3)) + ((float) i3);
            if (sqrt > 0) {
                round = Math.round(1000.0f * Math.abs(sin / ((float) sqrt))) * 4;
            } else {
                if (obj != null) {
                    round = abs;
                } else {
                    round = abs2;
                }
                round = (int) (((((float) round) / ((float) width)) + 1.0f) * 300.0f);
            }
            a(i, i2, Math.min(round, MAX_SCROLL_DURATION));
        }

        public final void a(int i, int i2, int i3) {
            a(i, i2, i3, sQuinticInterpolator);
        }

        public final void a(int i, int i2, int i3, Interpolator interpolator) {
            if (this.e != interpolator) {
                this.e = interpolator;
                this.c = ScrollerCompat.create(RecyclerView.this.getContext(), interpolator);
            }
            RecyclerView.this.setScrollState(SCROLL_STATE_SETTLING);
            this.b = 0;
            this.a = 0;
            this.c.startScroll(TOUCH_SLOP_DEFAULT, 0, i, i2, i3);
            a();
        }

        public final void b() {
            RecyclerView.this.removeCallbacks(this);
            this.c.abortAnimation();
        }
    }

    static {
        NESTED_SCROLLING_ATTRS = new int[]{16843830};
        boolean z = VERSION.SDK_INT == 18 || VERSION.SDK_INT == 19 || VERSION.SDK_INT == 20;
        FORCE_INVALIDATE_DISPLAY_LIST = z;
        if (VERSION.SDK_INT >= 23) {
            z = true;
        } else {
            z = false;
        }
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = z;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
        sQuinticInterpolator = new bc();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        boolean z;
        boolean z2 = true;
        super(context, attributeSet, i);
        this.mObserver = new o();
        this.mRecycler = new m();
        this.mViewInfoStore = new cv();
        this.mUpdateChildViewsRunnable = new ba(this);
        this.mTempRect = new Rect();
        this.mItemDecorations = new ArrayList();
        this.mOnItemTouchListeners = new ArrayList();
        this.mEatRequestLayout = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mLayoutOrScrollCounter = 0;
        this.mItemAnimator = new af();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScrollFactor = Float.MIN_VALUE;
        this.mViewFlinger = new s();
        this.mState = new q();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new f();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mNestedOffsets = new int[2];
        this.mItemAnimatorRunner = new bb(this);
        this.mViewInfoProcessCallback = new bd(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        this.mPostUpdatesOnAnimation = VERSION.SDK_INT >= 16;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        if (ViewCompat.getOverScrollMode(this) == 2) {
            z = true;
        } else {
            z = false;
        }
        setWillNotDraw(z);
        this.mItemAnimator.h = this.mItemAnimatorListener;
        initAdapterManager();
        initChildrenHelper();
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, VERTICAL);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new bh(this));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RecyclerView, i, TOUCH_SLOP_DEFAULT);
            String string = obtainStyledAttributes.getString(R.styleable.RecyclerView_layoutManager);
            obtainStyledAttributes.recycle();
            createLayoutManager(context, string, attributeSet, i, TOUCH_SLOP_DEFAULT);
            if (VERSION.SDK_INT >= 21) {
                obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, NESTED_SCROLLING_ATTRS, i, TOUCH_SLOP_DEFAULT);
                z2 = obtainStyledAttributes.getBoolean(TOUCH_SLOP_DEFAULT, true);
                obtainStyledAttributes.recycle();
            }
        }
        setNestedScrollingEnabled(z2);
    }

    public bh getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public void setAccessibilityDelegateCompat(bh bhVar) {
        this.mAccessibilityDelegate = bhVar;
        ViewCompat.setAccessibilityDelegate(this, this.mAccessibilityDelegate);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createLayoutManager(android.content.Context r9, java.lang.String r10, android.util.AttributeSet r11, int r12, int r13) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.createLayoutManager(android.content.Context, java.lang.String, android.util.AttributeSet, int, int):void");
        /*
        this = this;
        if (r10 == 0) goto L_0x0054;
    L_0x0002:
        r0 = r10.trim();
        r1 = r0.length();
        if (r1 == 0) goto L_0x0054;
    L_0x000c:
        r3 = r8.getFullClassName(r9, r0);
        r0 = r8.isInEditMode();	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        if (r0 == 0) goto L_0x0055;
    L_0x0016:
        r0 = r8.getClass();	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r0 = r0.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
    L_0x001e:
        r0 = r0.loadClass(r3);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r1 = android.support.v7.widget.RecyclerView.h.class;
        r4 = r0.asSubclass(r1);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r1 = 0;
        r0 = LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;	 Catch:{ NoSuchMethodException -> 0x005a }
        r2 = r4.getConstructor(r0);	 Catch:{ NoSuchMethodException -> 0x005a }
        r0 = 4;
        r0 = new java.lang.Object[r0];	 Catch:{ NoSuchMethodException -> 0x005a }
        r5 = 0;
        r0[r5] = r9;	 Catch:{ NoSuchMethodException -> 0x005a }
        r5 = 1;
        r0[r5] = r11;	 Catch:{ NoSuchMethodException -> 0x005a }
        r5 = 2;
        r6 = java.lang.Integer.valueOf(r12);	 Catch:{ NoSuchMethodException -> 0x005a }
        r0[r5] = r6;	 Catch:{ NoSuchMethodException -> 0x005a }
        r5 = 3;
        r6 = java.lang.Integer.valueOf(r13);	 Catch:{ NoSuchMethodException -> 0x005a }
        r0[r5] = r6;	 Catch:{ NoSuchMethodException -> 0x005a }
        r1 = r2;
    L_0x0047:
        r2 = 1;
        r1.setAccessible(r2);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r0 = r1.newInstance(r0);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r0 = (android.support.v7.widget.RecyclerView.h) r0;	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r8.setLayoutManager(r0);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
    L_0x0054:
        return;
    L_0x0055:
        r0 = r9.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        goto L_0x001e;
    L_0x005a:
        r0 = move-exception;
        r2 = 0;
        r2 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x0066 }
        r0 = r4.getConstructor(r2);	 Catch:{ NoSuchMethodException -> 0x0066 }
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0047;
    L_0x0066:
        r1 = move-exception;
        r1.initCause(r0);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r0 = new java.lang.IllegalStateException;	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r2.<init>();	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r4 = r11.getPositionDescription();	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r2 = r2.append(r4);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r4 = ": Error creating LayoutManager ";
        r2 = r2.append(r4);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r2 = r2.append(r3);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r2 = r2.toString();	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        r0.<init>(r2, r1);	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x008c, InvocationTargetException -> 0x00af, InstantiationException -> 0x00d2, IllegalAccessException -> 0x00f5, ClassCastException -> 0x0118 }
    L_0x008c:
        r0 = move-exception;
        r1 = new java.lang.IllegalStateException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r11.getPositionDescription();
        r2 = r2.append(r4);
        r4 = ": Unable to find LayoutManager ";
        r2 = r2.append(r4);
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x00af:
        r0 = move-exception;
        r1 = new java.lang.IllegalStateException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r11.getPositionDescription();
        r2 = r2.append(r4);
        r4 = ": Could not instantiate the LayoutManager: ";
        r2 = r2.append(r4);
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x00d2:
        r0 = move-exception;
        r1 = new java.lang.IllegalStateException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r11.getPositionDescription();
        r2 = r2.append(r4);
        r4 = ": Could not instantiate the LayoutManager: ";
        r2 = r2.append(r4);
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x00f5:
        r0 = move-exception;
        r1 = new java.lang.IllegalStateException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r11.getPositionDescription();
        r2 = r2.append(r4);
        r4 = ": Cannot access non-public constructor ";
        r2 = r2.append(r4);
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x0118:
        r0 = move-exception;
        r1 = new java.lang.IllegalStateException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r11.getPositionDescription();
        r2 = r2.append(r4);
        r4 = ": Class is not a LayoutManager ";
        r2 = r2.append(r4);
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
        */
    }

    private String getFullClassName(Context context, String str) {
        if (str.charAt(TOUCH_SLOP_DEFAULT) == '.') {
            return context.getPackageName() + str;
        }
        return !str.contains(".") ? RecyclerView.class.getPackage().getName() + '.' + str : str;
    }

    private void initChildrenHelper() {
        this.mChildHelper = new ac(new be(this));
    }

    void initAdapterManager() {
        this.mAdapterHelper = new o(new bf(this));
    }

    public void setHasFixedSize(boolean z) {
        this.mHasFixedSize = z;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    public void setClipToPadding(boolean z) {
        if (z != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z;
        super.setClipToPadding(z);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        switch (i) {
            case TOUCH_SLOP_DEFAULT:
                this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
            case VERTICAL:
                this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
            default:
                new StringBuilder("setScrollingTouchSlop(): bad argument constant ").append(i).append("; using default value");
                this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        }
    }

    public void swapAdapter(a aVar, boolean z) {
        setLayoutFrozen(FORCE_INVALIDATE_DISPLAY_LIST);
        setAdapterInternal(aVar, true, z);
        setDataSetChangedAfterLayout();
        requestLayout();
    }

    public void setAdapter(a aVar) {
        setLayoutFrozen(FORCE_INVALIDATE_DISPLAY_LIST);
        setAdapterInternal(aVar, FORCE_INVALIDATE_DISPLAY_LIST, true);
        requestLayout();
    }

    private void setAdapterInternal(a aVar, boolean z, boolean z2) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!z || z2) {
            if (this.mItemAnimator != null) {
                this.mItemAnimator.d();
            }
            if (this.mLayout != null) {
                this.mLayout.c(this.mRecycler);
                this.mLayout.b(this.mRecycler);
            }
            this.mRecycler.a();
        }
        this.mAdapterHelper.a();
        a aVar2 = this.mAdapter;
        this.mAdapter = aVar;
        if (aVar != null) {
            aVar.registerAdapterDataObserver(this.mObserver);
            aVar.onAttachedToRecyclerView(this);
        }
        m mVar = this.mRecycler;
        a aVar3 = this.mAdapter;
        mVar.a();
        l c = mVar.c();
        if (aVar2 != null) {
            c.b();
        }
        if (!z && c.c == 0) {
            c.a.clear();
        }
        if (aVar3 != null) {
            c.a();
        }
        this.mState.f = true;
        markKnownViewsInvalid();
    }

    public a getAdapter() {
        return this.mAdapter;
    }

    public void setRecyclerListener(n nVar) {
        this.mRecyclerListener = nVar;
    }

    public int getBaseline() {
        return this.mLayout != null ? NO_POSITION : super.getBaseline();
    }

    public void addOnChildAttachStateChangeListener(i iVar) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(iVar);
    }

    public void removeOnChildAttachStateChangeListener(i iVar) {
        if (this.mOnChildAttachStateListeners != null) {
            this.mOnChildAttachStateListeners.remove(iVar);
        }
    }

    public void clearOnChildAttachStateChangeListeners() {
        if (this.mOnChildAttachStateListeners != null) {
            this.mOnChildAttachStateListeners.clear();
        }
    }

    public void setLayoutManager(h hVar) {
        if (hVar != this.mLayout) {
            stopScroll();
            if (this.mLayout != null) {
                if (this.mIsAttached) {
                    this.mLayout.b(this, this.mRecycler);
                }
                this.mLayout.a(null);
            }
            this.mRecycler.a();
            ac acVar = this.mChildHelper;
            a aVar = acVar.b;
            while (true) {
                aVar.a = 0;
                if (aVar.b == null) {
                    break;
                }
                aVar = aVar.b;
            }
            for (int size = acVar.c.size() - 1; size >= 0; size--) {
                acVar.a.d((View) acVar.c.get(size));
                acVar.c.remove(size);
            }
            acVar.a.b();
            this.mLayout = hVar;
            if (hVar != null) {
                if (hVar.q != null) {
                    throw new IllegalArgumentException(new StringBuilder("LayoutManager ").append(hVar).append(" is already attached to a RecyclerView: ").append(hVar.q).toString());
                }
                this.mLayout.a(this);
                if (this.mIsAttached) {
                    this.mLayout.t = true;
                }
            }
            requestLayout();
        }
    }

    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.mPendingSavedState != null) {
            savedState.a = this.mPendingSavedState.a;
        } else if (this.mLayout != null) {
            savedState.a = this.mLayout.d();
        } else {
            savedState.a = null;
        }
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            super.onRestoreInstanceState(this.mPendingSavedState.getSuperState());
            if (this.mLayout != null && this.mPendingSavedState.a != null) {
                this.mLayout.a(this.mPendingSavedState.a);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    private void addAnimatingView(t tVar) {
        View view = tVar.itemView;
        if (view.getParent() == this) {
            boolean z = true;
        } else {
            Object obj = null;
        }
        this.mRecycler.b(getChildViewHolder(view));
        if (tVar.isTmpDetached()) {
            this.mChildHelper.a(view, NO_POSITION, view.getLayoutParams(), true);
        } else if (z) {
            ac acVar = this.mChildHelper;
            int a = acVar.a.a(view);
            if (a < 0) {
                throw new IllegalArgumentException(new StringBuilder("view is not a child, cannot hide ").append(view).toString());
            }
            acVar.b.a(a);
            acVar.a(view);
        } else {
            this.mChildHelper.a(view, NO_POSITION, true);
        }
    }

    private boolean removeAnimatingView(View view) {
        boolean z;
        boolean z2 = true;
        eatRequestLayout();
        ac acVar = this.mChildHelper;
        int a = acVar.a.a(view);
        if (a == -1) {
            acVar.b(view);
            z = true;
        } else if (acVar.b.c(a)) {
            acVar.b.d(a);
            acVar.b(view);
            acVar.a.a(a);
            z = true;
        } else {
            z = false;
        }
        if (z) {
            t childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.b(childViewHolderInt);
            this.mRecycler.a(childViewHolderInt);
        }
        if (z) {
            z2 = false;
        }
        resumeRequestLayout(z2);
        return z;
    }

    public h getLayoutManager() {
        return this.mLayout;
    }

    public l getRecycledViewPool() {
        return this.mRecycler.c();
    }

    public void setRecycledViewPool(l lVar) {
        m mVar = this.mRecycler;
        if (mVar.f != null) {
            mVar.f.b();
        }
        mVar.f = lVar;
        if (lVar != null) {
            l lVar2 = mVar.f;
            mVar.h.getAdapter();
            lVar2.a();
        }
    }

    public void setViewCacheExtension(r rVar) {
        this.mRecycler.g = rVar;
    }

    public void setItemViewCacheSize(int i) {
        m mVar = this.mRecycler;
        mVar.e = i;
        for (int size = mVar.c.size() - 1; size >= 0 && mVar.c.size() > i; size--) {
            mVar.c(size);
        }
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    private void setScrollState(int i) {
        if (i != this.mScrollState) {
            this.mScrollState = i;
            if (i != 2) {
                stopScrollersInternal();
            }
            dispatchOnScrollStateChanged(i);
        }
    }

    public void addItemDecoration(g gVar, int i) {
        if (this.mLayout != null) {
            this.mLayout.a("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(FORCE_INVALIDATE_DISPLAY_LIST);
        }
        if (i < 0) {
            this.mItemDecorations.add(gVar);
        } else {
            this.mItemDecorations.add(i, gVar);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addItemDecoration(g gVar) {
        addItemDecoration(gVar, NO_POSITION);
    }

    public void removeItemDecoration(g gVar) {
        if (this.mLayout != null) {
            this.mLayout.a("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(gVar);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(ViewCompat.getOverScrollMode(this) == 2 ? true : FORCE_INVALIDATE_DISPLAY_LIST);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void setChildDrawingOrderCallback(d dVar) {
        if (dVar != this.mChildDrawingOrderCallback) {
            this.mChildDrawingOrderCallback = dVar;
            setChildrenDrawingOrderEnabled(this.mChildDrawingOrderCallback != null ? true : FORCE_INVALIDATE_DISPLAY_LIST);
        }
    }

    @Deprecated
    public void setOnScrollListener(k kVar) {
        this.mScrollListener = kVar;
    }

    public void addOnScrollListener(k kVar) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(kVar);
    }

    public void removeOnScrollListener(k kVar) {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.remove(kVar);
        }
    }

    public void clearOnScrollListeners() {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.clear();
        }
    }

    public void scrollToPosition(int i) {
        if (!this.mLayoutFrozen) {
            stopScroll();
            if (this.mLayout != null) {
                this.mLayout.c(i);
                awakenScrollBars();
            }
        }
    }

    private void jumpToPositionForSmoothScroller(int i) {
        if (this.mLayout != null) {
            this.mLayout.c(i);
            awakenScrollBars();
        }
    }

    public void smoothScrollToPosition(int i) {
        if (!this.mLayoutFrozen && this.mLayout != null) {
            this.mLayout.a(this, i);
        }
    }

    public void scrollTo(int i, int i2) {
    }

    public void scrollBy(int i, int i2) {
        if (this.mLayout != null && !this.mLayoutFrozen) {
            boolean e = this.mLayout.e();
            boolean f = this.mLayout.f();
            if (e || f) {
                if (!e) {
                    i = 0;
                }
                if (!f) {
                    i2 = 0;
                }
                scrollByInternal(i, i2, null);
            }
        }
    }

    private void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete) {
            return;
        }
        if (this.mDataSetHasChangedAfterLayout) {
            TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
            dispatchLayout();
            TraceCompat.endSection();
        } else if (!this.mAdapterHelper.d()) {
        } else {
            if (this.mAdapterHelper.a((int) XZBDevice.DOWNLOAD_LIST_ALL) && !this.mAdapterHelper.a((int) XZBDevice.Success)) {
                TraceCompat.beginSection(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
                eatRequestLayout();
                this.mAdapterHelper.b();
                if (!this.mLayoutRequestEaten) {
                    if (hasUpdatedView()) {
                        dispatchLayout();
                    } else {
                        this.mAdapterHelper.c();
                    }
                }
                resumeRequestLayout(true);
                TraceCompat.endSection();
            } else if (this.mAdapterHelper.d()) {
                TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
                dispatchLayout();
                TraceCompat.endSection();
            }
        }
    }

    private boolean hasUpdatedView() {
        int a = this.mChildHelper.a();
        for (int i = 0; i < a; i++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.b(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                return true;
            }
        }
        return FORCE_INVALIDATE_DISPLAY_LIST;
    }

    boolean scrollByInternal(int i, int i2, MotionEvent motionEvent) {
        int a;
        int i3;
        int i4;
        int i5;
        consumePendingUpdateOperations();
        if (this.mAdapter != null) {
            int b;
            eatRequestLayout();
            onEnterLayoutOrScroll();
            TraceCompat.beginSection(TRACE_SCROLL_TAG);
            if (i != 0) {
                a = this.mLayout.a(i, this.mRecycler, this.mState);
                i3 = i - a;
            } else {
                a = 0;
                i3 = 0;
            }
            if (i2 != 0) {
                b = this.mLayout.b(i2, this.mRecycler, this.mState);
                i4 = i2 - b;
            } else {
                b = 0;
                i4 = 0;
            }
            TraceCompat.endSection();
            repositionShadowingViews();
            onExitLayoutOrScroll();
            resumeRequestLayout(FORCE_INVALIDATE_DISPLAY_LIST);
            i5 = i4;
            i4 = a;
            a = b;
        } else {
            a = 0;
            i4 = 0;
            i5 = 0;
            i3 = 0;
        }
        if (!this.mItemDecorations.isEmpty()) {
            invalidate();
        }
        if (dispatchNestedScroll(i4, a, i3, i5, this.mScrollOffset)) {
            this.mLastTouchX -= this.mScrollOffset[0];
            this.mLastTouchY -= this.mScrollOffset[1];
            if (motionEvent != null) {
                motionEvent.offsetLocation((float) this.mScrollOffset[0], (float) this.mScrollOffset[1]);
            }
            int[] iArr = this.mNestedOffsets;
            iArr[0] = iArr[0] + this.mScrollOffset[0];
            iArr = this.mNestedOffsets;
            iArr[1] = iArr[1] + this.mScrollOffset[1];
        } else if (ViewCompat.getOverScrollMode(this) != 2) {
            if (motionEvent != null) {
                pullGlows(motionEvent.getX(), (float) i3, motionEvent.getY(), (float) i5);
            }
            considerReleasingGlowsOnScroll(i, i2);
        }
        if (!(i4 == 0 && a == 0)) {
            dispatchOnScrolled(i4, a);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (i4 == 0 && a == 0) ? FORCE_INVALIDATE_DISPLAY_LIST : true;
    }

    public int computeHorizontalScrollOffset() {
        return (this.mLayout != null && this.mLayout.e()) ? this.mLayout.a(this.mState) : TOUCH_SLOP_DEFAULT;
    }

    public int computeHorizontalScrollExtent() {
        return (this.mLayout != null && this.mLayout.e()) ? this.mLayout.c(this.mState) : TOUCH_SLOP_DEFAULT;
    }

    public int computeHorizontalScrollRange() {
        return (this.mLayout != null && this.mLayout.e()) ? this.mLayout.e(this.mState) : TOUCH_SLOP_DEFAULT;
    }

    public int computeVerticalScrollOffset() {
        return (this.mLayout != null && this.mLayout.f()) ? this.mLayout.b(this.mState) : TOUCH_SLOP_DEFAULT;
    }

    public int computeVerticalScrollExtent() {
        return (this.mLayout != null && this.mLayout.f()) ? this.mLayout.d(this.mState) : TOUCH_SLOP_DEFAULT;
    }

    public int computeVerticalScrollRange() {
        return (this.mLayout != null && this.mLayout.f()) ? this.mLayout.f(this.mState) : TOUCH_SLOP_DEFAULT;
    }

    void eatRequestLayout() {
        this.mEatRequestLayout++;
        if (this.mEatRequestLayout == 1 && !this.mLayoutFrozen) {
            this.mLayoutRequestEaten = false;
        }
    }

    void resumeRequestLayout(boolean z) {
        if (this.mEatRequestLayout <= 0) {
            this.mEatRequestLayout = 1;
        }
        if (!z) {
            this.mLayoutRequestEaten = false;
        }
        if (this.mEatRequestLayout == 1) {
            if (!(!z || !this.mLayoutRequestEaten || this.mLayoutFrozen || this.mLayout == null || this.mAdapter == null)) {
                dispatchLayout();
            }
            if (!this.mLayoutFrozen) {
                this.mLayoutRequestEaten = false;
            }
        }
        this.mEatRequestLayout--;
    }

    public void setLayoutFrozen(boolean z) {
        if (z != this.mLayoutFrozen) {
            assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, XZBDevice.DOWNLOAD_LIST_FAILED, AutoScrollHelper.RELATIVE_UNSPECIFIED, 0.0f, TOUCH_SLOP_DEFAULT));
                this.mLayoutFrozen = true;
                this.mIgnoreMotionEventTillDown = true;
                stopScroll();
                return;
            }
            this.mLayoutFrozen = false;
            if (!(!this.mLayoutRequestEaten || this.mLayout == null || this.mAdapter == null)) {
                requestLayout();
            }
            this.mLayoutRequestEaten = false;
        }
    }

    public boolean isLayoutFrozen() {
        return this.mLayoutFrozen;
    }

    public void smoothScrollBy(int i, int i2) {
        int i3 = TOUCH_SLOP_DEFAULT;
        if (this.mLayout != null && !this.mLayoutFrozen) {
            if (!this.mLayout.e()) {
                i = 0;
            }
            if (this.mLayout.f()) {
                i3 = i2;
            }
            if (i != 0 || i3 != 0) {
                this.mViewFlinger.a(i, i3);
            }
        }
    }

    public boolean fling(int i, int i2) {
        if (this.mLayout == null || this.mLayoutFrozen) {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }
        boolean e = this.mLayout.e();
        boolean f = this.mLayout.f();
        if (!e || Math.abs(i) < this.mMinFlingVelocity) {
            i = 0;
        }
        if (!f || Math.abs(i2) < this.mMinFlingVelocity) {
            i2 = 0;
        }
        if ((i == 0 && i2 == 0) || dispatchNestedPreFling((float) i, (float) i2)) {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }
        e = e || f;
        dispatchNestedFling((float) i, (float) i2, e);
        if (!e) {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }
        int max = Math.max(-this.mMaxFlingVelocity, Math.min(i, this.mMaxFlingVelocity));
        int max2 = Math.max(-this.mMaxFlingVelocity, Math.min(i2, this.mMaxFlingVelocity));
        s sVar = this.mViewFlinger;
        sVar.d.setScrollState(SCROLL_STATE_SETTLING);
        sVar.b = 0;
        sVar.a = 0;
        sVar.c.fling(TOUCH_SLOP_DEFAULT, 0, max, max2, ExploreByTouchHelper.INVALID_ID, InMobiClientPositioning.NO_REPEAT, Integer.MIN_VALUE, Integer.MAX_VALUE);
        sVar.a();
        return true;
    }

    public void stopScroll() {
        setScrollState(TOUCH_SLOP_DEFAULT);
        stopScrollersInternal();
    }

    private void stopScrollersInternal() {
        this.mViewFlinger.b();
        if (this.mLayout != null) {
            this.mLayout.t();
        }
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void pullGlows(float r8, float r9, float r10, float r11) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.RecyclerView.pullGlows(float, float, float, float):void");
        /*
        this = this;
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r0 = 1;
        r5 = 0;
        r1 = 0;
        r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r2 >= 0) goto L_0x0050;
    L_0x0009:
        r7.ensureLeftGlow();
        r2 = r7.mLeftGlow;
        r3 = -r9;
        r4 = r7.getWidth();
        r4 = (float) r4;
        r3 = r3 / r4;
        r4 = r7.getHeight();
        r4 = (float) r4;
        r4 = r10 / r4;
        r4 = r6 - r4;
        r2 = r2.onPull(r3, r4);
        if (r2 == 0) goto L_0x0025;
    L_0x0024:
        r1 = r0;
    L_0x0025:
        r2 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r2 >= 0) goto L_0x006f;
    L_0x0029:
        r7.ensureTopGlow();
        r2 = r7.mTopGlow;
        r3 = -r11;
        r4 = r7.getHeight();
        r4 = (float) r4;
        r3 = r3 / r4;
        r4 = r7.getWidth();
        r4 = (float) r4;
        r4 = r8 / r4;
        r2 = r2.onPull(r3, r4);
        if (r2 == 0) goto L_0x008e;
    L_0x0042:
        if (r0 != 0) goto L_0x004c;
    L_0x0044:
        r0 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r0 != 0) goto L_0x004c;
    L_0x0048:
        r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r0 == 0) goto L_0x004f;
    L_0x004c:
        android.support.v4.view.ViewCompat.postInvalidateOnAnimation(r7);
    L_0x004f:
        return;
    L_0x0050:
        r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r2 <= 0) goto L_0x0025;
    L_0x0054:
        r7.ensureRightGlow();
        r2 = r7.mRightGlow;
        r3 = r7.getWidth();
        r3 = (float) r3;
        r3 = r9 / r3;
        r4 = r7.getHeight();
        r4 = (float) r4;
        r4 = r10 / r4;
        r2 = r2.onPull(r3, r4);
        if (r2 == 0) goto L_0x0025;
    L_0x006d:
        r1 = r0;
        goto L_0x0025;
    L_0x006f:
        r2 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r2 <= 0) goto L_0x008e;
    L_0x0073:
        r7.ensureBottomGlow();
        r2 = r7.mBottomGlow;
        r3 = r7.getHeight();
        r3 = (float) r3;
        r3 = r11 / r3;
        r4 = r7.getWidth();
        r4 = (float) r4;
        r4 = r8 / r4;
        r4 = r6 - r4;
        r2 = r2.onPull(r3, r4);
        if (r2 != 0) goto L_0x0042;
    L_0x008e:
        r0 = r1;
        goto L_0x0042;
        */
    }

    private void releaseGlows() {
        int i = TOUCH_SLOP_DEFAULT;
        if (this.mLeftGlow != null) {
            i = this.mLeftGlow.onRelease();
        }
        if (this.mTopGlow != null) {
            i |= this.mTopGlow.onRelease();
        }
        if (this.mRightGlow != null) {
            i |= this.mRightGlow.onRelease();
        }
        if (this.mBottomGlow != null) {
            i |= this.mBottomGlow.onRelease();
        }
        if (i != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void considerReleasingGlowsOnScroll(int i, int i2) {
        int i3 = TOUCH_SLOP_DEFAULT;
        if (!(this.mLeftGlow == null || this.mLeftGlow.isFinished() || i <= 0)) {
            i3 = this.mLeftGlow.onRelease();
        }
        if (!(this.mRightGlow == null || this.mRightGlow.isFinished() || i >= 0)) {
            i3 |= this.mRightGlow.onRelease();
        }
        if (!(this.mTopGlow == null || this.mTopGlow.isFinished() || i2 <= 0)) {
            i3 |= this.mTopGlow.onRelease();
        }
        if (!(this.mBottomGlow == null || this.mBottomGlow.isFinished() || i2 >= 0)) {
            i3 |= this.mBottomGlow.onRelease();
        }
        if (i3 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void absorbGlows(int i, int i2) {
        if (i < 0) {
            ensureLeftGlow();
            this.mLeftGlow.onAbsorb(-i);
        } else if (i > 0) {
            ensureRightGlow();
            this.mRightGlow.onAbsorb(i);
        }
        if (i2 < 0) {
            ensureTopGlow();
            this.mTopGlow.onAbsorb(-i2);
        } else if (i2 > 0) {
            ensureBottomGlow();
            this.mBottomGlow.onAbsorb(i2);
        }
        if (i != 0 || i2 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void ensureLeftGlow() {
        if (this.mLeftGlow == null) {
            this.mLeftGlow = new EdgeEffectCompat(getContext());
            if (this.mClipToPadding) {
                this.mLeftGlow.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    void ensureRightGlow() {
        if (this.mRightGlow == null) {
            this.mRightGlow = new EdgeEffectCompat(getContext());
            if (this.mClipToPadding) {
                this.mRightGlow.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    void ensureTopGlow() {
        if (this.mTopGlow == null) {
            this.mTopGlow = new EdgeEffectCompat(getContext());
            if (this.mClipToPadding) {
                this.mTopGlow.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    void ensureBottomGlow() {
        if (this.mBottomGlow == null) {
            this.mBottomGlow = new EdgeEffectCompat(getContext());
            if (this.mClipToPadding) {
                this.mBottomGlow.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public View focusSearch(View view, int i) {
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (!(findNextFocus != null || this.mAdapter == null || this.mLayout == null || isComputingLayout() || this.mLayoutFrozen)) {
            eatRequestLayout();
            findNextFocus = this.mLayout.a(view, i, this.mRecycler, this.mState);
            resumeRequestLayout(FORCE_INVALIDATE_DISPLAY_LIST);
        }
        return findNextFocus != null ? findNextFocus : super.focusSearch(view, i);
    }

    public void requestChildFocus(View view, View view2) {
        int i;
        if (this.mLayout.m() || isComputingLayout()) {
            i = 1;
        } else {
            i = 0;
        }
        if (i == 0 && view2 != null) {
            this.mTempRect.set(TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT, view2.getWidth(), view2.getHeight());
            android.view.ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                if (!layoutParams2.e) {
                    Rect rect = layoutParams2.d;
                    Rect rect2 = this.mTempRect;
                    rect2.left -= rect.left;
                    rect2 = this.mTempRect;
                    rect2.right += rect.right;
                    rect2 = this.mTempRect;
                    rect2.top -= rect.top;
                    rect2 = this.mTempRect;
                    rect.bottom += rect2.bottom;
                }
            }
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
            requestChildRectangleOnScreen(view, this.mTempRect, !this.mFirstLayoutComplete);
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        h hVar = this.mLayout;
        int o = hVar.o();
        int p = hVar.p();
        int q = hVar.y - hVar.q();
        int r = hVar.z - hVar.r();
        int left = (view.getLeft() + rect.left) - view.getScrollX();
        int top = (view.getTop() + rect.top) - view.getScrollY();
        int width = left + rect.width();
        int height = top + rect.height();
        int min = Math.min(TOUCH_SLOP_DEFAULT, left - o);
        int min2 = Math.min(TOUCH_SLOP_DEFAULT, top - p);
        int max = Math.max(TOUCH_SLOP_DEFAULT, width - q);
        r = Math.max(TOUCH_SLOP_DEFAULT, height - r);
        if (ViewCompat.getLayoutDirection(hVar.q) == 1) {
            if (max == 0) {
                max = Math.max(min, width - q);
            }
            min = max;
        } else {
            min = min != 0 ? min : Math.min(left - o, max);
        }
        max = min2 != 0 ? min2 : Math.min(top - p, r);
        if (min == 0 && max == 0) {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }
        if (z) {
            scrollBy(min, max);
        } else {
            smoothScrollBy(min, max);
        }
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        super.addFocusables(arrayList, i, i2);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = true;
        this.mFirstLayoutComplete = false;
        if (this.mLayout != null) {
            this.mLayout.t = true;
        }
        this.mPostedAnimatorRunner = false;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mItemAnimator != null) {
            this.mItemAnimator.d();
        }
        this.mFirstLayoutComplete = false;
        stopScroll();
        this.mIsAttached = false;
        if (this.mLayout != null) {
            this.mLayout.b(this, this.mRecycler);
        }
        removeCallbacks(this.mItemAnimatorRunner);
        a.b();
    }

    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    void assertInLayoutOrScroll(String str) {
        if (!isComputingLayout()) {
            if (str == null) {
                throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling");
            }
            throw new IllegalStateException(str);
        }
    }

    void assertNotInLayoutOrScroll(String str) {
        if (!isComputingLayout()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(str);
    }

    public void addOnItemTouchListener(j jVar) {
        this.mOnItemTouchListeners.add(jVar);
    }

    public void removeOnItemTouchListener(j jVar) {
        this.mOnItemTouchListeners.remove(jVar);
        if (this.mActiveOnItemTouchListener == jVar) {
            this.mActiveOnItemTouchListener = null;
        }
    }

    private boolean dispatchOnItemTouchIntercept(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.mActiveOnItemTouchListener = null;
        }
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            j jVar = (j) this.mOnItemTouchListeners.get(i);
            if (jVar.a(this, motionEvent) && action != 3) {
                this.mActiveOnItemTouchListener = jVar;
                return true;
            }
        }
        return false;
    }

    private boolean dispatchOnItemTouch(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (this.mActiveOnItemTouchListener != null) {
            if (action == 0) {
                this.mActiveOnItemTouchListener = null;
            } else {
                if (action == 3 || action == 1) {
                    this.mActiveOnItemTouchListener = null;
                }
                return true;
            }
        }
        if (action != 0) {
            int size = this.mOnItemTouchListeners.size();
            for (int i = 0; i < size; i++) {
                j jVar = (j) this.mOnItemTouchListeners.get(i);
                if (jVar.a(this, motionEvent)) {
                    this.mActiveOnItemTouchListener = jVar;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i = NO_POSITION;
        if (this.mLayoutFrozen) {
            return false;
        }
        if (dispatchOnItemTouchIntercept(motionEvent)) {
            cancelTouch();
            return true;
        } else if (this.mLayout == null) {
            return false;
        } else {
            boolean e = this.mLayout.e();
            boolean f = this.mLayout.f();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
            int i2;
            switch (actionMasked) {
                case TOUCH_SLOP_DEFAULT:
                    if (this.mIgnoreMotionEventTillDown) {
                        this.mIgnoreMotionEventTillDown = false;
                    }
                    this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, TOUCH_SLOP_DEFAULT);
                    i = (int) (motionEvent.getX() + 0.5f);
                    this.mLastTouchX = i;
                    this.mInitialTouchX = i;
                    i = (int) (motionEvent.getY() + 0.5f);
                    this.mLastTouchY = i;
                    this.mInitialTouchY = i;
                    if (this.mScrollState == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        setScrollState(VERTICAL);
                    }
                    int[] iArr = this.mNestedOffsets;
                    this.mNestedOffsets[1] = 0;
                    iArr[0] = 0;
                    if (e) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    if (f) {
                        i2 |= 2;
                    }
                    startNestedScroll(i2);
                    break;
                case VERTICAL:
                    this.mVelocityTracker.clear();
                    stopNestedScroll();
                    break;
                case SCROLL_STATE_SETTLING:
                    actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.mScrollPointerId);
                    if (actionMasked < 0) {
                        new StringBuilder("Error processing scroll; pointer index for id ").append(this.mScrollPointerId).append(" not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    actionIndex = (int) (MotionEventCompat.getX(motionEvent, actionMasked) + 0.5f);
                    actionMasked = (int) (MotionEventCompat.getY(motionEvent, actionMasked) + 0.5f);
                    if (this.mScrollState != 1) {
                        actionIndex -= this.mInitialTouchX;
                        actionMasked -= this.mInitialTouchY;
                        if (!e || Math.abs(actionIndex) <= this.mTouchSlop) {
                            e = false;
                        } else {
                            this.mLastTouchX = ((actionIndex < 0 ? -1 : 1) * this.mTouchSlop) + this.mInitialTouchX;
                            e = true;
                        }
                        if (f && Math.abs(actionMasked) > this.mTouchSlop) {
                            i2 = this.mInitialTouchY;
                            int i3 = this.mTouchSlop;
                            if (actionMasked >= 0) {
                                i = 1;
                            }
                            this.mLastTouchY = i2 + (i * i3);
                            e = true;
                        }
                        if (e) {
                            setScrollState(VERTICAL);
                        }
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    cancelTouch();
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    i2 = (int) (MotionEventCompat.getX(motionEvent, actionIndex) + 0.5f);
                    this.mLastTouchX = i2;
                    this.mInitialTouchX = i2;
                    i2 = (int) (MotionEventCompat.getY(motionEvent, actionIndex) + 0.5f);
                    this.mLastTouchY = i2;
                    this.mInitialTouchY = i2;
                    break;
                case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd:
                    onPointerUp(motionEvent);
                    break;
            }
            return this.mScrollState == 1;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.mOnItemTouchListeners.size();
        for (int i = TOUCH_SLOP_DEFAULT; i < size; i++) {
            this.mOnItemTouchListeners.get(i);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = FORCE_INVALIDATE_DISPLAY_LIST;
        if (this.mLayoutFrozen || this.mIgnoreMotionEventTillDown) {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }
        if (dispatchOnItemTouch(motionEvent)) {
            cancelTouch();
            return true;
        } else if (this.mLayout == null) {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        } else {
            boolean e = this.mLayout.e();
            boolean f = this.mLayout.f();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
            if (actionMasked == 0) {
                int[] iArr = this.mNestedOffsets;
                this.mNestedOffsets[1] = 0;
                iArr[0] = 0;
            }
            obtain.offsetLocation((float) this.mNestedOffsets[0], (float) this.mNestedOffsets[1]);
            switch (actionMasked) {
                case TOUCH_SLOP_DEFAULT:
                    this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, TOUCH_SLOP_DEFAULT);
                    actionMasked = (int) (motionEvent.getX() + 0.5f);
                    this.mLastTouchX = actionMasked;
                    this.mInitialTouchX = actionMasked;
                    actionMasked = (int) (motionEvent.getY() + 0.5f);
                    this.mLastTouchY = actionMasked;
                    this.mInitialTouchY = actionMasked;
                    if (e) {
                        actionMasked = 1;
                    } else {
                        actionMasked = 0;
                    }
                    if (f) {
                        actionMasked |= 2;
                    }
                    startNestedScroll(actionMasked);
                    break;
                case VERTICAL:
                    this.mVelocityTracker.addMovement(obtain);
                    this.mVelocityTracker.computeCurrentVelocity(IHost.HOST_NOFITY_REFRESH_LIST, (float) this.mMaxFlingVelocity);
                    float f2 = e ? -VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mScrollPointerId) : 0.0f;
                    float f3;
                    if (f) {
                        f3 = -VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mScrollPointerId);
                    } else {
                        f3 = 0.0f;
                    }
                    if ((f2 == 0.0f && r0 == 0.0f) || !fling((int) f2, (int) r0)) {
                        setScrollState(TOUCH_SLOP_DEFAULT);
                    }
                    resetTouch();
                    z = true;
                    break;
                case SCROLL_STATE_SETTLING:
                    actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.mScrollPointerId);
                    if (actionMasked < 0) {
                        new StringBuilder("Error processing scroll; pointer index for id ").append(this.mScrollPointerId).append(" not found. Did any MotionEvents get skipped?");
                        return FORCE_INVALIDATE_DISPLAY_LIST;
                    }
                    int x = (int) (MotionEventCompat.getX(motionEvent, actionMasked) + 0.5f);
                    int y = (int) (MotionEventCompat.getY(motionEvent, actionMasked) + 0.5f);
                    int i = this.mLastTouchX - x;
                    actionMasked = this.mLastTouchY - y;
                    if (dispatchNestedPreScroll(i, actionMasked, this.mScrollConsumed, this.mScrollOffset)) {
                        i -= this.mScrollConsumed[0];
                        actionMasked -= this.mScrollConsumed[1];
                        obtain.offsetLocation((float) this.mScrollOffset[0], (float) this.mScrollOffset[1]);
                        int[] iArr2 = this.mNestedOffsets;
                        iArr2[0] = iArr2[0] + this.mScrollOffset[0];
                        iArr2 = this.mNestedOffsets;
                        iArr2[1] = iArr2[1] + this.mScrollOffset[1];
                    }
                    if (this.mScrollState != 1) {
                        boolean z2;
                        if (!e || Math.abs(i) <= this.mTouchSlop) {
                            z2 = false;
                        } else {
                            if (i > 0) {
                                i -= this.mTouchSlop;
                            } else {
                                i += this.mTouchSlop;
                            }
                            z2 = true;
                        }
                        if (f && Math.abs(actionMasked) > this.mTouchSlop) {
                            if (actionMasked > 0) {
                                actionMasked -= this.mTouchSlop;
                            } else {
                                actionMasked += this.mTouchSlop;
                            }
                            z2 = true;
                        }
                        if (z2) {
                            setScrollState(VERTICAL);
                        }
                    }
                    if (this.mScrollState == 1) {
                        this.mLastTouchX = x - this.mScrollOffset[0];
                        this.mLastTouchY = y - this.mScrollOffset[1];
                        if (!e) {
                            i = 0;
                        }
                        if (!f) {
                            actionMasked = 0;
                        }
                        if (scrollByInternal(i, actionMasked, obtain)) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    cancelTouch();
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    actionMasked = (int) (MotionEventCompat.getX(motionEvent, actionIndex) + 0.5f);
                    this.mLastTouchX = actionMasked;
                    this.mInitialTouchX = actionMasked;
                    actionMasked = (int) (MotionEventCompat.getY(motionEvent, actionIndex) + 0.5f);
                    this.mLastTouchY = actionMasked;
                    this.mInitialTouchY = actionMasked;
                    break;
                case com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd:
                    onPointerUp(motionEvent);
                    break;
            }
            if (!z) {
                this.mVelocityTracker.addMovement(obtain);
            }
            obtain.recycle();
            return true;
        }
    }

    private void resetTouch() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.clear();
        }
        stopNestedScroll();
        releaseGlows();
    }

    private void cancelTouch() {
        resetTouch();
        setScrollState(TOUCH_SLOP_DEFAULT);
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mScrollPointerId) {
            actionIndex = actionIndex == 0 ? VERTICAL : TOUCH_SLOP_DEFAULT;
            this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
            int x = (int) (MotionEventCompat.getX(motionEvent, actionIndex) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            actionIndex = (int) (MotionEventCompat.getY(motionEvent, actionIndex) + 0.5f);
            this.mLastTouchY = actionIndex;
            this.mInitialTouchY = actionIndex;
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (!(this.mLayout == null || this.mLayoutFrozen || (MotionEventCompat.getSource(motionEvent) & 2) == 0 || motionEvent.getAction() != 8)) {
            float f;
            float axisValue;
            if (this.mLayout.f()) {
                f = -MotionEventCompat.getAxisValue(motionEvent, XZBDevice.Pause);
            } else {
                f = 0.0f;
            }
            if (this.mLayout.e()) {
                axisValue = MotionEventCompat.getAxisValue(motionEvent, XZBDevice.Stop);
            } else {
                axisValue = 0.0f;
            }
            if (!(f == 0.0f && axisValue == 0.0f)) {
                float scrollFactor = getScrollFactor();
                scrollByInternal((int) (axisValue * scrollFactor), (int) (f * scrollFactor), motionEvent);
            }
        }
        return FORCE_INVALIDATE_DISPLAY_LIST;
    }

    private float getScrollFactor() {
        if (this.mScrollFactor == Float.MIN_VALUE) {
            TypedValue typedValue = new TypedValue();
            if (!getContext().getTheme().resolveAttribute(16842829, typedValue, true)) {
                return AutoScrollHelper.RELATIVE_UNSPECIFIED;
            }
            this.mScrollFactor = typedValue.getDimension(getContext().getResources().getDisplayMetrics());
        }
        return this.mScrollFactor;
    }

    protected void onMeasure(int i, int i2) {
        boolean z = FORCE_INVALIDATE_DISPLAY_LIST;
        if (this.mLayout == null) {
            defaultOnMeasure(i, i2);
        } else if (this.mLayout.u) {
            int mode = MeasureSpec.getMode(i);
            int mode2 = MeasureSpec.getMode(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.mLayout.h(i, i2);
            if (!z && this.mAdapter != null) {
                if (this.mState.b == 1) {
                    dispatchLayoutStep1();
                }
                this.mLayout.f(i, i2);
                this.mState.k = true;
                dispatchLayoutStep2();
                this.mLayout.g(i, i2);
                if (this.mLayout.i()) {
                    this.mLayout.f(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.mState.k = true;
                    dispatchLayoutStep2();
                    this.mLayout.g(i, i2);
                }
            }
        } else if (this.mHasFixedSize) {
            this.mLayout.h(i, i2);
        } else {
            if (this.mAdapterUpdateDuringMeasure) {
                eatRequestLayout();
                processAdapterUpdatesAndSetAnimationFlags();
                if (this.mState.i) {
                    this.mState.g = true;
                } else {
                    this.mAdapterHelper.e();
                    this.mState.g = false;
                }
                this.mAdapterUpdateDuringMeasure = false;
                resumeRequestLayout(FORCE_INVALIDATE_DISPLAY_LIST);
            }
            if (this.mAdapter != null) {
                this.mState.c = this.mAdapter.getItemCount();
            } else {
                this.mState.c = 0;
            }
            eatRequestLayout();
            this.mLayout.h(i, i2);
            resumeRequestLayout(FORCE_INVALIDATE_DISPLAY_LIST);
            this.mState.g = false;
        }
    }

    void defaultOnMeasure(int i, int i2) {
        setMeasuredDimension(h.a(i, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), h.a(i2, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            invalidateGlows();
        }
    }

    public void setItemAnimator(e eVar) {
        if (this.mItemAnimator != null) {
            this.mItemAnimator.d();
            this.mItemAnimator.h = null;
        }
        this.mItemAnimator = eVar;
        if (this.mItemAnimator != null) {
            this.mItemAnimator.h = this.mItemAnimatorListener;
        }
    }

    private void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    private void onExitLayoutOrScroll() {
        this.mLayoutOrScrollCounter--;
        if (this.mLayoutOrScrollCounter <= 0) {
            this.mLayoutOrScrollCounter = 0;
            dispatchContentChangedIfNecessary();
        }
    }

    boolean isAccessibilityEnabled() {
        return (this.mAccessibilityManager == null || !this.mAccessibilityManager.isEnabled()) ? FORCE_INVALIDATE_DISPLAY_LIST : true;
    }

    private void dispatchContentChangedIfNecessary() {
        int i = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (i != 0 && isAccessibilityEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(Message.FLAG_RET);
            AccessibilityEventCompat.setContentChangeTypes(obtain, i);
            sendAccessibilityEventUnchecked(obtain);
        }
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0 ? true : FORCE_INVALIDATE_DISPLAY_LIST;
    }

    boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int i = TOUCH_SLOP_DEFAULT;
        if (!isComputingLayout()) {
            return FORCE_INVALIDATE_DISPLAY_LIST;
        }
        int contentChangeTypes;
        if (accessibilityEvent != null) {
            contentChangeTypes = AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent);
        } else {
            contentChangeTypes = 0;
        }
        if (contentChangeTypes != 0) {
            i = contentChangeTypes;
        }
        this.mEatenAccessibilityChangeFlags = i | this.mEatenAccessibilityChangeFlags;
        return true;
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!shouldDeferAccessibilityEvent(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public e getItemAnimator() {
        return this.mItemAnimator;
    }

    private void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }

    private boolean predictiveItemAnimationsEnabled() {
        return (this.mItemAnimator == null || !this.mLayout.c()) ? FORCE_INVALIDATE_DISPLAY_LIST : true;
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        boolean z;
        q qVar;
        boolean z2 = true;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.a();
            markKnownViewsInvalid();
            this.mLayout.a();
        }
        if (predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.b();
        } else {
            this.mAdapterHelper.e();
        }
        if (this.mItemsAddedOrRemoved || this.mItemsChanged) {
            boolean z3 = true;
        } else {
            Object obj = null;
        }
        q qVar2 = this.mState;
        if (this.mFirstLayoutComplete && this.mItemAnimator != null) {
            if ((this.mDataSetHasChangedAfterLayout || r0 || this.mLayout.s) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds())) {
                z = true;
                qVar2.h = z;
                qVar = this.mState;
                if (!(this.mState.h && r0 && !this.mDataSetHasChangedAfterLayout && predictiveItemAnimationsEnabled())) {
                    z2 = false;
                }
                qVar.i = z2;
            }
        }
        z = false;
        qVar2.h = z;
        qVar = this.mState;
        z2 = false;
        qVar.i = z2;
    }

    void dispatchLayout() {
        Object obj = VERTICAL;
        if (this.mAdapter != null && this.mLayout != null) {
            this.mState.k = false;
            if (this.mState.b == 1) {
                dispatchLayoutStep1();
                this.mLayout.b(this);
                dispatchLayoutStep2();
            } else {
                boolean z;
                o oVar = this.mAdapterHelper;
                if (oVar.b.isEmpty() || oVar.a.isEmpty()) {
                    z = false;
                }
                if (!z && this.mLayout.y == getWidth() && this.mLayout.z == getHeight()) {
                    this.mLayout.b(this);
                } else {
                    this.mLayout.b(this);
                    dispatchLayoutStep2();
                }
            }
            dispatchLayoutStep3();
        }
    }

    private void dispatchLayoutStep1() {
        boolean z;
        int a;
        int i;
        t childViewHolderInt;
        this.mState.a(VERTICAL);
        this.mState.k = false;
        eatRequestLayout();
        this.mViewInfoStore.a();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        q qVar = this.mState;
        if (this.mState.h && this.mItemsChanged) {
            z = true;
        } else {
            z = false;
        }
        qVar.j = z;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        this.mState.g = this.mState.i;
        this.mState.c = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.h) {
            a = this.mChildHelper.a();
            for (i = 0; i < a; i++) {
                childViewHolderInt = getChildViewHolderInt(this.mChildHelper.b(i));
                if (!childViewHolderInt.shouldIgnore()) {
                    if (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds()) {
                        e.d(childViewHolderInt);
                        childViewHolderInt.getUnmodifiedPayloads();
                        this.mViewInfoStore.a(childViewHolderInt, new b().a(childViewHolderInt));
                        if (this.mState.j && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                            this.mViewInfoStore.a(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                        }
                    }
                }
            }
        }
        if (this.mState.i) {
            saveOldPositions();
            z = this.mState.f;
            this.mState.f = false;
            this.mLayout.c(this.mRecycler, this.mState);
            this.mState.f = z;
            for (a = 0; a < this.mChildHelper.a(); a++) {
                childViewHolderInt = getChildViewHolderInt(this.mChildHelper.b(a));
                if (!childViewHolderInt.shouldIgnore()) {
                    a aVar = (a) this.mViewInfoStore.a.get(childViewHolderInt);
                    if (aVar == null || (aVar.a & 4) == 0) {
                        z = false;
                    } else {
                        i = 1;
                    }
                    if (i == 0) {
                        e.d(childViewHolderInt);
                        z = childViewHolderInt.hasAnyOfTheFlags(Message.FLAG_REQ_BIT2);
                        childViewHolderInt.getUnmodifiedPayloads();
                        b a2 = new b().a(childViewHolderInt);
                        if (z) {
                            recordAnimationInfoIfBouncedHiddenView(childViewHolderInt, a2);
                        } else {
                            cv cvVar = this.mViewInfoStore;
                            aVar = (a) cvVar.a.get(childViewHolderInt);
                            if (aVar == null) {
                                aVar = a.a();
                                cvVar.a.put(childViewHolderInt, aVar);
                            }
                            aVar.a |= 2;
                            aVar.b = a2;
                        }
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        resumeRequestLayout(FORCE_INVALIDATE_DISPLAY_LIST);
        this.mState.b = 2;
    }

    private void dispatchLayoutStep2() {
        boolean z;
        eatRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.a(com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd);
        this.mAdapterHelper.e();
        this.mState.c = this.mAdapter.getItemCount();
        this.mState.e = 0;
        this.mState.g = false;
        this.mLayout.c(this.mRecycler, this.mState);
        this.mState.f = false;
        this.mPendingSavedState = null;
        q qVar = this.mState;
        if (!this.mState.h || this.mItemAnimator == null) {
            z = false;
        } else {
            z = true;
        }
        qVar.h = z;
        this.mState.b = 4;
        onExitLayoutOrScroll();
        resumeRequestLayout(FORCE_INVALIDATE_DISPLAY_LIST);
    }

    private void dispatchLayoutStep3() {
        this.mState.a(XZBDevice.DOWNLOAD_LIST_ALL);
        eatRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.b = 1;
        if (this.mState.h) {
            t tVar;
            for (int a = this.mChildHelper.a() - 1; a >= 0; a--) {
                t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.b(a));
                if (!childViewHolderInt.shouldIgnore()) {
                    long changedHolderKey = getChangedHolderKey(childViewHolderInt);
                    b a2 = new b().a(childViewHolderInt);
                    tVar = (t) this.mViewInfoStore.b.get(changedHolderKey);
                    if (!(tVar == null || tVar.shouldIgnore())) {
                        boolean a3 = this.mViewInfoStore.a(tVar);
                        boolean a4 = this.mViewInfoStore.a(childViewHolderInt);
                        if (!(a3 && tVar == childViewHolderInt)) {
                            b a5 = this.mViewInfoStore.a(tVar, (int) XZBDevice.DOWNLOAD_LIST_ALL);
                            this.mViewInfoStore.b(childViewHolderInt, a2);
                            b a6 = this.mViewInfoStore.a(childViewHolderInt, (int) XZBDevice.Wait);
                            if (a5 == null) {
                                handleMissingPreInfoForChangeError(changedHolderKey, childViewHolderInt, tVar);
                            } else {
                                animateChange(tVar, childViewHolderInt, a5, a6, a3, a4);
                            }
                        }
                    }
                    this.mViewInfoStore.b(childViewHolderInt, a2);
                }
            }
            cv cvVar = this.mViewInfoStore;
            b bVar = this.mViewInfoProcessCallback;
            for (int size = cvVar.a.size() - 1; size >= 0; size--) {
                tVar = (t) cvVar.a.keyAt(size);
                a aVar = (a) cvVar.a.removeAt(size);
                if ((aVar.a & 3) == 3) {
                    bVar.a(tVar);
                } else if ((aVar.a & 1) != 0) {
                    if (aVar.b == null) {
                        bVar.a(tVar);
                    } else {
                        bVar.a(tVar, aVar.b, aVar.c);
                    }
                } else if ((aVar.a & 14) == 14) {
                    bVar.b(tVar, aVar.b, aVar.c);
                } else if ((aVar.a & 12) == 12) {
                    bVar.c(tVar, aVar.b, aVar.c);
                } else if ((aVar.a & 4) != 0) {
                    bVar.a(tVar, aVar.b, null);
                } else if ((aVar.a & 8) != 0) {
                    bVar.b(tVar, aVar.b, aVar.c);
                } else {
                    int i = aVar.a;
                }
                a.a(aVar);
            }
        }
        this.mLayout.b(this.mRecycler);
        this.mState.d = this.mState.c;
        this.mDataSetHasChangedAfterLayout = false;
        this.mState.h = false;
        this.mState.i = false;
        this.mLayout.s = false;
        if (this.mRecycler.b != null) {
            this.mRecycler.b.clear();
        }
        onExitLayoutOrScroll();
        resumeRequestLayout(FORCE_INVALIDATE_DISPLAY_LIST);
        this.mViewInfoStore.a();
        if (didChildRangeChange(this.mMinMaxLayoutPositions[0], this.mMinMaxLayoutPositions[1])) {
            dispatchOnScrolled(TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT);
        }
    }

    private void handleMissingPreInfoForChangeError(long j, t tVar, t tVar2) {
        int a = this.mChildHelper.a();
        for (int i = TOUCH_SLOP_DEFAULT; i < a; i++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.b(i));
            if (childViewHolderInt != tVar && getChangedHolderKey(childViewHolderInt) == j) {
                if (this.mAdapter == null || !this.mAdapter.hasStableIds()) {
                    throw new IllegalStateException(new StringBuilder("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:").append(childViewHolderInt).append(" \n View Holder 2:").append(tVar).toString());
                }
                throw new IllegalStateException(new StringBuilder("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:").append(childViewHolderInt).append(" \n View Holder 2:").append(tVar).toString());
            }
        }
        new StringBuilder("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ").append(tVar2).append(" cannot be found but it is necessary for ").append(tVar);
    }

    private void recordAnimationInfoIfBouncedHiddenView(t tVar, b bVar) {
        tVar.setFlags(TOUCH_SLOP_DEFAULT, Message.FLAG_REQ_BIT2);
        if (this.mState.j && tVar.isUpdated() && !tVar.isRemoved() && !tVar.shouldIgnore()) {
            this.mViewInfoStore.a(getChangedHolderKey(tVar), tVar);
        }
        this.mViewInfoStore.a(tVar, bVar);
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int a = this.mChildHelper.a();
        if (a == 0) {
            iArr[0] = 0;
            iArr[1] = 0;
            return;
        }
        int i;
        int i2;
        Object obj = InMobiClientPositioning.NO_REPEAT;
        Object obj2 = ExploreByTouchHelper.INVALID_ID;
        int i3 = 0;
        while (i3 < a) {
            int layoutPosition;
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.b(i3));
            if (!childViewHolderInt.shouldIgnore()) {
                layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i) {
                    i = layoutPosition;
                }
                if (layoutPosition > i2) {
                    i2 = i;
                    i3++;
                    i = i2;
                    i2 = layoutPosition;
                }
            }
            layoutPosition = i2;
            i2 = i;
            i3++;
            i = i2;
            i2 = layoutPosition;
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    private boolean didChildRangeChange(int i, int i2) {
        if (this.mChildHelper.a() == 0) {
            return (i == 0 && i2 == 0) ? FORCE_INVALIDATE_DISPLAY_LIST : true;
        } else {
            findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
            return (this.mMinMaxLayoutPositions[0] == i && this.mMinMaxLayoutPositions[1] == i2) ? FORCE_INVALIDATE_DISPLAY_LIST : true;
        }
    }

    protected void removeDetachedView(View view, boolean z) {
        t childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException(new StringBuilder("Called removeDetachedView with a view which is not flagged as tmp detached.").append(childViewHolderInt).toString());
            }
        }
        dispatchChildDetached(view);
        super.removeDetachedView(view, z);
    }

    long getChangedHolderKey(t tVar) {
        return this.mAdapter.hasStableIds() ? tVar.getItemId() : (long) tVar.mPosition;
    }

    private void animateAppearance(t tVar, b bVar, b bVar2) {
        tVar.setIsRecyclable(FORCE_INVALIDATE_DISPLAY_LIST);
        if (this.mItemAnimator.b(tVar, bVar, bVar2)) {
            postAnimationRunner();
        }
    }

    private void animateDisappearance(t tVar, b bVar, b bVar2) {
        addAnimatingView(tVar);
        tVar.setIsRecyclable(FORCE_INVALIDATE_DISPLAY_LIST);
        if (this.mItemAnimator.a(tVar, bVar, bVar2)) {
            postAnimationRunner();
        }
    }

    private void animateChange(t tVar, t tVar2, b bVar, b bVar2, boolean z, boolean z2) {
        tVar.setIsRecyclable(FORCE_INVALIDATE_DISPLAY_LIST);
        if (z) {
            addAnimatingView(tVar);
        }
        if (tVar != tVar2) {
            if (z2) {
                addAnimatingView(tVar2);
            }
            tVar.mShadowedHolder = tVar2;
            addAnimatingView(tVar);
            this.mRecycler.b(tVar);
            tVar2.setIsRecyclable(FORCE_INVALIDATE_DISPLAY_LIST);
            tVar2.mShadowingHolder = tVar;
        }
        if (this.mItemAnimator.a(tVar, tVar2, bVar, bVar2)) {
            postAnimationRunner();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceCompat.beginSection(TRACE_ON_LAYOUT_TAG);
        dispatchLayout();
        TraceCompat.endSection();
        this.mFirstLayoutComplete = true;
    }

    public void requestLayout() {
        if (this.mEatRequestLayout != 0 || this.mLayoutFrozen) {
            this.mLayoutRequestEaten = true;
        } else {
            super.requestLayout();
        }
    }

    void markItemDecorInsetsDirty() {
        int i = TOUCH_SLOP_DEFAULT;
        int b = this.mChildHelper.b();
        for (int i2 = 0; i2 < b; i2++) {
            ((LayoutParams) this.mChildHelper.c(i2).getLayoutParams()).e = true;
        }
        m mVar = this.mRecycler;
        b = mVar.c.size();
        while (i < b) {
            LayoutParams layoutParams = (LayoutParams) ((t) mVar.c.get(i)).itemView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.e = true;
            }
            i++;
        }
    }

    public void draw(Canvas canvas) {
        int i;
        int i2;
        Object obj = VERTICAL;
        int i3 = TOUCH_SLOP_DEFAULT;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        for (i = 0; i < size; i++) {
            ((g) this.mItemDecorations.get(i)).onDrawOver(canvas, this, this.mState);
        }
        if (this.mLeftGlow == null || this.mLeftGlow.isFinished()) {
            i2 = 0;
        } else {
            i = canvas.save();
            i2 = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((float) (i2 + (-getHeight())), AutoScrollHelper.RELATIVE_UNSPECIFIED);
            if (this.mLeftGlow == null || !this.mLeftGlow.draw(canvas)) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            canvas.restoreToCount(i);
        }
        if (!(this.mTopGlow == null || this.mTopGlow.isFinished())) {
            size = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            if (this.mTopGlow == null || !this.mTopGlow.draw(canvas)) {
                i = 0;
            } else {
                i = 1;
            }
            i2 |= i;
            canvas.restoreToCount(size);
        }
        if (!(this.mRightGlow == null || this.mRightGlow.isFinished())) {
            size = canvas.save();
            int width = getWidth();
            if (this.mClipToPadding) {
                i = getPaddingTop();
            } else {
                i = 0;
            }
            canvas.rotate(90.0f);
            canvas.translate((float) (-i), (float) (-width));
            if (this.mRightGlow == null || !this.mRightGlow.draw(canvas)) {
                i = 0;
            } else {
                i = 1;
            }
            i2 |= i;
            canvas.restoreToCount(size);
        }
        if (!(this.mBottomGlow == null || this.mBottomGlow.isFinished())) {
            i = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate((float) ((-getWidth()) + getPaddingRight()), (float) ((-getHeight()) + getPaddingBottom()));
            } else {
                canvas.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            if (this.mBottomGlow != null && this.mBottomGlow.draw(canvas)) {
                i3 = 1;
            }
            i2 |= i3;
            canvas.restoreToCount(i);
        }
        if (i2 != 0 || this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.b()) {
            int i4 = i2;
        }
        if (i4 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            ((g) this.mItemDecorations.get(i)).onDraw(canvas, this, this.mState);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return ((layoutParams instanceof LayoutParams) && this.mLayout.a((LayoutParams) layoutParams)) ? true : FORCE_INVALIDATE_DISPLAY_LIST;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        if (this.mLayout != null) {
            return this.mLayout.b();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        if (this.mLayout != null) {
            return this.mLayout.a(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (this.mLayout != null) {
            return this.mLayout.a(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager");
    }

    public boolean isAnimating() {
        return (this.mItemAnimator == null || !this.mItemAnimator.b()) ? FORCE_INVALIDATE_DISPLAY_LIST : true;
    }

    void saveOldPositions() {
        int b = this.mChildHelper.b();
        for (int i = TOUCH_SLOP_DEFAULT; i < b; i++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }

    void clearOldPositions() {
        int i = TOUCH_SLOP_DEFAULT;
        int b = this.mChildHelper.b();
        for (int i2 = 0; i2 < b; i2++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i2));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        m mVar = this.mRecycler;
        int size = mVar.c.size();
        for (b = 0; b < size; b++) {
            ((t) mVar.c.get(b)).clearOldPosition();
        }
        size = mVar.a.size();
        for (b = 0; b < size; b++) {
            ((t) mVar.a.get(b)).clearOldPosition();
        }
        if (mVar.b != null) {
            b = mVar.b.size();
            while (i < b) {
                ((t) mVar.b.get(i)).clearOldPosition();
                i++;
            }
        }
    }

    void offsetPositionRecordsForMove(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = NO_POSITION;
        int b = this.mChildHelper.b();
        if (i < i2) {
            i3 = -1;
            i4 = i2;
            i5 = i;
        } else {
            i3 = 1;
            i4 = i;
            i5 = i2;
        }
        for (int i7 = 0; i7 < b; i7++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i7));
            if (childViewHolderInt != null && childViewHolderInt.mPosition >= r4 && childViewHolderInt.mPosition <= r3) {
                if (childViewHolderInt.mPosition == i) {
                    childViewHolderInt.offsetPosition(i2 - i, FORCE_INVALIDATE_DISPLAY_LIST);
                } else {
                    childViewHolderInt.offsetPosition(i3, FORCE_INVALIDATE_DISPLAY_LIST);
                }
                this.mState.f = true;
            }
        }
        m mVar = this.mRecycler;
        int i8;
        if (i < i2) {
            i8 = i2;
            i4 = i;
        } else {
            i6 = 1;
            i4 = i2;
            i8 = i;
        }
        b = mVar.c.size();
        for (i5 = 0; i5 < b; i5++) {
            t tVar = (t) mVar.c.get(i5);
            if (tVar != null && tVar.mPosition >= r3 && tVar.mPosition <= r2) {
                if (tVar.mPosition == i) {
                    tVar.offsetPosition(i2 - i, FORCE_INVALIDATE_DISPLAY_LIST);
                } else {
                    tVar.offsetPosition(i6, FORCE_INVALIDATE_DISPLAY_LIST);
                }
            }
        }
        requestLayout();
    }

    void offsetPositionRecordsForInsert(int i, int i2) {
        int i3;
        int b = this.mChildHelper.b();
        for (i3 = 0; i3 < b; i3++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i3));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i) {
                childViewHolderInt.offsetPosition(i2, FORCE_INVALIDATE_DISPLAY_LIST);
                this.mState.f = true;
            }
        }
        m mVar = this.mRecycler;
        int size = mVar.c.size();
        for (i3 = 0; i3 < size; i3++) {
            t tVar = (t) mVar.c.get(i3);
            if (tVar != null && tVar.mPosition >= i) {
                tVar.offsetPosition(i2, true);
            }
        }
        requestLayout();
    }

    void offsetPositionRecordsForRemove(int i, int i2, boolean z) {
        int i3 = i + i2;
        int b = this.mChildHelper.b();
        for (int i4 = TOUCH_SLOP_DEFAULT; i4 < b; i4++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i4));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                if (childViewHolderInt.mPosition >= i3) {
                    childViewHolderInt.offsetPosition(-i2, z);
                    this.mState.f = true;
                } else if (childViewHolderInt.mPosition >= i) {
                    childViewHolderInt.flagRemovedAndOffsetPosition(i - 1, -i2, z);
                    this.mState.f = true;
                }
            }
        }
        m mVar = this.mRecycler;
        int i5 = i + i2;
        for (i3 = mVar.c.size() - 1; i3 >= 0; i3--) {
            t tVar = (t) mVar.c.get(i3);
            if (tVar != null) {
                if (tVar.mPosition >= i5) {
                    tVar.offsetPosition(-i2, z);
                } else if (tVar.mPosition >= i) {
                    tVar.addFlags(XZBDevice.Wait);
                    mVar.c(i3);
                }
            }
        }
        requestLayout();
    }

    void viewRangeUpdate(int i, int i2, Object obj) {
        int i3;
        int b = this.mChildHelper.b();
        int i4 = i + i2;
        for (i3 = 0; i3 < b; i3++) {
            View c = this.mChildHelper.c(i3);
            t childViewHolderInt = getChildViewHolderInt(c);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i && childViewHolderInt.mPosition < i4) {
                childViewHolderInt.addFlags(SCROLL_STATE_SETTLING);
                childViewHolderInt.addChangePayload(obj);
                ((LayoutParams) c.getLayoutParams()).e = true;
            }
        }
        m mVar = this.mRecycler;
        for (i3 = mVar.c.size() - 1; i3 >= 0; i3--) {
            t tVar = (t) mVar.c.get(i3);
            if (tVar != null) {
                int layoutPosition = tVar.getLayoutPosition();
                if (layoutPosition >= i && layoutPosition < i4) {
                    tVar.addFlags(SCROLL_STATE_SETTLING);
                    mVar.c(i3);
                }
            }
        }
    }

    private boolean canReuseUpdatedViewHolder(t tVar) {
        return (this.mItemAnimator == null || this.mItemAnimator.a(tVar, tVar.getUnmodifiedPayloads())) ? true : FORCE_INVALIDATE_DISPLAY_LIST;
    }

    private void setDataSetChangedAfterLayout() {
        if (!this.mDataSetHasChangedAfterLayout) {
            int i;
            this.mDataSetHasChangedAfterLayout = true;
            int b = this.mChildHelper.b();
            for (i = 0; i < b; i++) {
                t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i));
                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.addFlags(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
                }
            }
            m mVar = this.mRecycler;
            int size = mVar.c.size();
            for (i = 0; i < size; i++) {
                t tVar = (t) mVar.c.get(i);
                if (tVar != null) {
                    tVar.addFlags(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
                }
            }
        }
    }

    void markKnownViewsInvalid() {
        int i;
        int b = this.mChildHelper.b();
        for (i = 0; i < b; i++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd);
            }
        }
        markItemDecorInsetsDirty();
        m mVar = this.mRecycler;
        if (mVar.h.mAdapter == null || !mVar.h.mAdapter.hasStableIds()) {
            mVar.b();
            return;
        }
        int size = mVar.c.size();
        for (i = 0; i < size; i++) {
            t tVar = (t) mVar.c.get(i);
            if (tVar != null) {
                tVar.addFlags(com.xunlei.tdlive.R.styleable.Toolbar_contentInsetEnd);
                tVar.addChangePayload(null);
            }
        }
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() != 0) {
            if (this.mLayout != null) {
                this.mLayout.a("Cannot invalidate item decorations during a scroll or layout");
            }
            markItemDecorInsetsDirty();
            requestLayout();
        }
    }

    public t getChildViewHolder(View view) {
        Object parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException(new StringBuilder("View ").append(view).append(" is not a direct child of ").append(this).toString());
    }

    public View findContainingItemView(View view) {
        ViewParent parent = view.getParent();
        View view2 = view;
        while (r0 != null && r0 != this && (r0 instanceof View)) {
            View view3 = r0;
            view2 = view3;
            parent = view3.getParent();
        }
        return r0 == this ? view2 : null;
    }

    public t findContainingViewHolder(View view) {
        View findContainingItemView = findContainingItemView(view);
        return findContainingItemView == null ? null : getChildViewHolder(findContainingItemView);
    }

    static t getChildViewHolderInt(View view) {
        return view == null ? null : ((LayoutParams) view.getLayoutParams()).c;
    }

    @Deprecated
    public int getChildPosition(View view) {
        return getChildAdapterPosition(view);
    }

    public int getChildAdapterPosition(View view) {
        t childViewHolderInt = getChildViewHolderInt(view);
        return childViewHolderInt != null ? childViewHolderInt.getAdapterPosition() : NO_POSITION;
    }

    public int getChildLayoutPosition(View view) {
        t childViewHolderInt = getChildViewHolderInt(view);
        return childViewHolderInt != null ? childViewHolderInt.getLayoutPosition() : NO_POSITION;
    }

    public long getChildItemId(View view) {
        if (this.mAdapter == null || !this.mAdapter.hasStableIds()) {
            return NO_ID;
        }
        t childViewHolderInt = getChildViewHolderInt(view);
        return childViewHolderInt != null ? childViewHolderInt.getItemId() : NO_ID;
    }

    @Deprecated
    public t findViewHolderForPosition(int i) {
        return findViewHolderForPosition(i, FORCE_INVALIDATE_DISPLAY_LIST);
    }

    public t findViewHolderForLayoutPosition(int i) {
        return findViewHolderForPosition(i, FORCE_INVALIDATE_DISPLAY_LIST);
    }

    public t findViewHolderForAdapterPosition(int i) {
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int b = this.mChildHelper.b();
        for (int i2 = 0; i2 < b; i2++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i2));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionFor(childViewHolderInt) == i) {
                return childViewHolderInt;
            }
        }
        return null;
    }

    t findViewHolderForPosition(int i, boolean z) {
        int b = this.mChildHelper.b();
        for (int i2 = 0; i2 < b; i2++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i2));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
                if (z) {
                    if (childViewHolderInt.mPosition == i) {
                        return childViewHolderInt;
                    }
                } else if (childViewHolderInt.getLayoutPosition() == i) {
                    return childViewHolderInt;
                }
            }
        }
        return null;
    }

    public t findViewHolderForItemId(long j) {
        int b = this.mChildHelper.b();
        for (int i = 0; i < b; i++) {
            t childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i));
            if (childViewHolderInt != null && childViewHolderInt.getItemId() == j) {
                return childViewHolderInt;
            }
        }
        return null;
    }

    public View findChildViewUnder(float f, float f2) {
        for (int a = this.mChildHelper.a() - 1; a >= 0; a--) {
            View b = this.mChildHelper.b(a);
            float translationX = ViewCompat.getTranslationX(b);
            float translationY = ViewCompat.getTranslationY(b);
            if (f >= ((float) b.getLeft()) + translationX && f <= translationX + ((float) b.getRight()) && f2 >= ((float) b.getTop()) + translationY && f2 <= ((float) b.getBottom()) + translationY) {
                return b;
            }
        }
        return null;
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public void offsetChildrenVertical(int i) {
        int a = this.mChildHelper.a();
        for (int i2 = TOUCH_SLOP_DEFAULT; i2 < a; i2++) {
            this.mChildHelper.b(i2).offsetTopAndBottom(i);
        }
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    public void offsetChildrenHorizontal(int i) {
        int a = this.mChildHelper.a();
        for (int i2 = TOUCH_SLOP_DEFAULT; i2 < a; i2++) {
            this.mChildHelper.b(i2).offsetLeftAndRight(i);
        }
    }

    Rect getItemDecorInsetsForChild(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.e) {
            return layoutParams.d;
        }
        Rect rect = layoutParams.d;
        rect.set(TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            this.mTempRect.set(TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT, TOUCH_SLOP_DEFAULT);
            ((g) this.mItemDecorations.get(i)).getItemOffsets(this.mTempRect, view, this, this.mState);
            rect.left += this.mTempRect.left;
            rect.top += this.mTempRect.top;
            rect.right += this.mTempRect.right;
            rect.bottom += this.mTempRect.bottom;
        }
        layoutParams.e = false;
        return rect;
    }

    public void onScrolled(int i, int i2) {
    }

    void dispatchOnScrolled(int i, int i2) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        onScrolled(i, i2);
        if (this.mScrollListeners != null) {
            for (scrollX = this.mScrollListeners.size() - 1; scrollX >= 0; scrollX--) {
                this.mScrollListeners.get(scrollX);
            }
        }
    }

    public void onScrollStateChanged(int i) {
    }

    void dispatchOnScrollStateChanged(int i) {
        if (this.mLayout != null) {
            this.mLayout.h(i);
        }
        onScrollStateChanged(i);
        if (this.mScrollListener != null) {
            this.mScrollListener.a(this, i);
        }
        if (this.mScrollListeners != null) {
            for (int size = this.mScrollListeners.size() - 1; size >= 0; size--) {
                ((k) this.mScrollListeners.get(size)).a(this, i);
            }
        }
    }

    public boolean hasPendingAdapterUpdates() {
        return (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.d()) ? true : FORCE_INVALIDATE_DISPLAY_LIST;
    }

    private void repositionShadowingViews() {
        int a = this.mChildHelper.a();
        for (int i = TOUCH_SLOP_DEFAULT; i < a; i++) {
            View b = this.mChildHelper.b(i);
            t childViewHolder = getChildViewHolder(b);
            if (childViewHolder != null && childViewHolder.mShadowingHolder != null) {
                View view = childViewHolder.mShadowingHolder.itemView;
                int left = b.getLeft();
                int top = b.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    private void dispatchChildDetached(View view) {
        t childViewHolderInt = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        if (!(this.mAdapter == null || childViewHolderInt == null)) {
            this.mAdapter.onViewDetachedFromWindow(childViewHolderInt);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int size = this.mOnChildAttachStateListeners.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size);
            }
        }
    }

    private void dispatchChildAttached(View view) {
        t childViewHolderInt = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        if (!(this.mAdapter == null || childViewHolderInt == null)) {
            this.mAdapter.onViewAttachedToWindow(childViewHolderInt);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int size = this.mOnChildAttachStateListeners.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size);
            }
        }
    }

    private int getAdapterPositionFor(t tVar) {
        if (tVar.hasAnyOfTheFlags(524) || !tVar.isBound()) {
            return -1;
        }
        o oVar = this.mAdapterHelper;
        int i = tVar.mPosition;
        int size = oVar.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = (b) oVar.a.get(i2);
            switch (bVar.a) {
                case VERTICAL:
                    if (bVar.b <= i) {
                        i += bVar.d;
                    }
                case SCROLL_STATE_SETTLING:
                    if (bVar.b > i) {
                        continue;
                    } else if (bVar.b + bVar.d > i) {
                        return -1;
                    } else {
                        i -= bVar.d;
                    }
                    break;
                case XZBDevice.Wait:
                    if (bVar.b == i) {
                        i = bVar.d;
                    } else {
                        if (bVar.b < i) {
                            i--;
                        }
                        if (bVar.d <= i) {
                            i++;
                        }
                    }
                default:
                    break;
            }
        }
        return i;
    }

    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i) {
        return getScrollingChildHelper().startNestedScroll(i);
    }

    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().dispatchNestedPreFling(f, f2);
    }

    protected int getChildDrawingOrder(int i, int i2) {
        return this.mChildDrawingOrderCallback == null ? super.getChildDrawingOrder(i, i2) : this.mChildDrawingOrderCallback.a();
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }
}
