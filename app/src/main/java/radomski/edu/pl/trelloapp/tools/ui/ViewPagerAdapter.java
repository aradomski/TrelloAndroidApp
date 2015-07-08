package radomski.edu.pl.trelloapp.tools.ui;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import radomski.edu.pl.trelloapp.api.boards.TroelloList;
import radomski.edu.pl.trelloapp.api.cards.Card;

public abstract class ViewPagerAdapter<T> extends PagerAdapter {

    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void setItems(List<T> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(container, getItem(position), position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public abstract View getView(ViewGroup parent, T item, int position);

}