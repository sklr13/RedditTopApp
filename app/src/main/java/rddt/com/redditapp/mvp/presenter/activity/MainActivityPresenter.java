package rddt.com.redditapp.mvp.presenter.activity;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import rddt.com.redditapp.mvp.BasePresenter;
import rddt.com.redditapp.mvp.contract.activity.MainActivityContract;
import rddt.com.redditapp.ui.navigation.CustomRouter;
import rddt.com.redditapp.ui.navigation.Screens;

public class MainActivityPresenter
        extends BasePresenter<MainActivityContract.View>
        implements MainActivityContract.Presenter {

    @NonNull
    private final CustomRouter customRouter;

    public MainActivityPresenter(@NonNull Scheduler backgroundScheduler,
                                 @NonNull Scheduler foregroundScheduler,
                                 @NonNull CustomRouter customRouter) {
        super(backgroundScheduler, foregroundScheduler);
        this.customRouter = customRouter;
    }

    @Override
    public void showMainFragment() {
        customRouter.newRootScreen(Screens.TOP_LIST_FRAGMENT);
    }
}
