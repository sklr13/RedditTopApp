package rddt.com.redditapp.data.repository.network.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import rddt.com.redditapp.data.entity.RedditEntry;
import rddt.com.redditapp.data.repository.network.entity.RedditEntryNetworkModel;
import rddt.com.redditapp.data.repository.network.entity.RedditResponse;
import rddt.com.redditapp.data.repository.network.entity.RedditResponseData;

public class RedditEntryNetworkToDomainMapper implements Function<RedditResponse<RedditResponseData>, List<RedditEntry>> {

    @Inject
    public RedditEntryNetworkToDomainMapper() {
    }

    @Override
    @NonNull
    public List<RedditEntry> apply(RedditResponse<RedditResponseData> redditNetworkModel) {

        ArrayList<RedditEntry> redditEntiries = new ArrayList<>();
        List<RedditResponse<RedditEntryNetworkModel>> list = redditNetworkModel.getData().getChildren();

        for (RedditResponse<RedditEntryNetworkModel> response : list) {
            RedditEntry redditEntry = new RedditEntry();
            redditEntry.setAuthor(response.getData().getAuthor());
            redditEntry.setCreated(response.getData().getCreated());
            redditEntry.setCreatedUtc(response.getData().getCreatedUtc());
            redditEntry.setId(response.getData().getId());
            redditEntry.setNumComments(response.getData().getNumComments());
            redditEntry.setThumbnail(response.getData().getThumbnail());
            redditEntry.setTitle(response.getData().getTitle());
            redditEntiries.add(redditEntry);
        }

        return redditEntiries;
    }
}
