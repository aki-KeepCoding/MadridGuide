package info.akixe.madridguide.interactors;


import android.content.Context;

import info.akixe.madridguide.manager.db.ActivityDAO;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Activity;

public class CacheAllActivitiesInteractor {

    public interface CacheAllActivitiesInteractorResponse {
        public void onResponse(boolean success);
    }

    public void execute(final Context context, final Activities activities, final CacheAllActivitiesInteractorResponse responder) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityDAO dao = new ActivityDAO(context);
                boolean success = false;
                for(Activity activity: activities.allActivities()){
                    success = dao.insert(activity) > 0;
                    if(!success) {
                        break;
                    }
                }

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        responder.onResponse(true);
                    }
                });

            }
        }).start();
    }
}



/*

public class CacheAllShopsInteractor {

    public interface CacheAllShopsInteractorResponse {
        public void onResponse(boolean success);
    }

    public void execute(final Context context, final Shops shops, final CacheAllShopsInteractorResponse responder) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);

                boolean success = false;
                for (Shop shop: shops.allShops()) {
                    success = dao.insert(shop) > 0;
                    if (!success) {
                        break;
                    }
                }

                Looper mainThread = Looper.getMainLooper();
                // mainThread.getThread().run
                // TODO: 24/12/16 Falta implementar la ejecución del response  de CacheAllShopsInteractor en el hilo principal
                if (responder != null) {
                    responder.onResponse(true);
                }
            }
        }).start();
    }
}
* */