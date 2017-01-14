package info.akixe.madridguide.navigator;

import android.content.Intent;

import info.akixe.madridguide.activities.ActivitiesActivity;
import info.akixe.madridguide.activities.ActivityDetailActivity;
import info.akixe.madridguide.activities.MainActivity;
import info.akixe.madridguide.activities.ShopDetailActivity;
import info.akixe.madridguide.activities.ShopsActivity;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.util.Constants;

public class Navigator {

    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {
        final Intent i = new Intent(mainActivity, ShopsActivity.class);

        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromMainActivityToActivitiesActivity(final MainActivity mainActivity) {
        final Intent i = new Intent(mainActivity, ActivitiesActivity.class);

        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromShopsActivityToShopDetailActivity(final ShopsActivity shopsActivity, final Shop shop) {
        final Intent i = new Intent(shopsActivity, ShopDetailActivity.class);
        i.putExtra(Constants.INTENT_KEY_SHOP_DETAIL, shop);
        shopsActivity.startActivity(i);
        return i;
    }

    public static Intent navigateFromActivitiesActivityToActivityDetailActivity(ActivitiesActivity activitiesActivity, Activity activity) {
        final Intent i = new Intent(activitiesActivity, ActivityDetailActivity.class);
        i.putExtra(Constants.INTENT_KEY_ACTIVITY_DETAIL, activity);
        activitiesActivity.startActivity(i);
        return i;
    }
}
