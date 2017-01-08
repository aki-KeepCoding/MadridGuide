package info.akixe.madridguide;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import info.akixe.madridguide.manager.db.DBConstants;
import info.akixe.madridguide.manager.db.ShopDAO;
import info.akixe.madridguide.manager.db.provider.MadridGuideProvider;
import info.akixe.madridguide.model.Shop;

public class MadridGuideProviderTests extends AndroidTestCase {
    public void testQueryAllShops() {
        ContentResolver cr = getContext().getContentResolver();

        Cursor c = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        assertNotNull(c);
    }

    public void testInsertAShop() {
        final ContentResolver cr = getContext().getContentResolver();


        final Cursor beforeCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        final int beforeCount = beforeCursor.getCount();


        final Shop shop = new Shop(1, "Testing Shop");
        final Uri insertedURI = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));
        assertNotNull(insertedURI);

        final Cursor afterCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        final int afterCount = afterCursor.getCount();
        assertEquals(beforeCount + 1, afterCount);


    }
}
