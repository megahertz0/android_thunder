package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.q;
import android.view.View;

// compiled from: RecyclerViewAccessibilityDelegate.java
final class bi extends AccessibilityDelegateCompat {
    final /* synthetic */ bh a;

    bi(bh bhVar) {
        this.a = bhVar;
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (!this.a.a.hasPendingAdapterUpdates() && this.a.a.getLayoutManager() != null) {
            this.a.a.getLayoutManager().a(view, accessibilityNodeInfoCompat);
        }
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (this.a.a.hasPendingAdapterUpdates() || this.a.a.getLayoutManager() == null) {
            return false;
        }
        h layoutManager = this.a.a.getLayoutManager();
        m mVar = layoutManager.q.mRecycler;
        q qVar = layoutManager.q.mState;
        return false;
    }
}
