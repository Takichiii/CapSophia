package fr.unice.polytech.capsophia.fragments;

import android.content.Context;
import android.content.Intent;
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

import fr.unice.polytech.capsophia.R;
import fr.unice.polytech.capsophia.activities.ProductActivity;
import fr.unice.polytech.capsophia.adapters.ProductsRecyclerViewAdapter;
import fr.unice.polytech.capsophia.models.Product;
import fr.unice.polytech.capsophia.models.Shop;


/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class ProductsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentListener<Product> mListener;
    List<Product> products;
    //Shop shop;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProductsFragment newInstance(List<Product> products) {
        ProductsFragment fragment = new ProductsFragment();
        fragment.products = products;
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
            recyclerView.setAdapter(new ProductsRecyclerViewAdapter(getContext(),products, mListener));


        EditText search = (EditText) view.findViewById(R.id.search_boutique);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<Product> _products = new ArrayList<>();
                if (s.toString().isEmpty()) {
                    _products = products;
                }else {
                    for (Product b: products){
                        if (b.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                            _products.add(b);
                        }
                    }
                }

                recyclerView.setAdapter(new ProductsRecyclerViewAdapter(getContext(),_products, mListener));
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
        mListener = new OnListFragmentListener<Product>() {
            @Override
            public void onListFragmentInteraction(Product item) {
                Intent intent = new Intent(getContext(),ProductActivity.class);
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
