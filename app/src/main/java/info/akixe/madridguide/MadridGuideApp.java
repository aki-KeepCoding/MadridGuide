package info.akixe.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import info.akixe.madridguide.manager.db.ShopDAO;
import info.akixe.madridguide.model.Shop;

public class MadridGuideApp extends Application {

    private static WeakReference<Context> appContext;
    @Override
    public void onCreate() {
        super.onCreate();

        // inicializar cosas de la aplicación

        // Obtengo el Context de la aplicación
        appContext = new WeakReference<Context>(getApplicationContext());

        // test data
        insertTestDataInDB();

        // Picasso
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

    }

    private void insertTestDataInDB() {
        ShopDAO dao = new ShopDAO(getApplicationContext());
        for (int i = 0; i < 20; i++) {
            Shop shop = new Shop(0, "Shop " + i).setLogoImgUrl("http://www.blog.amayapadilla.com/wp-content/uploads/2014/12/new-wall-e-box-sitting.jpg");
            dao.insert(shop);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContex(){
        return appContext.get();
    }
}
