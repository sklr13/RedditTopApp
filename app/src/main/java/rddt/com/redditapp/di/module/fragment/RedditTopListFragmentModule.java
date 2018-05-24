package rddt.com.redditapp.di.module.fragment;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import rddt.com.redditapp.data.repository.network.RedditEntryNetworkRepository;
import rddt.com.redditapp.di.qualifiers.execution.Background;
import rddt.com.redditapp.di.qualifiers.execution.Foreground;
import rddt.com.redditapp.di.scope.FragmentScope;
import rddt.com.redditapp.mvp.contract.fragment.RedditTopListFragmentContract;
import rddt.com.redditapp.mvp.presenter.fragment.RedditTopListFragmentPresenter;
import rddt.com.redditapp.ui.navigation.CustomRouter;

@Module
public class RedditTopListFragmentModule {

    @FragmentScope
    @Provides
    RedditTopListFragmentContract.Presenter providePresenter(@Background Scheduler backgroundScheduler,
                                                             @Foreground Scheduler foregroundScheduler,
                                                             CustomRouter customRouter,
                                                             RedditEntryNetworkRepository redditEntryNetworkRepository) {
        return new RedditTopListFragmentPresenter(backgroundScheduler,
                foregroundScheduler,
                customRouter,
                redditEntryNetworkRepository);
    }
}
