package info.akixe.madridguide.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.akixe.madridguide.R;
import info.akixe.madridguide.adapters.ShopsAdapter;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;


public class ShopsListFragment extends Fragment {

    private RecyclerView shopsRecyclerView;
    private ShopsAdapter adapter;
    private Shops shops;

    public ShopsListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shops_list, container, false);
        // TODO: 18/12/16 ButterKnife para obtener el recycler view en ShopsListFragment?
        shopsRecyclerView = (RecyclerView) view.findViewById(R.id.shops_list_recycler_view);
        shopsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void updateUI() {
        adapter = new ShopsAdapter(shops, getActivity());
        shopsRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new ShopsAdapter.OnElementClick() {
            @Override
            public void clikedOn(Shop shop, int position) {
                // TODO: 19/12/16 Show shop detail
            }
        });

    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
        updateUI();
    }
}
