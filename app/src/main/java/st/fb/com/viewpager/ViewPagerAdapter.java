package st.fb.com.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Integer> mImageIds;

    public ViewPagerAdapter(FragmentManager fm, List<Integer> imageIds) {
        super(fm);
        mImageIds = imageIds;
    }

    @Override
    public Fragment getItem(int i) {
        return ImageViewFragment.createInstance(mImageIds.get(i));
    }

    @Override
    public int getCount() {
        return mImageIds == null ? 0 : mImageIds.size();
    }
}
