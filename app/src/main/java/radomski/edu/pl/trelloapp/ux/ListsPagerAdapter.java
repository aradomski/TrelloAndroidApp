package radomski.edu.pl.trelloapp.ux;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import radomski.edu.pl.trelloapp.api.boards.TroelloList;
import radomski.edu.pl.trelloapp.api.cards.Card;
import radomski.edu.pl.trelloapp.holdr.Holdr_MainPage;
import radomski.edu.pl.trelloapp.tools.ui.ListAdapter;
import radomski.edu.pl.trelloapp.tools.ui.SimpleDividerItemDecoration;
import radomski.edu.pl.trelloapp.tools.ui.ViewPagerAdapter;

/**
 * Created by adam on 7/8/15.
 */
public class ListsPagerAdapter extends ViewPagerAdapter<Map.Entry<TroelloList, List<Card>>> {
    @Inject
    Context context;
    private ListAdapter.OnItemClicked<Card> onItemClickListener;
    private ListAdapter.OnLongItemClicked<Card> onLongItemClickListener;

    @Inject
    public ListsPagerAdapter() {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getKey().getName();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public View getView(ViewGroup parent, Map.Entry<TroelloList, List<Card>> item, int position) {
        Holdr_MainPage holdr_mainPage = new Holdr_MainPage(LayoutInflater.from(context).inflate(Holdr_MainPage.LAYOUT, parent, false));
        CardAdapter cardAdapter = new CardAdapter(context);
        holdr_mainPage.mainPage.setLayoutManager(new LinearLayoutManager(context));
        holdr_mainPage.mainPage.addItemDecoration(new SimpleDividerItemDecoration(context));
        holdr_mainPage.mainPage.setAdapter(cardAdapter);
        cardAdapter.setItems(item.getValue());
        cardAdapter.setOnItemClickListener(onItemClickListener);
        cardAdapter.setOnLongItemClicked(onLongItemClickListener);
        return holdr_mainPage.getView();
    }

    public void setOnItemClickListener(ListAdapter.OnItemClicked<Card> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLongItemClickListener(ListAdapter.OnLongItemClicked<Card> onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }
}
