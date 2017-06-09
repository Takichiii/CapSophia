package fr.unice.polytech.capsophia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.unice.polytech.capsophia.R;
import fr.unice.polytech.capsophia.fragments.OnListFragmentListener;
import fr.unice.polytech.capsophia.models.Product;

public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.MyViewHolder> {

    private final List<Product> mValues;
    private final OnListFragmentListener<Product> mListener;
    Context context;

    public ProductsRecyclerViewAdapter(Context context, List<Product> items, OnListFragmentListener<Product> listener) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Product product = mValues.get(position);
        holder.mItem = product;
        holder.mDescription.setText(product.getDescription());
        holder.favoriteProduct(product);
        holder.mPrice.setText(""+product.getPrice());
        holder.mPrice.setCompoundDrawables(context.getResources().getDrawable(R.drawable.pricetag, null), null,null,null);
        Picasso.with(context)
                .load(product.getPicturePath())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(holder.mImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(product);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


     class MyViewHolder extends GenericViewHolder {
         final TextView mPrice;

         MyViewHolder(View view) {
            super(view);
            this.mPrice = (TextView) view.findViewById(R.id.price);

        }
    }

}
