package fr.unice.polytech.capsophia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.unice.polytech.capsophia.R;
import fr.unice.polytech.capsophia.models.Event;
import fr.unice.polytech.capsophia.models.Product;
import fr.unice.polytech.capsophia.util.RealmDBHelper;
import io.realm.RealmModel;

import android.support.design.widget.FloatingActionButton;


/**
 * Created by jihane on 04/06/2017.
 */



public class GenericViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView mDescription;
    public final ImageView mImage;
    public RealmModel mItem;
    private FloatingActionButton button;
    private Context context;

    public GenericViewHolder(View view) {
        super(view);
        mView = view;
        mDescription = (TextView) view.findViewById(R.id.description);
        mImage = (ImageView) view.findViewById(R.id.image);
        button = (FloatingActionButton) view.findViewById(R.id.floatingButton);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mDescription.getText() + "'";
    }


    void setEvent(final Event event) {
        mView.setVisibility(View.VISIBLE);
        if(event.isSaved()){
            setButton(R.drawable.ic_clear_black_12dp);
            //calendarUpdater.update(event);
        }else {
            setButton(R.drawable.ic_add_black_12dp);
            //calendarUpdater.update(event);
        }

        //Listener sur le bouton de réservation/favoris.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmDBHelper.getInstance(context).setSave(event);
                setEvent(event);
            }
        });
    }

    void favoriteProduct(final Product product) {
        mView.setVisibility(View.VISIBLE);
        if(product.isFavorited()){
            setButton(R.drawable.ic_clear_black_12dp);
            //calendarUpdater.update(event);
        }else {
            setButton(R.drawable.ic_add_black_12dp);
            //calendarUpdater.update(event);
        }

        //Listener sur le bouton de réservation/favoris.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmDBHelper.getInstance(context).setFavorite(product);
                favoriteProduct(product);
            }
        });
    }


    private void setButton(int res) {
        button.setImageResource(res);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}