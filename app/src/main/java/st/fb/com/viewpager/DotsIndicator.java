package st.fb.com.viewpager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DotsIndicator extends FrameLayout {

    private ImageView mSelectedDot;
    private LinearLayout mDotsContainer;
    private int mDotChangeDuration = 300;
    private boolean shouldAnimateDots = true;

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

    public void setDotChangeDuration(int dotChangeDuration) {
        mDotChangeDuration = dotChangeDuration;
    }

    public void setShouldAnimateDots(boolean shouldAnimateDots) {
        this.shouldAnimateDots = shouldAnimateDots;
    }

    public void init() {

    }

    public void setNumberOfIndicators(int count) {
        addDots(count);
        updateSelectedIndicator(0);
    }

    private void addDots(int numOfDots) {
        removeAllViews();
        mDotsContainer = new LinearLayout(getContext());
        mDotsContainer.setOrientation(LinearLayout.HORIZONTAL);
        mDotsContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mDotsContainer);
        mSelectedDot = createDotView();
        mSelectedDot.setVisibility(GONE);
        setBg(mSelectedDot, R.drawable.selected_dot);
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
            mDotsContainer.addView(view);
            setBg(view, R.drawable.default_dot);
        }

    }

    @NonNull
    private ImageView createDotView() {
        ImageView view = new ImageView(getContext());
        view.setPadding(8,8,8,8);
        view.setLayoutParams(new ViewGroup.LayoutParams((int) dpToPx(18), (int) dpToPx(18)));
        return view;
    }

    private void setBg(ImageView view, int resId) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), resId);
        view.setImageDrawable(drawable);
    }

    public void updateSelectedIndicator(int position) {
        if (mDotsContainer != null && mDotsContainer.getChildCount() >= position) {
            ImageView childAtPosition = (ImageView) mDotsContainer.getChildAt(position);
            float x = childAtPosition.getX();
            float y = childAtPosition.getY();
            int duration = mSelectedDot.getParent() == null ? 0 : mDotChangeDuration;
            mSelectedDot.animate()
                    .setDuration(shouldAnimateDots ? duration : 0)
                    .x(x)
                    .y(y)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mSelectedDot.setVisibility(VISIBLE);
                        }
                    })
                    .start();
            if (mSelectedDot.getParent() == null) {
                addView(mSelectedDot);
            }
        }
    }

    public float dpToPx(float dp) {
        Resources resources = getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}
