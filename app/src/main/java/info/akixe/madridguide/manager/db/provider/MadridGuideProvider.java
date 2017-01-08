package info.akixe.madridguide.manager.db.provider;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import info.akixe.madridguide.manager.db.ShopDAO;
import info.akixe.madridguide.model.Shop;

public class MadridGuideProvider extends ContentProvider {
    public static final String MADRIDGUIDE_PROVIDER = "info.akixe.madridguide.provider";

    public static final Uri SHOPS_URI = Uri.parse("content://" + MADRIDGUIDE_PROVIDER + "/shops");

    // Create the constants used to differentiate between the different URI requests.
    private static final int ALL_SHOPS = 1;
    private static final int SINGLE_SHOP = 2;

    private static final UriMatcher uriMatcher;
    // Populate the UriMatcher object, where a URI ending in ÔelementsÕ will correspond to a request for all items, and Ôelements/[rowID]Õ represents a single row.
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "shops", ALL_SHOPS);
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "shops/#", SINGLE_SHOP);

    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String selection, String[] selectionArgs, String sortOrder) {

        ShopDAO dao = new ShopDAO(getContext());

        Cursor cursor = null;
        // If this is a row query, limit the result set to the passed in row.
        String rowID = null;
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP:
                rowID = uri.getPathSegments().get(1);
                cursor = dao.queryCursor(Long.parseLong(rowID));
                break;
            case ALL_SHOPS:
                cursor = dao.queryCursor();
                break;
            default: break;
        }
        // Return the result Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String type = null;

        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP:
                type = "vnd.android.cursor.item/vnd.info.akixe.madridguide.provider";
                break;
            case ALL_SHOPS:
                type = "vnd.android.cursor.dir/vnd.info.akixe.madridguide.provider";
                break;
            default: break;
        }

        return type;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        // Insert the values into the table
        ShopDAO dao = new ShopDAO(getContext());


        Shop shop = ShopDAO.getShopFromContentValues(contentValues);

        long id = dao.insert(shop);

        // Construct and return the URI of the newly inserted row.
        if (id == -1) { return null; }
        Uri insertedUri = null;
        switch (uriMatcher.match(uri)) {
            case ALL_SHOPS:
                // content://info.akixe.madridguide.provider/shops/1234
                insertedUri = ContentUris.withAppendedId(SHOPS_URI, id);
                break;
            default: break;
        }

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);
        getContext().getContentResolver().notifyChange(insertedUri, null);

        return insertedUri;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereFields) {
        ShopDAO dao = new ShopDAO(getContext());
        int deleteCount = 0;

        String rowID = null;
        // If this is a row URI, limit the deletion to the specified row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP:
                rowID = uri.getPathSegments().get(1);
                deleteCount = dao.delete(Long.parseLong(rowID));
                break;
            case ALL_SHOPS:
                dao.deleteAll();
                break;
            default:
                break;
        }

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the number of deleted items.
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        // TODO: 22/12/16 Terminar el update del provider en la práctica
        return 0;
    }
}
