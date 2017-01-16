package info.akixe.madridguide.util;

import com.google.android.gms.maps.model.LatLng;

public class Constants {

    public static String appName = "info.akixe.madridguide";
    public static final String INTENT_KEY_SHOP_DETAIL = appName + ".INTENT_KEY_SHOP_DETAIL";
    public static final String INTENT_KEY_ACTIVITY_DETAIL = appName + ".INTENT_KEY_ACTIVITY_DETAIL";
    public static final String PREF_KEY_LAST_CACHE_UPDATE_DATE = appName + ".LAST_CACHE_UPDATE_DATE";
    public static final int CACHE_VALIDITY_TIME_IN_MILISECONDS = 7 * 24 * 60 * 60 * 1000;
    public static final LatLng MADRID_LATLNG = new LatLng(40.415363, -3.707398);


}
