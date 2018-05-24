package rddt.com.redditapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import rddt.com.redditapp.R;
import rddt.com.redditapp.mvp.contract.fragment.ImageFragmentContract;
import rddt.com.redditapp.ui.BaseFragment;

public class ImageFragment
        extends BaseFragment<ImageFragmentContract.View, ImageFragmentContract.Presenter>
        implements ImageFragmentContract.View {

    @BindView(R.id.thumb_imageview)
    ImageView imageView;

    @Nullable
    private static String imageURL;

    public static Fragment newInstance(@Nullable Object data) {
        imageURL = (String) data;
        return new ImageFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.image_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(this).load(imageURL).into(imageView);
    }
}
