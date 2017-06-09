package fr.unice.polytech.capsophia.fragments;

import android.content.Context;
import android.os.Bundle;
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

import fr.unice.polytech.capsophia.models.Category;
import fr.unice.polytech.capsophia.models.Shop;
import fr.unice.polytech.capsophia.R;
import fr.unice.polytech.capsophia.adapters.ShopsRecyclerViewAdapter;
import fr.unice.polytech.capsophia.util.RealmDBHelper;


/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class ShopsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentListener<Shop> mListener;

    List<Shop> shops;
    //Category category;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShopsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ShopsFragment newInstance(Category category) {
        ShopsFragment fragment = new ShopsFragment();
        if (category!=null){
            fragment.shops = category.getShops();
        }

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
        if (shops == null){
            shops = RealmDBHelper.getInstance(getContext()).find(Shop.class);
        }
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        // Set the adapter
            Context context = view.getContext();

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ShopsRecyclerViewAdapter(getContext(),shops, mListener));

        EditText search = (EditText) view.findViewById(R.id.search_boutique);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<Shop> _shops = new ArrayList<>();
                if (s.toString().isEmpty()) {
                    _shops = shops;
                }else {
                    for (Shop b: shops){
                        if (b.name.toLowerCase().contains(s.toString().toLowerCase())) {
                            _shops.add(b);
                        }
                    }
                }

                recyclerView.setAdapter(new ShopsRecyclerViewAdapter(getContext(),_shops, mListener));
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }


    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        mListener = new OnListFragmentListener<Shop>() {
            @Override
            public void onListFragmentInteraction(Shop item) {
                Fragment fragment = ProductsFragment.newInstance(item.getProductsList());
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
