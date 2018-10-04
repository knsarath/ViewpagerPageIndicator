package st.fb.com.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        ArrayList<Integer> imageIds = new ArrayList<>();
        imageIds.add(R.drawable.img1);
        imageIds.add(R.drawable.img2);
        imageIds.add(R.drawable.img3);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), imageIds);

       // Following is the usage of Dots indicator

        final DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
        dotsIndicator.setNumberOfIndicators(imageIds.size());
        dotsIndicator.setDotClickListener(new DotsIndicator.DotClickListener() {
            @Override
            public void onDotSelected(int position) {
                viewPager.setCurrentItem(position);
            }
        });

        dotsIndicator.setDotChangeDuration(200); // default is 300
        dotsIndicator.setShouldAnimateDots(false); // default is true

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                dotsIndicator.updateSelectedIndicator(position); // change the dots indicator position
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        viewPager.setAdapter(adapter);
    }
}
