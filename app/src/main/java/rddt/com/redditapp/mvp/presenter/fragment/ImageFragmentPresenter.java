package rddt.com.redditapp.mvp.presenter.fragment;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import rddt.com.redditapp.mvp.BasePresenter;
import rddt.com.redditapp.mvp.contract.fragment.ImageFragmentContract;

public class ImageFragmentPresenter extends BasePresenter<ImageFragmentContract.View>
        implements ImageFragmentContract.Presenter {

    public ImageFragmentPresenter(@NonNull Scheduler backgroundScheduler,
                                  @NonNull Scheduler foregroundScheduler) {
        super(backgroundScheduler, foregroundScheduler);
    }
}
