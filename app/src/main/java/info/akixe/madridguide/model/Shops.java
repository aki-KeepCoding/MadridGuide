package info.akixe.madridguide.model;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import info.akixe.madridguide.model.base.IFilterable;
import info.akixe.madridguide.model.base.IIterable;
import info.akixe.madridguide.model.base.IUpdatable;

public class Shops implements IIterable<Shop>, IUpdatable<Shop>, IFilterable<Shops> {

    List<Shop> shops;

    public static @NonNull Shops build(@NonNull final List<Shop> shopList){
        Shops shops = new Shops(shopList);

        if (shopList == null) {
            shops.shops = new ArrayList<>();
        }

        return shops;
    }

    public static @NonNull Shops build(){
        return build(new ArrayList<Shop>());
    }

    private Shops(List<Shop> shops) {
        this.shops = shops;
    }

    private Shops() {
        shops = new ArrayList<>();
    }

    @Override
    public long size() {
        return shops.size();
    }

    @Override
    public Shop get(long index) {
        return shops.get((int)index);
    }

    @Override
    public List<Shop> all() {
        return shops;
    }

    @Override
    public void add(Shop shop) {
        shops.add(shop);
    }

    @Override
    public void delete(Shop shop) {
    shops.remove(shop);
    }

    @Override
    public void edit(Shop newShop, long index) {
        shops.set((int)index, newShop);
    }

    @Override
    public Shops filter(String query) {
        List<Shop> filteredShopList = new ArrayList<>();

        for (Shop shop: shops) {
            if (shop.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredShopList.add(shop);
            }
        }
        return Shops.build(filteredShopList);
    }
}
