package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.q;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.taobao.accs.data.Message;

// compiled from: RecyclerViewAccessibilityDelegate.java
public final class bh extends AccessibilityDelegateCompat {
    final RecyclerView a;
    final AccessibilityDelegateCompat b;

    public bh(RecyclerView recyclerView) {
        this.b = new bi(this);
        this.a = recyclerView;
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (this.a.hasPendingAdapterUpdates() || this.a.getLayoutManager() == null) {
            return false;
        }
        h layoutManager = this.a.getLayoutManager();
        m mVar = layoutManager.q.mRecycler;
        q qVar = layoutManager.q.mState;
        if (layoutManager.q == null) {
            return false;
        }
        int p;
        int i2;
        switch (i) {
            case Message.FLAG_ERR:
                if (ViewCompat.canScrollVertically(layoutManager.q, 1)) {
                    p = (layoutManager.z - layoutManager.p()) - layoutManager.r();
                } else {
                    p = 0;
                }
                if (ViewCompat.canScrollHorizontally(layoutManager.q, 1)) {
                    i2 = p;
                    p = (layoutManager.y - layoutManager.o()) - layoutManager.q();
                    if (i2 != 0) {
                    }
                    layoutManager.q.scrollBy(p, i2);
                    return true;
                }
            case Message.FLAG_REQ_BIT2:
                if (ViewCompat.canScrollVertically(layoutManager.q, -1)) {
                    p = -((layoutManager.z - layoutManager.p()) - layoutManager.r());
                } else {
                    p = 0;
                }
                if (ViewCompat.canScrollHorizontally(layoutManager.q, -1)) {
                    i2 = p;
                    p = -((layoutManager.y - layoutManager.o()) - layoutManager.q());
                    if (i2 != 0) {
                    }
                    layoutManager.q.scrollBy(p, i2);
                    return true;
                }
            default:
                p = 0;
                i2 = 0;
                if (i2 != 0 && p == 0) {
                    return false;
                }
                layoutManager.q.scrollBy(p, i2);
                return true;
        }
        i2 = p;
        p = 0;
        if (i2 != 0) {
        }
        layoutManager.q.scrollBy(p, i2);
        return true;
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(RecyclerView.class.getName());
        if (!this.a.hasPendingAdapterUpdates() && this.a.getLayoutManager() != null) {
            h layoutManager = this.a.getLayoutManager();
            m mVar = layoutManager.q.mRecycler;
            q qVar = layoutManager.q.mState;
            if (ViewCompat.canScrollVertically(layoutManager.q, -1) || ViewCompat.canScrollHorizontally(layoutManager.q, -1)) {
                accessibilityNodeInfoCompat.addAction((int) Message.FLAG_REQ_BIT2);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (ViewCompat.canScrollVertically(layoutManager.q, 1) || ViewCompat.canScrollHorizontally(layoutManager.q, 1)) {
                accessibilityNodeInfoCompat.addAction((int) Message.FLAG_ERR);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(CollectionInfoCompat.obtain(layoutManager.a(mVar, qVar), layoutManager.b(mVar, qVar), false, 0));
        }
    }

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName(RecyclerView.class.getName());
        if ((view instanceof RecyclerView) && !this.a.hasPendingAdapterUpdates()) {
            RecyclerView recyclerView = (RecyclerView) view;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().a(accessibilityEvent);
            }
        }
    }
}
