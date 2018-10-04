package st.fb.com.viewpager;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageViewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int imageId = getArguments().getInt("imageId");
        imageView.setBackgroundResource(imageId);
        return imageView;
    }

    public static ImageViewFragment createInstance(@DrawableRes int imageId) {
        ImageViewFragment imageViewFragment = new ImageViewFragment();
        Bundle args = new Bundle();
        args.putInt("imageId", imageId);
        imageViewFragment.setArguments(args);
        return imageViewFragment;
    }
}
