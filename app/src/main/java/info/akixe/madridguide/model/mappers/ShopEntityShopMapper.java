package info.akixe.madridguide.model.mappers;


import java.util.LinkedList;
import java.util.List;

import info.akixe.madridguide.manager.net.ShopEntity;
import info.akixe.madridguide.model.Shop;

public class ShopEntityShopMapper {
    public List<Shop> map(List<ShopEntity> shopEntities) {
        List<Shop> shops = new LinkedList<>();

        for (ShopEntity entity: shopEntities) {
            Shop shop = new Shop(entity.getId(), entity.getName())
                    .setDescription(entity.getDescriptionES())
                    .setAddress(entity.getAddress())
                    .setImageUrl(entity.getImg())
                    .setLogoImgUrl(entity.getLogo_img())
                    .setLongitude(entity.getLongitude())
                    .setLatitude(entity.getLatitude());
            shops.add(shop);
        }

        return shops;
    }
}
