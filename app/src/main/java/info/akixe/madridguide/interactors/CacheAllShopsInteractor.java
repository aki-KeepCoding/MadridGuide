package info.akixe.madridguide.interactors;


import android.content.Context;
import android.os.Looper;

import info.akixe.madridguide.manager.db.ShopDAO;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;

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


