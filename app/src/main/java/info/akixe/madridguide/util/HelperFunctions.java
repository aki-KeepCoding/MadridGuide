package info.akixe.madridguide.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.util.Date;

public class HelperFunctions {

    static public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = false;

        if (activeNetwork != null) {
            isConnected = activeNetwork.isConnectedOrConnecting();
        }
        return isConnected;
    }


    static public boolean isCacheValid(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        long lastCacheUpdate = prefs.getLong(Constants.PREF_KEY_LAST_CACHE_UPDATE_DATE, 0);
        if (lastCacheUpdate == 0) {
            return false;
        }
        long actualTime = new Date().getTime();
        if (actualTime - lastCacheUpdate > Constants.CACHE_VALIDITY_TIME_IN_MILISECONDS){
            return false;
        }
        return true;
    }


    static public void updateCacheUpdateDate(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(Constants.PREF_KEY_LAST_CACHE_UPDATE_DATE, new Date().getTime());
        editor.commit();

    }


}
