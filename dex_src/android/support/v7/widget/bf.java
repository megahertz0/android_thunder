package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.q;
import android.support.v7.widget.RecyclerView.t;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: RecyclerView.java
final class bf implements a {
    final /* synthetic */ RecyclerView a;

    bf(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public final t a(int i) {
        t findViewHolderForPosition = this.a.findViewHolderForPosition(i, true);
        return (findViewHolderForPosition == null || this.a.mChildHelper.d(findViewHolderForPosition.itemView)) ? null : findViewHolderForPosition;
    }

    public final void a(int i, int i2) {
        this.a.offsetPositionRecordsForRemove(i, i2, true);
        this.a.mItemsAddedOrRemoved = true;
        q qVar = this.a.mState;
        qVar.e += i2;
    }

    public final void b(int i, int i2) {
        this.a.offsetPositionRecordsForRemove(i, i2, false);
        this.a.mItemsAddedOrRemoved = true;
    }

    public final void a(int i, int i2, Object obj) {
        this.a.viewRangeUpdate(i, i2, obj);
        this.a.mItemsChanged = true;
    }

    public final void a(b bVar) {
        c(bVar);
    }

    private void c(b bVar) {
        switch (bVar.a) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.a.mLayout.a(bVar.b, bVar.d);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.a.mLayout.b(bVar.b, bVar.d);
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.a.mLayout.c(bVar.b, bVar.d);
            case XZBDevice.Wait:
                this.a.mLayout.d(bVar.b, bVar.d);
            default:
                break;
        }
    }

    public final void b(b bVar) {
        c(bVar);
    }

    public final void c(int i, int i2) {
        this.a.offsetPositionRecordsForInsert(i, i2);
        this.a.mItemsAddedOrRemoved = true;
    }

    public final void d(int i, int i2) {
        this.a.offsetPositionRecordsForMove(i, i2);
        this.a.mItemsAddedOrRemoved = true;
    }
}
