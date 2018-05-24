package rddt.com.redditapp.data.repository.network.retrofit;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import rddt.com.redditapp.data.repository.network.entity.RedditEntryNetworkModel;
import rddt.com.redditapp.data.repository.network.entity.RedditResponse;
import rddt.com.redditapp.data.repository.network.entity.RedditResponseData;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("/top.json")
    Single<RedditResponse<RedditResponseData>> getTopRedditEntries(@Query("limit") int limit);

}
