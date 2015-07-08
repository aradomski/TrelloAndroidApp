package radomski.edu.pl.trelloapp.tools.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class ListAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {
    public interface OnItemClicked<T> {

        void onItemClicked(T t);
    }

    public interface OnLongItemClicked<T> {

        void onItemClicked(T t);
    }

    private OnItemClicked<T> onItemClickListener;
    private OnLongItemClicked<T> onLongItemClicked;
    private Context context;
    protected List<T> items = new ArrayList<>();


    public ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(holder, items.get(position), position);
    }

    public abstract void onBindViewHolder(VH holder, T item, int position);

    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    protected OnItemClicked<T> getOnItemClickListener() {
        if (onItemClickListener == null) {
            return t -> {
            };
        }
        return onItemClickListener;
    }

    protected OnLongItemClicked<T> getOnLogOnLongItemClicked() {
        if (onLongItemClicked == null) {
            return t -> {
            };
        }
        return onLongItemClicked;
    }

    public void setOnItemClickListener(OnItemClicked<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLongItemClicked(OnLongItemClicked<T> onLongItemClicked) {
        this.onLongItemClicked = onLongItemClicked;
    }

    public Context getContext() {
        return context;
    }
}
