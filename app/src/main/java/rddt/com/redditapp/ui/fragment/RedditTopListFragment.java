package rddt.com.redditapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import rddt.com.redditapp.R;
import rddt.com.redditapp.data.entity.RedditEntry;
import rddt.com.redditapp.mvp.contract.fragment.RedditTopListFragmentContract;
import rddt.com.redditapp.ui.BaseFragment;
import rddt.com.redditapp.ui.adapter.RedditTopListAdapter;

public class RedditTopListFragment
        extends BaseFragment<RedditTopListFragmentContract.View, RedditTopListFragmentContract.Presenter>
        implements RedditTopListFragmentContract.View {

    private static final int NUM_OF_ELEMENTS_FOR_FIRST_LOAD = 10;

    @BindView(R.id.reddit_top_list)
    RecyclerView redditTopList;

    @Nullable
    private RedditTopListAdapter redditTopListAdapter;

    private int pagesForLoading = NUM_OF_ELEMENTS_FOR_FIRST_LOAD;
    private boolean inLoading = false;

    @NonNull
    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0) {
                startPagination();
            }
        }
    };

    @NonNull
    public static Fragment newInstance() {
        return new RedditTopListFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        redditTopListAdapter = new RedditTopListAdapter(this::imageSelect);
        redditTopList.setAdapter(redditTopListAdapter);
        redditTopList.addOnScrollListener(scrollListener);
        getPresenter().loadTopList(NUM_OF_ELEMENTS_FOR_FIRST_LOAD);
    }

    public void imageSelect(@NonNull String imageUrl) {
        getPresenter().showImage(imageUrl);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.reddit_top_fragment;
    }

    @Override
    public void updateList(List<RedditEntry> redditEntries) {
        if (redditTopListAdapter != null) {
            redditTopListAdapter.replaceItems(redditEntries);
            inLoading = false;
        }
    }

    private void startPagination() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) redditTopList.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

        if (!inLoading && totalItemCount >= NUM_OF_ELEMENTS_FOR_FIRST_LOAD) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                inLoading = true;
                pagesForLoading = pagesForLoading + NUM_OF_ELEMENTS_FOR_FIRST_LOAD;
                getPresenter().loadTopList(pagesForLoading);
            }
        }
    }
}
