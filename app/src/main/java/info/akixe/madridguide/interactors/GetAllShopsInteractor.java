package info.akixe.madridguide.interactors;


import android.content.Context;

import java.util.List;

import info.akixe.madridguide.manager.net.NetworkManager;
import info.akixe.madridguide.manager.net.ShopEntity;
import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;
import info.akixe.madridguide.model.mappers.ShopEntityShopMapper;

public class GetAllShopsInteractor {

    public interface GetAllShopsInteractorResponse {
        public void onResponse(Shops shops);
    }

    public void execute(final Context context, final GetAllShopsInteractorResponse responder) {

        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getShopsFromServer(new NetworkManager.GetShopsListener() {
            @Override
            public void getShopEntitiesSuccess(List<ShopEntity> result) {
                List<Shop> shops = new ShopEntityShopMapper().map(result);
                if (responder != null) {
                    responder.onResponse(Shops.build(shops));
                }
            }

            @Override
            public void getShopEntitiesError() {
                if (responder != null) {
                    responder.onResponse(null);
                }
            }
        });
    }
}
