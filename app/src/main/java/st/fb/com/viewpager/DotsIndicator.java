package st.fb.com.viewpager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DotsIndicator extends LinearLayout {

    private static int NOTHING_SELECTED = -1;
    private int mCurrentlySelectedPosition = NOTHING_SELECTED; // indicate no dots are selected;


    public interface DotClickListener {
        void onDotSelected(int position);
    }

    private DotClickListener mDotClickListener;

    public DotsIndicator(Context context) {
        super(context);
        init();
    }

    public DotsIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setDotClickListener(DotClickListener dotClickListener) {
        mDotClickListener = dotClickListener;
    }


    public void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    public void setNumberOfIndicators(int count) {
        addDots(count);
        updateSelectedIndicator(0);
    }

    private void addDots(int numOfDots) {
        removeAllViews();
        for (int i = 0; i < numOfDots; i++) {
            ImageView view = createDotView();
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateSelectedIndicator(position);
                    if (mDotClickListener != null)
                        mDotClickListener.onDotSelected(position);
                }
            });
            addView(view);
            setBg(view, R.drawable.default_dot);
        }
    }

    @NonNull
    private ImageView createDotView() {
        ImageView view = new ImageView(getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams((int) dpToPx(32), (int) dpToPx(32)));
        return view;
    }

    private void setBg(ImageView view, int resId) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), resId);
        view.setImageDrawable(drawable);
    }

    public void updateSelectedIndicator(int position) {
        if (getChildCount() >= position && mCurrentlySelectedPosition != position) {
            if (mCurrentlySelectedPosition != NOTHING_SELECTED) {
                ImageView imageView = (ImageView) getChildAt(mCurrentlySelectedPosition);
                setBg(imageView, R.drawable.default_dot);
            }
            ImageView newlyActivatedDot = (ImageView) getChildAt(position);
            setBg(newlyActivatedDot, R.drawable.selected_dot);
            mCurrentlySelectedPosition = position;
        }
    }

    public float dpToPx(float dp) {
        Resources resources = getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}
