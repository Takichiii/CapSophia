package fr.unice.polytech.capsophia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.unice.polytech.capsophia.fragments.OnListFragmentListener;
import fr.unice.polytech.capsophia.models.Shop;
import fr.unice.polytech.capsophia.R;

public class ShopsRecyclerViewAdapter extends RecyclerView.Adapter<ShopsRecyclerViewAdapter.MyViewHolder> {

    private final List<Shop> mValues;
    private final OnListFragmentListener<Shop> mListener;
    Context context;

    public ShopsRecyclerViewAdapter(Context context, List<Shop> items, OnListFragmentListener<Shop> listener) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_shop_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.setContext(context);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Shop shop = mValues.get(position);
        holder.mItem = shop;
        holder.mDescription.setText(shop.getName());
        //holder.mPrice.setText(shop.getLocation());
        Picasso.with(context)
                .load(shop.getLogoPath())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(holder.mImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(shop);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


     class MyViewHolder extends GenericViewHolder {
         //final TextView mPrice;

         MyViewHolder(View view) {
            super(view);
            //this.mPrice = (TextView) view.findViewById(R.id.location);

        }
    }

}
