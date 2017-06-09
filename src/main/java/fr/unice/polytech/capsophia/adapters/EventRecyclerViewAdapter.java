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
import fr.unice.polytech.capsophia.models.Event;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.MyViewHolder> {

    private final List<Event> mValues;
    private final OnListFragmentListener<Event> mListener;
    Context context;

    public EventRecyclerViewAdapter(Context context, List<Event> items, OnListFragmentListener<Event> listener) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.setContext(context);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Event event = mValues.get(position);
        holder.mItem = event;
        holder.mDescription.setText(event.getDescription());
        holder.setEvent(event);
        holder.mDate.setText(""+ event.getDateDebut());
        holder.mDate.setCompoundDrawables(context.getResources().getDrawable(R.drawable.calendar, null), null,null,null);
        Picasso.with(context)
                .load(event.getImagePath())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(holder.mImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(event);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }


     class MyViewHolder extends GenericViewHolder {
         final TextView mDate;

         MyViewHolder(View view) {
            super(view);
            this.mDate = (TextView) view.findViewById(R.id.date);

        }
    }

}
