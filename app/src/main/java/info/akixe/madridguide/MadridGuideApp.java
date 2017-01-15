package info.akixe.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class MadridGuideApp extends Application {

    private static WeakReference<Context> appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = new WeakReference<Context>(getApplicationContext());

        setupPicasso();


    }
    private void setupPicasso() {
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContext(){
        return appContext.get();
    }
}
