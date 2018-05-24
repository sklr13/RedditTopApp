package rddt.com.redditapp.mvp.contract.fragment;

import android.support.annotation.NonNull;

import java.util.List;

import rddt.com.redditapp.data.entity.RedditEntry;
import rddt.com.redditapp.mvp.BaseContract;

public interface RedditTopListFragmentContract {

    interface View extends BaseContract.View {

        void updateList(List<RedditEntry> redditEntries);

        void imageSelect(@NonNull String imageUrl);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadTopList(int numOfLoadedElements);

        void showImage(@NonNull String imageUrl);
    }
}
