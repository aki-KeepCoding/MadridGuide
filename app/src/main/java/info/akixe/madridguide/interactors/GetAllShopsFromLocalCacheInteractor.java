package info.akixe.madridguide.interactors;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

import info.akixe.madridguide.manager.db.ShopDAO;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;

public class GetAllShopsFromLocalCacheInteractor {

    public interface OnGetAllShopsFromLocalCache {
        public void completion(Shops shops);
    }

    public void execute(final Context context, final OnGetAllShopsFromLocalCache backgroundTask) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);
                List<Shop> shopList = dao.query();
                final Shops shops = Shops.build(shopList);

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        backgroundTask.completion(shops);
                    }
                });

            }
        }).start();
    }
}


class MainThread {
    public static void run(final Runnable runnable) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });

    }
}
