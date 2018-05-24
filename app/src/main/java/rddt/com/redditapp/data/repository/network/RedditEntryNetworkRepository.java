package rddt.com.redditapp.data.repository.network;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import rddt.com.redditapp.data.entity.RedditEntry;
import rddt.com.redditapp.data.repository.RedditRepository;
import rddt.com.redditapp.data.repository.network.mapper.RedditEntryNetworkToDomainMapper;
import rddt.com.redditapp.data.repository.network.retrofit.NetworkInterface;

public class RedditEntryNetworkRepository implements RedditRepository {

    @NonNull
    private final NetworkInterface networkInterface;

    @NonNull
    private final RedditEntryNetworkToDomainMapper redditEntryNetworkToDomainMapper;

    public RedditEntryNetworkRepository(@NonNull NetworkInterface networkInterface,
                                        @NonNull RedditEntryNetworkToDomainMapper redditNetworkToDomainMapper) {
        this.networkInterface = networkInterface;
        this.redditEntryNetworkToDomainMapper = redditNetworkToDomainMapper;
    }

    @NonNull
    @Override
    public Single<List<RedditEntry>> getTopRedditEntries(int numOfPages) {
        return networkInterface.getTopRedditEntries(numOfPages).map(redditEntryNetworkToDomainMapper);
    }
}
