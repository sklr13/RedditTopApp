package rddt.com.redditapp.di.module.activity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import rddt.com.redditapp.di.module.fragment.ImageFragmentModule;
import rddt.com.redditapp.di.module.fragment.RedditTopListFragmentModule;
import rddt.com.redditapp.di.scope.FragmentScope;
import rddt.com.redditapp.ui.fragment.ImageFragment;
import rddt.com.redditapp.ui.fragment.RedditTopListFragment;

@Module
public interface MainActivityFragmentProviderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {
            RedditTopListFragmentModule.class,

    })
    RedditTopListFragment contributesRedditTopListFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {
            ImageFragmentModule.class,

    })
    ImageFragment contributesImageFragment();
}
