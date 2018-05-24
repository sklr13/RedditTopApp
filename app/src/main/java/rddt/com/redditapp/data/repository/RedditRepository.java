package rddt.com.redditapp.data.repository;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import rddt.com.redditapp.data.entity.RedditEntry;

public interface RedditRepository {

    @NonNull
    Single<List<RedditEntry>> getTopRedditEntries(int numOfPages);
}
