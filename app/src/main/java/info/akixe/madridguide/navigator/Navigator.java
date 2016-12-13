package info.akixe.madridguide.navigator;

import android.content.Intent;

import info.akixe.madridguide.activities.MainActivity;
import info.akixe.madridguide.activities.ShopsActivity;

public class Navigator {

    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {
        final Intent i = new Intent(mainActivity, ShopsActivity.class);

        mainActivity.startActivity(i);

        return i;
    }

}
