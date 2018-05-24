package rddt.com.redditapp.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import rddt.com.redditapp.data.repository.network.RedditEntryNetworkRepository;
import rddt.com.redditapp.data.repository.network.mapper.RedditEntryNetworkToDomainMapper;
import rddt.com.redditapp.data.repository.network.retrofit.NetworkInterface;
import rddt.com.redditapp.di.scope.ActivityScope;

@Module
public class RepositoryModule {

    @NonNull
    @ActivityScope
    @Provides
    public RedditEntryNetworkRepository provideNetworkRepository(NetworkInterface networkInterface,
                                                                 RedditEntryNetworkToDomainMapper userNetworkToDomainMapper) {
        return new RedditEntryNetworkRepository(networkInterface, userNetworkToDomainMapper);
    }

}
