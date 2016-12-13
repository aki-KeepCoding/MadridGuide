package info.akixe.madridguide;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

public class MadridGuideApp extends Application {

    private static WeakReference<Context> appContext;
    @Override
    public void onCreate() {
        super.onCreate();

        // inicializar cosas de la aplicación

        // Obtengo el Context de la aplicación
        appContext = new WeakReference<Context>(getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContex(){
        return appContext.get();
    }
}
