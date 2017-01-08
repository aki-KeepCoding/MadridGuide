package info.akixe.madridguide.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import info.akixe.madridguide.R;
import info.akixe.madridguide.fragments.ShopsListFragment;
import info.akixe.madridguide.interactors.GetAllShopsFromLocalCacheInteractor;
import info.akixe.madridguide.manager.db.DBConstants;
import info.akixe.madridguide.manager.db.ShopDAO;
import info.akixe.madridguide.manager.db.provider.MadridGuideProvider;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;
import info.akixe.madridguide.navigator.Navigator;
import info.akixe.madridguide.views.OnElementClick;

public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

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
                shopsListFragment.setShops(shops);
            }
        });


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final CursorLoader loader = new CursorLoader(this,
                MadridGuideProvider.SHOPS_URI,
                DBConstants.ALL_COLUMNS, // Projection
                null, // Where
                null, // Where fields
                null // Order By
        );
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        final Shops shops = ShopDAO.getShops(data);
        shopsListFragment.setListener(new OnElementClick<Shop>() {
            @Override
            public void clikedOn(Shop shop, int position) {
                Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
            }
        });
        shopsListFragment.setShops(shops);
    }



    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
