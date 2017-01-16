package info.akixe.madridguide.interactors;


import android.content.Context;

import info.akixe.madridguide.manager.db.ShopDAO;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;
import info.akixe.madridguide.util.MainThread;

public class CacheAllShopsInteractor {

    public interface CacheAllShopsInteractorResponse {
        public void onResponse(boolean success);
    }

    public void execute(final Context context,
                        final Shops shops,
                        final CacheAllShopsInteractorResponse responder) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);
                dao.deleteAll();
                boolean success = false;
                for (Shop shop: shops.all()) {
                    success = dao.insert(shop) > 0;
                    if (!success) {
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


