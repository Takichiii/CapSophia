package fr.unice.polytech.capsophia.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.unice.polytech.capsophia.R;
import fr.unice.polytech.capsophia.adapters.ShopCategoriesRecyclerViewAdapter;
import fr.unice.polytech.capsophia.models.Category;
import fr.unice.polytech.capsophia.models.Event;
import fr.unice.polytech.capsophia.util.RealmDBHelper;
import io.realm.RealmResults;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentListener}
 * interface.
 */
public class CategoriesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentListener <Category> mListener;
    private boolean isEvent;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoriesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CategoriesFragment newInstance(boolean isEvent) {
        CategoriesFragment fragment = new CategoriesFragment();
        fragment.isEvent = isEvent;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        //get the list of event
        RealmResults<Category> categories = RealmDBHelper.getInstance(getContext()).findSpecific(Category.class, "isEventCategory", isEvent);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        // Set the adapter
        Context context = view.getContext();
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        recyclerView.setAdapter(new ShopCategoriesRecyclerViewAdapter(getContext(),categories, mListener));

        //Button b = (Button)view.findViewById(R.id.all);


        view.findViewById(R.id.all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                if (isEvent) {
                    RealmResults<Event> allEvents = RealmDBHelper.getInstance(getContext()).find(Event.class);
                    fragment = EventsFragment.newInstance(allEvents);
                }else{
                    fragment = ShopsFragment.newInstance(null);
                }
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_container, fragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        mListener = new OnListFragmentListener<Category>() {
            @Override
            public void onListFragmentInteraction(Category item) {
                Fragment fragment;
                if (isEvent) {
                    fragment = EventsFragment.newInstance(item.getEvents());
                }else{
                    fragment = ShopsFragment.newInstance(item);
                }
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_container, fragment,null)
                        .addToBackStack(null)
                        .commit();
            }
        };

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
