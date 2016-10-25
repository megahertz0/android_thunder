package android.support.v4.media;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.widget.AutoScrollHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RatingCompat implements Parcelable {
    public static final Creator<RatingCompat> CREATOR;
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1.0f;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private Object mRatingObj;
    private final int mRatingStyle;
    private final float mRatingValue;

    @Retention(RetentionPolicy.SOURCE)
    public static @interface StarStyle {
    }

    @Retention(RetentionPolicy.SOURCE)
    public static @interface Style {
    }

    private RatingCompat(int i, float f) {
        this.mRatingStyle = i;
        this.mRatingValue = f;
    }

    public final String toString() {
        return new StringBuilder("Rating:style=").append(this.mRatingStyle).append(" rating=").append(this.mRatingValue < 0.0f ? "unrated" : String.valueOf(this.mRatingValue)).toString();
    }

    public final int describeContents() {
        return this.mRatingStyle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mRatingStyle);
        parcel.writeFloat(this.mRatingValue);
    }

    static {
        CREATOR = new Creator<RatingCompat>() {
            public final RatingCompat createFromParcel(Parcel parcel) {
                return new RatingCompat(parcel.readFloat(), null);
            }

            public final RatingCompat[] newArray(int i) {
                return new RatingCompat[i];
            }
        };
    }

    public static RatingCompat newUnratedRating(int i) {
        switch (i) {
            case RATING_HEART:
            case RATING_THUMB_UP_DOWN:
            case RATING_3_STARS:
            case RATING_4_STARS:
            case RATING_5_STARS:
            case RATING_PERCENTAGE:
                return new RatingCompat(i, -1.0f);
            default:
                return null;
        }
    }

    public static RatingCompat newHeartRating(boolean z) {
        return new RatingCompat(1, z ? 1.0f : AutoScrollHelper.RELATIVE_UNSPECIFIED);
    }

    public static RatingCompat newThumbRating(boolean z) {
        return new RatingCompat(2, z ? 1.0f : AutoScrollHelper.RELATIVE_UNSPECIFIED);
    }

    public static RatingCompat newStarRating(int i, float f) {
        float f2;
        switch (i) {
            case RATING_3_STARS:
                f2 = 3.0f;
                break;
            case RATING_4_STARS:
                f2 = 4.0f;
                break;
            case RATING_5_STARS:
                f2 = 5.0f;
                break;
            default:
                new StringBuilder("Invalid rating style (").append(i).append(") for a star rating");
                return null;
        }
        return (f < 0.0f || f > f2) ? null : new RatingCompat(i, f);
    }

    public static RatingCompat newPercentageRating(float f) {
        return (f < 0.0f || f > 100.0f) ? null : new RatingCompat(6, f);
    }

    public final boolean isRated() {
        return this.mRatingValue >= 0.0f;
    }

    public final int getRatingStyle() {
        return this.mRatingStyle;
    }

    public final boolean hasHeart() {
        return this.mRatingStyle == 1 && this.mRatingValue == 1.0f;
    }

    public final boolean isThumbUp() {
        return this.mRatingStyle == 2 && this.mRatingValue == 1.0f;
    }

    public final float getStarRating() {
        switch (this.mRatingStyle) {
            case RATING_3_STARS:
            case RATING_4_STARS:
            case RATING_5_STARS:
                if (isRated()) {
                    return this.mRatingValue;
                }
        }
        return RATING_NOT_RATED;
    }

    public final float getPercentRating() {
        return (this.mRatingStyle == 6 && isRated()) ? this.mRatingValue : RATING_NOT_RATED;
    }

    public static RatingCompat fromRating(Object obj) {
        RatingCompat ratingCompat = null;
        if (obj != null && VERSION.SDK_INT >= 21) {
            int ratingStyle = RatingCompatApi21.getRatingStyle(obj);
            if (RatingCompatApi21.isRated(obj)) {
                switch (ratingStyle) {
                    case RATING_HEART:
                        ratingCompat = newHeartRating(RatingCompatApi21.hasHeart(obj));
                        break;
                    case RATING_THUMB_UP_DOWN:
                        ratingCompat = newThumbRating(RatingCompatApi21.isThumbUp(obj));
                        break;
                    case RATING_3_STARS:
                    case RATING_4_STARS:
                    case RATING_5_STARS:
                        ratingCompat = newStarRating(ratingStyle, RatingCompatApi21.getStarRating(obj));
                        break;
                    case RATING_PERCENTAGE:
                        ratingCompat = newPercentageRating(RatingCompatApi21.getPercentRating(obj));
                        break;
                    default:
                        break;
                }
            } else {
                ratingCompat = newUnratedRating(ratingStyle);
            }
            ratingCompat.mRatingObj = obj;
        }
        return ratingCompat;
    }

    public final Object getRating() {
        if (this.mRatingObj != null || VERSION.SDK_INT < 21) {
            return this.mRatingObj;
        }
        if (isRated()) {
            switch (this.mRatingStyle) {
                case RATING_HEART:
                    this.mRatingObj = RatingCompatApi21.newHeartRating(hasHeart());
                    break;
                case RATING_THUMB_UP_DOWN:
                    this.mRatingObj = RatingCompatApi21.newThumbRating(isThumbUp());
                    break;
                case RATING_3_STARS:
                case RATING_4_STARS:
                case RATING_5_STARS:
                    this.mRatingObj = RatingCompatApi21.newStarRating(this.mRatingStyle, getStarRating());
                    break;
                case RATING_PERCENTAGE:
                    this.mRatingObj = RatingCompatApi21.newPercentageRating(getPercentRating());
                    return null;
                default:
                    return null;
            }
        }
        this.mRatingObj = RatingCompatApi21.newUnratedRating(this.mRatingStyle);
        return this.mRatingObj;
    }
}
