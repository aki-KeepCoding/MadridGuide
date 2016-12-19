package info.akixe.madridguide.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import info.akixe.madridguide.R;
import info.akixe.madridguide.fragments.ShopsListFragment;

public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ShopsListFragment shopsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopsListFragment = (ShopsListFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops_list);

        //getShops();

    }

    /*public void getShops() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO();
                List<Shop> shopList = dao.query();
                final Shops shops = Shops.build(shopList);

                ShopsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        shopsListFragment.setShops(shops);
                    }
                });

            }
        }).start();


    }*/

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
