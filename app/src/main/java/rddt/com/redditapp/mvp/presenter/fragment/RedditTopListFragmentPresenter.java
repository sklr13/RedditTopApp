package rddt.com.redditapp.mvp.presenter.fragment;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import rddt.com.redditapp.R;
import rddt.com.redditapp.data.entity.RedditEntry;
import rddt.com.redditapp.data.repository.network.RedditEntryNetworkRepository;
import rddt.com.redditapp.mvp.BasePresenter;
import rddt.com.redditapp.mvp.contract.fragment.RedditTopListFragmentContract;
import rddt.com.redditapp.ui.navigation.CustomRouter;
import rddt.com.redditapp.ui.navigation.Screens;

public class RedditTopListFragmentPresenter
        extends BasePresenter<RedditTopListFragmentContract.View>
        implements RedditTopListFragmentContract.Presenter {

    @NonNull
    private final CustomRouter customRouter;

    @NonNull
    private final RedditEntryNetworkRepository redditEntryNetworkRepository;

    public RedditTopListFragmentPresenter(@NonNull Scheduler backgroundScheduler,
                                          @NonNull Scheduler foregroundScheduler,
                                          @NonNull CustomRouter customRouter,
                                          @NonNull RedditEntryNetworkRepository redditEntryNetworkRepository) {
        super(backgroundScheduler, foregroundScheduler);
        this.customRouter = customRouter;
        this.redditEntryNetworkRepository = redditEntryNetworkRepository;
    }

    @Override
    public void loadTopList(int numOfElementsToLoad) {
        subscribe(redditEntryNetworkRepository.getTopRedditEntries(numOfElementsToLoad), this::onListLoaded);
    }

    @Override
    public void showImage(@NonNull String imageUrl) {
        customRouter.navigateTo(Screens.IMAGE_FRAGMENT, imageUrl);
    }

    private void onListLoaded(List<RedditEntry> redditEntries) {
        if (isViewAttached()) {
            getView().updateList(redditEntries);
        }
    }
}
