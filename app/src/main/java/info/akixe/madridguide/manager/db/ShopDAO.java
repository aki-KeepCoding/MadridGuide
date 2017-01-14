package info.akixe.madridguide.manager.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;

import static info.akixe.madridguide.manager.db.DBConstants.ALL_COLUMNS;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_SHOP_ADDRESS;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_SHOP_DESCRIPTION;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_SHOP_ID;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_SHOP_IMAGE_URL;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_SHOP_LATITUDE;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_SHOP_LOGO_IMAGE_URL;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_SHOP_LONGITUDE;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_SHOP_NAME;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_SHOP_URL;
import static info.akixe.madridguide.manager.db.DBConstants.TABLE_SHOP;

public class ShopDAO implements DAOPersistable<Shop> {
    private WeakReference<Context> context;
    DBHelper dbHelper;
    SQLiteDatabase db;


    public ShopDAO(Context context, DBHelper dbHelper) {
        this.context = new WeakReference<Context>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();
    }

    public ShopDAO(Context context) {
        this(context, DBHelper.getInstance(context));
    }

    @Override
    public long insert(@NonNull Shop shop) {

        if (shop == null) {
            return 0;
        }

        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = db.insert(TABLE_SHOP, null, this.getContentValues(shop));
            shop.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    public static ContentValues getContentValues(final @NonNull Shop shop) {
        final ContentValues contentValues = new ContentValues();

        if (shop == null) {
            return contentValues;
        }

        contentValues.put(KEY_SHOP_ADDRESS, shop.getAddress());
        contentValues.put(KEY_SHOP_DESCRIPTION, shop.getDescription());
        contentValues.put(KEY_SHOP_URL, shop.getUrl());
        contentValues.put(KEY_SHOP_IMAGE_URL, shop.getImageUrl());
        contentValues.put(KEY_SHOP_LOGO_IMAGE_URL, shop.getLogoImgUrl());
        contentValues.put(KEY_SHOP_LONGITUDE, shop.getLongitude());
        contentValues.put(KEY_SHOP_LATITUDE, shop.getLatitude());
        contentValues.put(KEY_SHOP_NAME, shop.getName());

        return contentValues;
    }

    public static @NonNull Shop getShopFromContentValues(final @NonNull ContentValues contentValues) {
        final Shop shop = new Shop(1, "");

        // TODO: 22/12/16 Poner los try..catch en el getShopFromContentValues (ver vid día 5 a las 21:09) Extraer a clase propia el getContentValues y getShopContentValues
        //shop.setId(contentValues.getAsInteger(KEY_SHOP_ID));
        shop.setName(contentValues.getAsString(KEY_SHOP_NAME));
        shop.setAddress(contentValues.getAsString(KEY_SHOP_ADDRESS));
        shop.setDescription(contentValues.getAsString(KEY_SHOP_DESCRIPTION));
        shop.setUrl(contentValues.getAsString(KEY_SHOP_URL));
        shop.setLogoImgUrl(contentValues.getAsString(KEY_SHOP_LOGO_IMAGE_URL));
        shop.setLongitude(contentValues.getAsFloat(KEY_SHOP_LONGITUDE));
        shop.setLatitude(contentValues.getAsFloat(KEY_SHOP_LATITUDE));

        return  shop;
    }

    @Override
    public void update(long id, @NonNull Shop data) {

    }

    @Override
    public int delete(long id) {
        // TODO: 13/12/16 Implementar transacciones para delete (ver el insert)
        return db.delete(TABLE_SHOP, KEY_SHOP_ID + " = " + id, null);
    }

    @Override
    public void deleteAll() {
        db.delete(TABLE_SHOP, null, null);
    }

    @Nullable
    @Override
    public @NonNull Cursor queryCursor() {
        Cursor c = db.query(TABLE_SHOP, ALL_COLUMNS, null, null, null, null, KEY_SHOP_ID);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor queryCursor(long id) {
        // TODO: 20/12/16 Implementar queryCursor(long id) de forma más bonita para la práctica (no repe código)
        Cursor c = db.query(TABLE_SHOP, ALL_COLUMNS, "ID = " + id, null, null, null, KEY_SHOP_ID);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    @Override
    public @Nullable Shop query(final long id) {
        Cursor c = db.query(TABLE_SHOP, ALL_COLUMNS, KEY_SHOP_ID + " = " + id, null, null, null, KEY_SHOP_ID);

        if (c != null && c.getCount() == 1) {
            c.moveToFirst();
        } else {
            return null;
        }

        Shop shop = getShop(c);

        return shop;
    }

    @NonNull
    public static Shop getShop(Cursor c) {
        // TODO: 22/12/16 Testear método estático getShop de ShopDAO
        long ident = c.getLong(c.getColumnIndex(KEY_SHOP_ID));
        String name = c.getString(c.getColumnIndex(KEY_SHOP_NAME));
        Shop shop = new Shop(ident, name);

        shop.setAddress(c.getString(c.getColumnIndex(KEY_SHOP_ADDRESS)));
        shop.setDescription(c.getString(c.getColumnIndex(KEY_SHOP_DESCRIPTION)));
        shop.setImageUrl(c.getString(c.getColumnIndex(KEY_SHOP_IMAGE_URL)));
        shop.setLogoImgUrl(c.getString(c.getColumnIndex(KEY_SHOP_LOGO_IMAGE_URL)));
        shop.setLatitude(c.getFloat(c.getColumnIndex(KEY_SHOP_LATITUDE)));
        shop.setLongitude(c.getFloat(c.getColumnIndex(KEY_SHOP_LONGITUDE)));
        shop.setUrl(c.getString(c.getColumnIndex(KEY_SHOP_URL)));
        return shop;
    }

    @NonNull
    public static Shops getShops(Cursor data) {
        List<Shop> shopList = new LinkedList<>();
        while(data.moveToNext()) {
            Shop shop = ShopDAO.getShop(data);
            shopList.add(shop);
        }
        return Shops.build(shopList);
    }

    @Nullable
    @Override
    public List<Shop> query() {

        Cursor c = queryCursor();

        if (c == null || !c.moveToFirst()) {
            return null;
        }

        List<Shop> shops = new LinkedList<>();
        do  {
            Shop shop = getShop(c);
            shops.add(shop);
        } while (c.moveToNext());
        return shops;
    }


}
