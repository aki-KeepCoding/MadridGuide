package info.akixe.madridguide.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import info.akixe.madridguide.R;
import info.akixe.madridguide.fragments.ShopsListFragment;
import info.akixe.madridguide.interactors.GetAllShopsFromLocalCacheInteractor;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;
import info.akixe.madridguide.navigator.Navigator;
import info.akixe.madridguide.views.base.OnPOIElementClick;

public class ShopsActivity extends AppCompatActivity {

    private ShopsListFragment shopsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopsListFragment = (ShopsListFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops_list);

        GetAllShopsFromLocalCacheInteractor i = new GetAllShopsFromLocalCacheInteractor();
        i.execute(this, new GetAllShopsFromLocalCacheInteractor.OnGetAllShopsFromLocalCache() {
            @Override
            public void completion(Shops shops) {
                shopsListFragment.setListener(new OnPOIElementClick<Shop>() {
                    @Override
                    public void clikedOn(Shop shop, int position) {
                        Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
                    }
                });


                shopsListFragment.setShops(shops);
            }
        });
    }
}
