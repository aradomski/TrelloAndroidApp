package radomski.edu.pl.trelloapp.ux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import radomski.edu.pl.trelloapp.api.cards.Card;
import radomski.edu.pl.trelloapp.holdr.Holdr_MainRow;
import radomski.edu.pl.trelloapp.tools.ui.ListAdapter;

/**
 * Created by adam on 7/8/15.
 */
public class CardAdapter extends ListAdapter<Holdr_MainRow, Card> {

    public CardAdapter(Context context) {
        super(context);
        setHasStableIds(true);
    }

    @Override
    public Holdr_MainRow onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holdr_MainRow(LayoutInflater.from(getContext()).inflate(Holdr_MainRow.LAYOUT, parent, false));
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).id.hashCode();
    }

    @Override
    public void onBindViewHolder(Holdr_MainRow holder, Card item, int position) {
        holder.cardName.setText(item.name);
        holder.cardDesc.setText(item.desc);
        holder.cardWrapper.setOnClickListener(v -> getOnItemClickListener().onItemClicked(item));
        holder.cardWrapper.setOnLongClickListener(v -> {
            getOnLogOnLongItemClicked().onItemClicked(item);
            return true;
        });
    }
}
