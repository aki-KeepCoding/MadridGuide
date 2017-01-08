package info.akixe.madridguide.navigator;

import android.content.Intent;

import info.akixe.madridguide.activities.MainActivity;
import info.akixe.madridguide.activities.ShopDetailActivity;
import info.akixe.madridguide.activities.ShopsActivity;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.util.Constants;

public class Navigator {

    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {
        final Intent i = new Intent(mainActivity, ShopsActivity.class);

        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromShopsActivityToShopDetailActivity(final ShopsActivity shopsActivity, final Shop shop) {
        final Intent i = new Intent(shopsActivity, ShopDetailActivity.class);
        i.putExtra(Constants.INTENT_KEY_SHOP_DETAIL, shop);
        shopsActivity.startActivity(i);
        return i;
    }
}
