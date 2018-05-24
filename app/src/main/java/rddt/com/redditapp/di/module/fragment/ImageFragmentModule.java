package rddt.com.redditapp.di.module.fragment;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import rddt.com.redditapp.di.qualifiers.execution.Background;
import rddt.com.redditapp.di.qualifiers.execution.Foreground;
import rddt.com.redditapp.di.scope.FragmentScope;
import rddt.com.redditapp.mvp.contract.fragment.ImageFragmentContract;
import rddt.com.redditapp.mvp.presenter.fragment.ImageFragmentPresenter;
import rddt.com.redditapp.ui.navigation.CustomRouter;

@Module
public class ImageFragmentModule {

    @FragmentScope
    @Provides
    ImageFragmentContract.Presenter providePresenter(@Background Scheduler backgroundScheduler,
                                                     @Foreground Scheduler foregroundScheduler) {
        return new ImageFragmentPresenter(backgroundScheduler, foregroundScheduler);
    }
}
