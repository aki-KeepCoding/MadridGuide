package info.akixe.madridguide.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import info.akixe.madridguide.R;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.views.base.POIRowViewHolder;


public class ShopRowViewHolder extends POIRowViewHolder<Shop> {
    private WeakReference<Context> context;

    private TextView nameTextView;
    private ImageView logoImageView;


    public ShopRowViewHolder(View rowShop) {
        super(rowShop);
        context = new WeakReference<Context>(rowShop.getContext());
        bindViews(rowShop);
    }

    @Override
    public void bindViews(View rowShop) {
        nameTextView = (TextView) rowShop.findViewById(R.id.row_shop_name);
        logoImageView = (ImageView) rowShop.findViewById(R.id.row_shop_logo);
    }

    @Override
    public void setModel(@NonNull Shop shop) {
        if (shop == null) {
            return;
        }
        nameTextView.setText(shop.getName());
        Picasso.with(context.get())
                .load(shop.getLogoImgUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(logoImageView);
    }

}
