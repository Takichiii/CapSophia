package fr.unice.polytech.capsophia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.unice.polytech.capsophia.R;
import fr.unice.polytech.capsophia.fragments.OnListFragmentListener;
import fr.unice.polytech.capsophia.fragments.dummy.DummyContent.DummyItem;
import fr.unice.polytech.capsophia.models.Category;
import fr.unice.polytech.capsophia.models.Event;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class EventsCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<GenericViewHolder> {

    private final List<Category> mValues;
    private final OnListFragmentListener<Category> mListener;
    Context context;

    public EventsCategoriesRecyclerViewAdapter(Context context, List<Category> items, OnListFragmentListener<Category> listener) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_category, parent, false);
        return new GenericViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GenericViewHolder holder, int position) {
        final Category category = mValues.get(position);
        holder.mItem = category;
        holder.mDescription.setText(category.getName());
        Picasso.with(context)
                .load(category.getImage())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(holder.mImage);

        //holder.i.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(category);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}
