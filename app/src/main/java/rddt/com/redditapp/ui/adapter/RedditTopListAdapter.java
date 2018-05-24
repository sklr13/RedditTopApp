package rddt.com.redditapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import rddt.com.redditapp.R;
import rddt.com.redditapp.data.entity.RedditEntry;

public class RedditTopListAdapter extends BaseAdapter<RedditEntry> {

    @NonNull
    private OnImageClickListener listener;

    public interface OnImageClickListener {

        void onImageSelected(@Nullable String thumb);
    }

    public RedditTopListAdapter(@NonNull OnImageClickListener listener) {
        this.listener = listener;
    }

    @Override
    int getLayoutId(int viewType) {
        return R.layout.reddit_entry_item;
    }

    @NonNull
    @Override
    public BaseViewHolder<RedditEntry> onCreateViewHolder(@NonNull View view, int viewType) {
        return new RedditEntryHolder(view);
    }

    class RedditEntryHolder extends BaseViewHolder<RedditEntry> {

        @BindView(R.id.author_textview)
        TextView autorTextView;

        @BindView(R.id.thumb_imageview)
        ImageView thumImageView;

        @BindView(R.id.title_textview)
        TextView titleTextView;

        @BindView(R.id.created_textview)
        TextView createdAtTextView;

        @BindView(R.id.comments_textview)
        TextView commentsTextView;

        RedditEntryHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        void bind(@NonNull RedditEntry item) {
            super.bind(item);
            autorTextView.setText(item.getAuthor());
            titleTextView.setText(item.getTitle());
            createdAtTextView.setText(String.valueOf(item.getCreatedUtc()));
            commentsTextView.setText(String.valueOf(item.getNumComments()));

            Glide.with(getContext())
                    .load(item.getThumbnail())
                    .centerCrop()
                    .into(thumImageView);

            thumImageView.setOnClickListener(view -> {
                listener.onImageSelected(item.getThumbnail());
            });
        }
    }
}
