package fr.unice.polytech.capsophia.fragments;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.capsophia.R;
import fr.unice.polytech.capsophia.adapters.EventRecyclerViewAdapter;
import fr.unice.polytech.capsophia.models.Event;


/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class EventsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentListener<Event> mListener;
    List<Event> events;
    //Category category;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EventsFragment newInstance(List<Event> events) {
        EventsFragment fragment = new EventsFragment();
        fragment.events = events;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        //shops = Shop.createList(getResources().getStringArray(R.array.boutiques));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        // Set the adapter
            Context context = view.getContext();

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new EventRecyclerViewAdapter(getContext(),events, mListener));

        EditText search = (EditText) view.findViewById(R.id.search_boutique);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<Event> _events = new ArrayList<>();
                if (s.toString().isEmpty()) {
                    _events = events;
                }else {
                    for (Event b: events){
                        if (b.name.toLowerCase().contains(s.toString().toLowerCase())) {
                            _events.add(b);
                        }
                    }
                }

                recyclerView.setAdapter(new EventRecyclerViewAdapter(getContext(),_events, mListener));
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = new OnListFragmentListener<Event>() {
            @Override
            public void onListFragmentInteraction(Event item) {

                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");

                Calendar cal = Calendar.getInstance();
                long startTime = cal.getTimeInMillis();
                long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, item.getDateDebut());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

                intent.putExtra(CalendarContract.Events.TITLE, item.getName());
                intent.putExtra(CalendarContract.Events.DESCRIPTION,  item.getDescription());
                intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

                startActivity(intent);

                intent.putExtra("name",item.getName());
                startActivity(intent);
            }
        };
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
