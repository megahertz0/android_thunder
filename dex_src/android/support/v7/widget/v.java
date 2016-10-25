package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

// compiled from: AppCompatSeekBarHelper.java
final class v extends u {
    private static final int[] c;
    private final SeekBar d;

    static {
        c = new int[]{16843074};
    }

    v(SeekBar seekBar, r rVar) {
        super(seekBar, rVar);
        this.d = seekBar;
    }

    final void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        cm a = cm.a(this.d.getContext(), attributeSet, c, i);
        Drawable b = a.b(0);
        if (b != null) {
            this.d.setThumb(b);
        }
        a.a.recycle();
    }
}
