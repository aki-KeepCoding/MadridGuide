package info.akixe.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.OkHttpDownloader;
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


        Picasso picasso =  new Picasso.Builder(this).downloader(new OkHttpDownloader(getCacheDir(), 250000000)).build();
        Picasso.setSingletonInstance(picasso);
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
