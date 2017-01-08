package info.akixe.madridguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.akixe.madridguide.R;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.util.Constants;

public class ShopDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_shop_detail_shop_name_text)
    TextView shopNameText;

    @BindView(R.id.activity_shop_detail_shop_logo_image)
    ImageView shopLogoImage;

    Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        ButterKnife.bind(this);

        getDetailShopFromCallingIntent();

        updateUI();
    }

    private void getDetailShopFromCallingIntent() {
        Intent intent = getIntent();
        if(intent != null) {
            shop = (Shop) intent.getSerializableExtra(Constants.INTENT_KEY_SHOP_DETAIL);
        }
    }

    private void updateUI() {
        shopNameText.setText(shop.getName());
        Picasso.with(this)
                .load(shop.getLogoImgUrl())
                .into(shopLogoImage);
    }
}
