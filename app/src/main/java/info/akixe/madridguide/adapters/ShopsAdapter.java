package info.akixe.madridguide.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.akixe.madridguide.R;
import info.akixe.madridguide.adapters.base.POIAdapter;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;
import info.akixe.madridguide.views.ShopRowViewHolder;
import info.akixe.madridguide.views.base.OnPOIElementClick;

public class ShopsAdapter extends POIAdapter<Shop> {

    private final LayoutInflater layoutInflater;


    private OnPOIElementClick<Shop> listener;

    public ShopsAdapter(Shops shops, Context context) {
        super(shops.all(), context);
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ShopRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_shop, parent, false);
        return new ShopRowViewHolder(view);
    }
}
