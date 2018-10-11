package st.fb.com.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        final ViewPager viewPager = view.findViewById(R.id.viewPager);

        ArrayList<Pair<Integer,String>> imageAndUrls = new ArrayList<>();
        imageAndUrls.add(new Pair<>(R.drawable.img1, "http://www.google.com"));
        imageAndUrls.add(new Pair<>(R.drawable.img2, "http://www.google.com"));
        imageAndUrls.add(new Pair<>(R.drawable.img3, "http://www.google.com"));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), imageAndUrls);

        final DotsIndicator dotsIndicator = view.findViewById(R.id.dots_indicator);
        dotsIndicator.setDotClickListener(new DotsIndicator.DotClickListener() {
            @Override
            public void onDotSelected(int position) {
                viewPager.setCurrentItem(position);
            }
        });
        dotsIndicator.setNumberOfIndicators(imageAndUrls.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                dotsIndicator.updateSelectedIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        viewPager.setAdapter(adapter);

        return view;
    }
}
