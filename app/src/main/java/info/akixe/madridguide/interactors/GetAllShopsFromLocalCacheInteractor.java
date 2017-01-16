package info.akixe.madridguide.interactors;


import android.content.Context;

import java.util.List;

import info.akixe.madridguide.manager.db.ShopDAO;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;
import info.akixe.madridguide.util.MainThread;

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



