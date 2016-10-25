package com.sina.weibo.sdk.register.mobile;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class LetterIndexBar extends View {
    public static final int INDEX_COUNT_DEFAULT = 27;
    public static final String SEARCH_ICON_LETTER = "";
    private int count;
    private int mIndex;
    private String[] mIndexLetter;
    private int mItemHeight;
    private int mItemPadding;
    private OnIndexChangeListener mListener;
    private boolean[] mNeedIndex;
    private int mOrgTextSzie;
    private Paint mPaint;
    private RectF mRect;
    private Drawable mSeatchIcon;
    private boolean mTouching;

    public static interface OnIndexChangeListener {
        void onIndexChange(int i);
    }

    public LetterIndexBar(Context context) {
        super(context);
        this.mPaint = new Paint();
        this.count = 27;
        init();
    }

    public LetterIndexBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint = new Paint();
        this.count = 27;
        init();
    }

    public LetterIndexBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaint = new Paint();
        this.count = 27;
        init();
    }

    private void init() {
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setColor(-10658467);
        this.mOrgTextSzie = ResourceManager.dp2px(getContext(), XZBDevice.Upload);
    }

    public void setIndexMark(boolean[] zArr) {
        if (zArr != null) {
            this.mNeedIndex = zArr;
            invalidate();
        }
    }

    public void setIndexLetter(String[] strArr) {
        if (strArr != null) {
            this.mIndexLetter = strArr;
            this.count = this.mIndexLetter.length;
            this.mIndex = -1;
            invalidate();
        }
    }

    public void setIndexChangeListener(OnIndexChangeListener onIndexChangeListener) {
        this.mListener = onIndexChangeListener;
    }

    protected void onDraw(Canvas canvas) {
        int color;
        int i = 0;
        super.onDraw(canvas);
        if (this.mTouching) {
            color = this.mPaint.getColor();
            this.mPaint.setColor(-2005436536);
            canvas.drawRoundRect(this.mRect, (float) (getMeasuredWidth() / 2), (float) (getMeasuredWidth() / 2), this.mPaint);
            this.mPaint.setColor(color);
        }
        if (this.mOrgTextSzie > this.mItemHeight) {
            color = this.mItemHeight;
        } else {
            color = this.mOrgTextSzie;
        }
        this.mPaint.setTextSize((float) color);
        int paddingTop;
        String str;
        if (this.mIndexLetter == null) {
            char c = 'A';
            while (i < this.count) {
                paddingTop = this.mItemPadding + (((this.mItemHeight * i) + getPaddingTop()) + color);
                if (this.mNeedIndex == null || this.mNeedIndex[i]) {
                    if (i == this.count - 1) {
                        str = "#";
                    } else {
                        char c2 = (char) (c + 1);
                        str = String.valueOf(c);
                        c = c2;
                    }
                    canvas.drawText(str, (float) ((getMeasuredWidth() - ((int) this.mPaint.measureText(str))) / 2), (float) paddingTop, this.mPaint);
                }
                i++;
            }
            return;
        }
        while (i < this.count) {
            int paddingTop2 = (((this.mItemHeight * i) + getPaddingTop()) + color) + this.mItemPadding;
            if (this.mNeedIndex == null || this.mNeedIndex[i]) {
                str = this.mIndexLetter[i];
                if (str.equals(SEARCH_ICON_LETTER)) {
                    int measureText = (int) this.mPaint.measureText("M");
                    paddingTop = (getMeasuredWidth() - measureText) / 2;
                    this.mSeatchIcon.setBounds(paddingTop, paddingTop2 - paddingTop, measureText + paddingTop, (paddingTop2 + measureText) - paddingTop);
                    this.mSeatchIcon.draw(canvas);
                } else {
                    canvas.drawText(str, (float) ((getMeasuredWidth() - ((int) this.mPaint.measureText(str))) / 2), (float) paddingTop2, this.mPaint);
                }
            }
            i++;
        }
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        this.mItemHeight = ((size - getPaddingTop()) - getPaddingBottom()) / this.count;
        this.mItemPadding = (int) ((((float) this.mItemHeight) - this.mPaint.getTextSize()) / 2.0f);
        setMeasuredDimension((this.mOrgTextSzie + getPaddingLeft()) + getPaddingRight(), i2);
        this.mRect = new RectF(0.0f, (float) getPaddingTop(), (float) getMeasuredWidth(), (float) ((size - getPaddingTop()) - getPaddingBottom()));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case SpdyAgent.ACCS_TEST_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.mTouching = true;
                int y = (((int) motionEvent.getY()) - getPaddingTop()) / this.mItemHeight;
                if (y != this.mIndex) {
                    if ((this.mNeedIndex == null || this.mNeedIndex[y]) && y < this.count && y >= 0) {
                        this.mIndex = y;
                        if (this.mListener != null) {
                            this.mListener.onIndexChange(this.mIndex);
                        }
                    }
                }
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.mTouching = false;
                break;
        }
        invalidate();
        return true;
    }
}
