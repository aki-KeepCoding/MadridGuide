package info.akixe.madridguide.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import info.akixe.madridguide.R;
import info.akixe.madridguide.model.Shop;


public class ShopRowViewHolder extends RecyclerView.ViewHolder {
    //@BindView(R.id.row_shop_name)
    private TextView nameTextView;

    //@BindView(R.id.row_shop_logo)
    private ImageView logoImageView;

    private WeakReference<Context> context;

    public ShopRowViewHolder(View rowShop) {
        super(rowShop);
        context = new WeakReference<Context>(rowShop.getContext());

        // TODO: 18/12/16 revisar si funciona ButterKnife en ViewHolder
        nameTextView = (TextView) rowShop.findViewById(R.id.row_shop_name);
        logoImageView = (ImageView) rowShop.findViewById(R.id.row_shop_logo);

        //ButterKnife.bind(this, rowShop);
    }

    public void setShop(final @NonNull Shop shop) {
        if (shop == null) {
            return;
        }
        nameTextView.setText(shop.getName());
        Picasso.with(context.get())
                .load(shop.getLogoImgUrl())
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(logoImageView);
    }
}
