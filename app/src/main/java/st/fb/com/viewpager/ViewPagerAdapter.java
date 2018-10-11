package st.fb.com.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Pair<Integer, String>> mImageIds;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Pair<Integer, String>> imageIds) {
        super(fm);
        mImageIds = imageIds;
    }

    @Override
    public Fragment getItem(int i) {
        Integer imageId = mImageIds.get(i).first;
        String url = mImageIds.get(i).second;
        return ImageViewFragment.createInstance(imageId, url);
    }

    @Override
    public int getCount() {
        return mImageIds == null ? 0 : mImageIds.size();
    }
}
