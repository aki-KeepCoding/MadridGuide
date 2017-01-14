package info.akixe.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import info.akixe.madridguide.interactors.CacheAllActivitiesInteractor;
import info.akixe.madridguide.interactors.CacheAllShopsInteractor;
import info.akixe.madridguide.interactors.GetAllActivitiesInteractor;
import info.akixe.madridguide.interactors.GetAllShopsInteractor;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Shops;

public class MadridGuideApp extends Application {

    private static WeakReference<Context> appContext;
    @Override
    public void onCreate() {
        super.onCreate();

        // inicializar cosas de la aplicación

        // Obtengo el Context de la aplicación
        appContext = new WeakReference<Context>(getApplicationContext());

        // test data
        // insertTestDataInDB();

        // Picasso
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
        // TODO: 26/12/16 Meter una barra de espera
        new GetAllShopsInteractor().execute(getApplicationContext(), new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
             @Override
             public void onResponse(Shops shops) {
                 new CacheAllShopsInteractor().execute(getApplicationContext(),
                         shops,
                         new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {
                            @Override
                            public void onResponse(boolean success) {}
                 });
             }
         });


        new GetAllActivitiesInteractor().execute(getApplicationContext(), new GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse() {
            @Override
            public void onResponse(Activities activities) {
                new CacheAllActivitiesInteractor().execute(getApplicationContext(), activities, new CacheAllActivitiesInteractor.CacheAllActivitiesInteractorResponse() {
                    @Override
                    public void onResponse(boolean success) {

                    }
                });
            }
        });
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContext(){
        return appContext.get();
    }
}
