package rddt.com.redditapp.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>> {

    @NonNull
    private final List<T> items = new ArrayList<>();

    @Override
    public final BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflateView(parent, viewType);
        return onCreateViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    private View inflateView(@NonNull ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext())
                .inflate(getLayoutId(viewType), parent, false);
    }

    public void replaceItems(@NonNull List<T> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void removeItem(@NonNull T item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(@NonNull T item) {
        items.add(item);
        notifyItemInserted(items.size());
    }

    public void addItem(@NonNull List<T> items) {
        this.items.addAll(items);
        notifyItemRangeInserted(this.items.size() - items.size(), items.size());
    }

    public void replaceItem(@NonNull T item) {
        int position = items.indexOf(item);
        items.set(position, item);
        notifyItemChanged(position);
    }

    @NonNull
    public List<T> getItems() {
        return items;
    }

    @LayoutRes
    abstract int getLayoutId(int viewType);

    @NonNull
    public abstract BaseViewHolder<T> onCreateViewHolder(@NonNull View view, int viewType);

    static class BaseViewHolder<T> extends RecyclerView.ViewHolder {

        @NonNull
        private final Context context;

        @Nullable
        private T item;

        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        @CallSuper
        void bind(@NonNull T item) {
            this.item = item;
        }

        @NonNull
        T getItem() {
            if (item == null) {
                throw new IllegalStateException("Can't retrieve item before bind call");
            }
            return item;
        }

        @NonNull
        Context getContext() {
            return context;
        }

        @NonNull
        Resources getResources() {
            return context.getResources();
        }
    }
}

